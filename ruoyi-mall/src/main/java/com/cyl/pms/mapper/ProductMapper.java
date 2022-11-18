package com.cyl.pms.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import com.cyl.pms.domain.Product;

/**
 * 商品信息Mapper接口
 * 
 * @author zcc
 */
public interface ProductMapper extends BaseMapper<Product> {
    /**
     * 查询商品信息列表
     *
     * @param product 商品信息
     * @return 商品信息集合
     */
    List<Product> selectByEntity(Product product);

    /**
     * 批量软删除
     * @param ids
     * @return
    */
    int updateDelFlagByIds(@Param("ids") Long[] ids);
}
