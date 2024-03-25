CREATE TABLE `act_coupon_activity`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT,
    `title`         varchar(255)   NOT NULL COMMENT '活动名称',
    `use_scope`     int(11) NOT NULL COMMENT '使用范围  1全场通用 2指定商品可用 3指定商品不可用',
    `product_ids`   varchar(255)   DEFAULT NULL COMMENT '商品id集合，逗号分隔',
    `total_count`   int(11) NOT NULL COMMENT '发行总数',
    `left_count`    int(11) NOT NULL COMMENT '剩余总数',
    `user_limit`    int(11) NOT NULL COMMENT '每人限领',
    `coupon_amount` decimal(10, 2) NOT NULL COMMENT '优惠券金额',
    `min_amount`    decimal(10, 2) DEFAULT NULL COMMENT '最低消费金额',
    `use_integral`  decimal(10, 2) DEFAULT NULL COMMENT '要兑换的积分',
    `coupon_type`   int(11) NOT NULL DEFAULT '1' COMMENT '1免费兑换  2积分兑换',
    `begin_time`    datetime(3) NOT NULL COMMENT '活动开始时间',
    `end_time`      datetime(3) NOT NULL COMMENT '活动结束时间',
    `create_time`   datetime(3) DEFAULT NULL COMMENT '创建时间',
    `update_time`   datetime(3) DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='优惠券活动表';

CREATE TABLE `act_integral_history`
(
    `id`           bigint(20) NOT NULL AUTO_INCREMENT,
    `member_id`    bigint(20) NOT NULL,
    `amount`       decimal(10, 2) NOT NULL COMMENT '变动金额',
    `op_type`      int(11) NOT NULL COMMENT '类型 1：收入 2：支出  3：其他',
    `sub_op_type`  int(11) DEFAULT NULL COMMENT '子类型：11签到  12消费获得  21退款扣除积分',
    `order_amount` decimal(10, 2) DEFAULT NULL COMMENT '订单金额',
    `order_id`     bigint(20) DEFAULT NULL COMMENT '订单id',
    `create_time`  datetime(3) DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=87 DEFAULT CHARSET=utf8mb4 COMMENT='积分流水表';