package com.cyl.manager.pms.mapper;

import java.time.LocalDateTime;
import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cyl.manager.pms.domain.entity.Sku;
import org.apache.ibatis.annotations.Param;

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

    int updateStockById(@Param("skuId")Long skuId, @Param("optDate")LocalDateTime optDate, @Param("quantity")Integer quantity);
}
