package com.cyl.manager.aws.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.stream.Collectors;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cyl.manager.oms.domain.Aftersale;
import com.cyl.manager.oms.mapper.AftersaleMapper;
import com.cyl.manager.oms.mapper.OrderMapper;
import com.cyl.manager.ums.domain.Member;
import com.cyl.manager.ums.domain.MemberCart;
import com.cyl.manager.ums.mapper.MemberCartMapper;
import com.cyl.manager.ums.mapper.MemberLogininforMapper;
import com.cyl.manager.ums.mapper.MemberMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import com.cyl.manager.aws.mapper.SystemStatisticsMapper;
import com.cyl.manager.aws.domain.SystemStatistics;
import com.cyl.manager.aws.pojo.query.SystemStatisticsQuery;

/**
 * 系统数据统计Service业务层处理
 *
 * @author zcc
 */
@Service
public class SystemStatisticsService {
    @Autowired
    private SystemStatisticsMapper systemStatisticsMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private AftersaleMapper aftersaleMapper;

    @Autowired
    private MemberLogininforMapper memberLogininforMapper;

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private MemberCartMapper memberCartMapper;

    /**
     * 查询系统数据统计
     *
     * @param id 系统数据统计主键
     * @return 系统数据统计
     */
    public SystemStatistics selectById(Long id) {
        return systemStatisticsMapper.selectById(id);
    }

    /**
     * 查询系统数据统计列表
     *
     * @param query 查询条件
     * @param page  分页条件
     * @return 系统数据统计
     */
    public List<SystemStatistics> selectList(SystemStatisticsQuery query, Pageable page) {
        if (page != null) {
            PageHelper.startPage(page.getPageNumber() + 1, page.getPageSize());
        }
        QueryWrapper<SystemStatistics> qw = new QueryWrapper<>();
        LocalDateTime date = query.getDate();
        if (date != null) {
            qw.eq("date", date);
        }
        Integer loginMemberCount = query.getLoginMemberCount();
        if (loginMemberCount != null) {
            qw.eq("login_member_count", loginMemberCount);
        }
        Integer registerMemberCount = query.getRegisterMemberCount();
        if (registerMemberCount != null) {
            qw.eq("register_member_count", registerMemberCount);
        }
        Integer addCartMemberCount = query.getAddCartMemberCount();
        if (addCartMemberCount != null) {
            qw.eq("add_cart_member_count", addCartMemberCount);
        }
        Integer createOrderMemberCount = query.getCreateOrderMemberCount();
        if (createOrderMemberCount != null) {
            qw.eq("create_order_member_count", createOrderMemberCount);
        }
        Integer dealMemberCount = query.getDealMemberCount();
        if (dealMemberCount != null) {
            qw.eq("deal_member_count", dealMemberCount);
        }
        Integer orderCount = query.getOrderCount();
        if (orderCount != null) {
            qw.eq("order_count", orderCount);
        }
        Integer dealCount = query.getDealCount();
        if (dealCount != null) {
            qw.eq("deal_count", dealCount);
        }
        BigDecimal dealAmount = query.getDealAmount();
        if (dealAmount != null) {
            qw.eq("deal_amount", dealAmount);
        }
        Integer aftersaleCount = query.getAftersaleCount();
        if (aftersaleCount != null) {
            qw.eq("aftersale_count", aftersaleCount);
        }
        BigDecimal aftersaleAmount = query.getAftersaleAmount();
        if (aftersaleAmount != null) {
            qw.eq("aftersale_amount", aftersaleAmount);
        }
        return systemStatisticsMapper.selectList(qw);
    }

    /**
     * 新增系统数据统计
     *
     * @param systemStatistics 系统数据统计
     * @return 结果
     */
    public int insert(SystemStatistics systemStatistics) {
        return systemStatisticsMapper.insert(systemStatistics);
    }

    /**
     * 修改系统数据统计
     *
     * @param systemStatistics 系统数据统计
     * @return 结果
     */
    public int update(SystemStatistics systemStatistics) {
        return systemStatisticsMapper.updateById(systemStatistics);
    }

    /**
     * 删除系统数据统计信息
     *
     * @param id 系统数据统计主键
     * @return 结果
     */
    public int deleteById(Long id) {
        return systemStatisticsMapper.deleteById(id);
    }

    public SystemStatistics stat(LocalDateTime startTime, LocalDateTime endTime) {
        //统计下单用户数、成交用户数、下单数、成交数、成交金额
        SystemStatistics systemStatistics = orderMapper.statNewAndDeal(startTime, endTime);
        //统计成交用户数
        systemStatistics.setDealMemberCount(orderMapper.statDealMember(startTime, endTime));
        //统计售后
        LambdaQueryWrapper<Aftersale> wrapper = new LambdaQueryWrapper<>();
        wrapper.between(Aftersale::getCreateTime, startTime, endTime);
        List<Aftersale> aftersaleList = aftersaleMapper.selectList(wrapper);
        if (CollectionUtil.isEmpty(aftersaleList)) {
            systemStatistics.setAftersaleCount(0);
            systemStatistics.setAftersaleAmount(BigDecimal.ZERO);
        } else {
            Map<Long, BigDecimal> map = aftersaleList.stream().collect(Collectors.toMap(Aftersale::getOrderId, Aftersale::getReturnAmount));
            systemStatistics.setAftersaleCount(map.values().size());
            systemStatistics.setAftersaleAmount(map.values().stream().reduce(BigDecimal::add).get());
        }
        //统计登录用户数
        systemStatistics.setLoginMemberCount(memberLogininforMapper.statLoginMember(startTime, endTime));
        //统计注册用户
        LambdaQueryWrapper<Member> memberWrapper = new LambdaQueryWrapper<>();
        memberWrapper.between(Member::getCreateTime, startTime, endTime);
        systemStatistics.setRegisterMemberCount(memberMapper.selectCount(memberWrapper));
        //统计加购数
        systemStatistics.setAddCartMemberCount(memberCartMapper.statAddCount(startTime, endTime));
        systemStatistics.setDate(startTime);
        return systemStatistics;
    }
}
