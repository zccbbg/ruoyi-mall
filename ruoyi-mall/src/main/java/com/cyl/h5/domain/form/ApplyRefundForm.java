package com.cyl.h5.domain.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@ApiModel("申请售后对象")
public class ApplyRefundForm {

    @ApiModelProperty(value = "订单id", required = true)
    private Long orderId;

    @ApiModelProperty(value = "申请售后类型 1：仅退款 2：退货退款", required = true)
    private Integer applyRefundType;

    @ApiModelProperty(value = "退款原因", required = true)
    private String reason;

    @ApiModelProperty(value = "申请退货数量", required = true)
    private Integer quantity;

    @ApiModelProperty(value = "退款金额")
    private BigDecimal refundAmount;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty("凭证图片以逗号隔开")
    private String proofPics;

}
