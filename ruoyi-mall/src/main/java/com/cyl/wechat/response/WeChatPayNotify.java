package com.cyl.wechat.response;
import lombok.Getter;
import lombok.Setter;

/**
 * @author: Jinxin
 * @date: 2023/4/18 23:03
 * @Description:
 */
@Getter
@Setter
public class WeChatPayNotify {

    /** 微信支付系统生成的订单号 */
    private String transaction_id;
    /** 商户号 */
    private String mchid;
    /** 订单金额信息 */
    private WeChatAmount amount;
    /** 商户订单号 */
    private String out_trade_no;
    /** 交易类型 */
    private String trade_type;
    /** 交易状态 */
    private String trade_state;
    /** 交易状态描述 */
    private String trade_state_desc;
    /** 付款银行 */
    private String bank_type;
    /** 附加数据 */
    private String attach;
    /** 支付完成时间 */
    private String success_time;
    /** 支付者信息 */
    private WeChatPayer payer;

}
