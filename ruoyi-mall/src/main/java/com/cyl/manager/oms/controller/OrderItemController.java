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
import com.cyl.manager.oms.convert.OrderItemConvert;
import com.cyl.manager.oms.domain.entity.OrderItem;
import com.cyl.manager.oms.domain.query.OrderItemQuery;
import com.cyl.manager.oms.service.OrderItemService;
import com.cyl.manager.oms.domain.vo.OrderItemVO;
import com.ruoyi.common.utils.poi.ExcelUtil;
/**
 * 订单中所包含的商品Controller
 * 
 * @author zcc
 * @date 2022-12-01
 */
@Api(description ="订单中所包含的商品接口列表")
@RestController
@RequestMapping("/oms/orderItem")
public class OrderItemController extends BaseController {
    @Autowired
    private OrderItemService service;
    @Autowired
    private OrderItemConvert convert;

    @ApiOperation("查询订单中所包含的商品列表")
    @PreAuthorize("@ss.hasPermi('oms:orderItem:list')")
    @PostMapping("/list")
    public ResponseEntity<Page<OrderItem>> list(@RequestBody OrderItemQuery query, Pageable page) {
        List<OrderItem> list = service.selectList(query, page);
        return ResponseEntity.ok(new PageImpl<>(list, page, ((com.github.pagehelper.Page)list).getTotal()));
    }

    @ApiOperation("导出订单中所包含的商品列表")
    @PreAuthorize("@ss.hasPermi('oms:orderItem:export')")
    @Log(title = "订单中所包含的商品", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public ResponseEntity<String> export(OrderItemQuery query) {
        List<OrderItem> list = service.selectList(query, null);
        ExcelUtil<OrderItemVO> util = new ExcelUtil<>(OrderItemVO.class);
        return ResponseEntity.ok(util.writeExcel(convert.dos2vos(list), "订单中所包含的商品数据"));
    }

    @ApiOperation("获取订单中所包含的商品详细信息")
    @PreAuthorize("@ss.hasPermi('oms:orderItem:query')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<OrderItem> getInfo(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.selectById(id));
    }

    @ApiOperation("新增订单中所包含的商品")
    @PreAuthorize("@ss.hasPermi('oms:orderItem:add')")
    @Log(title = "订单中所包含的商品", businessType = BusinessType.INSERT)
    @PostMapping
    public ResponseEntity<Integer> add(@RequestBody OrderItem orderItem) {
        return ResponseEntity.ok(service.insert(orderItem));
    }

    @ApiOperation("修改订单中所包含的商品")
    @PreAuthorize("@ss.hasPermi('oms:orderItem:edit')")
    @Log(title = "订单中所包含的商品", businessType = BusinessType.UPDATE)
    @PutMapping
    public ResponseEntity<Integer> edit(@RequestBody OrderItem orderItem) {
        return ResponseEntity.ok(service.update(orderItem));
    }

    @ApiOperation("删除订单中所包含的商品")
    @PreAuthorize("@ss.hasPermi('oms:orderItem:remove')")
    @Log(title = "订单中所包含的商品", businessType = BusinessType.DELETE)
	@DeleteMapping("/{id}")
    public ResponseEntity<Integer> remove(@PathVariable Long id) {
        return ResponseEntity.ok(service.deleteById(id));
    }
}
