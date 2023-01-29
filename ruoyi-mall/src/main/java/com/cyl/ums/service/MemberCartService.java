package com.cyl.ums.service;

import java.util.Arrays;
import java.util.List;
import java.time.LocalDateTime;

import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import com.cyl.ums.mapper.MemberCartMapper;
import com.cyl.ums.domain.MemberCart;
import com.cyl.ums.pojo.query.MemberCartQuery;

/**
 * 购物车Service业务层处理
 *
 * @author zcc
 */
@Service
public class MemberCartService {
    @Autowired
    private MemberCartMapper memberCartMapper;

    /**
     * 查询购物车
     *
     * @param id 购物车主键
     * @return 购物车
     */
    public MemberCart selectById(Long id) {
        return memberCartMapper.selectById(id);
    }

    /**
     * 查询购物车列表
     *
     * @param query 查询条件
     * @param page  分页条件
     * @return 购物车
     */
    public List<MemberCart> selectList(MemberCartQuery query, Pageable page) {
        if (page != null) {
            PageHelper.startPage(page.getPageNumber() + 1, page.getPageSize());
        }
        QueryWrapper<MemberCart> qw = new QueryWrapper<>();
        Integer status = query.getStatus();
        if (status != null) {
            qw.eq("status", status);
        }
        Long memberId = query.getMemberId();
        if (memberId != null) {
            qw.eq("member_id", memberId);
        }
        Long productId = query.getProductId();
        if (productId != null) {
            qw.eq("product_id", productId);
        }
        String pic = query.getPic();
        if (!StringUtils.isEmpty(pic)) {
            qw.eq("pic", pic);
        }
        Long skuId = query.getSkuId();
        if (skuId != null) {
            qw.eq("sku_id", skuId);
        }
        String productNameLike = query.getProductNameLike();
        if (!StringUtils.isEmpty(productNameLike)) {
            qw.like("product_name", productNameLike);
        }
        String spData = query.getSpData();
        if (!StringUtils.isEmpty(spData)) {
            qw.eq("sp_data", spData);
        }
        Integer quantity = query.getQuantity();
        if (quantity != null) {
            qw.eq("quantity", quantity);
        }
        return memberCartMapper.selectList(qw);
    }

    /**
     * 新增购物车
     *
     * @param memberCart 购物车
     * @return 结果
     */
    public int insert(MemberCart memberCart) {
        memberCart.setCreateTime(LocalDateTime.now());
        return memberCartMapper.insert(memberCart);
    }

    /**
     * 修改购物车
     *
     * @param memberCart 购物车
     * @return 结果
     */
    public int update(MemberCart memberCart) {
        return memberCartMapper.updateById(memberCart);
    }

    /**
     * 删除购物车信息
     *
     * @param id 购物车主键
     * @return 结果
     */
    public int deleteById(Long id) {
        return memberCartMapper.deleteById(id);
    }

    public Integer mineCartNum() {
        Long userId = SecurityUtils.getUserId();
        QueryWrapper<MemberCart> qw = new QueryWrapper<>();
        qw.eq("member_id", userId);
        qw.eq("status", 1);
        qw.select("sum(quantity) quantity");
        MemberCart c = memberCartMapper.selectOne(qw);
        if (c == null) {
            return 0;
        }
        return c.getQuantity();
    }
}
