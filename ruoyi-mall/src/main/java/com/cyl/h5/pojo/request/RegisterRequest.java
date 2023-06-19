package com.cyl.h5.pojo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: czc
 * @Description: TODO
 * @DateTime: 2023/6/19 16:31
 **/
@Data
public class RegisterRequest {

    @ApiModelProperty("手机号")
    private String mobile;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("uuid")
    private String uuid;

    @ApiModelProperty("验证码")
    private String code;

}
