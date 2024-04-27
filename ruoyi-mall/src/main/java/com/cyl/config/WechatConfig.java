package com.cyl.config;

import com.cyl.wechat.WechatPayConfig;
import com.wechat.pay.java.service.payments.jsapi.JsapiService;
import com.wechat.pay.java.service.refund.RefundService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
@DependsOn("WechatPayData")
@ConditionalOnProperty(prefix = "wechat", name = "enabled", havingValue = "true")
public class WechatConfig {

    @Bean
    public JsapiService jsapiService(){
        return new JsapiService.Builder().config(WechatPayConfig.getInstance()).build();
    }

    @Bean
    public RefundService refundService(){
        return new RefundService.Builder().config(WechatPayConfig.getInstance()).build();
    }
}
