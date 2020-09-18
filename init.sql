create table member(
    memberId INTEGER primary key,
    username varchar(20),
    password varchar(100),
    memberLevel INTEGER
);

create table video(
    videoId INTEGER primary key,
    videoName VARCHAR(30),
    videoType INTEGER ,
    videoSize varchar(10),
    videoTimeSize varchar(10),
    path varchar(100) ,
    uploadMember varchar(20)
);

create table videoType(
    typeId INTEGER  primary key,
    typeName varchar(10) ,
    typeDetail varchar(30)
);


create table if not exists comment (
    commentId INTEGER primary key,
    memberId INTEGER  not null,
    videoId INTEGER not null,
    publisherId INTEGER ,
    createTime timestamp ,
    content text not null,
    zan INTEGER
);


ALTER TABLE member
MODIFY COLUMN memberId int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '会员号' FIRST,
MODIFY COLUMN username varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名' AFTER memberId,
MODIFY COLUMN password varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码' AFTER username,
MODIFY COLUMN memberLevel int(11) NULL DEFAULT NULL COMMENT '会员等级' AFTER password;

ALTER TABLE video
MODIFY COLUMN videoId int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '影片id' FIRST,
MODIFY COLUMN videoName varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '影片名称' AFTER videoId,
MODIFY COLUMN videoType int(11) NULL DEFAULT NULL COMMENT '影片类型' AFTER videoName,
MODIFY COLUMN videoSize varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '影片大小' AFTER videoType,
MODIFY COLUMN videoTimeSize varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '影片时长' AFTER videoSize,
MODIFY COLUMN path varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '影片路径' AFTER videoTimeSize,
MODIFY COLUMN uploadMember varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '上传者' AFTER path;

ALTER TABLE comment
MODIFY COLUMN commentId int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '评论id' FIRST,
MODIFY COLUMN memberId int(11) NOT NULL COMMENT '评论人' AFTER commentId,
MODIFY COLUMN videoId int(11) NOT NULL COMMENT '评论影片' AFTER memberId,
MODIFY COLUMN publisherId int(11) NULL DEFAULT NULL COMMENT '被评论人' AFTER videoId,
MODIFY COLUMN createTime timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '评论时间' AFTER publisherId,
MODIFY COLUMN content text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '评论内容' AFTER createTime,
MODIFY COLUMN zan int(11) NULL DEFAULT NULL COMMENT '点赞数' AFTER content;

ALTER TABLE videoType
MODIFY COLUMN typeId int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '影片类型id' FIRST,
MODIFY COLUMN typeName varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型名称' AFTER typeId,
MODIFY COLUMN typeDetail varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型简介' AFTER typeName;


create table ums_admin
(
    id                   bigint not null auto_increment,
    username             varchar(64) comment '用户名',
    password             varchar(64) comment '密码',
    icon                 varchar(500) comment '头像',
    email                varchar(100) comment '邮箱',
    nick_name            varchar(200) comment '昵称',
    note                 varchar(500) comment '备注信息',
    create_time          datetime comment '创建时间',
    login_time           datetime comment '最后登录时间',
    status               int(1) default 1 comment '帐号启用状态：0->禁用；1->启用',
    primary key (id)
);

-- ----------------------------
-- Records of ums_admin
-- ----------------------------
INSERT INTO `ums_admin` VALUES ('1', 'test', '$2a$10$NZ5o7r2E.ayT2ZoxgjlI.eJ6OEYqjH7INR/F.mXDbjZJi9HF0YCVG', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/timg.jpg', null, '测试账号', null, '2018-09-29 13:55:30', '2018-09-29 13:55:39', '1');
INSERT INTO `ums_admin` VALUES ('3', 'admin', '$2a$10$NZ5o7r2E.ayT2ZoxgjlI.eJ6OEYqjH7INR/F.mXDbjZJi9HF0YCVG', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20190129/170157_yIl3_1767531.jpg', 'admin@163.com', '系统管理员', '系统管理员', '2018-10-08 13:32:47', '2019-03-20 15:38:50', '1');



create table ums_admin_role_relation
(
    id                   bigint not null auto_increment,
    admin_id             bigint,
    role_id              bigint,
    primary key (id)
);

-- ----------------------------
-- Records of ums_admin_role_relation
-- ----------------------------
INSERT INTO `ums_admin_role_relation` VALUES ('13', '3', '1');
INSERT INTO `ums_admin_role_relation` VALUES ('15', '3', '2');
INSERT INTO `ums_admin_role_relation` VALUES ('16', '3', '4');

create table ums_menu
(
    id                   bigint not null auto_increment,
    parent_id            bigint comment '父级ID',
    create_time          datetime comment '创建时间',
    title                varchar(100) comment '菜单名称',
    level                int(4) comment '菜单级数',
    sort                 int(4) comment '菜单排序',
    name                 varchar(100) comment '前端名称',
    icon                 varchar(200) comment '前端图标',
    hidden               int(1) comment '前端隐藏',
    primary key (id)
);

create table ums_resource
(
    id                   bigint not null auto_increment,
    category_id          bigint comment '资源分类ID',
    create_time          datetime comment '创建时间',
    name                 varchar(200) comment '资源名称',
    url                  varchar(200) comment '资源URL',
    description          varchar(500) comment '描述',
    primary key (id)
);

create table ums_resource_category
(
    id                   bigint not null auto_increment,
    create_time          datetime comment '创建时间',
    name                 varchar(200) comment '分类名称',
    sort                 int(4) comment '排序',
    primary key (id)
);

create table ums_role_menu_relation
(
    id                   bigint not null auto_increment,
    role_id              bigint comment '角色ID',
    menu_id              bigint comment '菜单ID',
    primary key (id)
);

create table ums_role_resource_relation
(
    id                   bigint not null auto_increment,
    role_id              bigint comment '角色ID',
    resource_id          bigint comment '资源ID',
    primary key (id)
);

CREATE TABLE `ums_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pid` bigint(20) DEFAULT NULL COMMENT '父级权限id',
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `value` varchar(200) DEFAULT NULL COMMENT '权限值',
  `icon` varchar(500) DEFAULT NULL COMMENT '图标',
  `type` int(1) DEFAULT NULL COMMENT '权限类型：0->目录；1->菜单；2->按钮（接口绑定权限）',
  `uri` varchar(200) DEFAULT NULL COMMENT '前端资源路径',
  `status` int(1) DEFAULT NULL COMMENT '启用状态；0->禁用；1->启用',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Records of ums_permission
-- ----------------------------
INSERT INTO `ums_permission` VALUES ('1', '0', '商品', null, null, '0', null, '1', '2018-09-29 16:15:14', '0');
INSERT INTO `ums_permission` VALUES ('2', '1', '商品列表', 'pms:product:read', null, '1', '/pms/product/index', '1', '2018-09-29 16:17:01', '0');
INSERT INTO `ums_permission` VALUES ('3', '1', '添加商品', 'pms:product:create', null, '1', '/pms/product/add', '1', '2018-09-29 16:18:51', '0');
INSERT INTO `ums_permission` VALUES ('4', '1', '商品分类', 'pms:productCategory:read', null, '1', '/pms/productCate/index', '1', '2018-09-29 16:23:07', '0');
INSERT INTO `ums_permission` VALUES ('5', '1', '商品类型', 'pms:productAttribute:read', null, '1', '/pms/productAttr/index', '1', '2018-09-29 16:24:43', '0');
INSERT INTO `ums_permission` VALUES ('6', '1', '品牌管理', 'pms:brand:read', null, '1', '/pms/brand/index', '1', '2018-09-29 16:25:45', '0');
INSERT INTO `ums_permission` VALUES ('7', '2', '编辑商品', 'pms:product:update', null, '2', '/pms/product/updateProduct', '1', '2018-09-29 16:34:23', '0');
INSERT INTO `ums_permission` VALUES ('8', '2', '删除商品', 'pms:product:delete', null, '2', '/pms/product/delete', '1', '2018-09-29 16:38:33', '0');
INSERT INTO `ums_permission` VALUES ('9', '4', '添加商品分类', 'pms:productCategory:create', null, '2', '/pms/productCate/create', '1', '2018-09-29 16:43:23', '0');
INSERT INTO `ums_permission` VALUES ('10', '4', '修改商品分类', 'pms:productCategory:update', null, '2', '/pms/productCate/update', '1', '2018-09-29 16:43:55', '0');
INSERT INTO `ums_permission` VALUES ('11', '4', '删除商品分类', 'pms:productCategory:delete', null, '2', '/pms/productAttr/delete', '1', '2018-09-29 16:44:38', '0');
INSERT INTO `ums_permission` VALUES ('12', '5', '添加商品类型', 'pms:productAttribute:create', null, '2', '/pms/productAttr/create', '1', '2018-09-29 16:45:25', '0');
INSERT INTO `ums_permission` VALUES ('13', '5', '修改商品类型', 'pms:productAttribute:update', null, '2', '/pms/productAttr/update', '1', '2018-09-29 16:48:08', '0');
INSERT INTO `ums_permission` VALUES ('14', '5', '删除商品类型', 'pms:productAttribute:delete', null, '2', '/pms/productAttr/delete', '1', '2018-09-29 16:48:44', '0');
INSERT INTO `ums_permission` VALUES ('15', '6', '添加品牌', 'pms:brand:create', null, '2', '/pms/brand/add', '1', '2018-09-29 16:49:34', '0');
INSERT INTO `ums_permission` VALUES ('16', '6', '修改品牌', 'pms:brand:update', null, '2', '/pms/brand/update', '1', '2018-09-29 16:50:55', '0');
INSERT INTO `ums_permission` VALUES ('17', '6', '删除品牌', 'pms:brand:delete', null, '2', '/pms/brand/delete', '1', '2018-09-29 16:50:59', '0');
INSERT INTO `ums_permission` VALUES ('18', '0', '首页', null, null, '0', null, '1', '2018-09-29 16:51:57', '0');

CREATE TABLE `ums_role_permission_relation` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `role_id` bigint(20) DEFAULT NULL,
    `permission_id` bigint(20) DEFAULT NULL,
    PRIMARY KEY (`id`)
);

-- ----------------------------
-- Records of ums_role_permission_relation
-- ----------------------------
INSERT INTO `ums_role_permission_relation` VALUES ('1', '1', '1');
INSERT INTO `ums_role_permission_relation` VALUES ('2', '1', '2');
INSERT INTO `ums_role_permission_relation` VALUES ('3', '1', '3');
INSERT INTO `ums_role_permission_relation` VALUES ('4', '1', '7');
INSERT INTO `ums_role_permission_relation` VALUES ('5', '1', '8');
INSERT INTO `ums_role_permission_relation` VALUES ('6', '2', '4');
INSERT INTO `ums_role_permission_relation` VALUES ('7', '2', '9');
INSERT INTO `ums_role_permission_relation` VALUES ('8', '2', '10');
INSERT INTO `ums_role_permission_relation` VALUES ('9', '2', '11');
INSERT INTO `ums_role_permission_relation` VALUES ('10', '3', '5');
INSERT INTO `ums_role_permission_relation` VALUES ('11', '3', '12');
INSERT INTO `ums_role_permission_relation` VALUES ('12', '3', '13');
INSERT INTO `ums_role_permission_relation` VALUES ('13', '3', '14');
INSERT INTO `ums_role_permission_relation` VALUES ('14', '4', '6');
INSERT INTO `ums_role_permission_relation` VALUES ('15', '4', '15');
INSERT INTO `ums_role_permission_relation` VALUES ('16', '4', '16');
INSERT INTO `ums_role_permission_relation` VALUES ('17', '4', '17');

CREATE TABLE `ums_role` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `name` varchar(100) DEFAULT NULL COMMENT '名称',
    `description` varchar(500) DEFAULT NULL COMMENT '描述',
    `admin_count` int(11) DEFAULT NULL COMMENT '后台用户数量',
    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
    `status` int(1) DEFAULT '1' COMMENT '启用状态：0->禁用；1->启用',
    `sort` int(11) DEFAULT '0',
    PRIMARY KEY (`id`)
);

-- ----------------------------
-- Records of ums_role
-- ----------------------------
INSERT INTO `ums_role` VALUES ('1', '商品管理员', '商品管理员', '0', '2018-09-30 15:46:11', '1', '0');
INSERT INTO `ums_role` VALUES ('2', '商品分类管理员', '商品分类管理员', '0', '2018-09-30 15:53:45', '1', '0');
INSERT INTO `ums_role` VALUES ('3', '商品类型管理员', '商品类型管理员', '0', '2018-09-30 15:53:56', '1', '0');
INSERT INTO `ums_role` VALUES ('4', '品牌管理员', '品牌管理员', '0', '2018-09-30 15:54:12', '1', '0');

CREATE TABLE `ums_admin_permission_relation` (
     `id` bigint(20) NOT NULL AUTO_INCREMENT,
     `admin_id` bigint(20) DEFAULT NULL,
     `permission_id` bigint(20) DEFAULT NULL,
     `type` int(1) DEFAULT NULL,
     PRIMARY KEY (`id`)
);






