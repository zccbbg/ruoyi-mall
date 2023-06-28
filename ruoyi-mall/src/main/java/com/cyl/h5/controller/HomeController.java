package com.cyl.h5.controller;

import com.cyl.h5.pojo.vo.HomeConfigVO;
import com.cyl.manager.pms.service.ProductCategoryService;
import com.ruoyi.system.service.ISysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/no-auth/home")
public class HomeController {
    @Autowired
    private ISysConfigService sysConfigService;
    @Autowired
    private ProductCategoryService categoryService;

    /**
     * 首页配置
     *
     * @return 首页配置
     */
    @GetMapping("/home-cfg")
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
    @GetMapping("/product-count")
    public ResponseEntity<HomeConfigVO> productCount() {
        HomeConfigVO res = new HomeConfigVO();
        res.setBanners(sysConfigService.selectConfigByKey("h5.home.banner"));
        res.setCategoryList(categoryService.queryCategoryWithProductsForH5());
        return ResponseEntity.ok(res);
    }
}
