package com.cyl.manager.act.convert;

import org.mapstruct.Mapper;
import com.cyl.manager.act.domain.entity.MemberCoupon;
import com.cyl.manager.act.domain.vo.MemberCouponVO;
import java.util.List;
/**
 * 用户领券记录  DO <=> DTO <=> VO / BO / Query
 *
 * @author zcc
 */
@Mapper(componentModel = "spring")
public interface MemberCouponConvert  {

    List<MemberCouponVO> dos2vos(List<MemberCoupon> list);
}
