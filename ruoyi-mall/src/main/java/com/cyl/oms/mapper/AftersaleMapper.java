package com.cyl.oms.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import com.cyl.oms.domain.Aftersale;

/**
 * 订单售后Mapper接口
 * 
 * @author zcc
 */
public interface AftersaleMapper extends BaseMapper<Aftersale> {
    /**
     * 查询订单售后列表
     *
     * @param aftersale 订单售后
     * @return 订单售后集合
     */
    List<Aftersale> selectByEntity(Aftersale aftersale);
}
