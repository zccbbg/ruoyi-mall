package com.cyl.oms.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import com.cyl.oms.mapper.RefundMapper;
import com.cyl.oms.domain.Refund;
import com.cyl.oms.pojo.query.RefundQuery;

/**
 * 订单售后Service业务层处理
 *
 *
 * @author zcc
 */
@Service
public class RefundService {
    @Autowired
    private RefundMapper refundMapper;

    /**
     * 查询订单售后
     *
     * @param id 订单售后主键
     * @return 订单售后
     */
    public Refund selectById(Long id) {
        return refundMapper.selectById(id);
    }

    /**
     * 查询订单售后列表
     *
     * @param query 查询条件
     * @param page 分页条件
     * @return 订单售后
     */
    public List<Refund> selectList(RefundQuery query, Pageable page) {
        if (page != null) {
            PageHelper.startPage(page.getPageNumber() + 1, page.getPageSize());
        }
        QueryWrapper<Refund> qw = new QueryWrapper<>();
        Long memberId = query.getMemberId();
        if (memberId != null) {
            qw.eq("member_id", memberId);
        }
        Long orderId = query.getOrderId();
        if (orderId != null) {
            qw.eq("order_id", orderId);
        }
        BigDecimal returnAmount = query.getReturnAmount();
        if (returnAmount != null) {
            qw.eq("return_amount", returnAmount);
        }
        Integer type = query.getType();
        if (type != null) {
            qw.eq("type", type);
        }
        Integer status = query.getStatus();
        if (status != null) {
            qw.eq("status", status);
        }
        LocalDateTime handleTime = query.getHandleTime();
        if (handleTime != null) {
            qw.eq("handle_time", handleTime);
        }
        Integer quantity = query.getQuantity();
        if (quantity != null) {
            qw.eq("quantity", quantity);
        }
        String reason = query.getReason();
        if (!StringUtils.isEmpty(reason)) {
            qw.eq("reason", reason);
        }
        String description = query.getDescription();
        if (!StringUtils.isEmpty(description)) {
            qw.eq("description", description);
        }
        String proofPics = query.getProofPics();
        if (!StringUtils.isEmpty(proofPics)) {
            qw.eq("proof_pics", proofPics);
        }
        String handleNote = query.getHandleNote();
        if (!StringUtils.isEmpty(handleNote)) {
            qw.eq("handle_note", handleNote);
        }
        String handleMan = query.getHandleMan();
        if (!StringUtils.isEmpty(handleMan)) {
            qw.eq("handle_man", handleMan);
        }
        return refundMapper.selectList(qw);
    }

    /**
     * 新增订单售后
     *
     * @param refund 订单售后
     * @return 结果
     */
    public int insert(Refund refund) {
        refund.setCreateTime(LocalDateTime.now());
        return refundMapper.insert(refund);
    }

    /**
     * 修改订单售后
     *
     * @param refund 订单售后
     * @return 结果
     */
    public int update(Refund refund) {
        return refundMapper.updateById(refund);
    }

    /**
     * 删除订单售后信息
     *
     * @param id 订单售后主键
     * @return 结果
     */
    public int deleteById(Long id) {
        return refundMapper.deleteById(id);
    }
}
