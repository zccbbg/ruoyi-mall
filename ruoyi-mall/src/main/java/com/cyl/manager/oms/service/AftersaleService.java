package com.cyl.manager.oms.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.cyl.manager.act.service.MemberCouponService;
import com.cyl.manager.oms.convert.OrderOperateHistoryConvert;
import com.cyl.manager.oms.domain.entity.*;
import com.cyl.manager.oms.mapper.*;
import com.cyl.manager.oms.domain.form.DealWithAftersaleForm;
import com.cyl.manager.oms.domain.form.ManagerAftersaleOrderForm;
import com.cyl.manager.oms.domain.vo.*;
import com.cyl.manager.pms.mapper.SkuMapper;
import com.cyl.manager.ums.domain.entity.Member;
import com.cyl.manager.ums.domain.entity.MemberWechat;
import com.cyl.manager.ums.mapper.MemberMapper;
import com.cyl.manager.ums.mapper.MemberWechatMapper;
import com.cyl.wechat.WechatPayService;
import com.github.pagehelper.PageHelper;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.constant.HttpStatus;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.enums.AftersaleStatus;
import com.ruoyi.common.enums.OrderRefundStatus;
import com.wechat.pay.java.service.refund.model.Refund;
import com.wechat.pay.java.service.refund.model.RefundNotification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

/**
 * 订单售后Service业务层处理
 *
 * @author zcc
 */
@Service
@Slf4j
public class AftersaleService {
    @Autowired
    private AftersaleMapper aftersaleMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private OrderOperateHistoryMapper orderOperateHistoryMapper;

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private OrderOperateHistoryConvert historyConvert;
    @Autowired
    private SkuMapper skuMapper;
    @Autowired
    private MemberCouponService memberCouponService;
    @Autowired
    private WechatPaymentHistoryMapper wechatPaymentHistoryMapper;
    @Autowired
    private MemberWechatMapper memberWechatMapper;
    @Autowired(required = false)
    private WechatPayService wechatPayService;
    @Autowired
    private OrderOperateHistoryMapper operateHistoryMapper;

    /**
     * 查询订单售后
     *
     * @param id 订单售后主键
     * @return 订单售后
     */
    public ManagerRefundOrderDetailVO selectById(Long id) {
        Order order = orderMapper.selectById(id);
        if (order == null) {
            throw new RuntimeException("无该订单信息");
        }
        ManagerRefundOrderDetailVO result = new ManagerRefundOrderDetailVO();
        //订单基本信息
        result.setOrderId(order.getId());
        result.setOrderSn(order.getOrderSn());
        result.setCreateTime(order.getCreateTime());
        result.setPayType(order.getPayType());
        result.setPayTime(order.getPaymentTime());
        result.setStatus(order.getStatus());
        result.setExpressName(order.getDeliveryCompany());
        result.setDeliveryTime(order.getDeliveryTime());
        result.setExpressNo(order.getDeliverySn());
        result.setTotalAmount(order.getTotalAmount());
        result.setPayAmount(order.getPayAmount());
        //用户信息
        Member member = memberMapper.selectById(order.getMemberId());
        result.setNickName(member.getNickname());
        result.setPhone(member.getPhoneHidden());
        //收货信息
        OrderAddressVO orderAddressVO = new OrderAddressVO();
        orderAddressVO.setAddress(order.getReceiverDetailAddress());
        orderAddressVO.setName(order.getReceiverName());
        orderAddressVO.setUserPhone(order.getReceiverPhone());
        orderAddressVO.setArea(order.getReceiverProvince() + order.getReceiverCity() + order.getReceiverDistrict());
        result.setAddressInfo(orderAddressVO);
        //orderItem
        QueryWrapper<OrderItem> orderItemQw = new QueryWrapper<>();
        orderItemQw.eq("order_id", id);
        List<OrderItem> orderItemList = orderItemMapper.selectList(orderItemQw);
        List<ManagerOrderProductVO> productList = new ArrayList<>();
        orderItemList.forEach(orderItem -> {
            ManagerOrderProductVO productVO = new ManagerOrderProductVO();
            productVO.setPic(orderItem.getPic());
            productVO.setSpData(orderItem.getSpData());
            productVO.setProductName(orderItem.getProductName());
            productVO.setSalePrice(orderItem.getSalePrice());
            productVO.setBuyNum(orderItem.getQuantity());
            productVO.setProductId(orderItem.getProductId());
            productList.add(productVO);
        });
        result.setProductList(productList);
        //售后信息
        QueryWrapper<Aftersale> aftersaleQw = new QueryWrapper<>();
        aftersaleQw.eq("order_id", order.getId());
        aftersaleQw.orderByDesc("create_time");
        List<Aftersale> aftersaleList = aftersaleMapper.selectList(aftersaleQw);
        List<RefundInfoVO> refundInfoList = new ArrayList<>();
        aftersaleList.forEach(aftersale -> {
            RefundInfoVO refundInfo = new RefundInfoVO();
            refundInfo.setId(aftersale.getId());
            refundInfo.setApplyRefundType(aftersale.getType());
            refundInfo.setApplyRefundTime(aftersale.getCreateTime());
            refundInfo.setRefundAmount(aftersale.getReturnAmount());
            refundInfo.setReason(aftersale.getReason());
            refundInfo.setDescription(aftersale.getDescription());
            refundInfo.setProofPics(aftersale.getProofPics());
            refundInfo.setRefundStatus(aftersale.getStatus());
            refundInfo.setRefundWpCode(aftersale.getRefundWpCode());
            refundInfo.setRefundWaybillCode(aftersale.getRefundWaybillCode());
            refundInfo.setRefundStatus(aftersale.getStatus());
            refundInfo.setRemark(aftersale.getHandleNote());
            refundInfoList.add(refundInfo);
        });
        result.setRefundInfoList(refundInfoList);
        return result;
    }

    /**
     * 查询订单售后列表
     *
     * @param query 查询条件
     * @param page  分页条件
     * @return 订单售后
     */
    public List<ManagerRefundOrderVO> selectList(ManagerAftersaleOrderForm query, Pageable page) {
        if (page != null) {
            PageHelper.startPage(page.getPageNumber() + 1, page.getPageSize());
        }
        if (StrUtil.isNotBlank(query.getOrderSn()) && query.getOrderSn().length() > 7) {
            query.setOrderSn(query.getOrderSn().substring(7));
        }
        List<ManagerRefundOrderVO> managerRefundOrderVOS = aftersaleMapper.selectManagerRefundOrder(query);
        if (CollectionUtil.isEmpty(managerRefundOrderVOS)) {
            return managerRefundOrderVOS;
        }
        Set<Long> idSet = managerRefundOrderVOS.stream().map(ManagerRefundOrderVO::getOrderId).collect(Collectors.toSet());
        //查一下orderSn集合
        QueryWrapper<Order> orderQw = new QueryWrapper<>();
        orderQw.in("id", idSet);
        Map<Long, Order> orderMap = orderMapper.selectList(orderQw).stream().collect(Collectors.toMap(Order::getId, it -> it));
        //封装售后单商品数据
        QueryWrapper<OrderItem> orderItemQw = new QueryWrapper<>();
        orderItemQw.in("order_id", idSet);
        Map<Long, List<OrderItem>> orderItemMap = orderItemMapper.selectList(orderItemQw).stream().collect(Collectors.groupingBy(OrderItem::getOrderId));
        managerRefundOrderVOS.forEach(vo -> {
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
        return managerRefundOrderVOS;
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
     *
     * @param request 请求体
     * @param user    操作人
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public void dealWith(DealWithAftersaleForm request, Long userId, String optUserName) {
        Order order = orderMapper.selectById(request.getOrderId());
        if (order == null) {
            throw new RuntimeException("无该订单");
        }
        QueryWrapper<Aftersale> aftersaleQw = new QueryWrapper<>();
        aftersaleQw.eq("order_id", request.getOrderId());
        if (request.getOptType() == 3) {
            aftersaleQw.eq("status", 1);
        } else {
            aftersaleQw.eq("status", 0);
        }
        Aftersale aftersale = aftersaleMapper.selectOne(aftersaleQw);
        if (aftersale == null) {
            throw new RuntimeException("没有售后单");
        }
        //售后状态与售后类型是否对应
        if (Constants.OptType.AGREE.equals(request.getOptType()) || Constants.OptType.REFUSE.equals(request.getOptType())) {
            if (!AftersaleStatus.APPLY.getType().equals(aftersale.getStatus())) {
                throw new RuntimeException("订单状态错误，请刷新页面后重试！");
            }
        } else {
            if (!AftersaleStatus.WAIT.getType().equals(aftersale.getStatus())) {
                throw new RuntimeException("订单状态错误，请刷新页面后重试！");
            }
        }
        //拒绝则理由必填
        if (Constants.OptType.REFUSE.equals(request.getOptType()) && StrUtil.isBlank(request.getRemark())) {
            throw new RuntimeException("请填写拒绝理由");
        }
        LocalDateTime optDate = LocalDateTime.now();
        //要创建的订单操作记录，status后续判断再设置
        OrderOperateHistory optHistory = new OrderOperateHistory();
        optHistory.setOrderId(order.getId());
        optHistory.setOrderSn(order.getOrderSn());
        optHistory.setOperateMan("后台管理员");
        optHistory.setCreateTime(optDate);
        optHistory.setCreateBy(userId);
        optHistory.setUpdateBy(userId);
        optHistory.setUpdateTime(optDate);
        //封装售后wrapper
        UpdateWrapper<Aftersale> aftersaleWrapper = new UpdateWrapper<>();
        aftersaleWrapper.eq("order_id", request.getOrderId());
        aftersaleWrapper.eq("status", AftersaleStatus.APPLY.getType());
        aftersaleWrapper.set("handle_man", optUserName);
        aftersaleWrapper.set("update_time", optDate);
        aftersaleWrapper.set("handle_time", optDate);
        aftersaleWrapper.set("update_by", userId);
        //封装订单wrapper
        UpdateWrapper<Order> orderWrapper = new UpdateWrapper<>();
        orderWrapper.eq("id", request.getOrderId());
        orderWrapper.set("update_time", optDate);
        orderWrapper.set("update_by", userId);
        //更新订单、售后单，创建操作记录
        if (request.getOptType().equals(Constants.OptType.REFUSE)) {
            aftersaleWrapper.set("status", AftersaleStatus.REJECT.getType());
            aftersaleWrapper.set("handle_note", request.getRemark());
            orderWrapper.set("aftersale_status", OrderRefundStatus.NO_REFUND.getType());
            optHistory.setOrderStatus(14);
        } else if (request.getOptType().equals(Constants.OptType.AGREE)) {
            aftersaleWrapper.set("status", AftersaleStatus.WAIT.getType());
            orderWrapper.set("aftersale_status", Objects.equals(aftersale.getType(), 1) ? 3 : 2);
            optHistory.setOrderStatus(12);
        } else {
            //如果是退货退款 order身上的售后状态应该是保持不变的 仅退款的话就进入退款了
            orderWrapper.set("aftersale_status", 3);
            int row = orderMapper.update(null, orderWrapper);
            if (row != 1) {
                throw new RuntimeException("修改订单状态失败");
            }
        }
        int rows = aftersaleMapper.update(null, aftersaleWrapper);
//        if (rows < 1) {
//            throw new RuntimeException("更新售后单失败");
//        }
        rows = orderMapper.update(null, orderWrapper);
        if (rows < 1) {
            throw new RuntimeException("更新订单失败");
        }
        rows = orderOperateHistoryMapper.insert(optHistory);
        if (rows < 1) {
            throw new RuntimeException("创建订单操作记录失败");
        }
        // 是否需要发起退款
        if ((request.getOptType() == Constants.OptType.GIVING || (request.getOptType() == Constants.OptType.AGREE && aftersale.getType() == 1))) {
            tradeRefund(aftersale, order, optDate, userId);
        }
    }

    public void tradeRefund(Aftersale returnApply, Order order, LocalDateTime optDate, Long userId) {
        //查一下微信订单
        QueryWrapper<WechatPaymentHistory> qw = new QueryWrapper<>();
        qw.eq("order_id", order.getPayId()).eq("op_type", 1);
        WechatPaymentHistory history = wechatPaymentHistoryMapper.selectOne(qw);
        //查用户的微信信息
        QueryWrapper<MemberWechat> wechatQw = new QueryWrapper<>();
        wechatQw.eq("member_id", order.getMemberId());
        MemberWechat memberWechat = memberWechatMapper.selectOne(wechatQw);
        //查订单item
        QueryWrapper<OrderItem> itemQw = new QueryWrapper<>();
        itemQw.eq("order_id", order.getId());
        OrderItem orderItem = orderItemMapper.selectList(itemQw).get(0);
        //开始退款
        Refund wechatResponse = wechatPayService.refundPay(returnApply.getId() + "",
                order.getPayId() + "",
                "https://mall.ichengle.top/api/no-auth/wechat/weChatRefundNotify",
                returnApply.getReturnAmount().multiply(new BigDecimal("100")).longValue(),
                history.getMoney().multiply(new BigDecimal("100")).longValue(), returnApply.getReason());
        log.info("发起微信退款返回信息,tradeRefund:{}", JSONObject.toJSONString(wechatResponse == null ? "" : wechatResponse));

        if (wechatResponse != null && Arrays.asList("PROCESSING", "SUCCESS").contains(wechatResponse.getStatus().name())) {
            qw = new QueryWrapper<>();
            qw.eq("order_id", order.getId()).eq("op_type", 3);
            WechatPaymentHistory refundHistory = wechatPaymentHistoryMapper.selectOne(qw);
            if (refundHistory == null) {
                WechatPaymentHistory wechatPaymentHistory = new WechatPaymentHistory();
                wechatPaymentHistory.setPaymentId(wechatResponse.getRefundId());
                wechatPaymentHistory.setMemberId(order.getMemberId());
                LambdaQueryWrapper<WechatPaymentHistory> queryWrapper = Wrappers.lambdaQuery();
                queryWrapper.eq(WechatPaymentHistory::getOrderId, order.getPayId());
                queryWrapper.eq(WechatPaymentHistory::getOpType, Constants.PaymentOpType.PAY);
                WechatPaymentHistory payHistory = wechatPaymentHistoryMapper.selectOne(queryWrapper);
                wechatPaymentHistory.setOpenid(payHistory.getOpenid());
                wechatPaymentHistory.setTitle(orderItem.getProductName());
                wechatPaymentHistory.setOrderId(order.getId());
                wechatPaymentHistory.setMoney(returnApply.getReturnAmount().multiply(new BigDecimal("100")));
                wechatPaymentHistory.setOpType(3);
                wechatPaymentHistory.setPaymentStatus(0);
                wechatPaymentHistory.setResponseBody(JSON.toJSONString(wechatResponse));
                wechatPaymentHistory.setCreateTime(optDate);
                wechatPaymentHistory.setUpdateTime(optDate);
                wechatPaymentHistory.setCreateBy(userId);
                wechatPaymentHistory.setUpdateBy(userId);
                wechatPaymentHistoryMapper.insert(wechatPaymentHistory);
            } else {
                UpdateWrapper<WechatPaymentHistory> updateWrapper = new UpdateWrapper<>();
                updateWrapper.eq("id", refundHistory.getId())
                        .set("payment_id", wechatResponse.getRefundId()).set("update_time", optDate);
                wechatPaymentHistoryMapper.update(null, updateWrapper);
            }
        }
    }

    /**
     * 订单退款回调MQ
     *
     * @param weChatRefundNotify
     */
    @Transactional
    public void refundOrderExc(RefundNotification weChatRefundNotify) {
        log.info("收到订单回调MQ：" + JSONObject.toJSONString(weChatRefundNotify));
        if ("PROCESSING".equals(weChatRefundNotify.getRefundStatus().name())) {
            return;
        }
        //查一下微信订单
        QueryWrapper<WechatPaymentHistory> qw = new QueryWrapper<>();
        qw.eq("payment_id", weChatRefundNotify.getRefundId()).eq("op_type", 3);
        WechatPaymentHistory history = wechatPaymentHistoryMapper.selectOne(qw);
        if (history == null) {
            log.info("未找到退款单");
            throw new RuntimeException();
        }
        LocalDateTime optDate = LocalDateTime.now();
        QueryWrapper<Order> orderQw = new QueryWrapper<>();
        orderQw.eq("id", history.getOrderId());
        Order order = orderMapper.selectOne(orderQw);
        if (order.getStatus() == OrderRefundStatus.SUCCESS.getType()) {
            log.info("订单已经是退款成功状态");
            throw new RuntimeException();
        }
        QueryWrapper<Aftersale> aftersaleQw = new QueryWrapper<>();
        aftersaleQw.eq("order_id", history.getOrderId()).eq("status", AftersaleStatus.WAIT.getType());
        if ("SUCCESS".equals(weChatRefundNotify.getRefundStatus().name())) {
            //更改订单表
            UpdateWrapper<Order> orderUpdateWrapper = new UpdateWrapper<>();
            orderUpdateWrapper.eq("id", history.getOrderId())
                    .set("aftersale_status", OrderRefundStatus.SUCCESS.getType());
            orderMapper.update(null, orderUpdateWrapper);

            //更改 售后表
            UpdateWrapper<Aftersale> aftersaleWrapper = new UpdateWrapper<>();
            aftersaleWrapper.eq("order_id", history.getOrderId()).set("status", AftersaleStatus.SUCCESS.getType());
            aftersaleMapper.update(null, aftersaleWrapper);

            //更改 微信表
            UpdateWrapper<WechatPaymentHistory> paymentWrapper = new UpdateWrapper<>();
            paymentWrapper.eq("payment_id", weChatRefundNotify.getRefundId()).eq("op_type", 3)
                    .set("payment_status", 1);
            wechatPaymentHistoryMapper.update(null, paymentWrapper);
            OrderOperateHistory optHistory = new OrderOperateHistory();
            optHistory.setOrderId(order.getId());
            optHistory.setOperateMan("系统");
            optHistory.setCreateTime(optDate);
            optHistory.setCreateBy(order.getMemberId());
            optHistory.setUpdateBy(order.getMemberId());
            optHistory.setUpdateTime(optDate);
            optHistory.setOrderStatus(13);
            operateHistoryMapper.insert(optHistory);
            // 回滚商品和sku销量
            OrderItem orderItem = orderItemMapper.selectOne(new QueryWrapper<OrderItem>().eq("order_id", order.getId()));
            skuMapper.updateStockById(orderItem.getSkuId(), LocalDateTime.now(), -1 * orderItem.getQuantity());
            //退还优惠券
            if (order.getMemberCouponId() != null) {
                memberCouponService.backCoupon(Arrays.asList(order.getMemberCouponId()));
            }
        } else {
            //更改订单表
            UpdateWrapper<Order> orderUpdateWrapper = new UpdateWrapper<>();
            orderUpdateWrapper.eq("id", history.getOrderId())
                    .set("aftersale_status", OrderRefundStatus.FAIL.getType());
            orderMapper.update(null, orderUpdateWrapper);
        }
    }

    public List<OrderOperateHistoryVO> log(Long orderId) {
        LambdaQueryWrapper<OrderOperateHistory> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(OrderOperateHistory::getOrderId, orderId);
        wrapper.in(OrderOperateHistory::getOrderStatus, Arrays.asList(11, 12, 13, 14));
        wrapper.orderByDesc(OrderOperateHistory::getCreateTime);
        List<OrderOperateHistory> historyList = orderOperateHistoryMapper.selectList(wrapper);
        return historyConvert.dos2vos(historyList);
    }

    public Aftersale queryAfterSale(Long orderId) {
        QueryWrapper<Aftersale> itemQw = new QueryWrapper<>();
        itemQw.eq("order_id",orderId);
        itemQw.in("status",Arrays.asList(AftersaleStatus.APPLY.getType(),AftersaleStatus.WAIT.getType()));
        return aftersaleMapper.selectOne(itemQw);
    }
}
