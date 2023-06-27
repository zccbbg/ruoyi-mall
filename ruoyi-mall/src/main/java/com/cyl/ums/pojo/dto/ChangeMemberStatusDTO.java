package com.cyl.ums.pojo.dto;

import lombok.Data;

@Data
public class ChangeMemberStatusDTO {
    private Long memberId;
    private Integer status;
}
