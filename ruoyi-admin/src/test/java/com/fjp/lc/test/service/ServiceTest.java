package com.fjp.lc.test.service;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;
import com.alibaba.fastjson.JSON;
import com.cyl.h5.domain.dto.PayNotifyMessageDTO;
import com.cyl.h5.service.H5OrderService;
import com.cyl.job.OrderJob;
import com.cyl.manager.act.service.IntegralHistoryService;
import com.cyl.manager.oms.service.AftersaleService;
import com.cyl.manager.ums.service.MemberCartService;
import com.ruoyi.RuoYiApplication;
import com.ruoyi.common.config.properties.SmsProperties;
import com.ruoyi.common.core.sms.AliyunSmsTemplate;
import com.ruoyi.common.core.sms.SmsTemplate;
import com.ruoyi.common.utils.SecurityUtils;
import com.wechat.pay.java.service.partnerpayments.jsapi.model.Transaction;
import com.wechat.pay.java.service.refund.model.RefundNotification;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = RuoYiApplication.class)
@ActiveProfiles("dev")
@Slf4j
public class ServiceTest {

    @Autowired
    private AftersaleService aftersaleService;

    @Autowired
    private MemberCartService memberCartService;

    @Autowired
    private SmsProperties smsProperties;

    @Value("${aes.key}")
    private String key;

    @Autowired
    private IntegralHistoryService integralHistoryService;
    @Autowired
    private OrderJob orderJob;

    @Test
    public void testOrderJob(){
        orderJob.batchCompleteOrder();
    }

    @Test
    public void test12(){
        integralHistoryService.handleIntegral(5405053175810048L,new BigDecimal("2.89"),29L);
    }


    @Test
    public void t(){
        String a = "{\"amount\":{\"currency\":\"CNY\",\"discountRefund\":0,\"from\":[],\"payerRefund\":2,\"payerTotal\":2,\"refund\":2,\"refundFee\":0,\"settlementRefund\":2,\"settlementTotal\":2,\"total\":2},\"channel\":\"ORIGINAL\",\"createTime\":\"2024-04-26T18:17:56+08:00\",\"fundsAccount\":\"UNAVAILABLE\",\"outRefundNo\":\"5773276988819456\",\"outTradeNo\":\"5773274485622785\",\"promotionDetail\":[],\"refundId\":\"50303909472024042683046217485\",\"refundStatus\":\"SUCCESS\",\"transactionId\":\"4200002186202404266125196354\",\"userReceivedAccount\":\"支付用户零钱通\"}";
        RefundNotification params = JSON.parseObject(a,RefundNotification.class);
        aftersaleService.refundOrderExc(params);
    }
    @Test
    public void test1() {
        memberCartService.mineCartNum();
    }

    @Test
    public void encryptPassword() {
        String newPwd = "admin123";
        System.out.println("新密码："+ SecurityUtils.encryptPassword(newPwd));
    }

    @Test
    public void test2(){
        System.out.println(smsProperties);
        if (!smsProperties.getEnabled()) {
            throw new RuntimeException("没有开启短信服务");
        }
        Map<String, String> map = new HashMap<>(1);
        map.put("code", "1234");
        SmsTemplate smsTemplate = new AliyunSmsTemplate(smsProperties);
        Object send = smsTemplate.send("15706259078", "SMS_146125046", map);
        log.info("短信发送结果：" + send);
    }

    @Test
    public void test3(){
        String content = "test中文";
        AES aes = SecureUtil.aes(key.getBytes());
        byte[] encrypt = aes.encrypt(content);
        byte[] decrypt = aes.decrypt(encrypt);
        String encryptHex = aes.encryptHex(content);
        System.out.println("加密后16进制：" + encryptHex);
        String decryptStr = aes.decryptStr(encryptHex, CharsetUtil.CHARSET_UTF_8);
        System.out.println("解密：" + decryptStr);
    }

    @Test
    public void test4(){
        //参数1为终端ID
//参数2为数据中心ID
        Snowflake snowflake = IdUtil.createSnowflake(1, 1);
        long id = snowflake.nextId();
        System.out.println("id:" + id);
    }

    @Autowired
    private H5OrderService h5OrderService;

    @Test
    public void test6(){
        PayNotifyMessageDTO messageDTO = new PayNotifyMessageDTO();
        messageDTO.setPayTime(new Date());
        messageDTO.setOutTradeNo(5365581195495425L);
        messageDTO.setMemberId(22L);
        messageDTO.setTradeStatus(Transaction.TradeStateEnum.SUCCESS);
        messageDTO.setTradeNo("");
        ResponseEntity<String> stringResponseEntity = h5OrderService.payCallBack(messageDTO);
        System.out.println(stringResponseEntity.getBody());
    }
}
