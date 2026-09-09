package com.cyl.manager.act.controller;

import com.cyl.h5.config.SecurityUtil;
import com.cyl.manager.act.domain.entity.IntegralHistory;
import com.cyl.manager.act.domain.query.IntegralHistoryQuery;
import com.cyl.manager.act.domain.vo.IntegralStatVO;
import com.cyl.manager.act.service.IntegralHistoryService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 积分流水表Controller
 * 
 * @author zcc
 * @date 2024-03-01
 */
@RestController
@RequestMapping("/h5/act/integral")
public class H5IntegralHistoryController extends BaseController {
    @Autowired
    private IntegralHistoryService service;

    @GetMapping("/list")
    public ResponseEntity<List<IntegralHistory>> list(Integer month) {
        IntegralHistoryQuery query = new IntegralHistoryQuery();
        query.setMemberId(SecurityUtil.getLocalMember().getId());
        query.setOpType(1);
        query.setSubOpType(11);
        List<LocalDateTime> timeDiff = DateUtils.getTimeDiff(month);
        query.setStart(timeDiff.get(0));
        query.setEnd(timeDiff.get(1));
        List<IntegralHistory> list = service.selectList2(query);
        return ResponseEntity.ok(list);
    }

    @Log(title = "积分流水表", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public ResponseEntity<Integer> add(BigDecimal amount) {

        IntegralHistory history = new IntegralHistory();
        history.setOpType(1);
        history.setSubOpType(11);
        history.setAmount(amount);
        history.setMemberId(SecurityUtil.getLocalMember().getId());
        history.setCreateTime(LocalDateTime.now());
        return ResponseEntity.ok(service.insert2(history));
    }

    @PostMapping("/history/list")
    public ResponseEntity<Page<IntegralHistory>> list(@RequestBody IntegralHistoryQuery query, Pageable page) {
        List<IntegralHistory> list = service.selectListByH5(query, page);
        return ResponseEntity.ok(new PageImpl<>(list, page, ((com.github.pagehelper.Page)list).getTotal()));
    }

    @PostMapping("/stat")
    public ResponseEntity<IntegralStatVO> statIntegral(@RequestBody IntegralHistoryQuery query) {
        IntegralStatVO res = service.statIntegral(query);
        return ResponseEntity.ok(res);
    }

}
