/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 80026
Source Host           : localhost:3306
Source Database       : my_shopping

Target Server Type    : MYSQL
Target Server Version : 80026
File Encoding         : 65001

Date: 2023-02-22 14:57:22
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tbl_admin
-- ----------------------------
DROP TABLE IF EXISTS `tbl_admin`;
CREATE TABLE `tbl_admin` (
  `admin_id` int NOT NULL AUTO_INCREMENT,
  `admin_name` varchar(20) DEFAULT NULL,
  `admin_password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`admin_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of tbl_admin
-- ----------------------------
INSERT INTO `tbl_admin` VALUES ('1', 'admin', 'admin');

-- ----------------------------
-- Table structure for tbl_book
-- ----------------------------
DROP TABLE IF EXISTS `tbl_book`;
CREATE TABLE `tbl_book` (
  `book_id` int NOT NULL AUTO_INCREMENT,
  `book_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `book_price` decimal(5,2) NOT NULL,
  `book_image` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `book_desc` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `book_is_display` int DEFAULT NULL COMMENT '是否上架，0表示上架(显示),-1表示下架(不显示)',
  PRIMARY KEY (`book_id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of tbl_book
-- ----------------------------
INSERT INTO `tbl_book` VALUES ('1', '疯狂Java讲义', '69.30', '20376113-1_l.jpg', '从2000年至今，Java语言一直是应用最广的开发语言，并拥有最广泛的开发人群。如今，Java已经不再简单地是一门语言，它更像一个完整的体系，一个系统的开发平台。更甚至，它被延伸成一种开源精神。', '0');
INSERT INTO `tbl_book` VALUES ('2', 'JavaScript高级程序设计', '66.80', '20884225-1_l.jpg', '本书在上一版基础上进行了大幅度更新和修订，融入了近几年来JavaScript应用发展的最新成果，几乎涵盖了所有需要理解的重要概念和最新的JavaScript应用成果。', '0');
INSERT INTO `tbl_book` VALUES ('3', 'Head First Java (中文版)', '53.50', '9265169-1_l.jpg', '《Head First Java》是完整的面向对象程序设计和Java的学习指导。此书是根据学习理论所设计的，让你开源从学习程序语言的基础开始一直到包括线程、网络与分布式程序等项目。', '0');
INSERT INTO `tbl_book` VALUES ('4', 'Java从入门到精通', '44.60', '20810282-1_l.jpg', '本书以零基础讲解为宗旨，用实例引导读者深入学习，采取【基础知识→核心技术→高级应用→项目实战】的讲解模式，深入浅出地讲解Java的各种技术及实战技能。', '0');
INSERT INTO `tbl_book` VALUES ('5', 'Java核心技术:卷1基础知识', '65.70', '20285763-1_l.jpg', '《Java核心技术》出版以来一直畅销不衰，深受读者青睐，每个新版本都尽可能快地跟上Java开发工具箱发展的步伐，而且每一版都重新改写了部分内容，以便应用Java的最新特性。', '0');
INSERT INTO `tbl_book` VALUES ('6', '疯狂Ajax讲义', '51.80', '20549981-1_l.jpg', '本书是《基于J2EE的Ajax宝典》的第二版，《基于J2EE的Ajax宝典》面市近2年，作为Ajax领域最全面、实用的图书，一直深受读者的好评。　　全书主要分为三个部分。\r\n', '0');
INSERT INTO `tbl_book` VALUES ('7', 'JavaScript权威指南(第五版)', '81.70', '20019046-1_l.jpg', '《JavaScript权威指南第4版》中文版出版至今已有三年多的时间。这本《JaVaScript权威指南》连续印刷5次，销售数十万册，成为很多JavaScript学习者的必备宝典。由于其封面上是一只“爪哇犀牛”（封面上...\r\n', '0');
INSERT INTO `tbl_book` VALUES ('8', 'jQuery权威指南', '40.50', '21006995-1_l.jpg', '本书由国内资深web技术专家亲自执笔，4大web开发社区一致鼎力推荐，权威性毋庸置疑。　　内容新颖，基于jquery的最新版本撰写，所有新功能和新特性一览无余；内容全面，不仅讲解了jquery技术本身的方方...\r\n', '0');
INSERT INTO `tbl_book` VALUES ('9', 'Java程序设计(第二版)', '21.40', '9336332-1_l.jpg', '本书是《Java程序设计》的第二版。它从Java语言的基本特点入手，全面介绍了Java语言的基本概念和编程方法，并深入介绍了Java的高级特性。本书共分为12章，涉及Java中的基本语法、数据类型、类、异常、...\r\n', '0');
INSERT INTO `tbl_book` VALUES ('10', 'JAVA核心技术卷II：高级特性（原书第8版）', '80.60', '20446562-1_l.jpg', '本书是Java技术权威指南，全面覆盖Java技术的高级主题，包括流与文件、XML、网络、数据库编程、高级Swing、高级 AWT、JavaBean构件、安全、分布式对象、脚本、编译与注解处理等，同时涉及本地化、国际...\r\n', '0');
INSERT INTO `tbl_book` VALUES ('11', 'Spring in Action（第二版）中文版', '44.60', '20345173-1_l.jpg', '本书分成三大部分并附有两个附录，每一部分分别对应Spring框架的一个领域：核心框架、业务和数据层、表示层。每一部分既是建立在前一部分的基础上，同时也可以独立存在。因此，读者不必从头开始，完全...\r\n', '0');
INSERT INTO `tbl_book` VALUES ('12', 'Tomcat与Java Web开发技术详解（第2版）(含光盘1张) ', '61.00', '20420983-1_l.jpg', '本书结合最新的Tomcat 6版本，详细介绍了开发Java Web应用的各种技术。主要内容包括：Tomcat和Java Web开发的基础知识、Java Web开发的高级技术、在Java Web应用中运用第三方提供的实用软件（如Struts...\r\n', '0');
INSERT INTO `tbl_book` VALUES ('13', 'Java程序员面试宝典（第2版）', '31.30', '21127179-1_l.jpg', '本书是《Java程序员面试宝典》的第2版。第2版在保留第1版数据结构、字符串处理、Java程序设计等主干内容的基础上，大量更新了程序面试题目，内容主要取材于2009年到2011年各大公司的面试题，以反...\r\n', '0');
INSERT INTO `tbl_book` VALUES ('14', '高性能JavaScript', '36.80', '20967330-1_l.jpg', '如果你使用JavaScript构建交互丰富的Web应用，那么JavaScript代码可能是造成你的Web应用速度变慢的主要原因。本书揭示的技术和策略能帮助你在开发过程中消除性能瓶颈。你将会了解如何提升各方面的性能...\r\n', '0');
INSERT INTO `tbl_book` VALUES ('15', 'Java项目开发案例全程实录（第2版）（配光盘）（软件项目开发全 ', '52.30', '20991549-1_l.jpg', '本书以进销存管理系统、企业内部通信系统、企业人事管理系统、酒店管理系统、图书馆管理系统、企业快信、欣想电子商城、医药管理系统、企业门户网站、BBS系统、手机网络游戏和棋牌游戏系统之网络...\r\n', '0');
INSERT INTO `tbl_book` VALUES ('16', '深入浅出JavaScript（中文版）', '73.30', '20958893-1_l.jpg', '你将从本书学到什么?这么说你准备从写html和css的静态网页跃进到编写动态网络应用程序脚本了?这里就是起点。《深入浅出java script》带你游历令人兴奋的交互式网页创建过程。为了启发你的思考，本书覆...\r\n', '0');
INSERT INTO `tbl_book` VALUES ('17', 'Java语言程序设计：基础篇（原书第8版）', '56.20', '21117631-1_l.jpg', '《java语言程序设计.基础篇(原书第8版)》是java语言的经典教材，中文版分为《java语言程序设计基础篇》和《java语言程序设计进阶篇》，主要介绍程序设计基础、面向对象程序设计、gui程序设计、...\r\n', '0');
INSERT INTO `tbl_book` VALUES ('18', '经典Java EE企业应用实战——基于WebLogic/JBoss的JSF+EJB 3+JPA ', '60.60', '20928547-1_l.jpg', '本书是《轻量级Java EE企业应用实战》的姊妹篇，《轻量级Java EE企业应用实战》主要介绍以Spring+Hibernate为基础的Java EE应用；本书则主要介绍以EJB 3+JPA为基础的Java EE应用。EJB 3、JPA规', '0');
INSERT INTO `tbl_book` VALUES ('19', 'Java编程思想（英文版·第4版）', '56.10', '9288920-1_l.jpg', '本书特色：　　·适合初学者与专业人员的经典的面向对象的叙述方式；为更新的Java SE5/6的相关内容增加了新的示例和章节。　　·测验框架显示程序输出。　　·设计模式贯穿于众多示例中：适配器、桥...\r\n', '0');
INSERT INTO `tbl_book` VALUES ('20', 'JAVA面向对象编程', '49.50', '9186890-1_l.jpg', '本书内容由浅入深，紧密结合实际，利用大量典型实例，详细讲解Java面向对象的编程思想、编程语法和设计模式，介绍常见Java类库的用法，总结优化Java编程的各种宝贵经验，深入阐述Java虚拟机执行Java程...\r\n', '0');
INSERT INTO `tbl_book` VALUES ('21', 'Java程序设计实用教程（第3版）', '26.20', '20791263-1_l.jpg', '本书为普通高等教育“十一五”国家级规划教材。　　本书全面、系统地介绍Java语言程序设计的基本知识、运行机制和各种常用编程方法和技术，将面向对象的程序设计思想贯穿其中，建立起牢固扎实的理...\r\n', '0');
INSERT INTO `tbl_book` VALUES ('22', '解密搜索引擎技术实战：Lucene&Java精华版(含DVD光盘1张) ', '52.40', '21117052-1_l.jpg', '本书是猎兔搜索开发团队的软件研发和教学实践的经验汇总。　　本书总结搜索引擎相关理论与实际解决方案，并给出了Java实现，其中利用了流行的开源项目Lucene和Solr，而且还包括原创的实现。', '0');
INSERT INTO `tbl_book` VALUES ('23', '犀利开发——jQuery内核详解与实践（配光盘）（网站开发路线图） ', '36.80', '20938710-1_l.jpg', '本书循序渐进地讲解了jQuery高效开发的方法和技巧，内容包括jQuery框架的设计模式和思路、Sizzle选择器的构成和工作机制、DOM文档操作、事件处理、动画设计、Ajax异步通信、插件扩展和辅助工具等。 ...\r\n', '0');
INSERT INTO `tbl_book` VALUES ('24', 'Java加密与解密的艺术', '51.80', '20813335-1_l.jpg', '本书是Java安全领域的百科全书，密码学领域的权威经典，4大社区一致鼎力推荐。 全书包含3个部分，基础篇对Java企业级应用的安全知识、密码学核心知识、与Java加密相关的API和通过权限文件加强系统安...\r\n', '0');
INSERT INTO `tbl_book` VALUES ('25', 'jQuery基础教程(第2版)', '37.00', '20719847-1_l.jpg', '本书作为《jQuery基础教程》的升级版，涵盖了jQuery 1.3的全部新特性，特别是新增了介绍jQuery UI(jQuery官方用户界面插件库)的内容。本书前6章以通俗易懂的方式介绍了jQuery的基本概念，主要包括', '0');
INSERT INTO `tbl_book` VALUES ('26', '数据结构与算法分析Java语言描述 第2版', '38.50', '20417467-1_l.jpg', '本书是国外数据结构与算法分析方面的经典教材，使用卓越的Java编程语言作为实现工具讨论了数据结构（组织大量数据的方法）和算法分析（对算法运行时间的估计）。　　随着计算机速度的不断增加和功...\r\n', '0');
INSERT INTO `tbl_book` VALUES ('27', '21天学通Java 6(第5版)', '33.80', '20443876-1_l.jpg', '本书包括3周的课程，通过学习，读者将具备使用Java开发应用程序、servlet和JSP的知识和技能。第1周介绍Java语言的基本知识，包括数据类型、变量、表达式、对象、数组、条件语句、循环、类、接口、包、...\r\n', '0');
INSERT INTO `tbl_book` VALUES ('28', 'Java面向对象程序设计（21世纪高等学校计算机专业实用规划教材） ', '25.50', '20773224-1_l.jpg', 'Java语言具有面向对象、与平台无关、安全、稳定和多线程等优良特性，是目前软件设计中极为强大的编程语言。Java已成为网络时代最重要的语言之一。本书注重结合实例，以及重要的设计模式，循序渐进地向...\r\n', '0');
INSERT INTO `tbl_book` VALUES ('29', 'jQuery全面提速', '39.00', '20856539-1_l.jpg', 'jQuery是一款轻量级的JavaScript库，它不仅功能强大，而且简洁优雅，能使用最少的代码来实现最多的功能，深受Web开发人员的青睐。本书通过大量实例循序渐进地讲解了jQuery的基本概念和使用技巧，前...\r\n', '0');
INSERT INTO `tbl_book` VALUES ('30', 'Java语言程序设计：进阶篇（原书第8版）', '59.20', '21117631-1_l.jpg', '本书由Y.Daniel Liang所著，是 Java语言的经典教材，中文版分为《Java语言程序设计基础篇》和《Java语言程序设计进阶篇》，主要介绍程序设计基础、面向对象程序设计、GUI程序设计、算法和数据结', '0');
INSERT INTO `tbl_book` VALUES ('31', 'Java SOA Cookbook中文版', '59.20', '22469090-1_l.jpg', 'Java SOA Cookbook中文版》为需要在企业中实现面向服务架构 (SOA)的程序员提供了实用的解决方案和建议。《Java SOA Cookbook中文版》不只是给出一些概念性、宏观性的SO...\r\n', '0');
INSERT INTO `tbl_book` VALUES ('32', '实战突击：Java Web项目整合开发(含DVD光盘1张) ', '67.30', '22477555-1_l.jpg', '本书以都市供求信息网、物流配货网、编程爱好者博客地带、明日知道、天下淘网络商城、成长在线考试网、企业物资管理系统、办公自动化管理系统、校园管理系统、高校学生选课系...\r\n', '0');
INSERT INTO `tbl_book` VALUES ('33', 'JavaScript详解(第2版)', '74.20', '22541821-1_l.jpg', 'JavaScript是目前最流行和最重要的Web编程语言。《JavaScript详解(第2版)》从JavaScript的编程结构开始，循序渐进地讲述了JavaScript及相关的CSS、W3C DOM、cookie、正则表达式和匹配模式，以及功...\r\n', '0');
INSERT INTO `tbl_book` VALUES ('34', ' JAVASCRIPT语言精髓与编程实践', '51.00', '20185383-1_l.jpg', '本书详细讲述JavaScript作为一种混合式语言的各方面特性，包括过程式、面向对象、函数式和动态语言特性等，在动态函数式语言特性方面有着尤为细致的讲述。本书的主要努力之一，就是分解出这些语言...\r\n', '0');
INSERT INTO `tbl_book` VALUES ('35', 'Java大学实用教程（第2版）', '21.80', '20228875-1_l.jpg', '本书是《Java大学实用教程》的第2版，继续保留原教材的特点——注重教材的可读性和实用性。本书加大了面向对象的知识容量，对部分例题的内容做了调整，增加了一些新的知识内容和例题。考虑到Java与数据...\r\n', '0');

-- ----------------------------
-- Table structure for tbl_car
-- ----------------------------
DROP TABLE IF EXISTS `tbl_car`;
CREATE TABLE `tbl_car` (
  `car_id` int NOT NULL AUTO_INCREMENT,
  `book_id` int NOT NULL,
  `car_count` int NOT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`car_id`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of tbl_car
-- ----------------------------
INSERT INTO `tbl_car` VALUES ('45', '4', '1', '105');
INSERT INTO `tbl_car` VALUES ('54', '3', '1', '93');
INSERT INTO `tbl_car` VALUES ('55', '1', '1', '93');
INSERT INTO `tbl_car` VALUES ('56', '4', '1', '93');
INSERT INTO `tbl_car` VALUES ('57', '2', '1', '93');

-- ----------------------------
-- Table structure for tbl_comment
-- ----------------------------
DROP TABLE IF EXISTS `tbl_comment`;
CREATE TABLE `tbl_comment` (
  `comment_id` int NOT NULL AUTO_INCREMENT COMMENT '评论编号',
  `user_id` int NOT NULL COMMENT '外键，用户编号,那个用户发布的评论',
  `book_id` int NOT NULL COMMENT '外键，图书编号，哪本的评论',
  `comment_message` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '评论消息',
  `comment_time` datetime NOT NULL COMMENT '评论时间',
  PRIMARY KEY (`comment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of tbl_comment
-- ----------------------------
INSERT INTO `tbl_comment` VALUES ('1', '105', '2', '这是一个本好书，很耐看，就是看不懂', '2022-12-07 19:49:34');
INSERT INTO `tbl_comment` VALUES ('2', '93', '2', '书很好，很棒，学习要有耐心', '2022-12-07 19:52:07');
INSERT INTO `tbl_comment` VALUES ('3', '104', '2', '完全看不懂', '2022-12-07 19:53:50');
INSERT INTO `tbl_comment` VALUES ('4', '93', '1', '很疯狂!', '2022-12-07 20:33:19');
INSERT INTO `tbl_comment` VALUES ('5', '93', '1', '很疯狂!', '2022-12-07 20:33:32');
INSERT INTO `tbl_comment` VALUES ('6', '93', '1', '非常疯狂，写的很好，看的很吃力!', '2022-12-07 20:34:24');
INSERT INTO `tbl_comment` VALUES ('7', '93', '1', '一本好书', '2022-12-07 20:34:42');
INSERT INTO `tbl_comment` VALUES ('8', '93', '2', 'JavaScript入门必备', '2022-12-07 20:36:29');
INSERT INTO `tbl_comment` VALUES ('9', '93', '2', '奥术大师多', '2022-12-07 20:36:44');
INSERT INTO `tbl_comment` VALUES ('10', '93', '4', '阿斯蒂芬', '2022-12-07 20:36:53');
INSERT INTO `tbl_comment` VALUES ('11', '93', '4', '发电房', '2022-12-07 20:36:56');
INSERT INTO `tbl_comment` VALUES ('12', '93', '3', 'asdasd', '2022-12-08 10:46:11');
INSERT INTO `tbl_comment` VALUES ('13', '93', '3', 'asfdsd', '2022-12-08 10:46:16');
INSERT INTO `tbl_comment` VALUES ('14', '93', '3', 'fdfdfdf', '2022-12-08 10:46:35');
INSERT INTO `tbl_comment` VALUES ('15', '93', '3', 'sssss', '2022-12-08 10:46:39');
INSERT INTO `tbl_comment` VALUES ('16', '93', '3', '是否发布成功', '2022-12-08 10:47:13');
INSERT INTO `tbl_comment` VALUES ('17', '93', '3', 'ddddd', '2022-12-08 10:48:36');
INSERT INTO `tbl_comment` VALUES ('18', '93', '3', 'hello', '2022-12-08 10:57:38');
INSERT INTO `tbl_comment` VALUES ('19', '93', '3', 'ssssss', '2022-12-08 10:58:22');
INSERT INTO `tbl_comment` VALUES ('20', '93', '3', '能发布吗', '2022-12-08 10:59:08');
INSERT INTO `tbl_comment` VALUES ('21', '93', '3', 'dddd', '2022-12-08 11:00:22');
INSERT INTO `tbl_comment` VALUES ('22', '93', '3', 'www', '2022-12-08 11:01:02');
INSERT INTO `tbl_comment` VALUES ('23', '93', '3', 'www', '2022-12-08 11:01:52');
INSERT INTO `tbl_comment` VALUES ('24', '93', '3', 'sddd', '2022-12-08 11:02:17');
INSERT INTO `tbl_comment` VALUES ('25', '93', '3', 'dsd', '2022-12-08 11:03:33');
INSERT INTO `tbl_comment` VALUES ('26', '93', '1', 'sdsd', '2022-12-08 11:04:55');
INSERT INTO `tbl_comment` VALUES ('27', '93', '3', 'ddd', '2022-12-08 11:22:51');
INSERT INTO `tbl_comment` VALUES ('28', '93', '2', 'sdsd', '2022-12-08 11:30:17');
INSERT INTO `tbl_comment` VALUES ('29', '93', '3', 'dddssss', '2022-12-11 17:00:49');

-- ----------------------------
-- Table structure for tbl_order
-- ----------------------------
DROP TABLE IF EXISTS `tbl_order`;
CREATE TABLE `tbl_order` (
  `order_id` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_id` int NOT NULL,
  `order_to_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `order_to_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `order_to_phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `order_time` datetime NOT NULL,
  `order_status` int NOT NULL DEFAULT '0' COMMENT '0表示未发货\r\n1表示已发货\r\n2表示交易成功\r\n-1表示交易失败',
  `order_total_price` float NOT NULL,
  PRIMARY KEY (`order_id`),
  KEY `fk_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of tbl_order
-- ----------------------------
INSERT INTO `tbl_order` VALUES ('202212111631530692', '93', 'ddd', 'wqwqw', '12324546', '2022-12-11 16:31:53', '1', '346.5');
INSERT INTO `tbl_order` VALUES ('202212111632244153', '93', 'xxx', 'xxx', '121212', '2022-12-11 16:32:24', '1', '107.3');
INSERT INTO `tbl_order` VALUES ('202212111641095201', '93', '小花', '宝鸡文理学院', '1324234543', '2022-12-11 16:41:09', '1', '398.3');
INSERT INTO `tbl_order` VALUES ('202212111644486371', '93', '小王八', '陕西省西安市', '2345678', '2022-12-11 16:44:48', '1', '121.1');
INSERT INTO `tbl_order` VALUES ('202212111645358212', '93', 'asdf', 'asdf', 'q234123', '2022-12-11 16:45:35', '1', '98.1');
INSERT INTO `tbl_order` VALUES ('202212111647329471', '93', 'dfdf', 'dfdf', '23223', '2022-12-11 16:47:32', '-1', '111.4');
INSERT INTO `tbl_order` VALUES ('202212111648073922', '105', '小明', '宝鸡市', '2342543253654', '2022-12-11 16:48:07', '1', '136.1');
INSERT INTO `tbl_order` VALUES ('202212111658349211', '107', 'sas', 'as', '2321313', '2022-12-11 16:58:34', '-1', '111.4');
INSERT INTO `tbl_order` VALUES ('202212120906171041', '93', 'ddd', '1212', '121212', '2022-12-12 09:06:17', '2', '122.8');
INSERT INTO `tbl_order` VALUES ('202212201432344451', '93', 'ddd', 'sss', '121221', '2022-12-20 14:32:34', '0', '107');

-- ----------------------------
-- Table structure for tbl_order_desc
-- ----------------------------
DROP TABLE IF EXISTS `tbl_order_desc`;
CREATE TABLE `tbl_order_desc` (
  `desc_id` int NOT NULL AUTO_INCREMENT,
  `order_id` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `book_id` int NOT NULL,
  `count` int NOT NULL,
  PRIMARY KEY (`desc_id`),
  KEY `fk_order_id` (`order_id`),
  KEY `fk_book_id` (`book_id`),
  CONSTRAINT `tbl_order_desc_ibfk_1` FOREIGN KEY (`book_id`) REFERENCES `tbl_book` (`book_id`)
) ENGINE=InnoDB AUTO_INCREMENT=132 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of tbl_order_desc
-- ----------------------------
INSERT INTO `tbl_order_desc` VALUES ('114', '202212111631530692', '1', '5');
INSERT INTO `tbl_order_desc` VALUES ('115', '202212111632244153', '2', '1');
INSERT INTO `tbl_order_desc` VALUES ('116', '202212111632244153', '8', '1');
INSERT INTO `tbl_order_desc` VALUES ('117', '202212111641095201', '1', '5');
INSERT INTO `tbl_order_desc` VALUES ('118', '202212111641095201', '6', '1');
INSERT INTO `tbl_order_desc` VALUES ('119', '202212111644486371', '1', '1');
INSERT INTO `tbl_order_desc` VALUES ('120', '202212111644486371', '6', '1');
INSERT INTO `tbl_order_desc` VALUES ('121', '202212111645358212', '4', '1');
INSERT INTO `tbl_order_desc` VALUES ('122', '202212111645358212', '3', '1');
INSERT INTO `tbl_order_desc` VALUES ('123', '202212111647329471', '2', '1');
INSERT INTO `tbl_order_desc` VALUES ('124', '202212111647329471', '4', '1');
INSERT INTO `tbl_order_desc` VALUES ('125', '202212111648073922', '2', '1');
INSERT INTO `tbl_order_desc` VALUES ('126', '202212111648073922', '1', '1');
INSERT INTO `tbl_order_desc` VALUES ('127', '202212111658349211', '2', '1');
INSERT INTO `tbl_order_desc` VALUES ('128', '202212111658349211', '4', '1');
INSERT INTO `tbl_order_desc` VALUES ('129', '202212120906171041', '1', '1');
INSERT INTO `tbl_order_desc` VALUES ('130', '202212120906171041', '3', '1');
INSERT INTO `tbl_order_desc` VALUES ('131', '202212201432344451', '3', '2');

-- ----------------------------
-- Table structure for tbl_user
-- ----------------------------
DROP TABLE IF EXISTS `tbl_user`;
CREATE TABLE `tbl_user` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `user_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_password` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `user_birthday` date DEFAULT NULL,
  `user_hobbys` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `user_sex` int DEFAULT NULL,
  `user_address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_status` int DEFAULT NULL COMMENT '用户状态，0表示正常，-1表示冻结(冻结不允许登录)',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `userName` (`user_name`)
) ENGINE=MyISAM AUTO_INCREMENT=108 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of tbl_user
-- ----------------------------
INSERT INTO `tbl_user` VALUES ('93', 'admin', '$2a$10$K/nUX5JIz.HILFMTjeUScubAu7yP94dYhC88BVdyj3TZVJJkqGCey', 'aaa@163.com', '2021-04-17', '吃', '1', '宝鸡市', '0');
INSERT INTO `tbl_user` VALUES ('94', 'xiaohua', '$2a$10$HZTKEkt7fhFj6.qBbhZeJeH6CUyEM60u/WOEGrleHVaFxj.dHLu2O', 'xiaoqiang@163.com', '2000-10-10', '吃', '1', '西安市', '-1');
INSERT INTO `tbl_user` VALUES ('95', 'xiaolizi', '$2a$10$NJLInDR/6C11q7w9oUIXq.kVK6xkf9BdZfaYe/6Uiw7B3xoG9Qj5e', 'xiaoli@163.com', '2000-10-10', '睡', '1', '宝鸡市', '0');
INSERT INTO `tbl_user` VALUES ('106', '小华', 'huahua', 'huahua@163.com', '2022-12-06', '听音乐_网游', '0', '陕西省西安市', '0');
INSERT INTO `tbl_user` VALUES ('97', '小明', '123456', 'xiaoming@163.com', '2021-07-01', '吃饭_逛街', '1', '陕西省西安市', '0');
INSERT INTO `tbl_user` VALUES ('98', '小明1', '123456', 'xiaoming@163.com', '2021-07-01', '吃饭_逛街', '1', '陕西省西安市', '0');
INSERT INTO `tbl_user` VALUES ('99', 'admin123', '123456', 'asdf@163.com', '2021-07-01', '睡觉_吃饭', '1', 'asdf', '0');
INSERT INTO `tbl_user` VALUES ('100', 'adsf123', 'asdf', 'asdf', '2021-07-01', '吃饭_睡觉', '1', 'asdfasdf', '-1');
INSERT INTO `tbl_user` VALUES ('102', 'adsf213', 'asdf', 'asdf', '2021-07-07', '吃饭_睡觉', '1', 'asdf', '0');
INSERT INTO `tbl_user` VALUES ('103', 'ooop', '123456', '121221@163.com', '2021-07-03', '吃饭', '1', 'asdfasdf', '0');
INSERT INTO `tbl_user` VALUES ('104', '小草', '123456', 'xiaocao@163.com', '2021-07-01', '逛街', '0', 'asdfasfd', '0');
INSERT INTO `tbl_user` VALUES ('105', '小明明', '123456', 'mingming@163.com', '2022-12-01', '听音乐_睡觉_逛街_网游', '1', '陕西省宝鸡市', '0');
INSERT INTO `tbl_user` VALUES ('107', 'sss', '121212', 'sss@163.com', '2022-11-29', '听音乐_逛街', '1', 'asdf', '0');
