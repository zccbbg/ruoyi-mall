package com.cyl.ums.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import com.cyl.ums.domain.MemberAddress;

/**
 * 会员收货地址Mapper接口
 * 
 * @author zcc
 */
public interface MemberAddressMapper extends BaseMapper<MemberAddress> {
    /**
     * 查询会员收货地址列表
     *
     * @param memberAddress 会员收货地址
     * @return 会员收货地址集合
     */
    List<MemberAddress> selectByEntity(MemberAddress memberAddress);

    int updateByPrimaryKeySelective(MemberAddress address);
}
