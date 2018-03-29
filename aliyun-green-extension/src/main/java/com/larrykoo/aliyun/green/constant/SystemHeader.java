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
 * @description 系统HTTP头常量
 * @date 2018/3/27 14:16
 * @slogon 站在巨人的肩膀上
 * @since 1.0.0
 *
 * https://help.aliyun.com/document_detail/53413.html?spm=a2c4g.11186623.6.564.lObH3h
 */
public class SystemHeader {

    /**
     * 接口版本
     */
    public static final String X_ACS_VERSION = "x-acs-version";

    /**
     * 请求放重放Nonce,15分钟内保持唯一,建议使用UUID
     */
    public static final String X_ACS_SIGNATURE_NONCE = "x-acs-signature-nonce";

    /**
     * 签名版本
     */
    public static final String X_ACS_SIGNATURE_VERSION = "x-acs-signature-version";

    /**
     * 签名方法，目前只支持: HMAC-SHA1
     */
    public static final String X_ACS_SIGNATURE_METHOD = "x-acs-signature-method";

    /**
     * 所有参与签名的 Header
     */
    public static final String X_CA_SIGNATURE_HEADERS = "signature-headers";
}
