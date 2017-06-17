-- MySQL dump 10.13  Distrib 5.7.14, for osx10.11 (x86_64)
--
-- Host: localhost    Database: hostelworld
-- ------------------------------------------------------
-- Server version	5.7.14

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `approval`
--

DROP TABLE IF EXISTS `approval`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `approval` (
  `approvalNum` int(11) NOT NULL AUTO_INCREMENT,
  `hostelNum` varchar(7) NOT NULL,
  `approveDate` date DEFAULT NULL,
  `approvalType` enum('REGISTER','MODIFY') DEFAULT 'REGISTER',
  `approveState` enum('APPROVE','DISAPPROVE','WAIT') DEFAULT 'WAIT',
  `applyDate` date DEFAULT NULL,
  PRIMARY KEY (`approvalNum`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `approval`
--

LOCK TABLES `approval` WRITE;
/*!40000 ALTER TABLE `approval` DISABLE KEYS */;
INSERT INTO `approval` VALUES (11,'H000001','2017-03-21','REGISTER','APPROVE','2017-03-21'),(12,'H000002','2017-03-21','REGISTER','APPROVE','2017-03-21'),(14,'H000004','2017-03-22','REGISTER','APPROVE','2017-03-22'),(15,'H000003','2017-03-22','MODIFY','APPROVE','2017-03-22');
/*!40000 ALTER TABLE `approval` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `balanceSettle`
--

DROP TABLE IF EXISTS `balanceSettle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `balanceSettle` (
  `settleNum` int(11) NOT NULL AUTO_INCREMENT,
  `settleDate` date DEFAULT NULL,
  `balance` double,
  `settleCondition` enum('SETTLED','WAIT'),
  `hostelNum` varchar(7) DEFAULT NULL,
  PRIMARY KEY (`settleNum`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `balanceSettle`
--

LOCK TABLES `balanceSettle` WRITE;
/*!40000 ALTER TABLE `balanceSettle` DISABLE KEYS */;
INSERT INTO `balanceSettle` VALUES (16,'2017-03-21',310,'SETTLED','H000001'),(17,'2017-03-21',230,'SETTLED','H000002'),(18,'2017-03-22',230.4,'SETTLED','H000002'),(19,'2017-03-22',80,'SETTLED','H000003'),(20,'2017-03-22',80,'SETTLED','H000003');
/*!40000 ALTER TABLE `balanceSettle` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bankCard`
--

DROP TABLE IF EXISTS `bankCard`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bankCard` (
  `bankCardId` varchar(19) NOT NULL,
  `balance` double DEFAULT NULL,
  PRIMARY KEY (`bankCardId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bankCard`
--

LOCK TABLES `bankCard` WRITE;
/*!40000 ALTER TABLE `bankCard` DISABLE KEYS */;
INSERT INTO `bankCard` VALUES ('01',10000),('02',8000),('03',8000),('3',10000);
/*!40000 ALTER TABLE `bankCard` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `checkInfo`
--

DROP TABLE IF EXISTS `checkInfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `checkInfo` (
  `hostelNum` varchar(7) NOT NULL,
  `lodgerName` varchar(50) DEFAULT NULL,
  `paidMoney` double DEFAULT NULL,
  `checkCondition` enum('CHECKIN','CHECKOUT') DEFAULT NULL,
  `checkinDate` date DEFAULT NULL,
  `checkoutDate` date DEFAULT NULL,
  `checkNum` int(11) NOT NULL AUTO_INCREMENT,
  `roomTypeId` int(11) DEFAULT NULL,
  `roomNum` varchar(10) DEFAULT NULL,
  `orderNum` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`checkNum`),
  KEY `checkInfo_roomType_id_fk` (`roomTypeId`),
  CONSTRAINT `checkInfo_roomType_id_fk` FOREIGN KEY (`roomTypeId`) REFERENCES `roomType` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `checkInfo`
--

LOCK TABLES `checkInfo` WRITE;
/*!40000 ALTER TABLE `checkInfo` DISABLE KEYS */;
INSERT INTO `checkInfo` VALUES ('H000001','V000001',310.40000000000003,'CHECKOUT','2017-03-21','2017-03-22',25,1,'1','H000001000002'),('H000001','tzh',188,'CHECKOUT','2017-03-21','2017-03-23',26,3,'2',NULL),('H000002','V000002',230.4,'CHECKOUT','2017-03-21','2017-03-22',28,1,'1','H000002000002'),('H000002','1',88,'CHECKOUT','2017-03-21','2017-03-24',29,3,'2',NULL),('H000002','V000001',230.4,'CHECKOUT','2017-03-21','2017-03-22',30,1,'1','H000002000003'),('H000002','txy',88,'CHECKOUT','2017-03-21','2017-03-24',31,3,'1',NULL),('H000003','V000003',80,'CHECKOUT','2017-03-22','2017-03-23',34,1,'1','H000003000001'),('H000003','txy',200,'CHECKIN','2017-03-22','2017-03-24',35,3,'2',NULL);
/*!40000 ALTER TABLE `checkInfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `currentSpareRoomInfo`
--

DROP TABLE IF EXISTS `currentSpareRoomInfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `currentSpareRoomInfo` (
  `hostelNum` varchar(7) NOT NULL,
  `spareNum` int(11) DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roomTypeId` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `currentSpareRoomInfo`
--

LOCK TABLES `currentSpareRoomInfo` WRITE;
/*!40000 ALTER TABLE `currentSpareRoomInfo` DISABLE KEYS */;
INSERT INTO `currentSpareRoomInfo` VALUES ('H000001',5,23,3),('H000001',6,24,2),('H000001',7,25,1),('H000002',5,26,3),('H000002',6,27,2),('H000002',7,28,1),('H000004',3,32,3),('H000004',14,33,2),('H000004',3,34,1);
/*!40000 ALTER TABLE `currentSpareRoomInfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `discountStrategy`
--

DROP TABLE IF EXISTS `discountStrategy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `discountStrategy` (
  `vipLevel` int(11) NOT NULL,
  `discount` double DEFAULT NULL,
  PRIMARY KEY (`vipLevel`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `discountStrategy`
--

LOCK TABLES `discountStrategy` WRITE;
/*!40000 ALTER TABLE `discountStrategy` DISABLE KEYS */;
INSERT INTO `discountStrategy` VALUES (0,1),(1,0.8),(2,0.6),(3,0.5),(4,0.5);
/*!40000 ALTER TABLE `discountStrategy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (1),(1),(1),(1),(1);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hostel`
--

DROP TABLE IF EXISTS `hostel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hostel` (
  `hostelNum` varchar(7) NOT NULL,
  `hostelPassword` varchar(20) DEFAULT NULL,
  `profit` double DEFAULT NULL,
  `province` varchar(20) DEFAULT NULL,
  `city` varchar(20) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `hostelInfo` varchar(255) DEFAULT NULL,
  `approvalState` enum('APPROVE','DISAPPROVE','WAIT') DEFAULT NULL,
  `level` int(11) DEFAULT NULL,
  `hostelName` varchar(50) DEFAULT NULL,
  `applyDate` date DEFAULT NULL,
  PRIMARY KEY (`hostelNum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hostel`
--

LOCK TABLES `hostel` WRITE;
/*!40000 ALTER TABLE `hostel` DISABLE KEYS */;
INSERT INTO `hostel` VALUES ('H000001','123',248,'江苏省','南京','1','hostel1','APPROVE',1,'hostel1','2017-03-21'),('H000002','123',368,'江苏省','南京','2','hostel2','APPROVE',1,'hostel2','2017-03-21'),('H000003','123',128,'江苏省','南京','3','213','APPROVE',2,'hostel03','2017-03-22'),('H000004','123',0,'江苏省','南京','4','hostel','APPROVE',2,'hostel04','2017-03-22');
/*!40000 ALTER TABLE `hostel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lodgerInfo`
--

DROP TABLE IF EXISTS `lodgerInfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lodgerInfo` (
  `lodgerNum` varchar(7) NOT NULL,
  `lodgerName` varchar(20) DEFAULT NULL,
  `idNum` varchar(22) DEFAULT NULL,
  `gender` enum('MALE','FEMALE') DEFAULT NULL,
  PRIMARY KEY (`lodgerNum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lodgerInfo`
--

LOCK TABLES `lodgerInfo` WRITE;
/*!40000 ALTER TABLE `lodgerInfo` DISABLE KEYS */;
/*!40000 ALTER TABLE `lodgerInfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `manager`
--

DROP TABLE IF EXISTS `manager`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `manager` (
  `managerNum` varchar(7) NOT NULL,
  `managerName` varchar(20) DEFAULT NULL,
  `managerPassword` varchar(20) DEFAULT NULL,
  `profit` double DEFAULT NULL,
  PRIMARY KEY (`managerNum`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `manager`
--

LOCK TABLES `manager` WRITE;
/*!40000 ALTER TABLE `manager` DISABLE KEYS */;
INSERT INTO `manager` VALUES ('M000001','dada','123123',463.36);
/*!40000 ALTER TABLE `manager` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
  `orderNum` varchar(15) NOT NULL,
  `hostelNum` varchar(7) NOT NULL,
  `vipNum` varchar(7) NOT NULL,
  `roomTypeId` int(11) NOT NULL,
  `roomNum` int(11) NOT NULL,
  `requiredMoney` double NOT NULL,
  `paidMoney` double DEFAULT '0',
  `orderCondition` enum('BOOK','CHECKIN','CHECKOUT','OVERDUE','CANCEL','VALID','SETTLE') DEFAULT 'BOOK',
  `checkinDate` date DEFAULT '1970-01-01',
  `checkoutDate` date DEFAULT '1970-01-01',
  `payMethod` enum('CASH','CARD') DEFAULT NULL,
  PRIMARY KEY (`orderNum`),
  KEY `order_roomType_id_fk` (`roomTypeId`),
  CONSTRAINT `order_roomType_id_fk` FOREIGN KEY (`roomTypeId`) REFERENCES `roomType` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES ('H000001000001','H000001','V000001',1,1,310,93,'CANCEL','2017-03-21','2017-03-22','CARD'),('H000001000002','H000001','V000001',1,1,310,310,'SETTLE','2017-03-21','2017-03-22','CARD'),('H000001000003','H000001','V000001',1,1,620.8000000000001,186.24000000000007,'CANCEL','2017-03-22','2017-03-24','CARD'),('H000002000001','H000002','V000001',1,1,230.4,0,'CANCEL','2017-03-21','2017-03-22','CARD'),('H000002000002','H000002','V000001',1,1,230.4,230.4,'SETTLE','2017-03-21','2017-03-22','CARD'),('H000002000003','H000002','V000001',1,1,230.4,230.4,'SETTLE','2017-03-21','2017-03-22','CARD'),('H000003000001','H000003','V000001',1,1,80,80,'SETTLE','2017-03-22','2017-03-23','CARD'),('H000003000002','H000003','V000001',1,1,80,80,'SETTLE','2017-03-22','2017-03-23','CARD');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roomPlan`
--

DROP TABLE IF EXISTS `roomPlan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roomPlan` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `hostelNum` varchar(7) DEFAULT NULL,
  `roomTypeId` int(11) DEFAULT NULL,
  `roomNum` int(11) DEFAULT NULL,
  `startDate` date DEFAULT NULL,
  `endDate` date DEFAULT NULL,
  `roomPrice` double DEFAULT NULL,
  `date` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `roomInfo_roomType_id_fk` (`roomTypeId`),
  CONSTRAINT `roomInfo_roomType_id_fk` FOREIGN KEY (`roomTypeId`) REFERENCES `roomType` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roomPlan`
--

LOCK TABLES `roomPlan` WRITE;
/*!40000 ALTER TABLE `roomPlan` DISABLE KEYS */;
INSERT INTO `roomPlan` VALUES (23,'H000001',3,5,'2017-03-21','2017-03-25',188,'2017-03-21'),(24,'H000001',2,6,'2017-03-21','2017-03-24',288,'2017-03-21'),(25,'H000001',1,7,'2017-03-21','2017-03-25',388,'2017-03-21'),(26,'H000002',3,5,'2017-03-21','2017-03-24',88,'2017-03-21'),(27,'H000002',2,6,'2017-03-21','2017-03-24',108,'2017-03-21'),(28,'H000002',1,7,'2017-03-21','2017-03-24',288,'2017-03-21'),(29,'H000003',3,4,'2017-03-22','2017-03-24',100,'2017-03-22'),(30,'H000003',2,5,'2017-03-22','2017-03-24',100,'2017-03-22'),(31,'H000003',1,5,'2017-03-22','2017-03-24',100,'2017-03-22'),(32,'H000004',3,3,'2017-03-22','2017-03-25',100,'2017-03-22'),(33,'H000004',2,14,'2017-03-22','2017-03-25',100,'2017-03-22'),(34,'H000004',1,3,'2017-03-22','2017-03-24',200,'2017-03-22');
/*!40000 ALTER TABLE `roomPlan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roomType`
--

DROP TABLE IF EXISTS `roomType`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roomType` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roomType` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roomType`
--

LOCK TABLES `roomType` WRITE;
/*!40000 ALTER TABLE `roomType` DISABLE KEYS */;
INSERT INTO `roomType` VALUES (1,'KingsizeRoom'),(2,'DoubleRoom'),(3,'SingleRoom');
/*!40000 ALTER TABLE `roomType` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vip`
--

DROP TABLE IF EXISTS `vip`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vip` (
  `vipNum` varchar(7) NOT NULL,
  `vipName` varchar(20) DEFAULT NULL,
  `vipPassword` varchar(20) DEFAULT NULL,
  `bankCardId` varchar(19) DEFAULT NULL,
  `vipPoint` double DEFAULT NULL,
  `money` double DEFAULT NULL,
  `state` enum('ACTIVATE','SUSPEND','CANCEL','REGISTER') DEFAULT NULL,
  `activateDate` date DEFAULT NULL,
  `vipLevel` int(11),
  `validDate` date DEFAULT NULL,
  PRIMARY KEY (`vipNum`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vip`
--

LOCK TABLES `vip` WRITE;
/*!40000 ALTER TABLE `vip` DISABLE KEYS */;
INSERT INTO `vip` VALUES ('V000001','Vivian','123','01',850.4,1365.6,'ACTIVATE','2017-03-21',2,'2018-03-21'),('V000002','vip02','123','02',540,1459,'ACTIVATE','2017-03-21',2,'2018-03-21'),('V000003','vip04','123','03',700,1733,'ACTIVATE','2017-03-22',2,'2018-03-22');
/*!40000 ALTER TABLE `vip` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-06-16 15:06:36
