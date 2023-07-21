package com.cyl.manager.ums.service;

import java.time.LocalDateTime;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.cyl.manager.ums.pojo.dto.ChangeMemberStatusDTO;
import com.github.pagehelper.PageHelper;
import com.ruoyi.common.utils.AesCryptoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import com.cyl.manager.ums.mapper.MemberMapper;
import com.cyl.manager.ums.domain.Member;
import com.cyl.manager.ums.pojo.query.MemberQuery;

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
    @Value("${aes.key}")
    private String aesKey;

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
        String nicknameLike = query.getNickname();
        if (!StringUtils.isEmpty(nicknameLike)) {
            qw.like("nickname", nicknameLike);
        }
        String phone = query.getPhone();
        if (!StringUtils.isEmpty(phone)) {
            qw.eq("phone_encrypted", AesCryptoUtils.encrypt(aesKey, phone));
        }
        if (!StringUtils.isEmpty(query.getBeginTime()) && !StringUtils.isEmpty(query.getEndTime())){
            qw.ge("create_time", query.getBeginTime());
            qw.lt("create_time", query.getEndTime());
        }
        if (query.getStatus() != null){
            qw.eq("status", query.getStatus());
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
     * 删除会员信息信息
     *
     * @param id 会员信息主键
     * @return 结果
     */
    public int deleteById(Long id) {
        return memberMapper.deleteById(id);
    }

    public Integer changeStatus(ChangeMemberStatusDTO dto) {
        UpdateWrapper<Member> wrapper = new UpdateWrapper<>();
        wrapper.eq("id", dto.getMemberId());
        wrapper.set("status", dto.getStatus());
        return memberMapper.update(null, wrapper);
    }

    public String getPhoneDecrypted(String phoneEncrypted) {
        return AesCryptoUtils.decrypt(aesKey, phoneEncrypted);
    }
}
