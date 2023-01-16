package com.cyl.h5.pojo.dto;

import lombok.Data;

import java.util.List;

@Data
public class CategoryDTO {
    private Long id;
    private Integer sort;
    private String name;
    private String icon;
    private List<ProductDTO> productList;
}
