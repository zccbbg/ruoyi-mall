package com.cyl.wechat.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class JssdkConfigResponse {
    @ApiModelProperty("appId")
    private String appId;
    @ApiModelProperty("timeStamp")
    private String timeStamp;
    @ApiModelProperty("nonceStr")
    private String nonceStr;
    @ApiModelProperty("signature")
    private String signature;
    @ApiModelProperty("jsApiList")
    private List<String> jsApiList;
}
