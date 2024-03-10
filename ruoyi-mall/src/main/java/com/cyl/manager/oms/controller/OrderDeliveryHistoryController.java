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
import com.cyl.manager.oms.convert.OrderDeliveryHistoryConvert;
import com.cyl.manager.oms.domain.entity.OrderDeliveryHistory;
import com.cyl.manager.oms.domain.query.OrderDeliveryHistoryQuery;
import com.cyl.manager.oms.service.OrderDeliveryHistoryService;
import com.cyl.manager.oms.domain.vo.OrderDeliveryHistoryVO;
import com.ruoyi.common.utils.poi.ExcelUtil;
/**
 * 订单发货记录Controller
 * 
 * @author zcc
 * @date 2022-12-01
 */
@Api(description ="订单发货记录接口列表")
@RestController
@RequestMapping("/oms/orderDeliveryHistory")
public class OrderDeliveryHistoryController extends BaseController {
    @Autowired
    private OrderDeliveryHistoryService service;
    @Autowired
    private OrderDeliveryHistoryConvert convert;

    @ApiOperation("查询订单发货记录列表")
    @PreAuthorize("@ss.hasPermi('oms:orderDeliveryHistory:list')")
    @PostMapping("/list")
    public ResponseEntity<Page<OrderDeliveryHistory>> list(@RequestBody OrderDeliveryHistoryQuery query, Pageable page) {
        List<OrderDeliveryHistory> list = service.selectList(query, page);
        return ResponseEntity.ok(new PageImpl<>(list, page, ((com.github.pagehelper.Page)list).getTotal()));
    }

    @ApiOperation("导出订单发货记录列表")
    @PreAuthorize("@ss.hasPermi('oms:orderDeliveryHistory:export')")
    @Log(title = "订单发货记录", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public ResponseEntity<String> export(OrderDeliveryHistoryQuery query) {
        List<OrderDeliveryHistory> list = service.selectList(query, null);
        ExcelUtil<OrderDeliveryHistoryVO> util = new ExcelUtil<>(OrderDeliveryHistoryVO.class);
        return ResponseEntity.ok(util.writeExcel(convert.dos2vos(list), "订单发货记录数据"));
    }

    @ApiOperation("获取订单发货记录详细信息")
    @PreAuthorize("@ss.hasPermi('oms:orderDeliveryHistory:query')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<OrderDeliveryHistory> getInfo(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.selectById(id));
    }

    @ApiOperation("新增订单发货记录")
    @PreAuthorize("@ss.hasPermi('oms:orderDeliveryHistory:add')")
    @Log(title = "订单发货记录", businessType = BusinessType.INSERT)
    @PostMapping
    public ResponseEntity<Integer> add(@RequestBody OrderDeliveryHistory orderDeliveryHistory) {
        return ResponseEntity.ok(service.insert(orderDeliveryHistory));
    }

    @ApiOperation("修改订单发货记录")
    @PreAuthorize("@ss.hasPermi('oms:orderDeliveryHistory:edit')")
    @Log(title = "订单发货记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public ResponseEntity<Integer> edit(@RequestBody OrderDeliveryHistory orderDeliveryHistory) {
        return ResponseEntity.ok(service.update(orderDeliveryHistory));
    }

    @ApiOperation("删除订单发货记录")
    @PreAuthorize("@ss.hasPermi('oms:orderDeliveryHistory:remove')")
    @Log(title = "订单发货记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{id}")
    public ResponseEntity<Integer> remove(@PathVariable Long id) {
        return ResponseEntity.ok(service.deleteById(id));
    }
}
