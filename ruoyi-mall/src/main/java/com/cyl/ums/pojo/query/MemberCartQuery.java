package com.cyl.ums.pojo.query;

import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 购物车 查询 对象
 *
 * @author zcc
 */
@ApiModel(description="购物车 查询 对象")
@Data
public class MemberCartQuery {
    @ApiModelProperty("用户ID 精确匹配")
    private Long memberId;

    @ApiModelProperty("商品ID 精确匹配")
    private Long productId;

    @ApiModelProperty("展示图片 精确匹配")
    private String pic;

    @ApiModelProperty("SKU ID 精确匹配")
    private Long skuId;

    @ApiModelProperty("PRODUCT_NAME 精确匹配")
    private String productNameLike;

    @ApiModelProperty("商品属性 精确匹配")
    private String spData;

    @ApiModelProperty("商品数量 精确匹配")
    private Integer cartNum;

}
