package com.cyl.wechat.response;

import io.swagger.annotations.ApiModelProperty;
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

    @ApiModelProperty("微信支付系统生成的订单号")
    private String transaction_id;
    @ApiModelProperty("商户号")
    private String mchid;
    @ApiModelProperty("订单金额信息")
    private WeChatAmount amount;
    @ApiModelProperty("商户订单号")
    private String out_trade_no;
    @ApiModelProperty("交易类型")
    private String trade_type;
    @ApiModelProperty("交易状态")
    private String trade_state;
    @ApiModelProperty("交易状态描述")
    private String trade_state_desc;
    @ApiModelProperty("付款银行")
    private String bank_type;
    @ApiModelProperty("附加数据")
    private String attach;
    @ApiModelProperty("支付完成时间")
    private String success_time;
    @ApiModelProperty("支付者信息")
    private WeChatPayer payer;

}
