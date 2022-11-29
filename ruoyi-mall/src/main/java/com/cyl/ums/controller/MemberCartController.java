package com.cyl.ums.controller;

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
import com.cyl.ums.convert.MemberCartConvert;
import com.cyl.ums.domain.MemberCart;
import com.cyl.ums.pojo.query.MemberCartQuery;
import com.cyl.ums.service.MemberCartService;
import com.cyl.ums.pojo.vo.MemberCartVO;
import com.ruoyi.common.utils.poi.ExcelUtil;
/**
 * 购物车Controller
 * 
 * @author zcc
 * @date 2022-11-29
 */
@Api(description ="购物车接口列表")
@RestController
@RequestMapping("/ums/memberCart")
public class MemberCartController extends BaseController {
    @Autowired
    private MemberCartService service;
    @Autowired
    private MemberCartConvert convert;

    @ApiOperation("查询购物车列表")
    @PreAuthorize("@ss.hasPermi('ums:memberCart:list')")
    @PostMapping("/list")
    public ResponseEntity<Page<MemberCart>> list(@RequestBody MemberCartQuery query, Pageable page) {
        List<MemberCart> list = service.selectList(query, page);
        return ResponseEntity.ok(new PageImpl<>(list, page, ((com.github.pagehelper.Page)list).getTotal()));
    }

    @ApiOperation("导出购物车列表")
    @PreAuthorize("@ss.hasPermi('ums:memberCart:export')")
    @Log(title = "购物车", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public ResponseEntity<String> export(MemberCartQuery query) {
        List<MemberCart> list = service.selectList(query, null);
        ExcelUtil<MemberCartVO> util = new ExcelUtil<>(MemberCartVO.class);
        return ResponseEntity.ok(util.writeExcel(convert.dos2vos(list), "购物车数据"));
    }

    @ApiOperation("获取购物车详细信息")
    @PreAuthorize("@ss.hasPermi('ums:memberCart:query')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<MemberCart> getInfo(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.selectById(id));
    }

    @ApiOperation("新增购物车")
    @PreAuthorize("@ss.hasPermi('ums:memberCart:add')")
    @Log(title = "购物车", businessType = BusinessType.INSERT)
    @PostMapping
    public ResponseEntity<Integer> add(@RequestBody MemberCart memberCart) {
        return ResponseEntity.ok(service.insert(memberCart));
    }

    @ApiOperation("修改购物车")
    @PreAuthorize("@ss.hasPermi('ums:memberCart:edit')")
    @Log(title = "购物车", businessType = BusinessType.UPDATE)
    @PutMapping
    public ResponseEntity<Integer> edit(@RequestBody MemberCart memberCart) {
        return ResponseEntity.ok(service.update(memberCart));
    }

    @ApiOperation("删除购物车")
    @PreAuthorize("@ss.hasPermi('ums:memberCart:remove')")
    @Log(title = "购物车", businessType = BusinessType.DELETE)
	@DeleteMapping("/{id}")
    public ResponseEntity<Integer> remove(@PathVariable Long id) {
        return ResponseEntity.ok(service.deleteById(id));
    }
}
