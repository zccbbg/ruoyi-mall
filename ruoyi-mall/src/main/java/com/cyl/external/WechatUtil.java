package com.cyl.external;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.cyl.external.resp.AccessTokenResp;
import com.cyl.external.resp.BaseResp;
import com.cyl.external.resp.UserInfoResp;
import com.ruoyi.common.exception.base.BaseException;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@ConfigurationProperties(prefix = "wechat")
public class WechatUtil {
    private static String appId;
    private static String secret;
    public void setAppId(String appId) {
        WechatUtil.appId = appId;
    }
    public void setSecret(String secret) {
        WechatUtil.secret = secret;
    }
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
    private static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
    private static final String USER_INFO_URL = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
    public static AccessTokenResp getAccessToken(String code) {
        String url = ACCESS_TOKEN_URL.replace("APPID", appId).replace("SECRET", secret).replace("CODE", code);
        String res = HttpUtil.get(url);
        AccessTokenResp resp = JSON.parseObject(res, AccessTokenResp.class);
        validResp(resp);
        return resp;
    }

    public static UserInfoResp getUserInfo(String accessToken, String openid) {
        String url = USER_INFO_URL.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openid);
        String res = HttpUtil.get(url);
        UserInfoResp resp = JSON.parseObject(res, UserInfoResp.class);
        validResp(resp);
        return resp;
    }
    public static void validResp(BaseResp resp) {
        if (resp.getErrcode() != null) {
            throw new ExternalException(resp.getErrcode() + "", resp.getErrmsg());
        }
    }
}
