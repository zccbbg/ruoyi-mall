package com.cyl.manager.ums.domain.form;

import lombok.Data;

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
    private Integer buyNum;
    private Long productId;
    private String pic;
    private String productName;
    private String spData;
    private Integer quantity;
}
