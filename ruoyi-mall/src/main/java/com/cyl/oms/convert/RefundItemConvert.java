package com.cyl.oms.convert;

import org.mapstruct.Mapper;
import com.cyl.oms.domain.RefundItem;
import com.cyl.oms.pojo.vo.RefundItemVO;
import java.util.List;
/**
 * 订单售后  DO <=> DTO <=> VO / BO / Query
 *
 * @author zcc
 */
@Mapper(componentModel = "spring")
public interface RefundItemConvert  {

    List<RefundItemVO> dos2vos(List<RefundItem> list);
}
