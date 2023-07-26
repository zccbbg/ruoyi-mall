package com.cyl.wechat;

import com.cyl.h5.service.H5MemberService;
import com.cyl.wechat.response.JssdkConfigResponse;
import com.cyl.wechat.response.WechatUserAuth;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/no-auth/wechat")
public class WechatController {

    @Autowired
    private H5MemberService service;

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
        jsApiList.add("updateAppMessageShareData");
        jsApiList.add("updateTimelineShareData");
        response.setJsApiList(jsApiList);
        response.setNonceStr(nonceStr);
        response.setTimeStamp(String.valueOf(timeStamp));
        response.setSignature(signature);
        return ResponseEntity.ok(response);
    }

    @ApiOperation("获取微信用户授权信息")
    @PostMapping("/getWechatUserAuth")
    public ResponseEntity<WechatUserAuth> getWechatUserAuth(@RequestBody String data){
        return ResponseEntity.ok(service.getWechatUserAuth(data));
    }
}
