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
 * @description 图片Logo识别 场景分类（label）
 * @date 2018/3/29 19:27
 * @slogon 站在巨人的肩膀上
 * @since 1.0.0
 * <p>
 * https://help.aliyun.com/document_detail/65581.html?spm=a2c4g.11186623.6.578.izfcoI
 */
public enum ImageLogoLabel {

    NORMAL("normal", "正常图片"),
    TV("TV", "带有管控logo的图片"),;

    /**
     * 分类 Label
     */
    private final String label;

    private final String description;

    ImageLogoLabel(String label, String description) {
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
