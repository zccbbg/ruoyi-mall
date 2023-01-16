package com.cyl.pms.convert;

import com.cyl.h5.pojo.dto.ProductDTO;
import org.mapstruct.Mapper;
import com.cyl.pms.domain.Product;
import com.cyl.pms.pojo.vo.ProductVO;
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

    List<ProductDTO> dos2dtos(List<Product> products);
}
