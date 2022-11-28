package com.cyl.ums.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import com.cyl.ums.domain.Member;

/**
 * 会员信息Mapper接口
 * 
 * @author zcc
 */
public interface MemberMapper extends BaseMapper<Member> {
    /**
     * 查询会员信息列表
     *
     * @param member 会员信息
     * @return 会员信息集合
     */
    List<Member> selectByEntity(Member member);

    /**
     * 批量软删除
     * @param ids
     * @return
    */
    int updateDelFlagByIds(@Param("ids") Long[] ids);
}
