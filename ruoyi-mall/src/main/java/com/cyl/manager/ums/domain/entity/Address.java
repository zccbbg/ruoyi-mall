package com.cyl.manager.ums.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 地址对象
 * 
 */
@ApiModel(description="地址对象")
@Data
@TableName("address")
public class Address {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("ID")
    private Integer id;

    @ApiModelProperty("地区邮编")
    @Excel(name = "地区邮编")
    private Long code;

    @ApiModelProperty("父地区邮编")
    @Excel(name = "父地区邮编")
    private Long parentCode;

    @ApiModelProperty("地区名")
    @Excel(name = "地区名")
    private String name;

    @ApiModelProperty("地区层级")
    @Excel(name = "地区层级")
    private Integer level;

    @ApiModelProperty("CREATED_AT")
    @Excel(name = "CREATED_AT")
    private String createdAt;

    @ApiModelProperty("UPDATED_AT")
    @Excel(name = "UPDATED_AT")
    private String updatedAt;

    @ApiModelProperty("DELETED_AT")
    @Excel(name = "DELETED_AT")
    private String deletedAt;

}
