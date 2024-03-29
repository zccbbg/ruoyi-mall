package com.cyl.manager.act.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cyl.manager.act.domain.entity.CouponActivity;
import org.apache.ibatis.annotations.Param;

/**
 * 优惠券活动表Mapper接口
 *
 * @author zcc
 */
public interface CouponActivityMapper extends BaseMapper<CouponActivity> {
    /**
     * 查询优惠券活动表列表
     *
     * @param couponActivity 优惠券活动表
     * @return 优惠券活动表集合
     */
    List<CouponActivity> selectByEntity(CouponActivity couponActivity);

    void receiveCoupon(@Param("id") Long id);
}
