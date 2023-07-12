package com.ruoyi.common.enums;

/**
 * 用户状态
 * 
 * @author ruoyi
 */
public enum OrderStatus
{
    ALL_DATA(-1,"全部订单"),
    UN_PAY(0, "待付款"),
    NOT_DELIVERED(1, "待发货"),
    DELIVERED(2, "待收货"),
    COMPLETE(3, "已完成"),
    CLOSED(4, "已关闭"),
    INVALID(5, "无效订单"),
    REFUUND(-2, "售后订单");

    private final Integer type;
    private final String msg;

    private OrderStatus(Integer type, String msg) {
        this.type = type;
        this.msg = msg;
    }

    public Integer getType() {
        return this.type;
    }

    public String getMsg() {
        return this.msg;
    }
}
