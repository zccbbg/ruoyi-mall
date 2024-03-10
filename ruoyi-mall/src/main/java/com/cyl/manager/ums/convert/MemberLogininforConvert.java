package com.cyl.manager.ums.convert;

import org.mapstruct.Mapper;
import com.cyl.manager.ums.domain.entity.MemberLogininfor;
import com.cyl.manager.ums.domain.vo.MemberLogininforVO;
import java.util.List;
/**
 * 会员登录记录  DO <=> DTO <=> VO / BO / Query
 *
 * @author zcc
 */
@Mapper(componentModel = "spring")
public interface MemberLogininforConvert  {

    List<MemberLogininforVO> dos2vos(List<MemberLogininfor> list);
}
