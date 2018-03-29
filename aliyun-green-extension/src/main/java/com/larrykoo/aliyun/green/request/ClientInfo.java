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
import java.util.Map;

/**
 * @author LarryKoo (larrykoo@126.com)
 * @description 客户端信息，所有HTTP访问中，请求有如下公共参数（query parameters）：
 * @date 2018/3/28 22:54
 * @slogon 站在巨人的肩膀上
 * @since 1.0.0
 * <p>
 * https://help.aliyun.com/document_detail/53413.html?spm=a2c4g.11186623.6.564.lObH3h
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientInfo implements Serializable {
    private static final long serialVersionUID = -2409082878662836714L;
    /**
     * 字符串 可选
     * sdk版本, 通过SDK调用时，需提供该字段
     */
    private String sdkVersion;

    /**
     * 字符串 可选
     * 配置信息版本,通过SDK调用时，需提供该字段
     */
    private String cfgVersion;

    /**
     * 字符串 可选
     * 用户账号类型，取值范围为：[“taobao”, “others”]
     */
    private String userType;


    /**
     * 字符串 可选
     * 用户ID，唯一标识一个用户
     */
    private String userId;

    /**
     * 字符串 可选
     * 用户昵称
     */
    private String userNick;

    /**
     * 字符串 可选
     * 用户头像
     */
    private String avatar;

    /**
     * 字符串 可选
     * 硬件设备码
     */
    private String imei;

    /**
     * 字符串 可选
     * 运营商设备码
     */
    private String imsi;

    /**
     * 字符串 可选
     * 设备指纹
     */
    private String umid;

    /**
     * 字符串 可选
     * 该IP应该为公网IP；如果请求中不填写，服务端会尝试从链接或者http头中获取。
     * 如果请求是从设备端发起的，该字段通常不填写；如果是从后台发起的，
     * 该IP为用户的login IP或者设备的公网IP
     */
    private String ip;

    /**
     * 字符串 可选
     * 设备的操作系统，如：’Android 6.0’
     */
    private String os;

    /**
     * 渠道号 可选
     * 渠道号
     */
    private String channel;

    /**
     * 字符串 可选
     * 宿主应用名称
     */
    private String hostAppName;

    /**
     * 字符串 可选
     * 宿主应用包名
     */
    private String hostPackage;

    /**
     * 字符串 可选
     * 宿主应用版本
     */
    private String hostVersion;


    /**
     * 转换对象为 JSON 字符串
     *
     * @return
     */
    public String toJSON() {
        return JSON.toJSONString(this);
    }
}
