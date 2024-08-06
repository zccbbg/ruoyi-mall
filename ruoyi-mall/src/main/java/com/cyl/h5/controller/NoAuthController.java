package com.cyl.h5.controller;

import cn.hutool.core.util.RandomUtil;
import com.cyl.h5.domain.vo.HomeConfigVO;
import com.cyl.manager.pms.service.ProductCategoryService;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysDictData;
import com.ruoyi.common.core.redis.RedisService;
import com.ruoyi.system.service.ISysConfigService;
import com.ruoyi.system.service.ISysDictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/no-auth")
public class NoAuthController {
    @Autowired
    private ISysConfigService sysConfigService;
    @Autowired
    private ProductCategoryService categoryService;
    @Autowired
    private ISysDictTypeService dictTypeService;
    @Autowired
    private RedisService redisService;

    /**
     * 首页配置
     *
     * @return 首页配置
     */
    @GetMapping("/home/home-cfg")
    public ResponseEntity<HomeConfigVO> getHomeConfig() {
        HomeConfigVO res = new HomeConfigVO();
        res.setBanners(sysConfigService.selectConfigByKey("h5.home.banner"));
        res.setCategoryList(categoryService.queryCategoryWithProductsForH5());
        return ResponseEntity.ok(res);
    }
    /**
     * 首页配置
     *
     * @return 首页配置
     */
    @GetMapping("/home/product-count")
    public ResponseEntity<HomeConfigVO> productCount() {
        HomeConfigVO res = new HomeConfigVO();
        res.setBanners(sysConfigService.selectConfigByKey("h5.home.banner"));
        res.setCategoryList(categoryService.queryCategoryWithProductsForH5());
        return ResponseEntity.ok(res);
    }

    /**
     * 获取应用账号
     */
    @GetMapping("/app/account/{type}")
    public ResponseEntity<String> getAppAccount(@PathVariable String type) {
        List<SysDictData> sysAppAccount = dictTypeService.selectDictDataByType("sys_app_account");
        SysDictData sysDictData = sysAppAccount.stream().filter(it -> it.getDictValue().equals(type)).findFirst().orElseGet(SysDictData::new);
        return ResponseEntity.ok(sysDictData.getDictLabel());
    }

    @GetMapping("/verified/code/generate")
    public AjaxResult createCode(){
        String code = RandomUtil.randomNumbers(6);
        redisService.setVerifyCode(code);
        return AjaxResult.successData(code);
    }
}
