package com.cyl.manager.oms.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cyl.manager.oms.domain.Order;
import com.cyl.manager.oms.domain.OrderItem;
import com.cyl.manager.oms.mapper.OrderItemMapper;
import com.cyl.manager.oms.mapper.OrderMapper;
import com.cyl.manager.oms.pojo.request.ManagerAftersaleOrderRequest;
import com.cyl.manager.oms.pojo.vo.ManagerOrderProductVO;
import com.cyl.manager.oms.pojo.vo.ManagerRefundOrderVo;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import com.cyl.manager.oms.mapper.AftersaleMapper;
import com.cyl.manager.oms.domain.Aftersale;
import com.cyl.manager.oms.pojo.query.AftersaleQuery;

/**
 * 订单售后Service业务层处理
 *
 *
 * @author zcc
 */
@Service
public class AftersaleService {
    @Autowired
    private AftersaleMapper aftersaleMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderItemMapper orderItemMapper;

    /**
     * 查询订单售后
     *
     * @param id 订单售后主键
     * @return 订单售后
     */
    public Aftersale selectById(Long id) {
        return aftersaleMapper.selectById(id);
    }

    /**
     * 查询订单售后列表
     *
     * @param query 查询条件
     * @param page 分页条件
     * @return 订单售后
     */
    public List<ManagerRefundOrderVo> selectList(ManagerAftersaleOrderRequest query, Pageable page) {
        if (page != null) {
            PageHelper.startPage(page.getPageNumber() + 1, page.getPageSize());
        }
        List<ManagerRefundOrderVo> managerRefundOrderVos = aftersaleMapper.selectManagerRefundOrder(query);
        if (CollectionUtil.isEmpty(managerRefundOrderVos)){
            return managerRefundOrderVos;
        }
        Set<Long> idSet = managerRefundOrderVos.stream().map(ManagerRefundOrderVo::getOrderId).collect(Collectors.toSet());
        //查一下orderSn集合
        QueryWrapper<Order> orderQw = new QueryWrapper<>();
        orderQw.in("id", idSet);
        Map<Long, Order> orderMap = orderMapper.selectList(orderQw).stream().collect(Collectors.toMap(Order::getId, it -> it));
        //封装售后单商品数据
        QueryWrapper<OrderItem> orderItemQw = new QueryWrapper<>();
        orderItemQw.in("order_id", idSet);
        Map<Long, List<OrderItem>> orderItemMap = orderItemMapper.selectList(orderItemQw).stream().collect(Collectors.groupingBy(OrderItem::getOrderId));
        managerRefundOrderVos.forEach(vo -> {
            Order order = orderMap.get(vo.getOrderId());
            vo.setOrderSn(order.getOrderSn());
            List<OrderItem> orderItemList = orderItemMap.get(vo.getOrderId());
            List<ManagerOrderProductVO> productList = new ArrayList<>();
            orderItemList.forEach(item -> {
                ManagerOrderProductVO productVO = new ManagerOrderProductVO();
                productVO.setProductName(item.getProductName());
                productVO.setSalePrice(item.getSalePrice());
                productVO.setPic(item.getPic());
                productVO.setBuyNum(item.getQuantity());
                productVO.setProductId(item.getProductId());
                productVO.setSpData(item.getSpData());
                productList.add(productVO);
            });
            vo.setProductList(productList);
        });
        return managerRefundOrderVos;
    }

    /**
     * 新增订单售后
     *
     * @param aftersale 订单售后
     * @return 结果
     */
    public int insert(Aftersale aftersale) {
        aftersale.setCreateTime(LocalDateTime.now());
        return aftersaleMapper.insert(aftersale);
    }

    /**
     * 修改订单售后
     *
     * @param aftersale 订单售后
     * @return 结果
     */
    public int update(Aftersale aftersale) {
        return aftersaleMapper.updateById(aftersale);
    }

    /**
     * 删除订单售后信息
     *
     * @param id 订单售后主键
     * @return 结果
     */
    public int deleteById(Long id) {
        return aftersaleMapper.deleteById(id);
    }
}
