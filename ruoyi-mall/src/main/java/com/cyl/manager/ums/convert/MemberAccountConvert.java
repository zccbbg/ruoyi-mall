package com.cyl.manager.ums.convert;

import org.mapstruct.Mapper;
import com.cyl.manager.ums.domain.entity.MemberAccount;
import com.cyl.manager.ums.domain.vo.MemberAccountVO;
import java.util.List;
/**
 * 会员账户表  DO <=> DTO <=> VO / BO / Query
 *
 * @author zcc
 */
@Mapper(componentModel = "spring")
public interface MemberAccountConvert  {

    List<MemberAccountVO> dos2vos(List<MemberAccount> list);
}
