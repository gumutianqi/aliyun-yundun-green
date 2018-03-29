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
 * @description results 中包含的元素对象
 * @date 2018/3/29 14:20
 * @slogon 站在巨人的肩膀上
 * @since 1.0.0
 * <p>
 * https://help.aliyun.com/document_detail/53423.html?spm=a2c4g.11186623.6.573.rsXfC3
 */
@Data
public class BodyResults implements Serializable {
    private static final long serialVersionUID = -8642825998363012459L;
    /**
     * 字符串	    必须
     * 风险场景，和传递进来的场景对应
     */
    private String scene;

    /**
     * 字符串  	必须
     * 建议用户处理，取值范围：[“pass”, “review”, “block”], pass:文本正常，review：需要人工审核，block：文本违规，可以直接删除或者做限制处理
     */
    private String suggestion;

    /**
     * 字符串	    必须
     * 该文本的分类，取值范围参考1.1小节
     */
    private String label;

    /**
     * 浮点数	    必须
     * 结果为该分类的概率；值越高，越趋于该分类；取值为[0.00-100.00], 分值仅供参考，您只需要关注label和suggestion的取值即可
     */
    private Float rate;

    /**
     * JSON对象	可选
     * 附加信息，比如命中了您自定义的词库,返回词库code.该值将来可能会调整，建议不要在业务上进行依赖
     */
    private Object extras;

    /**
     * 数组	可选
     * 命中风险的详细信息。参见下表
     */
    private List<BodyDetails> details;
}
