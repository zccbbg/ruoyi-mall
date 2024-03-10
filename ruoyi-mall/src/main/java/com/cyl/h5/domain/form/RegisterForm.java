package com.cyl.h5.domain.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RegisterForm {

    @ApiModelProperty("手机号")
    @NotBlank
    private String mobile;

    @ApiModelProperty("密码")
    @NotBlank
    private String password;

    @ApiModelProperty("uuid")
    @NotBlank
    private String uuid;

    @ApiModelProperty("验证码")
    @NotBlank
    private String code;

    @ApiModelProperty("微信授权code")
    private String wechatCode;

}
