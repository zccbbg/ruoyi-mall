package com.ruoyi.common.constant;

import io.jsonwebtoken.Claims;

/**
 * 通用常量信息
 * 
 * @author ruoyi
 */
public class Constants
{
    /**
     * UTF-8 字符集
     */
    public static final String UTF8 = "UTF-8";

    /**
     * GBK 字符集
     */
    public static final String GBK = "GBK";

    /**
     * http请求
     */
    public static final String HTTP = "http://";

    /**
     * https请求
     */
    public static final String HTTPS = "https://";

    /**
     * 通用成功标识
     */
    public static final String SUCCESS = "0";

    /**
     * 通用失败标识
     */
    public static final String FAIL = "1";

    /**
     * 登录成功
     */
    public static final String LOGIN_SUCCESS = "Success";

    /**
     * 注销
     */
    public static final String LOGOUT = "Logout";

    /**
     * 注册
     */
    public static final String REGISTER = "Register";

    /**
     * 登录失败
     */
    public static final String LOGIN_FAIL = "Error";

    /**
     * 验证码 redis key
     */
    public static final String CAPTCHA_CODE_KEY = "captcha_codes:";

    /**
     * 登录用户 redis key
     */
    public static final String LOGIN_TOKEN_KEY = "login_tokens:";
    public static final String LOGIN_MEMBER_TOKEN_KEY = "login_member_tokens:";
    public static final String MEMBER_INFO = "member_info";

    /**
     * 防重提交 redis key
     */
    public static final String REPEAT_SUBMIT_KEY = "repeat_submit:";

    /**
     * 限流 redis key
     */
    public static final String RATE_LIMIT_KEY = "rate_limit:";

    /**
     * 验证码有效期（分钟）
     */
    public static final Integer CAPTCHA_EXPIRATION = 2;

    /**
     * 令牌
     */
    public static final String TOKEN = "token";

    /**
     * 令牌前缀
     */
    public static final String TOKEN_PREFIX = "Bearer ";

    /**
     * 令牌前缀
     */
    public static final String LOGIN_USER_KEY = "login_user_key";
    public static final String LOGIN_MEMBER_KEY = "login_member_key";

    /**
     * 用户ID
     */
    public static final String JWT_USERID = "userid";

    /**
     * 用户名称
     */
    public static final String JWT_USERNAME = Claims.SUBJECT;

    /**
     * 用户头像
     */
    public static final String JWT_AVATAR = "avatar";

    /**
     * 创建时间
     */
    public static final String JWT_CREATED = "created";

    /**
     * 用户权限
     */
    public static final String JWT_AUTHORITIES = "authorities";

    /**
     * 参数管理 cache key
     */
    public static final String SYS_CONFIG_KEY = "sys_config:";

    public static final String INTEGRAL_RULE_KEY = "activity-integral-income-set-key";

    /**
     * 字典管理 cache key
     */
    public static final String SYS_DICT_KEY = "sys_dict:";

    /**
     * 资源映射路径 前缀
     */
    public static final String RESOURCE_PREFIX = "/profile";

    /**
     * RMI 远程方法调用
     */
    public static final String LOOKUP_RMI = "rmi://";

    /**
     * LDAP 远程方法调用
     */
    public static final String LOOKUP_LDAP = "ldap://";

    public static final String SPAN_ID = "spanId";

    /**
     * 会员账号状态
     */
    public static class MEMBER_ACCOUNT_STATUS {
        public static final Integer FORBIDDEN = 0;
        public static final Integer NORMAL = 1;
    }

    /**
     * 登录提示信息
     */
    public static class LOGIN_INFO {
        public static final String WRONG = "账号或密码错误";
        public static final String FORBIDDEN = "您的账号被禁用，请联系管理员";
        public static final String SUCCESS = "登录成功";
        public static final String TO_REGISTER = "请先注册";
    }

    /**
     * 验证码相关提示信息
     */
    public static class VERIFY_CODE_INFO {
        public static final String EXPIRED = "验证码已过期";
        public static final String WRONG = "验证码错误";
    }

    /**
     * 上架状态：0->下架；1->上架
     */
    public static class PublishStatus {
        public static final Integer GROUNDING = 1;
        public static final Integer UNDERCARRIAGE = 0;
    }

    /**
     * 0->未支付；1->支付宝；2->微信
     */
    public static class PayType {
        public static final Integer NO_PAY = 0;
        public static final Integer ALIPAY = 1;
        public static final Integer WECHAT = 2;
    }

    /**
     * 订单来源 购物车：cart
     */
    public static class OrderFrom {
        public static final String CART = "cart";
    }

    /**
     * 订单状态  0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单
     */
    public static class OrderStatus {
        public static final Integer NOTPAID = 0;
        public static final Integer SEND = 1;
        public static final Integer GET = 2;
        public static final Integer CONFIRM = 3;
        public static final Integer CLOSED = 4;
        public static final Integer UNVAILD = 5;
    }

    /**
     * H5订单查询状态
     * -1->全部 0->待付款；1->待发货；2->待收货；3->已完成；4->已关闭；5->无效订单 -2->售后单
     */
    public static class H5OrderStatus{
        public static final Integer ALL = -1;
        public static final Integer UN_PAY = 0;
        public static final Integer NOT_DELIVERED = 1;
        public static final Integer DELIVERED = 2;
        public static final Integer COMPLETED = 3;
        public static final Integer CLOSED = 4;
        public static final Integer INVALID = 5;
        public static final Integer REFUND = -2;
    }

    /**
     * 交易类型（1为支付  2为提现  3为退款）
     */
    public static class PaymentOpType {
        public static final Integer PAY = 1;
        public static final Integer WITHDRAWAL = 2;
        public static final Integer REFUND = 3;
    }

    /**
     * 状态（0：未完成交易   1：完成关键交易）
     */
    public static class PaymentStatus {
        public static final Integer INCOMPLETE = 0;
        public static final Integer COMPLETE = 1;
    }

    public static class OptType {
        public static final Integer AGREE = 1;
        public static final Integer REFUSE = 2;
        public static final Integer GIVING = 3;
    }

    /**
     * 自动识别json对象白名单配置（仅允许解析的包名，范围越小越安全）
     */
    public static final String[] JSON_WHITELIST_STR = { "org.springframework", "com.ruoyi","com.cyl" };

}
