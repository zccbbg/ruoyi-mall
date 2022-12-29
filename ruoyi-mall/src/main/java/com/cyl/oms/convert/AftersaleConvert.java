package com.cyl.oms.convert;

import org.mapstruct.Mapper;
import com.cyl.oms.domain.Aftersale;
import com.cyl.oms.pojo.vo.AftersaleVO;
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
