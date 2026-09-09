package com.cyl.manager.oms.domain.query;

import java.math.BigDecimal;
import lombok.Data;

/**
 * 订单售后 查询 对象
 *
 * @author zcc
 */
@Data
public class AftersaleItemQuery {
    /** MEMBER_ID 精确匹配 */
    private Long memberId;

    /** 订单id 精确匹配 */
    private Long orderId;

    /** 子订单id 精确匹配 */
    private Long orderItemId;

    /** 退款金额 精确匹配 */
    private BigDecimal returnAmount;

    /** 退货数量 精确匹配 */
    private Integer quantity;

}
