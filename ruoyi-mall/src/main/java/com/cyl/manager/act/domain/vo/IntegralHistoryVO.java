package com.cyl.manager.act.domain.vo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.ruoyi.common.annotation.Excel;
import lombok.Data;
/**
 * 积分流水表 数据视图对象
 * 
 * @author zcc
 */
@Data
public class IntegralHistoryVO  {
   /** ID */
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
    private LocalDateTime createTime;
}
