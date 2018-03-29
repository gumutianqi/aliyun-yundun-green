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
package com.larrykoo.aliyun.green.scenelabel;

/**
 * @author LarryKoo (larrykoo@126.com)
 * @description 垃圾文本场景分类（label）
 * @date 2018/3/29 14:55
 * @slogon 站在巨人的肩膀上
 * @since 1.0.0
 * <p>
 * https://help.aliyun.com/document_detail/53423.html?spm=a2c4g.11186623.6.573.rsXfC3
 * <p>
 * 垃圾检测会结合行为、内容采用多维度、多种模型、多种检测手段对文本进行垃圾与否的识别，
 * 识别色情、广告、灌水、渉政、辱骂等风险。
 * 用户可以自定义敏感词，自定义关键词请前往云盾内容安全控制台添加.
 */
public enum TextAntispam {
    NORMAL("normal", "正常文本"),
    SPAM("spam", "含垃圾信息"),
    AD("ad", "广告"),
    POLITICS("politics", "渉政"),
    TERRORISM("terrorism", "暴恐"),
    ABUSE("abuse", "辱骂"),
    PORN("porn", "色情"),
    FLOOD("flood", "灌水"),
    CONTRABAND("contraband", "违禁"),
    MEANINGLESS("meaningless", "无意义"),
    CUSTOMIZED("customized", "自定义(比如命中自定义关键词)"),


    ;

    /**
     * 分类 Label
     */
    private final String label;

    private final String description;

    TextAntispam(String label, String description) {
        this.label = label;
        this.description = description;
    }

    @Override
    public String toString() {
        return label;
    }

    public String toDescription() {
        return description;
    }
}
