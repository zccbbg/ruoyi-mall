package com.cyl.h5.controller;

import com.cyl.h5.pojo.vo.form.OrderSubmitForm;
import com.cyl.h5.pojo.vo.query.OrderH5Query;
import com.cyl.oms.pojo.vo.OrderVO;
import com.cyl.oms.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/h5-order")
public class Order1Controller {
    @Autowired
    private OrderService orderService;
    @PostMapping("submit")
    public ResponseEntity<OrderVO> submit(@RequestBody OrderSubmitForm form) {
        return ResponseEntity.ok(orderService.submit(form));
    }
    @PostMapping("orders")
    public ResponseEntity<Page<OrderVO>> queryOrderPage(@RequestBody OrderH5Query query, Pageable pageReq) {
        return ResponseEntity.ok(orderService.queryOrderPage(query, pageReq));
    }
}
