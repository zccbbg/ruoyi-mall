package com.cyl.h5.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cyl.ums.domain.Member;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.framework.config.LocalDataUtil;
import org.springframework.stereotype.Service;

@Service
public class SecurityUtil {

  public static Member getLocalMember() {
    Member member = (Member) LocalDataUtil.getVar(Constants.MEMBER_INFO);
    return member;
  }

}
