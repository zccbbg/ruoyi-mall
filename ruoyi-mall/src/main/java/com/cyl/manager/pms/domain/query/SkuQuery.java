package com.cyl.manager.pms.domain.query;

import java.math.BigDecimal;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * sku信息 查询 对象
 *
 * @author zcc
 */
@ApiModel(description="sku信息 查询 对象")
@Data
public class SkuQuery {
    @ApiModelProperty("PRODUCT_ID 精确匹配")
    private Long productId;

    @ApiModelProperty("sku编码 精确匹配")
    private String outSkuId;

    @ApiModelProperty("PRICE 精确匹配")
    private BigDecimal price;

    @ApiModelProperty("展示图片 精确匹配")
    private String pic;

    @ApiModelProperty("商品销售属性，json格式 精确匹配")
    private String spData;

}
