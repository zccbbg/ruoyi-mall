package com.cyl.manager.oms.controller;

import java.util.List;

import com.cyl.h5.config.SecurityUtil;
import com.cyl.manager.oms.pojo.request.DeliverProductRequest;
import com.cyl.manager.oms.pojo.request.ManagerOrderQueryRequest;
import com.cyl.manager.oms.pojo.vo.ManagerOrderDetailVO;
import com.cyl.manager.oms.pojo.vo.ManagerOrderVO;
import com.cyl.manager.oms.pojo.vo.OrderOperateHistoryVO;
import com.ruoyi.common.core.redis.RedisService;
import com.ruoyi.common.utils.SecurityUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class OrderController extends BaseController {
    @Autowired
    private OrderService service;
    @Autowired
    private OrderConvert convert;
    @Autowired
    private RedisService redisService;

    @ApiOperation("查询订单表列表")
    @PreAuthorize("@ss.hasPermi('oms:order:list')")
    @PostMapping("/list")
    public ResponseEntity<Page<ManagerOrderVO>> list(@RequestBody ManagerOrderQueryRequest query, Pageable page) {
        return ResponseEntity.ok(service.selectList(query, page));
    }

    @ApiOperation("导出订单表列表")
    @PreAuthorize("@ss.hasPermi('oms:order:export')")
    @Log(title = "订单表", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public ResponseEntity<String> export(OrderQuery query) {
//        List<Order> list = service.selectList(query, null);
//        ExcelUtil<OrderVO> util = new ExcelUtil<>(OrderVO.class);
//        return ResponseEntity.ok(util.writeExcel(convert.dos2vos(list), "订单表数据"));
        return null;
    }

    @ApiOperation("获取订单表详细信息")
    @PreAuthorize("@ss.hasPermi('oms:order:query')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<ManagerOrderDetailVO> getInfo(@PathVariable("id") Long id) {
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

    @ApiOperation("添加备注")
    @PreAuthorize("@ss.hasPermi('oms:order:note:add')")
    @Log(title = "订单表", businessType = BusinessType.UPDATE)
    @PostMapping("/merchantNote/add")
    public ResponseEntity<Integer> saveMerchantNote(@RequestBody Order order){
        return ResponseEntity.ok(service.saveMerchantNote(order));
    }

    @ApiOperation("管理后台订单发货")
    @PreAuthorize("@ss.hasPermi('oms:order:delivery')")
    @PostMapping("/deliverProduct")
    public ResponseEntity<String> delivery(@RequestBody DeliverProductRequest request){
        Long userId = SecurityUtils.getUserId();
        String redisKey = "oms_order_deliverProduct" + request.getOrderId();
        String redisValue = request.getOrderId() + "_" + System.currentTimeMillis();
        try{
            redisService.lock(redisKey, redisValue, 60);
            return ResponseEntity.ok(service.deliverProduct(request, userId));
        }catch (Exception e){
            log.error("订单发货接口异常");
            throw new RuntimeException("发货失败");
        }finally {
            try{
                redisService.unLock(redisKey, redisValue);;
            }catch (Exception e){
                log.error("", e);
            }
        }
    }

    @ApiOperation("订单日志")
    @GetMapping("/log/{orderId}")
    public ResponseEntity<List<OrderOperateHistoryVO>> log(@PathVariable Long orderId){
        return ResponseEntity.ok(service.log(orderId));
    }

}
