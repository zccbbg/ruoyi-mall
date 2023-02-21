package com.cyl.ums.pojo.vo.form;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class AddMemberCartForm {
    /**
     * 规格id
     */
    @NotNull(message = "规格必填！")
    private Long skuId;
    /**
     * 数量
     */
    @NotNull(message = "数量必填！")
    @Min(value = 1, message = "数量不小于1")
    @Max(value = Integer.MAX_VALUE, message = "数量不大于" + Integer.MAX_VALUE)
    private Integer num;
}
