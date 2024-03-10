package com.cyl.h5.domain.form;

import lombok.Data;

@Data
public class H5AccountLoginRequest extends H5LoginRequest{
    /** 密码 */
    private String password;
}
