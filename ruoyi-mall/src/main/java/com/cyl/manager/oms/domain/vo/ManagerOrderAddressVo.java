package com.cyl.manager.oms.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author: Jinxin
 * @date: 2022/4/22 14:12
 * @Description:
 */
@Getter
@Setter
@ApiModel("订单收获地址")
public class ManagerOrderAddressVo {
    @ApiModelProperty("收货人姓名")
    private String name;
    @ApiModelProperty("收货人手机号")
    private String userPhone;
    @ApiModelProperty("收获区域")
    private String area;
    @ApiModelProperty("详细地址")
    private String address;

}
