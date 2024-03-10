package com.cyl.manager.ums.service;

import java.time.LocalDateTime;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.cyl.h5.config.SecurityUtil;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import com.cyl.manager.ums.mapper.FeedbackMapper;
import com.cyl.manager.ums.domain.entity.Feedback;
import com.cyl.manager.ums.domain.query.FeedbackQuery;

/**
 * 意见反馈Service业务层处理
 *
 *
 * @author zcc
 */
@Service
public class FeedbackService {
    @Autowired
    private FeedbackMapper feedbackMapper;

    /**
     * 查询意见反馈
     *
     * @param id 意见反馈主键
     * @return 意见反馈
     */
    public Feedback selectById(Long id) {
        return feedbackMapper.selectById(id);
    }

    /**
     * 查询意见反馈列表
     *
     * @param query 查询条件
     * @param page 分页条件
     * @return 意见反馈
     */
    public List<Feedback> selectList(FeedbackQuery query, Pageable page) {
        if (page != null) {
            PageHelper.startPage(page.getPageNumber() + 1, page.getPageSize());
        }
        QueryWrapper<Feedback> qw = new QueryWrapper<>();
        String type = query.getType();
        if (!StringUtils.isEmpty(type)) {
            qw.eq("type", type);
        }
        String content = query.getContent();
        if (!StringUtils.isEmpty(content)) {
            qw.eq("content", content);
        }
        String images = query.getImages();
        if (!StringUtils.isEmpty(images)) {
            qw.eq("images", images);
        }
        String phone = query.getPhone();
        if (!StringUtils.isEmpty(phone)) {
            qw.eq("phone", phone);
        }
        Integer handleStatus = query.getHandleStatus();
        if (handleStatus != null) {
            qw.eq("handle_status", handleStatus);
        }
        LocalDateTime handleTime = query.getHandleTime();
        if (handleTime != null) {
            qw.eq("handle_time", handleTime);
        }
        if (!StringUtils.isEmpty(query.getBeginTime()) && !StringUtils.isEmpty(query.getEndTime())){
            qw.ge("create_time", query.getBeginTime());
            qw.lt("create_time", query.getEndTime());
        }
        qw.orderByDesc("id");
        return feedbackMapper.selectList(qw);
    }

    /**
     * 新增意见反馈
     *
     * @param feedback 意见反馈
     * @return 结果
     */
    public int insert(Feedback feedback) {
        feedback.setCreateTime(LocalDateTime.now());
        feedback.setCreateBy(SecurityUtil.getLocalMember().getId());
        feedback.setHandleStatus(0);
        return feedbackMapper.insert(feedback);
    }

    /**
     * 修改意见反馈
     *
     * @param feedback 意见反馈
     * @return 结果
     */
    public int update(Feedback feedback) {
        return feedbackMapper.updateById(feedback);
    }

    /**
     * 删除意见反馈信息
     *
     * @param id 意见反馈主键
     * @return 结果
     */
    public int deleteById(Long id) {
        return feedbackMapper.deleteById(id);
    }

    public Integer updateMark(Feedback feedback) {
        UpdateWrapper<Feedback> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("remark",feedback.getRemark())
                .eq("id",feedback.getId());
        return feedbackMapper.update(null,updateWrapper);
    }

    public Integer changeStatus(Feedback dto) {
        UpdateWrapper<Feedback> wrapper = new UpdateWrapper<>();
        wrapper.eq("id", dto.getId());
        wrapper.set("handle_status", dto.getHandleStatus())
                .set("handle_time",LocalDateTime.now());
        return feedbackMapper.update(null, wrapper);
    }
}
