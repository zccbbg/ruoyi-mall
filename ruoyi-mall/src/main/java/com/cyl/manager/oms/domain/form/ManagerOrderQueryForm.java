package com.cyl.manager.oms.domain.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
/** 后台订单查询请求体 */
public class ManagerOrderQueryForm {
    /** 订单id */
    private Long orderId;

    /** 订单编号 */
    private String orderSn;


    /** 商品id */
    private Long productId;

    /** 商品名称 */
    private String productName;

    /** 用户名称（手机号） */
    private String userPhone;

    /** 支付方式 0->未支付；1->支付宝；2->微信 */
    private Integer payType;

    /** 订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单 */
    private Integer status;

    /** 省份/直辖市 精确匹配 */
    private String receiverProvince;

    /** 城市 精确匹配 */
    private String receiverCity;

    /** 区 精确匹配 */
    private String receiverDistrict;

    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;
}
