package com.cyl.h5.domain.form;
import lombok.Data;

import java.util.List;

@Data
/** 取消订单请求 */
public class CancelOrderForm {
    /** 要取消的订单id集合 */
    private List<Long> idList;
}
