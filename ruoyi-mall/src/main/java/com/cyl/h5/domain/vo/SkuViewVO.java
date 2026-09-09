package com.cyl.h5.domain.vo;
import lombok.Data;

import java.math.BigDecimal;


/**
 * sku渲染详情
 *
 * @author Jinxin
 */
@Data
public class SkuViewVO {
    private Long productId;
    private Long skuId;
    /** 商品名称 */
    private String productName;
    /** 销售属性 */
    private String spData;
    /** 购买数量 */
    private Integer quantity;
    /** 主图 */
    private String pic;
    /** 售价 */
    private BigDecimal price;
    /** 库存数 */
    private Integer stock;
}