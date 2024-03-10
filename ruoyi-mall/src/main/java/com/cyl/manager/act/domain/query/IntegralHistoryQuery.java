package com.cyl.manager.act.domain.query;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 积分流水表 查询 对象
 *
 * @author zcc
 */
@ApiModel(description="积分流水表 查询 对象")
@Data
public class IntegralHistoryQuery {
    @ApiModelProperty("MEMBER_ID 精确匹配")
    private Long memberId;

    @ApiModelProperty("变动金额 精确匹配")
    private BigDecimal amount;

    @ApiModelProperty("类型 1：收入 2：支出  3：其他 精确匹配")
    private Integer opType;

    @ApiModelProperty("子类型：11签到  12消费获得  21退款扣除积分 精确匹配")
    private Integer subOpType;

    @ApiModelProperty("订单金额 精确匹配")
    private BigDecimal orderAmount;

    @ApiModelProperty("订单id 精确匹配")
    private Long orderId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime start;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime end;

}
