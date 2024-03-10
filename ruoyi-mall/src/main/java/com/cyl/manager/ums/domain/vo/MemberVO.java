package com.cyl.manager.ums.domain.vo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import com.ruoyi.common.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;
/**
 * 会员信息 数据视图对象
 * 
 * @author zcc
 */
@Data
public class MemberVO extends BaseAudit {
   /** ID */
    private Long id;
   /** 昵称 */
    @Excel(name = "昵称")
    private String nickname;
    /** 手机号 */
    private String phone;
    /** 隐藏前三位后四位的手机号 */
    private String phoneHidden;
   /** 用户备注 */
    @Excel(name = "用户备注")
    private String mark;
   /** 帐号启用状态:0->禁用；1->启用 */
    @Excel(name = "帐号启用状态:0->禁用；1->启用")
    private Integer status;
   /** 头像 */
    @Excel(name = "头像")
    private String avatar;
   /** 性别：0->未知；1->男；2->女 */
    @Excel(name = "性别：0->未知；1->男；2->女")
    private Integer gender;
   /** 用户所在城市 */
    @Excel(name = "用户所在城市")
    private String city;
   /** 用户所在省份 */
    @Excel(name = "用户所在省份")
    private String province;
   /** 用户所在国家 */
    @Excel(name = "用户所在国家")
    private String country;
   /** 公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注 */
    @Excel(name = "公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注")
    private String remark;
   /** 生日 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "生日", width = 30, dateFormat = "yyyy-MM-dd")
    private LocalDate birthday;
   /** 推广员id */
    @Excel(name = "推广员id")
    private Long spreadUid;
   /** 推广员关联时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "推广员关联时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime spreadTime;
   /** 等级 */
    @Excel(name = "等级")
    private Integer level;
   /** 用户剩余积分 */
    @Excel(name = "用户剩余积分")
    private BigDecimal integral;
    /** openId */
    @Excel(name = "openId")
    private String openId;
}
