package com.cyl.pms.pojo.dto;

import com.cyl.ums.domain.MemberCart;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class MemberCartDTO extends MemberCart {
    private BigDecimal price;
}
