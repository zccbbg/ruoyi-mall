package com.cyl.manager.pms.domain.entity;

import com.ruoyi.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 品牌管理对象 pms_brand
 * 
 * @author zcc
 */
@ApiModel(description="品牌管理对象")
@Data
@TableName("pms_brand")
public class Brand extends BaseAudit {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("ID")
    private Long id;

    @ApiModelProperty("NAME")
    @Excel(name = "NAME")
    private String name;

    @ApiModelProperty("SORT")
    @Excel(name = "SORT")
    private Integer sort;

    @ApiModelProperty("SHOW_STATUS")
    @Excel(name = "SHOW_STATUS")
    private Integer showStatus;

    @ApiModelProperty("品牌logo")
    @Excel(name = "品牌logo")
    private String logo;

}
