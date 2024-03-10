package com.cyl.manager.oms.domain.entity;

import java.math.BigDecimal;
import com.ruoyi.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 微信订单表对象 oms_wechat_payment_history
 * 
 * @author zcc
 */
@ApiModel(description="微信订单表对象")
@Data
@TableName("oms_wechat_payment_history")
public class WechatPaymentHistory extends BaseAudit {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("ID")
    private Long id;

    @ApiModelProperty("payment_id")
    @Excel(name = "payment_id")
    private String paymentId;

    @ApiModelProperty("用户 ID")
    @Excel(name = "用户 ID")
    private Long memberId;

    @ApiModelProperty("OPENID")
    @Excel(name = "OPENID")
    private String openid;

    @ApiModelProperty("真实姓名，提现需要")
    @Excel(name = "真实姓名，提现需要")
    private String realName;

    @ApiModelProperty("标题|商品名称")
    @Excel(name = "标题|商品名称")
    private String title;

    @ApiModelProperty("订单号 支付时是payId 其他为orderId")
    @Excel(name = "订单号 支付时是payId 其他为orderId")
    private Long orderId;

    @ApiModelProperty("金额，单位分")
    @Excel(name = "金额，单位分")
    private BigDecimal money;

    @ApiModelProperty("交易类型（1为支付  2为提现  3为退款）")
    @Excel(name = "交易类型", readConverterExp = "1=为支付,2=为提现,3=为退款")
    private Integer opType;

    @ApiModelProperty("状态（0：未完成交易   1：完成关键交易）")
    @Excel(name = "状态", readConverterExp = "0=：未完成交易,1=：完成关键交易")
    private Integer paymentStatus;

    @ApiModelProperty("备注")
    @Excel(name = "备注")
    private String remark;

    @ApiModelProperty("附加数据")
    @Excel(name = "附加数据")
    private String attach;

    @ApiModelProperty("响应内容")
    @Excel(name = "响应内容")
    private String responseBody;

}
