package com.cyl.oms.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
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
import com.cyl.oms.convert.OrderConvert;
import com.cyl.oms.domain.OrderItem;
import com.cyl.oms.domain.OrderOperateHistory;
import com.cyl.oms.mapper.OrderItemMapper;
import com.cyl.oms.mapper.OrderOperateHistoryMapper;
import com.cyl.oms.pojo.vo.OrderVO;
import com.cyl.pms.convert.SkuConvert;
import com.cyl.pms.domain.Product;
import com.cyl.pms.domain.Sku;
import com.cyl.pms.mapper.ProductMapper;
import com.cyl.pms.mapper.SkuMapper;
import com.cyl.pms.pojo.vo.SkuVO;
import com.cyl.ums.domain.Member;
import com.cyl.ums.domain.MemberAddress;
import com.cyl.ums.domain.MemberCart;
import com.cyl.ums.mapper.MemberAddressMapper;
import com.cyl.ums.mapper.MemberCartMapper;
import com.github.pagehelper.PageHelper;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.exception.base.BaseException;
import com.ruoyi.common.utils.IDGenerator;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.config.LocalDataUtil;
import com.ruoyi.system.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import com.cyl.oms.mapper.OrderMapper;
import com.cyl.oms.domain.Order;
import com.cyl.oms.pojo.query.OrderQuery;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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

    /**
     * 查询订单表
     *
     * @param id 订单表主键
     * @return 订单表
     */
    public Order selectById(Long id) {
        return orderMapper.selectById(id);
    }

    /**
     * 查询订单表列表
     *
     * @param query 查询条件
     * @param page 分页条件
     * @return 订单表
     */
    public List<Order> selectList(OrderQuery query, Pageable page) {
        if (page != null) {
            PageHelper.startPage(page.getPageNumber() + 1, page.getPageSize());
        }
        QueryWrapper<Order> qw = new QueryWrapper<>();
        Long memberId = query.getMemberId();
        if (memberId != null) {
            qw.eq("member_id", memberId);
        }
        String memberUsernameLike = query.getMemberUsernameLike();
        if (!StringUtils.isEmpty(memberUsernameLike)) {
            qw.like("member_username", memberUsernameLike);
        }
        BigDecimal totalAmount = query.getTotalAmount();
        if (totalAmount != null) {
            qw.eq("total_amount", totalAmount);
        }
        BigDecimal purchasePrice = query.getPurchasePrice();
        if (purchasePrice != null) {
            qw.eq("purchase_price", purchasePrice);
        }
        BigDecimal payAmount = query.getPayAmount();
        if (payAmount != null) {
            qw.eq("pay_amount", payAmount);
        }
        BigDecimal freightAmount = query.getFreightAmount();
        if (freightAmount != null) {
            qw.eq("freight_amount", freightAmount);
        }
        Integer payType = query.getPayType();
        if (payType != null) {
            qw.eq("pay_type", payType);
        }
        Integer status = query.getStatus();
        if (status != null) {
            qw.eq("status", status);
        }
        Integer aftersaleStatus = query.getAftersaleStatus();
        if (aftersaleStatus != null) {
            qw.eq("aftersale_status", aftersaleStatus);
        }
        String deliveryCompany = query.getDeliveryCompany();
        if (!StringUtils.isEmpty(deliveryCompany)) {
            qw.eq("delivery_company", deliveryCompany);
        }
        String deliverySn = query.getDeliverySn();
        if (!StringUtils.isEmpty(deliverySn)) {
            qw.eq("delivery_sn", deliverySn);
        }
        Integer autoConfirmDay = query.getAutoConfirmDay();
        if (autoConfirmDay != null) {
            qw.eq("auto_confirm_day", autoConfirmDay);
        }
        String receiverNameLike = query.getReceiverNameLike();
        if (!StringUtils.isEmpty(receiverNameLike)) {
            qw.like("receiver_name", receiverNameLike);
        }
        String receiverPhone = query.getReceiverPhone();
        if (!StringUtils.isEmpty(receiverPhone)) {
            qw.eq("receiver_phone", receiverPhone);
        }
        String receiverPostCode = query.getReceiverPostCode();
        if (!StringUtils.isEmpty(receiverPostCode)) {
            qw.eq("receiver_post_code", receiverPostCode);
        }
        String receiverProvince = query.getReceiverProvince();
        if (!StringUtils.isEmpty(receiverProvince)) {
            qw.eq("receiver_province", receiverProvince);
        }
        String receiverCity = query.getReceiverCity();
        if (!StringUtils.isEmpty(receiverCity)) {
            qw.eq("receiver_city", receiverCity);
        }
        String receiverDistrict = query.getReceiverDistrict();
        if (!StringUtils.isEmpty(receiverDistrict)) {
            qw.eq("receiver_district", receiverDistrict);
        }
        Long receiverProvinceId = query.getReceiverProvinceId();
        if (receiverProvinceId != null) {
            qw.eq("receiver_province_id", receiverProvinceId);
        }
        Long receiverCityId = query.getReceiverCityId();
        if (receiverCityId != null) {
            qw.eq("receiver_city_id", receiverCityId);
        }
        Long receiverDistrictId = query.getReceiverDistrictId();
        if (receiverDistrictId != null) {
            qw.eq("receiver_district_id", receiverDistrictId);
        }
        String receiverDetailAddress = query.getReceiverDetailAddress();
        if (!StringUtils.isEmpty(receiverDetailAddress)) {
            qw.eq("receiver_detail_address", receiverDetailAddress);
        }
        String note = query.getNote();
        if (!StringUtils.isEmpty(note)) {
            qw.eq("note", note);
        }
        Integer confirmStatus = query.getConfirmStatus();
        if (confirmStatus != null) {
            qw.eq("confirm_status", confirmStatus);
        }
        Integer deleteStatus = query.getDeleteStatus();
        if (deleteStatus != null) {
            qw.eq("delete_status", deleteStatus);
        }
        LocalDateTime paymentTime = query.getPaymentTime();
        if (paymentTime != null) {
            qw.eq("payment_time", paymentTime);
        }
        LocalDateTime deliveryTime = query.getDeliveryTime();
        if (deliveryTime != null) {
            qw.eq("delivery_time", deliveryTime);
        }
        LocalDateTime receiveTime = query.getReceiveTime();
        if (receiveTime != null) {
            qw.eq("receive_time", receiveTime);
        }
        return orderMapper.selectList(qw);
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
        order.setReceiverPhone(memberAddress.getPhone());
        order.setReceiverPostCode(memberAddress.getPostCode());
        order.setReceiverProvince(memberAddress.getProvince());
        order.setReceiverCity(memberAddress.getCity());
        order.setReceiverDistrict(memberAddress.getDistrict());
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
        //当前返回成功消息，接入支付后可返回payId
        return orderId;
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

}
