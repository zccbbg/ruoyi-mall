package com.cyl.h5.domain.vo;

import com.cyl.manager.act.domain.entity.MemberCoupon;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
/** 下单前校验返回数据 */
public class OrderCalcVO {
    /** sku数据 */
    private List<SkuViewVO> skuList;
    /** 商品总金额 */
    private BigDecimal productTotalAmount;
    /** 订单总金额 */
    private BigDecimal orderTotalAmount;
    private List<MemberCoupon> couponList;
}
