package com.cyl.h5.controller;

import com.cyl.h5.pojo.dto.LoginDTO;
import com.cyl.h5.pojo.request.RegisterRequest;
import com.cyl.h5.pojo.response.RegisterResponse;
import com.cyl.h5.pojo.vo.LoginResVO;
import com.cyl.h5.service.H5MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: czc
 * @Description: TODO
 * @DateTime: 2023/6/16 14:52
 **/
@RestController
@RequestMapping()
public class H5MemberController {

    @Autowired
    private H5MemberService service;

    @PostMapping("/no-auth/h5/member/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(service.register(request));
    }
}
