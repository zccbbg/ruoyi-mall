package com.cyl.manager.act.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
/**
 * 用户领券记录对象 act_member_coupon
 * 
 * @author zcc
 */
@ApiModel(description="用户领券记录对象")
@Data
@TableName("act_member_coupon")
public class MemberCoupon {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("ID")
    private Long id;

    @ApiModelProperty("活动id")
    @Excel(name = "活动id")
    private Long couponActivityId;

    @ApiModelProperty("用户id")
    @Excel(name = "用户id")
    private Long memberId;

    @ApiModelProperty("活动名称")
    @Excel(name = "活动名称")
    private String title;

    @ApiModelProperty("使用范围  1全场通用 2指定商品可用 3指定商品不可用")
    @Excel(name = "使用范围  1全场通用 2指定商品可用 3指定商品不可用")
    private Integer useScope;

    @ApiModelProperty("商品id集合，逗号分隔")
    @Excel(name = "商品id集合，逗号分隔")
    private String productIds;

    @ApiModelProperty("优惠券金额")
    @Excel(name = "优惠券金额")
    private BigDecimal couponAmount;

    @ApiModelProperty("最低消费金额")
    @Excel(name = "最低消费金额")
    private BigDecimal minAmount;

    @ApiModelProperty("要兑换的积分")
    @Excel(name = "要兑换的积分")
    private BigDecimal useIntegral;

    @ApiModelProperty("1免费兑换  2积分兑换")
    @Excel(name = "1免费兑换  2积分兑换")
    private Integer couponType;

    @ApiModelProperty("券开始时间")
    @Excel(name = "券开始时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime beginTime;

    @ApiModelProperty("券结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "券结束时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;

    @ApiModelProperty("0未使用  1已使用")
    @Excel(name = "0未使用  1已使用")
    private Integer useStatus;

    @ApiModelProperty("订单id")
    @Excel(name = "订单id")
    private Long orderId;

    @ApiModelProperty("使用时间")
    @Excel(name = "使用时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime useTime;

    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

}
