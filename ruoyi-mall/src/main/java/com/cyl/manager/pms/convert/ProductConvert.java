package com.cyl.manager.pms.convert;

import com.cyl.h5.domain.vo.H5ProductVO;
import com.cyl.manager.pms.domain.entity.Product;
import com.cyl.manager.pms.domain.vo.ProductVO;
import org.mapstruct.Mapper;

import java.util.List;
/**
 * 商品信息  DO <=> DTO <=> VO / BO / Query
 *
 * @author zcc
 */
@Mapper(componentModel = "spring")
public interface ProductConvert  {

    List<ProductVO> dos2vos(List<Product> list);
    Product vo2do(ProductVO productVO);
    ProductVO do2vo(Product product);

    List<H5ProductVO> dos2dtos(List<Product> products);
}
