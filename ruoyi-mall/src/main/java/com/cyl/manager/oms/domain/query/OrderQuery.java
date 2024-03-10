package com.cyl.manager.oms.domain.query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 订单表 查询 对象
 *
 * @author zcc
 */
@ApiModel(description="订单表 查询 对象")
@Data
public class OrderQuery {
    @ApiModelProperty("MEMBER_ID 精确匹配")
    private Long memberId;

    @ApiModelProperty("用户帐号 精确匹配")
    private String memberUsernameLike;

    @ApiModelProperty("订单总金额 精确匹配")
    private BigDecimal totalAmount;

    @ApiModelProperty("采购价 精确匹配")
    private BigDecimal purchasePrice;

    @ApiModelProperty("应付金额（实际支付金额） 精确匹配")
    private BigDecimal payAmount;

    @ApiModelProperty("运费金额 精确匹配")
    private BigDecimal freightAmount;

    @ApiModelProperty("支付方式：0->未支付；1->支付宝；2->微信 精确匹配")
    private Integer payType;

    @ApiModelProperty("订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单 精确匹配")
    private Integer status;

    @ApiModelProperty("退款状态，枚举值：1：无售后或售后关闭，2：售后处理中，3：退款中，4： 退款成功 精确匹配")
    private Integer aftersaleStatus;

    @ApiModelProperty("物流公司 精确匹配")
    private String deliveryCompany;

    @ApiModelProperty("物流单号 精确匹配")
    private String deliverySn;

    @ApiModelProperty("自动确认时间（天） 精确匹配")
    private Integer autoConfirmDay;

    @ApiModelProperty("收货人姓名 精确匹配")
    private String receiverNameLike;

    @ApiModelProperty("收货人电话 精确匹配")
    private String receiverPhone;

    @ApiModelProperty("收货人邮编 精确匹配")
    private String receiverPostCode;

    @ApiModelProperty("省份/直辖市 精确匹配")
    private String receiverProvince;

    @ApiModelProperty("城市 精确匹配")
    private String receiverCity;

    @ApiModelProperty("区 精确匹配")
    private String receiverDistrict;

    @ApiModelProperty("省份/直辖市id 精确匹配")
    private Long receiverProvinceId;

    @ApiModelProperty("城市id 精确匹配")
    private Long receiverCityId;

    @ApiModelProperty("区id 精确匹配")
    private Long receiverDistrictId;

    @ApiModelProperty("详细地址 精确匹配")
    private String receiverDetailAddress;

    @ApiModelProperty("订单备注 精确匹配")
    private String note;

    @ApiModelProperty("确认收货状态：0->未确认；1->已确认 精确匹配")
    private Integer confirmStatus;

    @ApiModelProperty("删除状态：0->未删除；1->已删除 精确匹配")
    private Integer deleteStatus;

    @ApiModelProperty("支付时间 精确匹配")
    private LocalDateTime paymentTime;

    @ApiModelProperty("发货时间 精确匹配")
    private LocalDateTime deliveryTime;

    @ApiModelProperty("确认收货时间 精确匹配")
    private LocalDateTime receiveTime;

    @ApiModelProperty("创建订单开始时间")
    private LocalDateTime startTime;

    @ApiModelProperty("创建订单结束时间")
    private LocalDateTime endTime;
}
