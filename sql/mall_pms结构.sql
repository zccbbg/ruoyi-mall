-- ----------------------------
-- Table structure for pms_brand
-- ----------------------------
DROP TABLE IF EXISTS `pms_brand`;
CREATE TABLE `pms_brand`
(
    `id`             bigint(20) NOT NULL AUTO_INCREMENT,
    `name`           varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `sort`           int(11) NULL DEFAULT NULL,
    `show_status`    int(1) NULL DEFAULT NULL,
    `logo`           varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '品牌logo',
    `create_by` bigint(20) DEFAULT NULL COMMENT '创建人',
    `create_time` datetime(3) DEFAULT NULL COMMENT '创建时间',
    `update_by` bigint(20) DEFAULT NULL COMMENT '修改人',
    `update_time` datetime(3) DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 59 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '品牌管理' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for pms_product
-- ----------------------------
DROP TABLE IF EXISTS `pms_product`;
CREATE TABLE `pms_product`
(
    `id`                            bigint(20) NOT NULL AUTO_INCREMENT,
    `product_snapshot_id`           bigint(20) NULL DEFAULT NULL,
    `brand_id`                      bigint(20) NULL DEFAULT NULL,
    `category_id`                   bigint(20) NULL DEFAULT NULL,
    `out_product_id`                varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品编码',
    `name`                          varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `pic`                           varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '主图',
    `album_pics`                    varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '画册图片，连产品图片限制为5张，以逗号分割',
    `publish_status`                int(1) NULL DEFAULT NULL COMMENT '上架状态：0->下架；1->上架',
    `sort`                          int(11) NULL DEFAULT NULL COMMENT '排序',
    `price`                         decimal(10, 2) NULL DEFAULT NULL,
    `unit`                          varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '单位',
    `weight`                        decimal(10, 2) NULL DEFAULT NULL COMMENT '商品重量，默认为克',
    `product_attr`         varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品销售属性，json格式',
    `detail_html`                   text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '产品详情网页内容',
    `detail_mobile_html`            text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '移动端网页详情',
    `brand_name`                    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '品牌名称',
    `product_category_name`         varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品分类名称',
    `create_by` bigint(20) DEFAULT NULL COMMENT '创建人',
    `create_time` datetime(3) DEFAULT NULL COMMENT '创建时间',
    `update_by` bigint(20) DEFAULT NULL COMMENT '修改人',
    `update_time` datetime(3) DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 37 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '商品信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for pms_product_category
-- ----------------------------
DROP TABLE IF EXISTS `pms_product_category`;
CREATE TABLE `pms_product_category`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT,
    `parent_id`     bigint(20) NULL DEFAULT NULL COMMENT '上级分类的编号：0表示一级分类',
    `name`          varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `level`         int(1) NULL DEFAULT NULL COMMENT '分类级别：0->1级；1->2级',
    `show_status`   int(1) NULL DEFAULT NULL COMMENT '显示状态：0->不显示；1->显示',
    `sort`          int(11) NULL DEFAULT NULL,
    `icon`          varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图标',
    `create_by` bigint(20) DEFAULT NULL COMMENT '创建人',
    `create_time` datetime(3) DEFAULT NULL COMMENT '创建时间',
    `update_by` bigint(20) DEFAULT NULL COMMENT '修改人',
    `update_time` datetime(3) DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 53 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '商品分类' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for pms_sku
-- ----------------------------
DROP TABLE IF EXISTS `pms_sku`;
CREATE TABLE `pms_sku`
(
    `id`              bigint(20) NOT NULL AUTO_INCREMENT,
    `sku_snapshot_id`      bigint(20) NULL DEFAULT NULL,
    `product_id`      bigint(20) NULL DEFAULT NULL,
    `out_sku_id`      varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'sku编码',
    `price`           decimal(10, 2) NOT NULL,
    `pic`             varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '展示图片',
    `sp_data`         varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品销售属性，json格式',
    `create_by` bigint(20) DEFAULT NULL COMMENT '创建人',
    `create_time` datetime(3) DEFAULT NULL COMMENT '创建时间',
    `update_by` bigint(20) DEFAULT NULL COMMENT '修改人',
    `update_time` datetime(3) DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 179 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'sku信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for pms_product
-- ----------------------------
DROP TABLE IF EXISTS `pms_product_snapshot`;
CREATE TABLE `pms_product_snapshot`
(
    `id`                            bigint(20) NOT NULL AUTO_INCREMENT,
    `product_id`                      bigint(20) NULL DEFAULT NULL,
    `brand_id`                      bigint(20) NULL DEFAULT NULL,
    `category_id`                   bigint(20) NULL DEFAULT NULL,
    `out_product_id`                varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品编码',
    `name`                          varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `pic`                           varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '主图',
    `album_pics`                    varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '画册图片，连产品图片限制为5张，以逗号分割',
    `publish_status`                int(1) NULL DEFAULT NULL COMMENT '上架状态：0->下架；1->上架',
    `sort`                          int(11) NULL DEFAULT NULL COMMENT '排序',
    `price`                         decimal(10, 2) NULL DEFAULT NULL,
    `unit`                          varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '单位',
    `weight`                        decimal(10, 2) NULL DEFAULT NULL COMMENT '商品重量，默认为克',
    `product_attr`         varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品销售属性，json格式',
    `detail_html`                   text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '产品详情网页内容',
    `detail_mobile_html`            text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '移动端网页详情',
    `brand_name`                    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '品牌名称',
    `product_category_name`         varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品分类名称',
    `create_by` bigint(20) DEFAULT NULL COMMENT '创建人',
    `create_time` datetime(3) DEFAULT NULL COMMENT '创建时间',
    `update_by` bigint(20) DEFAULT NULL COMMENT '修改人',
    `update_time` datetime(3) DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 37 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '商品信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for pms_sku
-- ----------------------------
DROP TABLE IF EXISTS `pms_sku_snapshot`;
CREATE TABLE `pms_sku_snapshot`
(
    `id`              bigint(20) NOT NULL AUTO_INCREMENT,
    `sku_id`      bigint(20) NULL DEFAULT NULL,
    `product_id`      bigint(20) NULL DEFAULT NULL,
    `out_sku_id`      varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'sku编码',
    `price`           decimal(10, 2) NOT NULL,
    `pic`             varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '展示图片',
    `sp_data`         varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品销售属性，json格式',
    `create_by` bigint(20) DEFAULT NULL COMMENT '创建人',
    `create_time` datetime(3) DEFAULT NULL COMMENT '创建时间',
    `update_by` bigint(20) DEFAULT NULL COMMENT '修改人',
    `update_time` datetime(3) DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 179 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'sku信息' ROW_FORMAT = Dynamic;
