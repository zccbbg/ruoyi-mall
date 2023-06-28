package com.cyl.sms.controller;

import com.cyl.sms.service.SmsService;
import com.ruoyi.common.core.domain.model.SmsResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/no-auth/sms")
public class SmsController {

    @Autowired
    private SmsService smsService;

    /**
     * 阿里云短信服务
     */
    @GetMapping("/sendAliyun/{phones}")
    public ResponseEntity<SmsResult> sendAliyun(@PathVariable String phones){
        return smsService.sendAliyun(phones);
    }

}
