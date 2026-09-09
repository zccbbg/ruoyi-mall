package com.cyl.manager.oms.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
/** 管理后台订单VO */
public class ManagerOrderVO {
    /** 订单id */
    private Long id;
    /** 订单编号 */
    private String orderSn;
    /** 商品id */
    private Long productId;

    /** 商品名称 */
    private String productName;

    /** 用户账号 */
    private String userPhone;

    /** 用户昵称 */
    private String nickName;

    /** 用户头像 */
    private String avatar;

    /** 订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单 */
    private Integer status;

    /** 退款状态，枚举值：1：无售后或售后关闭，2：售后处理中，3：退款中，4： 退款成功 */
    private Integer aftersaleStatus;

    /** 商品图片 */
    private String pic;

    /** 用户名称 */
    private String userName;

    /** 购买数量 */
    private Integer buyNum;

    /** 订单总金额 */
    private BigDecimal totalAmount;

    /** 应付金额 */
    private BigDecimal payAmount;

    /** 下单时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /** 支付时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime payTime;

    /** 支付方式 支付方式：0->未支付；1->支付宝；2->微信 */
    private Integer payType;

    /** 确认收货时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime receiveTime;

    /** 备注 */
    private String note;

    /** 商家备注 */
    private String merchantNote;

    /** 商品sku属性 */
    private String spData;

    /** 订单商品数据 */
    private List<ManagerOrderProductVO> productList;

    /** 发货时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime deliveryTime;

    /** 物流单号 */
    private String deliverySn;

    private String receiverName;

    private String receiverPhone;

    private String receiverProvince;

    private String receiverCity;

    private String receiverDistrict;

    private String receiverDetailAddress;

    private String mark;

    private BigDecimal couponAmount;
}
