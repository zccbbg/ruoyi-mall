package com.cyl.manager.aws.controller;
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
import com.cyl.manager.aws.convert.SystemStatisticsConvert;
import com.cyl.manager.aws.domain.entity.SystemStatistics;
import com.cyl.manager.aws.domain.query.SystemStatisticsQuery;
import com.cyl.manager.aws.service.SystemStatisticsService;

/**
 * 系统数据统计Controller
 * 
 * @author zcc
 * @date 2023-07-28
 */
@RestController
@RequestMapping("/aws/systemStatistics")
public class SystemStatisticsController extends BaseController {
    @Autowired
    private SystemStatisticsService service;
    @Autowired
    private SystemStatisticsConvert convert;

    @PreAuthorize("@ss.hasPermi('aws:systemStatistics:list')")
    @PostMapping("/list")
    public ResponseEntity<Page<SystemStatistics>> list(@RequestBody SystemStatisticsQuery query, Pageable page) {
        return ResponseEntity.ok(service.selectList(query, page));
    }

    @PreAuthorize("@ss.hasPermi('aws:systemStatistics:export')")
    @Log(title = "系统数据统计", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public ResponseEntity<String> export(SystemStatisticsQuery query) {
//        List<SystemStatistics> list = service.selectList(query, null);
//        ExcelUtil<SystemStatisticsVO> util = new ExcelUtil<>(SystemStatisticsVO.class);
//        return ResponseEntity.ok(util.writeExcel(convert.dos2vos(list), "系统数据统计数据"));
        return null;
    }

    @PreAuthorize("@ss.hasPermi('aws:systemStatistics:query')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<SystemStatistics> getInfo(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.selectById(id));
    }

    @PreAuthorize("@ss.hasPermi('aws:systemStatistics:add')")
    @Log(title = "系统数据统计", businessType = BusinessType.INSERT)
    @PostMapping
    public ResponseEntity<Integer> add(@RequestBody SystemStatistics systemStatistics) {
        return ResponseEntity.ok(service.insert(systemStatistics));
    }

    @PreAuthorize("@ss.hasPermi('aws:systemStatistics:edit')")
    @Log(title = "系统数据统计", businessType = BusinessType.UPDATE)
    @PutMapping
    public ResponseEntity<Integer> edit(@RequestBody SystemStatistics systemStatistics) {
        return ResponseEntity.ok(service.update(systemStatistics));
    }

    @PreAuthorize("@ss.hasPermi('aws:systemStatistics:remove')")
    @Log(title = "系统数据统计", businessType = BusinessType.DELETE)
	@DeleteMapping("/{id}")
    public ResponseEntity<Integer> remove(@PathVariable Long id) {
        return ResponseEntity.ok(service.deleteById(id));
    }
}
