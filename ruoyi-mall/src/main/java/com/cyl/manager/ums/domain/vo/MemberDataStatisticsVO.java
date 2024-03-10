package com.cyl.manager.ums.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@ApiModel("会员下单数据统计对象")
public class MemberDataStatisticsVO {
    @ApiModelProperty("购物车数")
    private Integer cartCount;
    @ApiModelProperty("订单数")
    private Integer orderCount;
    @ApiModelProperty("下单金额")
    private BigDecimal orderAmount;
    @ApiModelProperty("售后数")
    private Integer aftersaleCount;
}
