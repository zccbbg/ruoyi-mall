package com.cyl.h5.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cyl.ums.domain.Member;
import com.cyl.ums.domain.MemberAddress;
import com.cyl.ums.mapper.MemberAddressMapper;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.framework.config.LocalDataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * 会员收货地址Service业务层处理
 * 
 * @author sjm
 */
@Service
@Transactional
public class H5MemberAddressService {
    @Autowired
    private MemberAddressMapper memberAddressMapper;

    /**
     * 查询会员收货地址
     * 
     * @param id 会员收货地址主键
     * @return 会员收货地址
     */
   
    public MemberAddress selectById(Long id) {
        return memberAddressMapper.selectById(id);
    }

    public List<MemberAddress> selectList() {
        Member member = (Member) LocalDataUtil.getVar(Constants.MEMBER_INFO);
        MemberAddress memberAddress = new MemberAddress();
        memberAddress.setMemberId(member.getId());
        return memberAddressMapper.selectByEntity(memberAddress);
    }

    /**
     * 新增会员收货地址
     * 
     * @param memberAddress 会员收货地址
     * @return 结果
     */
    public int insert(MemberAddress memberAddress) {
        Member member = (Member) LocalDataUtil.getVar(Constants.MEMBER_INFO);
        if (memberAddress.getIsDefault() == 1) {
            //将别的设置为0
            memberAddressMapper.updateDefault(0,member.getId());
        }
        memberAddress.setMemberId(member.getId());
        memberAddress.setCreateTime(LocalDateTime.now());
        return memberAddressMapper.insert(memberAddress);
    }

    /**
     * 修改会员收货地址
     * 
     * @param memberAddress 会员收货地址
     * @return 结果
     */
   
    public int update(MemberAddress memberAddress) {
        Member member = (Member) LocalDataUtil.getVar(Constants.MEMBER_INFO);
        if (memberAddress.getIsDefault() == 1) {
            //将别的设置为0
            memberAddressMapper.updateDefault(0,member.getId());
        }
        memberAddress.setUpdateTime(LocalDateTime.now());
        return memberAddressMapper.updateById(memberAddress);
    }

    /**
     * 批量删除会员收货地址
     * 
     * @param ids 需要删除的会员收货地址主键
     * @return 结果
     */
   
    public int deleteByIds(Long[] ids) {
        return memberAddressMapper.deleteBatchIds(Arrays.asList(ids));
    }

    /**
     * 删除会员收货地址信息
     * 
     * @param id 会员收货地址主键
     * @return 结果
     */
   
    public int deleteById(Long id) {
        return memberAddressMapper.deleteById(id);
    }

    public MemberAddress getDefault() {
        Member member = (Member) LocalDataUtil.getVar(Constants.MEMBER_INFO);
        QueryWrapper<MemberAddress> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("member_id",member.getId());
        queryWrapper.eq("is_default",1);
        List<MemberAddress> list = memberAddressMapper.selectList(queryWrapper);
        return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }
}
