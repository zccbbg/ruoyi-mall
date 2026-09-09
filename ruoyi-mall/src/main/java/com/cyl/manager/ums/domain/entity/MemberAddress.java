package com.cyl.manager.ums.domain.entity;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 会员收货地址对象 ums_member_address
 * 
 * @author zcc
 */
@Data
@TableName("ums_member_address")
public class MemberAddress extends BaseAudit {
    private static final long serialVersionUID = 1L;

    private Long id;

    /** MEMBER_ID */
    @Excel(name = "MEMBER_ID")
    private Long memberId;

    /** 收货人名称 */
    @Excel(name = "收货人名称")
    private String name;

    /** 隐藏前三位后四位的手机号 */
    @Excel(name = "隐藏前三位后四位的手机号")
    private String phoneHidden;

    /** 加密的手机号 */
    @Excel(name = "加密的手机号")
    private String phoneEncrypted;

    /** 是否为默认 */
    @Excel(name = "是否为默认")
    private Integer defaultStatus;

    /** 邮政编码 */
    @Excel(name = "邮政编码")
    private String postCode;

    /** 省份/直辖市 */
    @Excel(name = "省份/直辖市")
    private String province;

    /** 城市 */
    @Excel(name = "城市")
    private String city;

    /** 区 */
    @Excel(name = "区")
    private String district;

    /** 省份/直辖市id */
    @Excel(name = "省份/直辖市id")
    private Long provinceId;

    /** 城市id */
    @Excel(name = "城市id")
    private Long cityId;

    /** 区id */
    @Excel(name = "区id")
    private Long districtId;

    /** 详细地址(街道) */
    @Excel(name = "详细地址(街道)")
    private String detailAddress;

    /** 是否默认 */
    @Excel(name = "是否默认")
    private Integer isDefault;

}
