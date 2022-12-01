package com.cyl.oms.controller;

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
import com.cyl.oms.convert.RefundConvert;
import com.cyl.oms.domain.Refund;
import com.cyl.oms.pojo.query.RefundQuery;
import com.cyl.oms.service.RefundService;
import com.cyl.oms.pojo.vo.RefundVO;
import com.ruoyi.common.utils.poi.ExcelUtil;
/**
 * 订单售后Controller
 * 
 * @author zcc
 * @date 2022-12-01
 */
@Api(description ="订单售后接口列表")
@RestController
@RequestMapping("/oms/refund")
public class RefundController extends BaseController {
    @Autowired
    private RefundService service;
    @Autowired
    private RefundConvert convert;

    @ApiOperation("查询订单售后列表")
    @PreAuthorize("@ss.hasPermi('oms:refund:list')")
    @PostMapping("/list")
    public ResponseEntity<Page<Refund>> list(@RequestBody RefundQuery query, Pageable page) {
        List<Refund> list = service.selectList(query, page);
        return ResponseEntity.ok(new PageImpl<>(list, page, ((com.github.pagehelper.Page)list).getTotal()));
    }

    @ApiOperation("导出订单售后列表")
    @PreAuthorize("@ss.hasPermi('oms:refund:export')")
    @Log(title = "订单售后", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public ResponseEntity<String> export(RefundQuery query) {
        List<Refund> list = service.selectList(query, null);
        ExcelUtil<RefundVO> util = new ExcelUtil<>(RefundVO.class);
        return ResponseEntity.ok(util.writeExcel(convert.dos2vos(list), "订单售后数据"));
    }

    @ApiOperation("获取订单售后详细信息")
    @PreAuthorize("@ss.hasPermi('oms:refund:query')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<Refund> getInfo(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.selectById(id));
    }

    @ApiOperation("新增订单售后")
    @PreAuthorize("@ss.hasPermi('oms:refund:add')")
    @Log(title = "订单售后", businessType = BusinessType.INSERT)
    @PostMapping
    public ResponseEntity<Integer> add(@RequestBody Refund refund) {
        return ResponseEntity.ok(service.insert(refund));
    }

    @ApiOperation("修改订单售后")
    @PreAuthorize("@ss.hasPermi('oms:refund:edit')")
    @Log(title = "订单售后", businessType = BusinessType.UPDATE)
    @PutMapping
    public ResponseEntity<Integer> edit(@RequestBody Refund refund) {
        return ResponseEntity.ok(service.update(refund));
    }

    @ApiOperation("删除订单售后")
    @PreAuthorize("@ss.hasPermi('oms:refund:remove')")
    @Log(title = "订单售后", businessType = BusinessType.DELETE)
	@DeleteMapping("/{id}")
    public ResponseEntity<Integer> remove(@PathVariable Long id) {
        return ResponseEntity.ok(service.deleteById(id));
    }
}
