/*
Navicat MySQL Data Transfer

Source Server         : test
Source Server Version : 50728
Source Host           : localhost:3306
Source Database       : yinhaoyan

Target Server Type    : MYSQL
Target Server Version : 50728
File Encoding         : 65001

Date: 2020-07-11 11:28:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `id` int(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('姜维', '123456', '3');
INSERT INTO `user` VALUES ('测试用户', '123456', '4');
INSERT INTO `user` VALUES ('test', '123456', '5');
INSERT INTO `user` VALUES ('刘备', '123456', '6');
INSERT INTO `user` VALUES ('诸葛亮', '123456', '7');
