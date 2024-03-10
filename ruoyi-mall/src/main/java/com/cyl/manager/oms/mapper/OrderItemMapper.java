package com.cyl.manager.oms.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cyl.manager.oms.domain.entity.OrderItem;

/**
 * 订单中所包含的商品Mapper接口
 * 
 * @author zcc
 */
public interface OrderItemMapper extends BaseMapper<OrderItem> {
    /**
     * 查询订单中所包含的商品列表
     *
     * @param orderItem 订单中所包含的商品
     * @return 订单中所包含的商品集合
     */
    List<OrderItem> selectByEntity(OrderItem orderItem);
}
