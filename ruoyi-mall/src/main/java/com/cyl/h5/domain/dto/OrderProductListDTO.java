 package com.cyl.h5.domain.dto;

 import com.cyl.manager.pms.domain.entity.Product;
 import com.cyl.manager.pms.domain.entity.Sku;
 import lombok.Data;

 import javax.validation.constraints.Min;
 import javax.validation.constraints.NotNull;
 import java.math.BigDecimal;


 /**
  * 创建订单请求VO
  * @author Jinxin
  *
  */
 @Data
 public class OrderProductListDTO {
     /** 商品skuId */
     @NotNull(message = "商品skuId不能为空")
     private Long skuId;

     /** 数量 */
     @NotNull(message = "数量不能为空")
     @Min(value = 1, message = "数量不能小于1")
     private Integer quantity;

     /** 消费金 */
     private BigDecimal consumption;

     /** 运费 */
     private BigDecimal freightAmount;

     /** 隐藏 业务过程中的数据 */
     private Sku sku;

     private Product product;
 }