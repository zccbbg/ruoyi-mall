package com.cyl.manager.act.convert;

import org.mapstruct.Mapper;
import com.cyl.manager.act.domain.entity.CouponActivity;
import com.cyl.manager.act.domain.vo.CouponActivityVO;
import java.util.List;
/**
 * 优惠券活动表  DO <=> DTO <=> VO / BO / Query
 *
 * @author zcc
 */
@Mapper(componentModel = "spring")
public interface CouponActivityConvert  {

    List<CouponActivityVO> dos2vos(List<CouponActivity> list);
}
