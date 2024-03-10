package com.cyl.manager.oms.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cyl.h5.domain.dto.OrderProductListDTO;
import com.cyl.manager.ums.domain.entity.Member;
import com.github.pagehelper.PageHelper;
import com.ruoyi.common.utils.IDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import com.cyl.manager.oms.mapper.OrderItemMapper;
import com.cyl.manager.oms.domain.entity.OrderItem;
import com.cyl.manager.oms.domain.query.OrderItemQuery;
import org.springframework.transaction.annotation.Transactional;

/**
 * 订单中所包含的商品Service业务层处理
 *
 *
 * @author zcc
 */
@Service
public class OrderItemService extends ServiceImpl<OrderItemMapper, OrderItem> {
    @Autowired
    private OrderItemMapper orderItemMapper;

    /**
     * 查询订单中所包含的商品
     *
     * @param id 订单中所包含的商品主键
     * @return 订单中所包含的商品
     */
    public OrderItem selectById(Long id) {
        return orderItemMapper.selectById(id);
    }

    /**
     * 查询订单中所包含的商品列表
     *
     * @param query 查询条件
     * @param page 分页条件
     * @return 订单中所包含的商品
     */
    public List<OrderItem> selectList(OrderItemQuery query, Pageable page) {
        if (page != null) {
            PageHelper.startPage(page.getPageNumber() + 1, page.getPageSize());
        }
        QueryWrapper<OrderItem> qw = new QueryWrapper<>();
        Long orderId = query.getOrderId();
        if (orderId != null) {
            qw.eq("order_id", orderId);
        }
        Long productId = query.getProductId();
        if (productId != null) {
            qw.eq("product_id", productId);
        }
        String outProductId = query.getOutProductId();
        if (!StringUtils.isEmpty(outProductId)) {
            qw.eq("out_product_id", outProductId);
        }
        Long skuId = query.getSkuId();
        if (skuId != null) {
            qw.eq("sku_id", skuId);
        }
        String outSkuId = query.getOutSkuId();
        if (!StringUtils.isEmpty(outSkuId)) {
            qw.eq("out_sku_id", outSkuId);
        }
        Long productSnapshotId = query.getProductSnapshotId();
        if (productSnapshotId != null) {
            qw.eq("product_snapshot_id", productSnapshotId);
        }
        Long skuSnapshotId = query.getSkuSnapshotId();
        if (skuSnapshotId != null) {
            qw.eq("sku_snapshot_id", skuSnapshotId);
        }
        String pic = query.getPic();
        if (!StringUtils.isEmpty(pic)) {
            qw.eq("pic", pic);
        }
        String productNameLike = query.getProductNameLike();
        if (!StringUtils.isEmpty(productNameLike)) {
            qw.like("product_name", productNameLike);
        }
        BigDecimal salePrice = query.getSalePrice();
        if (salePrice != null) {
            qw.eq("sale_price", salePrice);
        }
        BigDecimal purchasePrice = query.getPurchasePrice();
        if (purchasePrice != null) {
            qw.eq("purchase_price", purchasePrice);
        }
        Integer quantity = query.getQuantity();
        if (quantity != null) {
            qw.eq("quantity", quantity);
        }
        Long productCategoryId = query.getProductCategoryId();
        if (productCategoryId != null) {
            qw.eq("product_category_id", productCategoryId);
        }
        String spData = query.getSpData();
        if (!StringUtils.isEmpty(spData)) {
            qw.eq("sp_data", spData);
        }
        return orderItemMapper.selectList(qw);
    }

    /**
     * 新增订单中所包含的商品
     *
     * @param orderItem 订单中所包含的商品
     * @return 结果
     */
    public int insert(OrderItem orderItem) {
        orderItem.setCreateTime(LocalDateTime.now());
        return orderItemMapper.insert(orderItem);
    }

    /**
     * 修改订单中所包含的商品
     *
     * @param orderItem 订单中所包含的商品
     * @return 结果
     */
    public int update(OrderItem orderItem) {
        return orderItemMapper.updateById(orderItem);
    }

    /**
     * 删除订单中所包含的商品信息
     *
     * @param id 订单中所包含的商品主键
     * @return 结果
     */
    public int deleteById(Long id) {
        return orderItemMapper.deleteById(id);
    }

    @Transactional
    public void saveOrderItem(Member member, LocalDateTime optTime,
                              Long orderId, List<OrderProductListDTO> list){
        List<OrderItem> addOrderItemList = new ArrayList<>();
        list.forEach(item -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setId(IDGenerator.generateId());
            orderItem.setOrderId(orderId);
            orderItem.setProductId(item.getProduct().getId());
            orderItem.setOutProductId(item.getProduct().getOutProductId());
            orderItem.setSkuId(item.getSku().getId());
            orderItem.setOutSkuId(item.getSku().getOutSkuId());
            orderItem.setPic(item.getSku().getPic());
            orderItem.setProductName(item.getProduct().getName());
            orderItem.setSalePrice(item.getSku().getPrice());
            orderItem.setQuantity(item.getQuantity());
            orderItem.setProductCategoryId(item.getProduct().getCategoryId());
            orderItem.setSpData(item.getSku().getSpData());
            orderItem.setCreateBy(member.getId());
            orderItem.setCreateTime(optTime);
            addOrderItemList.add(orderItem);
        });
        boolean flag = saveBatch(addOrderItemList);
        if (!flag){
            throw new RuntimeException("新增订单item失败");
        }
    }
}
