package com.cyl.external.resp;

import lombok.Data;

@Data
public class UserInfoResp extends BaseResp {
    private String openid;
    private String nickname;
    private Integer sex;
    private String province;
    private String city;
    private String country;
    private String headimgurl;
}
