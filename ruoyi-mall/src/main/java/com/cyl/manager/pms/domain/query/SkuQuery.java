package com.cyl.manager.pms.domain.query;

import java.math.BigDecimal;
import lombok.Data;

/**
 * sku信息 查询 对象
 *
 * @author zcc
 */
@Data
public class SkuQuery {
    /** PRODUCT_ID 精确匹配 */
    private Long productId;

    /** sku编码 精确匹配 */
    private String outSkuId;

    /** PRICE 精确匹配 */
    private BigDecimal price;

    /** 展示图片 精确匹配 */
    private String pic;

    /** 商品销售属性，json格式 精确匹配 */
    private String spData;

}
