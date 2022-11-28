package com.cyl.pms.service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import com.cyl.pms.mapper.ProductMapper;
import com.cyl.pms.domain.Product;
import com.cyl.pms.pojo.query.ProductQuery;

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

    /**
     * 查询商品信息
     *
     * @param id 商品信息主键
     * @return 商品信息
     */
    public Product selectById(Long id) {
        return productMapper.selectById(id);
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
     * @param product 商品信息
     * @return 结果
     */
    public int insert(Product product) {
        product.setCreateTime(LocalDateTime.now());
        return productMapper.insert(product);
    }

    /**
     * 修改商品信息
     *
     * @param product 商品信息
     * @return 结果
     */
    public int update(Product product) {
        return productMapper.updateById(product);
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
