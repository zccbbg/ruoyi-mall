package com.cyl.manager.oms.convert;

import org.mapstruct.Mapper;
import com.cyl.manager.oms.domain.entity.OrderItem;
import com.cyl.manager.oms.domain.vo.OrderItemVO;
import java.util.List;
/**
 * 订单中所包含的商品  DO <=> DTO <=> VO / BO / Query
 *
 * @author zcc
 */
@Mapper(componentModel = "spring")
public interface OrderItemConvert  {

    List<OrderItemVO> dos2vos(List<OrderItem> list);
}
