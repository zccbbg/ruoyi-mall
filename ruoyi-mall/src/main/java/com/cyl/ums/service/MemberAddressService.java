package com.cyl.ums.service;

import java.util.Arrays;
import java.util.List;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import com.cyl.ums.mapper.MemberAddressMapper;
import com.cyl.ums.domain.MemberAddress;
import com.cyl.ums.pojo.query.MemberAddressQuery;

/**
 * 会员收货地址Service业务层处理
 *
 *
 * @author zcc
 */
@Service
public class MemberAddressService {
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

    /**
     * 查询会员收货地址列表
     *
     * @param query 查询条件
     * @param page 分页条件
     * @return 会员收货地址
     */
    public List<MemberAddress> selectList(MemberAddressQuery query, Pageable page) {
        if (page != null) {
            PageHelper.startPage(page.getPageNumber() + 1, page.getPageSize());
        }
        QueryWrapper<MemberAddress> qw = new QueryWrapper<>();
        Long memberId = query.getMemberId();
        if (memberId != null) {
            qw.eq("member_id", memberId);
        }
        String nameLike = query.getNameLike();
        if (!StringUtils.isEmpty(nameLike)) {
            qw.like("name", nameLike);
        }
        String phone = query.getPhone();
        if (!StringUtils.isEmpty(phone)) {
            qw.eq("phone", phone);
        }
        Integer defaultStatus = query.getDefaultStatus();
        if (defaultStatus != null) {
            qw.eq("default_status", defaultStatus);
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
            qw.eq("detail_address", detailAddress);
        }
        Integer isDefault = query.getIsDefault();
        if (isDefault != null) {
            qw.eq("is_default", isDefault);
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
}
