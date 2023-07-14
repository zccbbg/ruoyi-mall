package com.ruoyi.common.enums;

/**
 * 用户状态
 * 
 * @author ruoyi
 */
public enum OrderRefundStatus
{
    NO_REFUND(1, "无售后"),
    APPLY(2, "申请中"),
    WAIT(3, "退款中"),
    SUCCESS(4, "退款成功"),
    FAIL(5,"退款失败");

    private final Integer type;
    private final String msg;

    private OrderRefundStatus(Integer type, String msg) {
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
