package com.cyl.manager.oms.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import com.cyl.manager.oms.mapper.AftersaleMapper;
import com.cyl.manager.oms.domain.Aftersale;
import com.cyl.manager.oms.pojo.query.AftersaleQuery;

/**
 * 订单售后Service业务层处理
 *
 *
 * @author zcc
 */
@Service
public class AftersaleService {
    @Autowired
    private AftersaleMapper aftersaleMapper;

    /**
     * 查询订单售后
     *
     * @param id 订单售后主键
     * @return 订单售后
     */
    public Aftersale selectById(Long id) {
        return aftersaleMapper.selectById(id);
    }

    /**
     * 查询订单售后列表
     *
     * @param query 查询条件
     * @param page 分页条件
     * @return 订单售后
     */
    public List<Aftersale> selectList(AftersaleQuery query, Pageable page) {
        if (page != null) {
            PageHelper.startPage(page.getPageNumber() + 1, page.getPageSize());
        }
        QueryWrapper<Aftersale> qw = new QueryWrapper<>();
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
        return aftersaleMapper.selectList(qw);
    }

    /**
     * 新增订单售后
     *
     * @param aftersale 订单售后
     * @return 结果
     */
    public int insert(Aftersale aftersale) {
        aftersale.setCreateTime(LocalDateTime.now());
        return aftersaleMapper.insert(aftersale);
    }

    /**
     * 修改订单售后
     *
     * @param aftersale 订单售后
     * @return 结果
     */
    public int update(Aftersale aftersale) {
        return aftersaleMapper.updateById(aftersale);
    }

    /**
     * 删除订单售后信息
     *
     * @param id 订单售后主键
     * @return 结果
     */
    public int deleteById(Long id) {
        return aftersaleMapper.deleteById(id);
    }
}
