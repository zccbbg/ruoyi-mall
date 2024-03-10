package com.cyl.h5.domain.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel("取消订单请求")
public class CancelOrderForm {
    @ApiModelProperty("要取消的订单id集合")
    private List<Long> idList;
}
