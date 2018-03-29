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
package com.larrykoo.aliyun.green;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.http.ProtocolType;
import com.larrykoo.aliyun.green.constant.CategoryConstants;
import com.larrykoo.aliyun.green.constant.ScenesConstants;
import com.larrykoo.aliyun.green.http.AccessUtil;
import com.larrykoo.aliyun.green.http.AlibabaHttpKit;
import com.larrykoo.aliyun.green.http.AlibabaRequest;
import com.larrykoo.aliyun.green.request.*;
import com.larrykoo.aliyun.green.response.AlibabaResponse;
import com.larrykoo.aliyun.green.response.BodyResponse;
import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;

import javax.xml.bind.DatatypeConverter;
import java.util.*;

/**
 * @author LarryKoo (larrykoo@126.com)
 * @description 云盾内容安全单元测试
 * @date 2018/3/27 11:31
 * @slogon 站在巨人的肩膀上
 * @since 1.0.0
 */
@Slf4j
public class YundunTestCase extends TestCase {

    private ClientInfo clientInfo;

    private AlibabaHttpKit httpKit;

    /**
     * 创建一个测试的 ClientInfo 对象
     *
     * @return
     */
    private ClientInfo createClientInfo() {
        // 1. 构建可选的客户端信息 ClientInfo
        ClientInfo clientInfo = ClientInfo.builder()
                .ip("127.0.0.2")
                .userId("123456")
                .userNick("Milk")
                .userType("teacher")
                .channel("AppStore")
                .hostAppName("sxt")
                .hostPackage("cn.sxw.sxt")
                .hostVersion("2.0.0")
                .build();
        return clientInfo;
    }

    /**
     * 创建一个测试用的 TextTask 对象
     *
     * @param content
     * @return
     */
    private TextTask createText(String content) {
        return TextTask.builder()
                .content(content)
                .category(CategoryConstants.COMMENT.toString())
                .action(CategoryConstants.NEW.toString())
                .build()
                .buildBase(clientInfo, UUID.randomUUID().toString(), System.currentTimeMillis());
    }

    /**
     * 创建一个测试用的 ImagesTask 对象
     *
     * @param url 图片完整URI地址
     * @return
     */
    private ImageTask createImage(String url) {
        return ImageTask.builder()
                .url(url)
                .build()
                .buildBase(clientInfo, UUID.randomUUID().toString(), System.currentTimeMillis());
    }

    /**
     * 执行内容安全扫描动作
     *
     * @param bodyRequest 需要扫描的请求Body对象
     * @return List<BodyResponse>
     * @throws Exception
     */
    private List<BodyResponse> runScan(BodyRequest bodyRequest, String greenApiPath) throws Exception {
        // 1. 转换Body对象为字符串对象
        String bodyString = JSON.toJSONString(bodyRequest);

        log.debug("请求的Body格式化如下:\n{}", JSON.toJSONString(bodyRequest, true));
        log.debug("请求的Body原始内容如下:\n{}", bodyString);

        // 2. Body 对象 MD5 之后再进行Base64编码
        String bodyBase64 = DatatypeConverter.printBase64Binary(DigestUtils.md5(bodyString));
        log.debug("请求的Body Base64如下:\n{}", bodyBase64);

        // 3. 构建 Header
        Map<String, String> headers = new HashMap<>();

        AlibabaRequest alibabaRequest = AlibabaRequest.builder()
                .appKey(AliyunAccessKey.APP_KEY)
                .appSecret(AliyunAccessKey.APP_SECRET)
                .method(MethodType.POST.name())
                .headers(headers)
                .bodys(bodyRequest.toMap())
                .protocolType(ProtocolType.HTTPS.toString())
                .host(AccessUtil.getDomain(AliyunAccessKey.REGION_ID))
                .path(greenApiPath)
                .stringBody(bodyString)
                .querys(new HashMap<>())
                .timeout(1000)
                .signHeaderPrefixList(new ArrayList<>())
                .build();

        // 4.发起请求
        AlibabaResponse alibabaResponse = httpKit.httpPost(alibabaRequest);
        if (null != alibabaResponse && 200 == alibabaResponse.getCode()) {
            // 5. 转换返回结果信息为定义好的 response 对象
            List<BodyResponse> responseList = JSON.parseObject(JSON.toJSONString(alibabaResponse.getData()), ArrayList.class);
            return responseList;
        } else {
            log.error("The request failed: \n{}", alibabaResponse);
            return null;
        }
    }

    @Override
    public void setUp() {
        // 初始化 ClientInfo
        clientInfo = createClientInfo();
        httpKit = new AlibabaHttpKit();
    }

    /**
     * 发起一个文本发垃圾检测
     *
     * @throws Exception
     */
    public void testTextScan() throws Exception {
        BodyRequest body = BodyRequest.builder()
                .scenes(Arrays.asList(ScenesConstants.SCENES_TEXT_ANTISPAM.toString()))
                .tasks(Arrays.asList(
                        createText("今天看见习大大了"),
                        createText("我们一起发起农民起义吧，明天就去抢银行"),
                        createText("干死她丫的"),
                        createText("iPhone 4 最经典：再一次，改变一切 (This changes everything. Again)\n" +
                                "\n" +
                                "iPhone 5s 的也不错：超前，空前 (Forward thinking)\n" +
                                "\n" +
                                "iPad Air 也很可爱：轻出分量 （The power of lightness)\n" +
                                "\n" +
                                "iPad mini 台版：简而不减 (There is less of it; but no less to it)\n" +
                                "\n" +
                                "iPad mini with Retina display 更萌：小有乾坤 (Small wonder)")
                ))
                .build();

        List<BodyResponse> responseList = this.runScan(body, GreenApiPath.TEXT_SCAN);
        System.out.println("文本扫描结果：\n" + JSON.toJSONString(responseList, true));
    }

    /**
     * 发起一个图片不良场景识别检测
     */
    public void testImageScan() throws Exception {
        BodyRequest body = BodyRequest.builder()
                .scenes(Arrays.asList(
                        ScenesConstants.SCENES_IMAGE_PORN.toString(),
                        ScenesConstants.SCENES_IMAGE_TERRORISM.toString(),
                        ScenesConstants.SCENES_IMAGE_LIVE.toString(),
                        ScenesConstants.SCENES_IMAGE_AD.toString()
                ))
                .tasks(Arrays.asList(
                        createImage("http://y0.ifengimg.com/f04c9b92453d105f/2015/0213/xes_d76d6dca1cffe896451e77b4397c2db8.jpg"),
                        createImage("http://images.china.cn/attachement/jpg/site1000/20090824/00114320c9e60bfc3cbc1d.jpg"),
                        createImage("http://www.fansimg.com/uploads2015/04/userid431417time20150409032940at40.jpg"),
                        createImage("http://imgsrc.baidu.com/imgad/pic/item/1b4c510fd9f9d72a8ff47339de2a2834349bbb06.jpg"),
                        createImage("http://scimg.jb51.net/allimg/140808/11-140PQ50242311.jpg")
                ))
                .build();

        List<BodyResponse> responseList = this.runScan(body, GreenApiPath.IMAGE_SCAN);
        System.out.println("图片扫描结果：\n" + JSON.toJSONString(responseList, true));
    }
}
