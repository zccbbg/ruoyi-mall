package com.cyl.manager.pms.domain.query;

import lombok.Data;

/**
 * 品牌管理 查询 对象
 *
 * @author zcc
 */
@Data
public class BrandQuery {
    /** NAME 精确匹配 */
    private String nameLike;

    /** SORT 精确匹配 */
    private Integer sort;

    /** SHOW_STATUS 精确匹配 */
    private Integer showStatus;

    /** 品牌logo 精确匹配 */
    private String logo;

}
