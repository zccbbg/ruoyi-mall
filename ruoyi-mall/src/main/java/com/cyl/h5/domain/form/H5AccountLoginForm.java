package com.cyl.h5.domain.form;

import lombok.Data;

@Data
public class H5AccountLoginForm extends H5LoginForm {
    /** 密码 */
    private String password;
}
