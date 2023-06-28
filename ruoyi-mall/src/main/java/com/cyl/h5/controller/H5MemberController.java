package com.cyl.h5.controller;

import com.cyl.h5.pojo.request.RegisterRequest;
import com.cyl.h5.pojo.response.RegisterResponse;
import com.cyl.h5.pojo.response.ValidatePhoneResponse;
import com.cyl.h5.pojo.response.H5LoginResponse;
import com.cyl.h5.service.H5MemberService;
import com.cyl.manager.ums.pojo.vo.MemberVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/h5")
public class H5MemberController {

    @Autowired
    private H5MemberService service;

    @ApiOperation("会员注册")
    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(service.register(request));
    }

    @ApiOperation("注册验证码校验手机号")
    @GetMapping("/validate/{phone}")
    public ResponseEntity<ValidatePhoneResponse> validate(@PathVariable String phone){
        return ResponseEntity.ok(service.validate(phone));
    }

    @ApiOperation("手机号密码登录")
    @PostMapping("/account/login")
    public ResponseEntity<H5LoginResponse> accountLogin(@RequestBody String data){
        return ResponseEntity.ok(service.accountLogin(data));
    }

    @ApiOperation("sms登录")
    @PostMapping("/sms/login")
    public ResponseEntity<H5LoginResponse> smsLogin(@RequestBody String data){
        return ResponseEntity.ok(service.smsLogin(data));
    }

    @ApiOperation("获取会员信息")
    @GetMapping("/member/info")
    public ResponseEntity<MemberVO> getMemberInfo(){
        return ResponseEntity.ok(service.getMemberInfo());
    }
}
