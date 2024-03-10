package com.cyl.manager.oms.domain.query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 订单售后 查询 对象
 *
 * @author zcc
 */
@ApiModel(description="订单售后 查询 对象")
@Data
public class AftersaleQuery {
    @ApiModelProperty("MEMBER_ID 精确匹配")
    private Long memberId;

    @ApiModelProperty("订单id 精确匹配")
    private Long orderId;

    @ApiModelProperty("退款金额 精确匹配")
    private BigDecimal returnAmount;

    @ApiModelProperty("售后类型：1：退款，2：退货退款 精确匹配")
    private Integer type;

    @ApiModelProperty("申请状态：0->待处理；1->退货中；2->已完成；3->已拒绝 精确匹配")
    private Integer status;

    @ApiModelProperty("处理时间 精确匹配")
    private LocalDateTime handleTime;

    @ApiModelProperty("退货数量 精确匹配")
    private Integer quantity;

    @ApiModelProperty("原因 精确匹配")
    private String reason;

    @ApiModelProperty("描述 精确匹配")
    private String description;

    @ApiModelProperty("凭证图片，以逗号隔开 精确匹配")
    private String proofPics;

    @ApiModelProperty("处理备注 精确匹配")
    private String handleNote;

    @ApiModelProperty("处理人员 精确匹配")
    private String handleMan;

}
