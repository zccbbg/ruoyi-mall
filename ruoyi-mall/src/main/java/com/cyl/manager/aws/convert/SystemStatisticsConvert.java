package com.cyl.manager.aws.convert;

import org.mapstruct.Mapper;
import com.cyl.manager.aws.domain.entity.SystemStatistics;
import com.cyl.manager.aws.domain.vo.SystemStatisticsVO;
import java.util.List;
/**
 * 系统数据统计  DO <=> DTO <=> VO / BO / Query
 *
 * @author zcc
 */
@Mapper(componentModel = "spring")
public interface SystemStatisticsConvert  {

    List<SystemStatisticsVO> dos2vos(List<SystemStatistics> list);
}
