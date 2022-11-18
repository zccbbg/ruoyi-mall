package com.cyl.pms.service;

import java.util.Arrays;
import java.util.List;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import com.cyl.pms.mapper.ProductCategoryMapper;
import com.cyl.pms.domain.ProductCategory;
import com.cyl.pms.pojo.query.ProductCategoryQuery;

/**
 * 商品分类Service业务层处理
 *
 *
 * @author zcc
 */
@Service
public class ProductCategoryService {
    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    /**
     * 查询商品分类
     *
     * @param id 商品分类主键
     * @return 商品分类
     */
    public ProductCategory selectById(Long id) {
        return productCategoryMapper.selectById(id);
    }

    /**
     * 查询商品分类列表
     *
     * @param query 查询条件
     * @param page 分页条件
     * @return 商品分类
     */
    public List<ProductCategory> selectList(ProductCategoryQuery query, Pageable page) {
        if (page != null) {
            PageHelper.startPage(page.getPageNumber() + 1, page.getPageSize());
        }
        QueryWrapper<ProductCategory> qw = new QueryWrapper<>();
        qw.eq("del_flag",0);
        Long parentId = query.getParentId();
        if (parentId != null) {
            qw.eq("parent_id", parentId);
        }
        String nameLike = query.getNameLike();
        if (!StringUtils.isEmpty(nameLike)) {
            qw.like("name", nameLike);
        }
        Integer level = query.getLevel();
        if (level != null) {
            qw.eq("level", level);
        }
        Integer showStatus = query.getShowStatus();
        if (showStatus != null) {
            qw.eq("show_status", showStatus);
        }
        Integer sort = query.getSort();
        if (sort != null) {
            qw.eq("sort", sort);
        }
        String icon = query.getIcon();
        if (!StringUtils.isEmpty(icon)) {
            qw.eq("icon", icon);
        }
        return productCategoryMapper.selectList(qw);
    }

    /**
     * 新增商品分类
     *
     * @param productCategory 商品分类
     * @return 结果
     */
    public int insert(ProductCategory productCategory) {
        productCategory.setDelFlag(0);
        productCategory.setCreateTime(LocalDateTime.now());
        return productCategoryMapper.insert(productCategory);
    }

    /**
     * 修改商品分类
     *
     * @param productCategory 商品分类
     * @return 结果
     */
    public int update(ProductCategory productCategory) {
        return productCategoryMapper.updateById(productCategory);
    }

    /**
     * 批量删除商品分类
     *
     * @param ids 需要删除的商品分类主键
     * @return 结果
     */
    public int deleteByIds(Long[] ids) {
        return productCategoryMapper.updateDelFlagByIds(ids);
    }

    /**
     * 删除商品分类信息
     *
     * @param id 商品分类主键
     * @return 结果
     */
    public int deleteById(Long id) {
        Long[] ids = {id};
        return productCategoryMapper.updateDelFlagByIds(ids);
    }
}
