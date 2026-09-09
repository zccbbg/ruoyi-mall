package com.cyl.manager.statistics.domain.query;
import lombok.Data;

/** 订单统计查询条件对象 */
@Data
public class OrderStatisticsQuery {

    /** 查询范围类型 1：近一周 2：近一个月 */
    private Integer type;

}
