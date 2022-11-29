package com.cyl.ums.convert;

import org.mapstruct.Mapper;
import com.cyl.ums.domain.MemberCart;
import com.cyl.ums.pojo.vo.MemberCartVO;
import java.util.List;
/**
 * 购物车  DO <=> DTO <=> VO / BO / Query
 *
 * @author zcc
 */
@Mapper(componentModel = "spring")
public interface MemberCartConvert  {

    List<MemberCartVO> dos2vos(List<MemberCart> list);
}
