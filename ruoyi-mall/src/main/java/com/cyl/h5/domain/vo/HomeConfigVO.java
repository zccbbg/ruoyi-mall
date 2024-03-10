package com.cyl.h5.domain.vo;

import com.cyl.h5.domain.dto.CategoryDTO;
import lombok.Data;

import java.util.List;

@Data
public class HomeConfigVO {
    // 头部广告栏
    private String banners;
    // 品类列表
    private List<CategoryDTO> categoryList;
}
