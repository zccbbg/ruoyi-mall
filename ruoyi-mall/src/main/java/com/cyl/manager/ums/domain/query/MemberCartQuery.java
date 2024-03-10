package com.cyl.manager.ums.domain.query;

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
    @ApiModelProperty("0->失效；1->有效 精确匹配")
    private Integer status;

    @ApiModelProperty("用户ID 精确匹配")
    private Long memberId;

    @ApiModelProperty("商品ID 精确匹配")
    private Long productId;

    @ApiModelProperty("展示图片 精确匹配")
    private String pic;

    @ApiModelProperty("SKU ID 精确匹配")
    private Long skuId;

    @ApiModelProperty("商品名称 精确匹配")
    private String productName;

    @ApiModelProperty("商品属性 精确匹配")
    private String spData;

    @ApiModelProperty("商品数量 精确匹配")
    private Integer quantity;

    @ApiModelProperty("用户手机号")
    private String phone;

}
