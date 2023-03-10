package com.cyl.h5.controller;

import com.cyl.h5.pojo.dto.ProductDTO;
import com.cyl.h5.pojo.vo.ProductDetail;
import com.cyl.pms.convert.ProductConvert;
import com.cyl.pms.domain.Product;
import com.cyl.pms.pojo.query.ProductQuery;
import com.cyl.pms.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/no-auth/good")
public class GoodController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductConvert productConvert;

    @PostMapping("/list")
    public ResponseEntity<Page<ProductDTO>> queryGoodByPage(@RequestBody ProductQuery query, Pageable page) {
        List<Product> pageRes = productService.selectList(query, page);
        return ResponseEntity.ok(new PageImpl<>(productConvert.dos2dtos(pageRes), page, ((com.github.pagehelper.Page) pageRes).getTotal()));
    }
    @GetMapping("/detail")
    public ResponseEntity<ProductDetail> queryDetail(@RequestParam Long id) {
        ProductDetail detail = productService.queryDetail(id);
        return ResponseEntity.ok(detail);
    }
}
