package com.cyl.manager.statistics.mapper;


import com.cyl.manager.statistics.domain.query.GoodsStatisticsQuery;
import com.cyl.manager.statistics.domain.query.OrderStatisticsQuery;
import com.cyl.manager.statistics.domain.vo.OrderStatisticsVO;
import com.cyl.manager.statistics.domain.vo.ProductTopVO;

import java.util.List;

public interface IndexStatisticsMapper {
    List<ProductTopVO> goodsSkuStatistics(GoodsStatisticsQuery goodsStatisticsQuery);

    List<ProductTopVO> goodsStatistics(GoodsStatisticsQuery goodsStatisticsQuery);
    List<OrderStatisticsVO> orderStatistics(OrderStatisticsQuery param);

}
