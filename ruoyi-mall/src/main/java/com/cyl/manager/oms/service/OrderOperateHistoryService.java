package com.cyl.manager.oms.service;

import java.util.List;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import com.cyl.manager.oms.mapper.OrderOperateHistoryMapper;
import com.cyl.manager.oms.domain.OrderOperateHistory;
import com.cyl.manager.oms.pojo.query.OrderOperateHistoryQuery;

/**
 * 订单操作历史记录Service业务层处理
 *
 *
 * @author zcc
 */
@Service
public class OrderOperateHistoryService extends ServiceImpl<OrderOperateHistoryMapper, OrderOperateHistory> {
    @Autowired
    private OrderOperateHistoryMapper orderOperateHistoryMapper;

    /**
     * 查询订单操作历史记录
     *
     * @param id 订单操作历史记录主键
     * @return 订单操作历史记录
     */
    public OrderOperateHistory selectById(Long id) {
        return orderOperateHistoryMapper.selectById(id);
    }

    /**
     * 查询订单操作历史记录列表
     *
     * @param query 查询条件
     * @param page 分页条件
     * @return 订单操作历史记录
     */
    public List<OrderOperateHistory> selectList(OrderOperateHistoryQuery query, Pageable page) {
        if (page != null) {
            PageHelper.startPage(page.getPageNumber() + 1, page.getPageSize());
        }
        QueryWrapper<OrderOperateHistory> qw = new QueryWrapper<>();
        Long orderId = query.getOrderId();
        if (orderId != null) {
            qw.eq("order_id", orderId);
        }
        String operateMan = query.getOperateMan();
        if (!StringUtils.isEmpty(operateMan)) {
            qw.eq("operate_man", operateMan);
        }
        Integer orderStatus = query.getOrderStatus();
        if (orderStatus != null) {
            qw.eq("order_status", orderStatus);
        }
        String note = query.getNote();
        if (!StringUtils.isEmpty(note)) {
            qw.eq("note", note);
        }
        return orderOperateHistoryMapper.selectList(qw);
    }

    /**
     * 新增订单操作历史记录
     *
     * @param orderOperateHistory 订单操作历史记录
     * @return 结果
     */
    public int insert(OrderOperateHistory orderOperateHistory) {
        orderOperateHistory.setCreateTime(LocalDateTime.now());
        return orderOperateHistoryMapper.insert(orderOperateHistory);
    }

    /**
     * 修改订单操作历史记录
     *
     * @param orderOperateHistory 订单操作历史记录
     * @return 结果
     */
    public int update(OrderOperateHistory orderOperateHistory) {
        return orderOperateHistoryMapper.updateById(orderOperateHistory);
    }

    /**
     * 删除订单操作历史记录信息
     *
     * @param id 订单操作历史记录主键
     * @return 结果
     */
    public int deleteById(Long id) {
        return orderOperateHistoryMapper.deleteById(id);
    }
}
