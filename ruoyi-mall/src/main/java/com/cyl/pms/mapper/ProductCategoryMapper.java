package com.cyl.pms.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import com.cyl.pms.domain.ProductCategory;

/**
 * 商品分类Mapper接口
 * 
 * @author zcc
 */
public interface ProductCategoryMapper extends BaseMapper<ProductCategory> {
    /**
     * 查询商品分类列表
     *
     * @param productCategory 商品分类
     * @return 商品分类集合
     */
    List<ProductCategory> selectByEntity(ProductCategory productCategory);

    /**
     * 批量软删除
     * @param ids
     * @return
    */
    int updateDelFlagByIds(@Param("ids") Long[] ids);
}
