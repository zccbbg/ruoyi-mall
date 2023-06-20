package com.fjp.lc.test.service;

import com.cyl.ums.service.MemberCartService;
import com.ruoyi.RuoYiApplication;
import com.ruoyi.common.config.properties.SmsProperties;
import com.ruoyi.common.core.sms.AliyunSmsTemplate;
import com.ruoyi.common.core.sms.SmsTemplate;
import com.ruoyi.common.utils.spring.SpringUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = RuoYiApplication.class)
@ActiveProfiles("dev")
@Slf4j
public class ServiceTest {
    @Autowired
    private MemberCartService memberCartService;

    @Autowired
    private SmsProperties smsProperties;

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
}
