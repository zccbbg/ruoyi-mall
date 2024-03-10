 package com.cyl.manager.oms.domain.form;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


 @Data
 @ApiModel(value = "商城售后订单处理操作请求体")
 public class DealWithAftersaleForm {
     @ApiModelProperty(name = "orderId",value = "订单id",required = true,dataType = "Long")
     @NotBlank(message = "订单id不能为空")
     private Long orderId;

     @ApiModelProperty(name = "optType",value = "操作类型 1同意  2拒绝 3确认收货",required = true,dataType = "String")
     @NotNull(message = "操作类型不能为空")
     private Integer optType;

     @ApiModelProperty(name = "remark",value = "拒绝理由 操作类型为2时必填",required = true,dataType = "String")
     private String remark;
 }