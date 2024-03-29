package com.cyl.manager.act.mapper;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cyl.manager.act.domain.entity.MemberCoupon;
import com.cyl.manager.act.domain.vo.CouponActivityVO;
import org.apache.ibatis.annotations.Param;

/**
 * 用户领券记录Mapper接口
 * 
 * @author zcc
 */
public interface MemberCouponMapper extends BaseMapper<MemberCoupon> {
    /**
     * 查询用户领券记录列表
     *
     * @param memberCoupon 用户领券记录
     * @return 用户领券记录集合
     */
    List<MemberCoupon> selectByEntity(MemberCoupon memberCoupon);

    List<CouponActivityVO> countUseCoupon(@Param("couponIds") Collection<Long> couponIds);

    List<CouponActivityVO> countGetCoupon(@Param("couponIds") Set<Long> ids, @Param("memberId") Long memberId);
}
