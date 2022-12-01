package com.cyl.oms.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import com.cyl.oms.domain.RefundItem;

/**
 * 订单售后Mapper接口
 * 
 * @author zcc
 */
public interface RefundItemMapper extends BaseMapper<RefundItem> {
    /**
     * 查询订单售后列表
     *
     * @param refundItem 订单售后
     * @return 订单售后集合
     */
    List<RefundItem> selectByEntity(RefundItem refundItem);
}
