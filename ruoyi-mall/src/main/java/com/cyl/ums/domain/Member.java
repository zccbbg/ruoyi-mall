package com.cyl.ums.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import com.ruoyi.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
public class Member {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("ID")
    private Long id;

    @ApiModelProperty("昵称")
    @Excel(name = "昵称")
    private String nickname;

    @ApiModelProperty("密码")
    @Excel(name = "密码")
    private String password;

    @ApiModelProperty("手机号码")
    @Excel(name = "手机号码")
    private String phone;

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

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("修改时间")
    private LocalDateTime updateTime;

}
