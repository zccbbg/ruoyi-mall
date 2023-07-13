package com.fjp.lc.test.service;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;
import com.cyl.h5.pojo.dto.PayNotifyMessageDTO;
import com.cyl.h5.service.H5OrderService;
import com.cyl.manager.ums.service.MemberCartService;
import com.cyl.wechat.WechatAuthService;
import com.cyl.wechat.WechatPayService;
import com.ruoyi.RuoYiApplication;
import com.ruoyi.common.config.properties.SmsProperties;
import com.ruoyi.common.core.sms.AliyunSmsTemplate;
import com.ruoyi.common.core.sms.SmsTemplate;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = RuoYiApplication.class)
@ActiveProfiles("dev")
@Slf4j
public class ServiceTest {
    @Autowired
    private MemberCartService memberCartService;

    @Autowired
    private SmsProperties smsProperties;

    @Value("${aes.key}")
    private String key;

    @Test
    public void test1() {
        memberCartService.mineCartNum();
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
    private WechatPayService wechatPayService;
    @Autowired
    private WechatAuthService wechatAuthService;
    @Test
    public void test5(){
//        String code = "0611P2Ga1D8QCF0CVuJa1qNUJa11P2GL";
//        WechatUserAuth userToken = wechatAuthService.getUserToken(code);
        String openId="oUA8I6lDdwSfz-EwR4284dU3KOYw";
        String res = wechatPayService.jsapiPay(UUID.randomUUID().toString().substring(0,30), "测试支付", 1, openId);
        System.out.println(res);

    }

    @Autowired
    private H5OrderService h5OrderService;

    @Test
    public void test6(){
        PayNotifyMessageDTO messageDTO = new PayNotifyMessageDTO();
        messageDTO.setPayTime(new Date());
        messageDTO.setOutTradeNo(5364038883215361L);
        messageDTO.setMemberId(22L);
//        messageDTO.setTradeStatus(TradeStatusEnum.TRADE_SUCCESS.getStatus());
        messageDTO.setTradeNo("");
        ResponseEntity<String> stringResponseEntity = h5OrderService.payCallBack(messageDTO);
        System.out.println(stringResponseEntity.getBody());
    }
}
