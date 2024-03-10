package com.cyl.manager.pms.domain.query;

import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 商品分类 查询 对象
 *
 * @author zcc
 */
@ApiModel(description="商品分类 查询 对象")
@Data
public class ProductCategoryQuery {
    @ApiModelProperty("上级分类的编号：0表示一级分类 精确匹配")
    private Long parentId;

    @ApiModelProperty("NAME 精确匹配")
    private String nameLike;

    @ApiModelProperty("分类级别：0->1级；1->2级 精确匹配")
    private Integer level;

    @ApiModelProperty("显示状态：0->不显示；1->显示 精确匹配")
    private Integer showStatus;

    @ApiModelProperty("SORT 精确匹配")
    private Integer sort;

    @ApiModelProperty("图标 精确匹配")
    private String icon;

}
