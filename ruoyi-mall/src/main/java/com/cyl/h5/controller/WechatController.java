package com.cyl.h5.controller;

import com.cyl.external.WechatUtil;
import com.cyl.h5.pojo.vo.form.WechatLoginForm;
import com.cyl.manager.ums.service.MemberWechatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * h5/微信
 */
@RestController
@RequestMapping("")
public class WechatController {
    @Autowired
    private MemberWechatService memberWechatService;
    /**
     * 微信公众号服务器认证
     * @return
     */
    @GetMapping("/no-auth/wechat-server-auth")
    public String getHomeConfig(HttpServletRequest request) {
        String signature = request.getParameter("signature");
        String nonce = request.getParameter("nonce");
        String timestamp = request.getParameter("timestamp");
        String echostr = request.getParameter("echostr");
        if (WechatUtil.validParam(signature, "BLU9Jo7vo", timestamp, nonce)) {
            return echostr;
        }
        return "err";
    }
    /**
     * 微信公众号服务器认证
     * @return
     */
    @PostMapping("/no-auth/wechat/h5-login")
    public ResponseEntity<String> h5Login(@RequestBody WechatLoginForm form) {
        String token = memberWechatService.login(form);
        return ResponseEntity.ok("{\"data\": \"" + token + "\"}");
    }
}
