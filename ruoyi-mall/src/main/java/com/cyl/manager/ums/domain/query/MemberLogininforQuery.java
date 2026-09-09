package com.cyl.manager.ums.domain.query;

import java.time.LocalDateTime;
import lombok.Data;

/**
 * 会员登录记录 查询 对象
 *
 * @author zcc
 */
@Data
public class MemberLogininforQuery {
    /** 会员手机号 精确匹配 */
    private String phone;

    /** 会员id 精确匹配 */
    private Long memberId;

    /** 登录IP地址 精确匹配 */
    private String ipaddr;

    /** 登录地点 精确匹配 */
    private String loginLocation;

    /** 浏览器类型 精确匹配 */
    private String browser;

    /** 操作系统 精确匹配 */
    private String os;

    /** 登陆时间 精确匹配 */
    private LocalDateTime loginTime;

    private String beginTime;

    private String endTime;

}
