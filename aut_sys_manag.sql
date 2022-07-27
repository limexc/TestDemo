/*
 Navicat Premium Data Transfer

 Source Server         : 杭州阿里云
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : rm-bp1z9708mbdvq6c7mgo.mysql.rds.aliyuncs.com:3306
 Source Schema         : aut_sys_manag

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 25/07/2022 23:50:25
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for upms_menu_t
-- ----------------------------
DROP TABLE IF EXISTS `upms_menu_t`;
CREATE TABLE `upms_menu_t`  (
  `MENU_ID` int(10) NOT NULL COMMENT '主页菜单id',
  `MENU_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '主页菜单名字',
  `MENU_TYPE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单类型',
  `MENU_UPPER` int(255) NULL DEFAULT NULL COMMENT '上级菜单',
  `MENU_FLAG` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单标识',
  `MENU_URL` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单地址',
  `MENU_ICO` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单图标',
  `MENU_SERIAL` int(255) NULL DEFAULT NULL COMMENT '排序号',
  `MENU_STATUS` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态',
  `MENU_NOTE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `MENU_BACK1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `MENU_BACK2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `MENU_BACK3` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `DELETE_FLAG` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `DESCRIPTION` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `LAST_UPDATED_LOGIN` int(255) NULL DEFAULT NULL,
  `LAST_UPDATED_DATE` date NULL DEFAULT NULL,
  `LAST_UPDATED_BY` int(11) NULL DEFAULT NULL,
  `CREATED_BY` int(255) NULL DEFAULT NULL,
  `CREATION_DATE` date NULL DEFAULT NULL,
  PRIMARY KEY (`MENU_ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of upms_menu_t
-- ----------------------------
INSERT INTO `upms_menu_t` VALUES (1, '查询菜单', '1', 2, NULL, NULL, NULL, 1, 'Act', NULL, NULL, NULL, NULL, 'N', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `upms_menu_t` VALUES (2, '查询用户', '2', 1, NULL, NULL, NULL, 2, 'Act', NULL, NULL, NULL, NULL, 'N', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `upms_menu_t` VALUES (3, '新增用户', '3', 2, NULL, NULL, NULL, 3, 'Act', NULL, NULL, NULL, NULL, 'N', NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for upms_roah_t
-- ----------------------------
DROP TABLE IF EXISTS `upms_roah_t`;
CREATE TABLE `upms_roah_t`  (
  `ROAH_ID` int(11) NOT NULL COMMENT '用户权限ID',
  `ROAH_MENUID` int(255) NULL DEFAULT NULL COMMENT '权限id',
  `ROAH_ROLEID` int(255) NULL DEFAULT NULL COMMENT '角色表ID',
  `ROAH_NOTE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `DELETE_FLAG` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `DESCRIPTION` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `CREATED_BY` int(255) NULL DEFAULT NULL,
  `CREATION_DATE` date NULL DEFAULT NULL,
  `LAST_UPDATED_BY` int(11) NULL DEFAULT NULL,
  `LAST_UPDATED_DATE` date NULL DEFAULT NULL,
  `LAST_UPDATED_LOGIN` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`ROAH_ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of upms_roah_t
-- ----------------------------
INSERT INTO `upms_roah_t` VALUES (1, 1, 1, NULL, 'N', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `upms_roah_t` VALUES (2, 2, 1, NULL, 'N', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `upms_roah_t` VALUES (3, 3, 1, NULL, 'N', NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for upms_rofu_t
-- ----------------------------
DROP TABLE IF EXISTS `upms_rofu_t`;
CREATE TABLE `upms_rofu_t`  (
  `ROFU_ID` int(11) NOT NULL COMMENT '用户角色表ID',
  `ROFU_USERID` int(11) NULL DEFAULT NULL COMMENT '用户ID',
  `ROFU_ROLEID` int(11) NULL DEFAULT NULL COMMENT '权限表ID',
  `ROFU_NOTE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `DELETE_FLAG` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `CREATED_BY` int(255) NULL DEFAULT NULL,
  `CREATION_DATE` date NULL DEFAULT NULL,
  `LAST_UPDATED_BY` int(11) NULL DEFAULT NULL,
  `LAST_UPDATED_DATE` date NULL DEFAULT NULL,
  `LAST_UPDATED_LOGIN` int(11) NULL DEFAULT NULL,
  `DESCRIPTION` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`ROFU_ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of upms_rofu_t
-- ----------------------------
INSERT INTO `upms_rofu_t` VALUES (1, 1, 1, NULL, 'N', NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for upms_role_t
-- ----------------------------
DROP TABLE IF EXISTS `upms_role_t`;
CREATE TABLE `upms_role_t`  (
  `ROLE_ID` int(10) NOT NULL COMMENT '表序列ID',
  `ROLE_NO` int(10) NULL DEFAULT NULL COMMENT '角色ID',
  `ROLE_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名字',
  `ROLE_STARTTIME` date NULL DEFAULT NULL COMMENT '生效时间',
  `ROLE_STOPTIME` date NULL DEFAULT NULL COMMENT '结束时间',
  `ROLE_NOTE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `ROLE_BACK1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ROLE_STATUS` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色状态',
  `ROLE_BACK2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ROLE_BACK3` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `DELETE_FLAG` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `CREATED_BY` int(255) NULL DEFAULT NULL,
  `CREATION_DATE` date NULL DEFAULT NULL,
  `LAST_UPDATED_BY` int(11) NULL DEFAULT NULL,
  `LAST_UPDATED_DATE` date NULL DEFAULT NULL,
  `LAST_UPDATED_LOGIN` int(11) NULL DEFAULT NULL,
  `DESCRIPTION` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`ROLE_ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of upms_role_t
-- ----------------------------
INSERT INTO `upms_role_t` VALUES (1, 1, '1212', '2022-07-18', '2022-08-26', NULL, NULL, 'Act', NULL, NULL, 'N', NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for upms_user_t
-- ----------------------------
DROP TABLE IF EXISTS `upms_user_t`;
CREATE TABLE `upms_user_t`  (
  `USER_ID` int(10) NULL DEFAULT NULL COMMENT '用户ID',
  `USER_ALIAS` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `USER_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `USER_PASSWD` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账户密码',
  `USER_TEL` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  `USER_EMAIL` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `USER_GENDER` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `USER_STATUS` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态',
  `USER_NOTE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `USER_BACK1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `USER_BACK2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `USER_BACK3` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `DELETE_FLAG` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `CREATED_BY` int(255) NULL DEFAULT NULL,
  `CREATION_DATE` date NULL DEFAULT NULL,
  `LAST_UPDATED_BY` int(11) NULL DEFAULT NULL,
  `LAST_UPDATED_DATE` date NULL DEFAULT NULL,
  `LAST_UPDATED_LOGIN` int(11) NULL DEFAULT NULL,
  `DESCRIPTION` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of upms_user_t
-- ----------------------------
INSERT INTO `upms_user_t` VALUES (1, '1', '1', 'a01610228fe998f515a72dd730294d87', '1212', '1212', '1212', 'Act', NULL, NULL, NULL, NULL, 'N', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `upms_user_t` VALUES (2, '222', '222', 'a01610228fe998f515a72dd730294d87', '', '', '男', 'Act', '', NULL, NULL, NULL, 'N', NULL, '2022-07-25', NULL, '2022-07-25', NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
