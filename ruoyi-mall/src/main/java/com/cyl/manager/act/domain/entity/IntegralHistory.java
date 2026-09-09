package com.cyl.manager.act.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 积分流水表对象 act_integral_history
 * 
 * @author zcc
 */
@Data
@TableName("act_integral_history")
public class IntegralHistory {
    private static final long serialVersionUID = 1L;

    private Long id;

    /** MEMBER_ID */
    @Excel(name = "MEMBER_ID")
    private Long memberId;

    /** 变动金额 */
    @Excel(name = "变动金额")
    private BigDecimal amount;

    /** 类型 1：收入 2：支出  3：其他 */
    @Excel(name = "类型 1：收入 2：支出  3：其他")
    private Integer opType;

    /** 子类型：11签到  12消费获得  21退款扣除积分 */
    @Excel(name = "子类型：11签到  12消费获得  21退款扣除积分")
    private Integer subOpType;

    /** 订单金额 */
    @Excel(name = "订单金额")
    private BigDecimal orderAmount;

    /** 订单id */
    @Excel(name = "订单id")
    private Long orderId;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

}
