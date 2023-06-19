package com.cyl.h5.service;

import com.cyl.h5.pojo.request.RegisterRequest;
import com.cyl.h5.pojo.response.RegisterResponse;
import com.cyl.ums.domain.Member;
import com.cyl.ums.mapper.MemberMapper;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @Author: czc
 * @Description: TODO
 * @DateTime: 2023/6/16 15:01
 **/
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
        response.setResult(false);
        if (StringUtils.isEmpty(request.getMobile())){
            response.setMessage("手机号不能为空");
            return response;
        }
        if (StringUtils.isEmpty(request.getPassword())){
            response.setMessage("密码不能为空");
            return response;
        }
        int len = request.getPassword().length();
        if (len < 8 || len > 20){
            response.setMessage("密码长度为8-20位");
            return response;
        }
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
        response.setResult(true);
        response.setMessage("注册成功");
        return response;
    }
}
