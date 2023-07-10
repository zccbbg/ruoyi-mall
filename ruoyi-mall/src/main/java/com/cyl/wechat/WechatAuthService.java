package com.cyl.wechat;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.cyl.wechat.response.WechatUserAuth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class WechatAuthService {

    @Autowired
    private RestTemplate restTemplate;

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
}
