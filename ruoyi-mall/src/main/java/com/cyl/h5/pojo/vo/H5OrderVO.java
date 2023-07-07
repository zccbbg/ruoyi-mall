package com.cyl.h5.pojo.vo;

import com.cyl.manager.oms.domain.OrderItem;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class H5OrderVO {

    @ApiModelProperty("订单id")
    private Long orderId;

    @ApiModelProperty("订单编号")
    private String orderSn;

    @ApiModelProperty("会员id")
    private Long memberId;

    @ApiModelProperty("订单总金额")
    private BigDecimal totalAmount;

    @ApiModelProperty("应付金额")
    private BigDecimal payAmount;

    @ApiModelProperty("订单状态 0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭")
    private Integer status;

    @ApiModelProperty("售后状态")
    private Integer aftersaleStatus;

    @ApiModelProperty("订单Item")
    private List<OrderItem> orderItemList;

    @ApiModelProperty("订单备注")
    private String note;

    @ApiModelProperty("物流单号")
    private String deliverySn;

    @ApiModelProperty("下单时间")
    private LocalDateTime createTime;

}
