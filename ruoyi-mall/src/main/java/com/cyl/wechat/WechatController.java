package com.cyl.wechat;

import com.cyl.wechat.response.JssdkConfigResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/no-auth/wechat")
public class WechatController {

    @GetMapping("/jssdk")
    public ResponseEntity<JssdkConfigResponse> getJssdkConfig(){
        String appId = WechatPayData.appId;
        String nonceStr = WechatPayUtil.generateNonceStr();
        long timeStamp = WechatPayUtil.getCurrentTimestamp();
        String signature = Stream.of(appId, String.valueOf(timeStamp), nonceStr)
                .collect(Collectors.joining("\n", "", "\n"));
        JssdkConfigResponse response = new JssdkConfigResponse();
        response.setAppId(appId);
        List<String> jsApiList = new ArrayList<>();
        jsApiList.add("chooseWXPay");
        response.setJsApiList(jsApiList);
        response.setNonceStr(nonceStr);
        response.setTimeStamp(String.valueOf(timeStamp));
        response.setSignature(signature);
        return ResponseEntity.ok(response);
    }
}
