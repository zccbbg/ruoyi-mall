package com.cyl.manager.oms.domain.query;

import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 订单操作历史记录 查询 对象
 *
 * @author zcc
 */
@ApiModel(description="订单操作历史记录 查询 对象")
@Data
public class OrderOperateHistoryQuery {
    @ApiModelProperty("订单号 精确匹配")
    private String orderSn;

    @ApiModelProperty("操作人：用户；系统；后台管理员 精确匹配")
    private String operateMan;

    @ApiModelProperty("订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单 精确匹配")
    private Integer orderStatus;

    @ApiModelProperty("备注 精确匹配")
    private String note;

}
