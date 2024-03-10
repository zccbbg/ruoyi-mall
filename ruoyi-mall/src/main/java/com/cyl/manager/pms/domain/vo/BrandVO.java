package com.cyl.manager.pms.domain.vo;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;
/**
 * 品牌管理 数据视图对象
 * 
 * @author zcc
 */
@Data
public class BrandVO extends BaseAudit {
   /** ID */
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
