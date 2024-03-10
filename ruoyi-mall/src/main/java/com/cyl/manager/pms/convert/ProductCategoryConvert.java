package com.cyl.manager.pms.convert;

import com.cyl.h5.domain.dto.CategoryDTO;
import org.mapstruct.Mapper;
import com.cyl.manager.pms.domain.entity.ProductCategory;
import com.cyl.manager.pms.domain.vo.ProductCategoryVO;
import java.util.List;
/**
 * 商品分类  DO <=> DTO <=> VO / BO / Query
 *
 * @author zcc
 */
@Mapper(componentModel = "spring")
public interface ProductCategoryConvert  {

    List<ProductCategoryVO> dos2vos(List<ProductCategory> list);

    CategoryDTO do2dto(ProductCategory it);
}
