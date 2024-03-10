package com.cyl.manager.ums.mapper;

import java.time.LocalDateTime;
import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import com.cyl.manager.ums.domain.entity.MemberLogininfor;

/**
 * 会员登录记录Mapper接口
 * 
 * @author zcc
 */
public interface MemberLogininforMapper extends BaseMapper<MemberLogininfor> {
    /**
     * 查询会员登录记录列表
     *
     * @param memberLogininfor 会员登录记录
     * @return 会员登录记录集合
     */
    List<MemberLogininfor> selectByEntity(MemberLogininfor memberLogininfor);

    int statLoginMember(@Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime);
}
