package com.cyl.h5.domain.vo;

import com.cyl.manager.oms.domain.entity.OrderItem;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class H5OrderVO {

    @ApiModelProperty("订单id")
    private Long orderId;

    @ApiModelProperty("支付id")
    private Long payId;

    @ApiModelProperty("订单编号")
    private String orderSn;

    @ApiModelProperty("会员id")
    private Long memberId;

    @ApiModelProperty("订单总金额")
    private BigDecimal totalAmount;

    @ApiModelProperty("应付金额")
    private BigDecimal payAmount;

    private BigDecimal couponAmount;

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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("支付时间")
    private LocalDateTime paymentTime;

    @ApiModelProperty("收货人姓名")
    private String receiverName;

    @ApiModelProperty("收货人手机号")
    private String receiverPhone;

    @ApiModelProperty("省份/直辖市")
    private String receiverProvince;

    @ApiModelProperty("城市")
    private String receiverCity;

    @ApiModelProperty("区")
    private String receiverDistrict;

    @ApiModelProperty("详细地址")
    private String receiverDetailAddress;

    @ApiModelProperty("支付倒计时")
    private Long timeToPay;
}
