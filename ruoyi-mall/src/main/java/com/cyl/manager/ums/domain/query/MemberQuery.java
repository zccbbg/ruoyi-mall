package com.cyl.manager.ums.domain.query;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 会员信息 查询 对象
 *
 * @author zcc
 */
@ApiModel(description="会员信息 查询 对象")
@Data
public class MemberQuery {
    @ApiModelProperty("昵称 精确匹配")
    private String nickname;

    @ApiModelProperty("密码 精确匹配")
    private String password;

    @ApiModelProperty("手机号码 精确匹配")
    private String phone;

    @ApiModelProperty("有无备注 1：有备注  0：无备注")
    private Integer hasMark;

    @ApiModelProperty("用户备注 精确匹配")
    private String mark;

    @ApiModelProperty("帐号启用状态:0->禁用；1->启用 精确匹配")
    private Integer status;

    @ApiModelProperty("头像 精确匹配")
    private String avatar;

    @ApiModelProperty("性别：0->未知；1->男；2->女 精确匹配")
    private Integer gender;

    @ApiModelProperty("用户所在城市 精确匹配")
    private String city;

    @ApiModelProperty("用户所在省份 精确匹配")
    private String province;

    @ApiModelProperty("用户所在国家 精确匹配")
    private String country;

    @ApiModelProperty("生日 精确匹配")
    private LocalDate birthday;

    @ApiModelProperty("推广员id 精确匹配")
    private Long spreadUid;

    @ApiModelProperty("推广员关联时间 精确匹配")
    private LocalDateTime spreadTime;

    @ApiModelProperty("等级 精确匹配")
    private Integer level;

    @ApiModelProperty("用户剩余积分 精确匹配")
    private BigDecimal integral;

    private String beginTime;

    private String endTime;
}
