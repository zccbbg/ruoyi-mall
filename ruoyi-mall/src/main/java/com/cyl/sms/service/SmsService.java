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
        if (!smsProperties.getEnabled()){
            return ResponseEntity.ok(
                    SmsResult.builder()
                            .isSuccess(false)
                            .message("当前系统没有开启短信服务")
                            .build()
            );

        }
        byte[] decodedBytes = Base64.getDecoder().decode(phones);
        phones = new String(decodedBytes);
        String code = SmsUtils.createRandom(true, 4);
        Map<String, String> map = new HashMap<>();
        map.put("code", code);
        SmsTemplate smsTemplate = new AliyunSmsTemplate(smsProperties);
        SmsResult send = smsTemplate.send(phones, templateId, map);
        // 验证码存redis，当前只用于注册和登录，手机号只有一个
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        send.setUuid(uuid);
        try{
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
