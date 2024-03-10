package com.cyl.manager.oms.domain.query;

import java.math.BigDecimal;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 微信订单表 查询 对象
 *
 * @author zcc
 */
@ApiModel(description="微信订单表 查询 对象")
@Data
public class WechatPaymentHistoryQuery {
    @ApiModelProperty("payment_id 精确匹配")
    private String paymentId;

    @ApiModelProperty("用户 ID 精确匹配")
    private Long memberId;

    @ApiModelProperty("OPENID 精确匹配")
    private String openid;

    @ApiModelProperty("真实姓名，提现需要 精确匹配")
    private String realNameLike;

    @ApiModelProperty("标题|商品名称 精确匹配")
    private String title;

    @ApiModelProperty("订单号 支付时是payId 其他为orderId 精确匹配")
    private Long orderId;

    @ApiModelProperty("金额，单位分 精确匹配")
    private BigDecimal money;

    @ApiModelProperty("交易类型（1为支付  2为提现  3为退款） 精确匹配")
    private Integer opType;

    @ApiModelProperty("状态（0：未完成交易   1：完成关键交易） 精确匹配")
    private Integer paymentStatus;

    @ApiModelProperty("附加数据 精确匹配")
    private String attach;

    @ApiModelProperty("响应内容 精确匹配")
    private String responseBody;

}
