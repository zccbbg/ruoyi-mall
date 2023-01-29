package com.cyl.h5.controller;

import com.cyl.pms.domain.ProductCategory;
import com.cyl.ums.service.MemberCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/h5/cart")
public class CartController {
    @Autowired
    private MemberCartService memberCartService;

    /**
     * 当前用户的购物车商品数量
     *
     * @return
     */
    @GetMapping("goodscount")
    public ResponseEntity<Integer> allCategories() {
        return ResponseEntity.ok(memberCartService.mineCartNum());
    }
}
