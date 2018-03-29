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

import com.aliyuncs.http.HttpResponse;
import com.larrykoo.aliyun.green.response.AlibabaResponse;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

/**
 * @author LarryKoo (larrykoo@126.com)
 * @description 阿里云 SDK HTTP 工具接口
 * @date 2018/3/27 13:56
 * @slogon 站在巨人的肩膀上
 * @since 1.0.0
 */
public interface IAlibabaHttp {

    /**
     * HTTP GET 查询
     *
     * @param host
     * @param path
     * @param connectTimeout
     * @param headers
     * @param querys
     * @param signHeaderPrefixList
     * @param appKey
     * @param appSecret
     * @return
     * @throws Exception
     */
    HttpResponse httpGet(String host,
                         String path,
                         int connectTimeout,
                         Map<String, String> headers,
                         Map<String, String> querys,
                         List<String> signHeaderPrefixList,
                         String appKey, String appSecret) throws Exception;

    /**
     * HTTP POST
     *
     * @param alibabaRequest
     * @return GreenRequestBody
     * @throws Exception
     */
    AlibabaResponse httpPost(AlibabaRequest alibabaRequest) throws Exception;

    /**
     * HTTP PUT 字符串
     *
     * @param host
     * @param path
     * @param connectTimeout
     * @param headers
     * @param querys
     * @param body
     * @param signHeaderPrefixList
     * @param appKey
     * @param appSecret
     * @return
     * @throws Exception
     */
    HttpResponse httpPut(String host,
                         String path,
                         int connectTimeout,
                         Map<String, String> headers,
                         Map<String, String> querys,
                         String body, List<String> signHeaderPrefixList,
                         String appKey, String appSecret) throws Exception;

    /**
     * HTTP PUT字节数组
     *
     * @param host
     * @param path
     * @param connectTimeout
     * @param headers
     * @param querys
     * @param bodys
     * @param signHeaderPrefixList
     * @param appKey
     * @param appSecret
     * @return
     * @throws Exception
     */
    HttpResponse httpPut(String host,
                         String path,
                         int connectTimeout,
                         Map<String, String> headers,
                         Map<String, String> querys,
                         byte[] bodys,
                         List<String> signHeaderPrefixList,
                         String appKey, String appSecret) throws Exception;

    /**
     * HTTP DELETE
     *
     * @param host
     * @param path
     * @param connectTimeout
     * @param headers
     * @param querys
     * @param signHeaderPrefixList
     * @param appKey
     * @param appSecret
     * @return
     * @throws Exception
     */
    HttpResponse httpDelete(String host,
                            String path,
                            int connectTimeout,
                            Map<String, String> headers,
                            Map<String, String> querys,
                            List<String> signHeaderPrefixList,
                            String appKey, String appSecret) throws Exception;

    /**
     * URL 编码处理
     *
     * @param host
     * @param path
     * @param querys
     * @return
     * @throws UnsupportedEncodingException
     */
    String initUrl(String host, String path, Map<String, String> querys) throws UnsupportedEncodingException;

    /**
     * 初始化基础Header
     *
     * @param method
     * @param path
     * @param headers
     * @param querys
     * @param bodyMd5Base64
     * @param signHeaderPrefixList
     * @param appKey
     * @param appSecret
     * @return
     * @throws Exception
     */
    Map<String, String> initialBasicHeader(String method, String path, Map<String, String> headers, Map<String, String> querys,
                                           String bodyMd5Base64, List<String> signHeaderPrefixList,
                                           String appKey, String appSecret) throws Exception;
}
