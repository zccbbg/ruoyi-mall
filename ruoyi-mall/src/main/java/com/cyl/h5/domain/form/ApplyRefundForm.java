package com.cyl.h5.domain.form;
import lombok.Data;

import java.math.BigDecimal;

@Data
/** 申请售后对象 */
public class ApplyRefundForm {

    /** 订单id */
    private Long orderId;

    /** 申请售后类型 1：仅退款 2：退货退款 */
    private Integer applyRefundType;

    /** 退款原因 */
    private String reason;

    /** 申请退货数量 */
    private Integer quantity;

    /** 退款金额 */
    private BigDecimal refundAmount;

    /** 描述 */
    private String description;

    /** 凭证图片以逗号隔开 */
    private String proofPics;

}
