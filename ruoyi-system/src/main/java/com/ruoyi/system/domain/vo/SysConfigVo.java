package com.ruoyi.system.domain.vo;

import lombok.Data;

@Data
public class SysConfigVo {

    private Long configId;

    private String configKey;

    private String configName;

    private String configValue;

    private String configType;
}
