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

import java.io.Serializable;
import java.util.List;

/**
 * @author LarryKoo (larrykoo@126.com)
 * @description 返回结果 ResponseBody 对象
 * @date 2018/3/28 09:47
 * @slogon 站在巨人的肩膀上
 * @since 1.0.0
 * <p>
 * https://help.aliyun.com/document_detail/53423.html?spm=a2c4g.11186623.6.573.rsXfC3
 */
public class BodyResponse implements Serializable {
    private static final long serialVersionUID = 713968840222708435L;

    /**
     * 必须	错误码，和http的status code一致
     */
    private String code;

    /**
     * 必须	错误描述信息
     */
    private String msg;

    /**
     * 必须	云盾内容安全服务器返回的唯一标识该检测任务的ID
     */
    private String taskId;

    /**
     * 可选	对应的请求中的dataId
     */
    private String dataId;

    /**
     * 可选	对应的请求中的content
     */
    private String content;

    /**
     * 必须	对应的请求中的url
     */
    private String url;

    /**
     * 当成功时（code == 200）,该结果的包含一个或多个元素。每个元素是个结构体。参见下表。
     */
    private List<BodyResults> results;
}
