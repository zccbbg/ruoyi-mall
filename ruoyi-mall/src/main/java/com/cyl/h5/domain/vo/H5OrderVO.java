package com.cyl.h5.domain.vo;

import com.cyl.manager.oms.domain.entity.OrderItem;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class H5OrderVO {

    /** 订单id */
    private Long orderId;

    /** 支付id */
    private Long payId;

    /** 订单编号 */
    private String orderSn;

    /** 会员id */
    private Long memberId;

    /** 订单总金额 */
    private BigDecimal totalAmount;

    /** 应付金额 */
    private BigDecimal payAmount;

    private BigDecimal couponAmount;

    /** 订单状态 0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭 */
    private Integer status;

    /** 售后状态 */
    private Integer aftersaleStatus;

    /** 订单Item */
    private List<OrderItem> orderItemList;

    /** 订单备注 */
    private String note;

    /** 物流单号 */
    private String deliverySn;

    /** 下单时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    /** 支付时间 */
    private LocalDateTime paymentTime;

    /** 收货人姓名 */
    private String receiverName;

    /** 收货人手机号 */
    private String receiverPhone;

    /** 省份/直辖市 */
    private String receiverProvince;

    /** 城市 */
    private String receiverCity;

    /** 区 */
    private String receiverDistrict;

    /** 详细地址 */
    private String receiverDetailAddress;

    /** 支付倒计时 */
    private Long timeToPay;
}
