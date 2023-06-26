package com.ruoyi.common.utils;

/**
 * 手机号工具类
 */
public class PhoneUtils {

    public static String hidePhone(String phone){
        if (StringUtils.isEmpty(phone) || phone.length() < 11){
            throw new RuntimeException("手机号格式错误");
        }
        return phone.substring(0, 3) + "****" + phone.substring(7, 11);
    }
}
