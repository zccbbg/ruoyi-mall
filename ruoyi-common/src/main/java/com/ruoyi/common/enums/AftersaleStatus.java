package com.ruoyi.common.enums;

/**
 * 售后状态
 * 
 * @author ruoyi
 */
public enum AftersaleStatus
{
    APPLY(0, "待处理"),
    WAIT(1, "退货中"),
    SUCCESS(2, "已完成"),
    REJECT(3, "已拒绝"),
    CANCEL(4,"用户取消");

    private final Integer type;
    private final String msg;

    private AftersaleStatus(Integer type, String msg) {
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
