package com.cyl.h5.service;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.cyl.h5.pojo.dto.OrderCreateDTO;
import com.cyl.h5.pojo.dto.OrderProductListDTO;
import com.cyl.h5.pojo.request.CancelOrderRequest;
import com.cyl.h5.pojo.vo.CountOrderVO;
import com.cyl.h5.pojo.vo.H5OrderVO;
import com.cyl.h5.pojo.vo.OrderCalcVO;
import com.cyl.h5.pojo.vo.SkuViewDTO;
import com.cyl.h5.pojo.vo.form.OrderSubmitForm;
import com.cyl.manager.oms.domain.Order;
import com.cyl.manager.oms.domain.OrderItem;
import com.cyl.manager.oms.domain.OrderOperateHistory;
import com.cyl.manager.oms.mapper.OrderItemMapper;
import com.cyl.manager.oms.mapper.OrderMapper;
import com.cyl.manager.oms.mapper.OrderOperateHistoryMapper;
import com.cyl.manager.oms.service.OrderItemService;
import com.cyl.manager.oms.service.OrderOperateHistoryService;
import com.cyl.manager.pms.domain.Product;
import com.cyl.manager.pms.domain.Sku;
import com.cyl.manager.pms.mapper.ProductMapper;
import com.cyl.manager.pms.mapper.SkuMapper;
import com.cyl.manager.ums.domain.Member;
import com.cyl.manager.ums.domain.MemberAddress;
import com.cyl.manager.ums.domain.MemberCart;
import com.cyl.manager.ums.mapper.MemberAddressMapper;
import com.cyl.manager.ums.mapper.MemberCartMapper;
import com.github.pagehelper.PageHelper;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.IDGenerator;
import com.ruoyi.framework.config.LocalDataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class H5OrderService {

    @Autowired
    private MemberAddressMapper memberAddressMapper;

    @Autowired
    private SkuMapper skuMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private MemberCartMapper memberCartMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private OrderOperateHistoryMapper orderOperateHistoryMapper;

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private OrderOperateHistoryService orderOperateHistoryService;

    @Transactional
    public Long submit(OrderSubmitForm form) {
        Member member = (Member) LocalDataUtil.getVar(Constants.MEMBER_INFO);
        //只支持快递
        Long addressId = form.getAddressId();
        if (addressId == null){
            throw new RuntimeException("收获地址不能为空");
        }
        MemberAddress memberAddress = memberAddressMapper.selectById(addressId);
        if (memberAddress == null){
            throw new RuntimeException("收货地址不能为空");
        }
        //sku不能为空
        List<OrderProductListDTO> skuList = form.getSkuList();
        if (CollectionUtil.isEmpty(skuList)){
            throw new RuntimeException("商品SKU信息不能为空");
        }
        //将sku信息转换为 key：skuId ，value：购买数量
        Map<Long, Integer> skuQuantityMap = skuList.stream().collect(Collectors.toMap(OrderProductListDTO::getSkuId, OrderProductListDTO::getQuantity));
        //查询所有sku信息
        Map<Long, Sku> querySkuMap = skuMapper
                .selectBatchIds(skuList.stream().map(OrderProductListDTO::getSkuId).collect(Collectors.toList()))
                .stream().collect(Collectors.toMap(Sku::getId, it -> it));
        //计算商品总额、订单总额（订单总金额=商品总金额，因为暂时没有运费等概念）
        BigDecimal productTotalAmount = BigDecimal.ZERO;
        BigDecimal orderTotalAmount = BigDecimal.ZERO;
        for (OrderProductListDTO dto : skuList){
            if (!querySkuMap.containsKey(dto.getSkuId())){
                throw new RuntimeException("商品SKU不存在");
            }
            Sku sku = querySkuMap.get(dto.getSkuId());
            Product product = productMapper.selectById(sku.getProductId());
            if (product == null){
                throw new RuntimeException("商品不存在");
            }
            if (Constants.PublishStatus.UNDERCARRIAGE.equals(product.getPublishStatus())){
                throw new RuntimeException("商品" + product.getName() + "已下架");
            }
            productTotalAmount = productTotalAmount.add(sku.getPrice().multiply(BigDecimal.valueOf(skuQuantityMap.get(sku.getId()))));
            orderTotalAmount = orderTotalAmount.add(sku.getPrice().multiply(BigDecimal.valueOf(skuQuantityMap.get(sku.getId()))));
            dto.setSku(sku);
            dto.setProduct(product);
        }
        LocalDateTime optTime = LocalDateTime.now();

        //生成一个统一的订单号
        Long orderId = IDGenerator.generateId();
        //创建订单
        Order order = new Order();
        order.setId(orderId);
        order.setOrderSn(this.getOrderIdPrefix() + orderId);
        order.setMemberId(member.getId());
        order.setMemberUsername(member.getNickname());
        order.setPayType(Constants.PayType.WECHAT);
        order.setTotalAmount(orderTotalAmount);
        order.setPurchasePrice(BigDecimal.ZERO);
        order.setFreightAmount(BigDecimal.ZERO);
        order.setPayAmount(orderTotalAmount);
        //暂时为接入支付，直接设置为待发货
        order.setStatus(Constants.OrderStatus.SEND);
        order.setAftersaleStatus(1);
        order.setReceiverName(memberAddress.getName());
        order.setReceiverPhone(memberAddress.getPhoneHidden());
        order.setReceiverPostCode(memberAddress.getPostCode());
        order.setReceiverProvince(memberAddress.getProvince());
        order.setReceiverCity(memberAddress.getCity());
        order.setReceiverDistrict(memberAddress.getDistrict());
        order.setReceiverProvinceId(memberAddress.getProvinceId());
        order.setReceiverCityId(memberAddress.getCityId());
        order.setReceiverDistrictId(memberAddress.getDistrictId());
        order.setReceiverDetailAddress(memberAddress.getDetailAddress());
        order.setNote(form.getNote());
        order.setConfirmStatus(0);
        order.setDeleteStatus(0);
        order.setPaymentTime(optTime);
        order.setCreateTime(optTime);
        order.setCreateBy(member.getId());
        int rows = orderMapper.insert(order);
        if (rows < 1){
            throw new RuntimeException("订单新增失败");
        }
        // 保存orderItem
        orderItemService.saveOrderItem(member, optTime, orderId, skuList);
        // 保存订单操作记录
        OrderOperateHistory orderOperateHistory = new OrderOperateHistory();
        orderOperateHistory.setOrderId(orderId);
        orderOperateHistory.setOperateMan(member.getId() + "");
        orderOperateHistory.setOrderStatus(Constants.OrderStatus.SEND);
        orderOperateHistory.setCreateTime(optTime);
        orderOperateHistory.setCreateBy(member.getId());
        rows = orderOperateHistoryMapper.insert(orderOperateHistory);
        if (rows < 1){
            throw new RuntimeException("保存订单操作记录失败");
        }
        //若来源为购物车，删除购物车
        if (Constants.OrderFrom.CART.equals(form.getFrom())){
            List<Long> skuIdList = skuList.stream().map(OrderProductListDTO::getSkuId).collect(Collectors.toList());
            LambdaUpdateWrapper<MemberCart> wrapper = Wrappers.lambdaUpdate();
            wrapper.eq(MemberCart::getMemberId, member.getId());
            wrapper.in(MemberCart::getSkuId, skuIdList);
            rows = memberCartMapper.delete(wrapper);
            if (rows < 1){
                throw new RuntimeException("删除购物车失败");
            }
        }
        //当前订单id，接入支付后可返回payId
        return orderId;
    }

    public OrderCalcVO addOrderCheck(OrderCreateDTO orderCreateDTO) {
        OrderCalcVO res = new OrderCalcVO();
        List<SkuViewDTO> skuList = new ArrayList<>();
        List<OrderProductListDTO> list = orderCreateDTO.getSkuList();
        if (CollectionUtil.isEmpty(list)){
            throw new RuntimeException("商品SKU信息不能为空");
        }
        //将购买的sku信息转化为key：skuId value：数量
        Map<Long, Integer> quantityMap = list.stream().
                collect(Collectors.toMap(OrderProductListDTO::getSkuId, OrderProductListDTO::getQuantity, (v1, v2) -> v2));
        //查询所有sku信息
        Set<Long> collect = list.stream().map(OrderProductListDTO::getSkuId).collect(Collectors.toSet());
        Map<Long, Sku> querySkuMap = skuMapper.selectBatchIds(collect).stream().collect(Collectors.toMap(Sku::getId, it -> it));
        //计算商品总金额、订单总金额
        BigDecimal productTotalAmount = BigDecimal.ZERO;
        BigDecimal orderTotalAmount = BigDecimal.ZERO;
        for (OrderProductListDTO dto : list){
            if (!querySkuMap.containsKey(dto.getSkuId())){
                throw new RuntimeException("商品SKU不存在");
            }
            Sku sku = querySkuMap.get(dto.getSkuId());
            //查product
            Product product = productMapper.selectById(sku.getProductId());
            if (product == null){
                throw new RuntimeException("商品不存在");
            }
            if (Constants.PublishStatus.UNDERCARRIAGE.equals(product.getPublishStatus())){
                throw new RuntimeException("商品" + product.getName() + "已下架");
            }
            BigDecimal addAmount = sku.getPrice().multiply(BigDecimal.valueOf(dto.getQuantity()));
            //由于目前没有运费等数据，暂时订单总金额=商品总金额了
            productTotalAmount = productTotalAmount.add(addAmount);
            orderTotalAmount = orderTotalAmount.add(addAmount);
            //封装sku信息
            SkuViewDTO skuViewDTO = new SkuViewDTO();
            skuViewDTO.setPic(product.getPic());
            skuViewDTO.setPrice(sku.getPrice());
            skuViewDTO.setProductId(product.getId());
            skuViewDTO.setProductName(product.getName());
            skuViewDTO.setQuantity(quantityMap.get(sku.getId()));
            skuViewDTO.setSkuId(sku.getId());
            skuViewDTO.setSpData(sku.getSpData());
            skuList.add(skuViewDTO);
        }
        res.setSkuList(skuList);
        res.setOrderTotalAmount(orderTotalAmount);
        res.setProductTotalAmount(productTotalAmount);
        return res;
    }


    private String getOrderIdPrefix(){
        LocalDateTime time = LocalDateTime.now();
        return time.format(DateTimeFormatter.ofPattern("yyMMdd")) + "-";
    }

    /**
     * h5订单分页查询
     * @param status 订单状态 -1->全部；0->待付款；1->待发货；2->待收货；-2->售后单
     * @param memberId 会员id
     * @param pageable 分页
     * @return 结果
     */
    public PageImpl<H5OrderVO> orderPage(Integer status, Long memberId, Pageable pageable) {
        // 如果全部且页数为1，看看有无待付款单
        List<H5OrderVO> unpaidOrderList = new ArrayList<>();
        if (Constants.H5OrderStatus.ALL.equals(status) && pageable.getPageNumber() == 0){
            unpaidOrderList = orderMapper.orderPage(Constants.H5OrderStatus.UN_PAY, memberId);
        }
        if (pageable != null){
            PageHelper.startPage(pageable.getPageNumber() + 1, pageable.getPageSize());
        }
        List<H5OrderVO> orderList = orderMapper.orderPage(status, memberId);
        long total = ((com.github.pagehelper.Page) orderList).getTotal();
        // 两个list都没数据那肯定返回空了
        if (CollectionUtil.isEmpty(unpaidOrderList) && CollectionUtil.isEmpty(orderList)){
            return new PageImpl<>(Collections.EMPTY_LIST, pageable, total);
        }
        // 开始组装item了
        // 拿出所有orderId，查item，然后分组 by orderId
        List<Long> idList = new ArrayList<>();
        if (CollectionUtil.isNotEmpty(unpaidOrderList)){
            idList.addAll(unpaidOrderList.stream().map(H5OrderVO::getOrderId).collect(Collectors.toList()));
        }
        if (CollectionUtil.isNotEmpty(orderList)){
            idList.addAll(orderList.stream().map(H5OrderVO::getOrderId).collect(Collectors.toList()));
        }
        QueryWrapper<OrderItem> orderItemQw = new QueryWrapper<>();
        orderItemQw.in("order_id", idList);
        Map<Long, List<OrderItem>> orderItemMap =
                orderItemMapper.selectList(orderItemQw).stream().collect(Collectors.groupingBy(OrderItem::getOrderId));
        orderList.addAll(0, unpaidOrderList);
        orderList.forEach(item -> {
            item.setOrderItemList(orderItemMap.get(item.getOrderId()));
        });
        return new PageImpl<>(orderList, pageable, total);
    }

    public H5OrderVO orderDetail(Long orderId) {
        H5OrderVO order = orderMapper.selectOrderDetail(orderId);
        if (order == null){
            throw new RuntimeException("未查询到该订单");
        }
        // 组装item
        QueryWrapper<OrderItem> orderItemQw = new QueryWrapper<>();
        orderItemQw.eq("order_id", orderId);
        List<OrderItem> orderItemList = orderItemMapper.selectList(orderItemQw);
        order.setOrderItemList(orderItemList);
        // 如果未付款，计算倒计时
        if (Constants.OrderStatus.NOTPAID.equals(order.getStatus())){
            // 订单超时时间900s，后面可以配置到字典等
            Integer time = 900;
            Date addDate = Date.from(order.getCreateTime().plusSeconds(time).atZone(ZoneId.systemDefault()).toInstant());
            if (addDate.after(new Date())) {
                order.setTimeToPay(addDate.getTime());
            }
        }
        return order;
    }

    @Transactional
    public String orderComplete(Long orderId) {
        LocalDateTime optDate = LocalDateTime.now();
        Order order = orderMapper.selectById(orderId);
        OrderItem queryOrderItem = new OrderItem();
        queryOrderItem.setOrderId(orderId);
        List<OrderItem> orderItemList = orderItemMapper.selectByEntity(queryOrderItem);
        if(order == null || CollectionUtil.isEmpty(orderItemList)){
            throw new RuntimeException("未查询到订单信息");
        }
        // 只有【待收货】状态才能确认
        if(!order.getStatus().equals(Constants.H5OrderStatus.DELIVERED)){
            throw new RuntimeException("订单状态已改变，请刷新");
        }
        //更新订单
        UpdateWrapper<Order> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", order.getId());
        updateWrapper.set("status", Constants.H5OrderStatus.COMPLETED);
        updateWrapper.set("confirm_status", 1);
        updateWrapper.set("receive_time", optDate);
        int rows = orderMapper.update(null, updateWrapper);
        if (rows < 1){
            throw new RuntimeException("更新订单状态失败");
        }
        //创建订单操作记录
        OrderOperateHistory optHistory = new OrderOperateHistory();
        optHistory.setOrderId(order.getId());
        optHistory.setOperateMan("用户");
        optHistory.setOrderStatus(Constants.H5OrderStatus.COMPLETED);
        optHistory.setCreateTime(optDate);
        optHistory.setCreateBy(order.getMemberId());
        optHistory.setUpdateBy(order.getMemberId());
        optHistory.setUpdateTime(optDate);
        rows = orderOperateHistoryMapper.insert(optHistory);
        if (rows < 1){
            throw new RuntimeException("创建订单操作记录失败");
        }
        return order.getOrderSn();
    }

    /**
     * 统计待付款、待发货、待收货和售后订单数量
     * @param memberId
     * @return
     */
    public CountOrderVO orderNumCount(Long memberId) {
        return  orderMapper.countByStatusAndMemberId(memberId);
    }

    @Transactional
    public String orderBatchCancel(CancelOrderRequest request, Long userId) {
        LocalDateTime optDate = LocalDateTime.now();
        if (CollectionUtil.isEmpty(request.getIdList())){
            throw new RuntimeException("未指定需要取消的订单号");
        }
        QueryWrapper<Order> orderQw = new QueryWrapper<>();
        orderQw.in("id", request.getIdList());
        List<Order> orderList = orderMapper.selectList(orderQw);
        if (orderList.size() < request.getIdList().size()){
            throw new RuntimeException("未查询到订单信息");
        }
        long count = orderList.stream().filter(it -> !Constants.H5OrderStatus.UN_PAY.equals(it.getStatus())).count();
        if (count > 0){
            throw new RuntimeException("订单状态已更新，请刷新页面");
        }
        List<OrderOperateHistory> addHistoryList = new ArrayList<>();
        orderList.forEach(item -> {
            item.setStatus(Constants.H5OrderStatus.CLOSED);
            item.setUpdateTime(optDate);
            item.setUpdateBy(userId);
            OrderOperateHistory history = new OrderOperateHistory();
            history.setOrderId(item.getId());
            history.setOperateMan("用户");
            history.setOrderStatus(Constants.H5OrderStatus.CLOSED);
            history.setCreateTime(optDate);
            history.setCreateBy(userId);
            history.setUpdateBy(userId);
            history.setUpdateTime(optDate);
            addHistoryList.add(history);
        });
        //取消订单
        int rows = orderMapper.cancelBatch(orderList);
        if (rows < 1){
            throw new RuntimeException("更改订单状态失败");
        }
        //创建订单操作记录
        boolean flag = orderOperateHistoryService.saveBatch(addHistoryList);
        if (!flag){
            throw new RuntimeException("创建订单操作记录失败");
        }
        return "取消订单成功";
    }
}
