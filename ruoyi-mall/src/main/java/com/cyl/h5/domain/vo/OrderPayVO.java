package com.cyl.h5.domain.vo;
import lombok.Data;

/**
 * @author 86199
 */
@Data
/** 支付响应 */
public class OrderPayVO {
    /** 支付方式：1-支付宝，2-微信（默认） */
    private Integer payType;

    /** appId */
    private String appId;

    /** timeStamp */
    private String timeStamp;

    /** signType */
    private String signType;

    /**
     * TODO 变量名命名不规范
     */
    /** package */
    private String package_;

    /** nonceStr */
    private String nonceStr;

    /** 签名 */
    private String paySign;
}
