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
import com.cyl.manager.act.convert.MemberCouponConvert;
import com.cyl.manager.act.domain.entity.MemberCoupon;
import com.cyl.manager.act.domain.query.MemberCouponQuery;
import com.cyl.manager.act.service.MemberCouponService;
import com.cyl.manager.act.domain.vo.MemberCouponVO;
import com.ruoyi.common.utils.poi.ExcelUtil;
/**
 * 用户领券记录Controller
 * 
 * @author zcc
 * @date 2024-03-22
 */
@Api(description ="用户领券记录接口列表")
@RestController
@RequestMapping("/act/memberCoupon")
public class MemberCouponController extends BaseController {
    @Autowired
    private MemberCouponService service;
    @Autowired
    private MemberCouponConvert convert;

    @ApiOperation("查询用户领券记录列表")
    @PostMapping("/list")
    public ResponseEntity<Page<MemberCouponVO>> list(@RequestBody MemberCouponQuery query, Pageable page) {
        return ResponseEntity.ok(service.selectList(query, page));
    }

    @ApiOperation("获取用户领券记录详细信息")
    @GetMapping(value = "/{id}")
    public ResponseEntity<MemberCoupon> getInfo(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.selectById(id));
    }

    @ApiOperation("新增用户领券记录")
    @Log(title = "用户领券记录", businessType = BusinessType.INSERT)
    @PostMapping
    public ResponseEntity<Integer> add(@RequestBody MemberCoupon memberCoupon) {
        return ResponseEntity.ok(service.insert(memberCoupon));
    }

    @ApiOperation("修改用户领券记录")
    @Log(title = "用户领券记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public ResponseEntity<Integer> edit(@RequestBody MemberCoupon memberCoupon) {
        return ResponseEntity.ok(service.update(memberCoupon));
    }

    @ApiOperation("删除用户领券记录")
    @Log(title = "用户领券记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{id}")
    public ResponseEntity<Integer> remove(@PathVariable Long id) {
        return ResponseEntity.ok(service.deleteById(id));
    }
}
