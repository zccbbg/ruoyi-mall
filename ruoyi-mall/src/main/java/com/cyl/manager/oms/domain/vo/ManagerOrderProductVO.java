package com.cyl.manager.oms.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@ApiModel("订单")
public class ManagerOrderProductVO {
    @ApiModelProperty("商品id")
    private Long productId;
    @ApiModelProperty("商品名称")
    private String productName;
    @ApiModelProperty("商品规格")
    private String spData;
    @ApiModelProperty("商品图片")
    private String pic;
    @ApiModelProperty("购买数量")
    private Integer buyNum;
    @ApiModelProperty("销售价格")
    private BigDecimal salePrice;

}
