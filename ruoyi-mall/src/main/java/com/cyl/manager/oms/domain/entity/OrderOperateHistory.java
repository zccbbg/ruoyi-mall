package com.cyl.manager.oms.domain.entity;

import com.ruoyi.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 订单操作历史记录对象 oms_order_operate_history
 * 
 * @author zcc
 */
@ApiModel(description="订单操作历史记录对象")
@Data
@TableName("oms_order_operate_history")
public class OrderOperateHistory extends BaseAudit {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("ID")
    private Long id;

    @ApiModelProperty("订单id")
    @Excel(name = "订单id")
    private Long orderId;

    @ApiModelProperty("订单号")
    @Excel(name = "订单号")
    private String orderSn;

    @ApiModelProperty("操作人：用户；系统；后台管理员")
    @Excel(name = "操作人：用户；系统；后台管理员")
    private String operateMan;

    @ApiModelProperty("订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单")
    @Excel(name = "订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单")
    private Integer orderStatus;

    @ApiModelProperty("备注")
    @Excel(name = "备注")
    private String note;

}
