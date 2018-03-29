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
package com.larrykoo.aliyun.green.request;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.io.Serializable;

/**
 * @author LarryKoo (larrykoo@126.com)
 * @description 文本检测任务列表；每个元素是个结构体，最多支持100个，即100段文本的检测.参见下表。
 * @date 2018/3/28 09:43
 * @slogon 站在巨人的肩膀上
 * @since 1.0.0
 *
 * https://help.aliyun.com/document_detail/53423.html?spm=a2c4g.11186623.6.573.rsXfC3
 */
@Data
public abstract class AbstractTask implements Serializable {
    private static final long serialVersionUID = 3207498889598342922L;
    /**
     * 可选
     * 客户端信息，参考[调用方式/公共请求参数/公共查询参数]小节中ClientInfo结构体描述。
     * 服务器会把[调用方式/公共请求参数/公共查询参数]小节中全局的clientInfo和这里的独立的clientInfo合并。
     * 独立的clientInfo优先级更高。
     */
    private ClientInfo clientInfo;

    /**
     * 可选
     * 调用者通常保证一次请求中，所有的dataId不重复
     */
    private String dataId;

    /**
     * 可选
     * 内容创建/编辑时间，单位ms
     */
    private Long time;


    /**
     * 转换对象为 JSON 字符串
     *
     * @return
     */
    public String toJSON() {
        return JSON.toJSONString(this);
    }
}
