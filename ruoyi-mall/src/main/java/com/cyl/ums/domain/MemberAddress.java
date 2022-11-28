package com.cyl.ums.domain;

import java.time.LocalDateTime;
import com.ruoyi.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 会员收货地址对象 ums_member_address
 * 
 * @author zcc
 */
@ApiModel(description="会员收货地址对象")
@Data
@TableName("ums_member_address")
public class MemberAddress {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("ID")
    private Long id;

    @ApiModelProperty("MEMBER_ID")
    @Excel(name = "MEMBER_ID")
    private Long memberId;

    @ApiModelProperty("收货人名称")
    @Excel(name = "收货人名称")
    private String name;

    @ApiModelProperty("PHONE")
    @Excel(name = "PHONE")
    private String phone;

    @ApiModelProperty("是否为默认")
    @Excel(name = "是否为默认")
    private Integer defaultStatus;

    @ApiModelProperty("邮政编码")
    @Excel(name = "邮政编码")
    private String postCode;

    @ApiModelProperty("省份/直辖市")
    @Excel(name = "省份/直辖市")
    private String province;

    @ApiModelProperty("城市")
    @Excel(name = "城市")
    private String city;

    @ApiModelProperty("区")
    @Excel(name = "区")
    private String district;

    @ApiModelProperty("详细地址(街道)")
    @Excel(name = "详细地址(街道)")
    private String detailAddress;

    @ApiModelProperty("是否默认")
    @Excel(name = "是否默认")
    private Integer isDefault;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("修改时间")
    private LocalDateTime updateTime;

}
