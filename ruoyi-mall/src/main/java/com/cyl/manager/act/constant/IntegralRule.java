package com.cyl.manager.act.constant;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class IntegralRule {
    private Integer signStatus = 1;
    private BigDecimal signCount = BigDecimal.valueOf(1);
    private BigDecimal orderAmount = BigDecimal.valueOf(1);
    private BigDecimal orderCount = BigDecimal.valueOf(1);
}
