package com.cyl.manager.pms.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cyl.manager.pms.domain.Sku;

/**
 * sku信息Mapper接口
 * 
 * @author zcc
 */
public interface SkuMapper extends BaseMapper<Sku> {
    /**
     * 查询sku信息列表
     *
     * @param sku sku信息
     * @return sku信息集合
     */
    List<Sku> selectByEntity(Sku sku);
}
