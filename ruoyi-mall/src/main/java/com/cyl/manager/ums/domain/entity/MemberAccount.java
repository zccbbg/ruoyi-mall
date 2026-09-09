package com.cyl.manager.ums.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.ruoyi.common.annotation.Excel;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 会员账户表对象 ums_member_account
 * 
 * @author zcc
 */
@Data
@TableName("ums_member_account")
public class MemberAccount {
    private static final long serialVersionUID = 1L;

    /** MEMBER_ID */
    @TableId(value="member_id", type = IdType.ASSIGN_ID)
    private Long memberId;

    /** 积分余额 */
    @Excel(name = "积分余额")
    private BigDecimal integralBalance;

    /** 历史总共积分 */
    @Excel(name = "历史总共积分")
    private BigDecimal totalIntegralBalance;

    /** 修改时间 */
    private LocalDateTime updateTime;

    /** 创建时间 */
    private LocalDateTime createTime;

}
