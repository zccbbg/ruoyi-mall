package com.cyl.manager.pms.controller;

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
import com.cyl.manager.pms.convert.SkuConvert;
import com.cyl.manager.pms.domain.entity.Sku;
import com.cyl.manager.pms.domain.query.SkuQuery;
import com.cyl.manager.pms.service.SkuService;
import com.cyl.manager.pms.domain.vo.SkuVO;
import com.ruoyi.common.utils.poi.ExcelUtil;
/**
 * sku信息Controller
 * 
 * @author zcc
 * @date 2022-11-28
 */
@Api(description ="sku信息接口列表")
@RestController
@RequestMapping("/pms/sku")
public class SkuController extends BaseController {
    @Autowired
    private SkuService service;
    @Autowired
    private SkuConvert convert;

    @ApiOperation("查询sku信息列表")
    @PreAuthorize("@ss.hasPermi('pms:sku:list')")
    @PostMapping("/list")
    public ResponseEntity<Page<Sku>> list(@RequestBody SkuQuery query, Pageable page) {
        List<Sku> list = service.selectList(query, page);
        return ResponseEntity.ok(new PageImpl<>(list, page, ((com.github.pagehelper.Page)list).getTotal()));
    }

    @ApiOperation("导出sku信息列表")
    @PreAuthorize("@ss.hasPermi('pms:sku:export')")
    @Log(title = "sku信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public ResponseEntity<String> export(SkuQuery query) {
        List<Sku> list = service.selectList(query, null);
        ExcelUtil<SkuVO> util = new ExcelUtil<>(SkuVO.class);
        return ResponseEntity.ok(util.writeExcel(convert.dos2vos(list), "sku信息数据"));
    }

    @ApiOperation("获取sku信息详细信息")
    @PreAuthorize("@ss.hasPermi('pms:sku:query')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<Sku> getInfo(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.selectById(id));
    }

    @ApiOperation("新增sku信息")
    @PreAuthorize("@ss.hasPermi('pms:sku:add')")
    @Log(title = "sku信息", businessType = BusinessType.INSERT)
    @PostMapping
    public ResponseEntity<Integer> add(@RequestBody Sku sku) {
        return ResponseEntity.ok(service.insert(sku));
    }

    @ApiOperation("修改sku信息")
    @PreAuthorize("@ss.hasPermi('pms:sku:edit')")
    @Log(title = "sku信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public ResponseEntity<Integer> edit(@RequestBody Sku sku) {
        return ResponseEntity.ok(service.update(sku));
    }

    @ApiOperation("删除sku信息")
    @PreAuthorize("@ss.hasPermi('pms:sku:remove')")
    @Log(title = "sku信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{id}")
    public ResponseEntity<Integer> remove(@PathVariable Long id) {
        return ResponseEntity.ok(service.deleteById(id));
    }
}
