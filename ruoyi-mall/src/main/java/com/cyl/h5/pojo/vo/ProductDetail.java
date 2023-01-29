package com.cyl.h5.pojo.vo;

import com.cyl.pms.domain.Brand;
import com.cyl.pms.domain.Product;
import com.cyl.pms.domain.Sku;
import lombok.Data;

import java.util.List;

@Data
public class ProductDetail {
    private Product product;
    private List<Sku> skus;
    private Brand brand;
}
