package com.cyl.manager.ums.domain.vo;

import java.time.LocalDateTime;
import com.ruoyi.common.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;
/**
 * 用户微信信息 数据视图对象
 * 
 * @author zcc
 */
@Data
public class MemberWechatVO extends BaseAudit {
   /** ID */
    private Long id;
   /** MEMBER_ID */
    @Excel(name = "MEMBER_ID")
    private Long memberId;
   /** 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段 */
    @Excel(name = "只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段")
    private String unionid;
   /** 用户的标识，对当前公众号唯一 */
    @Excel(name = "用户的标识，对当前公众号唯一")
    private String openid;
   /** 小程序唯一身份ID */
    @Excel(name = "小程序唯一身份ID")
    private String routineOpenid;
   /** 用户所在的分组ID（兼容旧的用户分组接口） */
    @Excel(name = "用户所在的分组ID", readConverterExp = "兼=容旧的用户分组接口")
    private Integer groupid;
   /** 用户被打上的标签ID列表 */
    @Excel(name = "用户被打上的标签ID列表")
    private String tagidList;
   /** 用户是否订阅该公众号标识 */
    @Excel(name = "用户是否订阅该公众号标识")
    private Integer subscribe;
   /** 关注公众号时间 */
    @Excel(name = "关注公众号时间")
    private Integer subscribeTime;
   /** 小程序用户会话密匙 */
    @Excel(name = "小程序用户会话密匙")
    private String sessionKey;
   /** token */
    @Excel(name = "token")
    private String accessToken;
   /** 过期时间 */
    @Excel(name = "过期时间")
    private Integer expiresIn;
   /** 刷新token */
    @Excel(name = "刷新token")
    private String refreshToken;
   /** 过期时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "过期时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime expireTime;
}
