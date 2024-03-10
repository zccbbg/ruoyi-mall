package com.cyl.job;

import com.cyl.manager.aws.domain.entity.SystemStatistics;
import com.cyl.manager.aws.mapper.SystemStatisticsMapper;
import com.cyl.manager.aws.service.SystemStatisticsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


@Component
@Slf4j
public class StatisticsJob {

    @Autowired
    private SystemStatisticsService systemStatisticsService;

    @Autowired
    private SystemStatisticsMapper systemStatisticsMapper;

    @Async
    @Scheduled(cron = "00 00 3 * * ?")
    public void cancelOrder() {
        log.info("【统计昨日系统数据任务开始】");
        LocalDateTime startTime = LocalDateTime.of(LocalDate.now(), LocalTime.MIN).plusDays(-1);
        LocalDateTime endTime = LocalDateTime.of(LocalDate.now(), LocalTime.MAX).plusDays(-1);
        SystemStatistics data = systemStatisticsService.stat(startTime, endTime);
        systemStatisticsMapper.insert(data);
        log.info("【统计昨日系统数据任务结束】");
    }
}
