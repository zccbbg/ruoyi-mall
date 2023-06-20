package com.cyl.h5.pojo.response;

import com.cyl.ums.domain.Member;
import lombok.Data;

@Data
public class RegisterResponse {
    /** token */
    private String token;
    /** 会员信息 */
    private Member member;
}
