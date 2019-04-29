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

 Date: 29/04/2019 18:12:05
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
-- Table structure for t_sys_user
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user`;
CREATE TABLE `t_sys_user`  (
  `ID_` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '编号',
  `USERNAME_` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `PASSWORD_` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `AVATAR_` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `EMAIL_` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Email',
  `STATE_` tinyint(1) NULL DEFAULT 1 COMMENT '状态：1启用、0禁用',
  `PHONE_` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `LOGIN_IP_` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '最后登陆IP',
  `LOGIN_DATE_` datetime(0) NULL DEFAULT NULL COMMENT '最后登陆日期',
  `DEL_FLAG_` tinyint(1) NULL DEFAULT 1 COMMENT '删除标识：1启用、0删除',
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
INSERT INTO `t_sys_user` VALUES ('8a0887026a48d517016a48d5534a0000', 'gaoruichun', '123456', NULL, NULL, 1, NULL, NULL, NULL, 1, NULL, NULL, 'gao', '2019-04-24 03:56:55', NULL);
INSERT INTO `t_sys_user` VALUES ('8a0887026a48d517016a48d5535e0001', 'li1', '123456', NULL, NULL, 1, NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_user` VALUES ('8a0887026a48d517016a48d5535e0002', 'li2', '123456', NULL, NULL, 1, NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_user` VALUES ('8a0887026a48d517016a48d5535e0003', 'li3', '123456', NULL, NULL, 1, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_user` VALUES ('8a0887026a48d517016a48d5535e0004', 'li4', '123456', NULL, NULL, 1, NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_user` VALUES ('8a0887026a48d517016a48d5535f0005', 'li5', '123456', NULL, NULL, 1, NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_user` VALUES ('8a0887026a48d517016a48d5535f0006', 'li6', '123456', NULL, NULL, 1, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_user` VALUES ('8a0887026a48d517016a48d5535f0007', 'li7', '123456', NULL, NULL, 1, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_user` VALUES ('8a0887026a48d517016a48d5535f0008', 'li8', '123456', NULL, NULL, 1, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_sys_user` VALUES ('8a0887026a48d517016a48d553600009', 'li9', '123456', NULL, NULL, 1, NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
