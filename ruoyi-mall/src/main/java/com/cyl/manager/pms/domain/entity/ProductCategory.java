package com.cyl.manager.pms.domain.entity;

import com.ruoyi.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 商品分类对象 pms_product_category
 * 
 * @author zcc
 */
@ApiModel(description="商品分类对象")
@Data
@TableName("pms_product_category")
public class ProductCategory extends BaseAudit {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("ID")
    private Long id;

    @ApiModelProperty("上级分类的编号：0表示一级分类")
    @Excel(name = "上级分类的编号：0表示一级分类")
    private Long parentId;

    @ApiModelProperty("NAME")
    @Excel(name = "NAME")
    private String name;

    @ApiModelProperty("分类级别：0->1级；1->2级")
    @Excel(name = "分类级别：0->1级；1->2级")
    private Integer level;

    @ApiModelProperty("显示状态：0->不显示；1->显示")
    @Excel(name = "显示状态：0->不显示；1->显示")
    private Integer showStatus;

    @ApiModelProperty("SORT")
    @Excel(name = "SORT")
    private Integer sort;

    @ApiModelProperty("图标")
    @Excel(name = "图标")
    private String icon;

}
