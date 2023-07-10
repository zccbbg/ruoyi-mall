package com.cyl.wechat.response;

import lombok.Data;

@Data
public class WechatUserAuth {

  private String access_token;
  private Integer expires_in;
  private String refresh_token;
  private String openid;
  private String scope;
}