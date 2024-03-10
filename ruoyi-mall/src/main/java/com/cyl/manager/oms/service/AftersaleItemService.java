package com.cyl.manager.oms.service;

import java.math.BigDecimal;
import java.util.List;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.cyl.manager.oms.mapper.AftersaleItemMapper;
import com.cyl.manager.oms.domain.entity.AftersaleItem;
import com.cyl.manager.oms.domain.query.AftersaleItemQuery;

/**
 * 订单售后Service业务层处理
 *
 *
 * @author zcc
 */
@Service
public class AftersaleItemService {
    @Autowired
    private AftersaleItemMapper aftersaleItemMapper;

    /**
     * 查询订单售后
     *
     * @param id 订单售后主键
     * @return 订单售后
     */
    public AftersaleItem selectById(Long id) {
        return aftersaleItemMapper.selectById(id);
    }

    /**
     * 查询订单售后列表
     *
     * @param query 查询条件
     * @param page 分页条件
     * @return 订单售后
     */
    public List<AftersaleItem> selectList(AftersaleItemQuery query, Pageable page) {
        if (page != null) {
            PageHelper.startPage(page.getPageNumber() + 1, page.getPageSize());
        }
        QueryWrapper<AftersaleItem> qw = new QueryWrapper<>();
        Long memberId = query.getMemberId();
        if (memberId != null) {
            qw.eq("member_id", memberId);
        }
        Long orderId = query.getOrderId();
        if (orderId != null) {
            qw.eq("order_id", orderId);
        }
        Long orderItemId = query.getOrderItemId();
        if (orderItemId != null) {
            qw.eq("order_item_id", orderItemId);
        }
        BigDecimal returnAmount = query.getReturnAmount();
        if (returnAmount != null) {
            qw.eq("return_amount", returnAmount);
        }
        Integer quantity = query.getQuantity();
        if (quantity != null) {
            qw.eq("quantity", quantity);
        }
        return aftersaleItemMapper.selectList(qw);
    }

    /**
     * 新增订单售后
     *
     * @param aftersaleItem 订单售后
     * @return 结果
     */
    public int insert(AftersaleItem aftersaleItem) {
        aftersaleItem.setCreateTime(LocalDateTime.now());
        return aftersaleItemMapper.insert(aftersaleItem);
    }

    /**
     * 修改订单售后
     *
     * @param aftersaleItem 订单售后
     * @return 结果
     */
    public int update(AftersaleItem aftersaleItem) {
        return aftersaleItemMapper.updateById(aftersaleItem);
    }

    /**
     * 删除订单售后信息
     *
     * @param id 订单售后主键
     * @return 结果
     */
    public int deleteById(Long id) {
        return aftersaleItemMapper.deleteById(id);
    }
}
