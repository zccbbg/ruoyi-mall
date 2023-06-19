package com.cyl.h5.pojo.response;

import com.cyl.ums.domain.Member;
import lombok.Data;

/**
 * @Author: czc
 * @Description: TODO
 * @DateTime: 2023/6/19 16:38
 **/
@Data
public class RegisterResponse {

    private boolean result;

    private String message;

    private String token;

    private Member member;
}
