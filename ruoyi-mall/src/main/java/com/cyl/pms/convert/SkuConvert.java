package com.cyl.pms.convert;

import org.mapstruct.Mapper;
import com.cyl.pms.domain.Sku;
import com.cyl.pms.pojo.vo.SkuVO;
import java.util.List;
/**
 * sku信息  DO <=> DTO <=> VO / BO / Query
 *
 * @author zcc
 */
@Mapper(componentModel = "spring")
public interface SkuConvert  {

    List<SkuVO> dos2vos(List<Sku> list);
}
