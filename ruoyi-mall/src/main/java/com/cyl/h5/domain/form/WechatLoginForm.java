package com.cyl.h5.domain.form;

import lombok.Data;

@Data
public class WechatLoginForm {
    private String code;
    private String state;
}
