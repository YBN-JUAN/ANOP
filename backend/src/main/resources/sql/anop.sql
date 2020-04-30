/*
 Navicat Premium Data Transfer

 Source Server         : MySQL
 Source Server Type    : MySQL
 Source Server Version : 80015
 Source Host           : localhost:3306
 Source Schema         : anop

 Target Server Type    : MySQL
 Target Server Version : 80015
 File Encoding         : 65001

 Date: 30/04/2020 15:24:15
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for noti_group
-- ----------------------------
DROP TABLE IF EXISTS `noti_group`;
CREATE TABLE `noti_group`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `title` varchar(31) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `creation_date` datetime(0) NOT NULL,
  `permission` tinyint(4) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_user_id`(`user_id`) USING BTREE,
  CONSTRAINT `fk_user_noti_group` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of noti_group
-- ----------------------------
INSERT INTO `noti_group` VALUES (1, 1, 'test', '575875', '2020-04-06 15:07:25', 0);
INSERT INTO `noti_group` VALUES (4, 1, '47', 'Aaa按视aaa', '2020-04-15 13:30:54', 0);
INSERT INTO `noti_group` VALUES (5, 1, '共同', '131趣味请问3', '2020-04-15 13:31:41', 2);
INSERT INTO `noti_group` VALUES (6, 1, '2无情', '1趣味请问313', '2020-04-15 13:33:36', 1);
INSERT INTO `noti_group` VALUES (7, 1, '拒绝', '趣味无穷', '2020-04-15 13:39:07', 2);
INSERT INTO `noti_group` VALUES (8, 2, '213速度', '撒打算', '2020-04-15 22:14:18', 2);
INSERT INTO `noti_group` VALUES (9, 1, ' ', '1212', '2020-04-15 16:13:15', 1);
INSERT INTO `noti_group` VALUES (10, 1, ' ', '1212', '2020-04-15 16:15:26', 0);
INSERT INTO `noti_group` VALUES (14, 1, ' ', '52强强强强强强强强强强强强强强782', '2020-04-16 18:36:23', 0);
INSERT INTO `noti_group` VALUES (15, 2, '', '-1', '2020-04-18 01:14:38', 2);

-- ----------------------------
-- Table structure for noti_group_user
-- ----------------------------
DROP TABLE IF EXISTS `noti_group_user`;
CREATE TABLE `noti_group_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `group_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `is_admin` tinyint(4) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_group_id`(`group_id`) USING BTREE,
  INDEX `idx_user_id`(`user_id`) USING BTREE,
  CONSTRAINT `fk_noti_group_noti_group_user` FOREIGN KEY (`group_id`) REFERENCES `noti_group` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `fk_user_noti_group_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of noti_group_user
-- ----------------------------
INSERT INTO `noti_group_user` VALUES (1, 1, 2, 1);
INSERT INTO `noti_group_user` VALUES (5, 8, 3, 0);
INSERT INTO `noti_group_user` VALUES (10, 8, 1, 0);
INSERT INTO `noti_group_user` VALUES (11, 14, 3, 0);
INSERT INTO `noti_group_user` VALUES (12, 8, 4, 0);
INSERT INTO `noti_group_user` VALUES (15, 1, 3, 0);
INSERT INTO `noti_group_user` VALUES (16, 14, 4, 0);
INSERT INTO `noti_group_user` VALUES (27, 9, 2, 0);

-- ----------------------------
-- Table structure for noti_receiver
-- ----------------------------
DROP TABLE IF EXISTS `noti_receiver`;
CREATE TABLE `noti_receiver`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `notification_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `is_read` tinyint(4) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_notification_id`(`notification_id`) USING BTREE,
  INDEX `idx_user_id`(`user_id`) USING BTREE,
  CONSTRAINT `fk_noti_group_user_noti_receiver` FOREIGN KEY (`user_id`) REFERENCES `noti_group_user` (`user_id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `fk_notification_noti_receiver` FOREIGN KEY (`notification_id`) REFERENCES `notification` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of noti_receiver
-- ----------------------------
INSERT INTO `noti_receiver` VALUES (1, 6, 1, 1);
INSERT INTO `noti_receiver` VALUES (2, 6, 4, 0);
INSERT INTO `noti_receiver` VALUES (4, 7, 1, 1);

-- ----------------------------
-- Table structure for noti_user_request
-- ----------------------------
DROP TABLE IF EXISTS `noti_user_request`;
CREATE TABLE `noti_user_request`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `group_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `is_accepted` tinyint(4) NOT NULL DEFAULT 0,
  `request_time` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_group_id`(`group_id`) USING BTREE,
  INDEX `idx_user_id`(`user_id`) USING BTREE,
  CONSTRAINT `fk_noti_group_noti_user_request` FOREIGN KEY (`group_id`) REFERENCES `noti_group` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `fk_user_noti_user_request` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of noti_user_request
-- ----------------------------
INSERT INTO `noti_user_request` VALUES (1, 1, 3, 0, '2020-04-08 16:26:58');
INSERT INTO `noti_user_request` VALUES (2, 1, 4, 0, '2020-04-08 16:41:59');
INSERT INTO `noti_user_request` VALUES (3, 8, 2, 0, '2020-04-16 21:28:00');
INSERT INTO `noti_user_request` VALUES (6, 8, 3, 0, '2020-04-17 23:17:49');
INSERT INTO `noti_user_request` VALUES (9, 9, 3, 1, '2020-04-17 00:44:52');
INSERT INTO `noti_user_request` VALUES (10, 9, 4, 0, '2020-04-08 00:44:55');
INSERT INTO `noti_user_request` VALUES (11, 10, 2, 1, '2020-04-22 22:45:30');
INSERT INTO `noti_user_request` VALUES (12, 14, 2, 0, '2020-04-22 22:44:25');

-- ----------------------------
-- Table structure for notification
-- ----------------------------
DROP TABLE IF EXISTS `notification`;
CREATE TABLE `notification`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `group_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `title` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `creation_date` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_group_id`(`group_id`) USING BTREE,
  INDEX `idx_user_id`(`user_id`) USING BTREE,
  CONSTRAINT `fk_noti_group_notification` FOREIGN KEY (`group_id`) REFERENCES `noti_group` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `fk_user_notification` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of notification
-- ----------------------------
INSERT INTO `notification` VALUES (1, 1, 1, 'rdfdfsds啊飒飒', 'qweertqweqweqweqweqweqweqwewqe', '2020-04-19 01:24:39');
INSERT INTO `notification` VALUES (2, 14, 1, '25', '撒大大', '2020-04-22 00:56:59');
INSERT INTO `notification` VALUES (4, 8, 1, '5626', '48', '2020-04-19 01:05:48');
INSERT INTO `notification` VALUES (6, 8, 2, '123', '1234567', '2020-04-19 01:15:34');
INSERT INTO `notification` VALUES (7, 8, 2, '1222222222222222222223', '1234567', '2020-04-19 01:18:04');
INSERT INTO `notification` VALUES (8, 8, 2, '1222222222222222222223', '1234567', '2020-04-19 01:18:36');
INSERT INTO `notification` VALUES (9, 8, 2, '1222222222222222222223', '1234567', '2020-04-19 01:18:53');
INSERT INTO `notification` VALUES (11, 1, 1, '77', '47', '2020-04-09 11:41:49');
INSERT INTO `notification` VALUES (12, 8, 2, '54', '45', '2020-04-02 11:42:07');
INSERT INTO `notification` VALUES (13, 9, 1, '5785', '7587', '2020-04-17 17:09:16');
INSERT INTO `notification` VALUES (14, 14, 1, '24524', '45254', '2020-04-14 17:10:32');
INSERT INTO `notification` VALUES (16, 9, 2, '578578', '78578', '2020-04-02 00:47:50');
INSERT INTO `notification` VALUES (17, 9, 1, '578578', '587578', '2020-04-17 13:51:54');
INSERT INTO `notification` VALUES (18, 9, 1, '68689', '98689', '2020-04-17 13:53:47');

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_permission_id`(`permission_id`) USING BTREE,
  INDEX `idx_role_id`(`role_id`) USING BTREE,
  CONSTRAINT `fk_sys_permission_sys_role_permission` FOREIGN KEY (`permission_id`) REFERENCES `sys_permission` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `fk_sys_role_sys_role_permission` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `pid` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES (1, '/user', 'user', NULL, 0);
INSERT INTO `sys_permission` VALUES (2, '/resource', 'resource', NULL, 0);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_role_id`(`role_id`) USING BTREE,
  INDEX `idx_user_id`(`user_id`) USING BTREE,
  CONSTRAINT `fk_sys_role_sys_role_user` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `fk_user_sys_role_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for todo
-- ----------------------------
DROP TABLE IF EXISTS `todo`;
CREATE TABLE `todo`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `title` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `begin_date` datetime(0) NULL DEFAULT NULL,
  `end_date` datetime(0) NULL DEFAULT NULL,
  `remind_date` datetime(0) NULL DEFAULT NULL,
  `category_id` int(11) NULL DEFAULT NULL,
  `is_completed` tinyint(4) NOT NULL DEFAULT 0,
  `is_important` tinyint(4) NOT NULL DEFAULT 0,
  `is_favorite` tinyint(4) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_category_id`(`category_id`) USING BTREE,
  INDEX `idx_user_id`(`user_id`) USING BTREE,
  CONSTRAINT `fk_todo_category_todo` FOREIGN KEY (`category_id`) REFERENCES `todo_category` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `fk_user_todo` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of todo
-- ----------------------------
INSERT INTO `todo` VALUES (1, 1, '我的待办', '阿道夫就安分的链接克隆。。。。', '2020-04-01 19:00:00', NULL, '2020-04-30 19:00:00', 1, 0, 1, 1);
INSERT INTO `todo` VALUES (2, 2, 'rdfdfsds啊飒飒', 'qweertqweqweqweqweqweqweqwewqe', NULL, NULL, NULL, 3, 0, 1, 0);
INSERT INTO `todo` VALUES (3, 1, '阿拉山口附件', '打发打发', '2020-04-29 16:25:52', '2021-01-07 16:25:56', '2020-04-30 16:26:01', 1, 1, 1, 1);
INSERT INTO `todo` VALUES (4, 3, '阿斯顿法定', '暗室逢灯', NULL, '2020-09-30 16:27:37', NULL, 5, 0, 0, 0);
INSERT INTO `todo` VALUES (5, 4, '的方法的地方 ', '爱的发声', '2020-04-24 16:28:52', '2020-04-26 16:28:59', NULL, NULL, 0, 1, 1);
INSERT INTO `todo` VALUES (6, 1, '地方阿弟', '啊打发打发', '2020-04-28 16:32:05', '2021-01-29 16:32:07', '2020-04-30 16:32:57', NULL, 0, 0, 0);
INSERT INTO `todo` VALUES (7, 1, '发士大夫', '烦烦烦的', '2020-04-27 16:33:27', '2020-09-30 16:33:32', '2020-04-30 16:33:35', 2, 0, 0, 1);
INSERT INTO `todo` VALUES (8, 2, '打发打发', '发达发达地方', '2020-04-28 16:35:36', '2020-09-30 16:35:39', NULL, NULL, 0, 0, 0);
INSERT INTO `todo` VALUES (9, 1, '啊士大夫士大夫', '反对反对反对法', NULL, NULL, NULL, 6, 1, 0, 0);
INSERT INTO `todo` VALUES (10, 1, '啊打发士大夫', '对方对方的', NULL, '2020-04-20 18:33:11', NULL, 6, 0, 0, 0);
INSERT INTO `todo` VALUES (11, 4, '打发十分', '反对反对反对法', '2020-04-16 20:14:33', '2020-09-30 20:14:39', '2020-04-29 20:17:45', NULL, 0, 0, 0);

-- ----------------------------
-- Table structure for todo_category
-- ----------------------------
DROP TABLE IF EXISTS `todo_category`;
CREATE TABLE `todo_category`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `type_name` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_user_id`(`user_id`) USING BTREE,
  CONSTRAINT `fk_user_todo_category` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 36 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of todo_category
-- ----------------------------
INSERT INTO `todo_category` VALUES (1, 1, '啊打发打发');
INSERT INTO `todo_category` VALUES (2, 1, '阿迪斯发士大夫');
INSERT INTO `todo_category` VALUES (3, 2, '啊书法大赛得分');
INSERT INTO `todo_category` VALUES (4, 2, '阿斯顿发生');
INSERT INTO `todo_category` VALUES (5, 3, '爱的发声');
INSERT INTO `todo_category` VALUES (6, 1, '啊打发十分的');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(31) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `status` tinyint(4) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_name`(`username`) USING BTREE,
  UNIQUE INDEX `idx_email`(`email`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '1822467@qq.com', 'e10adc3949ba59abbe56e057f20f883e', 0);
INSERT INTO `user` VALUES (2, 'user', '23421923@qq.com', 'e10adc3949ba59abbe56e057f20f883e', 0);
INSERT INTO `user` VALUES (3, 'testUser', '1238498', 'e10adc3949ba59abbe56e057f20f883e', 0);
INSERT INTO `user` VALUES (4, '256', '213123', 'e10adc3949ba59abbe56e057f20f883e', 0);
INSERT INTO `user` VALUES (5, 'szf', 'shenzhifeng@qq.com', 'e10adc3949ba59abbe56e057f20f883e', 0);
INSERT INTO `user` VALUES (6, 'zyf', 'zhangyifan@qq.com', 'e10adc3949ba59abbe56e057f20f883e', 0);
INSERT INTO `user` VALUES (7, 'zwn', 'zhaoweinan@qq.com', 'e10adc3949ba59abbe56e057f20f883e', 0);

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `nickname` varchar(31) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `creation_time` datetime(0) NOT NULL,
  `avatar_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_user_id`(`user_id`) USING BTREE,
  CONSTRAINT `fk_user_user_info` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES (1, 1, 'zhangsan', '2020-04-07 18:57:08', 'http://img3.imgtn.bdimg.com/it/u=3369795427,2144448024&fm=26&gp=0.jpg');
INSERT INTO `user_info` VALUES (2, 2, 'lisi', '2020-04-01 22:23:36', 'http://img4.imgtn.bdimg.com/it/u=3487667129,3518265855&fm=26&gp=0.jpg');
INSERT INTO `user_info` VALUES (3, 3, '哈哈哈', '2020-04-16 16:26:26', 'http://img0.imgtn.bdimg.com/it/u=401822349,3898727221&fm=26&gp=0.jpg');
INSERT INTO `user_info` VALUES (4, 4, 'sdsadsad', '2020-04-09 16:41:17', 'http://img3.imgtn.bdimg.com/it/u=2800650412,1925088579&fm=26&gp=0.jpg');
INSERT INTO `user_info` VALUES (5, 5, 'shenzhifeng', '2020-04-28 16:38:38', '');
INSERT INTO `user_info` VALUES (6, 6, 'zhangyifan', '2020-04-28 16:38:38', '');
INSERT INTO `user_info` VALUES (7, 7, 'zhaoweinan', '2020-04-28 16:38:38', '');

-- ----------------------------
-- Table structure for valid_email
-- ----------------------------
DROP TABLE IF EXISTS `valid_email`;
CREATE TABLE `valid_email`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `code` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `expire` datetime(0) NOT NULL,
  `is_valid` tinyint(4) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_email`(`email`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of valid_email
-- ----------------------------
INSERT INTO `valid_email` VALUES (1, '1822467@qq.com', 'ABCDE', '2020-04-27 10:10:00', 1);
INSERT INTO `valid_email` VALUES (2, '23421923@qq.com', 'EDCBA', '2020-04-27 10:10:00', 1);
INSERT INTO `valid_email` VALUES (3, '1238498', '12345', '2020-04-27 10:10:00', 1);
INSERT INTO `valid_email` VALUES (4, '213123', '54321', '2020-04-27 10:10:00', 1);
INSERT INTO `valid_email` VALUES (5, 'shenzhifeng@qq.com', 'A1B2C', '2020-04-27 23:59:59', 1);
INSERT INTO `valid_email` VALUES (6, 'zhangyifan@qq.com', 'B2C3D', '2020-04-27 23:59:59', 1);
INSERT INTO `valid_email` VALUES (7, 'zhaoweinan@qq.com', 'C3D4E', '2020-04-27 23:59:59', 1);
INSERT INTO `valid_email` VALUES (8, 'test1@qq.com', 'ABCDE', '2020-05-30 23:59:59', 0);
INSERT INTO `valid_email` VALUES (9, 'test2@qq.com', '12345', '2020-01-01 23:59:59', 0);

SET FOREIGN_KEY_CHECKS = 1;
