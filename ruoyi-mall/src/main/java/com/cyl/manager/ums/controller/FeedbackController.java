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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.enums.BusinessType;
import com.cyl.manager.ums.convert.FeedbackConvert;
import com.cyl.manager.ums.domain.entity.Feedback;
import com.cyl.manager.ums.domain.query.FeedbackQuery;
import com.cyl.manager.ums.service.FeedbackService;

/**
 * 意见反馈Controller
 * 
 * @author zcc
 * @date 2024-02-26
 */
@Api(description ="意见反馈接口列表")
@RestController
@RequestMapping("/ums/feedback")
public class FeedbackController extends BaseController {
    @Autowired
    private FeedbackService service;
    @Autowired
    private FeedbackConvert convert;

    @ApiOperation("查询意见反馈列表")
    @PreAuthorize("@ss.hasPermi('ums:feedback:list')")
    @PostMapping("/list")
    public ResponseEntity<Page<Feedback>> list(@RequestBody FeedbackQuery query, Pageable page) {
        List<Feedback> list = service.selectList(query, page);
        return ResponseEntity.ok(new PageImpl<>(list, page, ((com.github.pagehelper.Page)list).getTotal()));
    }


    @ApiOperation("修改意见反馈备注信息")
    @Log(title = "意见反馈", businessType = BusinessType.UPDATE)
    @PostMapping("/mark/update")
    public ResponseEntity<Integer> editMark(@RequestBody Feedback feedback) {
        return ResponseEntity.ok(service.updateMark(feedback));
    }

    @ApiOperation(("修改状态"))
    @Log(title = "意见反馈", businessType = BusinessType.UPDATE)
    @PostMapping("/handle/status/change")
    public ResponseEntity<Integer> changeStatus(@RequestBody Feedback dto){
        return ResponseEntity.ok(service.changeStatus(dto));
    }


    @ApiOperation("删除意见反馈")
    @PreAuthorize("@ss.hasPermi('ums:feedback:remove')")
    @Log(title = "意见反馈", businessType = BusinessType.DELETE)
	@DeleteMapping("/{id}")
    public ResponseEntity<Integer> remove(@PathVariable Long id) {
        return ResponseEntity.ok(service.deleteById(id));
    }
}
