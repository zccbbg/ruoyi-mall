package com.cyl.manager.ums.convert;

import org.mapstruct.Mapper;
import com.cyl.manager.ums.domain.entity.Feedback;
import com.cyl.manager.ums.domain.vo.FeedbackVO;
import java.util.List;
/**
 * 意见反馈  DO <=> VO / BO
 *
 * @author zcc
 */
@Mapper(componentModel = "spring")
public interface FeedbackConvert  {

    List<FeedbackVO> dos2vos(List<Feedback> list);
}
