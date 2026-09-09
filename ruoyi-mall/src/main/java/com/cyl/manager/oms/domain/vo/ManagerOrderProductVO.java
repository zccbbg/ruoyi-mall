package com.cyl.manager.oms.domain.vo;
import lombok.Data;

import java.math.BigDecimal;

@Data
/** 订单 */
public class ManagerOrderProductVO {
    /** 商品id */
    private Long productId;
    /** 商品名称 */
    private String productName;
    /** 商品规格 */
    private String spData;
    /** 商品图片 */
    private String pic;
    /** 购买数量 */
    private Integer buyNum;
    /** 销售价格 */
    private BigDecimal salePrice;

}
