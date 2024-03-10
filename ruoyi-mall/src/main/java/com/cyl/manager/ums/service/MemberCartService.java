package com.cyl.manager.ums.service;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cyl.h5.config.SecurityUtil;
import com.cyl.manager.pms.domain.entity.Sku;
import com.cyl.manager.pms.mapper.ProductMapper;
import com.cyl.manager.pms.mapper.SkuMapper;
import com.cyl.manager.ums.convert.MemberCartConvert;
import com.cyl.manager.ums.domain.entity.Member;
import com.cyl.manager.ums.domain.entity.MemberCart;
import com.cyl.manager.ums.mapper.MemberCartMapper;
import com.cyl.manager.ums.mapper.MemberMapper;
import com.cyl.manager.ums.domain.query.MemberCartQuery;
import com.cyl.manager.ums.domain.vo.MemberCartVO;
import com.cyl.manager.ums.domain.form.UpdateMemberCartForm;
import com.github.pagehelper.PageHelper;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.exception.base.BaseException;
import com.ruoyi.common.utils.AesCryptoUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.SortUtil;
import com.ruoyi.framework.config.LocalDataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
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
    private MemberMapper memberMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private MemberCartConvert memberCartConvert;
    @Value("${aes.key}")
    private String aesKey;

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
    public PageImpl<MemberCartVO> selectList(MemberCartQuery query, Pageable page) {
        if (page != null) {
            PageHelper.startPage(page.getPageNumber() + 1, page.getPageSize(), SortUtil.sort2string(page.getSort(),"id desc"));
        }
        if (!StrUtil.isEmpty(query.getPhone())){
            query.setPhone(AesCryptoUtils.encrypt(aesKey, query.getPhone()));
        }
        List<MemberCartVO> memberCartList = memberCartMapper.selectByPage(query);
        long total = ((com.github.pagehelper.Page)memberCartList).getTotal();
        if (CollectionUtil.isEmpty(memberCartList)){
            return new PageImpl<>(Collections.EMPTY_LIST, page, total);
        }
        //查sku
        List<Long> skuIdList = memberCartList.stream().map(MemberCartVO::getSkuId).collect(Collectors.toList());
        QueryWrapper<Sku> skuQw = new QueryWrapper<>();
        skuQw.in("id", skuIdList);
        Map<Long, Sku> skuMap = skuMapper.selectList(skuQw).stream().collect(Collectors.toMap(Sku::getId, it -> it));
        memberCartList.forEach(item -> {
            if (!skuMap.containsKey(item.getSkuId())){
                item.setStatus(0);
                item.setSkuIfExist(0);
            }else {
                Sku sku = skuMap.get(item.getSkuId());
                item.setPrice(sku.getPrice());
                item.setSkuIfExist(1);
            }
        });
        return new PageImpl<>(memberCartList, page, total);
    }

    /**
     * 新增购物车
     *
     * @param memberCart 购物车
     * @return 结果
     */
    public int insert(MemberCart memberCart) {
        Member member = (Member) LocalDataUtil.getVar(Constants.MEMBER_INFO);
        memberCart.setMemberId(member.getId());
        //判断cart是否存在
        QueryWrapper<MemberCart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("member_id",member.getId());
        queryWrapper.eq("sku_id",memberCart.getSkuId());
        queryWrapper.eq("product_id",memberCart.getProductId());
        List<MemberCart> memberCarts = memberCartMapper.selectList(queryWrapper);
        if (CollectionUtils.isEmpty(memberCarts)) {
            memberCart.setStatus(1);
            memberCart.setCreateTime(LocalDateTime.now());
            memberCart.setCreateBy(member.getId());
            return memberCartMapper.insert(memberCart);
        }
        MemberCart dbCart = memberCarts.get(0);
        dbCart.setUpdateTime(LocalDateTime.now());
        dbCart.setQuantity(dbCart.getQuantity() + memberCart.getQuantity());
        return memberCartMapper.updateById(dbCart);
    }

    /**
     * 修改购物车
     *
     * @param memberCart 购物车
     * @return 结果
     */
    public int update(MemberCart memberCart) {
        MemberCart cart = memberCartMapper.selectById(memberCart.getId());
        if (cart == null){
            return 0;
        }
        cart.setQuantity(memberCart.getQuantity());
        cart.setUpdateTime(LocalDateTime.now());
        cart.setUpdateBy(SecurityUtil.getLocalMember().getId());
        return memberCartMapper.updateById(cart);
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
    public int deleteByIds(String ids) {
        List<Long> idList = Arrays.stream(ids.split(",")).map(it -> Long.parseLong(it)).collect(Collectors.toList());
        return memberCartMapper.deleteBatchIds(idList);
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
}
