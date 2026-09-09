package com.cyl.manager.ums.controller;

import java.util.List;
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
import com.cyl.manager.ums.convert.MemberAccountConvert;
import com.cyl.manager.ums.domain.entity.MemberAccount;
import com.cyl.manager.ums.domain.query.MemberAccountQuery;
import com.cyl.manager.ums.service.MemberAccountService;
import com.cyl.manager.ums.domain.vo.MemberAccountVO;
import com.ruoyi.common.utils.poi.ExcelUtil;
/**
 * 会员账户表Controller
 * 
 * @author zcc
 * @date 2024-03-01
 */
@RestController
@RequestMapping("/ums/memberAccount")
public class MemberAccountController extends BaseController {
    @Autowired
    private MemberAccountService service;
    @Autowired
    private MemberAccountConvert convert;

    @PreAuthorize("@ss.hasPermi('ums:memberAccount:list')")
    @PostMapping("/list")
    public ResponseEntity<Page<MemberAccount>> list(@RequestBody MemberAccountQuery query, Pageable page) {
        List<MemberAccount> list = service.selectList(query, page);
        return ResponseEntity.ok(new PageImpl<>(list, page, ((com.github.pagehelper.Page)list).getTotal()));
    }

    @PreAuthorize("@ss.hasPermi('ums:memberAccount:export')")
    @Log(title = "会员账户表", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public ResponseEntity<String> export(MemberAccountQuery query) {
        List<MemberAccount> list = service.selectList(query, null);
        ExcelUtil<MemberAccountVO> util = new ExcelUtil<>(MemberAccountVO.class);
        return ResponseEntity.ok(util.writeExcel(convert.dos2vos(list), "会员账户表数据"));
    }

    @PreAuthorize("@ss.hasPermi('ums:memberAccount:query')")
    @GetMapping(value = "/{memberId}")
    public ResponseEntity<MemberAccount> getInfo(@PathVariable("memberId") Long memberId) {
        return ResponseEntity.ok(service.selectByMemberId(memberId));
    }

    @PreAuthorize("@ss.hasPermi('ums:memberAccount:add')")
    @Log(title = "会员账户表", businessType = BusinessType.INSERT)
    @PostMapping
    public ResponseEntity<Integer> add(@RequestBody MemberAccount memberAccount) {
        return ResponseEntity.ok(service.insert(memberAccount));
    }

    @PreAuthorize("@ss.hasPermi('ums:memberAccount:edit')")
    @Log(title = "会员账户表", businessType = BusinessType.UPDATE)
    @PutMapping
    public ResponseEntity<Integer> edit(@RequestBody MemberAccount memberAccount) {
        return ResponseEntity.ok(service.update(memberAccount));
    }

    @PreAuthorize("@ss.hasPermi('ums:memberAccount:remove')")
    @Log(title = "会员账户表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{memberId}")
    public ResponseEntity<Integer> remove(@PathVariable Long memberId) {
        return ResponseEntity.ok(service.deleteByMemberId(memberId));
    }
}
