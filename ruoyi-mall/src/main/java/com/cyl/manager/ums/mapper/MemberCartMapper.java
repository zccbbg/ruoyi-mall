package com.cyl.manager.ums.mapper;

import java.time.LocalDateTime;
import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cyl.manager.ums.domain.entity.MemberCart;
import com.cyl.manager.ums.domain.query.MemberCartQuery;
import com.cyl.manager.ums.domain.vo.MemberCartVO;
import org.apache.ibatis.annotations.Param;

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

    /**
     *
     */
    List<MemberCartVO> selectByPage(MemberCartQuery query);

    int statAddCount(@Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime);
}
