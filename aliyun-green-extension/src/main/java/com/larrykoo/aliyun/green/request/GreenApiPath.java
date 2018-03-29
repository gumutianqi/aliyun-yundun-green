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

/**
 * @author LarryKoo (larrykoo@126.com)
 * @description 云盾，内容安全 API Path
 * @date 2018/3/29 17:02
 * @slogon 站在巨人的肩膀上
 * @since 1.0.0
 */
public class GreenApiPath {

    /**
     * 文本检测
     */
    public static final String TEXT_SCAN = "/green/text/scan";

    /**
     * 文本检测反馈接口
     */
    public static final String TEXT_FEEDBACK = "/green/text/feedback";

    /**
     * 图片不良场景同步检测
     */
    public static final String IMAGE_SCAN = "/green/image/scan";

    /**
     * 图片不良场景异步检测
     */
    public static final String IMAGE_ASYNCSCAN = "/green/image/asyncscan";

    /**
     * 图片不良场景异步检测结果查询接口
     */
    public static final String IMAGE_RESULTS = "/green/image/results";

    /**
     * 图片不良场景反馈接口
     */
    public static final String IMAGE_FEEDBACK = "/green/image/feedback";
}
