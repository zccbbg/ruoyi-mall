package com.cyl.manager.oms.domain.vo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.ruoyi.common.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;
/**
 * 订单售后 数据视图对象
 * 
 * @author zcc
 */
@Data
public class AftersaleVO extends BaseAudit {
   /** ID */
    private Long id;
   /** MEMBER_ID */
    @Excel(name = "MEMBER_ID")
    private Long memberId;
   /** 订单id */
    @Excel(name = "订单id")
    private Long orderId;
   /** 退款金额 */
    @Excel(name = "退款金额")
    private BigDecimal returnAmount;
   /** 售后类型：1：退款，2：退货退款 */
    @Excel(name = "售后类型：1：退款，2：退货退款")
    private Integer type;
   /** 申请状态：0->待处理；1->退货中；2->已完成；3->已拒绝 */
    @Excel(name = "申请状态：0->待处理；1->退货中；2->已完成；3->已拒绝")
    private Integer status;
   /** 处理时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "处理时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime handleTime;
   /** 退货数量 */
    @Excel(name = "退货数量")
    private Integer quantity;
   /** 原因 */
    @Excel(name = "原因")
    private String reason;
   /** 描述 */
    @Excel(name = "描述")
    private String description;
   /** 凭证图片，以逗号隔开 */
    @Excel(name = "凭证图片，以逗号隔开")
    private String proofPics;
   /** 处理备注 */
    @Excel(name = "处理备注")
    private String handleNote;
   /** 处理人员 */
    @Excel(name = "处理人员")
    private String handleMan;
}
