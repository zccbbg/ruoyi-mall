package com.cyl.pms.pojo.query;

import java.math.BigDecimal;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 商品信息 查询 对象
 *
 * @author zcc
 */
@ApiModel(description="商品信息 查询 对象")
@Data
public class ProductQuery {
    @ApiModelProperty("BRAND_ID 精确匹配")
    private Long brandId;

    @ApiModelProperty("CATEGORY_ID 精确匹配")
    private Long categoryId;

    @ApiModelProperty("商品编码 精确匹配")
    private String outProductId;

    @ApiModelProperty("NAME 精确匹配")
    private String nameLike;

    @ApiModelProperty("主图 精确匹配")
    private String pic;

    @ApiModelProperty("画册图片，连产品图片限制为5张，以逗号分割 精确匹配")
    private String albumPics;

    @ApiModelProperty("上架状态：0->下架；1->上架 精确匹配")
    private Integer publishStatus;

    @ApiModelProperty("排序 精确匹配")
    private Integer sort;

    @ApiModelProperty("PRICE 精确匹配")
    private BigDecimal price;

    @ApiModelProperty("单位 精确匹配")
    private String unit;

    @ApiModelProperty("商品重量，默认为克 精确匹配")
    private BigDecimal weight;

    @ApiModelProperty("产品详情网页内容 精确匹配")
    private String detailHtml;

    @ApiModelProperty("移动端网页详情 精确匹配")
    private String detailMobileHtml;

    @ApiModelProperty("品牌名称 精确匹配")
    private String brandNameLike;

    @ApiModelProperty("商品分类名称 精确匹配")
    private String productCategoryNameLike;

}
