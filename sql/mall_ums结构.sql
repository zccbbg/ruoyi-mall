-- ----------------------------
-- Table structure for ums_member
-- ----------------------------
DROP TABLE IF EXISTS `ums_member`;
CREATE TABLE `ums_member` (
                              `id` bigint(20) NOT NULL AUTO_INCREMENT,
                              `member_level_id` bigint(20) DEFAULT NULL,
                              `username` varchar(64) DEFAULT NULL COMMENT '用户名',
                              `password` varchar(64) DEFAULT NULL COMMENT '密码',
                              `nickname` varchar(64) DEFAULT NULL COMMENT '昵称',
                              `phone` varchar(64) DEFAULT NULL COMMENT '手机号码',
                              `status` int(1) DEFAULT NULL COMMENT '帐号启用状态:0->禁用；1->启用',
                              `create_time` datetime DEFAULT NULL COMMENT '注册时间',
                              `icon` varchar(500) DEFAULT NULL COMMENT '头像',
                              `gender` int(1) DEFAULT NULL COMMENT '性别：0->未知；1->男；2->女',
                              `birthday` date DEFAULT NULL COMMENT '生日',
                              `city` varchar(64) DEFAULT NULL COMMENT '所做城市',
                              `job` varchar(100) DEFAULT NULL COMMENT '职业',
                              `personalized_signature` varchar(200) DEFAULT NULL COMMENT '个性签名',
                              `source_type` int(1) DEFAULT NULL COMMENT '用户来源',
                              `integration` int(11) DEFAULT NULL COMMENT '积分',
                              `growth` int(11) DEFAULT NULL COMMENT '成长值',
                              `luckey_count` int(11) DEFAULT NULL COMMENT '剩余抽奖次数',
                              `history_integration` int(11) DEFAULT NULL COMMENT '历史积分数量',
                              PRIMARY KEY (`id`),
                              UNIQUE KEY `idx_username` (`username`),
                              UNIQUE KEY `idx_phone` (`phone`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='会员信息';

DROP TABLE IF EXISTS `ums_member_receive_address`;
CREATE TABLE `ums_member_receive_address` (
                                              `id` bigint(20) NOT NULL AUTO_INCREMENT,
                                              `member_id` bigint(20) DEFAULT NULL,
                                              `name` varchar(100) DEFAULT NULL COMMENT '收货人名称',
                                              `phone_number` varchar(64) DEFAULT NULL,
                                              `default_status` int(1) DEFAULT NULL COMMENT '是否为默认',
                                              `post_code` varchar(100) DEFAULT NULL COMMENT '邮政编码',
                                              `province` varchar(100) DEFAULT NULL COMMENT '省份/直辖市',
                                              `city` varchar(100) DEFAULT NULL COMMENT '城市',
                                              `region` varchar(100) DEFAULT NULL COMMENT '区',
                                              `detail_address` varchar(128) DEFAULT NULL COMMENT '详细地址(街道)',
                                              PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='会员收货地址表';
