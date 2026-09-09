package com.cyl.manager.oms.domain.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;


@Data
/** 商城订单请求体 */
public class ManagerAftersaleOrderForm {

  /** 售后单号 */
  private Long id;

  /** 订单号 */
  private String orderSn;

  /** 用户名称（手机号） */
  private String userPhone;

  /** 售后申请状态：0->待处理；1->退货中；2->已完成；3->已拒绝；4->用户取消 */
  private Integer status;

  /** 售后类型：1->退款；2->退货退款 */
  private Integer type;

  /** 开始时间 */
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime startTime;

  /** 结束时间 */
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime endTime;

}
