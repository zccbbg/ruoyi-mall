package com.cyl.manager.act.controller;

import com.cyl.manager.act.domain.entity.CouponActivity;
import com.cyl.manager.act.domain.entity.IntegralHistory;
import com.cyl.manager.act.domain.query.CouponActivityQuery;
import com.cyl.manager.act.domain.query.IntegralHistoryQuery;
import com.cyl.manager.act.domain.vo.CouponActivityVO;
import com.cyl.manager.act.service.CouponActivityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(description = "优惠券接口列表")
@RestController
@RequestMapping("/h5/coupon")
public class H5CouponController {

    @Autowired
    private CouponActivityService couponActivityService;

    @ApiOperation("优惠券活动列表")
    @PostMapping("/activity/list")
    public ResponseEntity<Page<CouponActivityVO>> list(Pageable page) {
        //获取所有未过期且运行中的活动
        return ResponseEntity.ok(couponActivityService.selectListByH5(page));
    }

    @GetMapping("/activity/get")
    public ResponseEntity<CouponActivity> getDetail(Long id) {
        CouponActivity detail = couponActivityService.getDetail(id);
        return ResponseEntity.ok(detail);
    }
}
