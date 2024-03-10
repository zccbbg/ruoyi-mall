package com.cyl.manager.act.convert;

import org.mapstruct.Mapper;
import com.cyl.manager.act.domain.entity.IntegralHistory;
import com.cyl.manager.act.domain.vo.IntegralHistoryVO;
import java.util.List;
/**
 * 积分流水表  DO <=> DTO <=> VO / BO / Query
 *
 * @author zcc
 */
@Mapper(componentModel = "spring")
public interface IntegralHistoryConvert  {

    List<IntegralHistoryVO> dos2vos(List<IntegralHistory> list);
}
