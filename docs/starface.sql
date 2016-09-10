/*
 Navicat Premium Data Transfer

 Source Server         : Local
 Source Server Type    : MySQL
 Source Server Version : 50163
 Source Host           : localhost
 Source Database       : starface

 Target Server Type    : MySQL
 Target Server Version : 50163
 File Encoding         : utf-8

 Date: 02/26/2015 13:25:12 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `sys_dictionary`
-- ----------------------------
DROP TABLE IF EXISTS `sys_dictionary`;
CREATE TABLE `sys_dictionary` (
  `dic_id` varchar(32) NOT NULL COMMENT '字典ID',
  `dic_name` varchar(50) DEFAULT NULL COMMENT '字典名字',
  `dic_value` varchar(300) DEFAULT NULL COMMENT '字典值',
  `dic_group` varchar(60) DEFAULT NULL COMMENT '字典分组',
  `dic_type` varchar(10) DEFAULT NULL COMMENT '字典类型',
  `dic_order` int(11) DEFAULT NULL COMMENT '字典排序',
  `dic_status` varchar(10) DEFAULT NULL COMMENT '字典状态',
  `dic_parent_id` varchar(32) DEFAULT NULL COMMENT '父节点ID',
  PRIMARY KEY (`dic_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_email_verify_code`
-- ----------------------------
DROP TABLE IF EXISTS `t_email_verify_code`;
CREATE TABLE `t_email_verify_code` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(200) DEFAULT NULL,
  `code` varchar(100) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
--  Table structure for `t_users`
-- ----------------------------
DROP TABLE IF EXISTS `t_users`;
CREATE TABLE `t_users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(200) DEFAULT NULL COMMENT '用户名',
  `pwd` varchar(200) DEFAULT NULL COMMENT 'MD5加密过的密码',
  `nick_name` varchar(200) DEFAULT NULL COMMENT '昵称',
  `create_time` datetime DEFAULT NULL COMMENT '注册时间',
  `is_stop` int(11) DEFAULT '0' COMMENT '0：正常使用，1：停用该账号',
  `code_name` varchar(100) DEFAULT NULL COMMENT '代号',
  `email_certified` int(11) DEFAULT '0' COMMENT '邮箱验证,0,未验证，1已验证',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

SET FOREIGN_KEY_CHECKS = 1;
