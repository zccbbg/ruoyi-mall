package com.cyl.manager.oms.domain.query;

import lombok.Data;

/**
 * 订单发货记录 查询 对象
 *
 * @author zcc
 */
@Data
public class OrderDeliveryHistoryQuery {
    /** 订单id 精确匹配 */
    private Long orderId;

    /** 物流公司 精确匹配 */
    private String deliveryCompany;

    /** 物流单号 精确匹配 */
    private String deliverySn;

}
