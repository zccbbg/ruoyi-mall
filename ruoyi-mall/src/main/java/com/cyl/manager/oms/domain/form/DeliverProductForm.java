package com.cyl.manager.oms.domain.form;
import com.ruoyi.common.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class DeliverProductForm {
    @ApiModelProperty("订单id")
    @NotBlank(message = "订单id不能为空")
    @Excel(name = "订单号")
    private Long orderId;

    @ApiModelProperty("快递名称")
    @NotBlank(message = "快递名称不能为空")
    @Excel(name = "快递公司")
    private String expressName;

    @ApiModelProperty("快递单号")
    @NotBlank(message = "快递单号不能为空")
    @Excel(name = "运单号")
    private String expressSn;

}
