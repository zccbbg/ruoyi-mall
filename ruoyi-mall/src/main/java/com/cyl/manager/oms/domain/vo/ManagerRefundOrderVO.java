 package com.cyl.manager.oms.domain.vo;
import com.ruoyi.common.annotation.Excel;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


 @Data
 /** 售后订单vo */
 public class ManagerRefundOrderVO {
     /** 售后单id */
     private Long id;

     /** 订单id */
     private Long orderId;

     /** 订单号 */
     private String orderSn;

     /** 支付ID */
     private Long payId;

     /** 用户手机号 */
     private String phone;

     /** 用户昵称 */
     private String nickName;

     /** 订单状态 0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单 */
     private Integer status;

     /** 用户名称 */
     private String userName;

     /** 支付时间 */
     private String payTime;

     /** 支付方式 支付方式：0->未支付；1->支付宝；2->微信 */
     private Integer payType;

     /** 申请售后的时间 */
     private String applyRefundTime;

     /** 售后完成的时间 */
     private String refundFinishTime;

     /** 0->待处理；1->退货中；2->已完成；3->已拒绝； 4->用户取消 */
     private Integer aftersaleStatus;
     /** 备注 */
     private String note;
     /** 退款金额 */
     private BigDecimal applyReturnAmount;
     /** 退款数量 */
     private Integer refundNum;

     /** 申请退货方式：1-仅退款，2-退货退款 */
     private Integer applyRefundType;
     /** 处理时间 */
     private LocalDateTime handleTime;

     /** 原因 */
     private String reason;

     /** 描述 */
     private String description;

     /** 凭证图片 */
     private String proofPics;

     /** 售后单商品数据 */
     private List<ManagerOrderProductVO> productList;

     /** 处理人员 */
     private String handleMan;

     private String mark;

     private String refundWpCode;

     /** 退货快递号 */
     private String refundWaybillCode;
 }
