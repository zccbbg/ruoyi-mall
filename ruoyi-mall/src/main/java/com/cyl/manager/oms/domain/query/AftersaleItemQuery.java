package com.cyl.manager.oms.domain.query;

import java.math.BigDecimal;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 订单售后 查询 对象
 *
 * @author zcc
 */
@ApiModel(description="订单售后 查询 对象")
@Data
public class AftersaleItemQuery {
    @ApiModelProperty("MEMBER_ID 精确匹配")
    private Long memberId;

    @ApiModelProperty("订单id 精确匹配")
    private Long orderId;

    @ApiModelProperty("子订单id 精确匹配")
    private Long orderItemId;

    @ApiModelProperty("退款金额 精确匹配")
    private BigDecimal returnAmount;

    @ApiModelProperty("退货数量 精确匹配")
    private Integer quantity;

}
