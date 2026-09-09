package com.cyl.manager.act.domain.query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.cyl.manager.pms.domain.entity.Product;
import lombok.Data;

/**
 * 用户领券记录 查询 对象
 *
 * @author zcc
 */
@Data
public class MemberCouponQuery {
    /** 活动id 精确匹配 */
    private Long couponActivityId;

    /** 用户id 精确匹配 */
    private Long memberId;

    /** 0未使用  1已使用 精确匹配 */
    private Integer useStatus;

    //1已领取 2已使用 3已过期
    private Integer type;

    private List<Product> products;


}
