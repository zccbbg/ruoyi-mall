package com.cyl.manager.statistics.domain.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderAndAftersaleStatisticsVO {
    /** 待处理售后 */
    private Integer pendingAftersaleCount;
    /** 处理中售后 */
    private Integer processingAftersaleCount;
    /** 待发货 */
    private Integer waitDeliveredCount;
    /** 已发货 */
    private Integer todayHasDeliveredCount;
    /** 订单数 */
    private Integer todayOrderCount;
    /** 成交额 */
    private BigDecimal todayTransactionAmount;
}
