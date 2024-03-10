package com.cyl.manager.aws.domain.query;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 系统数据统计 查询 对象
 *
 * @author zcc
 */
@ApiModel(description="系统数据统计 查询 对象")
@Data
public class SystemStatisticsQuery {
    @ApiModelProperty("统计开始日期")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime beginTime;

    @ApiModelProperty("统计结束日期")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;

    @ApiModelProperty("登录用户数 精确匹配")
    private Integer loginMemberCount;

    @ApiModelProperty("注册用户数 精确匹配")
    private Integer registerMemberCount;

    @ApiModelProperty("加购用户数 精确匹配")
    private Integer addCartMemberCount;

    @ApiModelProperty("下单用户数 精确匹配")
    private Integer createOrderMemberCount;

    @ApiModelProperty("成交用户数 精确匹配")
    private Integer dealMemberCount;

    @ApiModelProperty("下单数 精确匹配")
    private Integer orderCount;

    @ApiModelProperty("成交数 精确匹配")
    private Integer dealCount;

    @ApiModelProperty("成交金额 精确匹配")
    private BigDecimal dealAmount;

    @ApiModelProperty("售后数 精确匹配")
    private Integer aftersaleCount;

    @ApiModelProperty("售后金额 精确匹配")
    private BigDecimal aftersaleAmount;

}
