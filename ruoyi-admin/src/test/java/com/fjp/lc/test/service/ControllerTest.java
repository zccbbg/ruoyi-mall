package com.fjp.lc.test.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cyl.h5.controller.H5OrderController;
import com.cyl.h5.domain.form.ApplyRefundForm;
import com.cyl.manager.oms.domain.entity.Order;
import com.cyl.manager.oms.domain.entity.OrderItem;
import com.cyl.manager.oms.mapper.OrderMapper;
import com.cyl.manager.oms.service.OrderService;
import com.ruoyi.RuoYiApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RuoYiApplication.class)
@ActiveProfiles("dev")
@Slf4j
public class ControllerTest {
    @Autowired
    private H5OrderController h5OrderController;

    @Autowired
    private OrderMapper orderMapper;

    @Test
    public void test() {
        ApplyRefundForm applyRefundForm = new ApplyRefundForm();
        QueryWrapper<Order> queryWrapper = new QueryWrapper();
        queryWrapper.eq("pay_id", 6226229322123265l);
        Order order = orderMapper.selectOne(queryWrapper);
        applyRefundForm.setOrderId(order.getId());
        applyRefundForm.setApplyRefundType(1);
        applyRefundForm.setReason("不要了");
        applyRefundForm.setQuantity(1);
        applyRefundForm.setRefundAmount(new BigDecimal(0.01));
        h5OrderController.applyRefund(applyRefundForm);
    }

}
