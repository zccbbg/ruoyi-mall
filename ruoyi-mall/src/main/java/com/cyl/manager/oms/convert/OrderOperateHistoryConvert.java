package com.cyl.manager.oms.convert;

import org.mapstruct.Mapper;
import com.cyl.manager.oms.domain.entity.OrderOperateHistory;
import com.cyl.manager.oms.domain.vo.OrderOperateHistoryVO;
import java.util.List;
/**
 * 订单操作历史记录  DO <=> DTO <=> VO / BO / Query
 *
 * @author zcc
 */
@Mapper(componentModel = "spring")
public interface OrderOperateHistoryConvert  {

    List<OrderOperateHistoryVO> dos2vos(List<OrderOperateHistory> list);
}
