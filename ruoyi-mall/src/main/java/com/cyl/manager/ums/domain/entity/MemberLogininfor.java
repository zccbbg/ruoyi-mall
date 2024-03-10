package com.cyl.manager.ums.domain.entity;

import java.time.LocalDateTime;
import com.ruoyi.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 会员登录记录对象 ums_member_logininfor
 * 
 * @author zcc
 */
@ApiModel(description="会员登录记录对象")
@Data
@TableName("ums_member_logininfor")
public class MemberLogininfor {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("ID")
    private Long id;

    @ApiModelProperty("会员手机号")
    @Excel(name = "会员手机号")
    private String phone;

    @ApiModelProperty("会员id")
    @Excel(name = "会员id")
    private Long memberId;

    @ApiModelProperty("登录IP地址")
    @Excel(name = "登录IP地址")
    private String ipaddr;

    @ApiModelProperty("登录地点")
    @Excel(name = "登录地点")
    private String loginLocation;

    @ApiModelProperty("浏览器类型")
    @Excel(name = "浏览器类型")
    private String browser;

    @ApiModelProperty("操作系统")
    @Excel(name = "操作系统")
    private String os;

    @ApiModelProperty("登陆时间")
    @Excel(name = "登陆时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime loginTime;

}
