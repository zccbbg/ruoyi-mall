package com.ruoyi.common.config;

import com.ruoyi.common.config.properties.SmsProperties;
import com.ruoyi.common.core.sms.AliyunSmsTemplate;
import com.ruoyi.common.core.sms.SmsTemplate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * 短信配置类
 *
 * @author Lion Li
 * @version 4.2.0
 */
@EnableConfigurationProperties(SmsProperties.class)
public class SmsConfig {

    @Configuration
    @ConditionalOnProperty(value = "sms.enabled", havingValue = "true")
    @ConditionalOnClass(com.aliyun.dysmsapi20170525.Client.class)
    static class AliyunSmsConfig {

        @Bean
        public SmsTemplate aliyunSmsTemplate(SmsProperties smsProperties) {
            return new AliyunSmsTemplate(smsProperties);
        }

    }

}
