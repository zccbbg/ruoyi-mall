package com.cyl.job;

import cn.hutool.core.date.LocalDateTimeUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cyl.h5.domain.form.CancelOrderForm;
import com.cyl.h5.service.H5OrderService;
import com.cyl.manager.oms.domain.entity.Order;
import com.cyl.manager.oms.mapper.OrderMapper;
import com.ruoyi.common.constant.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class OrderJob {

    @Autowired
    private H5OrderService h5OrderService;

    @Autowired
    private OrderMapper orderMapper;

    /**
     * 每天的1点20分20秒执行任务
     */
    @Scheduled(cron = "20 20 1 * * ?")
    public void cancelOrder(){
        log.info("【取消订单任务开始】");
        QueryWrapper<Order> qw = new QueryWrapper<>();
        qw.eq("status", Constants.OrderStatus.NOTPAID);
        qw.eq("aftersale_status", 1);
        List<Order> orderList = orderMapper.selectList(qw);
        List<Long> idList = orderList.stream()
                .filter(order -> LocalDateTimeUtil.between(order.getCreateTime(), LocalDateTime.now()).toMinutes() >= 15)
                .map(Order::getId)
                .collect(Collectors.toList());
        CancelOrderForm request = new CancelOrderForm();
        request.setIdList(idList);
        h5OrderService.orderBatchCancel(request, null);
        log.info("【取消订单任务结束】");
    }

    /**
     * 每天的2点20分20秒执行任务
     */
    @Scheduled(cron = "20 20 2 * * ?")
    public void batchCompleteOrder(){
        log.info("【订单完成任务开始】");
        QueryWrapper<Order> qw = new QueryWrapper<>();
        qw.eq("status", Constants.OrderStatus.GET);
        qw.eq("aftersale_status", 1);
        qw.le("delivery_time", LocalDateTime.now().minusDays(15));
        List<Order> orderList = orderMapper.selectList(qw);
        h5OrderService.orderCompleteByJob(orderList);
        log.info("【订单完成任务结束】,处理了：{}个订单",orderList.size());
    }

}
