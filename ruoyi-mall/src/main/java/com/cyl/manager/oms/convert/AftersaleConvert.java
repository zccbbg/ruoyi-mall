package com.cyl.manager.oms.convert;

import org.mapstruct.Mapper;
import com.cyl.manager.oms.domain.entity.Aftersale;
import com.cyl.manager.oms.domain.vo.AftersaleVO;
import java.util.List;
/**
 * 订单售后  DO <=> DTO <=> VO / BO / Query
 *
 * @author zcc
 */
@Mapper(componentModel = "spring")
public interface AftersaleConvert  {

    List<AftersaleVO> dos2vos(List<Aftersale> list);
}
