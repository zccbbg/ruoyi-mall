package com.cyl.oms.convert;

import org.mapstruct.Mapper;
import com.cyl.oms.domain.OrderItem;
import com.cyl.oms.pojo.vo.OrderItemVO;
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
