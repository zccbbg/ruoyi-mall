package com.cyl.manager.act.controller;

import com.cyl.manager.act.convert.IntegralHistoryConvert;
import com.cyl.manager.act.domain.entity.IntegralHistory;
import com.cyl.manager.act.domain.query.IntegralHistoryQuery;
import com.cyl.manager.act.domain.vo.IntegralHistoryVO;
import com.cyl.manager.act.service.IntegralHistoryService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * 积分流水表Controller
 * 
 * @author zcc
 * @date 2024-03-01
 */
@RestController
@RequestMapping("/act/integralHistory")
public class IntegralHistoryController extends BaseController {
    @Autowired
    private IntegralHistoryService service;
    @Autowired
    private IntegralHistoryConvert convert;

    @PreAuthorize("@ss.hasPermi('act:integralHistory:list')")
    @PostMapping("/list")
    public ResponseEntity<Page<IntegralHistory>> list(@RequestBody IntegralHistoryQuery query, Pageable page) {
        List<IntegralHistory> list = service.selectList(query, page);
        return ResponseEntity.ok(new PageImpl<>(list, page, ((com.github.pagehelper.Page)list).getTotal()));
    }

    @PreAuthorize("@ss.hasPermi('act:integralHistory:export')")
    @Log(title = "积分流水表", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public ResponseEntity<String> export(IntegralHistoryQuery query) {
        List<IntegralHistory> list = service.selectList(query, null);
        ExcelUtil<IntegralHistoryVO> util = new ExcelUtil<>(IntegralHistoryVO.class);
        return ResponseEntity.ok(util.writeExcel(convert.dos2vos(list), "积分流水表数据"));
    }

    @PreAuthorize("@ss.hasPermi('act:integralHistory:query')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<IntegralHistory> getInfo(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.selectById(id));
    }

    @PreAuthorize("@ss.hasPermi('act:integralHistory:add')")
    @Log(title = "积分流水表", businessType = BusinessType.INSERT)
    @PostMapping
    public ResponseEntity<Integer> add(@RequestBody IntegralHistory integralHistory) {
        return ResponseEntity.ok(service.insert(integralHistory));
    }

    @PreAuthorize("@ss.hasPermi('act:integralHistory:edit')")
    @Log(title = "积分流水表", businessType = BusinessType.UPDATE)
    @PutMapping
    public ResponseEntity<Integer> edit(@RequestBody IntegralHistory integralHistory) {
        return ResponseEntity.ok(service.update(integralHistory));
    }

    @PreAuthorize("@ss.hasPermi('act:integralHistory:remove')")
    @Log(title = "积分流水表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{id}")
    public ResponseEntity<Integer> remove(@PathVariable Long id) {
        return ResponseEntity.ok(service.deleteById(id));
    }
}
