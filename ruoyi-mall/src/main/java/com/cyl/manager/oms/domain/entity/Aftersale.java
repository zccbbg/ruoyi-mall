package com.cyl.manager.oms.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.ruoyi.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 订单售后对象 oms_aftersale
 * 
 * @author zcc
 */
@ApiModel(description="订单售后对象")
@Data
@TableName("oms_aftersale")
public class Aftersale extends BaseAudit {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("ID")
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty("MEMBER_ID")
    @Excel(name = "MEMBER_ID")
    private Long memberId;

    @ApiModelProperty("订单id")
    @Excel(name = "订单id")
    private Long orderId;

    @ApiModelProperty("退款金额")
    @Excel(name = "退款金额")
    private BigDecimal returnAmount;

    @ApiModelProperty("售后类型：1：退款，2：退货退款")
    @Excel(name = "售后类型：1：退款，2：退货退款")
    private Integer type;

    @ApiModelProperty("申请状态：0->待处理；1->退货中；2->已完成；3->已拒绝")
    @Excel(name = "申请状态：0->待处理；1->退货中；2->已完成；3->已拒绝")
    private Integer status;

    @ApiModelProperty("处理时间")
    @Excel(name = "处理时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime handleTime;

    @ApiModelProperty("退货数量")
    @Excel(name = "退货数量")
    private Integer quantity;

    @ApiModelProperty("原因")
    @Excel(name = "原因")
    private String reason;

    @ApiModelProperty("描述")
    @Excel(name = "描述")
    private String description;

    @ApiModelProperty("凭证图片，以逗号隔开")
    @Excel(name = "凭证图片，以逗号隔开")
    private String proofPics;

    @ApiModelProperty("处理备注")
    @Excel(name = "处理备注")
    private String handleNote;

    @ApiModelProperty("处理人员")
    @Excel(name = "处理人员")
    private String handleMan;

    @ApiModelProperty("退款快递公司")
    @Excel(name = "退款快递公司")
    private String refundWpCode;

    @ApiModelProperty("退货快递号")
    @Excel(name = "退货快递号")
    private String refundWaybillCode;

}
