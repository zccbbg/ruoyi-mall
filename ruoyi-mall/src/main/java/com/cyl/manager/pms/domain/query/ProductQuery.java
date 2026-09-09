package com.cyl.manager.pms.domain.query;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;

/**
 * 商品信息 查询 对象
 *
 * @author zcc
 */
@Data
public class ProductQuery {
    /** BRAND_ID 精确匹配 */
    private Long brandId;

    /** CATEGORY_ID 精确匹配 */
    private Long categoryId;

    /** 商品编码 精确匹配 */
    private String outProductId;

    /** NAME 精确匹配 */
    private String nameLike;

    /** 主图 精确匹配 */
    private String pic;

    /** 画册图片，连产品图片限制为5张，以逗号分割 精确匹配 */
    private String albumPics;

    /** 上架状态：0->下架；1->上架 精确匹配 */
    private Integer publishStatus;

    /** 排序 精确匹配 */
    private Integer sort;

    /** PRICE 精确匹配 */
    private BigDecimal price;

    /** 单位 精确匹配 */
    private String unit;

    private String productAttr;

    /** 商品重量，默认为克 精确匹配 */
    private BigDecimal weight;

    /** 产品详情网页内容 精确匹配 */
    private String detailHtml;

    /** 移动端网页详情 精确匹配 */
    private String detailMobileHtml;

    /** 品牌名称 精确匹配 */
    private String brandNameLike;

    /** 商品分类名称 精确匹配 */
    private String productCategoryNameLike;

    /** 排序字段 */
    private String orderField = "sort";

    /** 排序规则 */
    private String orderSort = "desc";

    /** 搜索关键字 */
    private String search;

    //排查的id
    private List<Long> excludeProductIds;

    private List<Long> ids;

}
