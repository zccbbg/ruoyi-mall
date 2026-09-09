 package com.cyl.manager.oms.domain.form;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


 @Data
 /** 商城售后订单处理操作请求体 */
 public class DealWithAftersaleForm {
     /** 订单id */
     @NotBlank(message = "订单id不能为空")
     private Long orderId;

     /** 操作类型 1同意  2拒绝 3确认收货 */
     @NotNull(message = "操作类型不能为空")
     private Integer optType;

     /** 拒绝理由 操作类型为2时必填 */
     private String remark;
 }