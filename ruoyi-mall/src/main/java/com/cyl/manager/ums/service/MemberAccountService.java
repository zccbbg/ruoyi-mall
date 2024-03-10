package com.cyl.manager.ums.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.cyl.manager.ums.mapper.MemberAccountMapper;
import com.cyl.manager.ums.domain.entity.MemberAccount;
import com.cyl.manager.ums.domain.query.MemberAccountQuery;

/**
 * 会员账户表Service业务层处理
 *
 *
 * @author zcc
 */
@Service
public class MemberAccountService {
    @Autowired
    private MemberAccountMapper memberAccountMapper;

    /**
     * 查询会员账户表
     *
     * @param memberId 会员账户表主键
     * @return 会员账户表
     */
    public MemberAccount selectByMemberId(Long memberId) {
        return memberAccountMapper.selectById(memberId);
    }

    /**
     * 查询会员账户表列表
     *
     * @param query 查询条件
     * @param page 分页条件
     * @return 会员账户表
     */
    public List<MemberAccount> selectList(MemberAccountQuery query, Pageable page) {
        if (page != null) {
            PageHelper.startPage(page.getPageNumber() + 1, page.getPageSize());
        }
        QueryWrapper<MemberAccount> qw = new QueryWrapper<>();
        BigDecimal integralBalance = query.getIntegralBalance();
        if (integralBalance != null) {
            qw.eq("integral_balance", integralBalance);
        }
        BigDecimal totalIntegralBalance = query.getTotalIntegralBalance();
        if (totalIntegralBalance != null) {
            qw.eq("total_integral_balance", totalIntegralBalance);
        }
        return memberAccountMapper.selectList(qw);
    }

    /**
     * 新增会员账户表
     *
     * @param memberAccount 会员账户表
     * @return 结果
     */
    public int insert(MemberAccount memberAccount) {
        memberAccount.setCreateTime(LocalDateTime.now());
        return memberAccountMapper.insert(memberAccount);
    }

    /**
     * 修改会员账户表
     *
     * @param memberAccount 会员账户表
     * @return 结果
     */
    public int update(MemberAccount memberAccount) {
        return memberAccountMapper.updateById(memberAccount);
    }

    /**
     * 删除会员账户表信息
     *
     * @param memberId 会员账户表主键
     * @return 结果
     */
    public int deleteByMemberId(Long memberId) {
        return memberAccountMapper.deleteById(memberId);
    }
}
