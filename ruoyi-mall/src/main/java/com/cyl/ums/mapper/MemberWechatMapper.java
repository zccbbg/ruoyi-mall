package com.cyl.ums.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import com.cyl.ums.domain.MemberWechat;

/**
 * 用户微信信息Mapper接口
 * 
 * @author zcc
 */
public interface MemberWechatMapper extends BaseMapper<MemberWechat> {
    /**
     * 查询用户微信信息列表
     *
     * @param memberWechat 用户微信信息
     * @return 用户微信信息集合
     */
    List<MemberWechat> selectByEntity(MemberWechat memberWechat);

    /**
     * 批量软删除
     * @param ids
     * @return
    */
    int updateDelFlagByIds(@Param("ids") Long[] ids);
}
