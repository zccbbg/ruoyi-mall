package com.cyl.manager.act.domain.vo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.cyl.manager.pms.domain.entity.Product;
import com.ruoyi.common.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 优惠券活动表 数据视图对象
 *
 * @author zcc
 */
@Data
public class CouponActivityVO {
    /**
     * ID
     */
    private Long id;
    /**
     * 活动名称
     */
    @Excel(name = "活动名称")
    private String title;
    /**
     * 使用范围  1全场通用 2指定商品可用 3指定商品不可用
     */
    @Excel(name = "使用范围  1全场通用 2指定商品可用 3指定商品不可用")
    private Integer useScope;
    /**
     * 商品id集合，逗号分隔
     */
    @Excel(name = "商品id集合，逗号分隔")
    private String productIds;
    /**
     * 发行总数
     */
    @Excel(name = "发行总数")
    private Integer totalCount;
    /**
     * 剩余总数
     */
    @Excel(name = "剩余总数")
    private Integer leftCount;
    /**
     * 每人限领
     */
    @Excel(name = "每人限领")
    private Integer userLimit;
    /**
     * 优惠券金额
     */
    @Excel(name = "优惠券金额")
    private BigDecimal couponAmount;
    /**
     * 最低消费金额
     */
    @Excel(name = "最低消费金额")
    private BigDecimal minAmount;
    /**
     * 要兑换的积分
     */
    @Excel(name = "要兑换的积分")
    private BigDecimal useIntegral;
    /**
     * 1免费兑换  2积分兑换
     */
    @Excel(name = "1免费兑换  2积分兑换")
    private Integer couponType;
    /**
     * 活动开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "活动开始时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime beginTime;
    /**
     * 活动结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "活动结束时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    private List<Product> productList;

    //使用的张数
    private Integer useCount;

    private Boolean canGet;

    //获取的张数
    private Integer getCount;
}
