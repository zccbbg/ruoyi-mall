package com.cyl.ums.service;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cyl.pms.domain.Product;
import com.cyl.pms.domain.Sku;
import com.cyl.pms.mapper.ProductMapper;
import com.cyl.pms.mapper.SkuMapper;
import com.cyl.pms.pojo.dto.MemberCartDTO;
import com.cyl.ums.convert.MemberCartConvert;
import com.cyl.ums.domain.MemberCart;
import com.cyl.ums.mapper.MemberCartMapper;
import com.cyl.ums.pojo.query.MemberCartQuery;
import com.cyl.ums.pojo.vo.form.AddMemberCartForm;
import com.cyl.ums.pojo.vo.form.UpdateMemberCartForm;
import com.github.pagehelper.PageHelper;
import com.ruoyi.common.exception.base.BaseException;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.SortUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 购物车Service业务层处理
 *
 * @author zcc
 */
@Service
public class MemberCartService {
    @Autowired
    private MemberCartMapper memberCartMapper;
    @Autowired
    private SkuMapper skuMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private MemberCartConvert memberCartConvert;

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
            PageHelper.startPage(page.getPageNumber() + 1, page.getPageSize(), SortUtil.sort2string(page.getSort(),"id desc"));
        }
        QueryWrapper<MemberCart> qw = new QueryWrapper<>();
        Integer status = query.getStatus();
        if (status != null) {
            qw.eq("status", status);
        }
        Long memberId = query.getMemberId();
        if (memberId != null) {
            qw.eq("member_id", memberId);
        } else {
            qw.eq("member_id", SecurityUtils.getUserId());
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
     * 新增购物车
     *
     * @param form 添加购物车表单
     * @return 结果
     */
    public MemberCart insert(AddMemberCartForm form) {
        // 查询规格
        Sku sku = skuMapper.selectById(form.getSkuId());
        MemberCart memberCart = memberCartConvert.sku2Cart(sku);
        Product p = productMapper.selectById(sku.getProductId());
        memberCartConvert.injectProduct(memberCart, p);
        memberCart.setQuantity(form.getNum());
        memberCart.setMemberId(SecurityUtils.getUserId());
        memberCart.setStatus(1) ;
        memberCartMapper.insert(memberCart);
        return memberCart;
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
    public int update(UpdateMemberCartForm form) {
        if (form.getNum() == null || form.getId() == null) {
            throw new BaseException("参数错误");
        }
        Long userId = SecurityUtils.getUserId();
        LambdaQueryWrapper<MemberCart> qw = new LambdaQueryWrapper<>();
        qw.eq(MemberCart::getMemberId, userId);
        qw.eq(MemberCart::getId, form.getId());
        if (form.getNum() <= 0) {
            return memberCartMapper.delete(qw);
        }
        MemberCart e = new MemberCart();
        e.setQuantity(form.getNum());
        return memberCartMapper.update(e, qw);
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

    /**
     * 删除购物车信息
     *
     * @param ids 购物车主键
     * @return 结果
     */
    public int deleteByIds(List<Long> ids) {
        Long userId = SecurityUtils.getUserId();
        LambdaQueryWrapper<MemberCart> qw = new LambdaQueryWrapper<>();
        qw.eq(MemberCart::getMemberId, userId);
        qw.in(MemberCart::getId, ids);
        return memberCartMapper.delete(qw);
    }

    public Integer mineCartNum() {
        Long userId = SecurityUtils.getUserId();
        QueryWrapper<MemberCart> qw = new QueryWrapper<>();
        qw.eq("member_id", userId);
        qw.eq("status", 1);
        qw.select("count(quantity) quantity");
        MemberCart c = memberCartMapper.selectOne(qw);
        if (c == null) {
            return 0;
        }
        return c.getQuantity();
    }

    public void injectSku(List<MemberCartDTO> resList) {
        List<Long> skuIds = resList.stream().map(MemberCartDTO::getSkuId).distinct().collect(Collectors.toList());
        if (CollUtil.isEmpty(skuIds)) {
            return;
        }
        List<Sku> skus = skuMapper.selectBatchIds(skuIds);
        Map<Long, Sku> map = new HashMap<>();
        skus.forEach(it -> {
            map.put(it.getId(), it);
        });
        resList.forEach(it -> {
            Sku s = map.get(it.getSkuId());
            if (s == null) {
                return;
            }
            it.setPrice(s.getPrice());
        });
    }

    public List<Long> mineCartIds() {
        QueryWrapper<MemberCart> qw = new QueryWrapper<>();
        qw.eq("member_id", SecurityUtils.getUserId());
        qw.eq("status", 1);
        qw.select("id");
        List<MemberCart> list = memberCartMapper.selectList(qw);
        return list.stream().map(MemberCart::getId).collect(Collectors.toList());
    }
}
