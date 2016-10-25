/*
Navicat MySQL Data Transfer

Source Server         : 57
Source Server Version : 50714
Source Host           : localhost:3306
Source Database       : jeecg-p3

Target Server Type    : MYSQL
Target Server Version : 50714
File Encoding         : 65001

Date: 2016-10-25 08:24:20
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `cms_ad`
-- ----------------------------
DROP TABLE IF EXISTS `cms_ad`;
CREATE TABLE `cms_ad` (
  `id` varchar(36) NOT NULL,
  `create_name` varchar(50) DEFAULT NULL COMMENT 'createName',
  `create_by` varchar(50) DEFAULT NULL COMMENT 'createBy',
  `create_date` datetime DEFAULT NULL COMMENT 'createDate',
  `title` varchar(20) DEFAULT NULL COMMENT '标题',
  `image_href` varchar(255) DEFAULT NULL COMMENT '图片地址',
  `image_name` varchar(255) DEFAULT NULL COMMENT '图片名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cms_ad
-- ----------------------------
INSERT INTO `cms_ad` VALUES ('2E74717E30214B748A656263FBE49AB6', null, null, null, '111111', '2008822103119443_21466503870834.jpg', '2222222');
INSERT INTO `cms_ad` VALUES ('45952DE6A5ED487D8B61561844C33F02', null, '企业微信', '2016-06-18 10:23:22', '222222222222222', '138405284445531466503879084.png', '222222222222222');

-- ----------------------------
-- Table structure for `cms_article`
-- ----------------------------
DROP TABLE IF EXISTS `cms_article`;
CREATE TABLE `cms_article` (
  `id` varchar(36) NOT NULL,
  `title` varchar(50) DEFAULT NULL COMMENT '标题',
  `image_href` varchar(255) DEFAULT NULL COMMENT '图片地址',
  `summary` varchar(255) DEFAULT NULL COMMENT 'summary',
  `content` varchar(20000) DEFAULT NULL COMMENT '内容',
  `column_id` varchar(36) DEFAULT NULL COMMENT '栏目id',
  `create_name` varchar(255) DEFAULT NULL COMMENT '创建人',
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建人id',
  `create_date` datetime DEFAULT NULL COMMENT '创建日期',
  `publish` varchar(32) DEFAULT NULL COMMENT '是否发布',
  `PUBLISH_DATE` datetime DEFAULT NULL COMMENT '发布时间',
  `AUTHOR` varchar(32) DEFAULT NULL COMMENT '作者',
  `LABEL` varchar(200) DEFAULT NULL COMMENT '标签',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cms_article
-- ----------------------------
INSERT INTO `cms_article` VALUES ('51FA503CDC5D45E29BE38B8030196F87', '新闻一', '2008822103119443_21466585473920.jpg', null, '<p><img src=\"http://localhost/jeecg-p3-web/upload/ueditor/20160628/72431467092830594.gif\" title=\"1348502811_1337.gif\"/></p>', 'A01', null, null, null, 'Y', '2016-06-15 00:00:00', 'jeecg', 'jeecg');
INSERT INTO `cms_article` VALUES ('CC25D79A0A864CB49BBD017D82680087', '3224', '', null, '<p>41341344141<br/></p>', 'A03A01', null, null, '2016-10-06 16:58:28', 'Y', '2016-10-24 00:00:00', '31434', '4141');
INSERT INTO `cms_article` VALUES ('F4D5439F54094254A6694E9E39494F68', '新闻二', '70g58PICeGn1466585486280.jpg', null, '<h1 style=\"margin: 0px 0px 6px; padding: 16px 0px 8px; font-size: 22px; word-wrap: break-word; font-stretch: normal; line-height: 30px; font-family: 宋体, arial; text-align: center; color: rgb(0, 51, 102); border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: rgb(229, 229, 229); white-space: normal;\">骑士夺冠赌城疯玩 正点辣妹左拥右揽(组图)</h1><p><span id=\"pubtime_baidu\">发布时间：2016-06-21 18:10:15</span>&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;<span id=\"source_baidu\">来源：<a href=\"http://news.k618.cn/society/pic/201606/t20160621_7799242.htm\" target=\"_blank\" style=\"color: rgb(51, 51, 51); text-decoration: none;\">未来网</a></span>&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;<span id=\"author_baidu\">作者：佚名</span>&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;<span id=\"editor_baidu\">责任编辑：相涛</span></p><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 8px 0px; word-wrap: break-word; line-height: 23px; text-align: center;\"><img alt=\"骑士夺冠赌城疯玩\" src=\"http://jiangsu.china.com.cn/uploadfile/2016/0621/1466503684642411.jpg\" title=\"点击图片翻到下一页&gt;&gt;\" style=\"border: none; vertical-align: middle; max-width: 550px; cursor: pointer;\"/></p><p><br/></p>', 'A02', null, null, null, 'Y', '2016-06-15 00:00:00', 'jeewx', 'jeewx');

-- ----------------------------
-- Table structure for `cms_menu`
-- ----------------------------
DROP TABLE IF EXISTS `cms_menu`;
CREATE TABLE `cms_menu` (
  `ID` varchar(255) NOT NULL,
  `CREATE_BY` varchar(255) DEFAULT NULL,
  `CREATE_DATE` datetime DEFAULT NULL,
  `CREATE_NAME` varchar(255) DEFAULT NULL,
  `IMAGE_HREF` varchar(255) DEFAULT NULL COMMENT '图片地址',
  `IMAGE_NAME` varchar(255) DEFAULT NULL COMMENT '图片名称',
  `NAME` varchar(20) DEFAULT NULL COMMENT '栏目名称',
  `TYPE` varchar(20) DEFAULT NULL COMMENT '类型',
  `PARENT_CODE` varchar(255) DEFAULT NULL COMMENT '父节点',
  `HREF` varchar(100) DEFAULT NULL COMMENT '地址',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cms_menu
-- ----------------------------
INSERT INTO `cms_menu` VALUES ('A01', null, null, null, '70g58PICeGn1466573738755.jpg', null, '公告栏目', 'link', '', null);
INSERT INTO `cms_menu` VALUES ('A01A01', null, '2016-10-05 06:51:54', null, '', null, 'q', 'link', 'A01', null);
INSERT INTO `cms_menu` VALUES ('A03', '企业微信', '2016-06-21 18:12:27', null, '2008822103119443_21466573757981.jpg', null, '休闲栏目', 'link', '', null);
INSERT INTO `cms_menu` VALUES ('A03A01', null, '2016-09-22 15:16:47', null, '', null, '子栏目', 'link', 'A03', null);
INSERT INTO `cms_menu` VALUES ('A04', null, '2016-10-05 07:17:33', null, null, null, '电子产品', null, null, null);

-- ----------------------------
-- Table structure for `cms_site`
-- ----------------------------
DROP TABLE IF EXISTS `cms_site`;
CREATE TABLE `cms_site` (
  `ID` varchar(36) NOT NULL,
  `UPDATE_NAME` varchar(50) DEFAULT NULL,
  `UPDATE_DATE` datetime DEFAULT NULL,
  `CREATE_DATE` datetime DEFAULT NULL,
  `CREATE_NAME` varchar(50) DEFAULT NULL,
  `COMPANY_TEL` varchar(50) DEFAULT NULL COMMENT '公司电话',
  `SITE_LOGO` varchar(200) DEFAULT NULL COMMENT '站点Logo',
  `SITE_NAME` varchar(100) DEFAULT NULL COMMENT '站点名称',
  `SITE_TEMPLATE_STYLE` varchar(50) DEFAULT NULL COMMENT '站点模板',
  `SITE_PATH` varchar(50) DEFAULT NULL COMMENT '站点地址',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='站点';

-- ----------------------------
-- Records of cms_site
-- ----------------------------
INSERT INTO `cms_site` VALUES ('8AE3E507F312439A9B077DB6CA0E345C', null, null, '2016-06-19 23:14:22', null, '01000000000', 'LOGO-mini1466994393835.jpg', 'JEECG官网', '无', '/jeecg-p3-web');

-- ----------------------------
-- Table structure for `cms_style`
-- ----------------------------
DROP TABLE IF EXISTS `cms_style`;
CREATE TABLE `cms_style` (
  `ID` varchar(36) NOT NULL,
  `CREATE_DATE` datetime DEFAULT NULL,
  `CREATE_NAME` varchar(50) DEFAULT NULL,
  `UPDATE_DATE` datetime DEFAULT NULL,
  `UPDATE_NAME` varchar(50) DEFAULT NULL,
  `TEMPLATE_NAME` varchar(100) DEFAULT NULL COMMENT '模板名称',
  `REVIEW_IMG_URL` varchar(100) DEFAULT NULL COMMENT '预览图',
  `TEMPLATE_URL` varchar(200) DEFAULT NULL COMMENT '模板地址',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='站点模板';

-- ----------------------------
-- Records of cms_style
-- ----------------------------
INSERT INTO `cms_style` VALUES ('DC4742A4C41248B5ACA21592628EBC1E', '2016-10-04 20:29:53', null, null, null, 'd ', 'ds ', 'D ');

-- ----------------------------
-- Table structure for `erp_category`
-- ----------------------------
DROP TABLE IF EXISTS `erp_category`;
CREATE TABLE `erp_category` (
  `ID` varchar(36) NOT NULL,
  `NAME` varchar(255) DEFAULT NULL COMMENT '类型名称',
  `PARENT_CODE` varchar(36) DEFAULT NULL COMMENT '父节点',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of erp_category
-- ----------------------------
INSERT INTO `erp_category` VALUES ('A01', '电子产品', null);
INSERT INTO `erp_category` VALUES ('A01A01', '计算器', 'A01');
INSERT INTO `erp_category` VALUES ('A02', '电子器材', null);
INSERT INTO `erp_category` VALUES ('A02A01', '硬盘', 'A02');

-- ----------------------------
-- Table structure for `erp_code`
-- ----------------------------
DROP TABLE IF EXISTS `erp_code`;
CREATE TABLE `erp_code` (
  `ID` bigint(255) NOT NULL AUTO_INCREMENT,
  `CODE` varchar(255) DEFAULT NULL COMMENT '产品编号',
  `ONECODE` varchar(255) DEFAULT NULL COMMENT '产品一维码编号',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of erp_code
-- ----------------------------
INSERT INTO `erp_code` VALUES ('2', 'adfadfadfa', '2421312321');
INSERT INTO `erp_code` VALUES ('3', '21', '1210');
INSERT INTO `erp_code` VALUES ('6', '212', '1212');
INSERT INTO `erp_code` VALUES ('7', '213', '1213');
INSERT INTO `erp_code` VALUES ('8', '214', '1214');
INSERT INTO `erp_code` VALUES ('9', '215', '1215');
INSERT INTO `erp_code` VALUES ('10', '216', '1216');
INSERT INTO `erp_code` VALUES ('11', '217', '1217');
INSERT INTO `erp_code` VALUES ('12', '218', '1218');
INSERT INTO `erp_code` VALUES ('14', '2110', '12110');

-- ----------------------------
-- Table structure for `erp_product`
-- ----------------------------
DROP TABLE IF EXISTS `erp_product`;
CREATE TABLE `erp_product` (
  `ID` varchar(255) NOT NULL,
  `CODE` varchar(255) DEFAULT NULL COMMENT '产品编号',
  `NAME` varchar(255) DEFAULT NULL COMMENT '产品名称',
  `COLUMN_ID` varchar(255) DEFAULT NULL COMMENT '类型ID',
  `LOCATION` varchar(255) DEFAULT NULL COMMENT '货架位置',
  `IN_DATE` datetime DEFAULT NULL COMMENT '入库时间',
  `BRAND` varchar(255) DEFAULT NULL COMMENT '品牌',
  `MODEL` varchar(255) DEFAULT NULL COMMENT '产品型号',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of erp_product
-- ----------------------------
INSERT INTO `erp_product` VALUES ('E09D17C2D24D4C7DAC551F7F7D658F79', '219', 'name2', 'A01', 'R2', null, 'lining', '50');
INSERT INTO `erp_product` VALUES ('fafafdafdaf', '21', 'name1', 'A01', 'R1', '2016-10-24 23:14:17', 'nike', '100');
