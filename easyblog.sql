/*
 Navicat Premium Data Transfer

 Source Server         : easyblog
 Source Server Type    : MySQL
 Source Server Version : 80015
 Source Host           : localhost:3306
 Source Schema         : easyblog

 Target Server Type    : MySQL
 Target Server Version : 80015
 File Encoding         : 65001

 Date: 09/05/2019 19:43:54
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for hibernate_sequence
-- ----------------------------
DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence`  (
  `next_val` bigint(20) NULL DEFAULT NULL
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Fixed;

-- ----------------------------
-- Records of hibernate_sequence
-- ----------------------------
INSERT INTO `hibernate_sequence` VALUES (1);

-- ----------------------------
-- Table structure for t_sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_menu`;
CREATE TABLE `t_sys_menu`  (
  `ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单ID',
  `NAME_` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单名称',
  `PARENT_ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '父菜单ID',
  `SORT_` int(4) NULL DEFAULT 0 COMMENT '显示顺序',
  `URL_` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '#' COMMENT '请求地址',
  `TYPE_` int(1) NOT NULL DEFAULT 0 COMMENT '菜单类型（0:目录 1:菜单 2:按钮）',
  `STATE_` tinyint(1) NOT NULL DEFAULT 1 COMMENT '菜单状态：1启用、0禁用',
  `PERMISSION_` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限标识',
  `ICON_` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '#' COMMENT '菜单图标',
  `DEL_FLAG_` tinyint(1) NOT NULL DEFAULT 1 COMMENT '删除标识：1启用、0删除',
  `CREATE_BY_` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `CREATE_TIME_` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `UPDATE_BY_` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改者',
  `UPDATE_TIME_` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `REMARK_` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`ID_`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sys_menu
-- ----------------------------
INSERT INTO `t_sys_menu` VALUES ('8a0887026a48d517016a48d5535e9862', '系统管理', '0', 1, '#', 0, 1, NULL, '#', 1, 'gao', '2019-05-09 10:14:28', 'gao', '2019-05-09 10:14:33', '系统管理目录');

-- ----------------------------
-- Table structure for t_sys_role
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_role`;
CREATE TABLE `t_sys_role`  (
  `ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色ID',
  `NAME_` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  `KEY_` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色权限字符串',
  `SORT_` int(4) NOT NULL COMMENT '显示顺序',
  `STATE_` tinyint(1) NOT NULL DEFAULT 1 COMMENT '角色状态：1正常、0停用',
  `DEL_FLAG_` tinyint(1) NOT NULL DEFAULT 1 COMMENT '删除标识：1启用、0删除',
  `CREATE_BY_` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `CREATE_TIME_` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `UPDATE_BY_` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改者',
  `UPDATE_TIME_` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `REMARK_` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`ID_`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sys_role
-- ----------------------------
INSERT INTO `t_sys_role` VALUES ('057cf4fbc8f54d43b6cb960334536', '普通角色', 'common', 2, 1, 1, 'gao', '2019-05-09 09:32:36', 'gao', '2019-05-09 09:32:40', '最低权限');
INSERT INTO `t_sys_role` VALUES ('057cf4fbc8f54d43b6cb9607448efd36', '管理员', 'admin', 1, 1, 1, 'gao', '2019-05-08 17:00:25', 'gao', '2019-05-08 17:00:30', '最高权限');

-- ----------------------------
-- Table structure for t_sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_role_menu`;
CREATE TABLE `t_sys_role_menu`  (
  `ROLE_ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `MENU_ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`ROLE_ID_`, `MENU_ID_`) USING BTREE,
  INDEX `FKr5i33ph3r4hmg5eh8mto3lakn`(`MENU_ID_`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sys_role_menu
-- ----------------------------
INSERT INTO `t_sys_role_menu` VALUES ('057cf4fbc8f54d43b6cb960334536', '8a0887026a48d517016a48d5535e9862');
INSERT INTO `t_sys_role_menu` VALUES ('057cf4fbc8f54d43b6cb9607448efd36', '8a0887026a48d517016a48d5535e9862');

-- ----------------------------
-- Table structure for t_sys_user
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user`;
CREATE TABLE `t_sys_user`  (
  `ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '编号',
  `USERNAME_` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `NICKNAME_` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '昵称',
  `PASSWORD_` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `AVATAR_` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `EMAIL_` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Email',
  `STATE_` tinyint(1) NULL DEFAULT 1 COMMENT '状态：1启用、0禁用',
  `PHONE_` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `LOGIN_IP_` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '最后登陆IP',
  `LOGIN_DATE_` datetime(0) NULL DEFAULT NULL COMMENT '最后登陆日期',
  `DEL_FLAG_` tinyint(1) NOT NULL DEFAULT 1 COMMENT '删除标识：1启用、0删除',
  `CREATE_BY_` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `CREATE_TIME_` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `UPDATE_BY_` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改者',
  `UPDATE_TIME_` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `REMARK_` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`ID_`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sys_user
-- ----------------------------
INSERT INTO `t_sys_user` VALUES ('8a0887026a48d517016a48d5534a0000', 'ronaldo', '朗拿度', '$2a$10$uneM71X4Cvxr/6yQMlNf1uNKwafhFwt6hNfz3TZSChgWYvpfVVao6', NULL, '443599656@qq.com', 1, NULL, '0:0:0:0:0:0:0:1', '2019-05-09 11:28:35', 1, NULL, NULL, 'gao', '2019-05-09 11:28:35', NULL);
INSERT INTO `t_sys_user` VALUES ('8a0887026a48d517016a48d5535e0001', 'li1', '理想', '$2a$10$uneM71X4Cvxr/6yQMlNf1uNKwafhFwt6hNfz3TZSChgWYvpfVVao6', NULL, '443599657@qq.com', 1, NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_user` VALUES ('8a0887026a48d517016a48d5535e0002', 'li2', '暗巫', '$2a$10$uneM71X4Cvxr/6yQMlNf1uNKwafhFwt6hNfz3TZSChgWYvpfVVao6', NULL, '44359966756@qq.com', 1, NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_user` VALUES ('8a0887026a48d517016a48d5535e0003', 'li3', '力学', '$2a$10$uneM71X4Cvxr/6yQMlNf1uNKwafhFwt6hNfz3TZSChgWYvpfVVao6', NULL, '44679656@qq.com', 1, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_user` VALUES ('8a0887026a48d517016a48d5535e0004', 'li4', '安吧', '$2a$10$uneM71X4Cvxr/6yQMlNf1uNKwafhFwt6hNfz3TZSChgWYvpfVVao6', NULL, '44359676@qq.com', 1, NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_user` VALUES ('8a0887026a48d517016a48d5535f0005', 'li5', '南京', '$2a$10$uneM71X4Cvxr/6yQMlNf1uNKwafhFwt6hNfz3TZSChgWYvpfVVao6', NULL, '44359965676@qq.com', 1, NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_user` VALUES ('8a0887026a48d517016a48d5535f0006', 'li6', '横穴', '$2a$10$uneM71X4Cvxr/6yQMlNf1uNKwafhFwt6hNfz3TZSChgWYvpfVVao6', NULL, '44359965776@qq.com', 1, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_user` VALUES ('8a0887026a48d517016a48d5535f0007', 'li7', '很傻髚', '$2a$10$uneM71X4Cvxr/6yQMlNf1uNKwafhFwt6hNfz3TZSChgWYvpfVVao6', NULL, '44359965886@qq.com', 1, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_user` VALUES ('8a0887026a48d517016a48d5535f0008', 'li8', '郑爽', '$2a$10$uneM71X4Cvxr/6yQMlNf1uNKwafhFwt6hNfz3TZSChgWYvpfVVao6', NULL, '44359969956@qq.com', 1, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_user` VALUES ('8a0887026a48d517016a48d553600009', 'li9', 'i傲天', '$2a$10$uneM71X4Cvxr/6yQMlNf1uNKwafhFwt6hNfz3TZSChgWYvpfVVao6', NULL, '44359960056@qq.com', 1, NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_user` VALUES ('8a0887026a8611d5016a86848f250000', 'gao', '高瑞春', '$2a$10$uneM71X4Cvxr/6yQMlNf1uNKwafhFwt6hNfz3TZSChgWYvpfVVao6', NULL, '44359963@qq.com', 1, NULL, '0:0:0:0:0:0:0:1', '2019-05-09 11:24:15', 1, 'gao', '2019-05-05 05:43:15', 'gao', '2019-05-09 11:24:15', NULL);

-- ----------------------------
-- Table structure for t_sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user_role`;
CREATE TABLE `t_sys_user_role`  (
  `USER_ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户ID',
  `ROLE_ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`USER_ID_`, `ROLE_ID_`) USING BTREE,
  INDEX `FK8dyhycf73ld1gibhg4cjub4u3`(`ROLE_ID_`) USING BTREE,
  CONSTRAINT `FK8dyhycf73ld1gibhg4cjub4u3` FOREIGN KEY (`ROLE_ID_`) REFERENCES `t_sys_role` (`ID_`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKi6yvj1xhp397529ibi9ngpw49` FOREIGN KEY (`USER_ID_`) REFERENCES `t_sys_user` (`ID_`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户角色权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sys_user_role
-- ----------------------------
INSERT INTO `t_sys_user_role` VALUES ('8a0887026a48d517016a48d5534a0000', '057cf4fbc8f54d43b6cb960334536');
INSERT INTO `t_sys_user_role` VALUES ('8a0887026a48d517016a48d5535e0001', '057cf4fbc8f54d43b6cb960334536');
INSERT INTO `t_sys_user_role` VALUES ('8a0887026a8611d5016a86848f250000', '057cf4fbc8f54d43b6cb960334536');
INSERT INTO `t_sys_user_role` VALUES ('8a0887026a8611d5016a86848f250000', '057cf4fbc8f54d43b6cb9607448efd36');

SET FOREIGN_KEY_CHECKS = 1;
