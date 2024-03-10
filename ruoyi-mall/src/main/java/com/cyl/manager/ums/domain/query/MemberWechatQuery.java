package com.cyl.manager.ums.domain.query;

import java.time.LocalDateTime;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 用户微信信息 查询 对象
 *
 * @author zcc
 */
@ApiModel(description="用户微信信息 查询 对象")
@Data
public class MemberWechatQuery {
    @ApiModelProperty("MEMBER_ID 精确匹配")
    private Long memberId;

    @ApiModelProperty("只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段 精确匹配")
    private String unionid;

    @ApiModelProperty("用户的标识，对当前公众号唯一 精确匹配")
    private String openid;

    @ApiModelProperty("小程序唯一身份ID 精确匹配")
    private String routineOpenid;

    @ApiModelProperty("用户所在的分组ID（兼容旧的用户分组接口） 精确匹配")
    private Integer groupid;

    @ApiModelProperty("用户被打上的标签ID列表 精确匹配")
    private String tagidList;

    @ApiModelProperty("用户是否订阅该公众号标识 精确匹配")
    private Integer subscribe;

    @ApiModelProperty("关注公众号时间 精确匹配")
    private Integer subscribeTime;

    @ApiModelProperty("小程序用户会话密匙 精确匹配")
    private String sessionKey;

    @ApiModelProperty("token 精确匹配")
    private String accessToken;

    @ApiModelProperty("过期时间 精确匹配")
    private Integer expiresIn;

    @ApiModelProperty("刷新token 精确匹配")
    private String refreshToken;

    @ApiModelProperty("过期时间 精确匹配")
    private LocalDateTime expireTime;

}
