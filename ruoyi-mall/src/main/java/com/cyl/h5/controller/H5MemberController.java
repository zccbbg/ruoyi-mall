package com.cyl.h5.controller;

import com.alibaba.fastjson.JSON;
import com.cyl.h5.domain.form.RegisterForm;
import com.cyl.h5.domain.vo.H5LoginVO;
import com.cyl.h5.domain.vo.RegisterVO;
import com.cyl.h5.domain.vo.ValidatePhoneVO;
import com.cyl.h5.domain.vo.WechatLoginVO;
import com.cyl.h5.service.H5MemberService;
import com.cyl.manager.ums.domain.vo.MemberVO;
import com.ruoyi.common.core.domain.model.LoginMember;
import com.ruoyi.framework.web.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Base64Utils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/h5")
public class H5MemberController {

    @Autowired
    private H5MemberService service;
    @Autowired
    private TokenService tokenService;


    @PostMapping("/register")
    public ResponseEntity<RegisterVO> register(@RequestBody RegisterForm request){
        return ResponseEntity.ok(service.register(request));
    }

    @GetMapping("/validate/{phone}")
    public ResponseEntity<ValidatePhoneVO> validate(@PathVariable String phone){
        return ResponseEntity.ok(service.validate(phone));
    }

    @PostMapping("/account/login")
    public ResponseEntity<H5LoginVO> accountLogin(@RequestBody String data){
        return ResponseEntity.ok(service.accountLogin(data));
    }

    @PostMapping("/wechat/login")
    public ResponseEntity<H5LoginVO> wechatLogin(String data) throws Exception {
        if (StringUtils.isEmpty(data)) {
            return ResponseEntity.ok(null);
        }
        WechatLoginVO params = JSON.parseObject(new String(Base64Utils.decodeFromString(data)), WechatLoginVO.class);
        return ResponseEntity.ok(service.wechatLogin(params));
    }

    @PostMapping("/sms/login")
    public ResponseEntity<H5LoginVO> smsLogin(@RequestBody String data){
        return ResponseEntity.ok(service.smsLogin(data));
    }

    @GetMapping("/member/info")
    public ResponseEntity<MemberVO> getMemberInfo(){
        return ResponseEntity.ok(service.getMemberInfo());
    }

    @PostMapping("/member/setWechatInfo")
    public void setWechatInfo(@RequestBody String data){
        service.setWechatInfo(data);
    }

    @GetMapping("/record/login")
    public void add(HttpServletRequest request) {
        LoginMember loginMember = tokenService.getLoginMember(request);
        if (loginMember != null){
            service.insert(loginMember.getMemberId());
        }
    }
}
