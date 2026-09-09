 package com.cyl.h5.domain.form;

 import com.cyl.h5.domain.dto.OrderProductListDTO;
 import lombok.Data;

 import java.util.List;


 /**
  * 创建订单请求VO
  * @author Jinxin
  *
  */
 @Data
 public class OrderCreateForm {
     /** 商品购买明细 */
     private List<OrderProductListDTO> skuList;

     /** 收货地址id */
     private Long receiveAddressId;

     /** 收货方式 1：快递 2:自提 代理商只有1 */
     private Integer deliveryType = 1;

     /** 支付方式：1-支付宝，2-微信（默认） */
//     @NotNull(message = "支付方式不能为空")
     private Integer payType;

     /** 订单备注 */
     private String note;

     /** 订单来源 购物车是 cart */
     private String from;
 }