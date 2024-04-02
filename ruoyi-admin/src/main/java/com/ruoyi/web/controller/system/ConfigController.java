package com.ruoyi.web.controller.system;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.SysConfig;
import com.ruoyi.system.domain.vo.SysConfigVo;
import com.ruoyi.system.service.ISysConfigService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sys/config")
public class ConfigController extends BaseController {

    @Autowired
    private ISysConfigService configService;

    @PreAuthorize("@ss.hasAnyRoles('admin,viewer')")
    @GetMapping(value = "/configKey2/{configKey}")
    public AjaxResult getConfigKey2(@PathVariable String configKey) {
        return AjaxResult.success(configService.selectConfigByKey2(configKey));
    }

    @PreAuthorize("@ss.hasAnyRoles(admin)")
    @PostMapping("/addOrUpdate")
    public AjaxResult addOrUpdate(@RequestBody SysConfigVo content) {
        SysConfig config = new SysConfig();
        BeanUtils.copyProperties(content,config);
        if (config.getConfigId() == null) {
            config.setCreateBy(getUserId());
            return toAjax(configService.insertConfig(config));
        }
        config.setUpdateBy(getUserId());
        return toAjax(configService.updateConfig(config));
    }
}
