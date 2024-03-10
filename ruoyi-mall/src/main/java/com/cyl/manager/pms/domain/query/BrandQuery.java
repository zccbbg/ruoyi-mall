package com.cyl.manager.pms.domain.query;

import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 品牌管理 查询 对象
 *
 * @author zcc
 */
@ApiModel(description="品牌管理 查询 对象")
@Data
public class BrandQuery {
    @ApiModelProperty("NAME 精确匹配")
    private String nameLike;

    @ApiModelProperty("SORT 精确匹配")
    private Integer sort;

    @ApiModelProperty("SHOW_STATUS 精确匹配")
    private Integer showStatus;

    @ApiModelProperty("品牌logo 精确匹配")
    private String logo;

}
