package com.cyl.manager.ums.convert;

import com.cyl.manager.pms.domain.Product;
import com.cyl.manager.pms.domain.Sku;
import com.cyl.manager.pms.pojo.dto.MemberCartDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import com.cyl.manager.ums.domain.MemberCart;
import com.cyl.manager.ums.pojo.vo.MemberCartVO;
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

    List<MemberCartDTO> dos2Dtos(List<MemberCart> list);
}
