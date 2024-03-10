package com.cyl.manager.ums.domain.query;

import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 会员收货地址 查询 对象
 *
 * @author zcc
 */
@ApiModel(description="会员收货地址 查询 对象")
@Data
public class MemberAddressQuery {
    @ApiModelProperty("MEMBER_ID 精确匹配")
    private Long memberId;

    @ApiModelProperty("收货人名称 精确匹配")
    private String nameLike;

    @ApiModelProperty("PHONE 精确匹配")
    private String phone;

    @ApiModelProperty("是否为默认 精确匹配")
    private Integer defaultStatus;

    @ApiModelProperty("邮政编码 精确匹配")
    private String postCode;

    @ApiModelProperty("省份/直辖市 精确匹配")
    private String province;

    @ApiModelProperty("城市 精确匹配")
    private String city;

    @ApiModelProperty("区 精确匹配")
    private String district;

    @ApiModelProperty("详细地址 精确匹配")
    private String detailAddress;

    @ApiModelProperty("是否默认 精确匹配")
    private Integer isDefault;

}
