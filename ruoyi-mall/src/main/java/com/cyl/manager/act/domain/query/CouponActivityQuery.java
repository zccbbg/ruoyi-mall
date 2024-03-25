package com.cyl.manager.act.domain.query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 优惠券活动表 查询 对象
 *
 * @author zcc
 */
@ApiModel(description="优惠券活动表 查询 对象")
@Data
public class CouponActivityQuery {
    @ApiModelProperty("活动名称 精确匹配")
    private String title;

    @ApiModelProperty("使用范围  1全场通用 2指定商品可用 3指定商品不可用 精确匹配")
    private Integer useScope;


    @ApiModelProperty("1免费兑换  2积分兑换 精确匹配")
    private Integer couponType;

}
