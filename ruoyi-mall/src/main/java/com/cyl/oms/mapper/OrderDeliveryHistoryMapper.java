package com.cyl.oms.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import com.cyl.oms.domain.OrderDeliveryHistory;

/**
 * 订单发货记录Mapper接口
 * 
 * @author zcc
 */
public interface OrderDeliveryHistoryMapper extends BaseMapper<OrderDeliveryHistory> {
    /**
     * 查询订单发货记录列表
     *
     * @param orderDeliveryHistory 订单发货记录
     * @return 订单发货记录集合
     */
    List<OrderDeliveryHistory> selectByEntity(OrderDeliveryHistory orderDeliveryHistory);
}
