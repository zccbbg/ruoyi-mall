package com.cyl.manager.statistics.service;

import com.cyl.manager.oms.mapper.AftersaleMapper;
import com.cyl.manager.oms.mapper.OrderMapper;
import com.cyl.manager.statistics.mapper.IndexStatisticsMapper;
import com.cyl.manager.statistics.domain.query.GoodsStatisticsQuery;
import com.cyl.manager.statistics.domain.query.OrderStatisticsQuery;
import com.cyl.manager.statistics.domain.vo.MemberAndCartStatisticsVO;
import com.cyl.manager.statistics.domain.vo.OrderAndAftersaleStatisticsVO;
import com.cyl.manager.statistics.domain.vo.OrderStatisticsVO;
import com.cyl.manager.statistics.domain.vo.ProductTopVO;
import com.cyl.manager.ums.mapper.MemberCartMapper;
import com.cyl.manager.ums.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * 管理端,首页统计数据Service业务层处理
 *
 * @author zhangcheng
 * @since 2023/05/15 13:53
 */
@Service
public class IndexStatisticsService {
    @Autowired
    private IndexStatisticsMapper indexStatisticsMapper;
    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private MemberCartMapper memberCartMapper;
    @Autowired
    private AftersaleMapper aftersaleMapper;
    @Autowired
    private OrderMapper orderMapper;


    public List<ProductTopVO> goodsStatistics(GoodsStatisticsQuery goodsStatisticsQuery) {
        if (goodsStatisticsQuery.getStatType() == 1){
            return indexStatisticsMapper.goodsSkuStatistics(goodsStatisticsQuery);
        }else {
            return indexStatisticsMapper.goodsStatistics(goodsStatisticsQuery);
        }
    }

    public List<OrderStatisticsVO> orderStatistics(OrderStatisticsQuery param) {
        return indexStatisticsMapper.orderStatistics(param);
    }

    public MemberAndCartStatisticsVO statMemberAndCart() {
        MemberAndCartStatisticsVO vo = new MemberAndCartStatisticsVO();
        vo.setMemberCount(memberMapper.selectCount(null));
        vo.setCartCount(memberCartMapper.selectCount(null));
        return vo;
    }

    public OrderAndAftersaleStatisticsVO orderAndAftersaleStatistics() {
        //统计售后
        OrderAndAftersaleStatisticsVO vo = aftersaleMapper.statPendingAndProcessing();
        //统计未发货数
        vo.setWaitDeliveredCount(orderMapper.statWaitDelivered());
        //统计今日订单数，成交金额，发货数
        LocalDateTime startTime = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
        LocalDateTime endTime = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
        OrderAndAftersaleStatisticsVO todayData = orderMapper.statTodayData(startTime, endTime);
        vo.setTodayOrderCount(todayData.getTodayOrderCount());
        vo.setTodayHasDeliveredCount(todayData.getTodayHasDeliveredCount());
        vo.setTodayTransactionAmount(todayData.getTodayTransactionAmount());
        return vo;
    }
}
