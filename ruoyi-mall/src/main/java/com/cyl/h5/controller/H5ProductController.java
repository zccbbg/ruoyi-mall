package com.cyl.h5.controller;

import com.cyl.h5.domain.vo.H5ProductVO;
import com.cyl.h5.domain.vo.ProductDetailVO;
import com.cyl.manager.pms.convert.ProductConvert;
import com.cyl.manager.pms.domain.entity.Product;
import com.cyl.manager.pms.domain.query.ProductQuery;
import com.cyl.manager.pms.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/no-auth/product")
public class H5ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductConvert productConvert;

    @PostMapping("/list")
    public ResponseEntity<Page<H5ProductVO>> queryGoodByPage(@RequestBody ProductQuery query, Pageable page) {
        List<Product> pageRes = productService.selectList(query, page);
        return ResponseEntity.ok(new PageImpl<>(productConvert.dos2dtos(pageRes), page, ((com.github.pagehelper.Page) pageRes).getTotal()));
    }
    @GetMapping("/detail/{id}")
    public ResponseEntity<ProductDetailVO> queryDetail(@PathVariable Long id) {
        ProductDetailVO detail = productService.queryDetail(id);
        return ResponseEntity.ok(detail);
    }
}
