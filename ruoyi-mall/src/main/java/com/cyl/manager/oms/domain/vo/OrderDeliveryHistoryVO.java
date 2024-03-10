package com.cyl.manager.oms.domain.vo;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;
/**
 * 订单发货记录 数据视图对象
 * 
 * @author zcc
 */
@Data
public class OrderDeliveryHistoryVO extends BaseAudit {
   /** ID */
    private Long id;
   /** 订单id */
    @Excel(name = "订单id")
    private Long orderId;
   /** 物流公司(配送方式) */
    @Excel(name = "物流公司(配送方式)")
    private String deliveryCompany;
   /** 物流单号 */
    @Excel(name = "物流单号")
    private String deliverySn;
}
