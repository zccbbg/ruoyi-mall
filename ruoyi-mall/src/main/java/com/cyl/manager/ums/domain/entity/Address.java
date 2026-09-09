package com.cyl.manager.ums.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.annotation.Excel;
import lombok.Data;

/**
 * 地址对象
 * 
 */
@Data
@TableName("address")
public class Address {
    private static final long serialVersionUID = 1L;

    private Integer id;

    /** 地区邮编 */
    @Excel(name = "地区邮编")
    private Long code;

    /** 父地区邮编 */
    @Excel(name = "父地区邮编")
    private Long parentCode;

    /** 地区名 */
    @Excel(name = "地区名")
    private String name;

    /** 地区层级 */
    @Excel(name = "地区层级")
    private Integer level;

    /** CREATED_AT */
    @Excel(name = "CREATED_AT")
    private String createdAt;

    /** UPDATED_AT */
    @Excel(name = "UPDATED_AT")
    private String updatedAt;

    /** DELETED_AT */
    @Excel(name = "DELETED_AT")
    private String deletedAt;

}
