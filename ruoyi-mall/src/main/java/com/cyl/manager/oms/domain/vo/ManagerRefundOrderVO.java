 package com.cyl.manager.oms.domain.vo;
import com.ruoyi.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


 @Data
 @ApiModel(value = "售后订单vo")
 public class ManagerRefundOrderVO {
     @ApiModelProperty(name = "id",value = "售后单id",required = true,dataType = "Long")
     private Long id;

     @ApiModelProperty(name = "orderId",value = "订单id",required = true,dataType = "Long")
     private Long orderId;

     @ApiModelProperty(name = "orderSn",value = "订单号",required = true,dataType = "String")
     private String orderSn;

     @ApiModelProperty(name = "payId",value = "支付ID",required = true,dataType = "Long")
     private Long payId;

     @ApiModelProperty(name = "phone",value = "用户手机号",required = true,dataType = "String")
     private String phone;

     @ApiModelProperty(name = "nickName",value = "用户昵称",required = true,dataType = "String")
     private String nickName;

     @ApiModelProperty(name = "status",value = "订单状态 0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单",required = true,dataType = "Integer")
     private Integer status;

     @ApiModelProperty(name = "userName",value = "用户名称",required = true,dataType = "String")
     private String userName;

     @ApiModelProperty(name = "payTime",value = "支付时间",required = true,dataType = "String")
     private String payTime;

     @ApiModelProperty(name = "payType",value = "支付方式 支付方式：0->未支付；1->支付宝；2->微信",required = true,dataType = "Integer")
     private Integer payType;

     @ApiModelProperty(name = "applyRefundTime",value = "申请售后的时间",required = true,dataType = "String")
     private String applyRefundTime;

     @ApiModelProperty(name = "refundFinishTime",value = "售后完成的时间",required = true,dataType = "String")
     private String refundFinishTime;

     @ApiModelProperty(name = "aftersaleStatus",value = "0->待处理；1->退货中；2->已完成；3->已拒绝； 4->用户取消",required = true,dataType = "String")
     private Integer aftersaleStatus;
     @ApiModelProperty(name = "note",value = "备注",required = true,dataType = "String")
     private String note;
     @ApiModelProperty(name = "applyRefundAmount",value = "退款金额",required = true,dataType = "BigDecimal")
     private BigDecimal applyReturnAmount;
     @ApiModelProperty(name = "refundNum",value = "退款数量",required = true,dataType = "Integer")
     private Integer refundNum;

     @ApiModelProperty(name = "applyRefundType",value = "申请退货方式：1-仅退款，2-退货退款",required = true,dataType = "String")
     private Integer applyRefundType;
     @ApiModelProperty(name = "handleTime",value = "处理时间",required = true,dataType = "LocalDateTime")
     private LocalDateTime handleTime;

     @ApiModelProperty(name = "reason",value = "原因",required = true,dataType = "String")
     private String reason;

     @ApiModelProperty(name = "description",value = "描述",required = true,dataType = "String")
     private String description;

     @ApiModelProperty(name = "proofPics",value = "凭证图片",required = true,dataType = "String")
     private String proofPics;

     @ApiModelProperty(name = "productInfo", value = "售后单商品数据")
     private List<ManagerOrderProductVO> productList;

     @ApiModelProperty(name = "handleMan", value = "处理人员")
     private String handleMan;

     private String mark;

     private String refundWpCode;

     @ApiModelProperty("退货快递号")
     private String refundWaybillCode;
 }
