package com.cyl.manager.oms.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cyl.h5.domain.query.OrderH5Query;
import com.cyl.manager.oms.convert.OrderConvert;
import com.cyl.manager.oms.convert.OrderOperateHistoryConvert;
import com.cyl.manager.oms.domain.entity.Order;
import com.cyl.manager.oms.domain.entity.OrderDeliveryHistory;
import com.cyl.manager.oms.domain.entity.OrderItem;
import com.cyl.manager.oms.domain.entity.OrderOperateHistory;
import com.cyl.manager.oms.mapper.OrderDeliveryHistoryMapper;
import com.cyl.manager.oms.mapper.OrderItemMapper;
import com.cyl.manager.oms.mapper.OrderMapper;
import com.cyl.manager.oms.mapper.OrderOperateHistoryMapper;
import com.cyl.manager.oms.domain.form.DeliverProductForm;
import com.cyl.manager.oms.domain.form.ManagerOrderQueryForm;
import com.cyl.manager.oms.domain.vo.*;
import com.cyl.manager.pms.convert.SkuConvert;
import com.cyl.manager.pms.mapper.ProductMapper;
import com.cyl.manager.pms.mapper.SkuMapper;
import com.cyl.manager.ums.domain.entity.Member;
import com.cyl.manager.ums.mapper.MemberAddressMapper;
import com.cyl.manager.ums.mapper.MemberCartMapper;
import com.cyl.manager.ums.mapper.MemberMapper;
import com.github.pagehelper.PageHelper;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.AesCryptoUtils;
import com.ruoyi.common.utils.PhoneUtils;
import com.ruoyi.common.utils.SecurityUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

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
    @Autowired
    private OrderDeliveryHistoryMapper orderDeliveryHistoryMapper;
    @Autowired
    private OrderOperateHistoryConvert historyConvert;

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
        managerOrderDetailVO.setCouponAmount(order.getCouponAmount());
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
        managerOrderDetailVO.setReceiveTime(order.getReceiveTime());
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
    public PageImpl<ManagerOrderVO> selectList(ManagerOrderQueryForm query, Pageable page) {
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
        Map<Long, ManagerOrderVO> orderMap = managerOrderVOList.stream().collect(Collectors.toMap(ManagerOrderVO::getId, it -> it, (v1,v2) -> v2, LinkedHashMap::new));
        //查orderItem
        QueryWrapper<OrderItem> qw = new QueryWrapper<>();
        qw.in("order_id", orderMap.keySet());
        Map<Long, List<OrderItem>> groupedOrderItemMap = orderItemMapper.selectList(qw)
                .stream().collect(Collectors.groupingBy(OrderItem::getOrderId));
        groupedOrderItemMap.keySet().forEach(key -> {
            ManagerOrderVO managerOrderVO = orderMap.get(key);
            managerOrderVO.setBuyNum(0);
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
                managerOrderVO.setBuyNum(managerOrderVO.getBuyNum() + item.getQuantity());
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


    public Integer saveMerchantNote(Order order) {
        Order orderInDb = orderMapper.selectById(order.getId());
        if (orderInDb == null){
            throw new RuntimeException("订单不存在");
        }
        UpdateWrapper<Order> qw = new UpdateWrapper<>();
        qw.eq("id", order.getId());
        qw.set("merchant_note", order.getMerchantNote());
        return orderMapper.update(null, qw);
    }

    /**
     * 管理后台发货
     * 目前发货是这样的：待发货、已发货、已完成都能执行发货，每次都会创建一条新的发货记录且修改订单发货信息
     * @param request 发货请求
     * @param userId 操作人
     * @return 结果
     */
    @Transactional
    public String deliverProduct(DeliverProductForm request, Long userId) {
        //查询订单
        Order order = orderMapper.selectById(request.getOrderId());
        QueryWrapper<OrderItem> qw = new QueryWrapper<>();
        qw.eq("order_id", request.getOrderId());
        List<OrderItem> orderItemList = orderItemMapper.selectList(qw);
        if (order == null || CollectionUtil.isEmpty(orderItemList)){
            throw new RuntimeException("未找到该订单信息");
        }
        // 是否为待发货、已发货 、已完成
        if (!(Constants.OrderStatus.SEND.equals(order.getStatus())
                || Constants.OrderStatus.GET.equals(order.getStatus())
                || Constants.OrderStatus.CONFIRM.equals(order.getStatus()))){
            throw new RuntimeException("订单状态错误");
        }
        Integer orderStatus =
                Constants.OrderStatus.SEND.equals(order.getStatus()) ? Constants.OrderStatus.GET : order.getStatus();
        //更新订单
        LocalDateTime optDate = LocalDateTime.now();
        order.setUpdateBy(null);
        order.setStatus(orderStatus);
        order.setDeliveryTime(optDate);
        order.setUpdateTime(optDate);
        order.setDeliveryCompany(request.getExpressName());
        order.setDeliverySn(request.getExpressSn());
        orderMapper.updateById(order);
        //创建新的发货记录
        this.createDeliveryHistory(request, userId, optDate);
        //创建订单操作记录
        this.createOrderOptHistory(order.getId(), order.getOrderSn(), orderStatus, userId, optDate);
        return "发货成功";
    }

    /**
     * 创建发货记录
     * @param request 发货请求
     * @param userId 操作人
     * @param optDate 操作时间
     */
    private void createDeliveryHistory(DeliverProductForm request, Long userId, LocalDateTime optDate){
        OrderDeliveryHistory orderDeliveryHistory = new OrderDeliveryHistory();
        orderDeliveryHistory.setOrderId(request.getOrderId());
        orderDeliveryHistory.setDeliveryCompany(request.getExpressName());
        orderDeliveryHistory.setDeliverySn(request.getExpressSn());
        orderDeliveryHistory.setCreateTime(optDate);
        orderDeliveryHistory.setCreateBy(userId);
        int rows = orderDeliveryHistoryMapper.insert(orderDeliveryHistory);
        if (rows < 1) {
            throw new RuntimeException("新增订单发货记录失败");
        }
    }

    /**
     * 创建订单操作历史
     * @param orderId 订单id
     * @param orderStatus 订单状态
     * @param userId 操作人
     * @param optDate 操作时间
     */
    private void createOrderOptHistory(Long orderId, String orderSn, Integer orderStatus, Long userId, LocalDateTime optDate){
        OrderOperateHistory optHistory = new OrderOperateHistory();
        optHistory.setOrderId(orderId);
        optHistory.setOrderSn(orderSn);
        optHistory.setOperateMan(SecurityUtils.getUsername());
        optHistory.setOrderStatus(orderStatus);
        optHistory.setCreateTime(optDate);
        optHistory.setCreateBy(userId);
        optHistory.setUpdateBy(userId);
        optHistory.setUpdateTime(optDate);
        int rows = orderOperateHistoryMapper.insert(optHistory);
        if (rows < 1) {
            throw new RuntimeException("新增订单操作记录失败");
        }
    }

    /**
     * 根据订单id查询订单操作日志
     * @param orderId 订单id
     * @return 结果
     */
    public List<OrderOperateHistoryVO> log(Long orderId) {
        LambdaQueryWrapper<OrderOperateHistory> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(OrderOperateHistory::getOrderId, orderId);
        wrapper.in(OrderOperateHistory::getOrderStatus, Arrays.asList(0, 1, 2, 3, 4));
        wrapper.orderByDesc(OrderOperateHistory::getCreateTime);
        List<OrderOperateHistory> historyList = orderOperateHistoryMapper.selectList(wrapper);
        return historyConvert.dos2vos(historyList);
    }

    public String decryptPhone(Long orderId) {
        Order order = orderMapper.selectById(orderId);
        String receiverPhoneEncrypted = order.getReceiverPhoneEncrypted();
        if(receiverPhoneEncrypted!=null){
            return AesCryptoUtils.decrypt(aesKey,receiverPhoneEncrypted);
        }else {
            return null;
        }
    }

    public Boolean updateReceiver(Order order) {
        Order dbOrder = orderMapper.selectById(order.getId());
        if (dbOrder == null) {
            return false;
        }
        UpdateWrapper<Order> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("receiver_name",order.getReceiverName())
                        .set("receiver_phone",PhoneUtils.hidePhone(order.getReceiverPhone()))
                        .set("receiver_city",order.getReceiverCity())
                        .set("receiver_district",order.getReceiverDistrict())
                        .set("receiver_province",order.getReceiverProvince())
                        .set("receiver_detail_address",order.getReceiverDetailAddress())
                        .set("receiver_phone_encrypted",AesCryptoUtils.encrypt(aesKey, order.getReceiverPhone()))
                        .set("update_time",LocalDateTime.now());
        updateWrapper.eq("id",order.getId());
        int update = orderMapper.update(null, updateWrapper);
        return update == 1;
    }
}
