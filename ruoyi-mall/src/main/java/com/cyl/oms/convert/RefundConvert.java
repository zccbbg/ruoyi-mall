package com.cyl.oms.convert;

import org.mapstruct.Mapper;
import com.cyl.oms.domain.Refund;
import com.cyl.oms.pojo.vo.RefundVO;
import java.util.List;
/**
 * 订单售后  DO <=> DTO <=> VO / BO / Query
 *
 * @author zcc
 */
@Mapper(componentModel = "spring")
public interface RefundConvert  {

    List<RefundVO> dos2vos(List<Refund> list);
}
