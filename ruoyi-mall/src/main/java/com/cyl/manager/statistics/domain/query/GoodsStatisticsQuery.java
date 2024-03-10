package com.cyl.manager.statistics.domain.query;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 商品统计
 *
 * @author zhangcheng
 * @since 2023/05/15 13:53
 */
@Data
public class GoodsStatisticsQuery {
    @NotNull(message = "统计类型 1：商品规格排行 2：商品排行")
    private Integer statType;
    @NotNull(message = "参数size不能为空")
    private Integer size;
    @NotBlank(message = "参数startDate不能为空")
    private String startDate;
    @NotBlank(message = "参数endDate不能为空")
    private String endDate;
}
