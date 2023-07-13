package com.cyl.wechat.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author: Jinxin
 * @date: 2023/4/18 23:04
 * @Description:
 */
@Getter
@Setter
public class WeChatAmount {
    @ApiModelProperty("CNY：人民币，境内商户号仅支持人民币。")
    private String currency;
    @ApiModelProperty("用户支付币种")
    private String payer_currency;
    @ApiModelProperty("用户支付金额，单位为分。（指使用优惠券的情况下，这里等于总金额-优惠券金额）")
    private Long payer_total;
    @ApiModelProperty("订单总金额，单位为分")
    private Long total;
}
