package com.cyl.h5.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("统计订单数量VO")
public class CountOrderVO {

    @ApiModelProperty(value = "待付款订单数量", dataType = "Integer")
    private Integer unpaid;

    @ApiModelProperty(value = "待发货订单数量", dataType = "Integer")
    private Integer nosend;

    @ApiModelProperty(value = "待收货订单数量", dataType = "Integer")
    private Integer noget;

    @ApiModelProperty(value = "售后订单数量", dataType = "Integer")
    private Integer aftersale;
}
