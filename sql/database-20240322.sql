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

ALTER TABLE `act_integral_history`
    MODIFY COLUMN `sub_op_type` int (11) NULL DEFAULT NULL COMMENT '子类型：11签到  12消费获得  21退款扣除积分 22 兑换优惠券' AFTER `op_type`;

CREATE TABLE `act_member_coupon`
(
    `id`                 bigint(20) NOT NULL AUTO_INCREMENT,
    `coupon_activity_id` bigint(20) NOT NULL COMMENT '活动id',
    `member_id`          bigint(20) DEFAULT NULL COMMENT '用户id',
    `title`              varchar(255)   NOT NULL COMMENT '活动名称',
    `use_scope`          int(11) NOT NULL COMMENT '使用范围  1全场通用 2指定商品可用 3指定商品不可用',
    `product_ids`        varchar(255)   DEFAULT NULL COMMENT '商品id集合，逗号分隔',
    `coupon_amount`      decimal(10, 2) NOT NULL COMMENT '优惠券金额',
    `min_amount`         decimal(10, 2) DEFAULT NULL COMMENT '最低消费金额',
    `use_integral`       decimal(10, 2) DEFAULT NULL COMMENT '要兑换的积分',
    `coupon_type`        int(11) NOT NULL DEFAULT '1' COMMENT '1免费兑换  2积分兑换',
    `begin_time`         datetime(3) NOT NULL COMMENT '券开始时间',
    `end_time`           datetime(3) NOT NULL COMMENT '券结束时间',
    `use_status`         int(11) NOT NULL DEFAULT '0' COMMENT '0未使用  1已使用',
    `order_id`           bigint(20) DEFAULT NULL COMMENT '订单id',
    `use_time`           datetime(3) DEFAULT NULL COMMENT '使用时间',
    `create_time`        datetime(3) DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户领券记录';