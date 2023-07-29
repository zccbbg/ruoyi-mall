package com.cyl.manager.statistics.mapper;


import com.cyl.manager.statistics.pojo.GoodsStatisticsQueryParam;
import com.cyl.manager.statistics.pojo.OrderStatisticsQueryParam;
import com.cyl.manager.statistics.pojo.vo.OrderStatisticsVO;
import com.cyl.manager.statistics.pojo.vo.ProductTopVO;

import java.util.List;

public interface IndexStatisticsMapper {
    List<ProductTopVO> goodsSkuStatistics(GoodsStatisticsQueryParam goodsStatisticsQueryParam);

    List<ProductTopVO> goodsStatistics(GoodsStatisticsQueryParam goodsStatisticsQueryParam);
    List<OrderStatisticsVO> orderStatistics(OrderStatisticsQueryParam param);

}
