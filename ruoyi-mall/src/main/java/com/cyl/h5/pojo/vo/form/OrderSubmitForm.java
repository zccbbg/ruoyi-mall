package com.cyl.h5.pojo.vo.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class OrderSubmitForm {
    @NotNull
    private Long addressId;
    private String note;
    @NotEmpty
    private List<SkuParam> skus;
    @Data
    public static class SkuParam {
        private Long skuId;
        private Integer quantity;
    }
}
