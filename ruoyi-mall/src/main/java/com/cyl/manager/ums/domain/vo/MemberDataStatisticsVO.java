package com.cyl.manager.ums.domain.vo;
import lombok.Data;

import java.math.BigDecimal;

@Data
/** 会员下单数据统计对象 */
public class MemberDataStatisticsVO {
    /** 购物车数 */
    private Integer cartCount;
    /** 订单数 */
    private Integer orderCount;
    /** 下单金额 */
    private BigDecimal orderAmount;
    /** 售后数 */
    private Integer aftersaleCount;
}
