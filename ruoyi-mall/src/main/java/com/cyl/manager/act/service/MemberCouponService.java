package com.cyl.manager.act.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.cyl.h5.config.SecurityUtil;
import com.cyl.manager.act.domain.entity.MemberCoupon;
import com.cyl.manager.act.domain.query.MemberCouponQuery;
import com.cyl.manager.act.domain.vo.MemberCouponVO;
import com.cyl.manager.act.mapper.MemberCouponMapper;
import com.cyl.manager.pms.domain.entity.Product;
import com.cyl.manager.ums.domain.entity.Member;
import com.cyl.manager.ums.mapper.MemberMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

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

    public MemberCoupon selectValidCoupon(Long id) {
        MemberCoupon coupon = memberCouponMapper.selectById(id);
        if (coupon == null) {
            return null;
        }
        if (Objects.equals(coupon.getUseStatus(), 1)) {
            throw new RuntimeException("优惠券已使用");
        }
        LocalDateTime now = LocalDateTime.now();
        if (coupon.getBeginTime().isAfter(now)) {
            throw new RuntimeException("优惠券未到开始使用日期");
        }
        if (coupon.getEndTime().isBefore(now)) {
            throw new RuntimeException("优惠券已过期");
        }
        return coupon;
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

    public List<MemberCoupon> getCanUseList(Collection<Product> products) {
        //先获取我的未过期的优惠券
        QueryWrapper<MemberCoupon> queryWrapper = new QueryWrapper<>();
        LocalDateTime now = LocalDateTime.now();
        queryWrapper.eq("member_id", SecurityUtil.getLocalMember().getId())
                .eq("use_status", 0)
                .ge("end_time", now)
                .le("begin_time", now);
        List<MemberCoupon> list = memberCouponMapper.selectList(queryWrapper);
        if (CollectionUtils.isEmpty(list)) {
            return list;
        }
        List<MemberCoupon> matchList = new ArrayList<>();
        list.forEach(item -> {
            if (judgeCouponCanUse(item, products)) {
                matchList.add(item);
            }
        });
        return matchList;
    }

    public Boolean judgeCouponCanUse(MemberCoupon item, Collection<Product> products) {
        //判断是否满足菜品
        if (!Objects.equals(1, item.getUseScope())) {
            List<Long> couponProducts = Arrays.stream(item.getProductIds().split(",")).map(it -> Long.parseLong(it)).collect(Collectors.toList());
            if (Objects.equals(2, item.getUseScope()) && products.stream().noneMatch(it -> couponProducts.contains(it.getId()))) {
                //指定商品
                return false;
            }
            if (Objects.equals(3, item.getUseScope()) && products.stream().anyMatch(it -> couponProducts.contains(it.getId()))) {
                //指定商品不包括
                return false;
            }
        }
        //计算金额是否满足
        if (item.getMinAmount() == null || item.getMinAmount().equals(BigDecimal.ZERO)) {
            //无门槛
            return true;
        }
        if (item.getMinAmount().compareTo(calcMinAmount(products, item)) <= 0) {
            return true;
        }
        return false;
    }

    private BigDecimal calcMinAmount(Collection<Product> products, MemberCoupon coupon) {
        List<Long> ids;
        if (!Objects.equals(1, coupon.getUseScope())) {
            ids = Arrays.stream(coupon.getProductIds().split(",")).map(it -> Long.parseLong(it)).collect(Collectors.toList());
        } else {
            ids = new ArrayList<>();
        }
        switch (coupon.getUseScope()) {
            case 1:
                return products.stream().map(Product::getPrice).reduce(BigDecimal::add).get();
            case 2:
                return products.stream().filter(it -> ids.contains(it.getId())).map(Product::getPrice).reduce(BigDecimal::add).get();
            case 3:
                return products.stream().filter(it -> !ids.contains(it.getId())).map(Product::getPrice).reduce(BigDecimal::add).get();
            default:
                return BigDecimal.ZERO;
        }
    }

    public void updateCouponStatus(Long memberCouponId, Long orderId) {
        UpdateWrapper<MemberCoupon> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", memberCouponId)
                .set("use_status", 1)
                .set("use_time", LocalDateTime.now())
                .set("order_id", orderId);
        memberCouponMapper.update(null, updateWrapper);
    }

    public void backCoupon(List<Long> couponIdList) {
        UpdateWrapper<MemberCoupon> updateWrapper = new UpdateWrapper<>();
        updateWrapper.in("id", couponIdList)
                .set("use_status", 0)
                .set("use_time", null)
                .set("order_id", null);
        memberCouponMapper.update(null, updateWrapper);
    }
}
