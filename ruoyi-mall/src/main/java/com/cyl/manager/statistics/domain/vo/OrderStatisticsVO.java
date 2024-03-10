package com.cyl.manager.statistics.domain.vo;

import lombok.Data;

@Data
public class OrderStatisticsVO {

    private String date;
    //订单笔数
    private Double orderCount;
    //订单金额
    private Double orderAmount;
    private Double numPaidOrders;
    private Double numPendingOrders;
    private Double numRefundOrders;
}
