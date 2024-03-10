package com.cyl.manager.aws.domain.vo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.ruoyi.common.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
/**
 * 系统数据统计 数据视图对象
 * 
 * @author zcc
 */
@Data
public class SystemStatisticsVO  {
   /** ID */
    private Long id;
   /** 统计日期 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
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
