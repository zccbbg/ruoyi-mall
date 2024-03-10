package com.cyl.manager.aws.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.ruoyi.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 系统数据统计对象 aws_system_statistics
 * 
 * @author zcc
 */
@ApiModel(description="系统数据统计对象")
@Data
@TableName("aws_system_statistics")
public class SystemStatistics {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("ID")
    private Long id;

    @ApiModelProperty("统计日期")
    @Excel(name = "统计日期", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime date;

    @ApiModelProperty("登录用户数")
    @Excel(name = "登录用户数")
    private Integer loginMemberCount;

    @ApiModelProperty("注册用户数")
    @Excel(name = "注册用户数")
    private Integer registerMemberCount;

    @ApiModelProperty("加购用户数")
    @Excel(name = "加购用户数")
    private Integer addCartMemberCount;

    @ApiModelProperty("下单用户数")
    @Excel(name = "下单用户数")
    private Integer createOrderMemberCount;

    @ApiModelProperty("成交用户数")
    @Excel(name = "成交用户数")
    private Integer dealMemberCount;

    @ApiModelProperty("下单数")
    @Excel(name = "下单数")
    private Integer orderCount;

    @ApiModelProperty("成交数")
    @Excel(name = "成交数")
    private Integer dealCount;

    @ApiModelProperty("成交金额")
    @Excel(name = "成交金额")
    private BigDecimal dealAmount;

    @ApiModelProperty("售后数")
    @Excel(name = "售后数")
    private Integer aftersaleCount;

    @ApiModelProperty("售后金额")
    @Excel(name = "售后金额")
    private BigDecimal aftersaleAmount;

}
