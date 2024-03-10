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
import com.cyl.manager.pms.convert.ProductConvert;
import com.cyl.manager.pms.domain.entity.Product;
import com.cyl.manager.pms.domain.query.ProductQuery;
import com.cyl.manager.pms.service.ProductService;
import com.cyl.manager.pms.domain.vo.ProductVO;
import com.ruoyi.common.utils.poi.ExcelUtil;
/**
 * 商品信息Controller
 * 
 * @author zcc
 * @date 2022-11-28
 */
@Api(description ="商品信息接口列表")
@RestController
@RequestMapping("/pms/product")
public class ProductController extends BaseController {
    @Autowired
    private ProductService service;
    @Autowired
    private ProductConvert convert;

    @ApiOperation("查询商品信息列表")
    @PreAuthorize("@ss.hasPermi('pms:product:list')")
    @PostMapping("/list")
    public ResponseEntity<Page<Product>> list(@RequestBody ProductQuery query, Pageable page) {
        List<Product> list = service.selectList(query, page);
        return ResponseEntity.ok(new PageImpl<>(list, page, ((com.github.pagehelper.Page)list).getTotal()));
    }

    @ApiOperation("导出商品信息列表")
    @PreAuthorize("@ss.hasPermi('pms:product:export')")
    @Log(title = "商品信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public ResponseEntity<String> export(ProductQuery query) {
        List<Product> list = service.selectList(query, null);
        ExcelUtil<ProductVO> util = new ExcelUtil<>(ProductVO.class);
        return ResponseEntity.ok(util.writeExcel(convert.dos2vos(list), "商品信息数据"));
    }

    @ApiOperation("获取商品信息详细信息")
    @PreAuthorize("@ss.hasPermi('pms:product:query')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductVO> getInfo(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.selectById(id));
    }

    @ApiOperation("新增商品信息")
    @PreAuthorize("@ss.hasPermi('pms:product:add')")
    @Log(title = "商品信息", businessType = BusinessType.INSERT)
    @PostMapping
    public ResponseEntity<Integer> add(@RequestBody ProductVO product) {
        return ResponseEntity.ok(service.insert(product));
    }

    @ApiOperation("修改商品信息")
    @PreAuthorize("@ss.hasPermi('pms:product:edit')")
    @Log(title = "商品信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public ResponseEntity<Integer> edit(@RequestBody ProductVO product) {
        return ResponseEntity.ok(service.update(product));
    }

    @ApiOperation("删除商品信息")
    @PreAuthorize("@ss.hasPermi('pms:product:remove')")
    @Log(title = "商品信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{id}")
    public ResponseEntity<Integer> remove(@PathVariable Long id) {
        return ResponseEntity.ok(service.deleteById(id));
    }
}
