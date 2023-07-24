package com.cyl.h5.service;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.cyl.h5.pojo.dto.ApplyRefundDTO;
import com.cyl.h5.pojo.dto.OrderCreateDTO;
import com.cyl.h5.pojo.dto.OrderProductListDTO;
import com.cyl.h5.pojo.dto.PayNotifyMessageDTO;
import com.cyl.h5.pojo.request.CancelOrderRequest;
import com.cyl.h5.pojo.request.OrderPayRequest;
import com.cyl.h5.pojo.response.OrderPayResponse;
import com.cyl.h5.pojo.vo.*;
import com.cyl.h5.pojo.vo.form.OrderSubmitForm;
import com.cyl.manager.oms.convert.AftersaleItemConvert;
import com.cyl.manager.oms.convert.OrderItemConvert;
import com.cyl.manager.oms.domain.*;
import com.cyl.manager.oms.mapper.*;
import com.cyl.manager.oms.service.OrderItemService;
import com.cyl.manager.oms.service.OrderOperateHistoryService;
import com.cyl.manager.pms.domain.Product;
import com.cyl.manager.pms.domain.Sku;
import com.cyl.manager.pms.mapper.ProductMapper;
import com.cyl.manager.pms.mapper.SkuMapper;
import com.cyl.manager.ums.domain.Member;
import com.cyl.manager.ums.domain.MemberAddress;
import com.cyl.manager.ums.domain.MemberCart;
import com.cyl.manager.ums.domain.MemberWechat;
import com.cyl.manager.ums.mapper.MemberAddressMapper;
import com.cyl.manager.ums.mapper.MemberCartMapper;
import com.cyl.manager.ums.mapper.MemberWechatMapper;
import com.cyl.wechat.WechatPayData;
import com.cyl.wechat.WechatPayService;
import com.cyl.wechat.WechatPayUtil;
import com.github.pagehelper.PageHelper;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.redis.RedisService;
import com.ruoyi.common.enums.AftersaleStatus;
import com.ruoyi.common.enums.OrderRefundStatus;
import com.ruoyi.common.enums.OrderStatus;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.IDGenerator;
import com.ruoyi.framework.config.LocalDataUtil;
import com.wechat.pay.java.service.partnerpayments.jsapi.model.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
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

    @Autowired
    @Lazy
    private WechatPayService wechatPayService;

    @Autowired
    private MemberWechatMapper memberWechatMapper;

    @Autowired
    private WechatPaymentHistoryMapper wechatPaymentHistoryMapper;

    @Autowired
    private RedisService redisService;

    @Autowired
    private AftersaleMapper aftersaleMapper;

    @Autowired
    private AftersaleItemMapper aftersaleItemMapper;

    @Autowired
    private AftersaleItemConvert aftersaleItemConvert;

    @Autowired
    private OrderItemConvert orderItemConvert;

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
        //生产一个payId
        Long payId = IDGenerator.generateId();
        //创建订单
        Order order = new Order();
        order.setPayId(payId);
        order.setId(orderId);
        order.setOrderSn(this.getOrderIdPrefix() + orderId);
        order.setMemberId(member.getId());
        order.setMemberUsername(member.getNickname());
        order.setPayType(Constants.PayType.WECHAT);
        order.setTotalAmount(orderTotalAmount);
        order.setPurchasePrice(BigDecimal.ZERO);
        order.setFreightAmount(BigDecimal.ZERO);
        order.setPayAmount(orderTotalAmount);
        order.setStatus(Constants.OrderStatus.NOTPAID);
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
//        order.setPaymentTime(optTime);
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
        orderOperateHistory.setOrderSn(order.getOrderSn());
        orderOperateHistory.setOperateMan(member.getId() + "");
        orderOperateHistory.setOrderStatus(Constants.OrderStatus.NOTPAID);
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
        return payId;
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
        optHistory.setOrderSn(order.getOrderSn());
        optHistory.setOperateMan("" + order.getMemberId());
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
            history.setOrderSn(item.getOrderSn());
            history.setOperateMan("" + item.getMemberId());
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

    /**
     * 订单支付
     * @param req 支付请求
     * @return
     */
    public OrderPayResponse orderPay(OrderPayRequest req) {
        QueryWrapper<Order> qw = new QueryWrapper<>();
        qw.eq("pay_id", req.getPayId());
        qw.eq("status", 0);
        List<Order> orderList = orderMapper.selectList(qw);
        if (CollectionUtil.isEmpty(orderList)){
            throw new RuntimeException("没有待支付的订单");
        }
        QueryWrapper<MemberWechat> memberWechatQw = new QueryWrapper<>();
        memberWechatQw.eq("member_id", req.getMemberId());
        MemberWechat memberWechat = memberWechatMapper.selectOne(memberWechatQw);
        if (memberWechat == null || StrUtil.isBlank(memberWechat.getOpenid())){
            throw new RuntimeException("获取用户openId失败");
        }
        QueryWrapper<OrderItem> orderItemQw = new QueryWrapper<>();
        orderItemQw.eq("order_id", orderList.get(0).getId());
        List<OrderItem> orderItemList = orderItemMapper.selectList(orderItemQw);
        String orderDesc = orderItemList.get(0).getProductName().substring(0, Math.min(40, orderItemList.get(0).getProductName().length()));
        //保存微信支付历史
        LocalDateTime optDate = LocalDateTime.now();
        QueryWrapper<WechatPaymentHistory> wxPaymentQw = new QueryWrapper<>();
        wxPaymentQw.eq("order_id", orderList.get(0).getId());
        wxPaymentQw.eq("op_type", Constants.PaymentOpType.PAY);
        WechatPaymentHistory wechatPaymentHistory = wechatPaymentHistoryMapper.selectOne(wxPaymentQw);
        if (wechatPaymentHistory == null){
            wechatPaymentHistory = new WechatPaymentHistory();
            wechatPaymentHistory.setOrderId(orderList.get(0).getPayId());
            wechatPaymentHistory.setMemberId(req.getMemberId());
            wechatPaymentHistory.setOpenid(memberWechat.getOpenid());
            wechatPaymentHistory.setTitle(orderItemList.get(0).getProductName());
            wechatPaymentHistory.setMoney(orderList.get(0).getPayAmount());
            wechatPaymentHistory.setOpType(Constants.PaymentOpType.PAY);
            wechatPaymentHistory.setPaymentStatus(0);
            wechatPaymentHistory.setCreateBy(req.getMemberId());
            wechatPaymentHistory.setCreateTime(optDate);
            wechatPaymentHistory.setUpdateBy(req.getMemberId());
            wechatPaymentHistory.setUpdateTime(optDate);
            wechatPaymentHistoryMapper.insert(wechatPaymentHistory);
        }else {
            wechatPaymentHistory.setMoney(orderList.get(0).getPayAmount());
            wechatPaymentHistoryMapper.updateById(wechatPaymentHistory);
        }
        //请开启微信支付 wechat.enabled=true
        //调用wx的jsapi拿prepayId，返回签名等信息
        String prepayId = wechatPayService.jsapiPay(
                String.valueOf(req.getPayId()),
                orderDesc,
                Integer.valueOf(orderList.stream().map(Order::getPayAmount).
                        reduce(BigDecimal.ZERO, BigDecimal::add).multiply(new BigDecimal(100)).stripTrailingZeros().toPlainString()),
                memberWechat.getOpenid(),
                req.getMemberId()
        );
        OrderPayResponse response = new OrderPayResponse();
        response.setPayType(2);
        String appId = WechatPayData.appId;
        String nonceStr = WechatPayUtil.generateNonceStr();
        long timeStamp = WechatPayUtil.getCurrentTimestamp();
        prepayId = "prepay_id=" + prepayId;
        String signType = "RSA";
        String paySign = null;
        String signatureStr = Stream.of(appId, String.valueOf(timeStamp), nonceStr, prepayId)
                .collect(Collectors.joining("\n", "", "\n"));
        try {
            paySign = WechatPayUtil.getSign(signatureStr, WechatPayData.privateKeyPath);
        } catch (Exception e) {
            throw new RuntimeException("支付失败");
        }
        response.setAppId(appId);
        response.setTimeStamp(String.valueOf(timeStamp));
        response.setNonceStr(nonceStr);
        response.setSignType(signType);
        response.setPackage_(prepayId);
        response.setPaySign(paySign);
        return response;
    }

    /**
     * 支付回调方法
     * @param messageDTO
     * @return
     */
    @Transactional
    public ResponseEntity<String> payCallBack(PayNotifyMessageDTO messageDTO){
        log.info("【订单支付回调】" + JSONObject.toJSON(messageDTO));
        String redisKey = "h5_oms_order_pay_notify_" + messageDTO.getOutTradeNo();
        String redisValue = messageDTO.getOutTradeNo() + "_" + System.currentTimeMillis();
        LocalDateTime optDate = LocalDateTime.now();
        try{
            redisService.lock(redisKey, redisValue, 60);
            //先判断回信回调的是否未success
            if (!Transaction.TradeStateEnum.SUCCESS.equals(messageDTO.getTradeStatus())){
                log.error("【订单支付回调】订单状态不是支付成功状态" + messageDTO.getTradeStatus());
                throw new RuntimeException();
            }
            QueryWrapper<WechatPaymentHistory> paymentWrapper = new QueryWrapper<>();
            paymentWrapper.eq("order_id", messageDTO.getOutTradeNo());
            paymentWrapper.eq("op_type", Constants.PaymentOpType.PAY);
            WechatPaymentHistory paymentHistory = wechatPaymentHistoryMapper.selectOne(paymentWrapper);
            if (paymentHistory.getPaymentStatus() != Constants.PaymentStatus.INCOMPLETE) {
                log.info("【订单支付回调】支付订单不是未支付状态，不再处理" + "orderId" + paymentHistory.getOrderId() + "status" + paymentHistory.getPaymentStatus());
                throw new RuntimeException();
            }
            QueryWrapper<Order> orderQw = new QueryWrapper<>();
            orderQw.eq("pay_id", messageDTO.getOutTradeNo());
            orderQw.eq("status", OrderStatus.UN_PAY.getType());
            List<Order> orderList = orderMapper.selectList(orderQw);
            orderList.forEach(order -> {
                order.setPaymentTime(messageDTO.getPayTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
                order.setStatus(OrderStatus.NOT_DELIVERED.getType());
                orderMapper.updateById(order);

                OrderOperateHistory optHistory = new OrderOperateHistory();
                optHistory.setOrderId(order.getId());
                optHistory.setOrderSn(order.getOrderSn());
                optHistory.setOperateMan("系统");
                optHistory.setOrderStatus(OrderStatus.NOT_DELIVERED.getType());
                optHistory.setCreateTime(optDate);
                optHistory.setCreateBy(order.getMemberId());
                optHistory.setUpdateBy(order.getMemberId());
                optHistory.setUpdateTime(optDate);
                orderOperateHistoryMapper.insert(optHistory);
            });
            UpdateWrapper<WechatPaymentHistory> paymentHistoryUpdateWrapper = new UpdateWrapper<>();
            paymentHistoryUpdateWrapper.eq("order_id", messageDTO.getOutTradeNo()).set("payment_id", messageDTO.getTradeNo())
                    .set("payment_status", Constants.PaymentStatus.COMPLETE).set("update_time", optDate);
            wechatPaymentHistoryMapper.update(null, paymentHistoryUpdateWrapper);
        }catch (Exception e){
            log.error("订单支付回调异常",e);
            throw new RuntimeException("订单支付回调异常");
        }finally {
            try{
                redisService.unLock(redisKey, redisValue);
            }catch (Exception e){
                log.error("", e);
            }
        }
        return ResponseEntity.ok("订单支付回调成功");
    }

    /**
     * 申请售后
     * @param applyRefundDTO
     * @return
     */
    @Transactional
    public String applyRefund(ApplyRefundDTO applyRefundDTO) {
        Order order = orderMapper.selectById(applyRefundDTO.getOrderId());
        //是否符合售后条件
        this.checkIfCanApplyRefund(order);
        LocalDateTime optDate = LocalDateTime.now();
        Long memberId = order.getMemberId();
        //创建售后单aftersale
        Aftersale addAftersale = new Aftersale();
        addAftersale.setId(IDGenerator.generateId());
        addAftersale.setMemberId(order.getMemberId());
        addAftersale.setOrderId(order.getId());
        addAftersale.setReturnAmount(order.getPayAmount());
        addAftersale.setType(applyRefundDTO.getApplyRefundType());
        addAftersale.setStatus(AftersaleStatus.APPLY.getType());
        addAftersale.setReason(applyRefundDTO.getReason());
        addAftersale.setQuantity(applyRefundDTO.getQuantity());
        addAftersale.setReason(applyRefundDTO.getReason());
        addAftersale.setDescription(applyRefundDTO.getDescription());
        addAftersale.setProofPics(applyRefundDTO.getProofPics());
        addAftersale.setCreateTime(optDate);
        addAftersale.setCreateBy(memberId);
        addAftersale.setUpdateTime(optDate);
        addAftersale.setUpdateBy(memberId);
        int rows = aftersaleMapper.insert(addAftersale);
        if (rows != 1) {
            throw new RuntimeException("插入订单售后失败");
        }
        //创建aftersale item
        QueryWrapper<OrderItem> orderItemQw = new QueryWrapper<>();
        orderItemQw.eq("order_id", order.getId());
        List<OrderItem> orderItemList = orderItemMapper.selectList(orderItemQw);
        List<AftersaleItem> addAftersaleItemList = new ArrayList<>();
        orderItemList.forEach(orderItem -> {
            AftersaleItem aftersaleItem = new AftersaleItem();
            aftersaleItem.setMemberId(memberId);
            aftersaleItem.setAftersaleId(addAftersale.getId());
            aftersaleItem.setOrderId(orderItem.getOrderId());
            aftersaleItem.setOrderItemId(orderItem.getId());
            aftersaleItem.setReturnAmount(orderItem.getSalePrice().multiply(BigDecimal.valueOf(orderItem.getQuantity())));
            aftersaleItem.setQuantity(orderItem.getQuantity());
            aftersaleItem.setCreateTime(optDate);
            aftersaleItem.setCreateBy(memberId);
            aftersaleItem.setUpdateTime(optDate);
            aftersaleItem.setUpdateBy(memberId);
            addAftersaleItemList.add(aftersaleItem);
        });
        rows = aftersaleItemMapper.insertBatch(addAftersaleItemList);
        if (rows < 1){
            throw new RuntimeException("创建售后订单item失败");
        }
        //更新订单
        UpdateWrapper<Order> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", order.getId()).set("aftersale_status", OrderRefundStatus.APPLY.getType())
                .set("update_time", optDate)
                .set("update_by", memberId);
        rows = orderMapper.update(null, updateWrapper);
        if (rows < 1){
            throw new RuntimeException("修改订单状态失败");
        }
        //创建订单操作记录
        OrderOperateHistory optHistory = new OrderOperateHistory();
        optHistory.setOrderId(order.getId());
        optHistory.setOrderSn(order.getOrderSn());
        optHistory.setOperateMan("" + memberId);
        optHistory.setOrderStatus(11);
        optHistory.setCreateTime(optDate);
        optHistory.setCreateBy(memberId);
        optHistory.setUpdateBy(memberId);
        optHistory.setUpdateTime(optDate);
        rows = orderOperateHistoryMapper.insert(optHistory);
        if (rows < 1){
            throw new RuntimeException("创建订单操作记录失败");
        }
        return "售后申请成功";
    }

    /**
     * check是否能售后 可售后的状态为：待发货、待收货、已完成
     * @param order 订单
     */
    private void checkIfCanApplyRefund(Order order){
        if (order == null){
            throw new RuntimeException("为查询到订单信息");
        }
        Integer status = order.getStatus();
        boolean flag = OrderStatus.NOT_DELIVERED.getType().equals(status) || OrderStatus.DELIVERED.getType().equals(status)
                || OrderStatus.COMPLETE.getType().equals(status);
        if (!flag){
            throw new RuntimeException("该订单无法申请售后");
        }
        if (OrderStatus.COMPLETE.getType().equals(order.getStatus()) &&
                DateUtils.betweenDay(LocalDateTime.now(), order.getReceiveTime()) > 7){
            throw new RuntimeException("订单确认收货时间已超过7天，无法申请售后");
        }
        if(OrderRefundStatus.APPLY.getType().equals(order.getAftersaleStatus())
                || OrderRefundStatus.WAIT.getType().equals(order.getAftersaleStatus())){
            throw new RuntimeException("售后正在处理中");
        }
    }

    /**
     * 取消售后
     * @param orderId 订单id
     * @return
     */
    @Transactional
    public String cancelRefund(Long orderId) {
        Order order = orderMapper.selectById(orderId);
        if (order == null){
            throw new RuntimeException("未查询到该订单");
        }
        //查询是否有（待处理和退货中）售后单
        QueryWrapper<Aftersale> aftersaleQw = new QueryWrapper<>();
        aftersaleQw.eq("order_id", orderId);
        aftersaleQw.in("status", Arrays.asList(AftersaleStatus.APPLY.getType(), AftersaleStatus.WAIT.getType()));
        Aftersale aftersale = aftersaleMapper.selectOne(aftersaleQw);
        if (aftersale == null){
            throw new RuntimeException("无售后单");
        }
        if (OrderRefundStatus.SUCCESS.getType().equals(order.getAftersaleStatus())){
            throw new RuntimeException("已退款成功");
        }
        Member member = (Member) LocalDataUtil.getVar(Constants.MEMBER_INFO);
        LocalDateTime optDate = LocalDateTime.now();
        //更新售后单状态
        UpdateWrapper<Aftersale> aftersaleUpdateWrapper = new UpdateWrapper<>();
        aftersaleUpdateWrapper.eq("id", aftersale.getId());
        aftersaleUpdateWrapper.set("status", AftersaleStatus.CANCEL.getType());
        aftersaleUpdateWrapper.set("update_time", optDate);
        aftersaleUpdateWrapper.set("update_by", member.getId());
        int rows = aftersaleMapper.update(null, aftersaleUpdateWrapper);
        if (rows < 1){
            throw new RuntimeException("更新售后单失败");
        }
        //更新订单售后状态
        // 更新订单
        UpdateWrapper<Order> updateOrderWrapper = new UpdateWrapper<>();
        updateOrderWrapper.eq("id", orderId)
                .set("aftersale_status", OrderRefundStatus.NO_REFUND.getType()).set("update_time", optDate)
                .set("update_by", member.getId());
        rows = orderMapper.update(null, updateOrderWrapper);
        if (rows != 1) {
            throw new RuntimeException("更新订单状态失败");
        }
        return "售后取消成功";
    }

    /**
     * 售后订单详情
     * @param orderId 订单id
     * @return
     */
    public AftersaleRefundInfoVO refundOrderDetail(Long orderId) {
        QueryWrapper<Aftersale> aftersaleQw = new QueryWrapper<>();
        aftersaleQw.eq("order_id", orderId);
        aftersaleQw.orderByDesc("create_time");
        aftersaleQw.last("limit 1");
        Aftersale aftersale = aftersaleMapper.selectOne(aftersaleQw);
        if (aftersale == null){
            throw new RuntimeException("未查询到售后订单");
        }
        //查一下售后订单item
        QueryWrapper<AftersaleItem> aftersaleItemQw = new QueryWrapper<>();
        aftersaleItemQw.eq("aftersale_id", aftersale.getId());
        List<AftersaleItem> aftersaleItemList = aftersaleItemMapper.selectList(aftersaleItemQw);
        List<Long> orderItemIdList = aftersaleItemList.stream().map(AftersaleItem::getOrderItemId).collect(Collectors.toList());
        //再去查orderItem
        QueryWrapper<OrderItem> orderItemQw = new QueryWrapper<>();
        orderItemQw.in("id", orderItemIdList);
        List<OrderItem> orderItemList = orderItemMapper.selectList(orderItemQw);
        AftersaleRefundInfoVO vo = new AftersaleRefundInfoVO();
        BeanUtils.copyProperties(aftersale, vo);
        vo.setAftersaleItemList(aftersaleItemConvert.dos2vos(aftersaleItemList));
        vo.setOrderItemList(orderItemConvert.dos2vos(orderItemList));
        return vo;
    }
}
