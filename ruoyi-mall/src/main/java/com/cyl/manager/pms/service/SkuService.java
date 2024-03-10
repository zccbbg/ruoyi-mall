package com.cyl.manager.pms.service;

import java.math.BigDecimal;
import java.util.List;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import com.cyl.manager.pms.mapper.SkuMapper;
import com.cyl.manager.pms.domain.entity.Sku;
import com.cyl.manager.pms.domain.query.SkuQuery;

/**
 * sku信息Service业务层处理
 *
 *
 * @author zcc
 */
@Service
public class SkuService {
    @Autowired
    private SkuMapper skuMapper;

    /**
     * 查询sku信息
     *
     * @param id sku信息主键
     * @return sku信息
     */
    public Sku selectById(Long id) {
        return skuMapper.selectById(id);
    }

    /**
     * 查询sku信息列表
     *
     * @param query 查询条件
     * @param page 分页条件
     * @return sku信息
     */
    public List<Sku> selectList(SkuQuery query, Pageable page) {
        if (page != null) {
            PageHelper.startPage(page.getPageNumber() + 1, page.getPageSize());
        }
        QueryWrapper<Sku> qw = new QueryWrapper<>();
        Long productId = query.getProductId();
        if (productId != null) {
            qw.eq("product_id", productId);
        }
        String outSkuId = query.getOutSkuId();
        if (!StringUtils.isEmpty(outSkuId)) {
            qw.eq("out_sku_id", outSkuId);
        }
        BigDecimal price = query.getPrice();
        if (price != null) {
            qw.eq("price", price);
        }
        String pic = query.getPic();
        if (!StringUtils.isEmpty(pic)) {
            qw.eq("pic", pic);
        }
        String spData = query.getSpData();
        if (!StringUtils.isEmpty(spData)) {
            qw.eq("sp_data", spData);
        }
        return skuMapper.selectList(qw);
    }

    /**
     * 新增sku信息
     *
     * @param sku sku信息
     * @return 结果
     */
    public int insert(Sku sku) {
        sku.setCreateTime(LocalDateTime.now());
        return skuMapper.insert(sku);
    }

    /**
     * 修改sku信息
     *
     * @param sku sku信息
     * @return 结果
     */
    public int update(Sku sku) {
        return skuMapper.updateById(sku);
    }

    /**
     * 删除sku信息信息
     *
     * @param id sku信息主键
     * @return 结果
     */
    public int deleteById(Long id) {
        return skuMapper.deleteById(id);
    }
}
