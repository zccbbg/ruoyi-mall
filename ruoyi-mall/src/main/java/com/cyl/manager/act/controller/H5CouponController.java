package com.cyl.manager.act.controller;

import com.cyl.manager.act.domain.entity.CouponActivity;
import com.cyl.manager.act.domain.entity.MemberCoupon;
import com.cyl.manager.act.domain.query.MemberCouponQuery;
import com.cyl.manager.act.domain.vo.CouponActivityVO;
import com.cyl.manager.act.domain.vo.MemberCouponVO;
import com.cyl.manager.act.service.CouponActivityService;
import com.cyl.manager.act.service.MemberCouponService;
import com.ruoyi.common.core.redis.RedisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    @Autowired
    private RedisService redisService;
    @Autowired
    private MemberCouponService memberCouponService;

    @ApiOperation("优惠券活动列表")
    @PostMapping("/activity/list")
    public ResponseEntity<Page<CouponActivityVO>> list(Pageable page) {
        //获取所有未过期且运行中的活动
        return ResponseEntity.ok(couponActivityService.selectListByH5(page));
    }

    @GetMapping("/activity/get")
    public ResponseEntity<CouponActivityVO> getDetail(Long id) {
        CouponActivityVO detail = couponActivityService.getDetail(id);
        return ResponseEntity.ok(detail);
    }

    @PostMapping("/receive")
    public ResponseEntity<Boolean> receiveCoupon(Long id) {
        String redisKey = "h5_receive_coupon" + id;
        String redisValue = id + "_" + System.currentTimeMillis();
        try {
            redisService.lock(redisKey, redisValue, 60);
            return ResponseEntity.ok(couponActivityService.receiveCoupon(id));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            try {
                redisService.unLock(redisKey, redisValue);
            } catch (Exception e) {

            }
        }
    }


    @ApiOperation("优惠券活动列表")
    @PostMapping("/list")
    public ResponseEntity<Page<MemberCoupon>> list(@RequestBody MemberCouponQuery query, Pageable page) {
        //获取所有未过期且运行中的活动
        return ResponseEntity.ok(memberCouponService.selectListByH5(query, page));
    }


    /**
     * 根据购买的商品筛选可用优惠券
     *
     * @param query
     * @return
     */
    @PostMapping("/can/use/list")
    public ResponseEntity<List<MemberCoupon>> getCanUseList(@RequestBody MemberCouponQuery query) {
        return ResponseEntity.ok(memberCouponService.getCanUseList(query.getProducts()));
    }

}
