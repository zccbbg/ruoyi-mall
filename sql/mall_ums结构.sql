-- ----------------------------
-- Table structure for ums_member
-- ----------------------------
DROP TABLE IF EXISTS `ums_member`;
CREATE TABLE `ums_member` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `nickname` varchar(64) DEFAULT NULL COMMENT '昵称',
    `password` varchar(64) DEFAULT NULL COMMENT '密码',
    `phone` varchar(64) DEFAULT NULL COMMENT '手机号码',
    `mark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '用户备注',
    `status` int(1) DEFAULT NULL COMMENT '帐号启用状态:0->禁用；1->启用',
    `avatar` varchar(256) DEFAULT NULL COMMENT '头像',
    `gender` int(1) DEFAULT NULL COMMENT '性别：0->未知；1->男；2->女',
    `city` varchar(64) NULL COMMENT '用户所在城市',
    `province` varchar(64) NULL COMMENT '用户所在省份',
    `country` varchar(64) NULL COMMENT '用户所在国家',
    `remark` varchar(256) NULL COMMENT '公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注',
    `birthday` date DEFAULT NULL COMMENT '生日',
    `spread_uid` bigint unsigned DEFAULT '0' COMMENT '推广员id',
    `spread_time` datetime DEFAULT NULL COMMENT '推广员关联时间',
    `level` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '等级',
    `integral` decimal(8,2) unsigned NOT NULL DEFAULT '0.00' COMMENT '用户剩余积分',
    `create_by` bigint(20) DEFAULT NULL COMMENT '创建人',
    `create_time` datetime(3) DEFAULT NULL COMMENT '创建时间',
    `update_by` bigint(20) DEFAULT NULL COMMENT '修改人',
    `update_time` datetime(3) DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `idx_username` (`nickname`),
    UNIQUE KEY `idx_phone` (`phone`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='会员信息';

DROP TABLE IF EXISTS `ums_member_address`;
CREATE TABLE `ums_member_address` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `member_id` bigint(20) DEFAULT NULL,
    `name` varchar(100) DEFAULT NULL COMMENT '收货人名称',
    `phone` varchar(64) DEFAULT NULL,
    `default_status` int(1) DEFAULT NULL COMMENT '是否为默认',
    `post_code` varchar(100) DEFAULT NULL COMMENT '邮政编码',
    `province` varchar(100) DEFAULT NULL COMMENT '省份/直辖市',
    `city` varchar(100) DEFAULT NULL COMMENT '城市',
    `district` varchar(100) DEFAULT NULL COMMENT '区',
    `detail_address` varchar(128) DEFAULT NULL COMMENT '详细地址(街道)',
    `is_default` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '是否默认',
    `create_by` bigint(20) DEFAULT NULL COMMENT '创建人',
    `create_time` datetime(3) DEFAULT NULL COMMENT '创建时间',
    `update_by` bigint(20) DEFAULT NULL COMMENT '修改人',
    `update_time` datetime(3) DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='会员收货地址';

DROP TABLE IF EXISTS `ums_member_wechat`;
CREATE TABLE `ums_member_wechat` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `member_id` bigint(20) DEFAULT NULL,
    `unionid` varchar(30) NULL COMMENT '只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段',
    `openid` varchar(30) NULL COMMENT '用户的标识，对当前公众号唯一',
    `routine_openid` varchar(32) NULL COMMENT '小程序唯一身份ID',
    `groupid` smallint(5) NULL DEFAULT '0' COMMENT '用户所在的分组ID（兼容旧的用户分组接口）',
    `tagid_list` varchar(256) NULL COMMENT '用户被打上的标签ID列表',
    `subscribe` tinyint(3) NULL DEFAULT '1' COMMENT '用户是否订阅该公众号标识',
    `subscribe_time` int(10) NULL COMMENT '关注公众号时间',
    `session_key` varchar(32) NULL COMMENT '小程序用户会话密匙',
    access_token  varchar(500)                    null comment 'token',
    expires_in    int                             null comment '过期时间',
    refresh_token varchar(500)                    null comment '刷新token',
    expire_time   datetime(3)                     null comment '过期时间',
    `create_by` bigint(20) DEFAULT NULL COMMENT '创建人',
    `create_time` datetime(3) DEFAULT NULL COMMENT '创建时间',
    `update_by` bigint(20) DEFAULT NULL COMMENT '修改人',
    `update_time` datetime(3) DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='用户微信信息';

DROP TABLE IF EXISTS `ums_member_cart`;
CREATE TABLE `ums_member_cart` (
     `id` bigint NOT NULL AUTO_INCREMENT COMMENT '购物车表ID',
     `member_id` bigint unsigned NOT NULL COMMENT '用户ID',
     `product_id` bigint unsigned NOT NULL COMMENT '商品ID',
     `pic` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '展示图片',
     `sku_id` bigint unsigned COMMENT 'SKU ID',
     `product_name`  varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
     `sp_data` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '商品属性',
     `cart_num` smallint unsigned NOT NULL DEFAULT '0' COMMENT '商品数量',
     `create_by` bigint(20) DEFAULT NULL COMMENT '创建人',
     `create_time` datetime(3) DEFAULT NULL COMMENT '创建时间',
     `update_by` bigint(20) DEFAULT NULL COMMENT '修改人',
     `update_time` datetime(3) DEFAULT NULL COMMENT '修改时间',
     PRIMARY KEY (`id`) USING BTREE,
     KEY `member_id` (`member_id`) USING BTREE,
     KEY `product_id` (`product_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=150 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='购物车';
