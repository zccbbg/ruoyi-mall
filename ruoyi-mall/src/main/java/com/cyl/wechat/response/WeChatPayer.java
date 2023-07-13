package com.cyl.wechat.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author: Jinxin
 * @date: 2023/4/18 23:03
 * @Description:
 */
@Getter
@Setter
public class WeChatPayer {
    @ApiModelProperty("用户标识")
    private String openid;
}
