package com.cyl.manager.pms.domain.entity;

import java.math.BigDecimal;
import com.ruoyi.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * sku信息对象 pms_sku
 * 
 * @author zcc
 */
@ApiModel(description="sku信息对象")
@Data
@TableName("pms_sku")
public class Sku extends BaseAudit {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("ID")
    private Long id;

    @ApiModelProperty("PRODUCT_ID")
    @Excel(name = "PRODUCT_ID")
    private Long productId;

    @ApiModelProperty("sku编码")
    @Excel(name = "sku编码")
    private String outSkuId;

    @ApiModelProperty("PRICE")
    @Excel(name = "PRICE")
    private BigDecimal price;

    @ApiModelProperty("展示图片")
    @Excel(name = "展示图片")
    private String pic;

    @ApiModelProperty("商品销售属性，json格式")
    @Excel(name = "商品销售属性，json格式")
    private String spData;

    @ApiModelProperty("库存数")
    @Excel(name = "库存数")
    private Integer stock;

}
