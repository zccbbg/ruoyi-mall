package com.cyl.manager.oms.domain.vo;

import java.math.BigDecimal;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;
/**
 * 订单中所包含的商品 数据视图对象
 * 
 * @author zcc
 */
@Data
public class OrderItemVO extends BaseAudit {
   /** ID */
    private Long id;
   /** 订单id */
    @Excel(name = "订单id")
    private Long orderId;
   /** PRODUCT_ID */
    @Excel(name = "PRODUCT_ID")
    private Long productId;
   /** 商品编码 */
    @Excel(name = "商品编码")
    private String outProductId;
   /** 商品sku id */
    @Excel(name = "商品sku id")
    private Long skuId;
   /** sku编码 */
    @Excel(name = "sku编码")
    private String outSkuId;
   /** 商品快照id */
    @Excel(name = "商品快照id")
    private Long productSnapshotId;
   /** sku快照id */
    @Excel(name = "sku快照id")
    private Long skuSnapshotId;
   /** 展示图片 */
    @Excel(name = "展示图片")
    private String pic;
   /** PRODUCT_NAME */
    @Excel(name = "PRODUCT_NAME")
    private String productName;
   /** 销售价格 */
    @Excel(name = "销售价格")
    private BigDecimal salePrice;
   /** 采购价 */
    @Excel(name = "采购价")
    private BigDecimal purchasePrice;
   /** 购买数量 */
    @Excel(name = "购买数量")
    private Integer quantity;
   /** 商品分类id */
    @Excel(name = "商品分类id")
    private Long productCategoryId;
   /** 商品sku属性:[{\"key\":\"颜色\",\"value\":\"颜色\"},{\"key\":\"容量\",\"value\":\"4G\"}] */
    @Excel(name = "商品sku属性:[{\"key\":\"颜色\",\"value\":\"颜色\"},{\"key\":\"容量\",\"value\":\"4G\"}]")
    private String spData;
}
