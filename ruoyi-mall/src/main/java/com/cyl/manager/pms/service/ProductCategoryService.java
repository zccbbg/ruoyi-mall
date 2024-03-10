package com.cyl.manager.pms.service;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cyl.h5.domain.dto.CategoryDTO;
import com.cyl.manager.pms.convert.ProductCategoryConvert;
import com.cyl.manager.pms.convert.ProductConvert;
import com.cyl.manager.pms.domain.entity.Product;
import com.cyl.manager.pms.domain.entity.ProductCategory;
import com.cyl.manager.pms.mapper.ProductCategoryMapper;
import com.cyl.manager.pms.mapper.ProductMapper;
import com.cyl.manager.pms.domain.query.ProductCategoryQuery;
import com.cyl.manager.pms.domain.vo.ProductCategoryVO;
import com.github.pagehelper.PageHelper;
import com.ruoyi.common.exception.base.BaseException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 商品分类Service业务层处理
 *
 * @author zcc
 */
@Service
public class ProductCategoryService {
    @Autowired
    private ProductCategoryMapper productCategoryMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProductCategoryConvert convert;
    @Autowired
    private ProductConvert productConvert;

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
     * @param page  分页条件
     * @return 商品分类
     */
    public List<ProductCategoryVO> selectList(ProductCategoryQuery query, Pageable page) {
        if (page != null) {
            PageHelper.startPage(page.getPageNumber() + 1, page.getPageSize());
        }
        QueryWrapper<ProductCategory> qw = new QueryWrapper<>();
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
        qw.orderByAsc("sort");

        List<ProductCategory> productCategories = productCategoryMapper.selectList(qw);
        List<ProductCategoryVO> productCategoryVOS = convert.dos2vos(productCategories);
        return formatTree(productCategoryVOS);

    }

    private List<ProductCategoryVO> formatTree(List<ProductCategoryVO> nodes) {
        List<ProductCategoryVO> tree = new ArrayList<>();
        List<ProductCategoryVO> children = new ArrayList<>();
        // 1）先获取到所有根节点
        for (ProductCategoryVO node : nodes) {
            if (node.getParentId() == null || node.getParentId() == 0) {
                tree.add(node);
            } else {
                children.add(node);
            }
        }
        // 2）把所有除根结点外的节点作为子节点，然后遍历每一个根节点
        for (ProductCategoryVO node : tree) {
            // 3）递归构建此根的子节点
            recur(node, children);
        }
        return tree;
    }

    private void recur(ProductCategoryVO rootNode, List<ProductCategoryVO> children) {
        // 1）遍历剩余子节点，找出当前根的子节点
        for (ProductCategoryVO node : children) {
            // 2）如果子节点的父id等于根节点的id，那么就将这个节点加到根节点的children列表中
            if (rootNode.getId() == node.getParentId()) {
                if (rootNode.getChildren() == null) {
                    rootNode.setChildren(new ArrayList<>());
                }
                rootNode.getChildren().add(node);
                // 3）以当前节点作为根节点进行递归，检查是否还有子节点。
                recur(node, children);
            }
        }
    }


    /**
     * 新增商品分类
     *
     * @param productCategory 商品分类
     * @return 结果
     */
    public int insert(ProductCategory productCategory) {
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
     * 删除商品分类信息
     *
     * @param id 商品分类主键
     * @return 结果
     */
    public int deleteById(Long id) {
        return productCategoryMapper.deleteById(id);
    }

    public List<CategoryDTO> queryCategoryWithProductsForH5() {
        QueryWrapper<ProductCategory> qw1 = new QueryWrapper<>();
//        qw1.eq("level", 0);
        qw1.eq("show_status", 1);
        Page<ProductCategory> pageReq = new Page<>();
        pageReq.setCurrent(1L)
                .setSize(10)
                .setOrders(Collections.singletonList(OrderItem.desc("sort")));
        List<ProductCategory> categories = productCategoryMapper.selectPage(pageReq, qw1).getRecords();
        if (CollUtil.isEmpty(categories)) {
            return Collections.emptyList();
        }
        ;
        return categories.stream().map(it -> {
            CategoryDTO dto = convert.do2dto(it);
            // 寻找该分类下的所有子类
            List<Long> allChildCate = queryAllChildCate(Collections.singletonList(it.getId()), 0);
            QueryWrapper<Product> qw = new QueryWrapper<>();
            qw.select("id", "pic", "name", "price", "category_id");
            qw.in("category_id", allChildCate);
            qw.le("sort", 100);
            List<Product> categoryId2List = productMapper.selectList(qw);
            dto.setProductList(productConvert.dos2dtos(categoryId2List));
            return dto;
        }).collect(Collectors.toList());
    }

    private List<Long> queryAllChildCate(List<Long> categoryIds, int level) {
        List<Long> res = new ArrayList<>();
        QueryWrapper<ProductCategory> qw = new QueryWrapper<>();
        qw.select("id");
        List<Long> ids = categoryIds;
        while (true) {
            qw.clear();
            qw.in("parent_id", ids);
            qw.eq("level", level + 1);
            qw.eq("show_status", 1);
            ids = productCategoryMapper.selectList(qw).stream().map(ProductCategory::getId).collect(Collectors.toList());
            if (CollUtil.isEmpty(ids)) {
                break;
            }
            res.addAll(ids);
            level++;
        }
        res.addAll(categoryIds);
        return res;
    }

    public List<ProductCategory> h5Categories() {
        QueryWrapper<ProductCategory> qw = new QueryWrapper<>();
        qw.select("id", "parent_id", "name", "level", "sort", "icon");
        qw.eq("show_status", 1);
//        qw.le("level", 2);
        return productCategoryMapper.selectList(qw);
    }

    public List<ProductCategory> getBrotherAndChild(Long id, boolean withChild) {
        ProductCategory category = productCategoryMapper.selectById(id);
        if (category == null) {
            throw new BaseException("参数错误");
        }
        LambdaQueryWrapper<ProductCategory> qw = new LambdaQueryWrapper<>();
        qw.eq(ProductCategory::getParentId, category.getParentId());
        qw.eq(ProductCategory::getLevel, category.getLevel());
        qw.eq(ProductCategory::getShowStatus, 1);
        qw.select(ProductCategory::getId, ProductCategory::getParentId, ProductCategory::getName, ProductCategory::getLevel, ProductCategory::getSort, ProductCategory::getIcon);
        List<ProductCategory> res = productCategoryMapper.selectList(qw);
        if (withChild) {
            qw.clear();
            qw.eq(ProductCategory::getParentId, category.getId());
            qw.eq(ProductCategory::getLevel, category.getLevel() + 1);
            qw.eq(ProductCategory::getShowStatus, 1);
            List<ProductCategory> childs = productCategoryMapper.selectList(qw);
            res.addAll(childs);
        }
        if (category.getParentId() != null && category.getParentId() != -1) {
            res.add(productCategoryMapper.selectById(category.getParentId()));
        }
        return res;
    }
}
