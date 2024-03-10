package com.cyl.h5.domain.dto;

import com.cyl.h5.domain.vo.H5ProductVO;
import lombok.Data;

import java.util.List;

@Data
public class CategoryDTO {
    private Long id;
    private Integer sort;
    private String name;
    private String icon;
    private List<H5ProductVO> productList;
}
