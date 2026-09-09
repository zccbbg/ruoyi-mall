package com.cyl.manager.aws.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.ruoyi.common.annotation.Excel;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 系统数据统计对象 aws_system_statistics
 * 
 * @author zcc
 */
@Data
@TableName("aws_system_statistics")
public class SystemStatistics {
    private static final long serialVersionUID = 1L;

    private Long id;

    /** 统计日期 */
    @Excel(name = "统计日期", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime date;

    /** 登录用户数 */
    @Excel(name = "登录用户数")
    private Integer loginMemberCount;

    /** 注册用户数 */
    @Excel(name = "注册用户数")
    private Integer registerMemberCount;

    /** 加购用户数 */
    @Excel(name = "加购用户数")
    private Integer addCartMemberCount;

    /** 下单用户数 */
    @Excel(name = "下单用户数")
    private Integer createOrderMemberCount;

    /** 成交用户数 */
    @Excel(name = "成交用户数")
    private Integer dealMemberCount;

    /** 下单数 */
    @Excel(name = "下单数")
    private Integer orderCount;

    /** 成交数 */
    @Excel(name = "成交数")
    private Integer dealCount;

    /** 成交金额 */
    @Excel(name = "成交金额")
    private BigDecimal dealAmount;

    /** 售后数 */
    @Excel(name = "售后数")
    private Integer aftersaleCount;

    /** 售后金额 */
    @Excel(name = "售后金额")
    private BigDecimal aftersaleAmount;

}
