package com.cyl.manager.act.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cyl.h5.config.SecurityUtil;
import com.cyl.manager.act.constant.IntegralRule;
import com.cyl.manager.act.domain.vo.IntegralStatVO;
import com.cyl.manager.ums.domain.entity.MemberAccount;
import com.cyl.manager.ums.mapper.MemberAccountMapper;
import com.github.pagehelper.PageHelper;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.system.service.ISysConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import com.cyl.manager.act.mapper.IntegralHistoryMapper;
import com.cyl.manager.act.domain.entity.IntegralHistory;
import com.cyl.manager.act.domain.query.IntegralHistoryQuery;
import org.springframework.transaction.annotation.Transactional;

/**
 * 积分流水表Service业务层处理
 *
 * @author zcc
 */
@Service
@Slf4j
@Transactional
public class IntegralHistoryService {
    @Autowired
    private IntegralHistoryMapper integralHistoryMapper;
    @Autowired
    private MemberAccountMapper memberAccountMapper;
    @Autowired
    private ISysConfigService sysConfigService;

    /**
     * 查询积分流水表
     *
     * @param id 积分流水表主键
     * @return 积分流水表
     */
    public IntegralHistory selectById(Long id) {
        return integralHistoryMapper.selectById(id);
    }

    /**
     * 查询积分流水表列表
     *
     * @param query 查询条件
     * @param page  分页条件
     * @return 积分流水表
     */
    public List<IntegralHistory> selectList(IntegralHistoryQuery query, Pageable page) {
        if (page != null) {
            PageHelper.startPage(page.getPageNumber() + 1, page.getPageSize());
        }
        QueryWrapper<IntegralHistory> qw = new QueryWrapper<>();
        Long memberId = query.getMemberId();
        if (memberId != null) {
            qw.eq("member_id", memberId);
        }
        BigDecimal amount = query.getAmount();
        if (amount != null) {
            qw.eq("amount", amount);
        }
        Integer opType = query.getOpType();
        if (opType != null) {
            qw.eq("op_type", opType);
        }
        Integer subOpType = query.getSubOpType();
        if (subOpType != null) {
            qw.eq("sub_op_type", subOpType);
        }
        BigDecimal orderAmount = query.getOrderAmount();
        if (orderAmount != null) {
            qw.eq("order_amount", orderAmount);
        }
        Long orderId = query.getOrderId();
        if (orderId != null) {
            qw.eq("order_id", orderId);
        }
        return integralHistoryMapper.selectList(qw);
    }

    public List<IntegralHistory> selectList2(IntegralHistoryQuery query) {
        QueryWrapper<IntegralHistory> qw = new QueryWrapper<>();
        Long memberId = query.getMemberId();
        if (memberId != null) {
            qw.eq("member_id", memberId);
        }
        Integer opType = query.getOpType();
        if (opType != null) {
            qw.eq("op_type", opType);
        }
        Integer subOpType = query.getSubOpType();
        if (subOpType != null) {
            qw.eq("sub_op_type", subOpType);
        }
        if (query.getStart() != null) {
            qw.ge("create_time", query.getStart());
        }
        if (query.getEnd() != null) {
            qw.le("create_time", query.getEnd());
        }
        return integralHistoryMapper.selectList(qw);
    }

    /**
     * 新增积分流水表
     *
     * @param integralHistory 积分流水表
     * @return 结果
     */
    public int insert(IntegralHistory integralHistory) {
        integralHistory.setCreateTime(LocalDateTime.now());
        return integralHistoryMapper.insert(integralHistory);
    }

    public int insert2(IntegralHistory history) {
        Long memberId = history.getMemberId();
        //保存member_account
        MemberAccount memberAccount = memberAccountMapper.selectById(memberId);
        if (memberAccount == null) {
            memberAccount = new MemberAccount();
            memberAccount.setMemberId(memberId);
            memberAccount.setIntegralBalance(history.getAmount());
            memberAccount.setTotalIntegralBalance(history.getAmount());
            memberAccount.setCreateTime(LocalDateTime.now());
            memberAccountMapper.insert(memberAccount);
        } else {
            memberAccountMapper.updateIntegralBalance(history.getAmount(), memberId);
        }
        return integralHistoryMapper.insert(history);
    }

    public void handleIntegral(Long orderId, BigDecimal amount, Long memberId) {
        String config = sysConfigService.selectConfigByKey(Constants.INTEGRAL_RULE_KEY);
        IntegralRule rule;
        if (StringUtils.isNotEmpty(config)) {
            rule = JSON.parseObject(config, IntegralRule.class);
        } else {
            rule = new IntegralRule();
        }
        BigDecimal divide = amount.divide(rule.getOrderAmount(), 0, RoundingMode.DOWN);
        if (divide.compareTo(BigDecimal.ZERO) < 1) {
            log.info("订单：{}，金额：{}不足{}元，不记录积分",orderId,amount,rule.getOrderAmount());
            return;
        }
        BigDecimal total = divide.multiply(rule.getOrderCount());
        if (total.compareTo(BigDecimal.ZERO) < 1) {
            log.info("订单：{}，orderCount为0，不记录积分",orderId);
            return;
        }
        IntegralHistory history = new IntegralHistory();
        history.setOpType(1);
        history.setSubOpType(12);
        history.setAmount(total);
        history.setOrderId(orderId);
        history.setOrderAmount(amount);
        history.setMemberId(memberId);
        history.setCreateTime(LocalDateTime.now());
        insert2(history);
    }

    /**
     * 修改积分流水表
     *
     * @param integralHistory 积分流水表
     * @return 结果
     */
    public int update(IntegralHistory integralHistory) {
        return integralHistoryMapper.updateById(integralHistory);
    }

    /**
     * 删除积分流水表信息
     *
     * @param id 积分流水表主键
     * @return 结果
     */
    public int deleteById(Long id) {
        return integralHistoryMapper.deleteById(id);
    }

    public List<IntegralHistory> selectListByH5(IntegralHistoryQuery query, Pageable page) {
        if (page != null) {
            PageHelper.startPage(page.getPageNumber() + 1, page.getPageSize());
        }
        QueryWrapper<IntegralHistory> qw = new QueryWrapper<>();
        qw.eq("member_id", SecurityUtil.getLocalMember().getId())
                .ge("create_time", query.getStart())
                .le("create_time", query.getEnd());
        Integer opType = query.getOpType();
        if (opType != null) {
            qw.eq("op_type", opType);
        }
        Integer subOpType = query.getSubOpType();
        if (subOpType != null) {
            qw.eq("sub_op_type", subOpType);
        }
        qw.orderByDesc("id");
        return integralHistoryMapper.selectList(qw);
    }

    public IntegralStatVO statIntegral(IntegralHistoryQuery query) {
        Long memberId = SecurityUtil.getLocalMember().getId();
        IntegralStatVO statVO = integralHistoryMapper.statIntegral(query.getStart(), query.getEnd(), memberId);
        if (statVO == null) {
            statVO = new IntegralStatVO();
        }
        MemberAccount memberAccount = memberAccountMapper.selectById(memberId);
        statVO.setBalance(memberAccount == null ? BigDecimal.ZERO : memberAccount.getIntegralBalance());
        return statVO;
    }
}
