package com.cyl.manager.oms.mapper;

import java.time.LocalDateTime;
import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cyl.h5.domain.vo.CountOrderVO;
import com.cyl.h5.domain.vo.H5OrderVO;
import com.cyl.manager.aws.domain.entity.SystemStatistics;
import com.cyl.manager.oms.domain.entity.Order;
import com.cyl.manager.oms.domain.form.ManagerOrderQueryForm;
import com.cyl.manager.oms.domain.vo.ManagerOrderVO;
import com.cyl.manager.statistics.domain.vo.OrderAndAftersaleStatisticsVO;
import com.cyl.manager.ums.domain.vo.MemberDataStatisticsVO;
import org.apache.ibatis.annotations.Param;

/**
 * 订单表Mapper接口
 * 
 * @author zcc
 */
public interface OrderMapper extends BaseMapper<Order> {
    /**
     * 查询订单表列表
     *
     * @param order 订单表
     * @return 订单表集合
     */
    List<Order> selectByEntity(Order order);

    List<ManagerOrderVO> selectManagerOrderPage(ManagerOrderQueryForm request);

    List<H5OrderVO> orderPage(@Param("status") Integer status, @Param("memberId")Long memberId);

    H5OrderVO selectOrderDetail(Long orderId);

    CountOrderVO countByStatusAndMemberId(Long memberId);

    Integer cancelBatch(@Param("list") List<Order> orderList);

    MemberDataStatisticsVO statOrderCountAndAmount(Long memberId);


    Integer statWaitDelivered();

    OrderAndAftersaleStatisticsVO statTodayData(@Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime);

    SystemStatistics statNewAndDeal(@Param("startTime") LocalDateTime startTime,@Param("endTime") LocalDateTime endTime);

    int statDealMember(@Param("startTime") LocalDateTime startTime,@Param("endTime") LocalDateTime endTime);
}
