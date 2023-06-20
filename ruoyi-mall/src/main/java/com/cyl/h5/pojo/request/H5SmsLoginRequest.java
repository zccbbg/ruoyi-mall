package com.cyl.h5.pojo.request;

import lombok.Data;

@Data
public class H5SmsLoginRequest extends H5LoginRequest {
    /** 验证码 */
    private String code;
    /** uuid */
    private String uuid;
}
