package com.cyl.manager.act.mapper;

import java.time.LocalDateTime;
import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cyl.manager.act.pojo.vo.IntegralStatVO;
import org.apache.ibatis.annotations.Param;
import com.cyl.manager.act.domain.IntegralHistory;

/**
 * 积分流水表Mapper接口
 * 
 * @author zcc
 */
public interface IntegralHistoryMapper extends BaseMapper<IntegralHistory> {
    /**
     * 查询积分流水表列表
     *
     * @param integralHistory 积分流水表
     * @return 积分流水表集合
     */
    List<IntegralHistory> selectByEntity(IntegralHistory integralHistory);

    IntegralStatVO statIntegral(LocalDateTime start, LocalDateTime end, Long memberId);
}
