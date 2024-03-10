package com.cyl.manager.statistics.controller;


import com.cyl.manager.statistics.domain.query.GoodsStatisticsQuery;
import com.cyl.manager.statistics.domain.query.OrderStatisticsQuery;
import com.cyl.manager.statistics.domain.vo.MemberAndCartStatisticsVO;
import com.cyl.manager.statistics.domain.vo.OrderAndAftersaleStatisticsVO;
import com.cyl.manager.statistics.domain.vo.OrderStatisticsVO;
import com.cyl.manager.statistics.domain.vo.ProductTopVO;
import com.cyl.manager.statistics.service.IndexStatisticsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 管理端,首页统计数据接口
 *
 * @author zhangcheng
 * @since 2023/05/15 13:53
 */
@Slf4j
@Api(tags = "管理端,首页统计数据接口")
@RestController
@RequestMapping("/dev/statistics/index")
public class IndexStatisticsManagerController {

    /**
     * 首页统计
     */
    @Autowired
    private IndexStatisticsService indexStatisticsService;

    @ApiOperation(value = "获取首页查询热卖商品TOP10")
    @GetMapping("/goodsStatistics")
    public ResponseEntity<List<ProductTopVO>> goodsStatistics(@Validated GoodsStatisticsQuery goodsStatisticsQuery) {

        return ResponseEntity.ok(indexStatisticsService.goodsStatistics(goodsStatisticsQuery));
    }

    @ApiOperation(value = "订单信息")
    @PostMapping("/orderStatistics")
    public ResponseEntity<List<OrderStatisticsVO>> orderStatistics(@RequestBody OrderStatisticsQuery param) {
        return ResponseEntity.ok(indexStatisticsService.orderStatistics(param));
    }

    @ApiOperation(value = "会员数，加购数")
    @GetMapping("/memberAndCart/statistics")
    public ResponseEntity<MemberAndCartStatisticsVO> statMemberAndCart(){
        return ResponseEntity.ok(indexStatisticsService.statMemberAndCart());
    }

    @ApiOperation(value = "订单与售后单统计")
    @GetMapping("/order/aftersale/statistics")
    public ResponseEntity<OrderAndAftersaleStatisticsVO> orderAndAftersaleStatistics(){
        return ResponseEntity.ok(indexStatisticsService.orderAndAftersaleStatistics());
    }

}
