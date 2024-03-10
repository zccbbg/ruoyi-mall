package com.cyl.manager.ums.domain.query;

import java.time.LocalDateTime;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 会员登录记录 查询 对象
 *
 * @author zcc
 */
@ApiModel(description="会员登录记录 查询 对象")
@Data
public class MemberLogininforQuery {
    @ApiModelProperty("会员手机号 精确匹配")
    private String phone;

    @ApiModelProperty("会员id 精确匹配")
    private Long memberId;

    @ApiModelProperty("登录IP地址 精确匹配")
    private String ipaddr;

    @ApiModelProperty("登录地点 精确匹配")
    private String loginLocation;

    @ApiModelProperty("浏览器类型 精确匹配")
    private String browser;

    @ApiModelProperty("操作系统 精确匹配")
    private String os;

    @ApiModelProperty("登陆时间 精确匹配")
    private LocalDateTime loginTime;

    private String beginTime;

    private String endTime;

}
