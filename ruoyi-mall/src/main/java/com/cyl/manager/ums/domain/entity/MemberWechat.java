package com.cyl.manager.ums.domain.entity;

import java.time.LocalDateTime;
import com.ruoyi.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 用户微信信息对象 ums_member_wechat
 * 
 * @author zcc
 */
@ApiModel(description="用户微信信息对象")
@Data
@TableName("ums_member_wechat")
public class MemberWechat extends BaseAudit {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("ID")
    private Long id;

    @ApiModelProperty("MEMBER_ID")
    @Excel(name = "MEMBER_ID")
    private Long memberId;

    @ApiModelProperty("只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段")
    @Excel(name = "只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段")
    private String unionid;

    @ApiModelProperty("用户的标识，对当前公众号唯一")
    @Excel(name = "用户的标识，对当前公众号唯一")
    private String openid;

    @ApiModelProperty("小程序唯一身份ID")
    @Excel(name = "小程序唯一身份ID")
    private String routineOpenid;

    @ApiModelProperty("用户所在的分组ID（兼容旧的用户分组接口）")
    @Excel(name = "用户所在的分组ID", readConverterExp = "兼=容旧的用户分组接口")
    private Integer groupid;

    @ApiModelProperty("用户被打上的标签ID列表")
    @Excel(name = "用户被打上的标签ID列表")
    private String tagidList;

    @ApiModelProperty("用户是否订阅该公众号标识")
    @Excel(name = "用户是否订阅该公众号标识")
    private Integer subscribe;

    @ApiModelProperty("关注公众号时间")
    @Excel(name = "关注公众号时间")
    private Integer subscribeTime;

    @ApiModelProperty("小程序用户会话密匙")
    @Excel(name = "小程序用户会话密匙")
    private String sessionKey;

    @ApiModelProperty("token")
    @Excel(name = "token")
    private String accessToken;

    @ApiModelProperty("过期时间")
    @Excel(name = "过期时间")
    private Integer expiresIn;

    @ApiModelProperty("刷新token")
    @Excel(name = "刷新token")
    private String refreshToken;

    @ApiModelProperty("过期时间")
    @Excel(name = "过期时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime expireTime;

}
