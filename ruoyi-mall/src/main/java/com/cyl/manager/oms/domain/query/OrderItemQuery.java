package com.cyl.manager.oms.domain.query;

import java.math.BigDecimal;
import lombok.Data;

/**
 * 订单中所包含的商品 查询 对象
 *
 * @author zcc
 */
@Data
public class OrderItemQuery {
    /** 订单id 精确匹配 */
    private Long orderId;

    /** PRODUCT_ID 精确匹配 */
    private Long productId;

    /** 商品编码 精确匹配 */
    private String outProductId;

    /** 商品sku id 精确匹配 */
    private Long skuId;

    /** sku编码 精确匹配 */
    private String outSkuId;

    /** 商品快照id 精确匹配 */
    private Long productSnapshotId;

    /** sku快照id 精确匹配 */
    private Long skuSnapshotId;

    /** 展示图片 精确匹配 */
    private String pic;

    /** PRODUCT_NAME 精确匹配 */
    private String productNameLike;

    /** 销售价格 精确匹配 */
    private BigDecimal salePrice;

    /** 采购价 精确匹配 */
    private BigDecimal purchasePrice;

    /** 购买数量 精确匹配 */
    private Integer quantity;

    /** 商品分类id 精确匹配 */
    private Long productCategoryId;

    /** 商品sku属性:[{\ */
    private String spData;

}
