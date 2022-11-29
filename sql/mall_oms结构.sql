-- ----------------------------
-- Table structure for oms_order
-- ----------------------------
DROP TABLE IF EXISTS `oms_order`;
CREATE TABLE `oms_order`  (
    `id` bigint(20) NOT NULL COMMENT '订单id',
    `member_id` bigint(20) NOT NULL,
    `order_sn` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '订单编号',
    `create_time` datetime(0) NULL DEFAULT NULL COMMENT '提交时间',
    `member_username` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户帐号',
    `total_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '订单总金额',
    `purchase_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '采购价',
    `pay_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '应付金额（实际支付金额）',
    `freight_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '运费金额',
    `consumption_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '消费金抵扣金额',
    `pay_type` int(1) NULL DEFAULT NULL COMMENT '支付方式：0->未支付；1->支付宝；2->微信',
    `status` int(1) NULL DEFAULT NULL COMMENT '订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单',
    `refund_status` int(1) NULL DEFAULT NULL COMMENT '退款状态，枚举值：1：无售后或售后关闭，2：售后处理中，3：退款中，4： 退款成功',
    `order_type` int(1) NULL DEFAULT NULL COMMENT '订单类型：1：普通商品，2：活动商品，3：精品抢购商品',
    `delivery_company` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '物流公司(配送方式)',
    `delivery_sn` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '物流单号',
    `auto_confirm_day` int(11) NULL DEFAULT NULL COMMENT '自动确认时间（天）',
    `receiver_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '收货人姓名',
    `receiver_phone` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '收货人电话',
    `receiver_post_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '收货人邮编',
    `receiver_province` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '省份/直辖市',
    `receiver_city` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '城市',
    `receiver_region` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '区',
    `receiver_province_id` bigint(20) NULL DEFAULT NULL COMMENT '省份/直辖市id',
    `receiver_city_id` bigint(20) NULL DEFAULT NULL COMMENT '城市id',
    `receiver_region_id` bigint(20) NULL DEFAULT NULL COMMENT '区id',
    `receiver_detail_address` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '详细地址',
    `note` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '订单备注',
    `confirm_status` int(1) NULL DEFAULT NULL COMMENT '确认收货状态：0->未确认；1->已确认',
    `delete_status` bit(1) NULL DEFAULT NULL COMMENT '删除状态：0->未删除；1->已删除',
    `payment_time` datetime(0) NULL DEFAULT NULL COMMENT '支付时间',
    `delivery_time` datetime(0) NULL DEFAULT NULL COMMENT '发货时间',
    `receive_time` datetime(0) NULL DEFAULT NULL COMMENT '确认收货时间',
    `modify_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '订单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for oms_order_delivery_history
-- ----------------------------
DROP TABLE IF EXISTS `oms_order_delivery_history`;
CREATE TABLE `oms_order_delivery_history`  (
    `id` bigint(20) NOT NULL,
    `order_id` bigint(20) NULL DEFAULT NULL COMMENT '订单id',
    `delivery_company` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '物流公司(配送方式)',
    `delivery_sn` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '物流单号',
    `create_time` datetime(0) NULL DEFAULT NULL COMMENT '操作时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '订单发货记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for oms_order_item
-- ----------------------------
DROP TABLE IF EXISTS `oms_order_item`;
CREATE TABLE `oms_order_item`  (
    `id` bigint(20) NOT NULL,
    `order_id` bigint(20) NULL DEFAULT NULL COMMENT '订单id',
    `order_sn` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '订单编号',
    `product_id` bigint(20) NULL DEFAULT NULL,
    `product_pic` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `product_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `product_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '销售价格',
    `purchase_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '采购价',
    `product_quantity` int(11) NULL DEFAULT NULL COMMENT '购买数量',
    `product_sku_id` bigint(20) NULL DEFAULT NULL COMMENT '商品sku id',
    `product_sku_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品sku code',
    `product_category_id` bigint(20) NULL DEFAULT NULL COMMENT '商品分类id',
    `promotion_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品促销名称',
    `consumption_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '消费金使用金额',
    `product_attr` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品sku属性:[{\"key\":\"颜色\",\"value\":\"颜色\"},{\"key\":\"容量\",\"value\":\"4G\"}]',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '订单中所包含的商品' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for oms_order_operate_history
-- ----------------------------
DROP TABLE IF EXISTS `oms_order_operate_history`;
CREATE TABLE `oms_order_operate_history`  (
    `id` bigint(20) NOT NULL,
    `order_id` bigint(20) NULL DEFAULT NULL COMMENT '订单id',
    `operate_man` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作人：用户；系统；后台管理员',
    `create_time` datetime(0) NULL DEFAULT NULL COMMENT '操作时间',
    `order_status` int(1) NULL DEFAULT NULL COMMENT '订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单',
    `note` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '订单操作历史记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for oms_order_return_apply
-- ----------------------------
DROP TABLE IF EXISTS `oms_order_return_apply`;
CREATE TABLE `oms_order_return_apply`  (
    `id` bigint(20) NOT NULL,
    `order_id` bigint(20) NULL DEFAULT NULL COMMENT '订单id',
    `company_address_id` bigint(20) NULL DEFAULT NULL COMMENT '收货地址表id',
    `product_id` bigint(20) NULL DEFAULT NULL COMMENT '退货商品id',
    `order_sn` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '订单编号',
    `create_time` datetime(0) NULL DEFAULT NULL COMMENT '申请时间',
    `member_username` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '会员用户名',
    `return_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '退款金额',
    `return_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '退货人姓名',
    `return_phone` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '退货人电话',
    `status` int(1) NULL DEFAULT NULL COMMENT '申请状态：0->待处理；1->退货中；2->已完成；3->已拒绝',
    `handle_time` datetime(0) NULL DEFAULT NULL COMMENT '处理时间',
    `product_pic` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品图片',
    `product_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品名称',
    `product_brand` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品品牌',
    `product_attr` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品销售属性：颜色：红色；尺码：xl;',
    `product_count` int(11) NULL DEFAULT NULL COMMENT '退货数量',
    `product_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '商品单价',
    `product_real_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '商品实际支付单价',
    `reason` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '原因',
    `description` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
    `proof_pics` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '凭证图片，以逗号隔开',
    `handle_note` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '处理备注',
    `handle_man` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '处理人员',
    `receive_man` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '收货人',
    `receive_time` datetime(0) NULL DEFAULT NULL COMMENT '收货时间',
    `receive_note` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '收货备注',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '订单退货申请' ROW_FORMAT = Dynamic;
