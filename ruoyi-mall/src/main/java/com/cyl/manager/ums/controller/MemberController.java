package com.cyl.manager.ums.controller;

import com.cyl.manager.ums.convert.MemberConvert;
import com.cyl.manager.ums.domain.form.ChangeMemberStatusForm;
import com.cyl.manager.ums.domain.entity.Member;
import com.cyl.manager.ums.domain.query.MemberQuery;
import com.cyl.manager.ums.domain.vo.MemberDataStatisticsVO;
import com.cyl.manager.ums.domain.vo.MemberVO;
import com.cyl.manager.ums.service.MemberService;
import com.cyl.wechat.WechatAuthService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
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
 * 会员信息Controller
 * 
 * @author zcc
 * @date 2022-11-28
 */
@Api(description ="会员信息接口列表")
@RestController
public class MemberController extends BaseController {
    @Autowired
    private MemberService service;
    @Autowired
    private MemberConvert convert;
    @Autowired
    private WechatAuthService wechatAuthService;

    @RequestMapping(path ={"/ums/member/wechat/code","/h5/member/wechat/code","/no-auth/wechat/code"},method=RequestMethod.GET)
    public AjaxResult getWechatCode(@RequestParam(required = false) String scene) {
        return AjaxResult.successData(wechatAuthService.getQRCode(scene));
    }

    @ApiOperation("查询会员信息列表")
    @PreAuthorize("@ss.hasPermi('ums:member:list')")
    @PostMapping("/ums/member/list")
    public ResponseEntity<Page<Member>> list(@RequestBody MemberQuery query, Pageable page) {
        List<Member> list = service.selectList(query, page);
        return ResponseEntity.ok(new PageImpl<>(list, page, ((com.github.pagehelper.Page)list).getTotal()));
    }

    @ApiOperation("导出会员信息列表")
    @PreAuthorize("@ss.hasPermi('ums:member:export')")
    @Log(title = "会员信息", businessType = BusinessType.EXPORT)
    @GetMapping("/ums/member/export")
    public ResponseEntity<String> export(MemberQuery query) {
        List<Member> list = service.selectList(query, null);
        ExcelUtil<MemberVO> util = new ExcelUtil<>(MemberVO.class);
        return ResponseEntity.ok(util.writeExcel(convert.dos2vos(list), "会员信息数据"));
    }

    @ApiOperation("获取会员信息详细信息")
    @PreAuthorize("@ss.hasPermi('ums:member:query')")
    @GetMapping(value = "/ums/member/{id}")
    public ResponseEntity<Member> getInfo(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.selectById(id));
    }

    @ApiOperation("新增会员信息")
    @PreAuthorize("@ss.hasPermi('ums:member:add')")
    @Log(title = "会员信息", businessType = BusinessType.INSERT)
    @PostMapping("/ums/member")
    public ResponseEntity<Integer> add(@RequestBody Member member) {
        return ResponseEntity.ok(service.insert(member));
    }

    @ApiOperation("修改会员信息")
    @PreAuthorize("@ss.hasPermi('ums:member:edit')")
    @Log(title = "会员信息", businessType = BusinessType.UPDATE)
    @PutMapping("/ums/member")
    public ResponseEntity<Integer> edit(@RequestBody Member member) {
        return ResponseEntity.ok(service.update(member));
    }

    @ApiOperation("修改会员备注信息")
    @PreAuthorize("@ss.hasPermi('ums:member:edit')")
    @Log(title = "会员备注信息", businessType = BusinessType.UPDATE)
    @PostMapping("/ums/member/mark/update")
    public ResponseEntity<Integer> editMark(@RequestBody Member member) {
        return ResponseEntity.ok(service.updateMark(member));
    }

    @ApiOperation("删除会员信息")
    @PreAuthorize("@ss.hasPermi('ums:member:remove')")
    @Log(title = "会员信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/ums/member/{id}")
    public ResponseEntity<Integer> remove(@PathVariable Long id) {
        return ResponseEntity.ok(service.deleteById(id));
    }

    @ApiOperation(("修改会员账户状态"))
    @Log(title = "会员信息", businessType = BusinessType.UPDATE)
    @PostMapping("/ums/member/status/change")
    public ResponseEntity<Integer> changeStatus(@RequestBody ChangeMemberStatusForm form){
        return ResponseEntity.ok(service.changeStatus(form));
    }

    @ApiOperation("会员手机号解密")
    @GetMapping("/ums/member/phone/decrypt/{phoneEncrypted}")
    public ResponseEntity<String> getPhoneDecrypted(@PathVariable String phoneEncrypted){
        return ResponseEntity.ok(service.getPhoneDecrypted(phoneEncrypted));
    }

    @ApiOperation("查看会员统计数据")
    @GetMapping("/ums/member/view/statistics/{memberId}")
    public ResponseEntity<MemberDataStatisticsVO> viewStatistics(@PathVariable Long memberId){
        return ResponseEntity.ok(service.viewStatistics(memberId));
    }
}
