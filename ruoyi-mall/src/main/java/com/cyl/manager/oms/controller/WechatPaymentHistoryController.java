package com.cyl.manager.oms.controller;

import java.util.List;

import com.cyl.manager.oms.domain.entity.WechatPaymentHistory;
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
import com.cyl.manager.oms.convert.WechatPaymentHistoryConvert;
import com.cyl.manager.oms.domain.query.WechatPaymentHistoryQuery;
import com.cyl.manager.oms.service.WechatPaymentHistoryService;
import com.cyl.manager.oms.domain.vo.WechatPaymentHistoryVO;
import com.ruoyi.common.utils.poi.ExcelUtil;
/**
 * 微信订单表Controller
 * 
 * @author zcc
 * @date 2023-07-12
 */
@Api(description ="微信订单表接口列表")
@RestController
@RequestMapping("/pms/omsWechatPaymentHistory")
public class WechatPaymentHistoryController extends BaseController {
    @Autowired
    private WechatPaymentHistoryService service;
    @Autowired
    private WechatPaymentHistoryConvert convert;

    @ApiOperation("查询微信订单表列表")
    @PreAuthorize("@ss.hasPermi('pms:omsWechatPaymentHistory:list')")
    @PostMapping("/list")
    public ResponseEntity<Page<WechatPaymentHistory>> list(@RequestBody WechatPaymentHistoryQuery query, Pageable page) {
        List<WechatPaymentHistory> list = service.selectList(query, page);
        return ResponseEntity.ok(new PageImpl<>(list, page, ((com.github.pagehelper.Page)list).getTotal()));
    }

    @ApiOperation("导出微信订单表列表")
    @PreAuthorize("@ss.hasPermi('pms:omsWechatPaymentHistory:export')")
    @Log(title = "微信订单表", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public ResponseEntity<String> export(WechatPaymentHistoryQuery query) {
        List<WechatPaymentHistory> list = service.selectList(query, null);
        ExcelUtil<WechatPaymentHistoryVO> util = new ExcelUtil<>(WechatPaymentHistoryVO.class);
        return ResponseEntity.ok(util.writeExcel(convert.dos2vos(list), "微信订单表数据"));
    }

    @ApiOperation("获取微信订单表详细信息")
    @PreAuthorize("@ss.hasPermi('pms:omsWechatPaymentHistory:query')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<WechatPaymentHistory> getInfo(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.selectById(id));
    }

    @ApiOperation("新增微信订单表")
    @PreAuthorize("@ss.hasPermi('pms:omsWechatPaymentHistory:add')")
    @Log(title = "微信订单表", businessType = BusinessType.INSERT)
    @PostMapping
    public ResponseEntity<Integer> add(@RequestBody WechatPaymentHistory wechatPaymentHistory) {
        return ResponseEntity.ok(service.insert(wechatPaymentHistory));
    }

    @ApiOperation("修改微信订单表")
    @PreAuthorize("@ss.hasPermi('pms:omsWechatPaymentHistory:edit')")
    @Log(title = "微信订单表", businessType = BusinessType.UPDATE)
    @PutMapping
    public ResponseEntity<Integer> edit(@RequestBody WechatPaymentHistory wechatPaymentHistory) {
        return ResponseEntity.ok(service.update(wechatPaymentHistory));
    }

    @ApiOperation("删除微信订单表")
    @PreAuthorize("@ss.hasPermi('pms:omsWechatPaymentHistory:remove')")
    @Log(title = "微信订单表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{id}")
    public ResponseEntity<Integer> remove(@PathVariable Long id) {
        return ResponseEntity.ok(service.deleteById(id));
    }
}
