package com.cyl.manager.oms.domain.entity;

import com.ruoyi.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 订单发货记录对象 oms_order_delivery_history
 * 
 * @author zcc
 */
@ApiModel(description="订单发货记录对象")
@Data
@TableName("oms_order_delivery_history")
public class OrderDeliveryHistory extends BaseAudit {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("ID")
    private Long id;

    @ApiModelProperty("订单id")
    @Excel(name = "订单id")
    private Long orderId;

    @ApiModelProperty("物流公司(配送方式)")
    @Excel(name = "物流公司(配送方式)")
    private String deliveryCompany;

    @ApiModelProperty("物流单号")
    @Excel(name = "物流单号")
    private String deliverySn;

}
