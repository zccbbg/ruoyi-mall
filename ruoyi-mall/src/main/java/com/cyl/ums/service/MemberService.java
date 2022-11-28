package com.cyl.ums.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import com.cyl.ums.mapper.MemberMapper;
import com.cyl.ums.domain.Member;
import com.cyl.ums.pojo.query.MemberQuery;

/**
 * 会员信息Service业务层处理
 *
 *
 * @author zcc
 */
@Service
public class MemberService {
    @Autowired
    private MemberMapper memberMapper;

    /**
     * 查询会员信息
     *
     * @param id 会员信息主键
     * @return 会员信息
     */
    public Member selectById(Long id) {
        return memberMapper.selectById(id);
    }

    /**
     * 查询会员信息列表
     *
     * @param query 查询条件
     * @param page 分页条件
     * @return 会员信息
     */
    public List<Member> selectList(MemberQuery query, Pageable page) {
        if (page != null) {
            PageHelper.startPage(page.getPageNumber() + 1, page.getPageSize());
        }
        QueryWrapper<Member> qw = new QueryWrapper<>();
        qw.eq("del_flag",0);
        String nicknameLike = query.getNicknameLike();
        if (!StringUtils.isEmpty(nicknameLike)) {
            qw.like("nickname", nicknameLike);
        }
        String password = query.getPassword();
        if (!StringUtils.isEmpty(password)) {
            qw.eq("password", password);
        }
        String phone = query.getPhone();
        if (!StringUtils.isEmpty(phone)) {
            qw.eq("phone", phone);
        }
        String mark = query.getMark();
        if (!StringUtils.isEmpty(mark)) {
            qw.eq("mark", mark);
        }
        Integer status = query.getStatus();
        if (status != null) {
            qw.eq("status", status);
        }
        String avatar = query.getAvatar();
        if (!StringUtils.isEmpty(avatar)) {
            qw.eq("avatar", avatar);
        }
        Integer gender = query.getGender();
        if (gender != null) {
            qw.eq("gender", gender);
        }
        String city = query.getCity();
        if (!StringUtils.isEmpty(city)) {
            qw.eq("city", city);
        }
        String province = query.getProvince();
        if (!StringUtils.isEmpty(province)) {
            qw.eq("province", province);
        }
        String country = query.getCountry();
        if (!StringUtils.isEmpty(country)) {
            qw.eq("country", country);
        }
        LocalDate birthday = query.getBirthday();
        if (birthday != null) {
            qw.eq("birthday", birthday);
        }
        Long spreadUid = query.getSpreadUid();
        if (spreadUid != null) {
            qw.eq("spread_uid", spreadUid);
        }
        LocalDateTime spreadTime = query.getSpreadTime();
        if (spreadTime != null) {
            qw.eq("spread_time", spreadTime);
        }
        Integer level = query.getLevel();
        if (level != null) {
            qw.eq("level", level);
        }
        BigDecimal integral = query.getIntegral();
        if (integral != null) {
            qw.eq("integral", integral);
        }
        return memberMapper.selectList(qw);
    }

    /**
     * 新增会员信息
     *
     * @param member 会员信息
     * @return 结果
     */
    public int insert(Member member) {
        member.setDelFlag(0);
        member.setCreateTime(LocalDateTime.now());
        return memberMapper.insert(member);
    }

    /**
     * 修改会员信息
     *
     * @param member 会员信息
     * @return 结果
     */
    public int update(Member member) {
        return memberMapper.updateById(member);
    }

    /**
     * 批量删除会员信息
     *
     * @param ids 需要删除的会员信息主键
     * @return 结果
     */
    public int deleteByIds(Long[] ids) {
        return memberMapper.updateDelFlagByIds(ids);
    }

    /**
     * 删除会员信息信息
     *
     * @param id 会员信息主键
     * @return 结果
     */
    public int deleteById(Long id) {
        Long[] ids = {id};
        return memberMapper.updateDelFlagByIds(ids);
    }
}
