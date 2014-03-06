/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50096
Source Host           : localhost:3306
Source Database       : study_communication

Target Server Type    : MYSQL
Target Server Version : 50096
File Encoding         : 65001

Date: 2014-03-06 15:54:21
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL auto_increment,
  `role_name` varchar(20) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'ROLE_ADMIN');
INSERT INTO `role` VALUES ('2', 'ROLE_USER');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL auto_increment,
  `account_enabled` bit(1) default NULL,
  `account_expired` bit(1) NOT NULL,
  `account_locked` bit(1) NOT NULL,
  `credentials_expired` bit(1) NOT NULL,
  `email` varchar(255) NOT NULL,
  `nickname` varchar(255) default NULL,
  `password` varchar(255) NOT NULL,
  `reg_time` bigint(20) NOT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`),
  UNIQUE KEY `UK_sb8bbouer5wak8vyiiy4pf2bx` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '', '\0', '\0', '\0', 'admin@admin.com', 'admin', '$2a$10$gc2DYcgb/kR5UzVCxprE7OmWXfgaVsyUXWBx0jCPuk9RqNuypS0I2', '1393982669442', 'admin@admin.com');

-- ----------------------------
-- Table structure for user_enable_check
-- ----------------------------
DROP TABLE IF EXISTS `user_enable_check`;
CREATE TABLE `user_enable_check` (
  `id` int(11) NOT NULL auto_increment,
  `check_email` varchar(255) NOT NULL,
  `check_uuid` varchar(255) NOT NULL,
  `check_sign` varchar(255) NOT NULL,
  `check_expired_time` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `id` (`id`),
  KEY `uuid` (`check_uuid`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of user_enable_check
-- ----------------------------

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` int(11) NOT NULL auto_increment,
  `role_id` int(11) default NULL,
  `user_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', '2', '1');
INSERT INTO `user_role` VALUES ('2', '1', '1');
