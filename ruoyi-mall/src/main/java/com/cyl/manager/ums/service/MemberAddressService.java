package com.cyl.manager.ums.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cyl.manager.ums.convert.MemberAddressConvert;
import com.cyl.manager.ums.domain.entity.MemberAddress;
import com.cyl.manager.ums.mapper.MemberAddressMapper;
import com.cyl.manager.ums.domain.query.MemberAddressQuery;
import com.github.pagehelper.PageHelper;
import com.ruoyi.common.utils.AesCryptoUtils;
import com.ruoyi.common.utils.SecurityUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 会员收货地址Service业务层处理
 *
 * @author zcc
 */
@Service
public class MemberAddressService {
    @Autowired
    private MemberAddressMapper memberAddressMapper;
    @Autowired
    private MemberAddressConvert memberAddressConvert;
    @Value("${aes.key}")
    private String aesKey;

    /**
     * 查询会员收货地址
     *
     * @param id 会员收货地址主键
     * @return 会员收货地址
     */
    public MemberAddress selectById(Long id) {
        return memberAddressMapper.selectById(id);
    }

    /**
     * 查询会员收货地址列表
     *
     * @param query 查询条件
     * @param page  分页条件
     * @return 会员收货地址
     */
    public List<MemberAddress> selectList(MemberAddressQuery query, Pageable page) {
        if (page != null) {
            PageHelper.startPage(page.getPageNumber() + 1, page.getPageSize());
        }
        QueryWrapper<MemberAddress> qw = new QueryWrapper<>();
        String nameLike = query.getNameLike();
        if (!StringUtils.isEmpty(nameLike)) {
            qw.like("name", nameLike);
        }
        String phone = query.getPhone();
        if (!StringUtils.isEmpty(phone)) {
            qw.eq("phone_encrypted", AesCryptoUtils.encrypt(aesKey, phone));
        }
        String postCode = query.getPostCode();
        if (!StringUtils.isEmpty(postCode)) {
            qw.eq("post_code", postCode);
        }
        String province = query.getProvince();
        if (!StringUtils.isEmpty(province)) {
            qw.eq("province", province);
        }
        String city = query.getCity();
        if (!StringUtils.isEmpty(city)) {
            qw.eq("city", city);
        }
        String district = query.getDistrict();
        if (!StringUtils.isEmpty(district)) {
            qw.eq("district", district);
        }
        String detailAddress = query.getDetailAddress();
        if (!StringUtils.isEmpty(detailAddress)) {
            qw.like("detail_address", detailAddress);
        }
        return memberAddressMapper.selectList(qw);
    }

    /**
     * 新增会员收货地址
     *
     * @param memberAddress 会员收货地址
     * @return 结果
     */
    public int insert(MemberAddress memberAddress) {
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
        return memberAddressMapper.updateById(memberAddress);
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

    public Integer deleteUserIds(List<Long> ids) {
        LambdaQueryWrapper<MemberAddress> qw = new LambdaQueryWrapper<>();
        qw.eq(MemberAddress::getMemberId, SecurityUtils.getUserId());
        qw.in(MemberAddress::getId, ids);
        return memberAddressMapper.delete(qw);
    }

    public MemberAddress selectByUserAndId(Long id) {
        LambdaQueryWrapper<MemberAddress> qw = new LambdaQueryWrapper<>();
        qw.eq(MemberAddress::getMemberId, SecurityUtils.getUserId());
        qw.eq(MemberAddress::getId, id);
        return memberAddressMapper.selectOne(qw);
    }

    public int updateSelective(MemberAddress address) {
        return memberAddressMapper.updateByPrimaryKeySelective(address);
    }
}
