package com.cyl.wechat;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cyl.wechat.response.WechatUserAuth;
import com.ruoyi.common.core.redis.RedisService;
import com.ruoyi.common.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class WechatAuthService {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private RedisService redisService;
    @Value("${wechat.miniProgramAppId}")
    private String appId;
    @Value("${wechat.miniProgramSecret}")
    private String secret;

    public WechatUserAuth getUserToken(String code) {
        Map<String, String> params = new HashMap<>();
        params.put("APPID", WechatPayData.appId);
        params.put("SECRET", WechatPayData.secret);
        params.put("CODE", code);
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(
                "https://api.weixin.qq.com/sns/oauth2/access_token?appid={APPID}&secret={SECRET}&code={CODE}&grant_type=authorization_code",
                String.class, params);
        String body = responseEntity.getBody();
        try {
            WechatUserAuth object = JSON.parseObject(body, WechatUserAuth.class);
            if (object == null) {
                log.error("获取user wechat accesstoken失败");
                return null;
            }
            log.info("get user wechat accesstoken：{}",JSONUtil.toJsonStr(object));
            return object;
        } catch (Exception e) {
            log.info("get user wechat accesstoken error",e);
        }
        return null;
    }

    /**
     * 获取二维码
     *
     * @return 二维码字符串
     */
    public String getQRCode(String scene) {
        String base64Str = redisService.getQrCode(scene);
        if (!StringUtils.isEmpty(base64Str)) {
            return base64Str;
        }
        String accessToken = getAccessToken();
        if (StringUtils.isEmpty(accessToken)) {
            log.error("accseetoken is null");
            return null;
        }
        String postUrl = "https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=" + accessToken;
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("scene", StringUtils.isEmpty(scene) ? "1" : scene);  //可以用作传参，必填，内容随意
        jsonObject.put("page", "pages/index/verificationCodeIndex"); //扫码后跳转页面，选填
        jsonObject.put("check_path", false); //是否检查跳转页面存不存在
        jsonObject.put("env_version", "release"); //版本
        byte[] string = null;
        try {
            string = restTemplate.exchange(postUrl, HttpMethod.POST, new HttpEntity<>(jsonObject, null), byte[].class).getBody();
            log.info("wechat response code: {}",string);
        } catch (Exception e) {
            log.error("get wechat code error",e);
        }
        base64Str = Base64.getEncoder().encodeToString(string);
        redisService.setQrCode(base64Str, scene);
        log.info("wechat code:{}",base64Str);
        return base64Str;
    }


    private String getAccessToken() {
        String token = redisService.getWechatToken();

        log.info("redis token：{}",token);
        if (!StringUtils.isEmpty(token)) {
            return token;
        }
        Map<String, String> params = new HashMap<>();
        params.put("APPID",  appId);         //替换成自己的appId
        params.put("APPSECRET",secret); //替换成自己的appSecret
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(
                "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid={APPID}&secret={APPSECRET}",
                String.class, params);
        String body = responseEntity.getBody();
        JSONObject object = JSON.parseObject(body);
        String accessToken = object.getString("access_token");
        if (accessToken == null) {
            log.error("获取accessToken失败");
            return accessToken;
        }
        redisService.setWechatToken(accessToken);
        log.info("get token：{}",accessToken);
        return accessToken;
    }

}
