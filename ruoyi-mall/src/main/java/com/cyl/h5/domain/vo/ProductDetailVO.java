package com.cyl.h5.domain.vo;

import com.cyl.manager.pms.domain.entity.Brand;
import com.cyl.manager.pms.domain.entity.Product;
import com.cyl.manager.pms.domain.entity.Sku;
import lombok.Data;

import java.util.List;

@Data
public class ProductDetailVO {
    private Product product;
    private List<Sku> skus;
    private Brand brand;
}
