package com.cyl.manager.act.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cyl.manager.act.domain.entity.MemberCoupon;
import com.cyl.manager.act.domain.vo.CouponActivityVO;
import com.cyl.manager.act.mapper.MemberCouponMapper;
import com.cyl.manager.pms.domain.entity.Product;
import com.cyl.manager.pms.mapper.ProductMapper;
import com.github.pagehelper.PageHelper;
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
            productMap = productMapper.selectBatchIds(productIds).stream().collect(Collectors.toMap(it->it.getId(),it->it));
        }
        for (CouponActivity couponActivity : list) {
            CouponActivityVO vo = new CouponActivityVO();
            BeanUtils.copyProperties(couponActivity,vo);
            Integer integer = useCountMap.get(couponActivity.getId());
            vo.setUseCount(integer == null ? 0 : integer);
            if (Arrays.asList(2, 3).contains(couponActivity.getUseScope()) && StringUtils.isNotEmpty(couponActivity.getProductIds())){
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
        if (dbActivity==null) {
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
}
