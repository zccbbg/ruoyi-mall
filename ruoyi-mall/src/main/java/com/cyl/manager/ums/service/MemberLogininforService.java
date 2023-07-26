package com.cyl.manager.ums.service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cyl.manager.ums.domain.Member;
import com.cyl.manager.ums.mapper.MemberMapper;
import com.github.pagehelper.PageHelper;
import com.ruoyi.common.core.domain.model.LoginMember;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.ip.AddressUtils;
import com.ruoyi.common.utils.ip.IpUtils;
import eu.bitwalker.useragentutils.UserAgent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import com.cyl.manager.ums.mapper.MemberLogininforMapper;
import com.cyl.manager.ums.domain.MemberLogininfor;
import com.cyl.manager.ums.pojo.query.MemberLogininforQuery;

/**
 * 会员登录记录Service业务层处理
 *
 *
 * @author zcc
 */
@Service
public class MemberLogininforService {
    @Autowired
    private MemberLogininforMapper memberLogininforMapper;

    @Autowired
    private MemberMapper memberMapper;

    /**
     * 查询会员登录记录
     *
     * @param id 会员登录记录主键
     * @return 会员登录记录
     */
    public MemberLogininfor selectById(Long id) {
        return memberLogininforMapper.selectById(id);
    }

    /**
     * 查询会员登录记录列表
     *
     * @param query 查询条件
     * @param page 分页条件
     * @return 会员登录记录
     */
    public List<MemberLogininfor> selectList(MemberLogininforQuery query, Pageable page) {
        if (page != null) {
            PageHelper.startPage(page.getPageNumber() + 1, page.getPageSize());
        }
        QueryWrapper<MemberLogininfor> qw = new QueryWrapper<>();
        String phone = query.getPhone();
        if (!StringUtils.isEmpty(phone)) {
            qw.eq("phone", phone);
        }
        Long memberId = query.getMemberId();
        if (memberId != null) {
            qw.eq("member_id", memberId);
        }
        String ipaddr = query.getIpaddr();
        if (!StringUtils.isEmpty(ipaddr)) {
            qw.eq("ipaddr", ipaddr);
        }
        String loginLocation = query.getLoginLocation();
        if (!StringUtils.isEmpty(loginLocation)) {
            qw.eq("login_location", loginLocation);
        }
        String browser = query.getBrowser();
        if (!StringUtils.isEmpty(browser)) {
            qw.eq("browser", browser);
        }
        String os = query.getOs();
        if (!StringUtils.isEmpty(os)) {
            qw.eq("os", os);
        }
        LocalDateTime loginTime = query.getLoginTime();
        if (loginTime != null) {
            qw.eq("login_time", loginTime);
        }
        return memberLogininforMapper.selectList(qw);
    }

    /**
     * 修改会员登录记录
     *
     * @param memberLogininfor 会员登录记录
     * @return 结果
     */
    public int update(MemberLogininfor memberLogininfor) {
        return memberLogininforMapper.updateById(memberLogininfor);
    }

    /**
     * 删除会员登录记录信息
     *
     * @param id 会员登录记录主键
     * @return 结果
     */
    public int deleteById(Long id) {
        return memberLogininforMapper.deleteById(id);
    }
}
