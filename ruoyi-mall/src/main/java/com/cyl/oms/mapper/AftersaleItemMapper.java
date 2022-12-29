package com.cyl.oms.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import com.cyl.oms.domain.AftersaleItem;

/**
 * 订单售后Mapper接口
 * 
 * @author zcc
 */
public interface AftersaleItemMapper extends BaseMapper<AftersaleItem> {
    /**
     * 查询订单售后列表
     *
     * @param aftersaleItem 订单售后
     * @return 订单售后集合
     */
    List<AftersaleItem> selectByEntity(AftersaleItem aftersaleItem);
}
