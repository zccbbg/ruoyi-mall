 package com.cyl.h5.domain.dto;
 import lombok.Data;

 import javax.validation.constraints.NotBlank;
 import javax.validation.constraints.NotNull;

 /**
 * 发货记录
 * @author Jinxin
 * 
 */
 @Data
 public class DeliveryReq {

     /** 订单id */
     @NotNull(message = "订单id不能为空")
     private Long orderId;

     /** 物流单号 */
     @NotBlank(message = "物流单号不能为空")
     private String deliverySn;

     /** 快递公司Code */
     @NotBlank(message = "快递公司Code不能为空")
     private String deliveryCompanyCode;
}