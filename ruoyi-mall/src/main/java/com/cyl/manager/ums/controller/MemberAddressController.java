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
import com.cyl.manager.ums.convert.MemberAddressConvert;
import com.cyl.manager.ums.domain.entity.MemberAddress;
import com.cyl.manager.ums.domain.query.MemberAddressQuery;
import com.cyl.manager.ums.service.MemberAddressService;
import com.cyl.manager.ums.domain.vo.MemberAddressVO;

/**
 * 会员收货地址Controller
 * 
 * @author zcc
 * @date 2022-11-28
 */
@Api(description ="会员收货地址接口列表")
@RestController
@RequestMapping("/ums/memberAddress")
public class MemberAddressController extends BaseController {
    @Autowired
    private MemberAddressService service;
    @Autowired
    private MemberAddressConvert convert;

    @ApiOperation("查询会员收货地址列表")
    @PreAuthorize("@ss.hasPermi('ums:memberAddress:list')")
    @PostMapping("/list")
    public ResponseEntity<Page<MemberAddressVO>> list(@RequestBody MemberAddressQuery query, Pageable page) {
        List<MemberAddress> list = service.selectList(query, page);
        List<MemberAddressVO> resList = convert.dos2vos(list);
        return ResponseEntity.ok(new PageImpl<>(resList, page, ((com.github.pagehelper.Page)list).getTotal()));
    }

    @ApiOperation("导出会员收货地址列表")
    @PreAuthorize("@ss.hasPermi('ums:memberAddress:export')")
    @Log(title = "会员收货地址", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public ResponseEntity<String> export(MemberAddressQuery query) {
//        List<MemberAddressV> list = service.selectList(query, null);
//        ExcelUtil<MemberAddressVO> util = new ExcelUtil<>(MemberAddressVO.class);
//        return ResponseEntity.ok(util.writeExcel(convert.dos2vos(list), "会员收货地址数据"));
        return null;
    }

    @ApiOperation("获取会员收货地址详细信息")
    @PreAuthorize("@ss.hasPermi('ums:memberAddress:query')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<MemberAddress> getInfo(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.selectById(id));
    }

    @ApiOperation("新增会员收货地址")
    @PreAuthorize("@ss.hasPermi('ums:memberAddress:add')")
    @Log(title = "会员收货地址", businessType = BusinessType.INSERT)
    @PostMapping
    public ResponseEntity<Integer> add(@RequestBody MemberAddress memberAddress) {
        return ResponseEntity.ok(service.insert(memberAddress));
    }

    @ApiOperation("修改会员收货地址")
    @PreAuthorize("@ss.hasPermi('ums:memberAddress:edit')")
    @Log(title = "会员收货地址", businessType = BusinessType.UPDATE)
    @PutMapping
    public ResponseEntity<Integer> edit(@RequestBody MemberAddress memberAddress) {
        return ResponseEntity.ok(service.update(memberAddress));
    }

    @ApiOperation("删除会员收货地址")
    @PreAuthorize("@ss.hasPermi('ums:memberAddress:remove')")
    @Log(title = "会员收货地址", businessType = BusinessType.DELETE)
	@DeleteMapping("/{id}")
    public ResponseEntity<Integer> remove(@PathVariable Long id) {
        return ResponseEntity.ok(service.deleteById(id));
    }
}
