package com.cyl.manager.oms.service;

import java.util.List;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import com.cyl.manager.oms.mapper.OrderDeliveryHistoryMapper;
import com.cyl.manager.oms.domain.entity.OrderDeliveryHistory;
import com.cyl.manager.oms.domain.query.OrderDeliveryHistoryQuery;

/**
 * 订单发货记录Service业务层处理
 *
 *
 * @author zcc
 */
@Service
public class OrderDeliveryHistoryService {
    @Autowired
    private OrderDeliveryHistoryMapper orderDeliveryHistoryMapper;

    /**
     * 查询订单发货记录
     *
     * @param id 订单发货记录主键
     * @return 订单发货记录
     */
    public OrderDeliveryHistory selectById(Long id) {
        return orderDeliveryHistoryMapper.selectById(id);
    }

    /**
     * 查询订单发货记录列表
     *
     * @param query 查询条件
     * @param page 分页条件
     * @return 订单发货记录
     */
    public List<OrderDeliveryHistory> selectList(OrderDeliveryHistoryQuery query, Pageable page) {
        if (page != null) {
            PageHelper.startPage(page.getPageNumber() + 1, page.getPageSize());
        }
        QueryWrapper<OrderDeliveryHistory> qw = new QueryWrapper<>();
        Long orderId = query.getOrderId();
        if (orderId != null) {
            qw.eq("order_id", orderId);
        }
        String deliveryCompany = query.getDeliveryCompany();
        if (!StringUtils.isEmpty(deliveryCompany)) {
            qw.eq("delivery_company", deliveryCompany);
        }
        String deliverySn = query.getDeliverySn();
        if (!StringUtils.isEmpty(deliverySn)) {
            qw.eq("delivery_sn", deliverySn);
        }
        return orderDeliveryHistoryMapper.selectList(qw);
    }

    /**
     * 新增订单发货记录
     *
     * @param orderDeliveryHistory 订单发货记录
     * @return 结果
     */
    public int insert(OrderDeliveryHistory orderDeliveryHistory) {
        orderDeliveryHistory.setCreateTime(LocalDateTime.now());
        return orderDeliveryHistoryMapper.insert(orderDeliveryHistory);
    }

    /**
     * 修改订单发货记录
     *
     * @param orderDeliveryHistory 订单发货记录
     * @return 结果
     */
    public int update(OrderDeliveryHistory orderDeliveryHistory) {
        return orderDeliveryHistoryMapper.updateById(orderDeliveryHistory);
    }

    /**
     * 删除订单发货记录信息
     *
     * @param id 订单发货记录主键
     * @return 结果
     */
    public int deleteById(Long id) {
        return orderDeliveryHistoryMapper.deleteById(id);
    }
}
