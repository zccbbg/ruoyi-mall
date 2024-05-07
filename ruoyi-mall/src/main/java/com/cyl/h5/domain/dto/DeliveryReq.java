 package com.cyl.h5.domain.dto;

 import io.swagger.annotations.ApiModel;
 import io.swagger.annotations.ApiModelProperty;
 import lombok.Data;

 import javax.validation.constraints.NotBlank;
 import javax.validation.constraints.NotNull;

 /**
 * 发货记录
 * @author Jinxin
 * 
 */
 @Data
 @ApiModel(value = "发货记录")
 public class DeliveryReq {

     @ApiModelProperty(value = "订单id", required = true)
     @NotNull(message = "订单id不能为空")
     private Long orderId;

     @ApiModelProperty(value = "物流单号", required = true)
     @NotBlank(message = "物流单号不能为空")
     private String deliverySn;

     @ApiModelProperty(value = "快递公司Code", required = true)
     @NotBlank(message = "快递公司Code不能为空")
     private String deliveryCompanyCode;
}