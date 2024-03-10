package com.cyl.manager.ums.controller;

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
import com.cyl.manager.ums.convert.MemberWechatConvert;
import com.cyl.manager.ums.domain.entity.MemberWechat;
import com.cyl.manager.ums.domain.query.MemberWechatQuery;
import com.cyl.manager.ums.service.MemberWechatService;
import com.cyl.manager.ums.domain.vo.MemberWechatVO;
import com.ruoyi.common.utils.poi.ExcelUtil;
/**
 * 用户微信信息Controller
 * 
 * @author zcc
 * @date 2022-11-28
 */
@Api(description ="用户微信信息接口列表")
@RestController
@RequestMapping("/ums/memberWechat")
public class MemberWechatController extends BaseController {
    @Autowired
    private MemberWechatService service;
    @Autowired
    private MemberWechatConvert convert;

    @ApiOperation("查询用户微信信息列表")
    @PreAuthorize("@ss.hasPermi('ums:memberWechat:list')")
    @PostMapping("/list")
    public ResponseEntity<Page<MemberWechat>> list(@RequestBody MemberWechatQuery query, Pageable page) {
        List<MemberWechat> list = service.selectList(query, page);
        return ResponseEntity.ok(new PageImpl<>(list, page, ((com.github.pagehelper.Page)list).getTotal()));
    }

    @ApiOperation("导出用户微信信息列表")
    @PreAuthorize("@ss.hasPermi('ums:memberWechat:export')")
    @Log(title = "用户微信信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public ResponseEntity<String> export(MemberWechatQuery query) {
        List<MemberWechat> list = service.selectList(query, null);
        ExcelUtil<MemberWechatVO> util = new ExcelUtil<>(MemberWechatVO.class);
        return ResponseEntity.ok(util.writeExcel(convert.dos2vos(list), "用户微信信息数据"));
    }

    @ApiOperation("获取用户微信信息详细信息")
    @PreAuthorize("@ss.hasPermi('ums:memberWechat:query')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<MemberWechat> getInfo(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.selectById(id));
    }

    @ApiOperation("新增用户微信信息")
    @PreAuthorize("@ss.hasPermi('ums:memberWechat:add')")
    @Log(title = "用户微信信息", businessType = BusinessType.INSERT)
    @PostMapping
    public ResponseEntity<Integer> add(@RequestBody MemberWechat memberWechat) {
        return ResponseEntity.ok(service.insert(memberWechat));
    }

    @ApiOperation("修改用户微信信息")
    @PreAuthorize("@ss.hasPermi('ums:memberWechat:edit')")
    @Log(title = "用户微信信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public ResponseEntity<Integer> edit(@RequestBody MemberWechat memberWechat) {
        return ResponseEntity.ok(service.update(memberWechat));
    }

    @ApiOperation("删除用户微信信息")
    @PreAuthorize("@ss.hasPermi('ums:memberWechat:remove')")
    @Log(title = "用户微信信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{id}")
    public ResponseEntity<Integer> remove(@PathVariable Long id) {
        return ResponseEntity.ok(service.deleteById(id));
    }
}
