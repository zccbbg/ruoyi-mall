package com.cyl.manager.ums.convert;

import org.mapstruct.Mapper;
import com.cyl.manager.ums.domain.MemberAddress;
import com.cyl.manager.ums.pojo.vo.MemberAddressVO;
import java.util.List;
/**
 * 会员收货地址  DO <=> DTO <=> VO / BO / Query
 *
 * @author zcc
 */
@Mapper(componentModel = "spring")
public interface MemberAddressConvert  {

    List<MemberAddressVO> dos2vos(List<MemberAddress> list);
}
