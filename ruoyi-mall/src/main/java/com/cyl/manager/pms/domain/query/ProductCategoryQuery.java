package com.cyl.manager.pms.domain.query;

import lombok.Data;

/**
 * 商品分类 查询 对象
 *
 * @author zcc
 */
@Data
public class ProductCategoryQuery {
    /** 上级分类的编号：0表示一级分类 精确匹配 */
    private Long parentId;

    /** NAME 精确匹配 */
    private String nameLike;

    /** 分类级别：0->1级；1->2级 精确匹配 */
    private Integer level;

    /** 显示状态：0->不显示；1->显示 精确匹配 */
    private Integer showStatus;

    /** SORT 精确匹配 */
    private Integer sort;

    /** 图标 精确匹配 */
    private String icon;

}
