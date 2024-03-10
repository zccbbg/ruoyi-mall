package com.cyl.manager.oms.domain.vo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.cyl.manager.oms.domain.entity.OrderItem;
import com.ruoyi.common.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;
/**
 * 订单表 数据视图对象
 * 
 * @author zcc
 */
@Data
public class OrderVO extends BaseAudit {
   /** 订单id */
    private Long id;
    /** 支付id */
    private Long payId;
    /** 订单编号 */
    private String orderSn;
   /** MEMBER_ID */
    @Excel(name = "MEMBER_ID")
    private Long memberId;
   /** 用户帐号 */
    @Excel(name = "用户帐号")
    private String memberUsername;
   /** 订单总金额 */
    @Excel(name = "订单总金额")
    private BigDecimal totalAmount;
   /** 采购价 */
    @Excel(name = "采购价")
    private BigDecimal purchasePrice;
   /** 应付金额（实际支付金额） */
    @Excel(name = "应付金额", readConverterExp = "实=际支付金额")
    private BigDecimal payAmount;
   /** 运费金额 */
    @Excel(name = "运费金额")
    private BigDecimal freightAmount;
   /** 支付方式：0->未支付；1->支付宝；2->微信 */
    @Excel(name = "支付方式：0->未支付；1->支付宝；2->微信")
    private Integer payType;
   /** 订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单 */
    @Excel(name = "订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单")
    private Integer status;
   /** 退款状态，枚举值：1：无售后或售后关闭，2：售后处理中，3：退款中，4： 退款成功 */
    @Excel(name = "退款状态，枚举值：1：无售后或售后关闭，2：售后处理中，3：退款中，4： 退款成功")
    private Integer aftersaleStatus;
   /** 物流公司(配送方式) */
    @Excel(name = "物流公司(配送方式)")
    private String deliveryCompany;
   /** 物流单号 */
    @Excel(name = "物流单号")
    private String deliverySn;
   /** 自动确认时间（天） */
    @Excel(name = "自动确认时间", readConverterExp = "天=")
    private Integer autoConfirmDay;
   /** 收货人姓名 */
    @Excel(name = "收货人姓名")
    private String receiverName;
   /** 收货人电话 */
    @Excel(name = "收货人电话")
    private String receiverPhone;
   /** 收货人邮编 */
    @Excel(name = "收货人邮编")
    private String receiverPostCode;
   /** 省份/直辖市 */
    @Excel(name = "省份/直辖市")
    private String receiverProvince;
   /** 城市 */
    @Excel(name = "城市")
    private String receiverCity;
   /** 区 */
    @Excel(name = "区")
    private String receiverDistrict;
   /** 省份/直辖市id */
    @Excel(name = "省份/直辖市id")
    private Long receiverProvinceId;
   /** 城市id */
    @Excel(name = "城市id")
    private Long receiverCityId;
   /** 区id */
    @Excel(name = "区id")
    private Long receiverDistrictId;
   /** 详细地址 */
    @Excel(name = "详细地址")
    private String receiverDetailAddress;
   /** 订单备注 */
    @Excel(name = "订单备注")
    private String note;
   /** 确认收货状态：0->未确认；1->已确认 */
    @Excel(name = "确认收货状态：0->未确认；1->已确认")
    private Integer confirmStatus;
   /** 删除状态：0->未删除；1->已删除 */
    @Excel(name = "删除状态：0->未删除；1->已删除")
    private Integer deleteStatus;
   /** 支付时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "支付时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime paymentTime;
   /** 发货时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "发货时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime deliveryTime;
   /** 确认收货时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "确认收货时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime receiveTime;
    private List<OrderItem> items;
}
