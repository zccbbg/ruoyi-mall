package com.cyl.manager.act.domain.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class IntegralStatVO {
    private BigDecimal balance = BigDecimal.ZERO;

    private BigDecimal income = BigDecimal.ZERO;

    private BigDecimal expenditure = BigDecimal.ZERO;
}
