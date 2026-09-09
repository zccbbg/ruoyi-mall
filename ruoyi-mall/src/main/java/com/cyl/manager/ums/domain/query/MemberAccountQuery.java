package com.cyl.manager.ums.domain.query;

import java.math.BigDecimal;

import lombok.Data;

/**
 * 会员账户表 查询 对象
 *
 * @author zcc
 */
@Data
public class MemberAccountQuery {
    /** 积分余额 精确匹配 */
    private BigDecimal integralBalance;

    /** 历史总共积分 精确匹配 */
    private BigDecimal totalIntegralBalance;

}
