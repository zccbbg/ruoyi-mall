package com.cyl.manager.oms.domain.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;


@Data
@ApiModel(value = "商城订单请求体")
public class ManagerAftersaleOrderForm {

  @ApiModelProperty(name = "id", value = "售后单号", required = true, dataType = "Long")
  private Long id;

  @ApiModelProperty(name = "orderSn", value = "订单号", required = true, dataType = "String")
  private String orderSn;

  @ApiModelProperty(name = "userPhone", value = "用户名称（手机号）", required = true, dataType = "String")
  private String userPhone;

  @ApiModelProperty(name = "status", value = "售后申请状态：0->待处理；1->退货中；2->已完成；3->已拒绝；4->用户取消", required = true, dataType = "String")
  private Integer status;

  @ApiModelProperty(name = "type", value = "售后类型：1->退款；2->退货退款", required = true, dataType = "String")
  private Integer type;

  @ApiModelProperty(name = "startTime", value = "开始时间", required = true, dataType = "Date")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime startTime;

  @ApiModelProperty(name = "endTime", value = "结束时间", required = true, dataType = "Date")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime endTime;

}
