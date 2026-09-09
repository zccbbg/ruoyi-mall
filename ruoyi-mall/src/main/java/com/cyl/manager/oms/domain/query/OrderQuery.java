package com.cyl.manager.oms.domain.query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 订单表 查询 对象
 *
 * @author zcc
 */
@Data
public class OrderQuery {
    /** MEMBER_ID 精确匹配 */
    private Long memberId;

    /** 用户帐号 精确匹配 */
    private String memberUsernameLike;

    /** 订单总金额 精确匹配 */
    private BigDecimal totalAmount;

    /** 采购价 精确匹配 */
    private BigDecimal purchasePrice;

    /** 应付金额（实际支付金额） 精确匹配 */
    private BigDecimal payAmount;

    /** 运费金额 精确匹配 */
    private BigDecimal freightAmount;

    /** 支付方式：0->未支付；1->支付宝；2->微信 精确匹配 */
    private Integer payType;

    /** 订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单 精确匹配 */
    private Integer status;

    /** 退款状态，枚举值：1：无售后或售后关闭，2：售后处理中，3：退款中，4： 退款成功 精确匹配 */
    private Integer aftersaleStatus;

    /** 物流公司 精确匹配 */
    private String deliveryCompany;

    /** 物流单号 精确匹配 */
    private String deliverySn;

    /** 自动确认时间（天） 精确匹配 */
    private Integer autoConfirmDay;

    /** 收货人姓名 精确匹配 */
    private String receiverNameLike;

    /** 收货人电话 精确匹配 */
    private String receiverPhone;

    /** 收货人邮编 精确匹配 */
    private String receiverPostCode;

    /** 省份/直辖市 精确匹配 */
    private String receiverProvince;

    /** 城市 精确匹配 */
    private String receiverCity;

    /** 区 精确匹配 */
    private String receiverDistrict;

    /** 省份/直辖市id 精确匹配 */
    private Long receiverProvinceId;

    /** 城市id 精确匹配 */
    private Long receiverCityId;

    /** 区id 精确匹配 */
    private Long receiverDistrictId;

    /** 详细地址 精确匹配 */
    private String receiverDetailAddress;

    /** 订单备注 精确匹配 */
    private String note;

    /** 确认收货状态：0->未确认；1->已确认 精确匹配 */
    private Integer confirmStatus;

    /** 删除状态：0->未删除；1->已删除 精确匹配 */
    private Integer deleteStatus;

    /** 支付时间 精确匹配 */
    private LocalDateTime paymentTime;

    /** 发货时间 精确匹配 */
    private LocalDateTime deliveryTime;

    /** 确认收货时间 精确匹配 */
    private LocalDateTime receiveTime;

    /** 创建订单开始时间 */
    private LocalDateTime startTime;

    /** 创建订单结束时间 */
    private LocalDateTime endTime;
}
