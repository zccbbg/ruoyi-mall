package com.ruoyi.common.enums;

/**
 * 支付状态
 */
public enum TradeStatusEnum {

    // 交易成功
    TRADE_SUCCESS("TRADE_SUCCESS", 10),
    SUCCESS("SUCCESS", 10),
    // 用户待支付
    WAIT_BUYER_PAY("WAIT_BUYER_PAY", 20),
    NOTPAY("NOTPAY", 20),
    // 交易关闭
    TRADE_CLOSED("TRADE_CLOSED", 30),
    CLOSED("CLOSED", 30),
    // 交易结束
    TRADE_FINISHED("TRADE_FINISHED", 40),
    // 未知状态码
    UNKNOWN("UNKNOWN", -1);

    public static int dealTradeStatus(String code) {
        for (TradeStatusEnum tradeStatus : values()) {
            if (tradeStatus.getCode().equals(code)) {
                return tradeStatus.getStatus();
            }
        }
        return UNKNOWN.getStatus();
    }

    TradeStatusEnum(String code, Integer status) {
        this.code = code;
        this.status = status;
    }

    private String code;

    private Integer status;

    public String getCode() {
        return code;
    }

    public TradeStatusEnum setCode(String code) {
        this.code = code;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public TradeStatusEnum setStatus(Integer status) {
        this.status = status;
        return this;
    }

}
