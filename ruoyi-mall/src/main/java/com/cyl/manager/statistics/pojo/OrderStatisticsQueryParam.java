package com.cyl.manager.statistics.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("订单统计查询条件对象")
@Data
public class OrderStatisticsQueryParam {

    @ApiModelProperty("查询范围类型 1：近一周 2：近一个月")
    private Integer type;

}
