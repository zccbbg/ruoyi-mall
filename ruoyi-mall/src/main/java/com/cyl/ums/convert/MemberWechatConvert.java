package com.cyl.ums.convert;

import org.mapstruct.Mapper;
import com.cyl.ums.domain.MemberWechat;
import com.cyl.ums.pojo.vo.MemberWechatVO;
import java.util.List;
/**
 * 用户微信信息  DO <=> DTO <=> VO / BO / Query
 *
 * @author zcc
 */
@Mapper(componentModel = "spring")
public interface MemberWechatConvert  {

    List<MemberWechatVO> dos2vos(List<MemberWechat> list);
}
