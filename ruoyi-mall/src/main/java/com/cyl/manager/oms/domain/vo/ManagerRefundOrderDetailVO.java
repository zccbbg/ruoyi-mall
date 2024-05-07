package com.cyl.manager.oms.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author: Jinxin
 * @date: 2022/4/22 14:12
 * @Description:
 */
@Data
@ApiModel("售后订单详情")
public class ManagerRefundOrderDetailVO {
    @ApiModelProperty("订单id")
    private Long orderId;
    @ApiModelProperty("订单号")
    private String orderSn;
    @ApiModelProperty("用户昵称")
    private String nickName;
    @ApiModelProperty("用户手机号")
    private String phone;
    @ApiModelProperty("下单时间")
    private LocalDateTime createTime;
    @ApiModelProperty("支付方式：0->未支付；1->支付宝；2->微信")
    private Integer payType;
    @ApiModelProperty("支付时间")
    private LocalDateTime payTime;
    @ApiModelProperty("订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单")
    private Integer status;
    @ApiModelProperty("订单收获地址信息")
    private OrderAddressVO addressInfo;
    @ApiModelProperty("订单商品信息")
    private List<ManagerOrderProductVO> productList;
    @ApiModelProperty("售后信息")
    private List<RefundInfoVO> refundInfoList;
    @ApiModelProperty("退货时间")
    private LocalDateTime deliveryTime;
    @ApiModelProperty("物流单号")
    private String expressNo;
    @ApiModelProperty("物流名称")
    private String expressName;
    @ApiModelProperty("订单总金额")
    private BigDecimal totalAmount;
    @ApiModelProperty("应付金额（实际支付金额）")
    private BigDecimal payAmount;

}
