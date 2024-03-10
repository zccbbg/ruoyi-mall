package com.cyl.manager.ums.domain.query;

import java.math.BigDecimal;

import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 会员账户表 查询 对象
 *
 * @author zcc
 */
@ApiModel(description="会员账户表 查询 对象")
@Data
public class MemberAccountQuery {
    @ApiModelProperty("积分余额 精确匹配")
    private BigDecimal integralBalance;

    @ApiModelProperty("历史总共积分 精确匹配")
    private BigDecimal totalIntegralBalance;

}
