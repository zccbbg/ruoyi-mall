 package com.cyl.h5.domain.form;

 import com.cyl.h5.domain.dto.OrderProductListDTO;
 import io.swagger.annotations.ApiModel;
 import io.swagger.annotations.ApiModelProperty;
 import lombok.Data;

 import java.util.List;


 /**
  * 创建订单请求VO
  * @author Jinxin
  *
  */
 @Data
 @ApiModel(value = "创建订单请求VO")
 public class OrderCreateForm {
     @ApiModelProperty(value = "商品购买明细",required = true)
     private List<OrderProductListDTO> skuList;

     @ApiModelProperty(value = "收货地址id")
     private Long receiveAddressId;

     @ApiModelProperty(value = "收货方式 1：快递 2:自提 代理商只有1", required = false)
     private Integer deliveryType = 1;

     @ApiModelProperty(value = "支付方式：1-支付宝，2-微信（默认）", required = false, allowableValues = "1,2")
//     @NotNull(message = "支付方式不能为空")
     private Integer payType;

     @ApiModelProperty(value = "订单备注")
     private String note;

     @ApiModelProperty(value = "订单来源 购物车是 cart")
     private String from;
 }