/*
 * 四川生学教育科技有限公司
 * Copyright (c) 2015-2025 Founder Ltd. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Founder. You shall not disclose such Confidential Information
 * and shall use it only in accordance with the terms of the agreements
 * you entered into with Founder.
 *
 */
package com.larrykoo.aliyun.green.request;

import lombok.*;

/**
 * @author LarryKoo (larrykoo@126.com)
 * @description 图片不良场景识别内容对象
 * @date 2018/3/29 17:40
 * @slogon 站在巨人的肩膀上
 * @since 1.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ImageTask extends AbstractTask {
    private static final long serialVersionUID = 7915634695443206564L;

    /**
     * build 一个默认的Task对象
     *
     * @param clientInfo
     * @param dataId
     * @param time
     * @return
     */
    public ImageTask buildBase(ClientInfo clientInfo, String dataId, Long time) {
        this.setClientInfo(clientInfo);
        this.setDataId(dataId);
        this.setTime(time);
        return this;
    }

    /**
     * 必须
     * 待检测图像URL
     */
    private String url;
}
