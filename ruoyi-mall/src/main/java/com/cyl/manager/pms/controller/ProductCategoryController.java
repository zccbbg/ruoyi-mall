package com.cyl.manager.pms.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.enums.BusinessType;
import com.cyl.manager.pms.convert.ProductCategoryConvert;
import com.cyl.manager.pms.domain.entity.ProductCategory;
import com.cyl.manager.pms.domain.query.ProductCategoryQuery;
import com.cyl.manager.pms.service.ProductCategoryService;
import com.cyl.manager.pms.domain.vo.ProductCategoryVO;

/**
 * 商品分类Controller
 * 
 * @author zcc
 * @date 2022-11-28
 */
@RestController
@RequestMapping("/pms/productCategory")
public class ProductCategoryController extends BaseController {
    @Autowired
    private ProductCategoryService service;
    @Autowired
    private ProductCategoryConvert convert;

    @PreAuthorize("@ss.hasPermi('pms:productCategory:list')")
    @PostMapping("/list")
    public ResponseEntity<List<ProductCategoryVO>> list(@RequestBody ProductCategoryQuery query) {
        List<ProductCategoryVO> list = service.selectList(query, null);
        return ResponseEntity.ok(list);
    }

    @PreAuthorize("@ss.hasPermi('pms:productCategory:query')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductCategory> getInfo(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.selectById(id));
    }

    @PreAuthorize("@ss.hasPermi('pms:productCategory:add')")
    @Log(title = "商品分类", businessType = BusinessType.INSERT)
    @PostMapping
    public ResponseEntity<Integer> add(@RequestBody ProductCategory productCategory) {
        return ResponseEntity.ok(service.insert(productCategory));
    }

    @PreAuthorize("@ss.hasPermi('pms:productCategory:edit')")
    @Log(title = "商品分类", businessType = BusinessType.UPDATE)
    @PutMapping
    public ResponseEntity<Integer> edit(@RequestBody ProductCategory productCategory) {
        return ResponseEntity.ok(service.update(productCategory));
    }

    @PreAuthorize("@ss.hasPermi('pms:productCategory:remove')")
    @Log(title = "商品分类", businessType = BusinessType.DELETE)
	@DeleteMapping("/{id}")
    public ResponseEntity<Integer> remove(@PathVariable Long id) {
        return ResponseEntity.ok(service.deleteById(id));
    }
}
