package com.cyl.h5.domain.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("绑定openId请求对象")
@Data
public class BindOpenIDForm {
    @ApiModelProperty("wx提供的code")
    private String code;
}
