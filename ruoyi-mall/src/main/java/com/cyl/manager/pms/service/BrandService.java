package com.cyl.manager.pms.service;

import java.util.List;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import com.cyl.manager.pms.mapper.BrandMapper;
import com.cyl.manager.pms.domain.entity.Brand;
import com.cyl.manager.pms.domain.query.BrandQuery;

/**
 * 品牌管理Service业务层处理
 *
 *
 * @author zcc
 */
@Service
public class BrandService {
    @Autowired
    private BrandMapper brandMapper;

    /**
     * 查询品牌管理
     *
     * @param id 品牌管理主键
     * @return 品牌管理
     */
    public Brand selectById(Long id) {
        return brandMapper.selectById(id);
    }

    /**
     * 查询品牌管理列表
     *
     * @param query 查询条件
     * @param page 分页条件
     * @return 品牌管理
     */
    public List<Brand> selectList(BrandQuery query, Pageable page) {
        if (page != null) {
            PageHelper.startPage(page.getPageNumber() + 1, page.getPageSize());
        }
        QueryWrapper<Brand> qw = new QueryWrapper<>();
        String nameLike = query.getNameLike();
        if (!StringUtils.isEmpty(nameLike)) {
            qw.like("name", nameLike);
        }
        Integer sort = query.getSort();
        if (sort != null) {
            qw.eq("sort", sort);
        }
        Integer showStatus = query.getShowStatus();
        if (showStatus != null) {
            qw.eq("show_status", showStatus);
        }
        String logo = query.getLogo();
        if (!StringUtils.isEmpty(logo)) {
            qw.eq("logo", logo);
        }
        qw.orderByAsc("sort");
        return brandMapper.selectList(qw);
    }

    /**
     * 新增品牌管理
     *
     * @param brand 品牌管理
     * @return 结果
     */
    public int insert(Brand brand) {
        brand.setCreateTime(LocalDateTime.now());
        return brandMapper.insert(brand);
    }

    /**
     * 修改品牌管理
     *
     * @param brand 品牌管理
     * @return 结果
     */
    public int update(Brand brand) {
        return brandMapper.updateById(brand);
    }

    /**
     * 删除品牌管理信息
     *
     * @param id 品牌管理主键
     * @return 结果
     */
    public int deleteById(Long id) {
        return brandMapper.deleteById(id);
    }
}
