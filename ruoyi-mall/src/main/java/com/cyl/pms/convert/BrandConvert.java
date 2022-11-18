package com.cyl.pms.convert;

import org.mapstruct.Mapper;
import com.cyl.pms.domain.Brand;
import com.cyl.pms.pojo.vo.BrandVO;
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
