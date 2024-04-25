package com.cyl.manager.oms.domain.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@ApiModel("后台订单查询请求体")
public class ManagerOrderQueryForm {
    @ApiModelProperty(name = "orderId", value = "订单id", required = true, dataType = "Long")
    private Long orderId;

    @ApiModelProperty(name = "orderSn", value = "订单编号", required = true, dataType = "String")
    private String orderSn;


    @ApiModelProperty(name = "productId", value = "商品id", required = true, dataType = "Long")
    private Long productId;

    @ApiModelProperty(name = "productName", value = "商品名称", required = true, dataType = "String")
    private String productName;

    @ApiModelProperty(name = "userPhone", value = "用户名称（手机号）", required = true, dataType = "String")
    private String userPhone;

    @ApiModelProperty(name = "payType", value = "支付方式 0->未支付；1->支付宝；2->微信", required = true, dataType = "Integer")
    private Integer payType;

    @ApiModelProperty(name = "status", value = "订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单", required = true, dataType = "String")
    private Integer status;

    @ApiModelProperty("省份/直辖市 精确匹配")
    private String receiverProvince;

    @ApiModelProperty("城市 精确匹配")
    private String receiverCity;

    @ApiModelProperty("区 精确匹配")
    private String receiverDistrict;

    @ApiModelProperty(name = "startTime", value = "开始时间", required = true, dataType = "Date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    @ApiModelProperty(name = "endTime", value = "结束时间", required = true, dataType = "Date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;
}
