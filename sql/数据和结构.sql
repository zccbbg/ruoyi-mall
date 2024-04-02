SET NAMES utf8mb4;
SET
FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for address
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address`
(
    `id`          int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
    `code`        bigint(20) NOT NULL COMMENT '地区邮编',
    `parent_code` bigint(20) NOT NULL COMMENT '父地区邮编',
    `name`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '地区名',
    `level`       tinyint(4) NOT NULL COMMENT '地区层级',
    `created_at`  timestamp(0) NULL DEFAULT NULL,
    `updated_at`  timestamp(0) NULL DEFAULT NULL,
    `deleted_at`  timestamp(0) NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `address_code_parent_code_unique`(`code`, `parent_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 56925 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for aws_system_statistics
-- ----------------------------
DROP TABLE IF EXISTS `aws_system_statistics`;
CREATE TABLE `aws_system_statistics`
(
    `id`                        bigint(20) NOT NULL AUTO_INCREMENT,
    `date`                      datetime(3) NOT NULL COMMENT '统计日期',
    `login_member_count`        int(11) NOT NULL DEFAULT 0 COMMENT '登录用户数',
    `register_member_count`     int(11) NOT NULL DEFAULT 0 COMMENT '注册用户数',
    `add_cart_member_count`     int(11) NOT NULL DEFAULT 0 COMMENT '加购用户数',
    `create_order_member_count` int(11) NOT NULL DEFAULT 0 COMMENT '下单用户数',
    `deal_member_count`         int(11) NOT NULL DEFAULT 0 COMMENT '成交用户数',
    `order_count`               int(11) NOT NULL DEFAULT 0 COMMENT '下单数',
    `deal_count`                int(11) NOT NULL DEFAULT 0 COMMENT '成交数',
    `deal_amount`               decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '成交金额',
    `aftersale_count`           int(11) NOT NULL DEFAULT 0 COMMENT '售后数',
    `aftersale_amount`          decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '售后金额',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统数据统计' ROW_FORMAT = Dynamic;


-- ----------------------------
-- Table structure for gen_table
-- ----------------------------
DROP TABLE IF EXISTS `gen_table`;
CREATE TABLE `gen_table`
(
    `table_id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
    `table_name`        varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '表名称',
    `table_comment`     varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '表描述',
    `sub_table_name`    varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '关联子表的表名',
    `sub_table_fk_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '子表关联的外键名',
    `class_name`        varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '实体类名称',
    `tpl_category`      varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'crud' COMMENT '使用的模板（crud单表操作 tree树表操作）',
    `package_name`      varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '生成包路径',
    `module_name`       varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '生成模块名',
    `business_name`     varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '生成业务名',
    `function_name`     varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '生成功能名',
    `function_author`   varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '生成功能作者',
    `gen_type`          char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '生成代码方式（0zip压缩包 1自定义路径）',
    `gen_path`          varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '/' COMMENT '生成路径（不填默认项目路径）',
    `options`           varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '其它生成选项',
    `create_by`         bigint(20) NULL DEFAULT NULL COMMENT '创建者',
    `create_time`       datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
    `update_by`         bigint(20) NULL DEFAULT NULL COMMENT '更新者',
    `update_time`       datetime(3) NULL DEFAULT NULL COMMENT '更新时间',
    `remark`            varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`table_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 988 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '代码生成业务表' ROW_FORMAT = Dynamic;


-- ----------------------------
-- Table structure for gen_table_column
-- ----------------------------
DROP TABLE IF EXISTS `gen_table_column`;
CREATE TABLE `gen_table_column`
(
    `column_id`      bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
    `table_id`       varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '归属表编号',
    `column_name`    varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '列名称',
    `column_comment` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '列描述',
    `column_type`    varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '列类型',
    `java_type`      varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'JAVA类型',
    `java_field`     varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'JAVA字段名',
    `is_pk`          char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否主键（1是）',
    `is_increment`   char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否自增（1是）',
    `is_required`    char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否必填（1是）',
    `is_insert`      char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否为插入字段（1是）',
    `is_edit`        char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否编辑字段（1是）',
    `is_list`        char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否列表字段（1是）',
    `is_query`       char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否查询字段（1是）',
    `query_type`     varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'EQ' COMMENT '查询方式（等于、不等于、大于、小于、范围）',
    `html_type`      varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）',
    `dict_type`      varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典类型',
    `sort`           int(11) NULL DEFAULT NULL COMMENT '排序',
    `create_by`      bigint(20) NULL DEFAULT NULL COMMENT '创建者',
    `create_time`    datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
    `update_by`      bigint(20) NULL DEFAULT NULL COMMENT '更新者',
    `update_time`    datetime(3) NULL DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`column_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12677 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '代码生成业务表字段' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for oms_aftersale
-- ----------------------------
DROP TABLE IF EXISTS `oms_aftersale`;
CREATE TABLE `oms_aftersale`
(
    `id`            bigint(20) NOT NULL,
    `member_id`     bigint(20) NOT NULL,
    `order_id`      bigint(20) NULL DEFAULT NULL COMMENT '订单id',
    `return_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '退款金额',
    `type`          int(1) NULL DEFAULT NULL COMMENT '售后类型：1：退款，2：退货退款',
    `status`        int(1) NULL DEFAULT NULL COMMENT '申请状态：0->待处理；1->退货中；2->已完成；3->已拒绝',
    `handle_time`   datetime(0) NULL DEFAULT NULL COMMENT '处理时间',
    `quantity`      int(11) NULL DEFAULT NULL COMMENT '退货数量',
    `reason`        varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '原因',
    `description`   varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
    `proof_pics`    varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '凭证图片，以逗号隔开',
    `handle_note`   varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '处理备注',
    `handle_man`    varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '处理人员',
    `create_by`     bigint(20) NULL DEFAULT NULL COMMENT '创建人',
    `create_time`   datetime(3) NULL DEFAULT NULL COMMENT '创建时间',
    `update_by`     bigint(20) NULL DEFAULT NULL COMMENT '修改人',
    `update_time`   datetime(3) NULL DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '订单售后' ROW_FORMAT = Dynamic;


-- ----------------------------
-- Table structure for oms_aftersale_item
-- ----------------------------
DROP TABLE IF EXISTS `oms_aftersale_item`;
CREATE TABLE `oms_aftersale_item`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT,
    `member_id`     bigint(20) NOT NULL,
    `aftersale_id`  bigint(20) NULL DEFAULT NULL COMMENT '售后单id',
    `order_id`      bigint(20) NULL DEFAULT NULL COMMENT '订单id',
    `order_item_id` bigint(20) NULL DEFAULT NULL COMMENT '子订单id',
    `return_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '退款金额',
    `quantity`      int(11) NULL DEFAULT NULL COMMENT '退货数量',
    `create_by`     bigint(20) NULL DEFAULT NULL COMMENT '创建人',
    `create_time`   datetime(3) NULL DEFAULT NULL COMMENT '创建时间',
    `update_by`     bigint(20) NULL DEFAULT NULL COMMENT '修改人',
    `update_time`   datetime(3) NULL DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '订单售后' ROW_FORMAT = Dynamic;



-- ----------------------------
-- Table structure for oms_order
-- ----------------------------
DROP TABLE IF EXISTS `oms_order`;
CREATE TABLE `oms_order`
(
    `id`                      bigint(20) NOT NULL COMMENT '订单id',
    `order_sn`                varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '订单编号',
    `pay_id`                  bigint(20) NULL DEFAULT NULL COMMENT '支付id',
    `member_id`               bigint(20) NOT NULL,
    `member_username`         varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户帐号',
    `total_amount`            decimal(10, 2) NULL DEFAULT NULL COMMENT '订单总金额',
    `purchase_price`          decimal(10, 2) NULL DEFAULT NULL COMMENT '采购价',
    `pay_amount`              decimal(10, 2) NULL DEFAULT NULL COMMENT '应付金额（实际支付金额）',
    `freight_amount`          decimal(10, 2) NULL DEFAULT NULL COMMENT '运费金额',
    `pay_type`                int(1) NULL DEFAULT NULL COMMENT '支付方式：0->未支付；1->支付宝；2->微信',
    `status`                  int(1) NULL DEFAULT NULL COMMENT '订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单',
    `aftersale_status`        int(1) NULL DEFAULT NULL COMMENT '退款状态，枚举值：1：无售后或售后关闭，2：售后处理中，3：退款中，4： 退款成功',
    `delivery_company`        varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '物流公司(配送方式)',
    `delivery_sn`             varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '物流单号',
    `auto_confirm_day`        int(11) NULL DEFAULT NULL COMMENT '自动确认时间（天）',
    `receiver_name`           varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '收货人姓名',
    `receiver_phone`          varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '收货人电话',
    `receiver_post_code`      varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '收货人邮编',
    `receiver_province`       varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '省份/直辖市',
    `receiver_city`           varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '城市',
    `receiver_district`       varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '区',
    `receiver_province_id`    bigint(20) NULL DEFAULT NULL COMMENT '省份/直辖市id',
    `receiver_city_id`        bigint(20) NULL DEFAULT NULL COMMENT '城市id',
    `receiver_district_id`    bigint(20) NULL DEFAULT NULL COMMENT '区id',
    `receiver_detail_address` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '详细地址',
    `note`                    varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '订单备注',
    `merchant_note`           varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商家备注',
    `confirm_status`          int(1) NULL DEFAULT NULL COMMENT '确认收货状态：0->未确认；1->已确认',
    `delete_status`           bit(1) NULL DEFAULT NULL COMMENT '删除状态：0->未删除；1->已删除',
    `payment_time`            datetime(0) NULL DEFAULT NULL COMMENT '支付时间',
    `delivery_time`           datetime(0) NULL DEFAULT NULL COMMENT '发货时间',
    `receive_time`            datetime(0) NULL DEFAULT NULL COMMENT '确认收货时间',
    `create_by`               bigint(20) NULL DEFAULT NULL COMMENT '创建人',
    `create_time`             datetime(3) NULL DEFAULT NULL COMMENT '创建时间',
    `update_by`               bigint(20) NULL DEFAULT NULL COMMENT '修改人',
    `update_time`             datetime(3) NULL DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '订单表' ROW_FORMAT = Dynamic;


-- ----------------------------
-- Table structure for oms_order_delivery_history
-- ----------------------------
DROP TABLE IF EXISTS `oms_order_delivery_history`;
CREATE TABLE `oms_order_delivery_history`
(
    `id`               bigint(20) NOT NULL AUTO_INCREMENT,
    `order_id`         bigint(20) NULL DEFAULT NULL COMMENT '订单id',
    `delivery_company` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '物流公司(配送方式)',
    `delivery_sn`      varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '物流单号',
    `create_by`        bigint(20) NULL DEFAULT NULL COMMENT '创建人',
    `create_time`      datetime(3) NULL DEFAULT NULL COMMENT '创建时间',
    `update_by`        bigint(20) NULL DEFAULT NULL COMMENT '修改人',
    `update_time`      datetime(3) NULL DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '订单发货记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for oms_order_item
-- ----------------------------
DROP TABLE IF EXISTS `oms_order_item`;
CREATE TABLE `oms_order_item`
(
    `id`                  bigint(20) NOT NULL,
    `order_id`            bigint(20) NULL DEFAULT NULL COMMENT '订单id',
    `product_id`          bigint(20) NULL DEFAULT NULL,
    `out_product_id`      varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品编码',
    `sku_id`              bigint(20) NULL DEFAULT NULL COMMENT '商品sku id',
    `out_sku_id`          varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'sku编码',
    `product_snapshot_id` bigint(20) NULL DEFAULT NULL COMMENT '商品快照id',
    `sku_snapshot_id`     bigint(20) NULL DEFAULT NULL COMMENT 'sku快照id',
    `pic`                 varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '展示图片',
    `product_name`        varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `sale_price`          decimal(10, 2) NULL DEFAULT NULL COMMENT '销售价格',
    `purchase_price`      decimal(10, 2) NULL DEFAULT NULL COMMENT '采购价',
    `quantity`            int(11) NULL DEFAULT NULL COMMENT '购买数量',
    `product_category_id` bigint(20) NULL DEFAULT NULL COMMENT '商品分类id',
    `sp_data`             varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品sku属性:[{\"key\":\"颜色\",\"value\":\"颜色\"},{\"key\":\"容量\",\"value\":\"4G\"}]',
    `create_by`           bigint(20) NULL DEFAULT NULL COMMENT '创建人',
    `create_time`         datetime(3) NULL DEFAULT NULL COMMENT '创建时间',
    `update_by`           bigint(20) NULL DEFAULT NULL COMMENT '修改人',
    `update_time`         datetime(3) NULL DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '订单中所包含的商品' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for oms_order_operate_history
-- ----------------------------
DROP TABLE IF EXISTS `oms_order_operate_history`;
CREATE TABLE `oms_order_operate_history`
(
    `id`           bigint(20) NOT NULL AUTO_INCREMENT,
    `order_id`     bigint(20) NULL DEFAULT NULL COMMENT '订单id',
    `order_sn`     varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '订单号',
    `operate_man`  varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作人：用户；系统；后台管理员',
    `order_status` int(1) NULL DEFAULT NULL COMMENT '订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单 11->售后待处理；12->退货中；13->售后已完成；14->售后已拒绝',
    `note`         varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
    `create_by`    bigint(20) NULL DEFAULT NULL COMMENT '创建人',
    `create_time`  datetime(3) NULL DEFAULT NULL COMMENT '创建时间',
    `update_by`    bigint(20) NULL DEFAULT NULL COMMENT '修改人',
    `update_time`  datetime(3) NULL DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 340 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '订单操作历史记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for oms_wechat_payment_history
-- ----------------------------
DROP TABLE IF EXISTS `oms_wechat_payment_history`;
CREATE TABLE `oms_wechat_payment_history`
(
    `id`             bigint(20) NOT NULL AUTO_INCREMENT,
    `payment_id`     varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'payment_id',
    `member_id`      bigint(20) NOT NULL DEFAULT 0 COMMENT '用户 ID',
    `openid`         varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT 'OPENID',
    `real_name`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '真实姓名，提现需要',
    `title`          varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '标题|商品名称',
    `order_id`       bigint(20) NOT NULL COMMENT '订单号 支付时是payId 其他为orderId',
    `money`          decimal(10, 2)                                                NOT NULL COMMENT '金额，单位分',
    `op_type`        tinyint(1) NOT NULL DEFAULT 1 COMMENT '交易类型（1为支付  2为提现  3为退款）',
    `payment_status` tinyint(1) NOT NULL DEFAULT 0 COMMENT '状态（0：未完成交易   1：完成关键交易）',
    `remark`         varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
    `attach`         varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '附加数据',
    `response_body`  text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '响应内容',
    `create_by`      bigint(20) NULL DEFAULT NULL COMMENT '创建人',
    `create_time`    datetime(3) NULL DEFAULT NULL COMMENT '创建时间',
    `update_by`      bigint(20) NULL DEFAULT NULL COMMENT '修改人',
    `update_time`    datetime(3) NULL DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX            `payment_id_index`(`payment_id`) USING BTREE,
    INDEX            `orderid_index`(`order_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 242 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '微信订单表' ROW_FORMAT = Dynamic;


-- ----------------------------
-- Table structure for pms_brand
-- ----------------------------
DROP TABLE IF EXISTS `pms_brand`;
CREATE TABLE `pms_brand`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT,
    `name`        varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `sort`        int(11) NULL DEFAULT NULL,
    `show_status` int(1) NULL DEFAULT NULL,
    `logo`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '品牌logo',
    `create_by`   bigint(20) NULL DEFAULT NULL COMMENT '创建人',
    `create_time` datetime(3) NULL DEFAULT NULL COMMENT '创建时间',
    `update_by`   bigint(20) NULL DEFAULT NULL COMMENT '修改人',
    `update_time` datetime(3) NULL DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 63 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '品牌管理' ROW_FORMAT = Dynamic;


-- ----------------------------
-- Table structure for pms_product
-- ----------------------------
DROP TABLE IF EXISTS `pms_product`;
CREATE TABLE `pms_product`
(
    `id`                    bigint(20) NOT NULL AUTO_INCREMENT,
    `product_snapshot_id`   bigint(20) NULL DEFAULT NULL,
    `brand_id`              bigint(20) NULL DEFAULT NULL,
    `category_id`           bigint(20) NULL DEFAULT NULL,
    `out_product_id`        varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品编码',
    `name`                  varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `pic`                   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '主图',
    `album_pics`            varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '画册图片，连产品图片限制为5张，以逗号分割',
    `publish_status`        int(1) NULL DEFAULT NULL COMMENT '上架状态：0->下架；1->上架',
    `sort`                  int(11) NULL DEFAULT NULL COMMENT '排序',
    `price`                 decimal(10, 2) NULL DEFAULT NULL,
    `unit`                  varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '单位',
    `weight`                decimal(10, 2) NULL DEFAULT NULL COMMENT '商品重量，默认为克',
    `detail_html`           text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '产品详情网页内容',
    `detail_mobile_html`    text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '移动端网页详情',
    `brand_name`            varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '品牌名称',
    `product_category_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品分类名称',
    `create_by`             bigint(20) NULL DEFAULT NULL COMMENT '创建人',
    `create_time`           datetime(3) NULL DEFAULT NULL COMMENT '创建时间',
    `update_by`             bigint(20) NULL DEFAULT NULL COMMENT '修改人',
    `update_time`           datetime(3) NULL DEFAULT NULL COMMENT '修改时间',
    `product_attr`          varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品销售属性，json格式',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 62 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '商品信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for pms_product_category
-- ----------------------------
DROP TABLE IF EXISTS `pms_product_category`;
CREATE TABLE `pms_product_category`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT,
    `parent_id`   bigint(20) NULL DEFAULT NULL COMMENT '上机分类的编号：0表示一级分类',
    `name`        varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `level`       int(1) NULL DEFAULT NULL COMMENT '分类级别：0->1级；1->2级',
    `show_status` int(1) NULL DEFAULT NULL COMMENT '显示状态：0->不显示；1->显示',
    `sort`        int(11) NULL DEFAULT NULL,
    `icon`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图标',
    `create_by`   bigint(20) NULL DEFAULT NULL COMMENT '创建人',
    `create_time` datetime(3) NULL DEFAULT NULL COMMENT '创建时间',
    `update_by`   bigint(20) NULL DEFAULT NULL COMMENT '修改人',
    `update_time` datetime(3) NULL DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 76 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '商品分类' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for pms_product_snapshot
-- ----------------------------
DROP TABLE IF EXISTS `pms_product_snapshot`;
CREATE TABLE `pms_product_snapshot`
(
    `id`                    bigint(20) NOT NULL AUTO_INCREMENT,
    `product_id`            bigint(20) NULL DEFAULT NULL,
    `brand_id`              bigint(20) NULL DEFAULT NULL,
    `category_id`           bigint(20) NULL DEFAULT NULL,
    `out_product_id`        varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品编码',
    `name`                  varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `pic`                   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '主图',
    `album_pics`            varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '画册图片，连产品图片限制为5张，以逗号分割',
    `publish_status`        int(1) NULL DEFAULT NULL COMMENT '上架状态：0->下架；1->上架',
    `sort`                  int(11) NULL DEFAULT NULL COMMENT '排序',
    `price`                 decimal(10, 2) NULL DEFAULT NULL,
    `unit`                  varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '单位',
    `weight`                decimal(10, 2) NULL DEFAULT NULL COMMENT '商品重量，默认为克',
    `detail_html`           text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '产品详情网页内容',
    `detail_mobile_html`    text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '移动端网页详情',
    `brand_name`            varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '品牌名称',
    `product_category_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品分类名称',
    `create_by`             bigint(20) NULL DEFAULT NULL COMMENT '创建人',
    `create_time`           datetime(3) NULL DEFAULT NULL COMMENT '创建时间',
    `update_by`             bigint(20) NULL DEFAULT NULL COMMENT '修改人',
    `update_time`           datetime(3) NULL DEFAULT NULL COMMENT '修改时间',
    `product_attr`          varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品销售属性，json格式',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 37 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '商品信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for pms_sku
-- ----------------------------
DROP TABLE IF EXISTS `pms_sku`;
CREATE TABLE `pms_sku`
(
    `id`              bigint(20) NOT NULL AUTO_INCREMENT,
    `sku_snapshot_id` bigint(20) NULL DEFAULT NULL,
    `product_id`      bigint(20) NULL DEFAULT NULL,
    `out_sku_id`      varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'sku编码',
    `price`           decimal(10, 2) NOT NULL,
    `pic`             varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '展示图片',
    `sp_data`         varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品销售属性，json格式',
    `create_by`       bigint(20) NULL DEFAULT NULL COMMENT '创建人',
    `create_time`     datetime(3) NULL DEFAULT NULL COMMENT '创建时间',
    `update_by`       bigint(20) NULL DEFAULT NULL COMMENT '修改人',
    `update_time`     datetime(3) NULL DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 399 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'sku信息' ROW_FORMAT = Dynamic;

ALTER TABLE `pms_sku`
    ADD COLUMN `stock` int(11) DEFAULT 0 COMMENT '库存' AFTER `pic`;
-- ----------------------------
-- Table structure for pms_sku_snapshot
-- ----------------------------
DROP TABLE IF EXISTS `pms_sku_snapshot`;
CREATE TABLE `pms_sku_snapshot`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT,
    `sku_id`      bigint(20) NULL DEFAULT NULL,
    `product_id`  bigint(20) NULL DEFAULT NULL,
    `out_sku_id`  varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'sku编码',
    `price`       decimal(10, 2) NOT NULL,
    `pic`         varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '展示图片',
    `sp_data`     varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品销售属性，json格式',
    `create_by`   bigint(20) NULL DEFAULT NULL COMMENT '创建人',
    `create_time` datetime(3) NULL DEFAULT NULL COMMENT '创建时间',
    `update_by`   bigint(20) NULL DEFAULT NULL COMMENT '修改人',
    `update_time` datetime(3) NULL DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 179 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'sku信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_locks
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_locks`;
CREATE TABLE `qrtz_locks`
(
    `sched_name` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '调度名称',
    `lock_name`  varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '悲观锁名称',
    PRIMARY KEY (`sched_name`, `lock_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '存储的悲观锁信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_locks
-- ----------------------------
INSERT INTO `qrtz_locks`
VALUES ('RuoyiScheduler', 'TRIGGER_ACCESS');

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config`
(
    `config_id`    int(5) NOT NULL AUTO_INCREMENT COMMENT '参数主键',
    `config_name`  varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '参数名称',
    `config_key`   varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '参数键名',
    `config_value` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '参数键值',
    `config_type`  char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'N' COMMENT '系统内置（Y是 N否）',
    `create_by`    bigint(20) NULL DEFAULT NULL COMMENT '创建者',
    `create_time`  datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
    `update_by`    bigint(20) NULL DEFAULT NULL COMMENT '更新者',
    `update_time`  datetime(3) NULL DEFAULT NULL COMMENT '更新时间',
    `remark`       varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`config_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 101 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '参数配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config`
VALUES (1, '主框架页-默认皮肤样式名称', 'sys.index.skinName', 'skin-blue', 'Y', 1, '2022-06-17 17:20:29', NULL, NULL,
        '蓝色 skin-blue、绿色 skin-green、紫色 skin-purple、红色 skin-red、黄色 skin-yellow');
INSERT INTO `sys_config`
VALUES (2, '用户管理-账号初始密码', 'sys.user.initPassword', '123456', 'Y', 1, '2022-06-17 17:20:29', NULL, NULL,
        '初始化密码 123456');
INSERT INTO `sys_config`
VALUES (3, '主框架页-侧边栏主题', 'sys.index.sideTheme', 'theme-dark', 'Y', 1, '2022-06-17 17:20:29', NULL, NULL,
        '深色主题theme-dark，浅色主题theme-light');
INSERT INTO `sys_config`
VALUES (4, '账号自助-验证码开关', 'sys.account.captchaOnOff', 'true', 'Y', 1, '2022-06-17 17:20:29', 1,
        '2023-02-27 18:10:17.000', '是否开启验证码功能（true开启，false关闭）');
INSERT INTO `sys_config`
VALUES (5, '账号自助-是否开启用户注册功能', 'sys.account.registerUser', 'false', 'Y', 1, '2022-06-17 17:20:29', NULL,
        NULL, '是否开启注册用户功能（true开启，false关闭）');
INSERT INTO `sys_config`
VALUES (100, '首页轮播图', 'h5.home.banner',
        '[{\"id\":1,\"linkUrl\":\"/pages/goods/goods?id=15\",\"imgUrl\":\"https://zyq.mibolive.com/8gv8wmioh4r9ksrqyfwz.jpg\"}]',
        'N', NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`
(
    `dept_id`     bigint(20) NOT NULL AUTO_INCREMENT COMMENT '部门id',
    `parent_id`   bigint(20) NULL DEFAULT 0 COMMENT '父部门id',
    `ancestors`   varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '祖级列表',
    `dept_name`   varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '部门名称',
    `order_num`   int(4) NULL DEFAULT 0 COMMENT '显示顺序',
    `leader`      varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '负责人',
    `phone`       varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系电话',
    `email`       varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
    `status`      char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '部门状态（0正常 1停用）',
    `del_flag`    char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
    `create_by`   bigint(20) NULL DEFAULT NULL COMMENT '创建者',
    `create_time` datetime(3) NULL DEFAULT NULL COMMENT '创建时间',
    `update_by`   bigint(20) NULL DEFAULT NULL COMMENT '更新者',
    `update_time` datetime(3) NULL DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`dept_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 200 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '部门表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept`
VALUES (100, 0, '0', '若依科技', 0, '若依', '15888888888', 'ry@qq.com', '0', '0', 1, '2022-06-17 17:20:19.000', NULL,
        NULL);
INSERT INTO `sys_dept`
VALUES (101, 100, '0,100', '深圳总公司', 1, '若依', '15888888888', 'ry@qq.com', '0', '0', 1, '2022-06-17 17:20:19.000',
        NULL, NULL);
INSERT INTO `sys_dept`
VALUES (102, 100, '0,100', '长沙分公司', 2, '若依', '15888888888', 'ry@qq.com', '0', '0', 1, '2022-06-17 17:20:19.000',
        NULL, NULL);
INSERT INTO `sys_dept`
VALUES (103, 101, '0,100,101', '研发部门', 1, '若依', '15888888888', 'ry@qq.com', '0', '0', 1,
        '2022-06-17 17:20:19.000', NULL, NULL);
INSERT INTO `sys_dept`
VALUES (104, 101, '0,100,101', '市场部门', 2, '若依', '15888888888', 'ry@qq.com', '0', '0', 1,
        '2022-06-17 17:20:19.000', NULL, NULL);
INSERT INTO `sys_dept`
VALUES (105, 101, '0,100,101', '测试部门', 3, '若依', '15888888888', 'ry@qq.com', '0', '0', 1,
        '2022-06-17 17:20:19.000', NULL, NULL);
INSERT INTO `sys_dept`
VALUES (106, 101, '0,100,101', '财务部门', 4, '若依', '15888888888', 'ry@qq.com', '0', '0', 1,
        '2022-06-17 17:20:19.000', NULL, NULL);
INSERT INTO `sys_dept`
VALUES (107, 101, '0,100,101', '运维部门', 5, '若依', '15888888888', 'ry@qq.com', '0', '0', 1,
        '2022-06-17 17:20:19.000', NULL, NULL);
INSERT INTO `sys_dept`
VALUES (108, 102, '0,100,102', '市场部门', 1, '若依', '15888888888', 'ry@qq.com', '0', '0', 1,
        '2022-06-17 17:20:19.000', NULL, NULL);
INSERT INTO `sys_dept`
VALUES (109, 102, '0,100,102', '财务部门', 2, '若依', '15888888888', 'ry@qq.com', '0', '0', 1,
        '2022-06-17 17:20:19.000', NULL, NULL);

-- ----------------------------
-- Table structure for sys_dict_data
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_data`;
CREATE TABLE `sys_dict_data`
(
    `dict_code`   bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典编码',
    `dict_sort`   int(4) NULL DEFAULT 0 COMMENT '字典排序',
    `dict_label`  varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典标签',
    `dict_value`  varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典键值',
    `dict_type`   varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典类型',
    `css_class`   varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '样式属性（其他样式扩展）',
    `list_class`  varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '表格回显样式',
    `is_default`  char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'N' COMMENT '是否默认（Y是 N否）',
    `status`      char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
    `create_by`   bigint(20) NULL DEFAULT NULL COMMENT '创建者',
    `create_time` datetime(3) NULL DEFAULT NULL COMMENT '创建时间',
    `update_by`   bigint(20) NULL DEFAULT NULL COMMENT '更新者',
    `update_time` datetime(3) NULL DEFAULT NULL COMMENT '更新时间',
    `remark`      varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`dict_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 184 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '字典数据表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict_data
-- ----------------------------
INSERT INTO `sys_dict_data`
VALUES (1, 1, '男', '0', 'sys_user_sex', '', '', 'Y', '0', 1, '2022-06-17 17:20:28.000', NULL, NULL, '性别男');
INSERT INTO `sys_dict_data`
VALUES (2, 2, '女', '1', 'sys_user_sex', '', '', 'N', '0', 1, '2022-06-17 17:20:28.000', NULL, NULL, '性别女');
INSERT INTO `sys_dict_data`
VALUES (3, 3, '未知', '2', 'sys_user_sex', '', '', 'N', '0', 1, '2022-06-17 17:20:28.000', NULL, NULL, '性别未知');
INSERT INTO `sys_dict_data`
VALUES (4, 1, '显示', '0', 'sys_show_hide', '', 'primary', 'Y', '0', 1, '2022-06-17 17:20:28.000', NULL, NULL,
        '显示菜单');
INSERT INTO `sys_dict_data`
VALUES (5, 2, '隐藏', '1', 'sys_show_hide', '', 'danger', 'N', '0', 1, '2022-06-17 17:20:28.000', NULL, NULL,
        '隐藏菜单');
INSERT INTO `sys_dict_data`
VALUES (6, 1, '正常', '0', 'sys_normal_disable', '', 'primary', 'Y', '0', 1, '2022-06-17 17:20:28.000', NULL, NULL,
        '正常状态');
INSERT INTO `sys_dict_data`
VALUES (7, 2, '停用', '1', 'sys_normal_disable', '', 'danger', 'N', '0', 1, '2022-06-17 17:20:28.000', NULL, NULL,
        '停用状态');
INSERT INTO `sys_dict_data`
VALUES (8, 1, '正常', '0', 'sys_job_status', '', 'primary', 'Y', '0', 1, '2022-06-17 17:20:28.000', NULL, NULL,
        '正常状态');
INSERT INTO `sys_dict_data`
VALUES (9, 2, '暂停', '1', 'sys_job_status', '', 'danger', 'N', '0', 1, '2022-06-17 17:20:28.000', NULL, NULL,
        '停用状态');
INSERT INTO `sys_dict_data`
VALUES (10, 1, '默认', 'DEFAULT', 'sys_job_group', '', '', 'Y', '0', 1, '2022-06-17 17:20:28.000', NULL, NULL,
        '默认分组');
INSERT INTO `sys_dict_data`
VALUES (11, 2, '系统', 'SYSTEM', 'sys_job_group', '', '', 'N', '0', 1, '2022-06-17 17:20:28.000', NULL, NULL,
        '系统分组');
INSERT INTO `sys_dict_data`
VALUES (12, 1, '是', 'Y', 'sys_yes_no', '', 'primary', 'Y', '0', 1, '2022-06-17 17:20:28.000', NULL, NULL,
        '系统默认是');
INSERT INTO `sys_dict_data`
VALUES (13, 2, '否', 'N', 'sys_yes_no', '', 'danger', 'N', '0', 1, '2022-06-17 17:20:28.000', NULL, NULL, '系统默认否');
INSERT INTO `sys_dict_data`
VALUES (14, 1, '通知', '1', 'sys_notice_type', '', 'warning', 'Y', '0', 1, '2022-06-17 17:20:28.000', NULL, NULL,
        '通知');
INSERT INTO `sys_dict_data`
VALUES (15, 2, '公告', '2', 'sys_notice_type', '', 'success', 'N', '0', 1, '2022-06-17 17:20:28.000', NULL, NULL,
        '公告');
INSERT INTO `sys_dict_data`
VALUES (16, 1, '正常', '0', 'sys_notice_status', '', 'primary', 'Y', '0', 1, '2022-06-17 17:20:29.000', NULL, NULL,
        '正常状态');
INSERT INTO `sys_dict_data`
VALUES (17, 2, '关闭', '1', 'sys_notice_status', '', 'danger', 'N', '0', 1, '2022-06-17 17:20:29.000', NULL, NULL,
        '关闭状态');
INSERT INTO `sys_dict_data`
VALUES (18, 1, '新增', '1', 'sys_oper_type', '', 'info', 'N', '0', 1, '2022-06-17 17:20:29.000', NULL, NULL,
        '新增操作');
INSERT INTO `sys_dict_data`
VALUES (19, 2, '修改', '2', 'sys_oper_type', '', 'info', 'N', '0', 1, '2022-06-17 17:20:29.000', NULL, NULL,
        '修改操作');
INSERT INTO `sys_dict_data`
VALUES (20, 3, '删除', '3', 'sys_oper_type', '', 'danger', 'N', '0', 1, '2022-06-17 17:20:29.000', NULL, NULL,
        '删除操作');
INSERT INTO `sys_dict_data`
VALUES (21, 4, '授权', '4', 'sys_oper_type', '', 'primary', 'N', '0', 1, '2022-06-17 17:20:29.000', NULL, NULL,
        '授权操作');
INSERT INTO `sys_dict_data`
VALUES (22, 5, '导出', '5', 'sys_oper_type', '', 'warning', 'N', '0', 1, '2022-06-17 17:20:29.000', NULL, NULL,
        '导出操作');
INSERT INTO `sys_dict_data`
VALUES (23, 6, '导入', '6', 'sys_oper_type', '', 'warning', 'N', '0', 1, '2022-06-17 17:20:29.000', NULL, NULL,
        '导入操作');
INSERT INTO `sys_dict_data`
VALUES (24, 7, '强退', '7', 'sys_oper_type', '', 'danger', 'N', '0', 1, '2022-06-17 17:20:29.000', NULL, NULL,
        '强退操作');
INSERT INTO `sys_dict_data`
VALUES (25, 8, '生成代码', '8', 'sys_oper_type', '', 'warning', 'N', '0', 1, '2022-06-17 17:20:29.000', NULL, NULL,
        '生成操作');
INSERT INTO `sys_dict_data`
VALUES (26, 9, '清空数据', '9', 'sys_oper_type', '', 'danger', 'N', '0', 1, '2022-06-17 17:20:29.000', NULL, NULL,
        '清空操作');
INSERT INTO `sys_dict_data`
VALUES (27, 1, '成功', '0', 'sys_common_status', '', 'primary', 'N', '0', 1, '2022-06-17 17:20:29.000', NULL, NULL,
        '正常状态');
INSERT INTO `sys_dict_data`
VALUES (28, 2, '失败', '1', 'sys_common_status', '', 'danger', 'N', '0', 1, '2022-06-17 17:20:29.000', NULL, NULL,
        '停用状态');
INSERT INTO `sys_dict_data`
VALUES (131, 0, '上架', '1', 'pms_publish_status', NULL, 'primary', 'N', '0', 1, '2022-12-06 19:57:58.000', NULL, NULL,
        NULL);
INSERT INTO `sys_dict_data`
VALUES (132, 0, '下架', '0', 'pms_publish_status', NULL, 'info', 'N', '0', 1, '2022-12-06 19:58:15.000', NULL, NULL,
        NULL);
INSERT INTO `sys_dict_data`
VALUES (133, 1, '尺寸', 'sku_sort_list_1', 'sku_sort_list', NULL, NULL, 'N', '0', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_dict_data`
VALUES (134, 2, '型号', 'sku_sort_list_2', 'sku_sort_list', NULL, NULL, 'N', '0', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_dict_data`
VALUES (135, 3, '款式', 'sku_sort_list_3', 'sku_sort_list', NULL, NULL, 'N', '0', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_dict_data`
VALUES (136, 4, '器型', 'sku_sort_list_4', 'sku_sort_list', NULL, NULL, 'N', '0', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_dict_data`
VALUES (137, 5, '材质', 'sku_sort_list_5', 'sku_sort_list', NULL, NULL, 'N', '0', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_dict_data`
VALUES (138, 6, '口味', 'sku_sort_list_6', 'sku_sort_list', NULL, NULL, 'N', '0', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_dict_data`
VALUES (139, 7, '色号', 'sku_sort_list_7', 'sku_sort_list', NULL, NULL, 'N', '0', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_dict_data`
VALUES (140, 8, '适用人群', 'sku_sort_list_8', 'sku_sort_list', NULL, NULL, 'N', '0', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_dict_data`
VALUES (141, 9, '颜色', 'sku_sort_list_9', 'sku_sort_list', NULL, NULL, 'N', '0', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_dict_data`
VALUES (142, 10, '容量', 'sku_sort_list_10', 'sku_sort_list', NULL, NULL, 'N', '0', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_dict_data`
VALUES (143, 11, '花型', 'sku_sort_list_11', 'sku_sort_list', NULL, NULL, 'N', '0', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_dict_data`
VALUES (144, 12, '尺码', 'sku_sort_list_12', 'sku_sort_list', NULL, NULL, 'N', '0', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_dict_data`
VALUES (145, 13, '地点', 'sku_sort_list_13', 'sku_sort_list', NULL, NULL, 'N', '0', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_dict_data`
VALUES (146, 14, '香型', 'sku_sort_list_14', 'sku_sort_list', NULL, NULL, 'N', '0', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_dict_data`
VALUES (147, 15, '货号', 'sku_sort_list_15', 'sku_sort_list', NULL, NULL, 'N', '0', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_dict_data`
VALUES (148, 16, '组合', 'sku_sort_list_16', 'sku_sort_list', NULL, NULL, 'N', '0', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_dict_data`
VALUES (149, 17, '成份', 'sku_sort_list_17', 'sku_sort_list', NULL, NULL, 'N', '0', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_dict_data`
VALUES (150, 18, '版本', 'sku_sort_list_18', 'sku_sort_list', NULL, NULL, 'N', '0', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_dict_data`
VALUES (151, 19, '度数', 'sku_sort_list_19', 'sku_sort_list', NULL, NULL, 'N', '0', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_dict_data`
VALUES (152, 20, '运营商', 'sku_sort_list_20', 'sku_sort_list', NULL, NULL, 'N', '0', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_dict_data`
VALUES (153, 21, '属性', 'sku_sort_list_21', 'sku_sort_list', NULL, NULL, 'N', '0', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_dict_data`
VALUES (154, 22, '重量', 'sku_sort_list_22', 'sku_sort_list', NULL, NULL, 'N', '0', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_dict_data`
VALUES (155, 23, '地区', 'sku_sort_list_23', 'sku_sort_list', NULL, NULL, 'N', '0', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_dict_data`
VALUES (156, 24, '套餐', 'sku_sort_list_24', 'sku_sort_list', NULL, NULL, 'N', '0', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_dict_data`
VALUES (157, 25, '类别', 'sku_sort_list_25', 'sku_sort_list', NULL, NULL, 'N', '0', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_dict_data`
VALUES (158, 26, '适用年龄', 'sku_sort_list_26', 'sku_sort_list', NULL, NULL, 'N', '0', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_dict_data`
VALUES (159, 27, '功效', 'sku_sort_list_27', 'sku_sort_list', NULL, NULL, 'N', '0', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_dict_data`
VALUES (160, 28, '品类', 'sku_sort_list_28', 'sku_sort_list', NULL, NULL, 'N', '0', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_dict_data`
VALUES (161, 29, '时间', 'sku_sort_list_29', 'sku_sort_list', NULL, NULL, 'N', '0', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_dict_data`
VALUES (162, 1, '展示', '1', 'sys_show_status', NULL, NULL, 'N', '0', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_dict_data`
VALUES (163, 2, '隐藏', '0', 'sys_show_status', NULL, NULL, 'N', '0', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_dict_data`
VALUES (164, 0, '未支付', '0', 'oms_pay_type', NULL, 'default', 'N', '0', 1, '2023-06-28 09:38:38.000', NULL, NULL,
        NULL);
INSERT INTO `sys_dict_data`
VALUES (165, 1, '支付宝', '1', 'oms_pay_type', NULL, 'default', 'N', '0', 1, '2023-06-28 09:38:46.000', NULL, NULL,
        NULL);
INSERT INTO `sys_dict_data`
VALUES (166, 2, '微信', '2', 'oms_pay_type', NULL, 'default', 'N', '0', 1, '2023-06-28 09:38:52.000', NULL, NULL, NULL);
INSERT INTO `sys_dict_data`
VALUES (167, 0, '待付款', '0', 'oms_order_status', NULL, 'info', 'N', '0', 1, '2023-06-28 09:39:29.000', NULL, NULL,
        NULL);
INSERT INTO `sys_dict_data`
VALUES (168, 1, '待发货', '1', 'oms_order_status', NULL, 'info', 'N', '0', 1, '2023-06-28 09:39:49.000', NULL, NULL,
        NULL);
INSERT INTO `sys_dict_data`
VALUES (169, 2, '已发货', '2', 'oms_order_status', NULL, 'primary', 'N', '0', 1, '2023-06-28 09:39:59.000', NULL, NULL,
        NULL);
INSERT INTO `sys_dict_data`
VALUES (170, 3, '已完成', '3', 'oms_order_status', NULL, 'success', 'N', '0', 1, '2023-06-28 09:40:08.000', NULL, NULL,
        NULL);
INSERT INTO `sys_dict_data`
VALUES (171, 4, '已关闭', '4', 'oms_order_status', NULL, 'warning', 'N', '0', 1, '2023-06-28 09:40:18.000', NULL, NULL,
        NULL);
INSERT INTO `sys_dict_data`
VALUES (173, 0, '待处理', '0', 'oms_aftersale_status', NULL, 'info', 'N', '0', 1, '2023-07-17 10:19:57.000', NULL, NULL,
        NULL);
INSERT INTO `sys_dict_data`
VALUES (174, 1, '退货中', '1', 'oms_aftersale_status', NULL, 'primary', 'N', '0', 1, '2023-07-17 10:20:05.000', NULL,
        NULL, NULL);
INSERT INTO `sys_dict_data`
VALUES (175, 2, '已完成', '2', 'oms_aftersale_status', NULL, 'success', 'N', '0', 1, '2023-07-17 10:20:14.000', NULL,
        NULL, NULL);
INSERT INTO `sys_dict_data`
VALUES (176, 3, '已拒绝', '3', 'oms_aftersale_status', NULL, 'danger', 'N', '0', 1, '2023-07-17 10:20:24.000', NULL,
        NULL, NULL);
INSERT INTO `sys_dict_data`
VALUES (177, 4, '已关闭', '4', 'oms_aftersale_status', NULL, 'warning', 'N', '0', 1, '2023-07-17 10:20:32.000', 1,
        '2023-07-17 15:20:19.000', NULL);
INSERT INTO `sys_dict_data`
VALUES (178, 0, '退款', '1', 'oms_aftersale_type', NULL, 'primary', 'N', '0', 1, '2023-07-17 10:23:08.000', NULL, NULL,
        NULL);
INSERT INTO `sys_dict_data`
VALUES (179, 1, '退货退款', '2', 'oms_aftersale_type', NULL, 'warning', 'N', '0', 1, '2023-07-17 10:23:16.000', NULL,
        NULL, NULL);
INSERT INTO `sys_dict_data`
VALUES (180, 5, '售后待处理', '11', 'oms_order_status', NULL, 'info', 'N', '0', 1, '2023-07-17 17:21:50.000', NULL,
        NULL, NULL);
INSERT INTO `sys_dict_data`
VALUES (181, 6, '退货中', '12', 'oms_order_status', NULL, 'primary', 'N', '0', 1, '2023-07-17 17:22:05.000', NULL, NULL,
        NULL);
INSERT INTO `sys_dict_data`
VALUES (182, 7, '售后已完成', '13', 'oms_order_status', NULL, 'success', 'N', '0', 1, '2023-07-17 17:22:19.000', NULL,
        NULL, NULL);
INSERT INTO `sys_dict_data`
VALUES (183, 8, '售后已拒绝', '14', 'oms_order_status', NULL, 'danger', 'N', '0', 1, '2023-07-17 17:22:44.000', NULL,
        NULL, NULL);

-- ----------------------------
-- Table structure for sys_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_type`;
CREATE TABLE `sys_dict_type`
(
    `dict_id`     bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典主键',
    `dict_name`   varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典名称',
    `dict_type`   varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典类型',
    `status`      char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
    `create_by`   bigint(20) NULL DEFAULT NULL COMMENT '创建者',
    `create_time` datetime(3) NULL DEFAULT NULL COMMENT '创建时间',
    `update_by`   bigint(20) NULL DEFAULT NULL COMMENT '更新者',
    `update_time` datetime(3) NULL DEFAULT NULL COMMENT '更新时间',
    `remark`      varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`dict_id`) USING BTREE,
    UNIQUE INDEX `dict_type`(`dict_type`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 116 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '字典类型表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict_type
-- ----------------------------
INSERT INTO `sys_dict_type`
VALUES (1, '用户性别', 'sys_user_sex', '0', 1, '2022-06-17 17:20:27.000', NULL, NULL, '用户性别列表');
INSERT INTO `sys_dict_type`
VALUES (2, '菜单状态', 'sys_show_hide', '0', 1, '2022-06-17 17:20:27.000', NULL, NULL, '菜单状态列表');
INSERT INTO `sys_dict_type`
VALUES (3, '系统开关', 'sys_normal_disable', '0', 1, '2022-06-17 17:20:27.000', NULL, NULL, '系统开关列表');
INSERT INTO `sys_dict_type`
VALUES (4, '任务状态', 'sys_job_status', '0', 1, '2022-06-17 17:20:28.000', NULL, NULL, '任务状态列表');
INSERT INTO `sys_dict_type`
VALUES (5, '任务分组', 'sys_job_group', '0', 1, '2022-06-17 17:20:28.000', NULL, NULL, '任务分组列表');
INSERT INTO `sys_dict_type`
VALUES (6, '系统是否', 'sys_yes_no', '0', 1, '2022-06-17 17:20:28.000', NULL, NULL, '系统是否列表');
INSERT INTO `sys_dict_type`
VALUES (7, '通知类型', 'sys_notice_type', '0', 1, '2022-06-17 17:20:28.000', NULL, NULL, '通知类型列表');
INSERT INTO `sys_dict_type`
VALUES (8, '通知状态', 'sys_notice_status', '0', 1, '2022-06-17 17:20:28.000', NULL, NULL, '通知状态列表');
INSERT INTO `sys_dict_type`
VALUES (9, '操作类型', 'sys_oper_type', '0', 1, '2022-06-17 17:20:28.000', NULL, NULL, '操作类型列表');
INSERT INTO `sys_dict_type`
VALUES (10, '系统状态', 'sys_common_status', '0', 1, '2022-06-17 17:20:28.000', NULL, NULL, '登录状态列表');
INSERT INTO `sys_dict_type`
VALUES (109, '上架状态', 'pms_publish_status', '0', 1, '2022-12-06 19:57:39.000', NULL, NULL, NULL);
INSERT INTO `sys_dict_type`
VALUES (110, '规格类型', 'sku_sort_list', '0', 1, '2022-12-13 21:02:38.000', NULL, NULL, NULL);
INSERT INTO `sys_dict_type`
VALUES (111, '系统展示状态', 'sys_show_status', '0', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_dict_type`
VALUES (112, '支付方式', 'oms_pay_type', '0', 1, '2023-06-28 09:38:18.000', NULL, NULL, NULL);
INSERT INTO `sys_dict_type`
VALUES (113, '订单状态', 'oms_order_status', '0', 1, '2023-06-28 09:39:14.000', NULL, NULL, NULL);
INSERT INTO `sys_dict_type`
VALUES (114, '售后申请状态', 'oms_aftersale_status', '0', 1, '2023-07-17 10:19:36.000', 1, '2023-07-17 10:22:40.000',
        NULL);
INSERT INTO `sys_dict_type`
VALUES (115, '售后类型', 'oms_aftersale_type', '0', 1, '2023-07-17 10:22:51.000', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_job
-- ----------------------------
DROP TABLE IF EXISTS `sys_job`;
CREATE TABLE `sys_job`
(
    `job_id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务ID',
    `job_name`        varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL DEFAULT '' COMMENT '任务名称',
    `job_group`       varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL DEFAULT 'DEFAULT' COMMENT '任务组名',
    `invoke_target`   varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '调用目标字符串',
    `cron_expression` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT 'cron执行表达式',
    `misfire_policy`  varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '3' COMMENT '计划执行错误策略（1立即执行 2执行一次 3放弃执行）',
    `concurrent`      char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '1' COMMENT '是否并发执行（0允许 1禁止）',
    `status`          char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '状态（0正常 1暂停）',
    `create_by`       bigint(20) NULL DEFAULT NULL COMMENT '创建者',
    `create_time`     datetime(3) NULL DEFAULT NULL COMMENT '创建时间',
    `update_by`       bigint(20) NULL DEFAULT NULL COMMENT '更新者',
    `update_time`     datetime(3) NULL DEFAULT NULL COMMENT '更新时间',
    `remark`          varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '备注信息',
    PRIMARY KEY (`job_id`, `job_name`, `job_group`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 100 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '定时任务调度表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_job
-- ----------------------------
INSERT INTO `sys_job`
VALUES (1, '系统默认（无参）', 'DEFAULT', 'ryTask.ryNoParams', '0/10 * * * * ?', '3', '1', '1', 1,
        '2022-06-17 17:20:29.000', NULL, NULL, '');
INSERT INTO `sys_job`
VALUES (2, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '0/15 * * * * ?', '3', '1', '1', 1,
        '2022-06-17 17:20:29.000', NULL, NULL, '');
INSERT INTO `sys_job`
VALUES (3, '系统默认（多参）', 'DEFAULT', 'ryTask.ryMultipleParams(\'ry\', true, 2000L, 316.50D, 100)', '0/20 * * * * ?',
        '3', '1', '1', 1, '2022-06-17 17:20:29.000', NULL, NULL, '');

-- ----------------------------
-- Table structure for sys_job_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_job_log`;
CREATE TABLE `sys_job_log`
(
    `job_log_id`     bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务日志ID',
    `job_name`       varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '任务名称',
    `job_group`      varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '任务组名',
    `invoke_target`  varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '调用目标字符串',
    `job_message`    varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '日志信息',
    `status`         char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '执行状态（0正常 1失败）',
    `exception_info` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '异常信息',
    `create_time`    datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (`job_log_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '定时任务调度日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_logininfor
-- ----------------------------
DROP TABLE IF EXISTS `sys_logininfor`;
CREATE TABLE `sys_logininfor`
(
    `info_id`        bigint(20) NOT NULL AUTO_INCREMENT COMMENT '访问ID',
    `user_name`      varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '用户账号',
    `ipaddr`         varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '登录IP地址',
    `login_location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '登录地点',
    `browser`        varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '浏览器类型',
    `os`             varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '操作系统',
    `status`         char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '登录状态（0成功 1失败）',
    `msg`            varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '提示消息',
    `login_time`     datetime(0) NULL DEFAULT NULL COMMENT '访问时间',
    PRIMARY KEY (`info_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3120 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统访问记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`
(
    `menu_id`     bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
    `menu_name`   varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单名称',
    `parent_id`   bigint(20) NULL DEFAULT 0 COMMENT '父菜单ID',
    `order_num`   int(4) NULL DEFAULT 0 COMMENT '显示顺序',
    `path`        varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '路由地址',
    `component`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '组件路径',
    `query`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '路由参数',
    `is_frame`    int(1) NULL DEFAULT 1 COMMENT '是否为外链（0是 1否）',
    `is_cache`    int(1) NULL DEFAULT 0 COMMENT '是否缓存（0缓存 1不缓存）',
    `menu_type`   char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
    `visible`     char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
    `status`      char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '菜单状态（0正常 1停用）',
    `perms`       varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限标识',
    `icon`        varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '#' COMMENT '菜单图标',
    `create_by`   bigint(20) NULL DEFAULT NULL COMMENT '创建者',
    `create_time` datetime(3) NULL DEFAULT NULL COMMENT '创建时间',
    `update_by`   bigint(20) NULL DEFAULT NULL COMMENT '更新者',
    `update_time` datetime(3) NULL DEFAULT NULL COMMENT '更新时间',
    `remark`      varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '备注',
    PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2341 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '菜单权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu`
VALUES (1, '系统管理', 0, 300, 'system', NULL, '', 1, 0, 'M', '0', '0', '', 'system', 1, '2022-06-17 17:20:20.000', 1,
        '2022-10-31 09:09:13.000', '系统管理目录');
INSERT INTO `sys_menu`
VALUES (2, '系统监控', 0, 200, 'monitor', NULL, '', 1, 0, 'M', '0', '0', '', 'monitor', 1, '2022-06-17 17:20:20.000', 1,
        '2022-10-31 09:09:02.000', '系统监控目录');
INSERT INTO `sys_menu`
VALUES (3, '系统工具', 0, 100, 'tool', NULL, '', 1, 0, 'M', '0', '0', '', 'tool', 1, '2022-06-17 17:20:20.000', 1,
        '2022-10-31 09:08:48.000', '系统工具目录');
INSERT INTO `sys_menu`
VALUES (100, '用户管理', 1, 1, 'user', 'system/user/index', '', 1, 0, 'C', '0', '0', 'system:user:list', 'user', 1,
        '2022-06-17 17:20:20.000', NULL, NULL, '用户管理菜单');
INSERT INTO `sys_menu`
VALUES (101, '角色管理', 1, 2, 'role', 'system/role/index', '', 1, 0, 'C', '0', '0', 'system:role:list', 'peoples', 1,
        '2022-06-17 17:20:20.000', NULL, NULL, '角色管理菜单');
INSERT INTO `sys_menu`
VALUES (102, '菜单管理', 1, 3, 'menu', 'system/menu/index', '', 1, 0, 'C', '0', '0', 'system:menu:list', 'tree-table',
        1, '2022-06-17 17:20:20.000', NULL, NULL, '菜单管理菜单');
INSERT INTO `sys_menu`
VALUES (103, '部门管理', 1, 4, 'dept', 'system/dept/index', '', 1, 0, 'C', '0', '0', 'system:dept:list', 'tree', 1,
        '2022-06-17 17:20:20.000', NULL, NULL, '部门管理菜单');
INSERT INTO `sys_menu`
VALUES (104, '岗位管理', 1, 5, 'post', 'system/post/index', '', 1, 0, 'C', '0', '0', 'system:post:list', 'post', 1,
        '2022-06-17 17:20:20.000', NULL, NULL, '岗位管理菜单');
INSERT INTO `sys_menu`
VALUES (105, '字典管理', 1, 6, 'dict', 'system/dict/index', '', 1, 0, 'C', '0', '0', 'system:dict:list', 'dict', 1,
        '2022-06-17 17:20:20.000', NULL, NULL, '字典管理菜单');
INSERT INTO `sys_menu`
VALUES (106, '参数设置', 1, 7, 'config', 'system/config/index', '', 1, 0, 'C', '0', '0', 'system:config:list', 'edit',
        1, '2022-06-17 17:20:20.000', NULL, NULL, '参数设置菜单');
INSERT INTO `sys_menu`
VALUES (107, '通知公告', 1, 8, 'notice', 'system/notice/index', '', 1, 0, 'C', '0', '0', 'system:notice:list',
        'message', 1, '2022-06-17 17:20:20.000', NULL, NULL, '通知公告菜单');
INSERT INTO `sys_menu`
VALUES (108, '日志管理', 1, 9, 'log', '', '', 1, 0, 'M', '0', '0', '', 'log', 1, '2022-06-17 17:20:20.000', NULL, NULL,
        '日志管理菜单');
INSERT INTO `sys_menu`
VALUES (109, '在线用户', 2, 1, 'online', 'monitor/online/index', '', 1, 0, 'C', '0', '0', 'monitor:online:list',
        'online', 1, '2022-06-17 17:20:20.000', NULL, NULL, '在线用户菜单');
INSERT INTO `sys_menu`
VALUES (110, '定时任务', 2, 2, 'job', 'monitor/job/index', '', 1, 0, 'C', '0', '0', 'monitor:job:list', 'job', 1,
        '2022-06-17 17:20:20.000', NULL, NULL, '定时任务菜单');
INSERT INTO `sys_menu`
VALUES (111, '数据监控', 2, 3, 'druid', 'monitor/druid/index', '', 1, 0, 'C', '0', '0', 'monitor:druid:list', 'druid',
        1, '2022-06-17 17:20:20.000', NULL, NULL, '数据监控菜单');
INSERT INTO `sys_menu`
VALUES (112, '服务监控', 2, 4, 'server', 'monitor/server/index', '', 1, 0, 'C', '0', '0', 'monitor:server:list',
        'server', 1, '2022-06-17 17:20:20.000', NULL, NULL, '服务监控菜单');
INSERT INTO `sys_menu`
VALUES (113, '缓存监控', 2, 5, 'cache', 'monitor/cache/index', '', 1, 0, 'C', '0', '0', 'monitor:cache:list', 'redis',
        1, '2022-06-17 17:20:20.000', NULL, NULL, '缓存监控菜单');
INSERT INTO `sys_menu`
VALUES (114, '表单构建', 3, 1, 'build', 'tool/build/index', '', 1, 0, 'C', '0', '0', 'tool:build:list', 'build', 1,
        '2022-06-17 17:20:21.000', NULL, NULL, '表单构建菜单');
INSERT INTO `sys_menu`
VALUES (115, '代码生成', 3, 2, 'gen', 'tool/gen/index', '', 1, 0, 'C', '0', '0', 'tool:gen:list', 'code', 1,
        '2022-06-17 17:20:21.000', NULL, NULL, '代码生成菜单');
INSERT INTO `sys_menu`
VALUES (116, '系统接口', 3, 3, 'swagger', 'tool/swagger/index', '', 1, 0, 'C', '0', '0', 'tool:swagger:list', 'swagger',
        1, '2022-06-17 17:20:21.000', NULL, NULL, '系统接口菜单');
INSERT INTO `sys_menu`
VALUES (500, '操作日志', 108, 1, 'operlog', 'monitor/operlog/index', '', 1, 0, 'C', '0', '0', 'monitor:operlog:list',
        'form', 1, '2022-06-17 17:20:21.000', NULL, NULL, '操作日志菜单');
INSERT INTO `sys_menu`
VALUES (501, '登录日志', 0, 3, 'logininfor', 'monitor/logininfor/index', '', 1, 0, 'C', '0', '0',
        'monitor:logininfor:list', 'logininfor', 1, '2022-06-17 17:20:21.000', 1, '2023-07-27 10:02:27.000',
        '登录日志菜单');
INSERT INTO `sys_menu`
VALUES (1001, '用户查询', 100, 1, '', '', '', 1, 0, 'F', '0', '0', 'system:user:query', '#', 1,
        '2022-06-17 17:20:21.000', NULL, NULL, '');
INSERT INTO `sys_menu`
VALUES (1002, '用户新增', 100, 2, '', '', '', 1, 0, 'F', '0', '0', 'system:user:add', '#', 1, '2022-06-17 17:20:21.000',
        NULL, NULL, '');
INSERT INTO `sys_menu`
VALUES (1003, '用户修改', 100, 3, '', '', '', 1, 0, 'F', '0', '0', 'system:user:edit', '#', 1,
        '2022-06-17 17:20:21.000', NULL, NULL, '');
INSERT INTO `sys_menu`
VALUES (1004, '用户删除', 100, 4, '', '', '', 1, 0, 'F', '0', '0', 'system:user:remove', '#', 1,
        '2022-06-17 17:20:21.000', NULL, NULL, '');
INSERT INTO `sys_menu`
VALUES (1005, '用户导出', 100, 5, '', '', '', 1, 0, 'F', '0', '0', 'system:user:export', '#', 1,
        '2022-06-17 17:20:21.000', NULL, NULL, '');
INSERT INTO `sys_menu`
VALUES (1006, '用户导入', 100, 6, '', '', '', 1, 0, 'F', '0', '0', 'system:user:import', '#', 1,
        '2022-06-17 17:20:21.000', NULL, NULL, '');
INSERT INTO `sys_menu`
VALUES (1007, '重置密码', 100, 7, '', '', '', 1, 0, 'F', '0', '0', 'system:user:resetPwd', '#', 1,
        '2022-06-17 17:20:21.000', NULL, NULL, '');
INSERT INTO `sys_menu`
VALUES (1008, '角色查询', 101, 1, '', '', '', 1, 0, 'F', '0', '0', 'system:role:query', '#', 1,
        '2022-06-17 17:20:21.000', NULL, NULL, '');
INSERT INTO `sys_menu`
VALUES (1009, '角色新增', 101, 2, '', '', '', 1, 0, 'F', '0', '0', 'system:role:add', '#', 1, '2022-06-17 17:20:21.000',
        NULL, NULL, '');
INSERT INTO `sys_menu`
VALUES (1010, '角色修改', 101, 3, '', '', '', 1, 0, 'F', '0', '0', 'system:role:edit', '#', 1,
        '2022-06-17 17:20:21.000', NULL, NULL, '');
INSERT INTO `sys_menu`
VALUES (1011, '角色删除', 101, 4, '', '', '', 1, 0, 'F', '0', '0', 'system:role:remove', '#', 1,
        '2022-06-17 17:20:21.000', NULL, NULL, '');
INSERT INTO `sys_menu`
VALUES (1012, '角色导出', 101, 5, '', '', '', 1, 0, 'F', '0', '0', 'system:role:export', '#', 1,
        '2022-06-17 17:20:21.000', NULL, NULL, '');
INSERT INTO `sys_menu`
VALUES (1013, '菜单查询', 102, 1, '', '', '', 1, 0, 'F', '0', '0', 'system:menu:query', '#', 1,
        '2022-06-17 17:20:21.000', NULL, NULL, '');
INSERT INTO `sys_menu`
VALUES (1014, '菜单新增', 102, 2, '', '', '', 1, 0, 'F', '0', '0', 'system:menu:add', '#', 1, '2022-06-17 17:20:21.000',
        NULL, NULL, '');
INSERT INTO `sys_menu`
VALUES (1015, '菜单修改', 102, 3, '', '', '', 1, 0, 'F', '0', '0', 'system:menu:edit', '#', 1,
        '2022-06-17 17:20:21.000', NULL, NULL, '');
INSERT INTO `sys_menu`
VALUES (1016, '菜单删除', 102, 4, '', '', '', 1, 0, 'F', '0', '0', 'system:menu:remove', '#', 1,
        '2022-06-17 17:20:21.000', NULL, NULL, '');
INSERT INTO `sys_menu`
VALUES (1017, '部门查询', 103, 1, '', '', '', 1, 0, 'F', '0', '0', 'system:dept:query', '#', 1,
        '2022-06-17 17:20:21.000', NULL, NULL, '');
INSERT INTO `sys_menu`
VALUES (1018, '部门新增', 103, 2, '', '', '', 1, 0, 'F', '0', '0', 'system:dept:add', '#', 1, '2022-06-17 17:20:22.000',
        NULL, NULL, '');
INSERT INTO `sys_menu`
VALUES (1019, '部门修改', 103, 3, '', '', '', 1, 0, 'F', '0', '0', 'system:dept:edit', '#', 1,
        '2022-06-17 17:20:22.000', NULL, NULL, '');
INSERT INTO `sys_menu`
VALUES (1020, '部门删除', 103, 4, '', '', '', 1, 0, 'F', '0', '0', 'system:dept:remove', '#', 1,
        '2022-06-17 17:20:22.000', NULL, NULL, '');
INSERT INTO `sys_menu`
VALUES (1021, '岗位查询', 104, 1, '', '', '', 1, 0, 'F', '0', '0', 'system:post:query', '#', 1,
        '2022-06-17 17:20:22.000', NULL, NULL, '');
INSERT INTO `sys_menu`
VALUES (1022, '岗位新增', 104, 2, '', '', '', 1, 0, 'F', '0', '0', 'system:post:add', '#', 1, '2022-06-17 17:20:22.000',
        NULL, NULL, '');
INSERT INTO `sys_menu`
VALUES (1023, '岗位修改', 104, 3, '', '', '', 1, 0, 'F', '0', '0', 'system:post:edit', '#', 1,
        '2022-06-17 17:20:22.000', NULL, NULL, '');
INSERT INTO `sys_menu`
VALUES (1024, '岗位删除', 104, 4, '', '', '', 1, 0, 'F', '0', '0', 'system:post:remove', '#', 1,
        '2022-06-17 17:20:22.000', NULL, NULL, '');
INSERT INTO `sys_menu`
VALUES (1025, '岗位导出', 104, 5, '', '', '', 1, 0, 'F', '0', '0', 'system:post:export', '#', 1,
        '2022-06-17 17:20:22.000', NULL, NULL, '');
INSERT INTO `sys_menu`
VALUES (1026, '字典查询', 105, 1, '#', '', '', 1, 0, 'F', '0', '0', 'system:dict:query', '#', 1,
        '2022-06-17 17:20:22.000', NULL, NULL, '');
INSERT INTO `sys_menu`
VALUES (1027, '字典新增', 105, 2, '#', '', '', 1, 0, 'F', '0', '0', 'system:dict:add', '#', 1,
        '2022-06-17 17:20:22.000', NULL, NULL, '');
INSERT INTO `sys_menu`
VALUES (1028, '字典修改', 105, 3, '#', '', '', 1, 0, 'F', '0', '0', 'system:dict:edit', '#', 1,
        '2022-06-17 17:20:22.000', NULL, NULL, '');
INSERT INTO `sys_menu`
VALUES (1029, '字典删除', 105, 4, '#', '', '', 1, 0, 'F', '0', '0', 'system:dict:remove', '#', 1,
        '2022-06-17 17:20:22.000', NULL, NULL, '');
INSERT INTO `sys_menu`
VALUES (1030, '字典导出', 105, 5, '#', '', '', 1, 0, 'F', '0', '0', 'system:dict:export', '#', 1,
        '2022-06-17 17:20:22.000', NULL, NULL, '');
INSERT INTO `sys_menu`
VALUES (1031, '参数查询', 106, 1, '#', '', '', 1, 0, 'F', '0', '0', 'system:config:query', '#', 1,
        '2022-06-17 17:20:22.000', NULL, NULL, '');
INSERT INTO `sys_menu`
VALUES (1032, '参数新增', 106, 2, '#', '', '', 1, 0, 'F', '0', '0', 'system:config:add', '#', 1,
        '2022-06-17 17:20:22.000', NULL, NULL, '');
INSERT INTO `sys_menu`
VALUES (1033, '参数修改', 106, 3, '#', '', '', 1, 0, 'F', '0', '0', 'system:config:edit', '#', 1,
        '2022-06-17 17:20:22.000', NULL, NULL, '');
INSERT INTO `sys_menu`
VALUES (1034, '参数删除', 106, 4, '#', '', '', 1, 0, 'F', '0', '0', 'system:config:remove', '#', 1,
        '2022-06-17 17:20:22.000', NULL, NULL, '');
INSERT INTO `sys_menu`
VALUES (1035, '参数导出', 106, 5, '#', '', '', 1, 0, 'F', '0', '0', 'system:config:export', '#', 1,
        '2022-06-17 17:20:22.000', NULL, NULL, '');
INSERT INTO `sys_menu`
VALUES (1036, '公告查询', 107, 1, '#', '', '', 1, 0, 'F', '0', '0', 'system:notice:query', '#', 1,
        '2022-06-17 17:20:22.000', NULL, NULL, '');
INSERT INTO `sys_menu`
VALUES (1037, '公告新增', 107, 2, '#', '', '', 1, 0, 'F', '0', '0', 'system:notice:add', '#', 1,
        '2022-06-17 17:20:22.000', NULL, NULL, '');
INSERT INTO `sys_menu`
VALUES (1038, '公告修改', 107, 3, '#', '', '', 1, 0, 'F', '0', '0', 'system:notice:edit', '#', 1,
        '2022-06-17 17:20:22.000', NULL, NULL, '');
INSERT INTO `sys_menu`
VALUES (1039, '公告删除', 107, 4, '#', '', '', 1, 0, 'F', '0', '0', 'system:notice:remove', '#', 1,
        '2022-06-17 17:20:22.000', NULL, NULL, '');
INSERT INTO `sys_menu`
VALUES (1040, '操作查询', 500, 1, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:operlog:query', '#', 1,
        '2022-06-17 17:20:22.000', NULL, NULL, '');
INSERT INTO `sys_menu`
VALUES (1041, '操作删除', 500, 2, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:operlog:remove', '#', 1,
        '2022-06-17 17:20:23.000', NULL, NULL, '');
INSERT INTO `sys_menu`
VALUES (1042, '日志导出', 500, 4, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:operlog:export', '#', 1,
        '2022-06-17 17:20:23.000', NULL, NULL, '');
INSERT INTO `sys_menu`
VALUES (1043, '登录查询', 501, 1, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:logininfor:query', '#', 1,
        '2022-06-17 17:20:23.000', NULL, NULL, '');
INSERT INTO `sys_menu`
VALUES (1044, '登录删除', 501, 2, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:logininfor:remove', '#', 1,
        '2022-06-17 17:20:23.000', NULL, NULL, '');
INSERT INTO `sys_menu`
VALUES (1045, '日志导出', 501, 3, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:logininfor:export', '#', 1,
        '2022-06-17 17:20:23.000', NULL, NULL, '');
INSERT INTO `sys_menu`
VALUES (1046, '在线查询', 109, 1, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:online:query', '#', 1,
        '2022-06-17 17:20:23.000', NULL, NULL, '');
INSERT INTO `sys_menu`
VALUES (1047, '批量强退', 109, 2, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:online:batchLogout', '#', 1,
        '2022-06-17 17:20:23.000', NULL, NULL, '');
INSERT INTO `sys_menu`
VALUES (1048, '单条强退', 109, 3, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:online:forceLogout', '#', 1,
        '2022-06-17 17:20:23.000', NULL, NULL, '');
INSERT INTO `sys_menu`
VALUES (1049, '任务查询', 110, 1, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:job:query', '#', 1,
        '2022-06-17 17:20:23.000', NULL, NULL, '');
INSERT INTO `sys_menu`
VALUES (1050, '任务新增', 110, 2, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:job:add', '#', 1,
        '2022-06-17 17:20:23.000', NULL, NULL, '');
INSERT INTO `sys_menu`
VALUES (1051, '任务修改', 110, 3, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:job:edit', '#', 1,
        '2022-06-17 17:20:23.000', NULL, NULL, '');
INSERT INTO `sys_menu`
VALUES (1052, '任务删除', 110, 4, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:job:remove', '#', 1,
        '2022-06-17 17:20:23.000', NULL, NULL, '');
INSERT INTO `sys_menu`
VALUES (1053, '状态修改', 110, 5, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:job:changeStatus', '#', 1,
        '2022-06-17 17:20:23.000', NULL, NULL, '');
INSERT INTO `sys_menu`
VALUES (1054, '任务导出', 110, 7, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:job:export', '#', 1,
        '2022-06-17 17:20:23.000', NULL, NULL, '');
INSERT INTO `sys_menu`
VALUES (1055, '生成查询', 115, 1, '#', '', '', 1, 0, 'F', '0', '0', 'tool:gen:query', '#', 1, '2022-06-17 17:20:23.000',
        NULL, NULL, '');
INSERT INTO `sys_menu`
VALUES (1056, '生成修改', 115, 2, '#', '', '', 1, 0, 'F', '0', '0', 'tool:gen:edit', '#', 1, '2022-06-17 17:20:23.000',
        NULL, NULL, '');
INSERT INTO `sys_menu`
VALUES (1057, '生成删除', 115, 3, '#', '', '', 1, 0, 'F', '0', '0', 'tool:gen:remove', '#', 1,
        '2022-06-17 17:20:23.000', NULL, NULL, '');
INSERT INTO `sys_menu`
VALUES (1058, '导入代码', 115, 2, '#', '', '', 1, 0, 'F', '0', '0', 'tool:gen:import', '#', 1,
        '2022-06-17 17:20:23.000', NULL, NULL, '');
INSERT INTO `sys_menu`
VALUES (1059, '预览代码', 115, 4, '#', '', '', 1, 0, 'F', '0', '0', 'tool:gen:preview', '#', 1,
        '2022-06-17 17:20:23.000', NULL, NULL, '');
INSERT INTO `sys_menu`
VALUES (1060, '生成代码', 115, 5, '#', '', '', 1, 0, 'F', '0', '0', 'tool:gen:code', '#', 1, '2022-06-17 17:20:23.000',
        NULL, NULL, '');
INSERT INTO `sys_menu`
VALUES (2250, '商品管理', 0, 1, 'pms', NULL, NULL, 1, 0, 'M', '0', '0', '', 'table', 1, '2022-11-18 08:46:30.000', 1,
        '2022-11-18 08:47:08.000', '');
INSERT INTO `sys_menu`
VALUES (2251, '品牌管理', 2250, 2, 'brand', 'pms/brand/index', NULL, 1, 0, 'C', '0', '0', 'pms:brand:list', '#', 1,
        '2022-11-18 08:48:35.000', 1, '2022-11-23 16:18:43.000', '品牌表菜单');
INSERT INTO `sys_menu`
VALUES (2252, '品牌表查询', 2251, 1, '#', '', NULL, 1, 0, 'F', '0', '0', 'pms:brand:query', '#', 1,
        '2022-11-18 08:48:35.000', 0, NULL, '');
INSERT INTO `sys_menu`
VALUES (2253, '品牌表新增', 2251, 2, '#', '', NULL, 1, 0, 'F', '0', '0', 'pms:brand:add', '#', 1,
        '2022-11-18 08:48:35.000', 0, NULL, '');
INSERT INTO `sys_menu`
VALUES (2254, '品牌表修改', 2251, 3, '#', '', NULL, 1, 0, 'F', '0', '0', 'pms:brand:edit', '#', 1,
        '2022-11-18 08:48:35.000', 0, NULL, '');
INSERT INTO `sys_menu`
VALUES (2255, '品牌表删除', 2251, 4, '#', '', NULL, 1, 0, 'F', '0', '0', 'pms:brand:remove', '#', 1,
        '2022-11-18 08:48:35.000', 0, NULL, '');
INSERT INTO `sys_menu`
VALUES (2256, '品牌表导出', 2251, 5, '#', '', NULL, 1, 0, 'F', '0', '0', 'pms:brand:export', '#', 1,
        '2022-11-18 08:48:35.000', 0, NULL, '');
INSERT INTO `sys_menu`
VALUES (2257, '商品列表', 2250, 1, 'product', 'pms/product/index', NULL, 1, 0, 'C', '0', '0', 'pms:product:list', '#',
        1, '2022-11-18 08:50:02.000', 1, '2022-11-23 16:18:17.000', '商品信息菜单');
INSERT INTO `sys_menu`
VALUES (2258, '商品信息查询', 2257, 1, '#', '', NULL, 1, 0, 'F', '0', '0', 'pms:product:query', '#', 1,
        '2022-11-18 08:50:02.000', 0, NULL, '');
INSERT INTO `sys_menu`
VALUES (2259, '商品信息新增', 2257, 2, '#', '', NULL, 1, 0, 'F', '0', '0', 'pms:product:add', '#', 1,
        '2022-11-18 08:50:02.000', 0, NULL, '');
INSERT INTO `sys_menu`
VALUES (2260, '商品信息修改', 2257, 3, 'product/AddProduct', '', NULL, 1, 0, 'F', '0', '0', 'pms:product:edit', '#', 1,
        '2022-11-18 08:50:02.000', 1, '2023-01-09 18:08:20.000', '');
INSERT INTO `sys_menu`
VALUES (2261, '商品信息删除', 2257, 4, '#', '', NULL, 1, 0, 'F', '0', '0', 'pms:product:remove', '#', 1,
        '2022-11-18 08:50:02.000', 0, NULL, '');
INSERT INTO `sys_menu`
VALUES (2262, '商品信息导出', 2257, 5, '#', '', NULL, 1, 0, 'F', '0', '0', 'pms:product:export', '#', 1,
        '2022-11-18 08:50:02.000', 0, NULL, '');
INSERT INTO `sys_menu`
VALUES (2263, '商品分类', 2250, 3, 'productCategory', 'pms/productCategory/index', NULL, 1, 0, 'C', '0', '0',
        'pms:productCategory:list', '#', 1, '2022-11-18 08:50:16.000', 1, '2022-11-23 16:18:55.000', '产品分类菜单');
INSERT INTO `sys_menu`
VALUES (2264, '产品分类查询', 2263, 1, '#', '', NULL, 1, 0, 'F', '0', '0', 'pms:productCategory:query', '#', 1,
        '2022-11-18 08:50:16.000', 0, NULL, '');
INSERT INTO `sys_menu`
VALUES (2265, '产品分类新增', 2263, 2, '#', '', NULL, 1, 0, 'F', '0', '0', 'pms:productCategory:add', '#', 1,
        '2022-11-18 08:50:16.000', 0, NULL, '');
INSERT INTO `sys_menu`
VALUES (2266, '产品分类修改', 2263, 3, '#', '', NULL, 1, 0, 'F', '0', '0', 'pms:productCategory:edit', '#', 1,
        '2022-11-18 08:50:16.000', 0, NULL, '');
INSERT INTO `sys_menu`
VALUES (2267, '产品分类删除', 2263, 4, '#', '', NULL, 1, 0, 'F', '0', '0', 'pms:productCategory:remove', '#', 1,
        '2022-11-18 08:50:17.000', 0, NULL, '');
INSERT INTO `sys_menu`
VALUES (2268, '产品分类导出', 2263, 5, '#', '', NULL, 1, 0, 'F', '0', '0', 'pms:productCategory:export', '#', 1,
        '2022-11-18 08:50:17.000', 0, NULL, '');
INSERT INTO `sys_menu`
VALUES (2269, 'sku的库存', 2250, 1, 'sku', 'pms/sku/index', NULL, 1, 0, 'C', '1', '0', 'pms:sku:list', '#', 1,
        '2022-11-18 08:50:32.000', 1, '2022-11-23 16:16:59.000', 'sku的库存菜单');
INSERT INTO `sys_menu`
VALUES (2270, 'sku的库存查询', 2269, 1, '#', '', NULL, 1, 0, 'F', '0', '0', 'pms:sku:query', '#', 1,
        '2022-11-18 08:50:32.000', 0, NULL, '');
INSERT INTO `sys_menu`
VALUES (2271, 'sku的库存新增', 2269, 2, '#', '', NULL, 1, 0, 'F', '0', '0', 'pms:sku:add', '#', 1,
        '2022-11-18 08:50:32.000', 0, NULL, '');
INSERT INTO `sys_menu`
VALUES (2272, 'sku的库存修改', 2269, 3, '#', '', NULL, 1, 0, 'F', '0', '0', 'pms:sku:edit', '#', 1,
        '2022-11-18 08:50:32.000', 0, NULL, '');
INSERT INTO `sys_menu`
VALUES (2273, 'sku的库存删除', 2269, 4, '#', '', NULL, 1, 0, 'F', '0', '0', 'pms:sku:remove', '#', 1,
        '2022-11-18 08:50:32.000', 0, NULL, '');
INSERT INTO `sys_menu`
VALUES (2274, 'sku的库存导出', 2269, 5, '#', '', NULL, 1, 0, 'F', '0', '0', 'pms:sku:export', '#', 1,
        '2022-11-18 08:50:32.000', 0, NULL, '');
INSERT INTO `sys_menu`
VALUES (2275, '会员列表', 2293, 1, 'member', 'ums/member/index', NULL, 1, 0, 'C', '0', '0', 'ums:member:list', '#', 1,
        '2022-11-28 15:37:32.000', 1, '2023-07-26 12:42:06.000', '会员信息菜单');
INSERT INTO `sys_menu`
VALUES (2276, '会员信息查询', 2275, 1, '#', '', NULL, 1, 0, 'F', '0', '0', 'ums:member:query', '#', 1,
        '2022-11-28 15:37:32.000', 0, NULL, '');
INSERT INTO `sys_menu`
VALUES (2277, '会员信息新增', 2275, 2, '#', '', NULL, 1, 0, 'F', '0', '0', 'ums:member:add', '#', 1,
        '2022-11-28 15:37:32.000', 0, NULL, '');
INSERT INTO `sys_menu`
VALUES (2278, '会员信息修改', 2275, 3, '#', '', NULL, 1, 0, 'F', '0', '0', 'ums:member:edit', '#', 1,
        '2022-11-28 15:37:32.000', 0, NULL, '');
INSERT INTO `sys_menu`
VALUES (2279, '会员信息删除', 2275, 4, '#', '', NULL, 1, 0, 'F', '0', '0', 'ums:member:remove', '#', 1,
        '2022-11-28 15:37:32.000', 0, NULL, '');
INSERT INTO `sys_menu`
VALUES (2280, '会员信息导出', 2275, 5, '#', '', NULL, 1, 0, 'F', '0', '0', 'ums:member:export', '#', 1,
        '2022-11-28 15:37:32.000', 0, NULL, '');
INSERT INTO `sys_menu`
VALUES (2281, '会员收货地址', 2293, 1, 'memberAddress', 'ums/memberAddress/index', NULL, 1, 0, 'C', '1', '0',
        'ums:memberAddress:list', '#', 1, '2022-11-28 15:37:43.000', 1, '2023-07-21 08:45:25.000', '会员收货地址菜单');
INSERT INTO `sys_menu`
VALUES (2282, '会员收货地址查询', 2281, 1, '#', '', NULL, 1, 0, 'F', '0', '0', 'ums:memberAddress:query', '#', 1,
        '2022-11-28 15:37:43.000', 0, NULL, '');
INSERT INTO `sys_menu`
VALUES (2283, '会员收货地址新增', 2281, 2, '#', '', NULL, 1, 0, 'F', '0', '0', 'ums:memberAddress:add', '#', 1,
        '2022-11-28 15:37:43.000', 0, NULL, '');
INSERT INTO `sys_menu`
VALUES (2284, '会员收货地址修改', 2281, 3, '#', '', NULL, 1, 0, 'F', '0', '0', 'ums:memberAddress:edit', '#', 1,
        '2022-11-28 15:37:43.000', 0, NULL, '');
INSERT INTO `sys_menu`
VALUES (2285, '会员收货地址删除', 2281, 4, '#', '', NULL, 1, 0, 'F', '0', '0', 'ums:memberAddress:remove', '#', 1,
        '2022-11-28 15:37:44.000', 0, NULL, '');
INSERT INTO `sys_menu`
VALUES (2286, '会员收货地址导出', 2281, 5, '#', '', NULL, 1, 0, 'F', '0', '0', 'ums:memberAddress:export', '#', 1,
        '2022-11-28 15:37:44.000', 0, NULL, '');
INSERT INTO `sys_menu`
VALUES (2287, '用户微信信息', 2293, 1, 'memberWechat', 'ums/memberWechat/index', NULL, 1, 0, 'C', '1', '0',
        'ums:memberWechat:list', '#', 1, '2022-11-28 15:37:54.000', 1, '2023-07-21 08:45:29.000', '用户微信信息菜单');
INSERT INTO `sys_menu`
VALUES (2288, '用户微信信息查询', 2287, 1, '#', '', NULL, 1, 0, 'F', '0', '0', 'ums:memberWechat:query', '#', 1,
        '2022-11-28 15:37:54.000', 0, NULL, '');
INSERT INTO `sys_menu`
VALUES (2289, '用户微信信息新增', 2287, 2, '#', '', NULL, 1, 0, 'F', '0', '0', 'ums:memberWechat:add', '#', 1,
        '2022-11-28 15:37:54.000', 0, NULL, '');
INSERT INTO `sys_menu`
VALUES (2290, '用户微信信息修改', 2287, 3, '#', '', NULL, 1, 0, 'F', '0', '0', 'ums:memberWechat:edit', '#', 1,
        '2022-11-28 15:37:54.000', 0, NULL, '');
INSERT INTO `sys_menu`
VALUES (2291, '用户微信信息删除', 2287, 4, '#', '', NULL, 1, 0, 'F', '0', '0', 'ums:memberWechat:remove', '#', 1,
        '2022-11-28 15:37:54.000', 0, NULL, '');
INSERT INTO `sys_menu`
VALUES (2292, '用户微信信息导出', 2287, 5, '#', '', NULL, 1, 0, 'F', '0', '0', 'ums:memberWechat:export', '#', 1,
        '2022-11-28 15:37:54.000', 0, NULL, '');
INSERT INTO `sys_menu`
VALUES (2293, '会员管理', 0, 2, 'member', NULL, NULL, 1, 0, 'M', '0', '0', NULL, 'user', 1, '2022-11-28 15:39:56.000',
        NULL, NULL, '');
INSERT INTO `sys_menu`
VALUES (2294, '订单列表', 2330, 1, 'order', 'oms/order/index', NULL, 1, 0, 'C', '0', '0', 'oms:order:list', '#', 1,
        '2022-12-08 14:13:52.000', 1, '2022-12-08 14:20:13.000', '订单表菜单');
INSERT INTO `sys_menu`
VALUES (2295, '订单表查询', 2294, 1, '#', '', NULL, 1, 0, 'F', '0', '0', 'oms:order:query', '#', 1,
        '2022-12-08 14:13:52.000', 0, NULL, '');
INSERT INTO `sys_menu`
VALUES (2296, '订单表新增', 2294, 2, '#', '', NULL, 1, 0, 'F', '0', '0', 'oms:order:add', '#', 1,
        '2022-12-08 14:13:52.000', 0, NULL, '');
INSERT INTO `sys_menu`
VALUES (2297, '订单表修改', 2294, 3, '#', '', NULL, 1, 0, 'F', '0', '0', 'oms:order:edit', '#', 1,
        '2022-12-08 14:13:52.000', 0, NULL, '');
INSERT INTO `sys_menu`
VALUES (2298, '订单表删除', 2294, 4, '#', '', NULL, 1, 0, 'F', '0', '0', 'oms:order:remove', '#', 1,
        '2022-12-08 14:13:52.000', 0, NULL, '');
INSERT INTO `sys_menu`
VALUES (2299, '订单表导出', 2294, 5, '#', '', NULL, 1, 0, 'F', '0', '0', 'oms:order:export', '#', 1,
        '2022-12-08 14:13:52.000', 0, NULL, '');
INSERT INTO `sys_menu`
VALUES (2300, '订单发货记录', 2330, 1, 'orderDeliveryHistory', 'oms/orderDeliveryHistory/index', NULL, 1, 0, 'C', '1',
        '0', 'oms:orderDeliveryHistory:list', '#', 1, '2022-12-08 14:14:02.000', 1, '2022-12-08 14:21:48.000',
        '订单发货记录菜单');
INSERT INTO `sys_menu`
VALUES (2301, '订单发货记录查询', 2300, 1, '#', '', NULL, 1, 0, 'F', '0', '0', 'oms:orderDeliveryHistory:query', '#', 1,
        '2022-12-08 14:14:02.000', 0, NULL, '');
INSERT INTO `sys_menu`
VALUES (2302, '订单发货记录新增', 2300, 2, '#', '', NULL, 1, 0, 'F', '0', '0', 'oms:orderDeliveryHistory:add', '#', 1,
        '2022-12-08 14:14:03.000', 0, NULL, '');
INSERT INTO `sys_menu`
VALUES (2303, '订单发货记录修改', 2300, 3, '#', '', NULL, 1, 0, 'F', '0', '0', 'oms:orderDeliveryHistory:edit', '#', 1,
        '2022-12-08 14:14:03.000', 0, NULL, '');
INSERT INTO `sys_menu`
VALUES (2304, '订单发货记录删除', 2300, 4, '#', '', NULL, 1, 0, 'F', '0', '0', 'oms:orderDeliveryHistory:remove', '#',
        1, '2022-12-08 14:14:03.000', 0, NULL, '');
INSERT INTO `sys_menu`
VALUES (2305, '订单发货记录导出', 2300, 5, '#', '', NULL, 1, 0, 'F', '0', '0', 'oms:orderDeliveryHistory:export', '#',
        1, '2022-12-08 14:14:03.000', 0, NULL, '');
INSERT INTO `sys_menu`
VALUES (2306, '订单item', 2330, 1, 'orderItem', 'oms/orderItem/index', NULL, 1, 0, 'C', '1', '0', 'oms:orderItem:list',
        '#', 1, '2022-12-08 14:14:12.000', 1, '2022-12-08 14:18:42.000', '订单中所包含的商品菜单');
INSERT INTO `sys_menu`
VALUES (2307, '订单中所包含的商品查询', 2306, 1, '#', '', NULL, 1, 0, 'F', '0', '0', 'oms:orderItem:query', '#', 1,
        '2022-12-08 14:14:12.000', 0, NULL, '');
INSERT INTO `sys_menu`
VALUES (2308, '订单中所包含的商品新增', 2306, 2, '#', '', NULL, 1, 0, 'F', '0', '0', 'oms:orderItem:add', '#', 1,
        '2022-12-08 14:14:12.000', 0, NULL, '');
INSERT INTO `sys_menu`
VALUES (2309, '订单中所包含的商品修改', 2306, 3, '#', '', NULL, 1, 0, 'F', '0', '0', 'oms:orderItem:edit', '#', 1,
        '2022-12-08 14:14:12.000', 0, NULL, '');
INSERT INTO `sys_menu`
VALUES (2310, '订单中所包含的商品删除', 2306, 4, '#', '', NULL, 1, 0, 'F', '0', '0', 'oms:orderItem:remove', '#', 1,
        '2022-12-08 14:14:12.000', 0, NULL, '');
INSERT INTO `sys_menu`
VALUES (2311, '订单中所包含的商品导出', 2306, 5, '#', '', NULL, 1, 0, 'F', '0', '0', 'oms:orderItem:export', '#', 1,
        '2022-12-08 14:14:12.000', 0, NULL, '');
INSERT INTO `sys_menu`
VALUES (2312, '订单操作记录', 2330, 3, 'orderOperateHistory', 'oms/orderOperateHistory/index', NULL, 1, 0, 'C', '1',
        '0', 'oms:orderOperateHistory:list', '#', 1, '2022-12-08 14:14:20.000', 1, '2023-07-26 11:11:21.000',
        '订单操作历史记录菜单');
INSERT INTO `sys_menu`
VALUES (2313, '订单操作历史记录查询', 2312, 1, '#', '', NULL, 1, 0, 'F', '0', '0', 'oms:orderOperateHistory:query', '#',
        1, '2022-12-08 14:14:20.000', 0, NULL, '');
INSERT INTO `sys_menu`
VALUES (2314, '订单操作历史记录新增', 2312, 2, '#', '', NULL, 1, 0, 'F', '0', '0', 'oms:orderOperateHistory:add', '#',
        1, '2022-12-08 14:14:20.000', 0, NULL, '');
INSERT INTO `sys_menu`
VALUES (2315, '订单操作历史记录修改', 2312, 3, '#', '', NULL, 1, 0, 'F', '0', '0', 'oms:orderOperateHistory:edit', '#',
        1, '2022-12-08 14:14:21.000', 0, NULL, '');
INSERT INTO `sys_menu`
VALUES (2316, '订单操作历史记录删除', 2312, 4, '#', '', NULL, 1, 0, 'F', '0', '0', 'oms:orderOperateHistory:remove',
        '#', 1, '2022-12-08 14:14:21.000', 0, NULL, '');
INSERT INTO `sys_menu`
VALUES (2317, '订单操作历史记录导出', 2312, 5, '#', '', NULL, 1, 0, 'F', '0', '0', 'oms:orderOperateHistory:export',
        '#', 1, '2022-12-08 14:14:21.000', 0, NULL, '');
INSERT INTO `sys_menu`
VALUES (2318, '售后管理', 2330, 2, 'aftersale', 'oms/aftersale/index', NULL, 1, 0, 'C', '0', '0', 'oms:aftersale:list',
        '#', 1, '2022-12-08 14:14:30.000', 1, '2022-12-29 12:24:56.000', '订单售后菜单');
INSERT INTO `sys_menu`
VALUES (2319, '订单售后查询', 2318, 1, '#', '', NULL, 1, 0, 'F', '0', '0', 'oms:aftersale:query', '#', 1,
        '2022-12-08 14:14:30.000', 1, '2022-12-29 12:25:43.000', '');
INSERT INTO `sys_menu`
VALUES (2320, '订单售后新增', 2318, 2, '#', '', NULL, 1, 0, 'F', '0', '0', 'oms:aftersale:add', '#', 1,
        '2022-12-08 14:14:30.000', 1, '2022-12-29 12:25:59.000', '');
INSERT INTO `sys_menu`
VALUES (2321, '订单售后修改', 2318, 3, '#', '', NULL, 1, 0, 'F', '0', '0', 'oms:aftersale:edit', '#', 1,
        '2022-12-08 14:14:30.000', 1, '2022-12-29 12:26:13.000', '');
INSERT INTO `sys_menu`
VALUES (2322, '订单售后删除', 2318, 4, '#', '', NULL, 1, 0, 'F', '0', '0', 'oms:aftersale:remove', '#', 1,
        '2022-12-08 14:14:30.000', 1, '2022-12-29 12:26:32.000', '');
INSERT INTO `sys_menu`
VALUES (2323, '订单售后导出', 2318, 5, '#', '', NULL, 1, 0, 'F', '0', '0', 'oms:aftersale:export', '#', 1,
        '2022-12-08 14:14:30.000', 1, '2022-12-29 12:26:46.000', '');
INSERT INTO `sys_menu`
VALUES (2324, '订单售后明细', 2330, 1, 'refundItem', 'oms/refundItem/index', NULL, 1, 0, 'C', '1', '0',
        'oms:refundItem:list', '#', 1, '2022-12-08 14:14:38.000', 1, '2022-12-08 14:19:33.000', '订单售后菜单');
INSERT INTO `sys_menu`
VALUES (2325, '订单售后查询', 2324, 1, '#', '', NULL, 1, 0, 'F', '0', '0', 'oms:refundItem:query', '#', 1,
        '2022-12-08 14:14:38.000', 0, NULL, '');
INSERT INTO `sys_menu`
VALUES (2326, '订单售后新增', 2324, 2, '#', '', NULL, 1, 0, 'F', '0', '0', 'oms:refundItem:add', '#', 1,
        '2022-12-08 14:14:38.000', 0, NULL, '');
INSERT INTO `sys_menu`
VALUES (2327, '订单售后修改', 2324, 3, '#', '', NULL, 1, 0, 'F', '0', '0', 'oms:refundItem:edit', '#', 1,
        '2022-12-08 14:14:38.000', 0, NULL, '');
INSERT INTO `sys_menu`
VALUES (2328, '订单售后删除', 2324, 4, '#', '', NULL, 1, 0, 'F', '0', '0', 'oms:refundItem:remove', '#', 1,
        '2022-12-08 14:14:38.000', 0, NULL, '');
INSERT INTO `sys_menu`
VALUES (2329, '订单售后导出', 2324, 5, '#', '', NULL, 1, 0, 'F', '0', '0', 'oms:refundItem:export', '#', 1,
        '2022-12-08 14:14:38.000', 0, NULL, '');
INSERT INTO `sys_menu`
VALUES (2330, '订单管理', 0, 1, 'order', NULL, NULL, 1, 0, 'M', '0', '0', '', 'date-range', 1,
        '2022-12-08 14:17:58.000', 1, '2023-03-20 15:17:53.000', '');
INSERT INTO `sys_menu`
VALUES (2331, '修改会员账户状态', 2275, 6, '', NULL, NULL, 1, 0, 'F', '0', '0', 'ums:member:change:status', '#', 1,
        '2023-06-27 15:11:14.000', 1, '2023-06-27 15:11:27.000', '');
INSERT INTO `sys_menu`
VALUES (2333, '订单发货', 2294, 6, '', NULL, NULL, 1, 0, 'F', '0', '0', 'oms:order:delivery', '#', 1,
        '2023-06-30 16:01:32.000', NULL, NULL, '');
INSERT INTO `sys_menu`
VALUES (2335, '购物车列表', 2293, 3, 'shoppingCart', 'ums/memberCart/index', NULL, 1, 0, 'C', '0', '0',
        'ums:memberCart:list', '#', 1, '2023-07-21 15:35:01.000', 1, '2023-07-26 12:42:16.000', '');
INSERT INTO `sys_menu`
VALUES (2336, '查看日志', 2294, 7, '', NULL, NULL, 1, 0, 'F', '0', '0', 'oms:order:log', '#', 1,
        '2023-07-24 09:52:16.000', NULL, NULL, '');
INSERT INTO `sys_menu`
VALUES (2337, '查看日志', 2318, 6, '', NULL, NULL, 1, 0, 'F', '0', '0', 'oms:aftersale:log', '#', 1,
        '2023-07-24 12:15:42.000', NULL, NULL, '');
INSERT INTO `sys_menu`
VALUES (2338, '查看会员统计数据', 2275, 7, '', NULL, NULL, 1, 0, 'F', '0', '0', 'ums:member:statistics', '#', 1,
        '2023-07-24 12:16:53.000', NULL, NULL, '');
INSERT INTO `sys_menu`
VALUES (2339, '登录管理', 2293, 4, 'loginInfor', 'ums/memberLogininfor/index', NULL, 1, 0, 'C', '0', '0',
        'ums:memberLogininfor:list', '#', 1, '2023-07-26 15:03:06.000', 1, '2023-07-26 16:41:35.000', '');
INSERT INTO `sys_menu`
VALUES (2340, '数据统计', 0, 4, 'systemStatistics', 'aws/systemStatistics/index', NULL, 1, 0, 'C', '0', '0',
        'aws:systemStatistics:list', 'chart', 1, '2023-07-31 15:57:38.000', NULL, NULL, '');

-- ----------------------------
-- Table structure for sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice`
(
    `notice_id`      int(4) NOT NULL AUTO_INCREMENT COMMENT '公告ID',
    `notice_title`   varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '公告标题',
    `notice_type`    char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci     NOT NULL COMMENT '公告类型（1通知 2公告）',
    `notice_content` longblob NULL COMMENT '公告内容',
    `status`         char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '公告状态（0正常 1关闭）',
    `create_by`      bigint(20) NULL DEFAULT NULL COMMENT '创建者',
    `create_time`    datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
    `update_by`      bigint(20) NULL DEFAULT NULL COMMENT '更新者',
    `update_time`    datetime(3) NULL DEFAULT NULL COMMENT '更新时间',
    `remark`         varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`notice_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '通知公告表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_notice
-- ----------------------------
INSERT INTO `sys_notice`
VALUES (1, '温馨提醒：2018-07-01 若依新版本发布啦', '2',
        0x3C703E3C696D67207372633D22687474703A2F2F72756F79692D6D616C6C2D737A2E6F73732D636E2D7368656E7A68656E2E616C6979756E63732E636F6D2F323032332F30312F30323734353932336139376532653432313438326266376662663930343661336434E5BEAEE4BFA1E59BBEE789875F32303232303630363131343233312E6A7067223EE696B0E78988E69CACE58685E5AEB93C2F703E,
        '0', 1, '2022-06-17 17:20:29', 1, '2023-01-02 21:38:22.000', '管理员');
INSERT INTO `sys_notice`
VALUES (2, '维护通知：2018-07-01 若依系统凌晨维护', '1', 0xE7BBB4E68AA4E58685E5AEB9, '0', 1, '2022-06-17 17:20:30', NULL,
        NULL, '管理员');

-- ----------------------------
-- Table structure for sys_oper_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_oper_log`;
CREATE TABLE `sys_oper_log`
(
    `oper_id`        bigint(20) NOT NULL AUTO_INCREMENT COMMENT '日志主键',
    `title`          varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '模块标题',
    `business_type`  int(2) NULL DEFAULT 0 COMMENT '业务类型（0其它 1新增 2修改 3删除）',
    `method`         varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '方法名称',
    `request_method` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '请求方式',
    `operator_type`  int(1) NULL DEFAULT 0 COMMENT '操作类别（0其它 1后台用户 2手机端用户）',
    `oper_name`      varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '操作人员',
    `dept_name`      varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '部门名称',
    `oper_url`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '请求URL',
    `oper_ip`        varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '主机地址',
    `oper_location`  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '操作地点',
    `oper_param`     varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '请求参数',
    `json_result`    varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '返回参数',
    `status`         int(1) NULL DEFAULT 0 COMMENT '操作状态（0正常 1异常）',
    `error_msg`      varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '错误消息',
    `oper_time`      datetime(0) NULL DEFAULT NULL COMMENT '操作时间',
    PRIMARY KEY (`oper_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1234 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '操作日志记录' ROW_FORMAT = Dynamic;


-- ----------------------------
-- Table structure for sys_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_post`;
CREATE TABLE `sys_post`
(
    `post_id`     bigint(20) NOT NULL AUTO_INCREMENT COMMENT '岗位ID',
    `post_code`   varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '岗位编码',
    `post_name`   varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '岗位名称',
    `post_sort`   int(4) NOT NULL COMMENT '显示顺序',
    `status`      char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci     NOT NULL COMMENT '状态（0正常 1停用）',
    `create_by`   bigint(20) NULL DEFAULT NULL COMMENT '创建者',
    `create_time` datetime(3) NULL DEFAULT NULL COMMENT '创建时间',
    `update_by`   bigint(20) NULL DEFAULT NULL COMMENT '更新者',
    `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
    `remark`      varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`post_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '岗位信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_post
-- ----------------------------
INSERT INTO `sys_post`
VALUES (1, 'ceo', '董事长', 1, '0', 1, '2022-06-17 17:20:20.000', NULL, NULL, '');
INSERT INTO `sys_post`
VALUES (2, 'se', '项目经理', 2, '0', 1, '2022-06-17 17:20:20.000', NULL, NULL, '');
INSERT INTO `sys_post`
VALUES (3, 'hr', '人力资源', 3, '0', 1, '2022-06-17 17:20:20.000', NULL, NULL, '');
INSERT INTO `sys_post`
VALUES (4, 'user', '普通员工', 4, '0', 1, '2022-06-17 17:20:20.000', NULL, NULL, '');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`
(
    `role_id`             bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
    `role_name`           varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '角色名称',
    `role_key`            varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色权限字符串',
    `role_sort`           int(4) NOT NULL COMMENT '显示顺序',
    `data_scope`          char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '1' COMMENT '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
    `menu_check_strictly` tinyint(1) NULL DEFAULT 1 COMMENT '菜单树选择项是否关联显示',
    `dept_check_strictly` tinyint(1) NULL DEFAULT 1 COMMENT '部门树选择项是否关联显示',
    `status`              char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci      NOT NULL COMMENT '角色状态（0正常 1停用）',
    `del_flag`            char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
    `create_by`           bigint(20) NULL DEFAULT NULL COMMENT '创建者',
    `create_time`         datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
    `update_by`           bigint(20) NULL DEFAULT NULL COMMENT '更新者',
    `update_time`         datetime(3) NULL DEFAULT NULL COMMENT '更新时间',
    `remark`              varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 102 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role`
VALUES (1, '超级管理员', 'admin', 1, '1', 1, 1, '0', '0', 1, '2022-06-17 17:20:20', NULL, NULL, '超级管理员');
INSERT INTO `sys_role`
VALUES (3, '管理员', 'super', 3, '1', 1, 1, '0', '0', 1, '2022-06-28 15:57:51', NULL, NULL, '管理员');
INSERT INTO `sys_role`
VALUES (100, '普通用户', 'common', 2, '2', 1, 1, '0', '0', NULL, '2022-06-20 09:52:16', 1, '2022-11-07 10:38:58.000',
        NULL);
INSERT INTO `sys_role`
VALUES (101, '浏览者', 'viewer', 4, '1', 1, 1, '0', '0', 1, '2022-11-01 17:52:33', 1, '2023-07-31 15:57:48.000', NULL);

-- ----------------------------
-- Table structure for sys_role_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_dept`;
CREATE TABLE `sys_role_dept`
(
    `role_id` bigint(20) NOT NULL COMMENT '角色ID',
    `dept_id` bigint(20) NOT NULL COMMENT '部门ID',
    PRIMARY KEY (`role_id`, `dept_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色和部门关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_dept
-- ----------------------------
INSERT INTO `sys_role_dept`
VALUES (2, 100);
INSERT INTO `sys_role_dept`
VALUES (2, 101);
INSERT INTO `sys_role_dept`
VALUES (2, 105);

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`
(
    `role_id` bigint(20) NOT NULL COMMENT '角色ID',
    `menu_id` bigint(20) NOT NULL COMMENT '菜单ID',
    PRIMARY KEY (`role_id`, `menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色和菜单关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu`
VALUES (2, 1);
INSERT INTO `sys_role_menu`
VALUES (2, 2);
INSERT INTO `sys_role_menu`
VALUES (2, 3);
INSERT INTO `sys_role_menu`
VALUES (2, 4);
INSERT INTO `sys_role_menu`
VALUES (2, 100);
INSERT INTO `sys_role_menu`
VALUES (2, 101);
INSERT INTO `sys_role_menu`
VALUES (2, 102);
INSERT INTO `sys_role_menu`
VALUES (2, 103);
INSERT INTO `sys_role_menu`
VALUES (2, 104);
INSERT INTO `sys_role_menu`
VALUES (2, 105);
INSERT INTO `sys_role_menu`
VALUES (2, 106);
INSERT INTO `sys_role_menu`
VALUES (2, 107);
INSERT INTO `sys_role_menu`
VALUES (2, 108);
INSERT INTO `sys_role_menu`
VALUES (2, 109);
INSERT INTO `sys_role_menu`
VALUES (2, 110);
INSERT INTO `sys_role_menu`
VALUES (2, 111);
INSERT INTO `sys_role_menu`
VALUES (2, 112);
INSERT INTO `sys_role_menu`
VALUES (2, 113);
INSERT INTO `sys_role_menu`
VALUES (2, 114);
INSERT INTO `sys_role_menu`
VALUES (2, 115);
INSERT INTO `sys_role_menu`
VALUES (2, 116);
INSERT INTO `sys_role_menu`
VALUES (2, 500);
INSERT INTO `sys_role_menu`
VALUES (2, 501);
INSERT INTO `sys_role_menu`
VALUES (2, 1000);
INSERT INTO `sys_role_menu`
VALUES (2, 1001);
INSERT INTO `sys_role_menu`
VALUES (2, 1002);
INSERT INTO `sys_role_menu`
VALUES (2, 1003);
INSERT INTO `sys_role_menu`
VALUES (2, 1004);
INSERT INTO `sys_role_menu`
VALUES (2, 1005);
INSERT INTO `sys_role_menu`
VALUES (2, 1006);
INSERT INTO `sys_role_menu`
VALUES (2, 1007);
INSERT INTO `sys_role_menu`
VALUES (2, 1008);
INSERT INTO `sys_role_menu`
VALUES (2, 1009);
INSERT INTO `sys_role_menu`
VALUES (2, 1010);
INSERT INTO `sys_role_menu`
VALUES (2, 1011);
INSERT INTO `sys_role_menu`
VALUES (2, 1012);
INSERT INTO `sys_role_menu`
VALUES (2, 1013);
INSERT INTO `sys_role_menu`
VALUES (2, 1014);
INSERT INTO `sys_role_menu`
VALUES (2, 1015);
INSERT INTO `sys_role_menu`
VALUES (2, 1016);
INSERT INTO `sys_role_menu`
VALUES (2, 1017);
INSERT INTO `sys_role_menu`
VALUES (2, 1018);
INSERT INTO `sys_role_menu`
VALUES (2, 1019);
INSERT INTO `sys_role_menu`
VALUES (2, 1020);
INSERT INTO `sys_role_menu`
VALUES (2, 1021);
INSERT INTO `sys_role_menu`
VALUES (2, 1022);
INSERT INTO `sys_role_menu`
VALUES (2, 1023);
INSERT INTO `sys_role_menu`
VALUES (2, 1024);
INSERT INTO `sys_role_menu`
VALUES (2, 1025);
INSERT INTO `sys_role_menu`
VALUES (2, 1026);
INSERT INTO `sys_role_menu`
VALUES (2, 1027);
INSERT INTO `sys_role_menu`
VALUES (2, 1028);
INSERT INTO `sys_role_menu`
VALUES (2, 1029);
INSERT INTO `sys_role_menu`
VALUES (2, 1030);
INSERT INTO `sys_role_menu`
VALUES (2, 1031);
INSERT INTO `sys_role_menu`
VALUES (2, 1032);
INSERT INTO `sys_role_menu`
VALUES (2, 1033);
INSERT INTO `sys_role_menu`
VALUES (2, 1034);
INSERT INTO `sys_role_menu`
VALUES (2, 1035);
INSERT INTO `sys_role_menu`
VALUES (2, 1036);
INSERT INTO `sys_role_menu`
VALUES (2, 1037);
INSERT INTO `sys_role_menu`
VALUES (2, 1038);
INSERT INTO `sys_role_menu`
VALUES (2, 1039);
INSERT INTO `sys_role_menu`
VALUES (2, 1040);
INSERT INTO `sys_role_menu`
VALUES (2, 1041);
INSERT INTO `sys_role_menu`
VALUES (2, 1042);
INSERT INTO `sys_role_menu`
VALUES (2, 1043);
INSERT INTO `sys_role_menu`
VALUES (2, 1044);
INSERT INTO `sys_role_menu`
VALUES (2, 1045);
INSERT INTO `sys_role_menu`
VALUES (2, 1046);
INSERT INTO `sys_role_menu`
VALUES (2, 1047);
INSERT INTO `sys_role_menu`
VALUES (2, 1048);
INSERT INTO `sys_role_menu`
VALUES (2, 1049);
INSERT INTO `sys_role_menu`
VALUES (2, 1050);
INSERT INTO `sys_role_menu`
VALUES (2, 1051);
INSERT INTO `sys_role_menu`
VALUES (2, 1052);
INSERT INTO `sys_role_menu`
VALUES (2, 1053);
INSERT INTO `sys_role_menu`
VALUES (2, 1054);
INSERT INTO `sys_role_menu`
VALUES (2, 1055);
INSERT INTO `sys_role_menu`
VALUES (2, 1056);
INSERT INTO `sys_role_menu`
VALUES (2, 1057);
INSERT INTO `sys_role_menu`
VALUES (2, 1058);
INSERT INTO `sys_role_menu`
VALUES (2, 1059);
INSERT INTO `sys_role_menu`
VALUES (2, 1060);
INSERT INTO `sys_role_menu`
VALUES (100, 1);
INSERT INTO `sys_role_menu`
VALUES (100, 105);
INSERT INTO `sys_role_menu`
VALUES (100, 1026);
INSERT INTO `sys_role_menu`
VALUES (101, 1);
INSERT INTO `sys_role_menu`
VALUES (101, 107);
INSERT INTO `sys_role_menu`
VALUES (101, 1036);
INSERT INTO `sys_role_menu`
VALUES (101, 2250);
INSERT INTO `sys_role_menu`
VALUES (101, 2251);
INSERT INTO `sys_role_menu`
VALUES (101, 2252);
INSERT INTO `sys_role_menu`
VALUES (101, 2257);
INSERT INTO `sys_role_menu`
VALUES (101, 2258);
INSERT INTO `sys_role_menu`
VALUES (101, 2263);
INSERT INTO `sys_role_menu`
VALUES (101, 2264);
INSERT INTO `sys_role_menu`
VALUES (101, 2269);
INSERT INTO `sys_role_menu`
VALUES (101, 2270);
INSERT INTO `sys_role_menu`
VALUES (101, 2275);
INSERT INTO `sys_role_menu`
VALUES (101, 2276);
INSERT INTO `sys_role_menu`
VALUES (101, 2281);
INSERT INTO `sys_role_menu`
VALUES (101, 2282);
INSERT INTO `sys_role_menu`
VALUES (101, 2287);
INSERT INTO `sys_role_menu`
VALUES (101, 2288);
INSERT INTO `sys_role_menu`
VALUES (101, 2293);
INSERT INTO `sys_role_menu`
VALUES (101, 2294);
INSERT INTO `sys_role_menu`
VALUES (101, 2295);
INSERT INTO `sys_role_menu`
VALUES (101, 2300);
INSERT INTO `sys_role_menu`
VALUES (101, 2301);
INSERT INTO `sys_role_menu`
VALUES (101, 2306);
INSERT INTO `sys_role_menu`
VALUES (101, 2307);
INSERT INTO `sys_role_menu`
VALUES (101, 2312);
INSERT INTO `sys_role_menu`
VALUES (101, 2313);
INSERT INTO `sys_role_menu`
VALUES (101, 2318);
INSERT INTO `sys_role_menu`
VALUES (101, 2319);
INSERT INTO `sys_role_menu`
VALUES (101, 2324);
INSERT INTO `sys_role_menu`
VALUES (101, 2325);
INSERT INTO `sys_role_menu`
VALUES (101, 2330);
INSERT INTO `sys_role_menu`
VALUES (101, 2333);
INSERT INTO `sys_role_menu`
VALUES (101, 2335);
INSERT INTO `sys_role_menu`
VALUES (101, 2336);
INSERT INTO `sys_role_menu`
VALUES (101, 2337);
INSERT INTO `sys_role_menu`
VALUES (101, 2338);
INSERT INTO `sys_role_menu`
VALUES (101, 2340);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`
(
    `user_id`     bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
    `dept_id`     bigint(20) NULL DEFAULT NULL COMMENT '部门ID',
    `user_name`   varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户账号',
    `nick_name`   varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户昵称',
    `user_type`   varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '00' COMMENT '用户类型（00系统用户）',
    `email`       varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '用户邮箱',
    `phonenumber` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '手机号码',
    `sex`         char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '用户性别（0男 1女 2未知）',
    `avatar`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '头像地址',
    `password`    varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '密码',
    `status`      char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
    `del_flag`    char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
    `login_ip`    varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '最后登录IP',
    `login_date`  datetime(0) NULL DEFAULT NULL COMMENT '最后登录时间',
    `create_by`   bigint(20) NULL DEFAULT NULL COMMENT '创建者',
    `create_time` datetime(3) NULL DEFAULT NULL COMMENT '创建时间',
    `update_by`   bigint(20) NULL DEFAULT NULL COMMENT '更新者',
    `update_time` datetime(3) NULL DEFAULT NULL COMMENT '更新时间',
    `remark`      varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 125 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user admin用户密码：admin123
-- ----------------------------
INSERT INTO `sys_user`
VALUES (1, 103, 'admin', '若依', '00', 'ry@163.com', '15888888888', '1', '',
        '$2a$10$QuYyT7spyaC5gXaYNoHtMOu/51WRLHnQSXRsK0kgipTudLgh2MI.K', '0', '0', '153.37.195.162',
        '2023-08-16 02:16:35', 1, '2022-06-17 17:20:19.000', NULL, '2023-08-16 10:16:35.000', '管理员');
INSERT INTO `sys_user`
VALUES (115, 100, 'cyl', 'mall', '00', '', '', '0', '', '$2a$10$QuYyT7spyaC5gXaYNoHtMOu/51WRLHnQSXRsK0kgipTudLgh2MI.K',
        '0', '2', '127.0.0.1', '2022-11-17 10:18:24', 1, '2022-11-04 17:06:20.000', 1, '2023-01-09 14:08:29.000', NULL);
-- ----------------------------
-- Table structure for sys_user_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_post`;
CREATE TABLE `sys_user_post`
(
    `user_id` bigint(20) NOT NULL COMMENT '用户ID',
    `post_id` bigint(20) NOT NULL COMMENT '岗位ID',
    PRIMARY KEY (`user_id`, `post_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户与岗位关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_post
-- ----------------------------
INSERT INTO `sys_user_post`
VALUES (1, 1);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`
(
    `user_id` bigint(20) NOT NULL COMMENT '用户ID',
    `role_id` bigint(20) NOT NULL COMMENT '角色ID',
    PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户和角色关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role`
VALUES (1, 1);
INSERT INTO `sys_user_role`
VALUES (113, 100);
INSERT INTO `sys_user_role`
VALUES (114, 100);
INSERT INTO `sys_user_role`
VALUES (116, 101);
INSERT INTO `sys_user_role`
VALUES (117, 100);
INSERT INTO `sys_user_role`
VALUES (118, 100);
INSERT INTO `sys_user_role`
VALUES (119, 100);
INSERT INTO `sys_user_role`
VALUES (120, 100);
INSERT INTO `sys_user_role`
VALUES (121, 100);
INSERT INTO `sys_user_role`
VALUES (122, 100);
INSERT INTO `sys_user_role`
VALUES (123, 100);
INSERT INTO `sys_user_role`
VALUES (124, 100);

-- ----------------------------
-- Table structure for ums_member
-- ----------------------------
DROP TABLE IF EXISTS `ums_member`;
CREATE TABLE `ums_member`
(
    `id`              bigint(20) NOT NULL AUTO_INCREMENT,
    `nickname`        varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
    `password`        varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
    `phone_encrypted` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '加密手机号',
    `phone_hidden`    varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '隐藏前三位后四位的手机号',
    `mark`            varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '用户备注',
    `status`          int(1) NULL DEFAULT NULL COMMENT '帐号启用状态:0->禁用；1->启用',
    `avatar`          varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
    `gender`          int(1) NULL DEFAULT NULL COMMENT '性别：0->未知；1->男；2->女',
    `city`            varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户所在城市',
    `province`        varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户所在省份',
    `country`         varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户所在国家',
    `remark`          varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注',
    `birthday`        date NULL DEFAULT NULL COMMENT '生日',
    `spread_uid`      bigint(20) UNSIGNED NULL DEFAULT 0 COMMENT '推广员id',
    `spread_time`     datetime(0) NULL DEFAULT NULL COMMENT '推广员关联时间',
    `level`           tinyint(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '等级',
    `integral`        decimal(8, 2) UNSIGNED NOT NULL DEFAULT 0.00 COMMENT '用户剩余积分',
    `create_by`       bigint(20) NULL DEFAULT NULL COMMENT '创建人',
    `create_time`     datetime(3) NULL DEFAULT NULL COMMENT '创建时间',
    `update_by`       bigint(20) NULL DEFAULT NULL COMMENT '修改人',
    `update_time`     datetime(3) NULL DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 47 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '会员信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ums_member_address
-- ----------------------------
DROP TABLE IF EXISTS `ums_member_address`;
CREATE TABLE `ums_member_address`
(
    `id`              bigint(20) NOT NULL AUTO_INCREMENT,
    `member_id`       bigint(20) NULL DEFAULT NULL,
    `name`            varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收货人名称',
    `phone_hidden`    varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '隐藏前三位后四位的手机号',
    `phone_encrypted` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `default_status`  int(1) NULL DEFAULT NULL COMMENT '是否为默认',
    `post_code`       varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮政编码',
    `province`        varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '省份/直辖市',
    `city`            varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '城市',
    `district`        varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '区',
    `detail_address`  varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '详细地址(街道)',
    `is_default`      tinyint(3) UNSIGNED ZEROFILL NOT NULL DEFAULT 000 COMMENT '是否默认',
    `province_id`     bigint(20) NULL DEFAULT NULL COMMENT '省份/直辖市id',
    `city_id`         bigint(20) NULL DEFAULT NULL COMMENT '城市id',
    `district_id`     bigint(20) NULL DEFAULT NULL COMMENT '区id',
    `create_by`       bigint(20) NULL DEFAULT NULL COMMENT '创建人',
    `create_time`     datetime(3) NULL DEFAULT NULL COMMENT '创建时间',
    `update_by`       bigint(20) NULL DEFAULT NULL COMMENT '修改人',
    `update_time`     datetime(3) NULL DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '会员收货地址' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ums_member_cart
-- ----------------------------
DROP TABLE IF EXISTS `ums_member_cart`;
CREATE TABLE `ums_member_cart`
(
    `id`           bigint(20) NOT NULL AUTO_INCREMENT COMMENT '购物车表ID',
    `status`       int(1) NULL DEFAULT NULL COMMENT '0->失效；1->有效',
    `member_id`    bigint(20) UNSIGNED NOT NULL COMMENT '用户ID',
    `product_id`   bigint(20) UNSIGNED NOT NULL COMMENT '商品ID',
    `pic`          varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '展示图片',
    `sku_id`       bigint(20) UNSIGNED NULL DEFAULT NULL COMMENT 'SKU ID',
    `product_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `sp_data`      varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品属性',
    `quantity`     smallint(5) UNSIGNED NOT NULL DEFAULT 0 COMMENT '商品数量',
    `create_by`    bigint(20) NULL DEFAULT NULL COMMENT '创建人',
    `create_time`  datetime(3) NULL DEFAULT NULL COMMENT '创建时间',
    `update_by`    bigint(20) NULL DEFAULT NULL COMMENT '修改人',
    `update_time`  datetime(3) NULL DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX          `member_id`(`member_id`) USING BTREE,
    INDEX          `product_id`(`product_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 219 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '购物车' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ums_member_logininfor
-- ----------------------------
DROP TABLE IF EXISTS `ums_member_logininfor`;
CREATE TABLE `ums_member_logininfor`
(
    `id`             bigint(20) NOT NULL AUTO_INCREMENT,
    `phone`          varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '会员手机号',
    `member_id`      bigint(20) NULL DEFAULT NULL COMMENT '会员id',
    `ipaddr`         varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '登录IP地址',
    `login_location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '登录地点',
    `browser`        varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '浏览器类型',
    `os`             varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '操作系统',
    `login_time`     datetime(0) NULL DEFAULT NULL COMMENT '登陆时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 196 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '会员登录记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ums_member_wechat
-- ----------------------------
DROP TABLE IF EXISTS `ums_member_wechat`;
CREATE TABLE `ums_member_wechat`
(
    `id`             bigint(20) NOT NULL AUTO_INCREMENT,
    `member_id`      bigint(20) NULL DEFAULT NULL,
    `unionid`        varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段',
    `openid`         varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户的标识，对当前公众号唯一',
    `routine_openid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '小程序唯一身份ID',
    `groupid`        smallint(5) NULL DEFAULT 0 COMMENT '用户所在的分组ID（兼容旧的用户分组接口）',
    `tagid_list`     varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户被打上的标签ID列表',
    `subscribe`      tinyint(3) NULL DEFAULT 1 COMMENT '用户是否订阅该公众号标识',
    `subscribe_time` int(10) NULL DEFAULT NULL COMMENT '关注公众号时间',
    `session_key`    varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '小程序用户会话密匙',
    `access_token`   varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'token',
    `expires_in`     int(11) NULL DEFAULT NULL COMMENT '过期时间',
    `refresh_token`  varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '刷新token',
    `expire_time`    datetime(3) NULL DEFAULT NULL COMMENT '过期时间',
    `create_by`      bigint(20) NULL DEFAULT NULL COMMENT '创建人',
    `create_time`    datetime(3) NULL DEFAULT NULL COMMENT '创建时间',
    `update_by`      bigint(20) NULL DEFAULT NULL COMMENT '修改人',
    `update_time`    datetime(3) NULL DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 35 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户微信信息' ROW_FORMAT = Dynamic;


SET
FOREIGN_KEY_CHECKS = 1;

CREATE TABLE `ums_feedback`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT,
    `type`          varchar(255) DEFAULT NULL COMMENT '类型',
    `content`       varchar(500) DEFAULT NULL COMMENT '具体说明',
    `images`        text COMMENT '图片',
    `phone`         varchar(255) DEFAULT NULL COMMENT '联系电话',
    `create_by`     bigint(20) DEFAULT NULL COMMENT '创建人',
    `create_time`   datetime(3) DEFAULT NULL COMMENT '创建时间',
    `handle_status` int(11) DEFAULT NULL COMMENT '处理状态 0：未处理  1：已处理',
    `remark`        varchar(255) DEFAULT NULL COMMENT '备注',
    `handle_time`   datetime(3) DEFAULT NULL COMMENT '处理时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COMMENT='意见反馈';

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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COMMENT='积分流水表';

CREATE TABLE `ums_member_account`
(
    `member_id`              bigint(20) NOT NULL,
    `integral_balance`       decimal(10, 2) DEFAULT '0.00' COMMENT '积分余额',
    `total_integral_balance` decimal(10, 2) DEFAULT NULL COMMENT '历史总共积分',
    `update_time`            datetime(3) DEFAULT NULL COMMENT '修改时间',
    `create_time`            datetime(3) DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='会员账户表';

INSERT INTO `sys_config`(`config_id`, `config_name`, `config_key`, `config_value`, `config_type`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (104, '客服配置', 'mall.contact', '<p></p>', 'N', 1, '2024-02-28 15:07:33', 1, '2024-02-29 11:44:17.000', NULL);

INSERT INTO `sys_config`(`config_id`, `config_name`, `config_key`, `config_value`, `config_type`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (106, '隐私协议', 'mall.privacyAgreement', '<p></p><p>&nbsp;</p>', 'N', 1, '2024-02-28 15:53:35', NULL, NULL, NULL);

INSERT INTO `sys_config`(`config_id`, `config_name`, `config_key`, `config_value`, `config_type`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (107, '常见问题', 'mall.question', '<p>暂无</p>', 'N', 1, '2024-02-28 15:55:10', NULL, NULL, NULL);

INSERT INTO `sys_config`(`config_id`, `config_name`, `config_key`, `config_value`, `config_type`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (108, '关于我们', 'mall.aboutUs', '<p></p>', 'N', 1, '2024-02-29 11:44:55', NULL, NULL, NULL);

INSERT INTO `sys_config`(`config_id`, `config_name`, `config_key`, `config_value`, `config_type`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (114, '积分配置', 'activity-integral-income-set-key', '{\"signStatus\":1,\"signCount\":1,\"orderAmount\":\"1\",\"orderCount\":1}', 'N', 1, '2024-03-01 14:44:13', 116, '2024-03-06 16:04:08.000', NULL);

UPDATE `sys_config` SET `config_name` = '账号自助-验证码开关', `config_key` = 'sys.account.captchaOnOff', `config_value` = 'true', `config_type` = 'Y', `create_by` = 1, `create_time` = '2022-06-17 17:20:29', `update_by` = 1, `update_time` = '2023-12-18 18:08:30.000', `remark` = '是否开启验证码功能（true开启，false关闭）' WHERE `config_id` = 4;

INSERT INTO `sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (2341, '意见反馈', 2342, 5, 'feedback', 'ums/feedback/index', NULL, 1, 0, 'C', '0', '0', 'ums:feedback:list', 'list', 1, '2024-02-26 15:50:59.000', 1, '2024-02-27 15:40:59.000', '');

INSERT INTO `sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (2342, '消息管理', 0, 6, 'notice', NULL, NULL, 1, 0, 'M', '0', '0', NULL, 'message', 1, '2024-02-27 15:40:35.000', NULL, NULL, '');

INSERT INTO `sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (2343, '内容配置', 2342, 2, 'contentSet', 'set/setting', NULL, 1, 0, 'C', '0', '0', 'sys:content:set', 'system', 1, '2024-02-28 13:32:01.000', 1, '2024-02-28 13:33:45.000', '');

INSERT INTO `sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (2344, '缓存列表', 2, 6, 'cacheList', 'monitor/cache/list', NULL, 1, 0, 'C', '0', '0', 'monitor:cache:list', 'list', 1, '2024-02-29 17:46:30.000', NULL, NULL, '');

INSERT INTO `sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (2345, '营销管理', 0, 5, 'activity', NULL, NULL, 1, 0, 'M', '0', '0', NULL, 'money', 1, '2024-03-01 13:25:02.000', NULL, NULL, '');

INSERT INTO `sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (2346, '积分设置', 2345, 1, 'integralSetting', 'set/integralSetting', NULL, 1, 0, 'C', '0', '0', 'sys:integral:set', '#', 1, '2024-03-01 13:25:52.000', 1, '2024-03-01 13:37:12.000', '');

INSERT INTO `sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (2347, '保存', 2346, 1, '', NULL, NULL, 1, 0, 'F', '0', '0', 'sys:integral:save', '#', 1, '2024-03-01 13:27:01.000', 1, '2024-03-01 13:37:26.000', '');

ALTER TABLE `oms_order`
ADD COLUMN `receiver_phone_encrypted` varchar(255) NULL COMMENT '收件人手机号密文' AFTER `update_time`;

ALTER TABLE `ums_member_address`
MODIFY COLUMN `phone_encrypted` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号密文' AFTER `phone_hidden`;

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