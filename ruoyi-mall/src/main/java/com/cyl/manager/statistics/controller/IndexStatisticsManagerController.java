package com.cyl.manager.statistics.controller;


import com.cyl.manager.statistics.pojo.GoodsStatisticsQueryParam;
import com.cyl.manager.statistics.pojo.OrderStatisticsQueryParam;
import com.cyl.manager.statistics.pojo.vo.MemberAndCartStatisticsVO;
import com.cyl.manager.statistics.pojo.vo.OrderAndAftersaleStatisticsVO;
import com.cyl.manager.statistics.pojo.vo.OrderStatisticsVO;
import com.cyl.manager.statistics.pojo.vo.ProductTopVO;
import com.cyl.manager.statistics.service.IndexStatisticsService;
import com.ruoyi.common.core.domain.AjaxResult;
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
    public ResponseEntity<List<ProductTopVO>> goodsStatistics(@Validated GoodsStatisticsQueryParam goodsStatisticsQueryParam) {

        return ResponseEntity.ok(indexStatisticsService.goodsStatistics(goodsStatisticsQueryParam));
    }

    @ApiOperation(value = "订单信息")
    @PostMapping("/orderStatistics")
    public ResponseEntity<List<OrderStatisticsVO>> orderStatistics(@RequestBody OrderStatisticsQueryParam param) {
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
