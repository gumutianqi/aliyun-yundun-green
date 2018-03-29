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
package com.larrykoo.aliyun.green.http;

import org.apache.commons.lang3.StringUtils;

/**
 * @author LarryKoo (larrykoo@126.com)
 * @description 构建发起请求的 Domain 信息
 * @date 2018/3/28 15:08
 * @slogon 站在巨人的肩膀上
 * @since 1.0.0
 */
public class AccessUtil {
    /**
     * 根据区域ID计算出请求的Domain
     *
     * @param regionId 阿里云区域ID，可选：cn-hangzhou/cn-shanghai/local
     * @return
     */
    public static String getDomain(String regionId) {
        String domain = "green.cn-shanghai.aliyuncs.com";
        if (StringUtils.isNotBlank(regionId)) {
            switch (regionId) {
                case "cn-shanghai":
                    domain = "green.cn-shanghai.aliyuncs.com";
                    break;
                case "cn-hangzhou":
                    domain = "green.cn-hangzhou.aliyuncs.com";
                    break;
                case "local":
                    domain = "api.green.alibaba.com";
                    break;
                default:
                    break;
            }
        }
        return domain;
    }
}
