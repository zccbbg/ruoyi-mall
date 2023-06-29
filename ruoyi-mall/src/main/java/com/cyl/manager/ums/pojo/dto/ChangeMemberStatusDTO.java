package com.cyl.manager.ums.pojo.dto;

import lombok.Data;

@Data
public class ChangeMemberStatusDTO {
    private Long memberId;
    private Integer status;
}
