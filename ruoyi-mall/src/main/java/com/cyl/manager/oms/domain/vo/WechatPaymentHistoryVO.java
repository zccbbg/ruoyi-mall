package com.cyl.manager.oms.domain.vo;

import java.math.BigDecimal;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;
/**
 * 微信订单表 数据视图对象
 * 
 * @author zcc
 */
@Data
public class WechatPaymentHistoryVO extends BaseAudit {
   /** ID */
    private Long id;
   /** payment_id */
    @Excel(name = "payment_id")
    private String paymentId;
   /** 用户 ID */
    @Excel(name = "用户 ID")
    private Long memberId;
   /** OPENID */
    @Excel(name = "OPENID")
    private String openid;
   /** 真实姓名，提现需要 */
    @Excel(name = "真实姓名，提现需要")
    private String realName;
   /** 标题|商品名称 */
    @Excel(name = "标题|商品名称")
    private String title;
   /** 订单号 支付时是payId 其他为orderId */
    @Excel(name = "订单号 支付时是payId 其他为orderId")
    private Long orderId;
   /** 金额，单位分 */
    @Excel(name = "金额，单位分")
    private BigDecimal money;
   /** 交易类型（1为支付  2为提现  3为退款） */
    @Excel(name = "交易类型", readConverterExp = "1=为支付,2=为提现,3=为退款")
    private Integer opType;
   /** 状态（0：未完成交易   1：完成关键交易） */
    @Excel(name = "状态", readConverterExp = "0=：未完成交易,1=：完成关键交易")
    private Integer paymentStatus;
   /** 备注 */
    @Excel(name = "备注")
    private String remark;
   /** 附加数据 */
    @Excel(name = "附加数据")
    private String attach;
   /** 响应内容 */
    @Excel(name = "响应内容")
    private String responseBody;
}
