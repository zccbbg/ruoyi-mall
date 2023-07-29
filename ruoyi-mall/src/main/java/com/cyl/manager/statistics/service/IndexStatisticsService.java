package com.cyl.manager.statistics.service;

import com.cyl.manager.oms.mapper.AftersaleMapper;
import com.cyl.manager.oms.mapper.OrderMapper;
import com.cyl.manager.oms.service.AftersaleService;
import com.cyl.manager.oms.service.OrderDeliveryHistoryService;
import com.cyl.manager.oms.service.OrderService;
import com.cyl.manager.statistics.mapper.IndexStatisticsMapper;
import com.cyl.manager.statistics.pojo.GoodsStatisticsQueryParam;
import com.cyl.manager.statistics.pojo.OrderStatisticsQueryParam;
import com.cyl.manager.statistics.pojo.vo.MemberAndCartStatisticsVO;
import com.cyl.manager.statistics.pojo.vo.OrderAndAftersaleStatisticsVO;
import com.cyl.manager.statistics.pojo.vo.OrderStatisticsVO;
import com.cyl.manager.statistics.pojo.vo.ProductTopVO;
import com.cyl.manager.ums.mapper.MemberCartMapper;
import com.cyl.manager.ums.mapper.MemberMapper;
import com.cyl.manager.ums.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

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


    public List<ProductTopVO> goodsStatistics(GoodsStatisticsQueryParam goodsStatisticsQueryParam) {
        if (goodsStatisticsQueryParam.getStatType() == 1){
            return indexStatisticsMapper.goodsSkuStatistics(goodsStatisticsQueryParam);
        }else {
            return indexStatisticsMapper.goodsStatistics(goodsStatisticsQueryParam);
        }
    }

    public List<OrderStatisticsVO> orderStatistics(OrderStatisticsQueryParam param) {
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
