package com.cyl.h5.domain.dto;

import com.wechat.pay.java.service.partnerpayments.jsapi.model.Transaction;
import lombok.Data;

import java.util.Date;

@Data
public class PayNotifyMessageDTO {

    /**主订单号**/
    private Long outTradeNo;

    /**第三方订单号**/
    private String tradeNo;

    /**
     * 交易状态，枚举值：
     * SUCCESS：支付成功
     * REFUND：转入退款
     * NOTPAY：未支付
     * CLOSED：已关闭
     * REVOKED：已撤销（仅付款码支付会返回）
     * USERPAYING：用户支付中（仅付款码支付会返回）
     * PAYERROR：支付失败（仅付款码支付会返回）
     */
    private Transaction.TradeStateEnum tradeStatus;

    /**支付时间**/
    private Date payTime;

    /**用户id**/
    private Long memberId;
}
