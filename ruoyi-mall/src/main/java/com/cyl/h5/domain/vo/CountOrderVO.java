package com.cyl.h5.domain.vo;
import lombok.Data;

@Data
/** 统计订单数量VO */
public class CountOrderVO {

    /** 待付款订单数量 */
    private Integer unpaid;

    /** 待发货订单数量 */
    private Integer nosend;

    /** 待收货订单数量 */
    private Integer noget;

    /** 售后订单数量 */
    private Integer aftersale;
}
