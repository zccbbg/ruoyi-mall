package com.cyl.oms.convert;

import org.mapstruct.Mapper;
import com.cyl.oms.domain.Order;
import com.cyl.oms.pojo.vo.OrderVO;
import java.util.List;
/**
 * 订单表  DO <=> DTO <=> VO / BO / Query
 *
 * @author zcc
 */
@Mapper(componentModel = "spring")
public interface OrderConvert  {

    List<OrderVO> dos2vos(List<Order> list);
}
