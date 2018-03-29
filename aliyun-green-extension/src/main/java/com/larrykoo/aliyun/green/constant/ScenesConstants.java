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
package com.larrykoo.aliyun.green.constant;

/**
 * @author LarryKoo (larrykoo@126.com)
 * @description 字符串数组，场景定义
 * @date 2018/3/28 10:33
 * @slogon 站在巨人的肩膀上
 * @since 1.0.0
 * <p>
 * https://help.aliyun.com/document_detail/28427.html?spm=a2c4g.11186623.6.561.eRP8Xn
 */
public enum ScenesConstants {

    /**
     * 文本反垃圾场景
     */
    SCENES_TEXT_ANTISPAM("antispam", "反垃圾检测，scenes 请填写 antispam"),
    SCEENS_TEXT_KEYWORD("keyword", "关键词检测，scenes 请填写 keyword"),

    /**
     * 图片智能检测场景
     */
    SCENES_IMAGE_PORN("porn", "图片智能鉴黄"),
    SCENES_IMAGE_TERRORISM("terrorism", "图片暴恐涉政"),
    SCENES_IMAGE_OCR("ocr", "OCR图文识别"),
    SCENES_IMAGE_SFACE("sface", "图片人脸识别"),
    SCENES_IMAGE_AD("ad", "图片广告识别"),
    SCENES_IMAGE_QRCODE("qrcode", "图片二维码识别"),
    SCENES_IMAGE_LOGO("logo", "图片logo识别"),
    SCENES_IMAGE_LIVE("live", "图片不良场景识别");

    /**
     * 分类 Label
     */
    private final String label;

    private final String description;

    ScenesConstants(String label, String description) {
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
