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
    `birthday` date DEFAULT NULL COMMENT '生日',
    `spread_uid` bigint unsigned DEFAULT '0' COMMENT '推广员id',
    `spread_time` datetime DEFAULT NULL COMMENT '推广员关联时间',
    `level` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '等级',
    `integral` decimal(8,2) unsigned NOT NULL DEFAULT '0.00' COMMENT '用户剩余积分',
    `create_time` datetime(3) DEFAULT NULL COMMENT '创建时间',
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
    `create_time` datetime(3) DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime(3) DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='会员收货地址';
