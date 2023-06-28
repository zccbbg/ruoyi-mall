package com.cyl.manager.pms.pojo.dto;

import com.cyl.manager.ums.domain.MemberCart;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class MemberCartDTO extends MemberCart {
    private BigDecimal price;
}
