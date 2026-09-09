package com.cyl.manager.oms.domain.vo;
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
/** 售后信息 */
public class RefundInfoVO {
    /** 退款单号 */
    private Long id;
    /** 申请退货方式：1-仅退款，2-退货退款 */
    private Integer applyRefundType;
    /** 申请售后时间 */
    private LocalDateTime applyRefundTime;
    /** 售后金额 */
    private BigDecimal refundAmount;
    /** 申请原因 */
    private String reason;
    /** 描述 */
    private String description;
    /** 凭证 */
    private String proofPics;
    /** 申请状态：0->待处理；1->退货中；2->已完成；3->已拒绝； 4->用户取消 */
    private Integer refundStatus;
    /** 平台拒绝理由 */
    private String remark;
    /** 物流单号 */
    private String expressNo;
    /** 物流名称 */
    private String expressName;
    /** 最新物流数据 */
    private String logistics;
    /** 所有物流信息 JSON格式 */
    private String allLogistics;
    private String refundWpCode;

    /** 退货快递号 */
    private String refundWaybillCode;
}
