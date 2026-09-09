package com.cyl.h5.domain.form;
import lombok.Data;

/** 绑定openId请求对象 */
@Data
public class BindOpenIDForm {
    /** wx提供的code */
    private String code;
}
