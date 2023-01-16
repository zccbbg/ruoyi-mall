package com.cyl.h5.pojo.dto;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class ProductDTO {
    private Long id;
    private String pic;
    private String name;
    private BigDecimal price;
}
