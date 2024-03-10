package com.cyl.manager.ums.convert;

import com.cyl.manager.pms.domain.entity.Product;
import com.cyl.manager.pms.domain.entity.Sku;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import com.cyl.manager.ums.domain.entity.MemberCart;
import com.cyl.manager.ums.domain.vo.MemberCartVO;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

/**
 * 购物车  DO <=> DTO <=> VO / BO / Query
 *
 * @author zcc
 */
@Mapper(componentModel = "spring")
public interface MemberCartConvert {

    List<MemberCartVO> dos2vos(List<MemberCart> list);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "skuId", source = "id")
    MemberCart sku2Cart(Sku sku);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(source = "name", target = "productName")
    void injectProduct(@MappingTarget MemberCart memberCart, Product p);
}
