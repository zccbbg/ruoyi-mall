package com.cyl.h5.pojo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("订单支付请求体")
public class OrderPayRequest {
    @ApiModelProperty(value = "支付id", required = true)
    private Long payId;

    @ApiModelProperty(value = "支付方式： 1-支付宝 2-微信（默认）", required = true)
    private Integer type;

    @ApiModelProperty(value = "用户id", hidden = true)
    private Long memberId;
}