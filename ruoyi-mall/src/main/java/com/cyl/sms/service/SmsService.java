package com.cyl.sms.service;

import com.ruoyi.common.config.properties.SmsProperties;
import com.ruoyi.common.core.domain.model.SmsResult;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.core.sms.AliyunSmsTemplate;
import com.ruoyi.common.core.sms.SmsTemplate;
import com.ruoyi.common.utils.SmsUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class SmsService {

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private SmsProperties smsProperties;

    private final String templateId = "SMS_146125046";

    public ResponseEntity<SmsResult> sendAliyun(String phones){
        String code = SmsUtils.createRandom(true, 4);
        // 验证码存redis，当前只用于注册和登录，手机号只有一个
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        SmsResult send;
        byte[] decodedBytes = Base64.getDecoder().decode(phones);
        phones = new String(decodedBytes);
        if (!smsProperties.getEnabled()){
            code="1234";
            send=SmsResult.builder()
                    .isSuccess(true)
                    .message("不需要发送，验证码是："+code)
                    .build();
        }else{
            Map<String, String> map = new HashMap<>();
            map.put("code", code);
            SmsTemplate smsTemplate = new AliyunSmsTemplate(smsProperties);
            send = smsTemplate.send(phones, templateId, map);
        }
        send.setUuid(uuid);
        try{
            log.info("发送短信成功，验证码是：" + uuid+"_"+phones+code);
            redisCache.setCacheObject(uuid + "_" + phones, code, 5, TimeUnit.MINUTES);
        }catch (Exception e){
            return ResponseEntity.ok(
                    SmsResult.builder()
                            .isSuccess(false)
                            .message("服务繁忙，请稍后再试")
                            .build()
            );
        }
        return ResponseEntity.ok(send);
    }
}
