package com.cyl.manager.ums.domain.query;

import lombok.Data;

/**
 * 购物车 查询 对象
 *
 * @author zcc
 */
@Data
public class MemberCartQuery {
    /** 0->失效；1->有效 精确匹配 */
    private Integer status;

    /** 用户ID 精确匹配 */
    private Long memberId;

    /** 商品ID 精确匹配 */
    private Long productId;

    /** 展示图片 精确匹配 */
    private String pic;

    /** SKU ID 精确匹配 */
    private Long skuId;

    /** 商品名称 精确匹配 */
    private String productName;

    /** 商品属性 精确匹配 */
    private String spData;

    /** 商品数量 精确匹配 */
    private Integer quantity;

    /** 用户手机号 */
    private String phone;

}
