package com.cyl.external.resp;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class AccessTokenResp extends BaseResp {
    @JSONField(name = "access_token")
    private String accessToken;
    @JSONField(name = "expires_in")
    private Integer expiresIn;
    @JSONField(name = "refresh_token")
    private String refreshToken;
    private String openid;
    private String scope;
    @JSONField(name = "is_snapshotuser")
    private Integer snapshotuser;
    private String unionid;
}
