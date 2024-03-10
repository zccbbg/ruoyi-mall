package com.cyl.manager.pms.domain.entity;

import java.math.BigDecimal;
import com.ruoyi.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 商品信息对象 pms_product
 * 
 * @author zcc
 */
@ApiModel(description="商品信息对象")
@Data
@TableName("pms_product")
public class Product extends BaseAudit {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("ID")
    private Long id;

    @ApiModelProperty("BRAND_ID")
    @Excel(name = "BRAND_ID")
    private Long brandId;

    @ApiModelProperty("CATEGORY_ID")
    @Excel(name = "CATEGORY_ID")
    private Long categoryId;

    @ApiModelProperty("商品编码")
    @Excel(name = "商品编码")
    private String outProductId;

    @ApiModelProperty("NAME")
    @Excel(name = "NAME")
    private String name;

    @ApiModelProperty("主图")
    @Excel(name = "主图")
    private String pic;

    @ApiModelProperty("画册图片，连产品图片限制为5张，以逗号分割")
    @Excel(name = "画册图片，连产品图片限制为5张，以逗号分割")
    private String albumPics;

    @ApiModelProperty("上架状态：0->下架；1->上架")
    @Excel(name = "上架状态：0->下架；1->上架")
    private Integer publishStatus;

    @ApiModelProperty("排序")
    @Excel(name = "排序")
    private Integer sort;

    @ApiModelProperty("PRICE")
    @Excel(name = "PRICE")
    private BigDecimal price;

    @ApiModelProperty("单位")
    @Excel(name = "单位")
    private String unit;

    @ApiModelProperty("商品重量，默认为克")
    @Excel(name = "商品重量，默认为克")
    private BigDecimal weight;

    @ApiModelProperty("商品销售属性，json格式")
    @Excel(name = "商品销售属性，json格式")
    private String productAttr;

    @ApiModelProperty("产品详情网页内容")
    @Excel(name = "产品详情网页内容")
    private String detailHtml;

    @ApiModelProperty("移动端网页详情")
    @Excel(name = "移动端网页详情")
    private String detailMobileHtml;

    @ApiModelProperty("品牌名称")
    @Excel(name = "品牌名称")
    private String brandName;

    @ApiModelProperty("商品分类名称")
    @Excel(name = "商品分类名称")
    private String productCategoryName;

}
