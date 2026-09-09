package com.cyl.manager.oms.domain.query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 订单售后 查询 对象
 *
 * @author zcc
 */
@Data
public class AftersaleQuery {
    /** MEMBER_ID 精确匹配 */
    private Long memberId;

    /** 订单id 精确匹配 */
    private Long orderId;

    /** 退款金额 精确匹配 */
    private BigDecimal returnAmount;

    /** 售后类型：1：退款，2：退货退款 精确匹配 */
    private Integer type;

    /** 申请状态：0->待处理；1->退货中；2->已完成；3->已拒绝 精确匹配 */
    private Integer status;

    /** 处理时间 精确匹配 */
    private LocalDateTime handleTime;

    /** 退货数量 精确匹配 */
    private Integer quantity;

    /** 原因 精确匹配 */
    private String reason;

    /** 描述 精确匹配 */
    private String description;

    /** 凭证图片，以逗号隔开 精确匹配 */
    private String proofPics;

    /** 处理备注 精确匹配 */
    private String handleNote;

    /** 处理人员 精确匹配 */
    private String handleMan;

}
