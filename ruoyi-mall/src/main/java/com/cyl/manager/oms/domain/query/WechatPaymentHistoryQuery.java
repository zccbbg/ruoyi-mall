package com.cyl.manager.oms.domain.query;

import java.math.BigDecimal;
import lombok.Data;

/**
 * 微信订单表 查询 对象
 *
 * @author zcc
 */
@Data
public class WechatPaymentHistoryQuery {
    /** payment_id 精确匹配 */
    private String paymentId;

    /** 用户 ID 精确匹配 */
    private Long memberId;

    /** OPENID 精确匹配 */
    private String openid;

    /** 真实姓名，提现需要 精确匹配 */
    private String realNameLike;

    /** 标题|商品名称 精确匹配 */
    private String title;

    /** 订单号 支付时是payId 其他为orderId 精确匹配 */
    private Long orderId;

    /** 金额，单位分 精确匹配 */
    private BigDecimal money;

    /** 交易类型（1为支付  2为提现  3为退款） 精确匹配 */
    private Integer opType;

    /** 状态（0：未完成交易   1：完成关键交易） 精确匹配 */
    private Integer paymentStatus;

    /** 附加数据 精确匹配 */
    private String attach;

    /** 响应内容 精确匹配 */
    private String responseBody;

}
