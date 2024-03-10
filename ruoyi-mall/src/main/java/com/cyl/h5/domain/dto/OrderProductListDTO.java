 package com.cyl.h5.domain.dto;

 import com.cyl.manager.pms.domain.entity.Product;
 import com.cyl.manager.pms.domain.entity.Sku;
 import io.swagger.annotations.ApiModel;
 import io.swagger.annotations.ApiModelProperty;
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
 @ApiModel(value = "创建订单请求VO")
 public class OrderProductListDTO {
     @ApiModelProperty(value = "商品skuId", required = true)
     @NotNull(message = "商品skuId不能为空")
     private Long skuId;

     @ApiModelProperty(value = "数量", required = true)
     @NotNull(message = "数量不能为空")
     @Min(value = 1, message = "数量不能小于1")
     private Integer quantity;

     @ApiModelProperty(value = "消费金", hidden = true)
     private BigDecimal consumption;

     @ApiModelProperty(value = "运费", hidden = true)
     private BigDecimal freightAmount;

     @ApiModelProperty(value = "隐藏 业务过程中的数据", hidden = true)
     private Sku sku;

     @ApiModelProperty(value = "隐藏 业务过程中的数据", hidden = true)
     private Product product;
 }