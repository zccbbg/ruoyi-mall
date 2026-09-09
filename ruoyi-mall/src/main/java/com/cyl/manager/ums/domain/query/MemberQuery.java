package com.cyl.manager.ums.domain.query;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 会员信息 查询 对象
 *
 * @author zcc
 */
@Data
public class MemberQuery {
    /** 昵称 精确匹配 */
    private String nickname;

    /** 密码 精确匹配 */
    private String password;

    /** 手机号码 精确匹配 */
    private String phone;

    /** 有无备注 1：有备注  0：无备注 */
    private Integer hasMark;

    /** 用户备注 精确匹配 */
    private String mark;

    /** 帐号启用状态:0->禁用；1->启用 精确匹配 */
    private Integer status;

    /** 头像 精确匹配 */
    private String avatar;

    /** 性别：0->未知；1->男；2->女 精确匹配 */
    private Integer gender;

    /** 用户所在城市 精确匹配 */
    private String city;

    /** 用户所在省份 精确匹配 */
    private String province;

    /** 用户所在国家 精确匹配 */
    private String country;

    /** 生日 精确匹配 */
    private LocalDate birthday;

    /** 推广员id 精确匹配 */
    private Long spreadUid;

    /** 推广员关联时间 精确匹配 */
    private LocalDateTime spreadTime;

    /** 等级 精确匹配 */
    private Integer level;

    /** 用户剩余积分 精确匹配 */
    private BigDecimal integral;

    private String beginTime;

    private String endTime;
}
