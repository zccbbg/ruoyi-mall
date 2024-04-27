package com.cyl.manager.oms.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author: Jinxin
 * @date: 2022/4/22 14:12
 * @Description:
 */
@Getter
@Setter
@ApiModel("售后信息")
public class RefundInfoVO {
    @ApiModelProperty("退款单号")
    private Long id;
    @ApiModelProperty("申请退货方式：1-仅退款，2-退货退款")
    private Integer applyRefundType;
    @ApiModelProperty("申请售后时间")
    private LocalDateTime applyRefundTime;
    @ApiModelProperty("售后金额")
    private BigDecimal refundAmount;
    @ApiModelProperty("申请原因")
    private String reason;
    @ApiModelProperty("描述")
    private String description;
    @ApiModelProperty("凭证")
    private String proofPics;
    @ApiModelProperty("申请状态：0->待处理；1->退货中；2->已完成；3->已拒绝； 4->用户取消")
    private Integer refundStatus;
    @ApiModelProperty("平台拒绝理由")
    private String remark;
    @ApiModelProperty("物流单号")
    private String expressNo;
    @ApiModelProperty("物流名称")
    private String expressName;
    @ApiModelProperty("最新物流数据")
    private String logistics;
    @ApiModelProperty("所有物流信息 JSON格式")
    private String allLogistics;
    private String refundWpCode;

    @ApiModelProperty("退货快递号")
    private String refundWaybillCode;
}
