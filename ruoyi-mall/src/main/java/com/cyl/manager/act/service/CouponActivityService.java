package com.cyl.manager.act.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.cyl.h5.config.SecurityUtil;
import com.cyl.manager.act.domain.entity.IntegralHistory;
import com.cyl.manager.act.domain.entity.MemberCoupon;
import com.cyl.manager.act.domain.vo.CouponActivityVO;
import com.cyl.manager.act.mapper.IntegralHistoryMapper;
import com.cyl.manager.act.mapper.MemberCouponMapper;
import com.cyl.manager.pms.domain.entity.Product;
import com.cyl.manager.pms.mapper.ProductMapper;
import com.cyl.manager.ums.domain.entity.MemberAccount;
import com.cyl.manager.ums.mapper.MemberAccountMapper;
import com.github.pagehelper.PageHelper;
import com.ruoyi.framework.web.domain.server.Mem;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import com.cyl.manager.act.mapper.CouponActivityMapper;
import com.cyl.manager.act.domain.entity.CouponActivity;
import com.cyl.manager.act.domain.query.CouponActivityQuery;
import org.springframework.transaction.annotation.Transactional;

/**
 * 优惠券活动表Service业务层处理
 *
 * @author zcc
 */
@Service
public class CouponActivityService {
    @Autowired
    private CouponActivityMapper couponActivityMapper;
    @Autowired
    private MemberCouponMapper memberCouponMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private MemberAccountMapper memberAccountMapper;
    @Autowired
    private IntegralHistoryMapper integralHistoryMapper;

    /**
     * 查询优惠券活动表
     *
     * @param id 优惠券活动表主键
     * @return 优惠券活动表
     */
    public CouponActivity selectById(Long id) {
        return couponActivityMapper.selectById(id);
    }

    /**
     * 查询优惠券活动表列表
     *
     * @param query 查询条件
     * @param page  分页条件
     * @return 优惠券活动表
     */
    public Page<CouponActivityVO> selectList(CouponActivityQuery query, Pageable page) {
        PageHelper.startPage(page.getPageNumber() + 1, page.getPageSize());
        QueryWrapper<CouponActivity> qw = new QueryWrapper<>();
        String title = query.getTitle();
        if (!StringUtils.isEmpty(title)) {
            qw.like("title", "%".concat(title.trim()).concat("%"));
        }
        Integer useScope = query.getUseScope();
        if (useScope != null) {
            qw.eq("use_scope", useScope);
        }
        Integer couponType = query.getCouponType();
        if (couponType != null) {
            qw.eq("coupon_type", couponType);
        }
        List<CouponActivity> list = couponActivityMapper.selectList(qw);
        if (CollectionUtil.isEmpty(list)) {
            return new PageImpl<>(Collections.emptyList(), page, 0);
        }
        long total = ((com.github.pagehelper.Page) list).getTotal();
        List<CouponActivityVO> resList = new ArrayList<>();
        //查找已使用张数
        Map<Long, Integer> useCountMap = memberCouponMapper.countUseCoupon((list.stream().map(it -> it.getId()).collect(Collectors.toSet())))
                .stream().collect(Collectors.toMap(it -> it.getId(), it -> it.getUseCount()));
        Set<Long> productIds = new HashSet<>();
        list.stream().filter(it -> Arrays.asList(2, 3).contains(it.getUseScope()) && StringUtils.isNotEmpty(it.getProductIds()))
                .forEach(it -> {
                    productIds.addAll(Arrays.stream(it.getProductIds().split(",")).map(item -> Long.parseLong(item)).collect(Collectors.toSet()));
                });
        //查找商品列表
        Map<Long, Product> productMap = new HashMap<>();
        if (productIds.size() > 0) {
            productMap = productMapper.selectBatchIds(productIds).stream().collect(Collectors.toMap(it -> it.getId(), it -> it));
        }
        for (CouponActivity couponActivity : list) {
            CouponActivityVO vo = new CouponActivityVO();
            BeanUtils.copyProperties(couponActivity, vo);
            Integer integer = useCountMap.get(couponActivity.getId());
            vo.setUseCount(integer == null ? 0 : integer);
            if (Arrays.asList(2, 3).contains(couponActivity.getUseScope()) && StringUtils.isNotEmpty(couponActivity.getProductIds())) {
                List<Product> products = new ArrayList<>();
                for (String s : couponActivity.getProductIds().split(",")) {
                    Product product = productMap.get(Long.parseLong(s));
                    if (product != null) {
                        products.add(product);
                    }
                }
                vo.setProductList(products);
            }
            resList.add(vo);
        }
        return new PageImpl<>(resList, page, total);
    }


    public CouponActivityVO getDetail(Long id) {
        CouponActivity couponActivity = couponActivityMapper.selectById(id);
        CouponActivityVO res = new CouponActivityVO();
        BeanUtils.copyProperties(couponActivity,res);
        //判断领的有没有超
        QueryWrapper<MemberCoupon> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("member_id", SecurityUtil.getLocalMember().getId())
                .eq("coupon_activity_id", id);
        Integer count = memberCouponMapper.selectCount(queryWrapper);
        if (count != null && count >= couponActivity.getUserLimit()) {
            res.setCanGet(false);
        } else {
            res.setCanGet(true);
        }
        return res;
    }

    /**
     * 新增优惠券活动表
     *
     * @param couponActivity 优惠券活动表
     * @return 结果
     */
    public int insert(CouponActivity couponActivity) {
        couponActivity.setLeftCount(couponActivity.getTotalCount());
        couponActivity.setCreateTime(LocalDateTime.now());
        return couponActivityMapper.insert(couponActivity);
    }

    /**
     * 修改优惠券活动表
     *
     * @param couponActivity 优惠券活动表
     * @return 结果
     */
    public int update(CouponActivity couponActivity) {
        CouponActivity dbActivity = couponActivityMapper.selectById(couponActivity.getId());
        if (dbActivity == null) {
            return 0;
        }
        couponActivity.setLeftCount(dbActivity.getLeftCount());
        couponActivity.setUpdateTime(LocalDateTime.now());
        return couponActivityMapper.updateById(couponActivity);
    }

    /**
     * 删除优惠券活动表信息
     *
     * @param id 优惠券活动表主键
     * @return 结果
     */
    public int deleteById(Long id) {
        return couponActivityMapper.deleteById(id);
    }

    public Page<CouponActivityVO> selectListByH5(Pageable page) {
        PageHelper.startPage(page.getPageNumber() + 1, page.getPageSize());
        QueryWrapper<CouponActivity> qw = new QueryWrapper<>();
        LocalDateTime now = LocalDateTime.now();
        qw.lt("begin_time", now)
                .gt("end_time", now);
        List<CouponActivity> list = couponActivityMapper.selectList(qw);
        if (CollectionUtil.isEmpty(list)) {
            return new PageImpl<>(Collections.emptyList(), page, 0);
        }
        long total = ((com.github.pagehelper.Page) list).getTotal();
        //看用户已领取的张数
        Set<Long> ids = list.stream().map(it -> it.getId()).collect(Collectors.toSet());
        Map<Long, Integer> countMap = memberCouponMapper.countGetCoupon(ids, SecurityUtil.getLocalMember().getId()).stream().collect(Collectors.toMap(it -> it.getId(), it -> it.getGetCount()));
        List<CouponActivityVO> resList = new ArrayList<>();
        for (CouponActivity it : list) {
            CouponActivityVO vo = new CouponActivityVO();
            BeanUtils.copyProperties(it, vo);
            Integer integer = countMap.get(it.getId());
            if (integer == null || integer < it.getUserLimit()) {
                vo.setGetCount(integer == null ? 0 : integer);
                vo.setCanGet(true);
            } else {
                vo.setGetCount(integer);
                vo.setCanGet(false);
            }
            resList.add(vo);
        }

        return new PageImpl<>(resList, page, total);
    }

    @Transactional
    public Boolean receiveCoupon(Long id) {
        CouponActivity couponActivity = couponActivityMapper.selectById(id);
        if (couponActivity == null) {
            throw new RuntimeException("未找到活动");
        }
        //判断有没有余量
        if (couponActivity.getLeftCount() < 1) {
            throw new RuntimeException("活动已没有余额");
        }
        //判断时间有没有超
        LocalDateTime now = LocalDateTime.now();
        if (couponActivity.getBeginTime().isAfter(now) || couponActivity.getEndTime().isBefore(now)) {
            throw new RuntimeException("活动已过期");
        }
        Long memberId = SecurityUtil.getLocalMember().getId();
        //判断领的有没有超
        QueryWrapper<MemberCoupon> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("member_id", memberId)
                .eq("coupon_activity_id", id);
        Integer count = memberCouponMapper.selectCount(queryWrapper);
        if (count != null && count >= couponActivity.getUserLimit()) {
            throw new RuntimeException("您已达到领取额度");
        }
        //如果是积分兑换
        if (Objects.equals(2, couponActivity.getCouponType())) {
            //判断积分是否够
            MemberAccount memberAccount = memberAccountMapper.selectById(memberId);
            if (memberAccount.getIntegralBalance().compareTo(couponActivity.getUseIntegral()) < 0) {
                throw new RuntimeException("您的积分不足");
            }

            //扣除积分
            memberAccountMapper.updateIntegral(couponActivity.getUseIntegral(), memberId);
            //记录日志
            insertIntegralHistory(couponActivity.getUseIntegral(), couponActivity.getCouponAmount(), memberId);
        }

        //兑换券
        couponActivityMapper.receiveCoupon(id);
        int saveCount = saveMemberCoupon(couponActivity, memberId);

        return saveCount > 0;
    }

    private int saveMemberCoupon(CouponActivity activity, Long memberId) {
        MemberCoupon memberCoupon = new MemberCoupon();
        memberCoupon.setCouponActivityId(activity.getId());
        memberCoupon.setTitle(activity.getTitle());
        memberCoupon.setUseScope(activity.getUseScope());
        memberCoupon.setProductIds(activity.getProductIds());
        memberCoupon.setCouponAmount(activity.getCouponAmount());
        memberCoupon.setMinAmount(activity.getMinAmount());
        memberCoupon.setUseIntegral(activity.getUseIntegral());
        memberCoupon.setCouponType(activity.getCouponType());
        memberCoupon.setBeginTime(LocalDateTime.now());
        memberCoupon.setEndTime(activity.getEndTime());
        memberCoupon.setCreateTime(LocalDateTime.now());
        memberCoupon.setMemberId(memberId);
        return memberCouponMapper.insert(memberCoupon);
    }

    private void insertIntegralHistory(BigDecimal amount, BigDecimal couponAmount, Long memberId) {
        IntegralHistory history = new IntegralHistory();
        history.setOpType(2);
        history.setSubOpType(22);
        history.setAmount(amount);
        history.setOrderAmount(couponAmount);
        history.setMemberId(memberId);
        history.setCreateTime(LocalDateTime.now());
        integralHistoryMapper.insert(history);
    }
}
