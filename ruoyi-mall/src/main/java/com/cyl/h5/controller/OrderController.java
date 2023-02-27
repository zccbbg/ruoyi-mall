package com.cyl.h5.controller;

import com.cyl.h5.pojo.vo.form.OrderSubmitForm;
import com.cyl.oms.pojo.vo.OrderVO;
import com.cyl.oms.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @PostMapping("submit")
    public ResponseEntity<OrderVO> submit(@RequestBody OrderSubmitForm form) {
        return ResponseEntity.ok(orderService.submit(form));
    }
}
