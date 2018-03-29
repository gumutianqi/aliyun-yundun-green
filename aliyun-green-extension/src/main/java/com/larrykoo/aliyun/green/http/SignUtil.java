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
import com.aliyuncs.auth.BasicCredentials;
import com.aliyuncs.auth.HmacSHA1Signer;
import com.aliyuncs.auth.Signer;
import com.aliyuncs.http.ProtocolType;
import com.larrykoo.aliyun.green.constant.Constants;
import com.larrykoo.aliyun.green.constant.HttpHeader;
import com.larrykoo.aliyun.green.constant.SystemHeader;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author LarryKoo (larrykoo@126.com)
 * @description 签名工具
 * @date 2018/3/27 14:38
 * @slogon 站在巨人的肩膀上
 * @since 1.0.0
 */
@Slf4j
public class SignUtil {
    /**
     * 计算签名
     *
     * @param method
     * @param path
     * @param headers
     * @param querys
     * @param signHeaderPrefixList 自定义参与签名Header前缀
     * @param appKey
     * @param appSecret
     * @return 签名后的字符串
     */
    public static String sign(String method, String path,
                              Map<String, String> headers,
                              Map<String, String> querys,
                              List<String> signHeaderPrefixList,
                              String appKey, String appSecret) {
        try {
            StringBuilder sb = new StringBuilder();
            Signer signer = new HmacSHA1Signer();

            String signerString = buildStringToSign(method, path, headers, querys, signHeaderPrefixList);
            String signature = signer.signString(signerString, new BasicCredentials(appKey, appSecret));

            sb.append("acs").append(Constants.SPE6)
                    .append(appKey).append(Constants.SPE2)
                    .append(signature);
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 构建完整的待签名字符串
     *
     * @param method
     * @param path
     * @param headers
     * @param querys
     * @param signHeaderPrefixList
     * @return
     */
    private static String buildStringToSign(String method, String path,
                                            Map<String, String> headers,
                                            Map<String, String> querys,
                                            List<String> signHeaderPrefixList) throws UnsupportedEncodingException {
        StringBuilder sb = new StringBuilder();

        sb.append(method.toUpperCase()).append(Constants.LF);
        if (null != headers) {
            if (null != headers.get(HttpHeader.HTTP_HEADER_ACCEPT)) {
                sb.append(headers.get(HttpHeader.HTTP_HEADER_ACCEPT));
                sb.append(Constants.LF);
            }
            if (null != headers.get(HttpHeader.HTTP_HEADER_CONTENT_MD5)) {
                sb.append(headers.get(HttpHeader.HTTP_HEADER_CONTENT_MD5));
                sb.append(Constants.LF);
            }
            if (null != headers.get(HttpHeader.HTTP_HEADER_CONTENT_TYPE)) {
                sb.append(headers.get(HttpHeader.HTTP_HEADER_CONTENT_TYPE));
                sb.append(Constants.LF);
            }
            if (null != headers.get(HttpHeader.HTTP_HEADER_DATE)) {
                sb.append(headers.get(HttpHeader.HTTP_HEADER_DATE));
                sb.append(Constants.LF);
            }
        }
        sb.append(buildHeaders(headers, signHeaderPrefixList));
        sb.append(buildResource(path, querys));

        if (log.isDebugEnabled()) {
            log.debug("Signature string is complete: \n{}", sb.toString());
        }

        return sb.toString();
    }

    /**
     * 构建待签名Path+Query
     *
     * @param path
     * @param querys
     * @return 待签名
     */
    private static String buildResource(String path, Map<String, String> querys) {
        StringBuilder sb = new StringBuilder();

        if (!StringUtils.isBlank(path)) {
            sb.append(path);
        }
        Map<String, String> sortMap = new TreeMap<>();
        if (null != querys) {
            for (Map.Entry<String, String> query : querys.entrySet()) {
                if (!StringUtils.isBlank(query.getKey())) {
                    sortMap.put(query.getKey(), query.getValue());
                }
            }
        }

        StringBuilder sbParam = new StringBuilder();
        for (Map.Entry<String, String> item : sortMap.entrySet()) {
            if (!StringUtils.isBlank(item.getKey())) {
                if (0 < sbParam.length()) {
                    sbParam.append(Constants.SPE3);
                }
                sbParam.append(item.getKey());
                if (!StringUtils.isBlank(JSON.toJSONString(item.getValue()))) {
                    sbParam.append(Constants.SPE4).append(JSON.toJSONString(item.getValue()));
                }
            }
        }
        if (0 < sbParam.length()) {
            sb.append(Constants.SPE5);
            sb.append(sbParam);
        }

        return sb.toString();
    }

    /**
     * 构建待签名Http头
     *
     * @param headers              请求中所有的Http头
     * @param signHeaderPrefixList 自定义参与签名Header前缀
     * @return 待签名Http头
     */
    private static String buildHeaders(Map<String, String> headers, List<String> signHeaderPrefixList) {
        StringBuilder sb = new StringBuilder();

        if (null != signHeaderPrefixList) {
            signHeaderPrefixList.remove(HttpHeader.HTTP_HEADER_ACCEPT);
            signHeaderPrefixList.remove(HttpHeader.HTTP_HEADER_CONTENT_MD5);
            signHeaderPrefixList.remove(HttpHeader.HTTP_HEADER_CONTENT_TYPE);
            signHeaderPrefixList.remove(HttpHeader.HTTP_HEADER_DATE);
            Collections.sort(signHeaderPrefixList);
            if (null != headers) {
                Map<String, String> sortMap = new TreeMap<>();
                sortMap.putAll(headers);
                StringBuilder signHeadersStringBuilder = new StringBuilder();
                for (Map.Entry<String, String> header : sortMap.entrySet()) {
                    if (isHeaderToSign(header.getKey(), signHeaderPrefixList)) {
                        sb.append(header.getKey());
                        sb.append(Constants.SPE2);
                        if (!StringUtils.isBlank(header.getValue())) {
                            sb.append(header.getValue());
                        }
                        sb.append(Constants.LF);

                        if (0 < signHeadersStringBuilder.length()) {
                            signHeadersStringBuilder.append(Constants.SPE1);
                        }
                        signHeadersStringBuilder.append(header.getKey());
                    }
                }
                headers.put(SystemHeader.X_CA_SIGNATURE_HEADERS, signHeadersStringBuilder.toString());
            }
        }
        return sb.toString();
    }

    /**
     * Http头是否参与签名 return
     */
    private static boolean isHeaderToSign(String headerName, List<String> signHeaderPrefixList) {
        if (StringUtils.isBlank(headerName)) {
            return false;
        }

        if (headerName.startsWith(Constants.CA_HEADER_TO_SIGN_PREFIX_SYSTEM)) {
            return true;
        }

        if (null != signHeaderPrefixList) {
            for (String signHeaderPrefix : signHeaderPrefixList) {
                if (headerName.equalsIgnoreCase(signHeaderPrefix)) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * 转换获取接口地址 Host
     *
     * @param protocolType
     * @param domain       如：green.cn-shanghai.aliyuncs.com
     * @param path         如：/green/text/scan
     * @return
     */
    public static String getSDKHost(ProtocolType protocolType, String domain, String path) {
        return String.format("%s:%s%s", protocolType.toString(), domain, path);
    }

    /**
     * 转换 GMT 格式的时间字符串
     *
     * @param headers
     */
    public static void writeHeaderDate(Map<String, String> headers) {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss 'GMT'", Locale.US);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        headers.put(HttpHeader.HTTP_HEADER_DATE, sdf.format(new Date()));
    }
}
