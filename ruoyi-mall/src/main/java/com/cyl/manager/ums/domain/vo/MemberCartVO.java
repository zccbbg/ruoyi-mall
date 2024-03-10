package com.cyl.manager.ums.domain.vo;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 购物车 数据视图对象
 * 
 * @author zcc
 */
@Data
public class MemberCartVO extends BaseAudit {
   /** 购物车表ID */
    private Long id;
   /** 0->失效；1->有效 */
    @Excel(name = "0->失效；1->有效")
    private Integer status;
   /** 用户ID */
    @Excel(name = "用户ID")
    private Long memberId;
    private String nickname;
    private String mark;
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
    private Integer quantity;
    /** 加入时间 */
    private LocalDateTime createTime;
    /** sku价格 */
    private BigDecimal price;
    /** sku是否存在 */
    private Integer skuIfExist;
    /** 隐藏四位的手机号 */
    private String phoneHidden;
}
