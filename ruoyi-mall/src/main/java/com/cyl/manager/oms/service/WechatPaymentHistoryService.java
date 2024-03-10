package com.cyl.manager.oms.service;

import java.math.BigDecimal;
import java.util.List;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cyl.manager.oms.domain.entity.WechatPaymentHistory;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import com.cyl.manager.oms.mapper.WechatPaymentHistoryMapper;
import com.cyl.manager.oms.domain.query.WechatPaymentHistoryQuery;

/**
 * 微信订单表Service业务层处理
 *
 *
 * @author zcc
 */
@Service
public class WechatPaymentHistoryService {
    @Autowired
    private WechatPaymentHistoryMapper wechatPaymentHistoryMapper;

    /**
     * 查询微信订单表
     *
     * @param id 微信订单表主键
     * @return 微信订单表
     */
    public WechatPaymentHistory selectById(Long id) {
        return wechatPaymentHistoryMapper.selectById(id);
    }

    /**
     * 查询微信订单表列表
     *
     * @param query 查询条件
     * @param page 分页条件
     * @return 微信订单表
     */
    public List<WechatPaymentHistory> selectList(WechatPaymentHistoryQuery query, Pageable page) {
        if (page != null) {
            PageHelper.startPage(page.getPageNumber() + 1, page.getPageSize());
        }
        QueryWrapper<WechatPaymentHistory> qw = new QueryWrapper<>();
        String paymentId = query.getPaymentId();
        if (!StringUtils.isEmpty(paymentId)) {
            qw.eq("payment_id", paymentId);
        }
        Long memberId = query.getMemberId();
        if (memberId != null) {
            qw.eq("member_id", memberId);
        }
        String openid = query.getOpenid();
        if (!StringUtils.isEmpty(openid)) {
            qw.eq("openid", openid);
        }
        String realNameLike = query.getRealNameLike();
        if (!StringUtils.isEmpty(realNameLike)) {
            qw.like("real_name", realNameLike);
        }
        String title = query.getTitle();
        if (!StringUtils.isEmpty(title)) {
            qw.eq("title", title);
        }
        Long orderId = query.getOrderId();
        if (orderId != null) {
            qw.eq("order_id", orderId);
        }
        BigDecimal money = query.getMoney();
        if (money != null) {
            qw.eq("money", money);
        }
        Integer opType = query.getOpType();
        if (opType != null) {
            qw.eq("op_type", opType);
        }
        Integer paymentStatus = query.getPaymentStatus();
        if (paymentStatus != null) {
            qw.eq("payment_status", paymentStatus);
        }
        String attach = query.getAttach();
        if (!StringUtils.isEmpty(attach)) {
            qw.eq("attach", attach);
        }
        String responseBody = query.getResponseBody();
        if (!StringUtils.isEmpty(responseBody)) {
            qw.eq("response_body", responseBody);
        }
        return wechatPaymentHistoryMapper.selectList(qw);
    }

    /**
     * 新增微信订单表
     *
     * @param wechatPaymentHistory 微信订单表
     * @return 结果
     */
    public int insert(WechatPaymentHistory wechatPaymentHistory) {
        wechatPaymentHistory.setCreateTime(LocalDateTime.now());
        return wechatPaymentHistoryMapper.insert(wechatPaymentHistory);
    }

    /**
     * 修改微信订单表
     *
     * @param wechatPaymentHistory 微信订单表
     * @return 结果
     */
    public int update(WechatPaymentHistory wechatPaymentHistory) {
        return wechatPaymentHistoryMapper.updateById(wechatPaymentHistory);
    }

    /**
     * 删除微信订单表信息
     *
     * @param id 微信订单表主键
     * @return 结果
     */
    public int deleteById(Long id) {
        return wechatPaymentHistoryMapper.deleteById(id);
    }
}
