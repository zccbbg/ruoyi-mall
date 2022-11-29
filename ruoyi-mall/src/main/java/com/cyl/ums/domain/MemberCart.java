package com.cyl.ums.domain;

import com.ruoyi.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 购物车对象 ums_member_cart
 * 
 * @author zcc
 */
@ApiModel(description="购物车对象")
@Data
@TableName("ums_member_cart")
public class MemberCart extends BaseAudit {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("购物车表ID")
    private Long id;

    @ApiModelProperty("用户ID")
    @Excel(name = "用户ID")
    private Long memberId;

    @ApiModelProperty("商品ID")
    @Excel(name = "商品ID")
    private Long productId;

    @ApiModelProperty("展示图片")
    @Excel(name = "展示图片")
    private String pic;

    @ApiModelProperty("SKU ID")
    @Excel(name = "SKU ID")
    private Long skuId;

    @ApiModelProperty("PRODUCT_NAME")
    @Excel(name = "PRODUCT_NAME")
    private String productName;

    @ApiModelProperty("商品属性")
    @Excel(name = "商品属性")
    private String spData;

    @ApiModelProperty("商品数量")
    @Excel(name = "商品数量")
    private Integer cartNum;

}
