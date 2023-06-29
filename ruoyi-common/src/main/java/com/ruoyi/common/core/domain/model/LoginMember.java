package com.ruoyi.common.core.domain.model;

import lombok.Data;

@Data
public class LoginMember {

  private Long memberId;
  private String token;
  private Long loginTime;
  private Long expireTime;

}
