package com.cyl.manager.pms.service;

import java.util.*;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cyl.h5.domain.vo.ProductDetailVO;
import com.cyl.manager.pms.convert.ProductConvert;
import com.cyl.manager.pms.domain.entity.Sku;
import com.cyl.manager.pms.mapper.BrandMapper;
import com.cyl.manager.pms.mapper.SkuMapper;
import com.cyl.manager.pms.domain.vo.ProductVO;
import com.github.pagehelper.PageHelper;
import com.ruoyi.common.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import com.cyl.manager.pms.mapper.ProductMapper;
import com.cyl.manager.pms.domain.entity.Product;
import com.cyl.manager.pms.domain.query.ProductQuery;
import org.springframework.transaction.annotation.Transactional;

/**
 * 商品信息Service业务层处理
 *
 *
 * @author zcc
 */
@Service
@Slf4j
public class ProductService {
    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private SkuMapper skuMapper;
    @Autowired
    private BrandMapper brandMapper;
    @Autowired
    private ProductConvert convert;

    /**
     * 查询商品信息
     *
     * @param id 商品信息主键
     * @return 商品信息
     */
    public ProductVO selectById(Long id) {
        Product product = productMapper.selectById(id);
        ProductVO productVO = convert.do2vo(product);
        QueryWrapper<Sku> qw = new QueryWrapper<>();
        qw.eq("product_id", product.getId());
        List<Sku> skus = skuMapper.selectList(qw);
        productVO.setSkuList(skus);
        return productVO;
    }

    /**
     * 查询商品信息列表
     *
     * @param query 查询条件
     * @param page 分页条件
     * @return 商品信息
     */
    public List<Product> selectList(ProductQuery query, Pageable page) {
        if (page != null) {
            PageHelper.startPage(page.getPageNumber() + 1, page.getPageSize());
        }
        QueryWrapper<Product> qw = new QueryWrapper<>();
        if (StringUtils.isNoneEmpty(query.getOrderField())){
            if (StringUtils.isNotEmpty(query.getOrderSort()) && "desc".equalsIgnoreCase(query.getOrderSort())) {
                qw.orderByDesc(query.getOrderField());
            } else {
                qw.orderByAsc(query.getOrderField());
            }
        }else {
            qw.orderByDesc("publish_status");
            qw.orderByAsc("sort");
        }
        Long categoryId = query.getCategoryId();
        if (categoryId != null) {
            qw.eq("category_id", categoryId);
        }
        Integer publishStatus = query.getPublishStatus();
        if (publishStatus != null) {
            qw.eq("publish_status", publishStatus);
        }
        String search = query.getSearch();
        if (StringUtils.isNoneEmpty(search)){
            qw.like("name", "%".concat(query.getSearch().trim()).concat("%"));
        }
        if (CollectionUtil.isNotEmpty(query.getExcludeProductIds())) {
            qw.notIn("id",query.getExcludeProductIds());
        }
        if (CollectionUtil.isNotEmpty(query.getIds())) {
            qw.in("id",query.getIds());
        }
        return productMapper.selectList(qw);
    }

    /**
     * 新增商品信息
     *
     * @param productVO 商品信息
     * @return 结果
     */
    @Transactional
    public int insert(ProductVO productVO) {

        Product product = convert.vo2do(productVO);
        product.setCreateTime(LocalDateTime.now());
        List<Sku> skuList = productVO.getSkuList();
        productMapper.insert(product);
        if(skuList!=null){
            skuList.forEach(sku -> {
                sku.setProductId(product.getId());
                sku.setCreateTime(LocalDateTime.now());
                skuMapper.insert(sku);
            });
        }
        return 1;
    }

    /**
     * 修改商品信息
     *
     * @param productVO 商品信息
     * @return 结果
     */
    @Transactional
    public int update(ProductVO productVO) {
        Product dbProduct = productMapper.selectById(productVO.getId());
        List<Long> idList = productVO.getSkuList().stream().filter(it -> it.getId() != null).map(it -> it.getId()).collect(Collectors.toList());
        if (dbProduct == null) {
            return 0;
        }
        Long userId = SecurityUtils.getUserId();
        Product product = convert.vo2do(productVO);
        List<Sku> skuList = productVO.getSkuList();
        product.setUpdateBy(userId);
        product.setUpdateTime(LocalDateTime.now());
        productMapper.updateById(product);
        //查找库中所有的sku
        Map<String,Object> map = new HashMap<>();
        map.put("product_id", product.getId());
        Map<Long, Sku> skuMap = skuMapper.selectByMap(map).stream().collect(Collectors.toMap(it -> it.getId(), it -> it));
        //针对已有的进行编辑
        List<Sku> updateList = productVO.getSkuList().stream().filter(it -> it.getId() != null).collect(Collectors.toList());
        if (!CollectionUtil.isEmpty(updateList)) {
            log.info("共有{}个sku需要修改，{}，productId：{}",updateList.size(), JSONUtil.toJsonStr(updateList),productVO.getId());
            updateList.forEach(it->{
                Sku sku = skuMap.get(it.getId());
                sku.setUpdateBy(SecurityUtils.getUserId());
                sku.setUpdateTime(LocalDateTime.now());
                sku.setPrice(it.getPrice());
                sku.setSpData(it.getSpData());
                sku.setPic(it.getPic());
                sku.setOutSkuId(it.getOutSkuId());
                sku.setStock(it.getStock());
                skuMapper.updateById(sku);
            });
        }
        //针对没有的进行新增
        List<Sku> addList = productVO.getSkuList().stream().filter(it -> it.getId() == null).collect(Collectors.toList());
        if (!CollectionUtil.isEmpty(addList)) {
            log.info("共有{}个sku需要新增，{}，productId：{}",addList.size(), JSONUtil.toJsonStr(addList),productVO.getId());
            addList.forEach(sku -> {
                sku.setProductId(product.getId());
                sku.setCreateTime(LocalDateTime.now());
                skuMapper.insert(sku);
            });
        }
        //删除
        List<Long> deleteIds = skuMap.keySet().stream().filter(it -> !idList.contains(it)).collect(Collectors.toList());
        if (!CollectionUtil.isEmpty(deleteIds)) {
            log.info("共有{}个sku需要删除，{}，productId：{}",deleteIds.size(), JSONUtil.toJsonStr(deleteIds),productVO.getId());
            skuMapper.deleteBatchIds(deleteIds);
        }
        return 1;
    }

    /**
     * 删除商品信息信息
     *
     * @param id 商品信息主键
     * @return 结果
     */
    public int deleteById(Long id) {
        return productMapper.deleteById(id);
    }

    public ProductDetailVO queryDetail(Long id) {
        ProductDetailVO res = new ProductDetailVO();
        Product d = productMapper.selectById(id);
        res.setProduct(d);
        LambdaQueryWrapper<Sku> qw = new LambdaQueryWrapper<>();
        qw.eq(Sku::getProductId, id);
        res.setSkus(skuMapper.selectList(qw));
        if (d.getBrandId() != null) {
            res.setBrand(brandMapper.selectById(d.getBrandId()));
        }
        return res;
    }
}
