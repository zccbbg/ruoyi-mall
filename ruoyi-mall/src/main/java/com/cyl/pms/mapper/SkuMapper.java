package com.cyl.pms.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import com.cyl.pms.domain.Sku;

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

    /**
     * 批量软删除
     * @param ids
     * @return
    */
    int updateDelFlagByIds(@Param("ids") Long[] ids);
}
