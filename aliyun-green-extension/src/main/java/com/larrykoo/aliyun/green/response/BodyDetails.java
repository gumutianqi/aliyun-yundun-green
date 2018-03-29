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
 * @description 命中风险的详细信息，details中包含的元素对象
 * @date 2018/3/29 14:22
 * @slogon 站在巨人的肩膀上
 * @since 1.0.0
 * <p>
 * https://help.aliyun.com/document_detail/53423.html?spm=a2c4g.11186623.6.573.rsXfC3
 */
@Data
public class BodyDetails implements Serializable {
    private static final long serialVersionUID = -7864168960942443969L;

    /**
     * 字符串	必须
     * 文本命中风险的分类
     */
    private String label;

    /**
     * 数组	可须
     * 命中该风险的上下文信息
     */
    private List<DetailContext> contexts;


    @Data
    private class DetailContext implements Serializable {
        private static final long serialVersionUID = 918029081406396644L;

        /**
         * 字符串	必须
         * 命中风险的内容
         */
        private String context;

        /**
         * 字符串	可选
         * 命中自定义词库，才有本字段。值为创建词库时填写的词库名称
         */
        private String libName;

        /**
         * 字符串	可选
         * 命中行为规则，才有该字段。可能取值 user_id,ip,umid,content
         */
        private String ruleType;
    }
}
