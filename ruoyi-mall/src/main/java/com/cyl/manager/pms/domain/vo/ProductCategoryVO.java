package com.cyl.manager.pms.domain.vo;

import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;

import java.util.List;

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
    private Long parentId;
   /** NAME */
    private String name;
   /** 分类级别：0->1级；1->2级 */
    private Integer level;
   /** 显示状态：0->不显示；1->显示 */
    private Integer showStatus;
   /** SORT */
    private Integer sort;
   /** 图标 */
    private String icon;
    private List<ProductCategoryVO> children;
}
