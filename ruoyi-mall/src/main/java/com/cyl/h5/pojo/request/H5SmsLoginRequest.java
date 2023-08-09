package com.cyl.h5.pojo.request;

import com.cyl.wechat.response.WechatUserAuth;
import lombok.Data;

@Data
public class H5SmsLoginRequest extends H5LoginRequest {
    /** 验证码 */
    private String code;
    /** uuid */
    private String uuid;
    /** 微信授权信息 */
    private WechatUserAuth authInfo;
    /** 小程序openid **/
    private String mpOpenId;
}
