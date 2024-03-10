package com.cyl.manager.oms.domain.entity;

import java.math.BigDecimal;
import com.ruoyi.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 订单售后对象 oms_aftersale_item
 * 
 * @author zcc
 */
@ApiModel(description="订单售后对象")
@Data
@TableName("oms_aftersale_item")
public class AftersaleItem extends BaseAudit {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("ID")
    private Long id;

    @ApiModelProperty("MEMBER_ID")
    @Excel(name = "MEMBER_ID")
    private Long memberId;

    @ApiModelProperty("售后单id")
    @Excel(name = "售后单id")
    private Long aftersaleId;

    @ApiModelProperty("订单id")
    @Excel(name = "订单id")
    private Long orderId;

    @ApiModelProperty("子订单id")
    @Excel(name = "子订单id")
    private Long orderItemId;

    @ApiModelProperty("退款金额")
    @Excel(name = "退款金额")
    private BigDecimal returnAmount;

    @ApiModelProperty("退货数量")
    @Excel(name = "退货数量")
    private Integer quantity;

}
