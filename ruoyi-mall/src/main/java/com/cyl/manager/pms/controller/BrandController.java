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
import com.cyl.manager.pms.convert.BrandConvert;
import com.cyl.manager.pms.domain.entity.Brand;
import com.cyl.manager.pms.domain.query.BrandQuery;
import com.cyl.manager.pms.service.BrandService;
import com.cyl.manager.pms.domain.vo.BrandVO;
import com.ruoyi.common.utils.poi.ExcelUtil;
/**
 * 品牌管理Controller
 * 
 * @author zcc
 * @date 2022-11-28
 */
@Api(description ="品牌管理接口列表")
@RestController
@RequestMapping("/pms/brand")
public class BrandController extends BaseController {
    @Autowired
    private BrandService service;
    @Autowired
    private BrandConvert convert;

    @ApiOperation("查询品牌管理列表")
    @PreAuthorize("@ss.hasPermi('pms:brand:list')")
    @PostMapping("/list")
    public ResponseEntity<Page<Brand>> list(@RequestBody BrandQuery query, Pageable page) {
        List<Brand> list = service.selectList(query, page);
        return ResponseEntity.ok(new PageImpl<>(list, page, ((com.github.pagehelper.Page)list).getTotal()));
    }
    @ApiOperation("所有品牌管理列表")
    @PreAuthorize("@ss.hasPermi('pms:brand:list')")
    @PostMapping("/all")
    public ResponseEntity<List<Brand>> all(@RequestBody BrandQuery query) {
        return ResponseEntity.ok(service.selectList(query, null));
    }

    @ApiOperation("导出品牌管理列表")
    @PreAuthorize("@ss.hasPermi('pms:brand:export')")
    @Log(title = "品牌管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public ResponseEntity<String> export(BrandQuery query) {
        List<Brand> list = service.selectList(query, null);
        ExcelUtil<BrandVO> util = new ExcelUtil<>(BrandVO.class);
        return ResponseEntity.ok(util.writeExcel(convert.dos2vos(list), "品牌管理数据"));
    }

    @ApiOperation("获取品牌管理详细信息")
    @PreAuthorize("@ss.hasPermi('pms:brand:query')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<Brand> getInfo(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.selectById(id));
    }

    @ApiOperation("新增品牌管理")
    @PreAuthorize("@ss.hasPermi('pms:brand:add')")
    @Log(title = "品牌管理", businessType = BusinessType.INSERT)
    @PostMapping
    public ResponseEntity<Integer> add(@RequestBody Brand brand) {
        return ResponseEntity.ok(service.insert(brand));
    }

    @ApiOperation("修改品牌管理")
    @PreAuthorize("@ss.hasPermi('pms:brand:edit')")
    @Log(title = "品牌管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public ResponseEntity<Integer> edit(@RequestBody Brand brand) {
        return ResponseEntity.ok(service.update(brand));
    }

    @ApiOperation("删除品牌管理")
    @PreAuthorize("@ss.hasPermi('pms:brand:remove')")
    @Log(title = "品牌管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{id}")
    public ResponseEntity<Integer> remove(@PathVariable Long id) {
        return ResponseEntity.ok(service.deleteById(id));
    }
}
