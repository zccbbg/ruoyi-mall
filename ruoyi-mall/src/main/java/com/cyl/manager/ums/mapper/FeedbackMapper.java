package com.cyl.manager.ums.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cyl.manager.ums.domain.entity.Feedback;

/**
 * 意见反馈Mapper接口
 * 
 * @author zcc
 */
public interface FeedbackMapper extends BaseMapper<Feedback> {
    /**
     * 查询意见反馈列表
     *
     * @param feedback 意见反馈
     * @return 意见反馈集合
     */
    List<Feedback> selectByEntity(Feedback feedback);
}
