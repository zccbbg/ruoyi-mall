package com.cyl.pms.pojo.vo;

import com.ruoyi.common.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;
/**
 * 商品分类 数据视图对象
 * 
 * @author zcc
 */
@Data
public class ProductCategoryVO extends BaseAudit {
   /** ID */
    private Long id;
   /** 上级分类的编号：0表示一级分类 */
    @Excel(name = "上级分类的编号：0表示一级分类")
    private Long parentId;
   /** NAME */
    @Excel(name = "NAME")
    private String name;
   /** 分类级别：0->1级；1->2级 */
    @Excel(name = "分类级别：0->1级；1->2级")
    private Integer level;
   /** 显示状态：0->不显示；1->显示 */
    @Excel(name = "显示状态：0->不显示；1->显示")
    private Integer showStatus;
   /** SORT */
    @Excel(name = "SORT")
    private Integer sort;
   /** 图标 */
    @Excel(name = "图标")
    private String icon;
}
