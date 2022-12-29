package com.cyl.oms.convert;

import org.mapstruct.Mapper;
import com.cyl.oms.domain.AftersaleItem;
import com.cyl.oms.pojo.vo.AftersaleItemVO;
import java.util.List;
/**
 * 订单售后  DO <=> DTO <=> VO / BO / Query
 *
 * @author zcc
 */
@Mapper(componentModel = "spring")
public interface AftersaleItemConvert  {

    List<AftersaleItemVO> dos2vos(List<AftersaleItem> list);
}
