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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author LarryKoo (larrykoo@126.com)
 * @description 检测文本是否为违规信息。请求body是一个结构体
 * @date 2018/3/28 10:15
 * @slogon 站在巨人的肩膀上
 * @since 1.0.0
 *
 * https://help.aliyun.com/document_detail/53423.html?spm=a2c4g.11186623.6.573.rsXfC3
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BodyRequest implements Serializable {
    private static final long serialVersionUID = -6275630412509418331L;

    /**
     * 可选
     * 业务类型，调用方从云盾内容安全申请所得。主要针对图片安全监测
     * 每个bizType对应不同的算法/模型。根据配置，后端可根据该字段对请求做不同处理。属于高级用法
     */
    private String bizType;

    /**
     * 必须
     * 字符串数组，场景定义参考1.1小节；
     * 反垃圾检测，scenes 请填写 antispam；
     * 关键词检测，scenes 请填写 keyword。
     */
    private List<String> scenes;

    /**
     * 必须
     * 文本检测任务列表；每个元素是个结构体，最多支持100个，即100段文本的检测.参见下表。
     */
    private List<AbstractTask> tasks;

    /**
     * 转换对象为 JSON 字符串
     *
     * @return
     */
    public String toJSON() {
        return JSON.toJSONString(this);
    }

    /**
     * 转换对象为 MAP 对象
     *
     * @return
     */
    public Map<String, String> toMap() {
        return JSON.parseObject(this.toJSON(), Map.class);
    }
}
