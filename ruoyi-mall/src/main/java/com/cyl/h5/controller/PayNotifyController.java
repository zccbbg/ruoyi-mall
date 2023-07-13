package com.cyl.h5.controller;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.cyl.h5.pojo.dto.PayNotifyMessageDTO;
import com.cyl.h5.service.H5OrderService;
import com.cyl.wechat.WechatPayData;
import com.cyl.wechat.response.WeChatPayNotify;
import com.wechat.pay.java.core.Config;
import com.wechat.pay.java.core.RSAAutoCertificateConfig;
import com.wechat.pay.java.core.notification.NotificationConfig;
import com.wechat.pay.java.core.notification.NotificationParser;
import com.wechat.pay.java.core.notification.RequestParam;
import com.wechat.pay.java.service.partnerpayments.jsapi.model.Transaction;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * 订单表Controller
 * 
 * @author sjm
 * @date 2023-04-05
 */
@Api(description ="微信回调接口列表")
@RestController
@RequestMapping("/no-auth/wechat")
public class PayNotifyController {
    private static final Logger log = LoggerFactory.getLogger(PayNotifyController.class);

    @Autowired
    private H5OrderService h5OrderService;

    private final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    /**
     * 微信支付回调
     * @param request
     * @throws IOException
     */
    @PostMapping("/notify")
    public void weChatPayNotify(HttpServletRequest request) throws Exception {
        log.info("收到了微信支付回调");
        // 从请求头中获取信息
        String timestamp  = request.getHeader("Wechatpay-Timestamp");
        String nonce = request.getHeader("Wechatpay-Nonce");
        String signature = request.getHeader("Wechatpay-Signature");
        String singType = request.getHeader("Wechatpay-Signature-Type");
        String wechatPayCertificateSerialNumber = request.getHeader("Wechatpay-Serial");
        // 拿到请求体body
        StringBuilder requestBody = new StringBuilder();
        String line;
        BufferedReader reader;
        reader = request.getReader();
        while (null != (line = reader.readLine())) {
            requestBody.append(line);
        }
        // 构造 RequestParam
        RequestParam requestParam = new RequestParam.Builder()
                .serialNumber(wechatPayCertificateSerialNumber)
                .nonce(nonce)
                .signature(signature)
                .timestamp(timestamp)
                .body(requestBody.toString())
                .build();
        //初始化了 RSAAutoCertificateConfig
        Config config = new RSAAutoCertificateConfig.Builder()
                .merchantId(WechatPayData.merchantId)
                .privateKeyFromPath(WechatPayData.privateKeyPath)
                .merchantSerialNumber(WechatPayData.merchantSerialNumber)
                .apiV3Key(WechatPayData.apiV3key)
                .build();
        // 初始化解析器 NotificationParser
        NotificationParser parser = new NotificationParser((NotificationConfig) config);
        // 以支付通知回调为例，验签、解密并转换成 Transaction
        Transaction transaction = parser.parse(requestParam, Transaction.class);
        PayNotifyMessageDTO message = new PayNotifyMessageDTO();
        message.setTradeNo(transaction.getOutTradeNo());
        message.setMemberId(Long.valueOf(transaction.getAttach()));
        message.setTradeStatus(transaction.getTradeState());
        if (StrUtil.isEmpty(transaction.getSuccessTime())){
            throw new RuntimeException("微信支付回调失败");
        }
        message.setPayTime(formatter.parse(transaction.getSuccessTime().substring(0, transaction.getSuccessTime().indexOf("+"))));
        message.setTradeNo(transaction.getTransactionId());
        h5OrderService.payCallBack(message);
    }


}
