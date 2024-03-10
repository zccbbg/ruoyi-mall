package com.cyl.h5.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@ApiModel("下单前校验返回数据")
public class OrderCalcVO {
    @ApiModelProperty("sku数据")
    private List<SkuViewDTO> skuList;
    @ApiModelProperty("商品总金额")
    private BigDecimal productTotalAmount;
    @ApiModelProperty("订单总金额")
    private BigDecimal orderTotalAmount;
}
