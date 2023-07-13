package com.cyl.wechat;

import com.wechat.pay.java.service.payments.jsapi.JsapiService;
import com.wechat.pay.java.service.payments.jsapi.model.Amount;
import com.wechat.pay.java.service.payments.jsapi.model.Payer;
import com.wechat.pay.java.service.payments.jsapi.model.PrepayRequest;
import com.wechat.pay.java.service.payments.jsapi.model.PrepayResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WechatPayService {

    @Autowired
    private JsapiService service;

    /**
     * jsapi下单
     * @param orderNo 订单号
     * @param desc  订单描述
     * @param totalAmount  总金额，单位：分
     * @param openId   用户openid
     * @return  prepay_id
     */
    public String jsapiPay(String orderNo,String desc,Integer totalAmount,String openId, Long memberId){
        PrepayRequest prepayRequest = new PrepayRequest();
        prepayRequest.setAppid(WechatPayData.appId);
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
}
