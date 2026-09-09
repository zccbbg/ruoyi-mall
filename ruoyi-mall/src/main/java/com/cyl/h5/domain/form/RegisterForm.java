package com.cyl.h5.domain.form;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RegisterForm {

    /** 手机号 */
    @NotBlank
    private String mobile;

    /** 密码 */
    @NotBlank
    private String password;

    /** uuid */
    @NotBlank
    private String uuid;

    /** 验证码 */
    @NotBlank
    private String code;

    /** 微信授权code */
    private String wechatCode;

}
