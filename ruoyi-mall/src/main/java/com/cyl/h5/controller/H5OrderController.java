package com.cyl.h5.controller;

import com.cyl.h5.pojo.dto.OrderCreateDTO;
import com.cyl.h5.pojo.vo.OrderCalcVO;
import com.cyl.h5.pojo.vo.form.OrderSubmitForm;
import com.cyl.h5.pojo.vo.query.OrderH5Query;
import com.cyl.manager.oms.pojo.vo.OrderVO;
import com.cyl.manager.oms.service.OrderService;
import com.cyl.manager.ums.domain.Member;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.redis.RedisService;
import com.ruoyi.framework.config.LocalDataUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/h5/order")
@Slf4j
public class H5OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private RedisService redisService;

    @PostMapping("/add")
    public ResponseEntity<Long> submit(@RequestBody OrderSubmitForm form) {
        Member member = (Member) LocalDataUtil.getVar(Constants.MEMBER_INFO);
        Long memberId = member.getId();
        String redisKey = "h5_order_add" + memberId;
        String redisValue = memberId + "_" + System.currentTimeMillis();
        try{
            redisService.lock(redisKey, redisValue, 60);
            return ResponseEntity.ok(orderService.submit(form));
        }catch (Exception e){
            log.info("创建订单方法异常", e);
            return null;
        }finally {
            try {
                redisService.unLock(redisKey, redisValue);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    @PostMapping("orders")
    public ResponseEntity<Page<OrderVO>> queryOrderPage(@RequestBody OrderH5Query query, Pageable pageReq) {
        return ResponseEntity.ok(orderService.queryOrderPage(query, pageReq));
    }

    @ApiOperation("下单前校验")
    @PostMapping("/addOrderCheck")
    public ResponseEntity<OrderCalcVO> addOrderCheck(@RequestBody OrderCreateDTO orderCreateDTO){
        return ResponseEntity.ok(orderService.addOrderCheck(orderCreateDTO));
    }
}
