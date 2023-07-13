package com.cyl.wechat;

import com.wechat.pay.java.core.Config;
import com.wechat.pay.java.core.RSAAutoCertificateConfig;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

public class WechatPayConfig {

    private static Config wechatPayConfig;

    private WechatPayConfig(){}

    public static Config getInstance() {


        if (wechatPayConfig == null) {
            wechatPayConfig = new RSAAutoCertificateConfig.Builder()
                    .merchantId(WechatPayData.merchantId)
                    .privateKeyFromPath(WechatPayData.privateKeyPath)
                    .merchantSerialNumber(WechatPayData.merchantSerialNumber)
                    .apiV3Key(WechatPayData.apiV3key)
                    .build();
        }
        return wechatPayConfig;
    }
}
