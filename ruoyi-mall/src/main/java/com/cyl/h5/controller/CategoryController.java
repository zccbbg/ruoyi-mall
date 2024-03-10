package com.cyl.h5.controller;

import com.cyl.manager.pms.domain.entity.ProductCategory;
import com.cyl.manager.pms.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/no-auth/category")
public class CategoryController {
    @Autowired
    private ProductCategoryService categoryService;

    @GetMapping("/all-categories")
    public ResponseEntity<List<ProductCategory>> allCategories() {
        return ResponseEntity.ok(categoryService.h5Categories());
    }

    @GetMapping("/category-by-id")
    public ResponseEntity<List<ProductCategory>> getBrotherAndChild(@RequestParam Long id, @RequestParam(name = "withChild", defaultValue = "false") boolean withChild) {
        return ResponseEntity.ok(categoryService.getBrotherAndChild(id, withChild));
    }
}
