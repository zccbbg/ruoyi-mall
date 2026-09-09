package com.cyl.manager.ums.domain.query;

import lombok.Data;

/**
 * 会员收货地址 查询 对象
 *
 * @author zcc
 */
@Data
public class MemberAddressQuery {
    /** MEMBER_ID 精确匹配 */
    private Long memberId;

    /** 收货人名称 精确匹配 */
    private String nameLike;

    /** PHONE 精确匹配 */
    private String phone;

    /** 是否为默认 精确匹配 */
    private Integer defaultStatus;

    /** 邮政编码 精确匹配 */
    private String postCode;

    /** 省份/直辖市 精确匹配 */
    private String province;

    /** 城市 精确匹配 */
    private String city;

    /** 区 精确匹配 */
    private String district;

    /** 详细地址 精确匹配 */
    private String detailAddress;

    /** 是否默认 精确匹配 */
    private Integer isDefault;

}
