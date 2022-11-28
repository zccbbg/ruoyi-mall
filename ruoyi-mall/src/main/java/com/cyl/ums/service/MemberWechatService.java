package com.cyl.ums.service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import com.cyl.ums.mapper.MemberWechatMapper;
import com.cyl.ums.domain.MemberWechat;
import com.cyl.ums.pojo.query.MemberWechatQuery;

/**
 * 用户微信信息Service业务层处理
 *
 *
 * @author zcc
 */
@Service
public class MemberWechatService {
    @Autowired
    private MemberWechatMapper memberWechatMapper;

    /**
     * 查询用户微信信息
     *
     * @param id 用户微信信息主键
     * @return 用户微信信息
     */
    public MemberWechat selectById(Long id) {
        return memberWechatMapper.selectById(id);
    }

    /**
     * 查询用户微信信息列表
     *
     * @param query 查询条件
     * @param page 分页条件
     * @return 用户微信信息
     */
    public List<MemberWechat> selectList(MemberWechatQuery query, Pageable page) {
        if (page != null) {
            PageHelper.startPage(page.getPageNumber() + 1, page.getPageSize());
        }
        QueryWrapper<MemberWechat> qw = new QueryWrapper<>();
        qw.eq("del_flag",0);
        Long memberId = query.getMemberId();
        if (memberId != null) {
            qw.eq("member_id", memberId);
        }
        String unionid = query.getUnionid();
        if (!StringUtils.isEmpty(unionid)) {
            qw.eq("unionid", unionid);
        }
        String openid = query.getOpenid();
        if (!StringUtils.isEmpty(openid)) {
            qw.eq("openid", openid);
        }
        String routineOpenid = query.getRoutineOpenid();
        if (!StringUtils.isEmpty(routineOpenid)) {
            qw.eq("routine_openid", routineOpenid);
        }
        Integer groupid = query.getGroupid();
        if (groupid != null) {
            qw.eq("groupid", groupid);
        }
        String tagidList = query.getTagidList();
        if (!StringUtils.isEmpty(tagidList)) {
            qw.eq("tagid_list", tagidList);
        }
        Integer subscribe = query.getSubscribe();
        if (subscribe != null) {
            qw.eq("subscribe", subscribe);
        }
        Integer subscribeTime = query.getSubscribeTime();
        if (subscribeTime != null) {
            qw.eq("subscribe_time", subscribeTime);
        }
        String sessionKey = query.getSessionKey();
        if (!StringUtils.isEmpty(sessionKey)) {
            qw.eq("session_key", sessionKey);
        }
        String accessToken = query.getAccessToken();
        if (!StringUtils.isEmpty(accessToken)) {
            qw.eq("access_token", accessToken);
        }
        Integer expiresIn = query.getExpiresIn();
        if (expiresIn != null) {
            qw.eq("expires_in", expiresIn);
        }
        String refreshToken = query.getRefreshToken();
        if (!StringUtils.isEmpty(refreshToken)) {
            qw.eq("refresh_token", refreshToken);
        }
        LocalDateTime expireTime = query.getExpireTime();
        if (expireTime != null) {
            qw.eq("expire_time", expireTime);
        }
        return memberWechatMapper.selectList(qw);
    }

    /**
     * 新增用户微信信息
     *
     * @param memberWechat 用户微信信息
     * @return 结果
     */
    public int insert(MemberWechat memberWechat) {
        memberWechat.setDelFlag(0);
        memberWechat.setCreateTime(LocalDateTime.now());
        return memberWechatMapper.insert(memberWechat);
    }

    /**
     * 修改用户微信信息
     *
     * @param memberWechat 用户微信信息
     * @return 结果
     */
    public int update(MemberWechat memberWechat) {
        return memberWechatMapper.updateById(memberWechat);
    }

    /**
     * 批量删除用户微信信息
     *
     * @param ids 需要删除的用户微信信息主键
     * @return 结果
     */
    public int deleteByIds(Long[] ids) {
        return memberWechatMapper.updateDelFlagByIds(ids);
    }

    /**
     * 删除用户微信信息信息
     *
     * @param id 用户微信信息主键
     * @return 结果
     */
    public int deleteById(Long id) {
        Long[] ids = {id};
        return memberWechatMapper.updateDelFlagByIds(ids);
    }
}
