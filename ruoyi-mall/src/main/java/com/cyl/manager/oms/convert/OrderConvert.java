package com.cyl.manager.oms.convert;

import org.mapstruct.Mapper;
import com.cyl.manager.oms.domain.entity.Order;
import com.cyl.manager.oms.domain.vo.OrderVO;
import java.util.List;
/**
 * 订单表  DO <=> DTO <=> VO / BO / Query
 *
 * @author zcc
 */
@Mapper(componentModel = "spring")
public interface OrderConvert  {

    List<OrderVO> dos2vos(List<Order> list);

    OrderVO do2vo(Order order);
}
