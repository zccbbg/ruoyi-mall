package com.cyl.manager.oms.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cyl.manager.oms.domain.entity.WechatPaymentHistory;

/**
 * 微信订单表Mapper接口
 * 
 * @author zcc
 */
public interface WechatPaymentHistoryMapper extends BaseMapper<WechatPaymentHistory> {
    /**
     * 查询微信订单表列表
     *
     * @param wechatPaymentHistory 微信订单表
     * @return 微信订单表集合
     */
    List<WechatPaymentHistory> selectByEntity(WechatPaymentHistory wechatPaymentHistory);
}
