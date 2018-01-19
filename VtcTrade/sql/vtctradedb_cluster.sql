/*
Navicat MySQL Data Transfer

Source Server         : iamp
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : vtctradedb_cluster

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-10-12 17:29:46
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for cluster_user
-- ----------------------------
DROP TABLE IF EXISTS `cluster_user`;
CREATE TABLE `cluster_user` (
  `c_id` int(11) NOT NULL,
  `c_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`c_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of cluster_user
-- ----------------------------
INSERT INTO `cluster_user` VALUES ('1', 'kkk_qqq');
