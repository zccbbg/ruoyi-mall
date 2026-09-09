package com.cyl.wechat.response;
import lombok.Data;

import java.util.List;

@Data
public class JssdkConfigResponse {
    /** appId */
    private String appId;
    /** timeStamp */
    private String timeStamp;
    /** nonceStr */
    private String nonceStr;
    /** signature */
    private String signature;
    /** jsApiList */
    private List<String> jsApiList;
}
