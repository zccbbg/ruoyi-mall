package com.cyl.external;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.Arrays;

public class WechatUtil {
    public static boolean validParam(String signature, String... arr) {
        Arrays.sort(arr);
        StringBuilder sb = new StringBuilder();
        String[] var2 = arr;
        int var3 = arr.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            String a = var2[var4];
            sb.append(a);
        }

        return signature.equals(DigestUtils.sha1Hex(sb.toString()));
    }
}
