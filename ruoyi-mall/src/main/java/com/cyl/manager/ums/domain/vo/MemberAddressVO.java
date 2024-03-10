package com.cyl.manager.ums.domain.vo;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;
/**
 * 会员收货地址 数据视图对象
 * 
 * @author zcc
 */
@Data
public class MemberAddressVO extends BaseAudit {
   /** ID */
    private Long id;
   /** MEMBER_ID */
    @Excel(name = "MEMBER_ID")
    private Long memberId;
   /** 收货人名称 */
    @Excel(name = "收货人名称")
    private String name;
   /** PHONE */
    @Excel(name = "PHONE")
    private String phone;
    @Excel(name = "隐藏前三位后四位的手机号")
    private String phoneHidden;
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
   /** 详细地址(街道) */
    @Excel(name = "详细地址(街道)")
    private String detailAddress;
   /** 是否默认 */
    @Excel(name = "是否默认")
    private Integer isDefault;
}
