package com.cyl.manager.pms.service;

import java.util.*;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cyl.h5.pojo.vo.ProductDetail;
import com.cyl.manager.pms.convert.ProductConvert;
import com.cyl.manager.pms.domain.Sku;
import com.cyl.manager.pms.mapper.BrandMapper;
import com.cyl.manager.pms.mapper.SkuMapper;
import com.cyl.manager.pms.pojo.vo.ProductVO;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import com.cyl.manager.pms.mapper.ProductMapper;
import com.cyl.manager.pms.domain.Product;
import com.cyl.manager.pms.pojo.query.ProductQuery;
import org.springframework.transaction.annotation.Transactional;

/**
 * 商品信息Service业务层处理
 *
 *
 * @author zcc
 */
@Service
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
        Product product = convert.vo2do(productVO);
        List<Sku> skuList = productVO.getSkuList();
        productMapper.updateById(product);
        Map<String,Object> map = new HashMap<>();
        map.put("product_id", product.getId());
        skuMapper.deleteByMap(map);
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
     * 删除商品信息信息
     *
     * @param id 商品信息主键
     * @return 结果
     */
    public int deleteById(Long id) {
        return productMapper.deleteById(id);
    }

    public ProductDetail queryDetail(Long id) {
        ProductDetail res = new ProductDetail();
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
