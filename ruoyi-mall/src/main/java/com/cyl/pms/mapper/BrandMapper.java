package com.cyl.pms.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import com.cyl.pms.domain.Brand;

/**
 * 品牌管理Mapper接口
 * 
 * @author zcc
 */
public interface BrandMapper extends BaseMapper<Brand> {
    /**
     * 查询品牌管理列表
     *
     * @param brand 品牌管理
     * @return 品牌管理集合
     */
    List<Brand> selectByEntity(Brand brand);
}
