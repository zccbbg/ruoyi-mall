package com.cyl.manager.oms.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.cyl.manager.oms.domain.Order;
import com.cyl.manager.oms.domain.OrderItem;
import com.cyl.manager.oms.domain.OrderOperateHistory;
import com.cyl.manager.oms.mapper.OrderItemMapper;
import com.cyl.manager.oms.mapper.OrderMapper;
import com.cyl.manager.oms.mapper.OrderOperateHistoryMapper;
import com.cyl.manager.oms.pojo.request.DealWithAftersaleRequest;
import com.cyl.manager.oms.pojo.request.ManagerAftersaleOrderRequest;
import com.cyl.manager.oms.pojo.vo.ManagerOrderProductVO;
import com.cyl.manager.oms.pojo.vo.ManagerRefundOrderVo;
import com.github.pagehelper.PageHelper;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.enums.AftersaleStatus;
import com.ruoyi.common.enums.OrderRefundStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import com.cyl.manager.oms.mapper.AftersaleMapper;
import com.cyl.manager.oms.domain.Aftersale;
import com.cyl.manager.oms.pojo.query.AftersaleQuery;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private OrderOperateHistoryMapper orderOperateHistoryMapper;

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

    /**
     * 售后处理
     * @param request 请求体
     * @param user 操作人
     * @return
     */
    @Transactional
    public String dealWith(DealWithAftersaleRequest request, LoginUser user) {
        Order order = orderMapper.selectById(request.getOrderId());
        if (order == null){
            throw new RuntimeException("无该订单");
        }
        //目前售后类型只有退款，没有退货退款
        //所以目前只需要查看是否有待处理的售后单
        QueryWrapper<Aftersale> aftersaleQw = new QueryWrapper<>();
        aftersaleQw.eq("status", AftersaleStatus.APPLY.getType());
        Aftersale aftersale = aftersaleMapper.selectOne(aftersaleQw);
        if (aftersale == null){
            throw new RuntimeException("没有售后单");
        }
        //售后状态与售后类型是否对应
        boolean ifAgree = Constants.OptType.AGREE.equals(request.getOptType());
        boolean ifRefuse = Constants.OptType.REFUSE.equals(request.getOptType());
        if (ifAgree || ifRefuse){
            if (!AftersaleStatus.APPLY.getType().equals(aftersale.getStatus())){
                throw new RuntimeException("订单状态有误，请刷新后重试");
            }
        }
        //拒绝则理由必填
        if (ifRefuse && StrUtil.isBlank(request.getRemark())){
            throw new RuntimeException("请填写拒绝理由");
        }
        LocalDateTime optDate = LocalDateTime.now();
        //要创建的订单操作记录，status后续判断再设置
        OrderOperateHistory optHistory = new OrderOperateHistory();
        optHistory.setOrderId(order.getId());
        optHistory.setOperateMan("后台管理员");
        optHistory.setCreateTime(optDate);
        optHistory.setCreateBy(user.getUserId());
        optHistory.setUpdateBy(user.getUserId());
        optHistory.setUpdateTime(optDate);
        //封装售后wrapper
        UpdateWrapper<Aftersale> aftersaleWrapper = new UpdateWrapper<>();
        aftersaleWrapper.eq("order_id", request.getOrderId());
        aftersaleWrapper.set("handle_man", user.getUser().getNickName());
        aftersaleWrapper.set("update_time", optDate);
        aftersaleWrapper.set("handle_time", optDate);
        aftersaleWrapper.set("update_by", user.getUserId());
        //封装订单wrapper
        UpdateWrapper<Order> orderWrapper = new UpdateWrapper<>();
        orderWrapper.eq("id", request.getOrderId());
        orderWrapper.set("update_time", optDate);
        orderWrapper.set("update_by", user.getUserId());
        //更新订单、售后单，创建操作记录
        if (ifRefuse){
            aftersaleWrapper.set("status", AftersaleStatus.REJECT.getType());
            aftersaleWrapper.set("handle_note", request.getRemark());
            orderWrapper.set("aftersale_status", OrderRefundStatus.NO_REFUND.getType());
            optHistory.setOrderStatus(14);
        }else if (ifAgree){
            aftersaleWrapper.set("status", AftersaleStatus.WAIT.getType());
            orderWrapper.set("aftersale_status", OrderRefundStatus.WAIT.getType());
            optHistory.setOrderStatus(12);
        }
        int rows = aftersaleMapper.update(null, aftersaleWrapper);
        if (rows < 1){
            throw new RuntimeException("更新售后单失败");
        }
        rows = orderMapper.update(null, orderWrapper);
        if (rows < 1){
            throw new RuntimeException("更新订单失败");
        }
        rows = orderOperateHistoryMapper.insert(optHistory);
        if (rows < 1){
            throw new RuntimeException("创建订单操作记录失败");
        }
        return "操作成功";
    }
}
