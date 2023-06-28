package com.cyl.h5.controller;

import com.cyl.h5.pojo.dto.ProductDTO;
import com.cyl.h5.pojo.vo.ProductDetail;
import com.cyl.manager.pms.convert.ProductConvert;
import com.cyl.manager.pms.domain.Product;
import com.cyl.manager.pms.pojo.query.ProductQuery;
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
    public ResponseEntity<Page<ProductDTO>> queryGoodByPage(@RequestBody ProductQuery query, Pageable page) {
        List<Product> pageRes = productService.selectList(query, page);
        return ResponseEntity.ok(new PageImpl<>(productConvert.dos2dtos(pageRes), page, ((com.github.pagehelper.Page) pageRes).getTotal()));
    }
    @GetMapping("/detail/{id}")
    public ResponseEntity<ProductDetail> queryDetail(@PathVariable Long id) {
        ProductDetail detail = productService.queryDetail(id);
        return ResponseEntity.ok(detail);
    }
}
