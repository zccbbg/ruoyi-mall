package com.cyl.ums.pojo.vo;

import com.ruoyi.common.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;
/**
 * 购物车 数据视图对象
 * 
 * @author zcc
 */
@Data
public class MemberCartVO extends BaseAudit {
   /** 购物车表ID */
    private Long id;
   /** 用户ID */
    @Excel(name = "用户ID")
    private Long memberId;
   /** 商品ID */
    @Excel(name = "商品ID")
    private Long productId;
   /** 展示图片 */
    @Excel(name = "展示图片")
    private String pic;
   /** SKU ID */
    @Excel(name = "SKU ID")
    private Long skuId;
   /** PRODUCT_NAME */
    @Excel(name = "PRODUCT_NAME")
    private String productName;
   /** 商品属性 */
    @Excel(name = "商品属性")
    private String spData;
   /** 商品数量 */
    @Excel(name = "商品数量")
    private Integer cartNum;
}
