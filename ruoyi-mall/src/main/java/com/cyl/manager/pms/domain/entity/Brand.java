package com.cyl.manager.pms.domain.entity;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 品牌管理对象 pms_brand
 * 
 * @author zcc
 */
@Data
@TableName("pms_brand")
public class Brand extends BaseAudit {
    private static final long serialVersionUID = 1L;

    private Long id;

    /** NAME */
    @Excel(name = "NAME")
    private String name;

    /** SORT */
    @Excel(name = "SORT")
    private Integer sort;

    /** SHOW_STATUS */
    @Excel(name = "SHOW_STATUS")
    private Integer showStatus;

    /** 品牌logo */
    @Excel(name = "品牌logo")
    private String logo;

}
