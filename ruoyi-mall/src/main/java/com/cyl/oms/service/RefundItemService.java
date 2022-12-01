package com.cyl.oms.service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import com.cyl.oms.mapper.RefundItemMapper;
import com.cyl.oms.domain.RefundItem;
import com.cyl.oms.pojo.query.RefundItemQuery;

/**
 * 订单售后Service业务层处理
 *
 *
 * @author zcc
 */
@Service
public class RefundItemService {
    @Autowired
    private RefundItemMapper refundItemMapper;

    /**
     * 查询订单售后
     *
     * @param id 订单售后主键
     * @return 订单售后
     */
    public RefundItem selectById(Long id) {
        return refundItemMapper.selectById(id);
    }

    /**
     * 查询订单售后列表
     *
     * @param query 查询条件
     * @param page 分页条件
     * @return 订单售后
     */
    public List<RefundItem> selectList(RefundItemQuery query, Pageable page) {
        if (page != null) {
            PageHelper.startPage(page.getPageNumber() + 1, page.getPageSize());
        }
        QueryWrapper<RefundItem> qw = new QueryWrapper<>();
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
        return refundItemMapper.selectList(qw);
    }

    /**
     * 新增订单售后
     *
     * @param refundItem 订单售后
     * @return 结果
     */
    public int insert(RefundItem refundItem) {
        refundItem.setCreateTime(LocalDateTime.now());
        return refundItemMapper.insert(refundItem);
    }

    /**
     * 修改订单售后
     *
     * @param refundItem 订单售后
     * @return 结果
     */
    public int update(RefundItem refundItem) {
        return refundItemMapper.updateById(refundItem);
    }

    /**
     * 删除订单售后信息
     *
     * @param id 订单售后主键
     * @return 结果
     */
    public int deleteById(Long id) {
        return refundItemMapper.deleteById(id);
    }
}
