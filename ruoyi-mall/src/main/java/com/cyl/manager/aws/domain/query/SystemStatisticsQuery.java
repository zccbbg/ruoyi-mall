package com.cyl.manager.aws.domain.query;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 系统数据统计 查询 对象
 *
 * @author zcc
 */
@Data
public class SystemStatisticsQuery {
    /** 统计开始日期 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime beginTime;

    /** 统计结束日期 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;

    /** 登录用户数 精确匹配 */
    private Integer loginMemberCount;

    /** 注册用户数 精确匹配 */
    private Integer registerMemberCount;

    /** 加购用户数 精确匹配 */
    private Integer addCartMemberCount;

    /** 下单用户数 精确匹配 */
    private Integer createOrderMemberCount;

    /** 成交用户数 精确匹配 */
    private Integer dealMemberCount;

    /** 下单数 精确匹配 */
    private Integer orderCount;

    /** 成交数 精确匹配 */
    private Integer dealCount;

    /** 成交金额 精确匹配 */
    private BigDecimal dealAmount;

    /** 售后数 精确匹配 */
    private Integer aftersaleCount;

    /** 售后金额 精确匹配 */
    private BigDecimal aftersaleAmount;

}
