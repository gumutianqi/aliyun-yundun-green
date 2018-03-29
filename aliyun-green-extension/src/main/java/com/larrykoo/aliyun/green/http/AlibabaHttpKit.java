/**
 * Copyright (c) 2009-2016, LarryKoo (gumutianqi@gmail.com)
 * Created on 2018/3/29
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.larrykoo.aliyun.green.http;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.http.HttpResponse;
import com.larrykoo.aliyun.green.constant.Constants;
import com.larrykoo.aliyun.green.constant.ScenesConstants;
import com.larrykoo.aliyun.green.constant.HttpHeader;
import com.larrykoo.aliyun.green.constant.SystemHeader;
import com.larrykoo.aliyun.green.response.AlibabaResponse;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author LarryKoo (larrykoo@126.com)
 * @description 阿里云SDK HTTP 调用的OkHttp实现
 * @date 2018/3/27 13:54
 * @slogon 站在巨人的肩膀上
 * @since 1.0.0
 */
@Slf4j
public class AlibabaHttpKit implements IAlibabaHttp {

    public static final MediaType MEDIA_JSON = MediaType.parse("application/json; charset=utf-8");

    private final OkHttpClient client = new OkHttpClient();

    @Override
    public HttpResponse httpGet(String host, String path, int connectTimeout, Map<String, String> headers, Map<String, String> querys, List<String> signHeaderPrefixList, String appKey, String appSecret) throws Exception {
        return null;
    }

    @Override
    public AlibabaResponse httpPost(AlibabaRequest alibabaRequest) throws Exception {
        String bodyBase64 = DatatypeConverter.printBase64Binary(DigestUtils.md5(alibabaRequest.getStringBody()));

        Map<String, String> headers = this.initialBasicHeader(alibabaRequest.getMethod(), alibabaRequest.getPath(),
                alibabaRequest.getHeaders(), alibabaRequest.getQuerys(), bodyBase64,
                alibabaRequest.getSignHeaderPrefixList(), alibabaRequest.getAppKey(), alibabaRequest.getAppSecret());

        // TMD 搞了这么多事儿出来，终于可以发起请求了
        RequestBody requestBody = RequestBody.create(MEDIA_JSON, alibabaRequest.getStringBody());
        Request request = new Request.Builder()
                .url(alibabaRequest.getRequestURI())
                .headers(Headers.of(headers))
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            return JSON.parseObject(response.body().string(), AlibabaResponse.class);
        } else {
            log.error("The request failed: \nMessage:{} \nBody:\n{}", response.message(), response.body());
        }
        return null;
    }


    @Override
    public HttpResponse httpPut(String host, String path, int connectTimeout, Map<String, String> headers, Map<String, String> querys, String body, List<String> signHeaderPrefixList, String appKey, String appSecret) throws Exception {
        return null;
    }

    @Override
    public HttpResponse httpPut(String host, String path, int connectTimeout, Map<String, String> headers, Map<String, String> querys, byte[] bodys, List<String> signHeaderPrefixList, String appKey, String appSecret) throws Exception {
        return null;
    }

    @Override
    public HttpResponse httpDelete(String host, String path, int connectTimeout, Map<String, String> headers, Map<String, String> querys, List<String> signHeaderPrefixList, String appKey, String appSecret) throws Exception {
        return null;
    }

    @Override
    public String initUrl(String host, String path, Map<String, String> querys) throws UnsupportedEncodingException {
        StringBuilder sbUrl = new StringBuilder();
        sbUrl.append(host);
        if (!StringUtils.isBlank(path)) {
            sbUrl.append(path);
        }
        if (null != querys) {
            StringBuilder sbQuery = new StringBuilder();
            for (Map.Entry<String, String> query : querys.entrySet()) {
                if (0 < sbQuery.length()) {
                    sbQuery.append(Constants.SPE3);
                }
                if (StringUtils.isBlank(query.getKey()) && !StringUtils.isBlank(query.getValue())) {
                    sbQuery.append(query.getValue());
                }
                if (!StringUtils.isBlank(query.getKey())) {
                    sbQuery.append(query.getKey());
                    if (!StringUtils.isBlank(query.getValue())) {
                        sbQuery.append(Constants.SPE4);
                        sbQuery.append(URLEncoder.encode(query.getValue(), Constants.ENCODING));
                    }
                }
            }
            if (0 < sbQuery.length()) {
                sbUrl.append(Constants.SPE5).append(sbQuery);
            }
        }

        return sbUrl.toString();
    }

    @Override
    public Map<String, String> initialBasicHeader(String method, String path, Map<String, String> headers, Map<String, String> querys,
                                                  String bodyMd5Base64, List<String> signHeaderPrefixList,
                                                  String appKey, String appSecret) throws Exception {
        if (headers == null) {
            headers = new HashMap<>(16);
        }
        try {
            // 1. 构建请求 header 对象
            headers.put(HttpHeader.HTTP_HEADER_ACCEPT, HttpHeader.HTTP_HEADER_ACCEPT_VALUE);
            headers.put(HttpHeader.HTTP_HEADER_CONTENT_TYPE, HttpHeader.HTTP_HEADER_CONTENT_TYPE_VALUE);
            headers.put(SystemHeader.X_ACS_VERSION, HttpHeader.X_ACS_VERSION);
            headers.put(SystemHeader.X_ACS_SIGNATURE_VERSION, HttpHeader.X_ACS_SIGNATURE_VERSION);
            headers.put(SystemHeader.X_ACS_SIGNATURE_METHOD, Constants.HMAC_SHA1);
            headers.put(SystemHeader.X_ACS_SIGNATURE_NONCE, UUID.randomUUID().toString());
            headers.put(HttpHeader.HTTP_HEADER_CONTENT_MD5, bodyMd5Base64);
            SignUtil.writeHeaderDate(headers);

            // 2. 对请求进行阿里云要求的签名
            String authorization = SignUtil.sign(method, path,
                    headers, querys, signHeaderPrefixList, appKey, appSecret);

            if (log.isDebugEnabled()) {
                log.debug("Signature string as follows：{}", authorization);
            }

            // 3. 将签名字符串放入 header 当中
            headers.put(HttpHeader.HTTP_AUTHORIZATION, authorization);

            if (log.isDebugEnabled()) {
                log.debug("The request Header is as follows: \n{}", JSON.toJSONString(headers, true));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return headers;
    }
}
