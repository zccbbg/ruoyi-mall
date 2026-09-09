package com.cyl.manager.oms.domain.vo;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
/** 后台订单详情对象 */
public class ManagerOrderDetailVO {
    /** 订单id */
    private Long orderId;
    /** 订单编号 */
    private String orderSn;
    /** 用户昵称 */
    private String userName;
    /** 用户手机号 */
    private String userPhone;
    /** 下单时间 */
    private LocalDateTime createTime;
    /** 支付方式：0->未支付；1->支付宝；2->微信 */
    private Integer payType;
    /** 支付时间 */
    private LocalDateTime payTime;
    /** 订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单 */
    private Integer orderStatus;
    /** 订单收获地址信息 */
    private ManagerOrderAddressVo addressInfo;
    /** 订单商品信息 */
    private List<ManagerOrderProductVO> productInfo;
    /** 发货时间 */
    private LocalDateTime deliveryTime;
    /** 收货时间 */
    private LocalDateTime receiveTime;
    /** 物流单号 */
    private String expressNo;
    /** 物流名称 */
    private String expressName;
    /** 支付金额 */
    private BigDecimal payAmount;
    /** 订单金额 */
    private BigDecimal totalAmount;
    private BigDecimal couponAmount;
}
