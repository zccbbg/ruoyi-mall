package com.cyl.pms.service;

import java.math.BigDecimal;
import java.util.*;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import com.cyl.pms.convert.ProductConvert;
import com.cyl.pms.domain.Sku;
import com.cyl.pms.mapper.SkuMapper;
import com.cyl.pms.pojo.vo.ProductVO;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import com.cyl.pms.mapper.ProductMapper;
import com.cyl.pms.domain.Product;
import com.cyl.pms.pojo.query.ProductQuery;
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
        qw.orderByDesc("publish_status");
        qw.orderByAsc("sort");
        Long brandId = query.getBrandId();
        if (brandId != null) {
            qw.eq("brand_id", brandId);
        }
        Long categoryId = query.getCategoryId();
        if (categoryId != null) {
            qw.eq("category_id", categoryId);
        }
        String outProductId = query.getOutProductId();
        if (!StringUtils.isEmpty(outProductId)) {
            qw.eq("out_product_id", outProductId);
        }
        String nameLike = query.getNameLike();
        if (!StringUtils.isEmpty(nameLike)) {
            qw.like("name", nameLike);
        }
        String pic = query.getPic();
        if (!StringUtils.isEmpty(pic)) {
            qw.eq("pic", pic);
        }
        String albumPics = query.getAlbumPics();
        if (!StringUtils.isEmpty(albumPics)) {
            qw.eq("album_pics", albumPics);
        }
        Integer publishStatus = query.getPublishStatus();
        if (publishStatus != null) {
            qw.eq("publish_status", publishStatus);
        }
        Integer sort = query.getSort();
        if (sort != null) {
            qw.eq("sort", sort);
        }
        BigDecimal price = query.getPrice();
        if (price != null) {
            qw.eq("price", price);
        }
        String unit = query.getUnit();
        if (!StringUtils.isEmpty(unit)) {
            qw.eq("unit", unit);
        }
        BigDecimal weight = query.getWeight();
        if (weight != null) {
            qw.eq("weight", weight);
        }
        String detailHtml = query.getDetailHtml();
        if (!StringUtils.isEmpty(detailHtml)) {
            qw.eq("detail_html", detailHtml);
        }
        String detailMobileHtml = query.getDetailMobileHtml();
        if (!StringUtils.isEmpty(detailMobileHtml)) {
            qw.eq("detail_mobile_html", detailMobileHtml);
        }
        String brandNameLike = query.getBrandNameLike();
        if (!StringUtils.isEmpty(brandNameLike)) {
            qw.like("brand_name", brandNameLike);
        }
        String productCategoryNameLike = query.getProductCategoryNameLike();
        if (!StringUtils.isEmpty(productCategoryNameLike)) {
            qw.like("product_category_name", productCategoryNameLike);
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
}
