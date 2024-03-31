package com.cyl.manager.oms.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@ApiModel(value = "管理后台订单VO")
public class ManagerOrderVO {
    @ApiModelProperty(name = "id",value = "订单id",required = true,dataType = "Long")
    private Long id;
    @ApiModelProperty(name = "orderSn",value = "订单编号",required = true,dataType = "String")
    private String orderSn;
    @ApiModelProperty(name = "productId",value = "商品id",required = true,dataType = "Long")
    private Long productId;

    @ApiModelProperty(name = "productName",value = "商品名称",required = true,dataType = "String")
    private String productName;

    @ApiModelProperty(name = "userPhone",value = "用户账号",required = true,dataType = "String")
    private String userPhone;

    @ApiModelProperty(name = "nickName",value = "用户昵称",required = true,dataType = "String")
    private String nickName;

    @ApiModelProperty(name = "avatar",value = "用户头像",required = true,dataType = "String")
    private String avatar;

    @ApiModelProperty(name = "status",value = "订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单",required = true,dataType = "Integer")
    private Integer status;

    @ApiModelProperty("退款状态，枚举值：1：无售后或售后关闭，2：售后处理中，3：退款中，4： 退款成功")
    private Integer aftersaleStatus;

    @ApiModelProperty(name = "pic",value = "商品图片",required = true,dataType = "String")
    private String pic;

    @ApiModelProperty(name = "userName",value = "用户名称",required = true,dataType = "String")
    private String userName;

    @ApiModelProperty(name = "buyNum",value = "购买数量",required = true,dataType = "Integer")
    private Integer buyNum;

    @ApiModelProperty(name = "totalAmount",value = "订单总金额",required = true,dataType = "BigDecimal")
    private BigDecimal totalAmount;

    @ApiModelProperty(name = "payAmount",value = "应付金额",required = true,dataType = "BigDecimal")
    private BigDecimal payAmount;

    @ApiModelProperty(name = "createTime",value = "下单时间",required = true,dataType = "Date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @ApiModelProperty(name = "payTime",value = "支付时间",required = true,dataType = "Date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime payTime;

    @ApiModelProperty(name = "payType",value = "支付方式 支付方式：0->未支付；1->支付宝；2->微信",required = true,dataType = "Integer")
    private Integer payType;

    @ApiModelProperty(name = "receiveTime",value = "确认收货时间",required = true,dataType = "Date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime receiveTime;

    @ApiModelProperty(name = "note",value = "备注",required = true,dataType = "String")
    private String note;

    @ApiModelProperty("商家备注")
    private String merchantNote;

    @ApiModelProperty(name = "spData",value = "商品sku属性",required = true,dataType = "String")
    private String spData;

    @ApiModelProperty(name = "productInfo", value = "订单商品数据")
    private List<ManagerOrderProductVO> productList;

    @ApiModelProperty(name = "deliveryTime",value = "发货时间",required = true,dataType = "LocalDataTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime deliveryTime;

    @ApiModelProperty(name = "deliverySn",value = "物流单号",required = true,dataType = "String")
    private String deliverySn;

    private String receiverName;

    private String receiverPhone;

    private String receiverProvince;

    private String receiverCity;

    private String receiverDistrict;

    private String receiverDetailAddress;

    private String mark;

    private BigDecimal couponAmount;
}
