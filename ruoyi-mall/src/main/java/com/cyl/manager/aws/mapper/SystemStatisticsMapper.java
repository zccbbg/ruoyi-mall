package com.cyl.manager.aws.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cyl.manager.aws.domain.entity.SystemStatistics;

/**
 * 系统数据统计Mapper接口
 * 
 * @author zcc
 */
public interface SystemStatisticsMapper extends BaseMapper<SystemStatistics> {
    /**
     * 查询系统数据统计列表
     *
     * @param systemStatistics 系统数据统计
     * @return 系统数据统计集合
     */
    List<SystemStatistics> selectByEntity(SystemStatistics systemStatistics);
}
