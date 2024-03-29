package com.cyl.manager.act.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cyl.h5.config.SecurityUtil;
import com.cyl.manager.act.domain.vo.MemberCouponVO;
import com.cyl.manager.ums.domain.entity.Member;
import com.cyl.manager.ums.mapper.MemberMapper;
import com.github.pagehelper.PageHelper;
import com.google.gson.JsonObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import com.cyl.manager.act.mapper.MemberCouponMapper;
import com.cyl.manager.act.domain.entity.MemberCoupon;
import com.cyl.manager.act.domain.query.MemberCouponQuery;

/**
 * 用户领券记录Service业务层处理
 *
 * @author zcc
 */
@Service
public class MemberCouponService {
    @Autowired
    private MemberCouponMapper memberCouponMapper;
    @Autowired
    private MemberMapper memberMapper;

    /**
     * 查询用户领券记录
     *
     * @param id 用户领券记录主键
     * @return 用户领券记录
     */
    public MemberCoupon selectById(Long id) {
        return memberCouponMapper.selectById(id);
    }

    /**
     * 查询用户领券记录列表
     *
     * @param query 查询条件
     * @param page  分页条件
     * @return 用户领券记录
     */
    public Page<MemberCouponVO> selectList(MemberCouponQuery query, Pageable page) {

        PageHelper.startPage(page.getPageNumber() + 1, page.getPageSize());
        QueryWrapper<MemberCoupon> qw = new QueryWrapper<>();
        Long couponActivityId = query.getCouponActivityId();
        if (couponActivityId != null) {
            qw.eq("coupon_activity_id", couponActivityId);
        }
        Long memberId = query.getMemberId();
        if (memberId != null) {
            qw.eq("member_id", memberId);
        }
        Integer useStatus = query.getUseStatus();
        if (useStatus != null) {
            qw.eq("use_status", useStatus);
        }
        List<MemberCoupon> list = memberCouponMapper.selectList(qw);

        long total = ((com.github.pagehelper.Page) list).getTotal();
        if (total < 1) {
            return new PageImpl<>(Collections.emptyList(), page, total);
        }
        List<MemberCouponVO> resList = new ArrayList<>();
        Set<Long> memberIds = list.stream().map(it -> it.getMemberId()).collect(Collectors.toSet());
        Map<Long, Member> memberMap = memberMapper.selectList(new QueryWrapper<Member>().in("id", memberIds))
                .stream().collect(Collectors.toMap(it -> it.getId(), it -> it));
        for (MemberCoupon it : list) {
            MemberCouponVO vo = new MemberCouponVO();
            BeanUtils.copyProperties(it, vo);
            Member member = memberMap.get(it.getMemberId());
            if (member != null) {
                vo.setNickname(member.getNickname());
                vo.setPhone(member.getPhoneHidden());
                vo.setAvatar(member.getAvatar());
            }
            resList.add(vo);
        }

        return new PageImpl<>(resList, page, total);
    }

    /**
     * 新增用户领券记录
     *
     * @param memberCoupon 用户领券记录
     * @return 结果
     */
    public int insert(MemberCoupon memberCoupon) {
        memberCoupon.setCreateTime(LocalDateTime.now());
        return memberCouponMapper.insert(memberCoupon);
    }

    /**
     * 修改用户领券记录
     *
     * @param memberCoupon 用户领券记录
     * @return 结果
     */
    public int update(MemberCoupon memberCoupon) {
        return memberCouponMapper.updateById(memberCoupon);
    }

    /**
     * 删除用户领券记录信息
     *
     * @param id 用户领券记录主键
     * @return 结果
     */
    public int deleteById(Long id) {
        return memberCouponMapper.deleteById(id);
    }

    public Page<MemberCoupon> selectListByH5(MemberCouponQuery query, Pageable page) {
        PageHelper.startPage(page.getPageNumber() + 1, page.getPageSize());
        QueryWrapper<MemberCoupon> qw = new QueryWrapper<>();
        qw.eq("member_id", SecurityUtil.getLocalMember().getId());
        LocalDateTime now = LocalDateTime.now();
        if (query.getType() != null) {
            switch (query.getType()) {
                case 1:
                    //已领取
                    qw.eq("use_status", 0)
                            .ge("end_time", now)
                            .le("begin_time", now);
                    break;
                case 2:
                    //已使用
                    qw.eq("use_status", 1);
                    break;
                case 3:
                    //已过期
                    qw.eq("use_status", 0);
                    qw.and(it -> it.le("end_time", now).or().ge("begin_time", now));
                    break;
                default:
                    break;
            }
        }
        List<MemberCoupon> list = memberCouponMapper.selectList(qw);
        return new PageImpl<>(list, page, ((com.github.pagehelper.Page) list).getTotal());
    }

}
