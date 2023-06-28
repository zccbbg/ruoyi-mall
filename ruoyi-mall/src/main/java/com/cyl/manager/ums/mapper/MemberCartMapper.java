package com.cyl.manager.ums.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cyl.manager.ums.domain.MemberCart;

/**
 * 购物车Mapper接口
 * 
 * @author zcc
 */
public interface MemberCartMapper extends BaseMapper<MemberCart> {
    /**
     * 查询购物车列表
     *
     * @param memberCart 购物车
     * @return 购物车集合
     */
    List<MemberCart> selectByEntity(MemberCart memberCart);
}