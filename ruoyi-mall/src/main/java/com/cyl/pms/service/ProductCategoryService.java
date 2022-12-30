package com.cyl.pms.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.time.LocalDateTime;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cyl.pms.convert.ProductCategoryConvert;
import com.cyl.pms.pojo.vo.ProductCategoryVO;
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
    @Autowired
    private ProductCategoryConvert convert;

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
            if (node.getParentId() == null || node.getParentId() == 0 ) {
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
}
