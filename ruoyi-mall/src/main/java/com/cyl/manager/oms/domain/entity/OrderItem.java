package com.cyl.manager.oms.domain.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.ruoyi.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 订单中所包含的商品对象 oms_order_item
 * 
 * @author zcc
 */
@ApiModel(description="订单中所包含的商品对象")
@Data
@TableName("oms_order_item")
public class OrderItem extends BaseAudit {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("ID")
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty("订单id")
    @Excel(name = "订单id")
    private Long orderId;

    @ApiModelProperty("PRODUCT_ID")
    @Excel(name = "PRODUCT_ID")
    private Long productId;

    @ApiModelProperty("商品编码")
    @Excel(name = "商品编码")
    private String outProductId;

    @ApiModelProperty("商品sku id")
    @Excel(name = "商品sku id")
    private Long skuId;

    @ApiModelProperty("sku编码")
    @Excel(name = "sku编码")
    private String outSkuId;

    @ApiModelProperty("商品快照id")
    @Excel(name = "商品快照id")
    private Long productSnapshotId;

    @ApiModelProperty("sku快照id")
    @Excel(name = "sku快照id")
    private Long skuSnapshotId;

    @ApiModelProperty("展示图片")
    @Excel(name = "展示图片")
    private String pic;

    @ApiModelProperty("PRODUCT_NAME")
    @Excel(name = "PRODUCT_NAME")
    private String productName;

    @ApiModelProperty("销售价格")
    @Excel(name = "销售价格")
    private BigDecimal salePrice;

    @ApiModelProperty("采购价")
    @Excel(name = "采购价")
    private BigDecimal purchasePrice;

    @ApiModelProperty("购买数量")
    @Excel(name = "购买数量")
    private Integer quantity;

    @ApiModelProperty("商品分类id")
    @Excel(name = "商品分类id")
    private Long productCategoryId;

    @ApiModelProperty("商品sku属性:[{\"key\":\"颜色\",\"value\":\"颜色\"},{\"key\":\"容量\",\"value\":\"4G\"}]")
    @Excel(name = "商品sku属性:[{\"key\":\"颜色\",\"value\":\"颜色\"},{\"key\":\"容量\",\"value\":\"4G\"}]")
    private String spData;

}
