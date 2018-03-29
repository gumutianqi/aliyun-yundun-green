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
package com.larrykoo.aliyun.green.constant;

/**
 * @author LarryKoo (larrykoo@126.com)
 * @description HTTP头常量
 * @date 2018/3/27 14:11
 * @slogon 站在巨人的肩膀上
 * @since 1.0.0
 * <p>
 * https://help.aliyun.com/document_detail/53413.html?spm=a2c4g.11186623.6.564.lObH3h
 */
public class HttpHeader {
    /**
     * 请求Header Accept
     */
    public static final String HTTP_HEADER_ACCEPT = "Accept";
    /**
     * 请求Body内容MD5 Header
     */
    public static final String HTTP_HEADER_CONTENT_MD5 = "Content-MD5";
    /**
     * 请求Header Content-Type
     */
    public static final String HTTP_HEADER_CONTENT_TYPE = "Content-Type";
    /**
     * 请求Header UserAgent
     */
    public static final String HTTP_HEADER_USER_AGENT = "User-Agent";
    /**
     * 请求Header Date
     */
    public static final String HTTP_HEADER_DATE = "Date";

    /**
     * 认证方式，取值格式为：”acs” + “ “ + AccessKeyId + “:” + signature。
     * 其中AccessKeyId从阿里云控制台申请所得，而signature为请求签名。
     */
    public static final String HTTP_AUTHORIZATION = "Authorization";

    /**
     * 内容安全接口版本，当前版本为：2017-01-12
     */
    public static final String X_ACS_VERSION = "2017-01-12";

    /**
     * 签名版本，目前取值：1.0
     */
    public static final String X_ACS_SIGNATURE_VERSION = "1.0";

    /**
     * 接受的返回类型，目前只支持JSON：application/json
     */
    public static final String HTTP_HEADER_ACCEPT_VALUE = "application/json";

    /**
     * 当前请求body的数据类型，目前只支持JSON：application/json
     */
    public static final String HTTP_HEADER_CONTENT_TYPE_VALUE = "application/json; charset=utf-8";

}
