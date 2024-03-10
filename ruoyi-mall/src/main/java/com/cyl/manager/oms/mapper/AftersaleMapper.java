package com.cyl.manager.oms.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cyl.manager.oms.domain.entity.Aftersale;
import com.cyl.manager.oms.domain.form.ManagerAftersaleOrderForm;
import com.cyl.manager.oms.domain.vo.ManagerRefundOrderVO;
import com.cyl.manager.statistics.domain.vo.OrderAndAftersaleStatisticsVO;

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

    List<ManagerRefundOrderVO> selectManagerRefundOrder(ManagerAftersaleOrderForm managerAftersaleOrderPageRequest);

    int countByMemberId(Long memberId);

    OrderAndAftersaleStatisticsVO statPendingAndProcessing();
}
