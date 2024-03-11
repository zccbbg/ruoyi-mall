package com.cyl.h5.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;


/**
 * sku渲染详情
 *
 * @author Jinxin
 */
@Data
@ApiModel(value = "sku渲染详情")
public class SkuViewVO {
    private Long productId;
    private Long skuId;
    @ApiModelProperty(value = "商品名称")
    private String productName;
    @ApiModelProperty(value = "销售属性")
    private String spData;
    @ApiModelProperty(value = "购买数量")
    private Integer quantity;
    @ApiModelProperty(value = "主图")
    private String pic;
    @ApiModelProperty(value = "售价")
    private BigDecimal price;
    @ApiModelProperty(value = "库存数")
    private Integer stock;
}