package com.cyl.ums.convert;

import org.mapstruct.Mapper;
import com.cyl.ums.domain.Member;
import com.cyl.ums.pojo.vo.MemberVO;
import java.util.List;
/**
 * 会员信息  DO <=> DTO <=> VO / BO / Query
 *
 * @author zcc
 */
@Mapper(componentModel = "spring")
public interface MemberConvert  {

    List<MemberVO> dos2vos(List<Member> list);
}
