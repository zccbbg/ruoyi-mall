package com.cyl.manager.ums.controller;

import com.cyl.manager.ums.convert.MemberLogininforConvert;
import com.cyl.manager.ums.domain.entity.MemberLogininfor;
import com.cyl.manager.ums.domain.query.MemberLogininforQuery;
import com.cyl.manager.ums.domain.vo.MemberLogininforVO;
import com.cyl.manager.ums.service.MemberLogininforService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.service.TokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 会员登录记录Controller
 * 
 * @author zcc
 * @date 2023-07-26
 */
@Api(description ="会员登录记录接口列表")
@RestController
@RequestMapping("/ums/memberLogininfor")
public class MemberLogininforController extends BaseController {
    @Autowired
    private MemberLogininforService service;
    @Autowired
    private MemberLogininforConvert convert;
    @Autowired
    private TokenService tokenService;

    @ApiOperation("查询会员登录记录列表")
    @PreAuthorize("@ss.hasPermi('ums:memberLogininfor:list')")
    @PostMapping("/list")
    public ResponseEntity<Page<MemberLogininfor>> list(@RequestBody MemberLogininforQuery query, Pageable page) {
        List<MemberLogininfor> list = service.selectList(query, page);
        return ResponseEntity.ok(new PageImpl<>(list, page, ((com.github.pagehelper.Page)list).getTotal()));
    }

    @ApiOperation("导出会员登录记录列表")
    @PreAuthorize("@ss.hasPermi('ums:memberLogininfor:export')")
    @Log(title = "会员登录记录", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public ResponseEntity<String> export(MemberLogininforQuery query) {
        List<MemberLogininfor> list = service.selectList(query, null);
        ExcelUtil<MemberLogininforVO> util = new ExcelUtil<>(MemberLogininforVO.class);
        return ResponseEntity.ok(util.writeExcel(convert.dos2vos(list), "会员登录记录数据"));
    }

    @ApiOperation("获取会员登录记录详细信息")
    @PreAuthorize("@ss.hasPermi('ums:memberLogininfor:query')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<MemberLogininfor> getInfo(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.selectById(id));
    }

    @ApiOperation("修改会员登录记录")
    @PreAuthorize("@ss.hasPermi('ums:memberLogininfor:edit')")
    @Log(title = "会员登录记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public ResponseEntity<Integer> edit(@RequestBody MemberLogininfor memberLogininfor) {
        return ResponseEntity.ok(service.update(memberLogininfor));
    }

    @ApiOperation("删除会员登录记录")
    @PreAuthorize("@ss.hasPermi('ums:memberLogininfor:remove')")
    @Log(title = "会员登录记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{id}")
    public ResponseEntity<Integer> remove(@PathVariable Long id) {
        return ResponseEntity.ok(service.deleteById(id));
    }
}
