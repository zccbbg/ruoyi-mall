package com.cyl.wechat;

import com.wechat.pay.java.service.payments.jsapi.JsapiService;
import com.wechat.pay.java.service.payments.jsapi.model.Amount;
import com.wechat.pay.java.service.payments.jsapi.model.Payer;
import com.wechat.pay.java.service.payments.jsapi.model.PrepayRequest;
import com.wechat.pay.java.service.payments.jsapi.model.PrepayResponse;
import com.wechat.pay.java.service.refund.RefundService;
import com.wechat.pay.java.service.refund.model.AmountReq;
import com.wechat.pay.java.service.refund.model.CreateRequest;
import com.wechat.pay.java.service.refund.model.Refund;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@ConditionalOnProperty(prefix = "wechat", name = "enabled", havingValue = "true")
public class WechatPayService {

    @Autowired
    private JsapiService service;

    @Autowired
    private RefundService refundService;

    /**
     * jsapi下单
     * @param orderNo 订单号
     * @param desc  订单描述
     * @param totalAmount  总金额，单位：分
     * @param openId   用户openid
     * @return  prepay_id
     */
    public String jsapiPay(String orderNo,String desc,Integer totalAmount,String openId, Long memberId,String appId){
        PrepayRequest prepayRequest = new PrepayRequest();
        prepayRequest.setAppid(appId);
        prepayRequest.setMchid(WechatPayData.merchantId);
        prepayRequest.setDescription(desc);
        prepayRequest.setOutTradeNo(orderNo);
        prepayRequest.setAttach(String.valueOf(memberId));
        prepayRequest.setNotifyUrl(WechatPayData.notifyUrl);
        Amount amount = new Amount();
        amount.setTotal(totalAmount);
        prepayRequest.setAmount(amount);
        Payer payer = new Payer();
        payer.setOpenid(openId);
        prepayRequest.setPayer(payer);
        PrepayResponse response = service.prepay(prepayRequest);
        return response.getPrepayId();
    }

    public Refund refundPay(String refundId,String payId,String notifyUrl,Long refundAmount, Long totalAmount,String reason) {
        //请求参数
        CreateRequest request = new CreateRequest();
        request.setReason(reason);
        //设置退款金额 根据自己的实际业务自行填写
        AmountReq amountReq = new AmountReq();
        amountReq.setRefund(refundAmount);
        amountReq.setTotal(totalAmount);
        amountReq.setCurrency("CNY");
        request.setAmount(amountReq);
        //支付成功后回调回来的transactionId 按照实际情况填写
        request.setOutTradeNo(payId);
        //支付成功后回调回来的transactionId 按照实际情况填写
        request.setOutRefundNo(refundId);
        //退款成功的回调地址
        request.setNotifyUrl(notifyUrl);
        //发起请求,申请退款
        return refundService.create(request);
    }
}
