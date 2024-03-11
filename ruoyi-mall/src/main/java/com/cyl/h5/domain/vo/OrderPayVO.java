package com.cyl.h5.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author 86199
 */
@Data
@ApiModel("支付响应")
public class OrderPayVO {
    @ApiModelProperty(value = "支付方式：1-支付宝，2-微信（默认）", dataType = "Integer")
    private Integer payType;

    @ApiModelProperty("appId")
    private String appId;

    @ApiModelProperty("timeStamp")
    private String timeStamp;

    @ApiModelProperty("signType")
    private String signType;

    /**
     * TODO 变量名命名不规范
     */
    @ApiModelProperty("package")
    private String package_;

    @ApiModelProperty("nonceStr")
    private String nonceStr;

    @ApiModelProperty("签名")
    private String paySign;
}
