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
package com.larrykoo.aliyun.green.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author LarryKoo (larrykoo@126.com)
 * @description 阿里云 Restful 返回结果通用 Response 对象
 * @date 2018/3/29 15:36
 * @slogon 站在巨人的肩膀上
 * @since 1.0.0
 */
@Data
public class AlibabaResponse implements Serializable {
    private static final long serialVersionUID = -8393386786461621867L;

    /**
     * HTTP 请求返回Code，200 为成功
     */
    private Integer code;

    /**
     * HTTP 请求成功是返回的 Data 数据
     * 返回body中的Data字段是JSON数组，对应 com.larrykoo.aliyun.green.response.BodyResponse
     */
    private List<Object> data;

    /**
     * HTTP 请求返回情况描述，成功时为OK，失败时为异常详情描述
     */
    private String msg;

    /**
     * HTTP 请求唯一ID，用于接口调用追溯
     */
    private String requestId;
}
