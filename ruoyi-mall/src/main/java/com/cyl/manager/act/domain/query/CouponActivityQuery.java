package com.cyl.manager.act.domain.query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 优惠券活动表 查询 对象
 *
 * @author zcc
 */
@Data
public class CouponActivityQuery {
    /** 活动名称 精确匹配 */
    private String title;

    /** 使用范围  1全场通用 2指定商品可用 3指定商品不可用 精确匹配 */
    private Integer useScope;


    /** 1免费兑换  2积分兑换 精确匹配 */
    private Integer couponType;

}
