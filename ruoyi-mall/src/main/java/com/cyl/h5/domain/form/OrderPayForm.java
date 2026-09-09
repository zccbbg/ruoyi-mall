package com.cyl.h5.domain.form;
import lombok.Data;

@Data
/** 订单支付请求体 */
public class OrderPayForm {
    /** 支付id */
    private Long payId;

    /** 支付方式： 1-支付宝 2-微信（默认） */
    private Integer type;

    /** 用户id */
    private Long memberId;

    /** 微信支付方式 1：公众号  2：小程序 */
    private Integer wechatType = 1;
}
