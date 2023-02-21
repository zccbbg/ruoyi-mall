package com.cyl.h5.controller;

import com.cyl.external.WechatUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * h5/微信
 */
@RestController
@RequestMapping("")
public class WechatController {
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
    @GetMapping("/no-auth/wechat/h5-login")
    public String h5Login(HttpServletRequest request) {
        // TODO
        return "err";
    }
}
