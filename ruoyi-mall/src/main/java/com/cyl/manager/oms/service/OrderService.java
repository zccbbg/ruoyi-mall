package com.cyl.manager.oms.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.cyl.h5.pojo.dto.OrderCreateDTO;
import com.cyl.h5.pojo.dto.OrderProductListDTO;
import com.cyl.h5.pojo.vo.OrderCalcVO;
import com.cyl.h5.pojo.vo.SkuViewDTO;
import com.cyl.h5.pojo.vo.form.OrderSubmitForm;
import com.cyl.h5.pojo.vo.query.OrderH5Query;
import com.cyl.manager.oms.convert.OrderConvert;
import com.cyl.manager.oms.domain.OrderItem;
import com.cyl.manager.oms.domain.OrderOperateHistory;
import com.cyl.manager.oms.mapper.OrderItemMapper;
import com.cyl.manager.oms.mapper.OrderOperateHistoryMapper;
import com.cyl.manager.oms.pojo.request.ManagerOrderQueryRequest;
import com.cyl.manager.oms.pojo.vo.*;
import com.cyl.manager.pms.convert.SkuConvert;
import com.cyl.manager.pms.domain.Product;
import com.cyl.manager.pms.domain.Sku;
import com.cyl.manager.pms.mapper.ProductMapper;
import com.cyl.manager.pms.mapper.SkuMapper;
import com.cyl.manager.ums.domain.Member;
import com.cyl.manager.ums.domain.MemberAddress;
import com.cyl.manager.ums.domain.MemberCart;
import com.cyl.manager.ums.mapper.MemberAddressMapper;
import com.cyl.manager.ums.mapper.MemberCartMapper;
import com.cyl.manager.ums.mapper.MemberMapper;
import com.github.pagehelper.PageHelper;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.AesCryptoUtils;
import com.ruoyi.common.utils.IDGenerator;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.config.LocalDataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import com.cyl.manager.oms.mapper.OrderMapper;
import com.cyl.manager.oms.domain.Order;
import com.cyl.manager.oms.pojo.query.OrderQuery;
import org.springframework.transaction.annotation.Transactional;

/**
 * 订单表Service业务层处理
 *
 *
 * @author zcc
 */
@Service
public class OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderConvert orderConvert;
    @Autowired
    private OrderItemMapper orderItemMapper;
    @Autowired
    private MemberAddressMapper memberAddressMapper;
    @Autowired
    private SkuMapper skuMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private SkuConvert skuConvert;
    @Autowired
    private OrderItemService orderItemService;
    @Autowired
    private OrderOperateHistoryMapper orderOperateHistoryMapper;
    @Autowired
    private MemberCartMapper memberCartMapper;
    @Autowired
    private MemberMapper memberMapper;
    @Value("${aes.key}")
    private String aesKey;

    /**
     * 查询订单表
     *
     * @param id 订单表主键
     * @return 订单表
     */
    public ManagerOrderDetailVO selectById(Long id) {
        Order order = orderMapper.selectById(id);
        if (order == null){
            throw new RuntimeException("查不到订单信息");
        }
        ManagerOrderDetailVO managerOrderDetailVO = new ManagerOrderDetailVO();
        //封装订单信息
        managerOrderDetailVO.setOrderId(id);
        managerOrderDetailVO.setOrderSn(order.getOrderSn());
        managerOrderDetailVO.setOrderStatus(order.getStatus());
        managerOrderDetailVO.setCreateTime(order.getCreateTime());
        managerOrderDetailVO.setDeliveryTime(order.getDeliveryTime());
        managerOrderDetailVO.setExpressName(order.getDeliveryCompany());
        managerOrderDetailVO.setExpressNo(order.getDeliverySn());
        managerOrderDetailVO.setPayAmount(order.getPayAmount());
        managerOrderDetailVO.setPayTime(order.getPaymentTime());
        managerOrderDetailVO.setPayType(order.getPayType());
        managerOrderDetailVO.setTotalAmount(order.getTotalAmount());
        managerOrderDetailVO.setPayAmount(order.getPayAmount());
        //封装订单地址信息
        ManagerOrderAddressVo managerOrderAddressVo = new ManagerOrderAddressVo();
        managerOrderAddressVo.setUserPhone(order.getReceiverPhone());
        managerOrderAddressVo.setAddress(order.getReceiverDetailAddress());
        managerOrderAddressVo.setArea(
                order.getReceiverProvince() +
                order.getReceiverCity() +
                order.getReceiverDistrict());
        managerOrderAddressVo.setName(order.getReceiverName());
        managerOrderDetailVO.setAddressInfo(managerOrderAddressVo);
        //查询会员信息
        Member member = memberMapper.selectById(order.getMemberId());
        managerOrderDetailVO.setUserName(member.getNickname());
        managerOrderDetailVO.setUserPhone(member.getPhoneHidden());
        //查询购买商品信息
        QueryWrapper<OrderItem> qw = new QueryWrapper<>();
        qw.eq("order_id", order.getId());
        List<OrderItem> orderItemList = orderItemMapper.selectList(qw);
        List<ManagerOrderProductVO> productList = new ArrayList<>();
        orderItemList.forEach(item -> {
            ManagerOrderProductVO productVO = new ManagerOrderProductVO();
            productVO.setProductId(item.getProductId());
            productVO.setBuyNum(item.getQuantity());
            productVO.setPic(item.getPic());
            productVO.setProductName(item.getProductName());
            productVO.setSalePrice(item.getSalePrice());
            productVO.setSpData(item.getSpData());
            productList.add(productVO);
        });
        managerOrderDetailVO.setProductInfo(productList);
        return managerOrderDetailVO;
    }

    /**
     * 查询订单表列表
     *
     * @param query 查询条件
     * @param page 分页条件
     * @return 订单表
     */
    public PageImpl<ManagerOrderVO> selectList(ManagerOrderQueryRequest query, Pageable page) {
        if (page != null) {
            PageHelper.startPage(page.getPageNumber() + 1, page.getPageSize());
        }
        if (!StringUtils.isEmpty(query.getUserPhone())){
            query.setUserPhone(AesCryptoUtils.encrypt(aesKey, query.getUserPhone()));
        }
        List<ManagerOrderVO> managerOrderVOList = orderMapper.selectManagerOrderPage(query);
        if (CollectionUtil.isEmpty(managerOrderVOList)){
            return new PageImpl<>(managerOrderVOList, page, 0);
        }
        long total = ((com.github.pagehelper.Page) managerOrderVOList).getTotal();
        Map<Long, ManagerOrderVO> orderMap = managerOrderVOList.stream().collect(Collectors.toMap(ManagerOrderVO::getId, it -> it));
        //查orderItem
        QueryWrapper<OrderItem> qw = new QueryWrapper<>();
        qw.in("order_id", orderMap.keySet());
        Map<Long, List<OrderItem>> groupedOrderItemMap = orderItemMapper.selectList(qw)
                .stream().collect(Collectors.groupingBy(OrderItem::getOrderId));
        groupedOrderItemMap.keySet().forEach(key -> {
            ManagerOrderVO managerOrderVO = orderMap.get(key);
            List<OrderItem> orderItemList = groupedOrderItemMap.get(key);
            List<ManagerOrderProductVO> addProductList = new ArrayList<>();
            orderItemList.forEach(item -> {
                ManagerOrderProductVO vo = new ManagerOrderProductVO();
                vo.setProductName(item.getProductName());
                vo.setSalePrice(item.getSalePrice());
                vo.setPic(item.getPic());
                vo.setBuyNum(item.getQuantity());
                vo.setProductId(item.getProductId());
                vo.setSpData(item.getSpData());
                addProductList.add(vo);
            });
            managerOrderVO.setProductList(addProductList);
        });
        return new PageImpl<>(new ArrayList<>(orderMap.values()), page, total);
    }

    /**
     * 新增订单表
     *
     * @param order 订单表
     * @return 结果
     */
    public int insert(Order order) {
        order.setCreateTime(LocalDateTime.now());
        return orderMapper.insert(order);
    }

    /**
     * 修改订单表
     *
     * @param order 订单表
     * @return 结果
     */
    public int update(Order order) {
        return orderMapper.updateById(order);
    }

    /**
     * 删除订单表信息
     *
     * @param id 订单表主键
     * @return 结果
     */
    public int deleteById(Long id) {
        return orderMapper.deleteById(id);
    }

    public Page<OrderVO> queryOrderPage(OrderH5Query query, Pageable pageReq) {
        QueryWrapper<Order> qw = new QueryWrapper<>();
        qw.eq("member_id", SecurityUtils.getUserId());
        IPage<Order> page = new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>();
        page.setCurrent(pageReq.getPageNumber())
                .setSize(pageReq.getPageSize());
        if (CollUtil.isEmpty(pageReq.getSort())) {
            pageReq.getSort().forEach(it -> {
                qw.orderBy(true, it.getDirection().isAscending(), it.getProperty());
            });
        }
        Integer tab = query.getTab();
        if (tab != null) {
            qw.eq("delete_status", 0);
            if (tab == 1) {
                qw.eq("status", 0);
            } else if (tab == 2) {
                qw.eq("status", 1);
                qw.eq("aftersale_status", 1);
            } else if (tab == 3) {
                qw.eq("status", 2);
                qw.eq("confirm_status", 0);
            } else if (tab == 4) {
                qw.eq("status", 2);
                qw.eq("confirm_status", 1);
            }
        }
        orderMapper.selectPage(page, qw);
        List<Order> orders = page.getRecords();
        long total = page.getPages();
        if (CollUtil.isEmpty(orders)) {
            return new PageImpl<>(Collections.emptyList(), pageReq, total);
        }
        LambdaQueryWrapper<OrderItem> qw1 = new LambdaQueryWrapper<>();
        qw1.in(OrderItem::getOrderId, orders.stream().map(Order::getId).collect(Collectors.toList()));
        Map<Long, List<OrderItem>> oid2items = orderItemMapper.selectList(qw1)
                .stream().collect(Collectors.groupingBy(OrderItem::getOrderId));
        List<OrderVO> res = orderConvert.dos2vos(orders);
        res.forEach(it -> {
            it.setItems(oid2items.get(it.getId()));
        });
        return new PageImpl<>(res, pageReq, total);
    }


}
