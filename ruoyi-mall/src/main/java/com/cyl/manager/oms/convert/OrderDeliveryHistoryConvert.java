package com.cyl.manager.oms.convert;

import org.mapstruct.Mapper;
import com.cyl.manager.oms.domain.entity.OrderDeliveryHistory;
import com.cyl.manager.oms.domain.vo.OrderDeliveryHistoryVO;
import java.util.List;
/**
 * 订单发货记录  DO <=> DTO <=> VO / BO / Query
 *
 * @author zcc
 */
@Mapper(componentModel = "spring")
public interface OrderDeliveryHistoryConvert  {

    List<OrderDeliveryHistoryVO> dos2vos(List<OrderDeliveryHistory> list);
}
