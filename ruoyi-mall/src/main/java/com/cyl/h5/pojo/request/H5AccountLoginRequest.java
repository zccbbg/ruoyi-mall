package com.cyl.h5.pojo.request;

import lombok.Data;

@Data
public class H5AccountLoginRequest extends H5LoginRequest{
    /** 密码 */
    private String password;
}
