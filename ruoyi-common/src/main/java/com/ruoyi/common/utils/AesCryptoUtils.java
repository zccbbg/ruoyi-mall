package com.ruoyi.common.utils;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;

/**
 * aes加密工具
 */
public class AesCryptoUtils {

    public static String encrypt(String key, String content){
        SecureUtil.disableBouncyCastle();
        if (StringUtils.isBlank(key) || StringUtils.isBlank(content)){
            throw new RuntimeException("错误");
        }
        AES aes = SecureUtil.aes(key.getBytes());
        byte[] encrypt = aes.encrypt(content);
        return aes.encryptHex(content);
    }

    public static String decrypt(String key, String content){
        SecureUtil.disableBouncyCastle();
        if (StringUtils.isBlank(key) || StringUtils.isBlank(content)){
            throw new RuntimeException("错误");
        }
        AES aes = SecureUtil.aes(key.getBytes());
        return aes.decryptStr(content, CharsetUtil.CHARSET_UTF_8);
    }
}
