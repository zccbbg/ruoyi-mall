package com.cyl.manager.pms.convert;

import org.mapstruct.Mapper;
import com.cyl.manager.pms.domain.entity.Brand;
import com.cyl.manager.pms.domain.vo.BrandVO;
import java.util.List;
/**
 * 品牌管理  DO <=> DTO <=> VO / BO / Query
 *
 * @author zcc
 */
@Mapper(componentModel = "spring")
public interface BrandConvert  {

    List<BrandVO> dos2vos(List<Brand> list);
}
