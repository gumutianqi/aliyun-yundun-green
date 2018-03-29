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

import lombok.*;

/**
 * @author LarryKoo (larrykoo@126.com)
 * @description 文本反垃圾检测内容对象
 * @date 2018/3/28 22:49
 * @slogon 站在巨人的肩膀上
 * @since 1.0.0
 * <p>
 * https://help.aliyun.com/document_detail/53423.html?spm=a2c4g.11186623.6.573.rsXfC3
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TextTask extends AbstractTask {
    private static final long serialVersionUID = -8636069658859253148L;

    /**
     * build 一个默认的Task对象
     *
     * @param clientInfo
     * @param dataId
     * @param time
     * @return
     */
    public TextTask buildBase(ClientInfo clientInfo, String dataId, Long time) {
        this.setClientInfo(clientInfo);
        this.setDataId(dataId);
        this.setTime(time);
        return this;
    }

    /**
     * 必须
     * 待检测文本，最长4000个字符
     */
    private String content;

    /**
     * 可选
     * 内容类别，取值范围为[“post”, “reply”, “comment”, “title”, “others”]；也可以自定义的其他类型，但长度不超过64字节
     */
    private String category;

    /**
     * 可选
     * 操作类型，取值范围为[“new”, “edit”, “share”, “others”]；也可以自定义的其他操作类型，但长度不超过64字节
     */
    private String action;

    /**
     * 可选
     * 相关dataId；当contentType为reply或comment时，该字段设置相关的主贴或对应的comment的dataId
     */
    private String relatedDataId;

    /**
     * 可选
     * 相关字符串；当contentType为reply或comment时，该字段设置为主贴内容或对应的comment
     */
    private String relatedContent;
}
