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
import com.cyl.oms.convert.AftersaleConvert;
import com.cyl.oms.domain.Aftersale;
import com.cyl.oms.pojo.query.AftersaleQuery;
import com.cyl.oms.service.AftersaleService;
import com.cyl.oms.pojo.vo.AftersaleVO;
import com.ruoyi.common.utils.poi.ExcelUtil;
/**
 * 订单售后Controller
 * 
 * @author zcc
 * @date 2022-12-29
 */
@Api(description ="订单售后接口列表")
@RestController
@RequestMapping("/oms/aftersale")
public class AftersaleController extends BaseController {
    @Autowired
    private AftersaleService service;
    @Autowired
    private AftersaleConvert convert;

    @ApiOperation("查询订单售后列表")
    @PreAuthorize("@ss.hasPermi('oms:aftersale:list')")
    @PostMapping("/list")
    public ResponseEntity<Page<Aftersale>> list(@RequestBody AftersaleQuery query, Pageable page) {
        List<Aftersale> list = service.selectList(query, page);
        return ResponseEntity.ok(new PageImpl<>(list, page, ((com.github.pagehelper.Page)list).getTotal()));
    }

    @ApiOperation("导出订单售后列表")
    @PreAuthorize("@ss.hasPermi('oms:aftersale:export')")
    @Log(title = "订单售后", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public ResponseEntity<String> export(AftersaleQuery query) {
        List<Aftersale> list = service.selectList(query, null);
        ExcelUtil<AftersaleVO> util = new ExcelUtil<>(AftersaleVO.class);
        return ResponseEntity.ok(util.writeExcel(convert.dos2vos(list), "订单售后数据"));
    }

    @ApiOperation("获取订单售后详细信息")
    @PreAuthorize("@ss.hasPermi('oms:aftersale:query')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<Aftersale> getInfo(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.selectById(id));
    }

    @ApiOperation("新增订单售后")
    @PreAuthorize("@ss.hasPermi('oms:aftersale:add')")
    @Log(title = "订单售后", businessType = BusinessType.INSERT)
    @PostMapping
    public ResponseEntity<Integer> add(@RequestBody Aftersale aftersale) {
        return ResponseEntity.ok(service.insert(aftersale));
    }

    @ApiOperation("修改订单售后")
    @PreAuthorize("@ss.hasPermi('oms:aftersale:edit')")
    @Log(title = "订单售后", businessType = BusinessType.UPDATE)
    @PutMapping
    public ResponseEntity<Integer> edit(@RequestBody Aftersale aftersale) {
        return ResponseEntity.ok(service.update(aftersale));
    }

    @ApiOperation("删除订单售后")
    @PreAuthorize("@ss.hasPermi('oms:aftersale:remove')")
    @Log(title = "订单售后", businessType = BusinessType.DELETE)
	@DeleteMapping("/{id}")
    public ResponseEntity<Integer> remove(@PathVariable Long id) {
        return ResponseEntity.ok(service.deleteById(id));
    }
}
