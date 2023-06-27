package com.cyl.h5.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cyl.ums.domain.Member;
import com.cyl.ums.domain.MemberAddress;
import com.cyl.ums.mapper.MemberAddressMapper;
import com.cyl.ums.pojo.dto.MemberAddressDTO;
import com.cyl.ums.pojo.vo.MemberAddressVO;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.AesCryptoUtils;
import com.ruoyi.common.utils.PhoneUtils;
import com.ruoyi.framework.config.LocalDataUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    @Value("${aes.key}")
    private String aesKey;

    /**
     * 查询会员收货地址
     * 
     * @param id 会员收货地址主键
     * @return 会员收货地址
     */
   
    public MemberAddressVO selectById(Long id) {
        MemberAddress memberAddress = memberAddressMapper.selectById(id);
        MemberAddressVO memberAddressVO = new MemberAddressVO();
        BeanUtils.copyProperties(memberAddress, memberAddressVO);
        memberAddressVO.setPhone(AesCryptoUtils.decrypt(aesKey, memberAddress.getPhoneEncrypted()));
        return memberAddressVO;
    }

    public List<MemberAddressVO> selectList() {
        Member member = (Member) LocalDataUtil.getVar(Constants.MEMBER_INFO);
        MemberAddress memberAddress = new MemberAddress();
        memberAddress.setMemberId(member.getId());
        List<MemberAddress> memberAddressesList = memberAddressMapper.selectByEntity(memberAddress);
        return  memberAddressesList.stream().map(it -> {
            MemberAddressVO vo = new MemberAddressVO();
            BeanUtils.copyProperties(it, vo);
            vo.setPhone(AesCryptoUtils.decrypt(aesKey, it.getPhoneEncrypted()));
            return vo;
        }).collect(Collectors.toList());
    }

    /**
     * 新增会员收货地址
     * 
     * @param memberAddressDTO 会员收货地址
     * @return 结果
     */
    public int insert(MemberAddressDTO memberAddressDTO) {
        Member member = (Member) LocalDataUtil.getVar(Constants.MEMBER_INFO);
        if (memberAddressDTO.getIsDefault() == 1) {
            //将别的设置为0
            memberAddressMapper.updateDefault(0,member.getId());
        }
        MemberAddress memberAddress = new MemberAddress();
        BeanUtils.copyProperties(memberAddressDTO, memberAddress);
        memberAddress.setPhoneHidden(PhoneUtils.hidePhone(memberAddressDTO.getPhone()));
        memberAddress.setPhoneEncrypted(AesCryptoUtils.encrypt(aesKey, memberAddressDTO.getPhone()));
        memberAddress.setMemberId(member.getId());
        memberAddress.setCreateTime(LocalDateTime.now());
        return memberAddressMapper.insert(memberAddress);
    }

    /**
     * 修改会员收货地址
     * 
     * @param memberAddressDTO 会员收货地址
     * @return 结果
     */
   
    public int update(MemberAddressDTO memberAddressDTO) {
        Member member = (Member) LocalDataUtil.getVar(Constants.MEMBER_INFO);
        if (memberAddressDTO.getIsDefault() == 1) {
            //将别的设置为0
            memberAddressMapper.updateDefault(0,member.getId());
        }
        MemberAddress memberAddress = new MemberAddress();
        BeanUtils.copyProperties(memberAddressDTO, memberAddress);
        memberAddress.setPhoneHidden(PhoneUtils.hidePhone(memberAddressDTO.getPhone()));
        memberAddress.setPhoneEncrypted(AesCryptoUtils.encrypt(aesKey, memberAddressDTO.getPhone()));
        memberAddress.setUpdateTime(LocalDateTime.now());
        memberAddress.setUpdateBy(member.getId());
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

    public MemberAddressVO getDefault() {
        Member member = (Member) LocalDataUtil.getVar(Constants.MEMBER_INFO);
        QueryWrapper<MemberAddress> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("member_id",member.getId());
        queryWrapper.eq("is_default",1);
        List<MemberAddress> list = memberAddressMapper.selectList(queryWrapper);
        if (CollectionUtils.isEmpty(list)){
            return null;
        }
        MemberAddressVO memberAddressVO = new MemberAddressVO();
        BeanUtils.copyProperties(list.get(0), memberAddressVO);
        memberAddressVO.setPhone(AesCryptoUtils.decrypt(aesKey, list.get(0).getPhoneEncrypted()));
        return memberAddressVO;
    }
}
