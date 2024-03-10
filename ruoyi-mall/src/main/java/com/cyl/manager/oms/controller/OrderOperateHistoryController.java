package com.cyl.manager.oms.controller;

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
import com.cyl.manager.oms.convert.OrderOperateHistoryConvert;
import com.cyl.manager.oms.domain.entity.OrderOperateHistory;
import com.cyl.manager.oms.domain.query.OrderOperateHistoryQuery;
import com.cyl.manager.oms.service.OrderOperateHistoryService;
import com.cyl.manager.oms.domain.vo.OrderOperateHistoryVO;
import com.ruoyi.common.utils.poi.ExcelUtil;
/**
 * 订单操作历史记录Controller
 * 
 * @author zcc
 * @date 2022-12-01
 */
@Api(description ="订单操作历史记录接口列表")
@RestController
@RequestMapping("/oms/orderOperateHistory")
public class OrderOperateHistoryController extends BaseController {
    @Autowired
    private OrderOperateHistoryService service;
    @Autowired
    private OrderOperateHistoryConvert convert;

    @ApiOperation("查询订单操作历史记录列表")
    @PreAuthorize("@ss.hasPermi('oms:orderOperateHistory:list')")
    @PostMapping("/list")
    public ResponseEntity<Page<OrderOperateHistory>> list(@RequestBody OrderOperateHistoryQuery query, Pageable page) {
        List<OrderOperateHistory> list = service.selectList(query, page);
        return ResponseEntity.ok(new PageImpl<>(list, page, ((com.github.pagehelper.Page)list).getTotal()));
    }

    @ApiOperation("导出订单操作历史记录列表")
    @PreAuthorize("@ss.hasPermi('oms:orderOperateHistory:export')")
    @Log(title = "订单操作历史记录", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public ResponseEntity<String> export(OrderOperateHistoryQuery query) {
        List<OrderOperateHistory> list = service.selectList(query, null);
        ExcelUtil<OrderOperateHistoryVO> util = new ExcelUtil<>(OrderOperateHistoryVO.class);
        return ResponseEntity.ok(util.writeExcel(convert.dos2vos(list), "订单操作历史记录数据"));
    }

    @ApiOperation("获取订单操作历史记录详细信息")
    @PreAuthorize("@ss.hasPermi('oms:orderOperateHistory:query')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<OrderOperateHistory> getInfo(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.selectById(id));
    }

    @ApiOperation("新增订单操作历史记录")
    @PreAuthorize("@ss.hasPermi('oms:orderOperateHistory:add')")
    @Log(title = "订单操作历史记录", businessType = BusinessType.INSERT)
    @PostMapping
    public ResponseEntity<Integer> add(@RequestBody OrderOperateHistory orderOperateHistory) {
        return ResponseEntity.ok(service.insert(orderOperateHistory));
    }

    @ApiOperation("修改订单操作历史记录")
    @PreAuthorize("@ss.hasPermi('oms:orderOperateHistory:edit')")
    @Log(title = "订单操作历史记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public ResponseEntity<Integer> edit(@RequestBody OrderOperateHistory orderOperateHistory) {
        return ResponseEntity.ok(service.update(orderOperateHistory));
    }

    @ApiOperation("删除订单操作历史记录")
    @PreAuthorize("@ss.hasPermi('oms:orderOperateHistory:remove')")
    @Log(title = "订单操作历史记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{id}")
    public ResponseEntity<Integer> remove(@PathVariable Long id) {
        return ResponseEntity.ok(service.deleteById(id));
    }
}
