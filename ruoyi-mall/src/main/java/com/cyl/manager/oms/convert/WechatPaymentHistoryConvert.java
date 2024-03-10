package com.cyl.manager.oms.convert;

import org.mapstruct.Mapper;
import com.cyl.manager.oms.domain.entity.WechatPaymentHistory;
import com.cyl.manager.oms.domain.vo.WechatPaymentHistoryVO;
import java.util.List;
/**
 * 微信订单表  DO <=> DTO <=> VO / BO / Query
 *
 * @author zcc
 */
@Mapper(componentModel = "spring")
public interface WechatPaymentHistoryConvert {

    List<WechatPaymentHistoryVO> dos2vos(List<WechatPaymentHistory> list);
}
