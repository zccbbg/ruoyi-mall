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
import com.cyl.manager.oms.convert.OrderConvert;
import com.cyl.manager.oms.domain.Order;
import com.cyl.manager.oms.pojo.query.OrderQuery;
import com.cyl.manager.oms.service.OrderService;
import com.cyl.manager.oms.pojo.vo.OrderVO;
import com.ruoyi.common.utils.poi.ExcelUtil;
/**
 * 订单表Controller
 * 
 * @author zcc
 * @date 2022-12-01
 */
@Api(description ="订单表接口列表")
@RestController
@RequestMapping("/oms/order")
public class OrderController extends BaseController {
    @Autowired
    private OrderService service;
    @Autowired
    private OrderConvert convert;

    @ApiOperation("查询订单表列表")
    @PreAuthorize("@ss.hasPermi('oms:order:list')")
    @PostMapping("/list")
    public ResponseEntity<Page<Order>> list(@RequestBody OrderQuery query, Pageable page) {
        List<Order> list = service.selectList(query, page);
        return ResponseEntity.ok(new PageImpl<>(list, page, ((com.github.pagehelper.Page)list).getTotal()));
    }

    @ApiOperation("导出订单表列表")
    @PreAuthorize("@ss.hasPermi('oms:order:export')")
    @Log(title = "订单表", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public ResponseEntity<String> export(OrderQuery query) {
        List<Order> list = service.selectList(query, null);
        ExcelUtil<OrderVO> util = new ExcelUtil<>(OrderVO.class);
        return ResponseEntity.ok(util.writeExcel(convert.dos2vos(list), "订单表数据"));
    }

    @ApiOperation("获取订单表详细信息")
    @PreAuthorize("@ss.hasPermi('oms:order:query')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<Order> getInfo(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.selectById(id));
    }

    @ApiOperation("新增订单表")
    @PreAuthorize("@ss.hasPermi('oms:order:add')")
    @Log(title = "订单表", businessType = BusinessType.INSERT)
    @PostMapping
    public ResponseEntity<Integer> add(@RequestBody Order order) {
        return ResponseEntity.ok(service.insert(order));
    }

    @ApiOperation("修改订单表")
    @PreAuthorize("@ss.hasPermi('oms:order:edit')")
    @Log(title = "订单表", businessType = BusinessType.UPDATE)
    @PutMapping
    public ResponseEntity<Integer> edit(@RequestBody Order order) {
        return ResponseEntity.ok(service.update(order));
    }

    @ApiOperation("删除订单表")
    @PreAuthorize("@ss.hasPermi('oms:order:remove')")
    @Log(title = "订单表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{id}")
    public ResponseEntity<Integer> remove(@PathVariable Long id) {
        return ResponseEntity.ok(service.deleteById(id));
    }
}
