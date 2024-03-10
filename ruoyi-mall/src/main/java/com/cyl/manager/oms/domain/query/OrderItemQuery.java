package com.cyl.manager.oms.domain.query;

import java.math.BigDecimal;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 订单中所包含的商品 查询 对象
 *
 * @author zcc
 */
@ApiModel(description="订单中所包含的商品 查询 对象")
@Data
public class OrderItemQuery {
    @ApiModelProperty("订单id 精确匹配")
    private Long orderId;

    @ApiModelProperty("PRODUCT_ID 精确匹配")
    private Long productId;

    @ApiModelProperty("商品编码 精确匹配")
    private String outProductId;

    @ApiModelProperty("商品sku id 精确匹配")
    private Long skuId;

    @ApiModelProperty("sku编码 精确匹配")
    private String outSkuId;

    @ApiModelProperty("商品快照id 精确匹配")
    private Long productSnapshotId;

    @ApiModelProperty("sku快照id 精确匹配")
    private Long skuSnapshotId;

    @ApiModelProperty("展示图片 精确匹配")
    private String pic;

    @ApiModelProperty("PRODUCT_NAME 精确匹配")
    private String productNameLike;

    @ApiModelProperty("销售价格 精确匹配")
    private BigDecimal salePrice;

    @ApiModelProperty("采购价 精确匹配")
    private BigDecimal purchasePrice;

    @ApiModelProperty("购买数量 精确匹配")
    private Integer quantity;

    @ApiModelProperty("商品分类id 精确匹配")
    private Long productCategoryId;

    @ApiModelProperty("商品sku属性:[{\"key\":\"颜色\",\"value\":\"颜色\"},{\"key\":\"容量\",\"value\":\"4G\"}] 精确匹配")
    private String spData;

}
