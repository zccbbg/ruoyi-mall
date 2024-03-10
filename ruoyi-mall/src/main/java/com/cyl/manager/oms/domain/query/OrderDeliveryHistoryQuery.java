package com.cyl.manager.oms.domain.query;

import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 订单发货记录 查询 对象
 *
 * @author zcc
 */
@ApiModel(description="订单发货记录 查询 对象")
@Data
public class OrderDeliveryHistoryQuery {
    @ApiModelProperty("订单id 精确匹配")
    private Long orderId;

    @ApiModelProperty("物流公司 精确匹配")
    private String deliveryCompany;

    @ApiModelProperty("物流单号 精确匹配")
    private String deliverySn;

}
