package com.cyl.manager.act.controller;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.enums.BusinessType;
import com.cyl.manager.act.convert.CouponActivityConvert;
import com.cyl.manager.act.domain.entity.CouponActivity;
import com.cyl.manager.act.domain.query.CouponActivityQuery;
import com.cyl.manager.act.service.CouponActivityService;
import com.cyl.manager.act.domain.vo.CouponActivityVO;
import com.ruoyi.common.utils.poi.ExcelUtil;
/**
 * 优惠券活动表Controller
 * 
 * @author zcc
 * @date 2024-03-22
 */
@Api(description ="优惠券活动表接口列表")
@RestController
@RequestMapping("/act/couponActivity")
public class CouponActivityController extends BaseController {
    @Autowired
    private CouponActivityService service;
    @Autowired
    private CouponActivityConvert convert;

    @ApiOperation("查询优惠券活动表列表")
    @PostMapping("/list")
    public ResponseEntity<Page<CouponActivityVO>> list(@RequestBody CouponActivityQuery query, Pageable page) {
        return ResponseEntity.ok(service.selectList(query, page));
    }

    @ApiOperation("获取优惠券活动表详细信息")
    @GetMapping(value = "/{id}")
    public ResponseEntity<CouponActivity> getInfo(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.selectById(id));
    }

    @ApiOperation("新增优惠券活动表")
    @Log(title = "优惠券活动表", businessType = BusinessType.INSERT)
    @PostMapping
    public ResponseEntity<Integer> add(@RequestBody CouponActivity couponActivity) {
        return ResponseEntity.ok(service.insert(couponActivity));
    }

    @ApiOperation("修改优惠券活动表")
    @Log(title = "优惠券活动表", businessType = BusinessType.UPDATE)
    @PutMapping
    public ResponseEntity<Integer> edit(@RequestBody CouponActivity couponActivity) {
        return ResponseEntity.ok(service.update(couponActivity));
    }

    @ApiOperation("删除优惠券活动表")
    @Log(title = "优惠券活动表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{id}")
    public ResponseEntity<Integer> remove(@PathVariable Long id) {
        return ResponseEntity.ok(service.deleteById(id));
    }
}
