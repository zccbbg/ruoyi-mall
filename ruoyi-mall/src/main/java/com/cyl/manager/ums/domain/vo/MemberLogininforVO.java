package com.cyl.manager.ums.domain.vo;

import java.time.LocalDateTime;
import com.ruoyi.common.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
/**
 * 会员登录记录 数据视图对象
 * 
 * @author zcc
 */
@Data
public class MemberLogininforVO  {
   /** ID */
    private Long id;
   /** 会员手机号 */
    @Excel(name = "会员手机号")
    private String phone;
   /** 会员id */
    @Excel(name = "会员id")
    private Long memberId;
   /** 登录IP地址 */
    @Excel(name = "登录IP地址")
    private String ipaddr;
   /** 登录地点 */
    @Excel(name = "登录地点")
    private String loginLocation;
   /** 浏览器类型 */
    @Excel(name = "浏览器类型")
    private String browser;
   /** 操作系统 */
    @Excel(name = "操作系统")
    private String os;
   /** 登陆时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "登陆时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime loginTime;
}
