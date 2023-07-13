package com.cyl.h5.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author: Jinxin
 * @date: 2023/4/16 10:22
 * @Description:
 */
@Getter
@Setter
public class MicroMallOrderSyncMsg {
    @ApiModelProperty(
            name = "orderId",
            value = "订单id",
            required = true,
            dataType = "String"
    )
    private Long orderId;
    @ApiModelProperty(
            name = "type",
            value = "type 0延时关闭 1新下单  2退款",
            required = true,
            dataType = "Integer"
    )
    private Integer type;
}
