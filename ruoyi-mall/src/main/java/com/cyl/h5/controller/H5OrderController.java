package com.cyl.h5.controller;

import com.alibaba.fastjson.JSONObject;
import com.cyl.h5.domain.dto.DeliveryReq;
import com.cyl.h5.domain.form.*;
import com.cyl.h5.domain.vo.*;
import com.cyl.h5.service.H5OrderService;
import com.cyl.manager.oms.domain.entity.Aftersale;
import com.cyl.manager.oms.domain.entity.Order;
import com.cyl.manager.oms.domain.form.DealWithAftersaleForm;
import com.cyl.manager.oms.service.AftersaleService;
import com.cyl.manager.oms.service.OrderService;
import com.cyl.manager.ums.domain.entity.Member;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.redis.RedisService;
import com.ruoyi.common.enums.AftersaleStatus;
import com.ruoyi.common.enums.OrderStatus;
import com.ruoyi.framework.config.LocalDataUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/h5/order")
@Slf4j
public class H5OrderController {
    @Autowired
    private RedisService redisService;
    @Autowired
    private H5OrderService service;
    @Autowired
    private AftersaleService aftersaleService;
    @Autowired
    private OrderService orderService;

    @ApiOperation("下单")
    @PostMapping("/add")
    public ResponseEntity<Long> submit(@RequestBody OrderSubmitForm form) {
        Member member = (Member) LocalDataUtil.getVar(Constants.MEMBER_INFO);
        Long memberId = member.getId();
        String redisKey = "h5_order_add" + memberId;
        String redisValue = memberId + "_" + System.currentTimeMillis();
        try{
            redisService.lock(redisKey, redisValue, 60);
            return ResponseEntity.ok(service.submit(form));
        }catch (Exception e){
            log.info("创建订单方法异常", e);
            throw new RuntimeException(e.getMessage());
        }finally {
            try {
                redisService.unLock(redisKey, redisValue);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @ApiOperation("下单前校验")
    @PostMapping("/addOrderCheck")
    public ResponseEntity<OrderCalcVO> addOrderCheck(@RequestBody OrderCreateForm orderCreateForm){
        return ResponseEntity.ok(service.addOrderCheck(orderCreateForm));
    }

    @ApiOperation("订单列表")
    @GetMapping("/page")
    public ResponseEntity<PageImpl<H5OrderVO>> orderPage(Integer status, Pageable pageable){
        Member member = (Member) LocalDataUtil.getVar(Constants.MEMBER_INFO);
        return ResponseEntity.ok(service.orderPage(status, member.getId(), pageable));
    }

    @ApiOperation("订单详情")
    @GetMapping("/orderDetail")
    public ResponseEntity<H5OrderVO> orderDetail(@RequestParam(required = false) Long orderId){
        if (orderId == null){
            throw new RuntimeException("系统繁忙");
        }
        return ResponseEntity.ok(service.orderDetail(orderId));
    }

    @ApiOperation("确认收货")
    @GetMapping("/orderComplete")
    public ResponseEntity<String> orderComplete(Long orderId) {
        log.info("确认收货，订单id："+orderId);
        String redisKey = "h5_oms_order_complete_"+orderId;
        String redisValue = orderId+"_"+System.currentTimeMillis();
        try{
            redisService.lock(redisKey,redisValue,60);
            return ResponseEntity.ok(service.orderComplete(orderId));
        }catch (Exception e){
            log.error("确认收货异常",e);
            throw new RuntimeException(e.getMessage());
        }finally {
            try{
                redisService.unLock(redisKey,redisValue);
            }catch (Exception e){
                log.error("",e);
            }
        }
    }

    @ApiOperation("订单数量统计")
    @GetMapping("/countOrder")
    public ResponseEntity<CountOrderVO> orderNumCount(){
        Member member = (Member) LocalDataUtil.getVar(Constants.MEMBER_INFO);
        Long memberId = member.getId();
        return ResponseEntity.ok(service.orderNumCount(memberId));
    }

    @ApiOperation("取消订单")
    @PostMapping("/orderCancel")
    public ResponseEntity<String> orderCancel(@RequestBody CancelOrderForm request){
        Member member = (Member) LocalDataUtil.getVar(Constants.MEMBER_INFO);
        String redisKey = "h5_oms_order_cancel_"+ request.getIdList().get(0);
        String redisValue = request.getIdList().get(0)+"_"+System.currentTimeMillis();
        try{
            redisService.lock(redisKey,redisValue,60);
            return ResponseEntity.ok(service.orderBatchCancel(request, member.getId()));
        }catch (Exception e){
            log.error("订单取消方法异常",e);
            throw new RuntimeException("订单取消失败");
        }finally {
            try {
                redisService.unLock(redisKey,redisValue);
            }catch (Exception e){
                log.error("",e);
            }
        }
    }

    @ApiOperation("订单支付")
    @PostMapping("/orderPay")
    public ResponseEntity<OrderPayVO> orderPay(@RequestBody OrderPayForm req){
        log.info("订单支付","提交的数据："+JSONObject.toJSONString(req));
        String redisKey = "h5_oms_order_pay_"+req.getPayId();
        String redisValue = req.getPayId()+"_"+System.currentTimeMillis();
        try {
            redisService.lock(redisKey, redisValue, 60);
            Member member = (Member) LocalDataUtil.getVar(Constants.MEMBER_INFO);
            Long memberId = member.getId();
            req.setMemberId(memberId);
            return ResponseEntity.ok(service.orderPay(req));
        }catch (Exception e){
            log.error("支付方法异常", e);
            throw new RuntimeException(e.getMessage());
        }finally {
            try{
                redisService.unLock(redisKey,redisValue);
            }catch (Exception e){
                log.error("",e);
            }
        }
    }

    @ApiOperation("申请售后")
    @PostMapping("/applyRefund")
    public ResponseEntity<Boolean> applyRefund(@RequestBody ApplyRefundForm applyRefundForm){
        String redisKey = "h5_oms_order_applyRefund_" + applyRefundForm.getOrderId();
        String redisValue = applyRefundForm.getOrderId() + "_" + System.currentTimeMillis();
        try{
            redisService.lock(redisKey, redisValue, 60);
            Order order = service.applyRefund(applyRefundForm);
            // 如果是未发货，系统自动退款
            if (order.getStatus().equals(OrderStatus.NOT_DELIVERED.getType())) {
                DealWithAftersaleForm req = new DealWithAftersaleForm();
                req.setOrderId(applyRefundForm.getOrderId());
                req.setOptType(1);
                aftersaleService.dealWith(req, order.getMemberId(), "直接发起退款");
            }
            return ResponseEntity.ok(true);
        }catch (Exception e){
            log.error("申请售后发生异常",e);
            throw new RuntimeException(e.getMessage());
        }finally {
            try {
                redisService.unLock(redisKey, redisValue);
            } catch (Exception e) {
                log.error("", e);
            }
        }
    }

    @ApiOperation("取消售后")
    @GetMapping("/cancelRefund")
    public ResponseEntity<String> cancelRefund(Long orderId){
        log.info("【取消售后】订单id：" + orderId);
        String redisKey = "h5_oms_order_cancelRefund_" + orderId;
        String redisValue = orderId + "_" + System.currentTimeMillis();
        try{
            redisService.lock(redisKey, redisValue, 60);
            return ResponseEntity.ok(service.cancelRefund(orderId));
        }catch (Exception e){
            log.error("取消售后发生异常",e);
            throw new RuntimeException(e.getMessage());
        }finally {
            try {
                redisService.unLock(redisKey, redisValue);
            } catch (Exception e) {
                log.error("", e);
            }
        }
    }

    @ApiOperation("售后订单详情")
    @GetMapping("/refundOrderDetail")
    public ResponseEntity<AftersaleRefundInfoVO> refundOrderDetail(@RequestParam Long orderId){
        return ResponseEntity.ok(service.refundOrderDetail(orderId));
    }

    @ApiOperation("用户提交退货单号")
    @PostMapping("/aftersale/delivery")
    public AjaxResult delivery(@RequestBody @Valid DeliveryReq req){
        log.info("用户提交退货单号","提交的数据："+JSONObject.toJSONString(req));
        String redisKey = "h5_oms_order_delivery_"+req.getOrderId();
        String redisValue = req.getOrderId()+"_"+System.currentTimeMillis();
        try {
            redisService.lock(redisKey, redisValue, 60);
            Order order = service.selectById(req.getOrderId());
            Aftersale aftersale = aftersaleService.queryAfterSale(req.getOrderId());
            if(order == null || aftersale == null){
                return AjaxResult.error("未查询到订单信息");
            }
            //仅退款不需要退货
            if(aftersale.getType() == 1){
                return AjaxResult.error("仅退款不需要退货");
            }
            if(aftersale.getStatus() != AftersaleStatus.WAIT.getType()){
                return AjaxResult.error("当前状态不可退货");
            }
            //更新退款单
            aftersale.setRefundWpCode(req.getDeliveryCompanyCode());
            aftersale.setRefundWaybillCode(req.getDeliverySn());
            aftersaleService.update(aftersale);

            return AjaxResult.success();
        }catch (Exception e){
            log.error("用户提交退货单号异常", e);
            return AjaxResult.error("提交发货信息失败");
        }finally {
            try{
                redisService.unLock(redisKey,redisValue);
            }catch (Exception e){
                log.error("",e);
            }
        }
    }

}
