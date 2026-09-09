package com.cyl.manager.oms.domain.query;

import lombok.Data;

/**
 * 订单操作历史记录 查询 对象
 *
 * @author zcc
 */
@Data
public class OrderOperateHistoryQuery {
    /** 订单号 精确匹配 */
    private String orderSn;

    /** 操作人：用户；系统；后台管理员 精确匹配 */
    private String operateMan;

    /** 订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单 精确匹配 */
    private Integer orderStatus;

    /** 备注 精确匹配 */
    private String note;

}
