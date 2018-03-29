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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * @author LarryKoo (larrykoo@126.com)
 * @description Alibaba Request
 * @date 2018/3/27 13:49
 * @slogon 站在巨人的肩膀上
 * @since 1.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlibabaRequest {

    /**
     * （必选）HTTP 请求方式
     */
    private String method;

    /**
     * （可选）Protocol 协议，HTTP/HTTPS
     */
    private String protocolType;

    /**
     * （必选）Host
     */
    private String host;

    /**
     * （必选）Path
     */
    private String path;

    /**
     * （必选)APP KEY
     */
    private String appKey;

    /**
     * （必选）APP密钥
     */
    private String appSecret;

    /**
     * （必选）超时时间,单位毫秒,设置零默认使用com.aliyun.apigateway.demo.constant.Constants.DEFAULT_TIMEOUT
     */
    private int timeout;

    /**
     * （可选） HTTP头
     */
    private Map<String, String> headers;

    /**
     * （可选） Querys
     */
    private Map<String, String> querys;

    /**
     * （可选）表单参数
     */
    private Map<String, String> bodys;

    /**
     * （可选）字符串Body体
     */
    private String stringBody;

    /**
     * （可选）字节数组类型Body体
     */
    private byte[] bytesBody;

    /**
     * （可选）自定义参与签名Header前缀
     */
    private List<String> signHeaderPrefixList;

    /**
     * 构建发起请求的
     *
     * @return
     */
    public String getRequestURI() {
        return String.format("%s:%s%s", protocolType, host, path);
    }
}
