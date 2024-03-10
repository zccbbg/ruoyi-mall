package com.cyl.manager.ums.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import com.ruoyi.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 会员信息对象 ums_member
 * 
 * @author zcc
 */
@ApiModel(description="会员信息对象")
@Data
@TableName("ums_member")
public class Member extends BaseAudit {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("ID")
    private Long id;

    @ApiModelProperty("昵称")
    @Excel(name = "昵称")
    private String nickname;

    @ApiModelProperty("密码")
    @Excel(name = "密码")
    private String password;

    @ApiModelProperty("加密手机号")
    private String phoneEncrypted;

    @ApiModelProperty("隐藏前三位后四位的手机号")
    private String phoneHidden;

    @ApiModelProperty("用户备注")
    @Excel(name = "用户备注")
    private String mark;

    @ApiModelProperty("帐号启用状态:0->禁用；1->启用")
    @Excel(name = "帐号启用状态:0->禁用；1->启用")
    private Integer status;

    @ApiModelProperty("头像")
    @Excel(name = "头像")
    private String avatar;

    @ApiModelProperty("性别：0->未知；1->男；2->女")
    @Excel(name = "性别：0->未知；1->男；2->女")
    private Integer gender;

    @ApiModelProperty("用户所在城市")
    @Excel(name = "用户所在城市")
    private String city;

    @ApiModelProperty("用户所在省份")
    @Excel(name = "用户所在省份")
    private String province;

    @ApiModelProperty("用户所在国家")
    @Excel(name = "用户所在国家")
    private String country;

    @ApiModelProperty("公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注")
    @Excel(name = "公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注")
    private String remark;

    @ApiModelProperty("生日")
    @Excel(name = "生日", width = 30, dateFormat = "yyyy-MM-dd")
    private LocalDate birthday;

    @ApiModelProperty("推广员id")
    @Excel(name = "推广员id")
    private Long spreadUid;

    @ApiModelProperty("推广员关联时间")
    @Excel(name = "推广员关联时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime spreadTime;

    @ApiModelProperty("等级")
    @Excel(name = "等级")
    private Integer level;

    @ApiModelProperty("用户剩余积分")
    @Excel(name = "用户剩余积分")
    private BigDecimal integral;

}
