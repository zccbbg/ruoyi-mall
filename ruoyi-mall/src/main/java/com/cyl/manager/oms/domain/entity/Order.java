package com.cyl.manager.oms.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseAudit;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
/**
 * 订单表对象 oms_order
 * 
 * @author zcc
 */
@ApiModel(description="订单表对象")
@Data
@TableName("oms_order")
public class Order extends BaseAudit {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("订单id")
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty("支付id")
    private Long payId;

    @ApiModelProperty("订单编号")
    @Excel(name = "订单编号")
    private String orderSn;

    @ApiModelProperty("MEMBER_ID")
    @Excel(name = "MEMBER_ID")
    private Long memberId;

    @ApiModelProperty("用户帐号")
    @Excel(name = "用户帐号")
    private String memberUsername;

    @ApiModelProperty("订单总金额")
    @Excel(name = "订单总金额")
    private BigDecimal totalAmount;

    @ApiModelProperty("采购价")
    @Excel(name = "采购价")
    private BigDecimal purchasePrice;

    @ApiModelProperty("应付金额（实际支付金额）")
    @Excel(name = "应付金额", readConverterExp = "实=际支付金额")
    private BigDecimal payAmount;

    @ApiModelProperty("运费金额")
    @Excel(name = "运费金额")
    private BigDecimal freightAmount;

    @ApiModelProperty("支付方式：0->未支付；1->支付宝；2->微信")
    @Excel(name = "支付方式：0->未支付；1->支付宝；2->微信")
    private Integer payType;

    @ApiModelProperty("订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单")
    @Excel(name = "订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单")
    private Integer status;

    @ApiModelProperty("退款状态，枚举值：1：无售后或售后关闭，2：售后处理中，3：退款中，4： 退款成功")
    @Excel(name = "退款状态，枚举值：1：无售后或售后关闭，2：售后处理中，3：退款中，4： 退款成功")
    private Integer aftersaleStatus;

    @ApiModelProperty("物流公司(配送方式)")
    @Excel(name = "物流公司(配送方式)")
    private String deliveryCompany;

    @ApiModelProperty("物流单号")
    @Excel(name = "物流单号")
    private String deliverySn;

    @ApiModelProperty("自动确认时间（天）")
    @Excel(name = "自动确认时间", readConverterExp = "天=")
    private Integer autoConfirmDay;

    @ApiModelProperty("收货人姓名")
    @Excel(name = "收货人姓名")
    private String receiverName;

    @ApiModelProperty("收货人电话")
    @Excel(name = "收货人电话")
    private String receiverPhone;

    @ApiModelProperty("加密的手机号")
    @Excel(name = "加密的手机号")
    private String receiverPhoneEncrypted;

    @ApiModelProperty("收货人邮编")
    @Excel(name = "收货人邮编")
    private String receiverPostCode;

    @ApiModelProperty("省份/直辖市")
    @Excel(name = "省份/直辖市")
    private String receiverProvince;

    @ApiModelProperty("城市")
    @Excel(name = "城市")
    private String receiverCity;

    @ApiModelProperty("区")
    @Excel(name = "区")
    private String receiverDistrict;

    @ApiModelProperty("省份/直辖市id")
    @Excel(name = "省份/直辖市id")
    private Long receiverProvinceId;

    @ApiModelProperty("城市id")
    @Excel(name = "城市id")
    private Long receiverCityId;

    @ApiModelProperty("区id")
    @Excel(name = "区id")
    private Long receiverDistrictId;

    @ApiModelProperty("详细地址")
    @Excel(name = "详细地址")
    private String receiverDetailAddress;

    @ApiModelProperty("订单备注")
    @Excel(name = "订单备注")
    private String note;

    @ApiModelProperty("商家备注")
    @Excel(name = "商家备注")
    private String merchantNote;

    @ApiModelProperty("确认收货状态：0->未确认；1->已确认")
    @Excel(name = "确认收货状态：0->未确认；1->已确认")
    private Integer confirmStatus;

    @ApiModelProperty("删除状态：0->未删除；1->已删除")
    @Excel(name = "删除状态：0->未删除；1->已删除")
    private Integer deleteStatus;

    @ApiModelProperty("支付时间")
    @Excel(name = "支付时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime paymentTime;

    @ApiModelProperty("发货时间")
    @Excel(name = "发货时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime deliveryTime;

    @ApiModelProperty("确认收货时间")
    @Excel(name = "确认收货时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime receiveTime;

    @ApiModelProperty("优惠券ID")
    private Long memberCouponId;

    @ApiModelProperty("优惠券金额")
    private BigDecimal couponAmount;

}
