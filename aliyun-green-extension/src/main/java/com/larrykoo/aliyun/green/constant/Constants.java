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
 * @description 常量
 * @date 2018/3/27 14:07
 * @slogon 站在巨人的肩膀上
 * @since 1.0.0
 */
public class Constants {
    /**
     * 签名算法 HMAC_SHA256
     */
    public static final String HMAC_SHA256 = "HmacSHA256";

    /**
     * 签名算法 HMAC_SHA1
     */
    public static final String HMAC_SHA1 = "HMAC-SHA1";

    /**
     * 编码UTF-8
     */
    public static final String ENCODING = "UTF-8";

    /**
     * UserAgent
     */
    public static final String USER_AGENT = "sxw/aliyun/java";

    /**
     * 换行符
     */
    public static final String LF = "\n";

    /**
     * 串联符
     */
    public static final String SPE1 = ",";

    /**
     * 示意符
     */
    public static final String SPE2 = ":";

    /**
     * 连接符
     */
    public static final String SPE3 = "&";

    /**
     * 赋值符
     */
    public static final String SPE4 = "=";

    /**
     * 问号符
     */
    public static final String SPE5 = "?";

    /**
     * 空格
     */
    public static final String SPE6 = " ";

    /**
     * 默认请求超时时间,单位毫秒
     */
    public static final int DEFAULT_TIMEOUT = 1000;

    /**
     * 参与签名的系统Header前缀,只有指定前缀的Header才会参与到签名中
     */
    public static final String CA_HEADER_TO_SIGN_PREFIX_SYSTEM = "x-acs-";
}
