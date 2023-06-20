package com.cyl.h5.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cyl.h5.pojo.request.RegisterRequest;
import com.cyl.h5.pojo.response.RegisterResponse;
import com.cyl.h5.pojo.response.ValidatePhoneResponse;
import com.cyl.ums.domain.Member;
import com.cyl.ums.mapper.MemberMapper;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Base64;

@Service
@Slf4j
public class H5MemberService {

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private RedisCache redisCache;

    /**
     * 注册
     * @param request 注册请求体
     * @return 结果
     */
    public RegisterResponse register(RegisterRequest request){
        log.info("request:{}", request);
        RegisterResponse response = new RegisterResponse();
        response.setIfSuccess(false);
        //校验 验证码
        String key = request.getUuid() + "_" + request.getMobile();
        String code = redisCache.getCacheObject(key);
        log.info("code:{}", code);
        if (null == code){
            response.setMessage("验证码已过期");
            return response;
        }else if (!code.equals(request.getCode())){
            response.setMessage("验证码错误");
            return response;
        }
        //删除缓存
        redisCache.deleteObject(key);
        //创建会员
        Member member = new Member();
        member.setPhone(request.getMobile());
        member.setPassword(SecurityUtils.encryptPassword(request.getPassword()));
        member.setNickname("用户" + request.getMobile());
        member.setCreateTime(LocalDateTime.now());
        memberMapper.insert(member);
        response.setIfSuccess(true);
        response.setMessage("注册成功");
        return response;
    }

    public ValidatePhoneResponse validate(String phone) {
        ValidatePhoneResponse response = new ValidatePhoneResponse();
        response.setIfSuccess(false);
        byte[] decodedBytes = Base64.getDecoder().decode(phone);
        phone = new String(decodedBytes);
        QueryWrapper<Member> qw = new QueryWrapper<>();
        qw.eq("phone", phone);
        Member member = memberMapper.selectOne(qw);
        if (member != null){
            response.setMessage("该手机号已被占用");
            return response;
        }
        response.setIfSuccess(true);
        response.setMessage("该手机号可用");
        return response;
    }
}
