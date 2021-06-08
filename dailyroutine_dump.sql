-- MySQL dump 10.13  Distrib 5.7.26, for Win64 (x86_64)
--
-- Host: localhost    Database: dailyroutine
-- ------------------------------------------------------
-- Server version	5.7.26-log

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
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account` (
  `accountid` varchar(255) NOT NULL,
  `accountname` varchar(255) NOT NULL,
  `admin` bit(1) NOT NULL,
  `enabled` bit(1) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`accountid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES ('admin','管理者',_binary '',_binary '','$2a$10$W5nZJ/iURO.a8gXC5tGJueI5zo.WdhHkNeu9t6tPHYhC7b.Ul4bn2'),('dbadmin','dbadmin',_binary '',_binary '','$2a$10$zrn13Xu78ZbMXHIXCPGFH.pcm9RDyCVLNaqDE.SiK/HpXZbIuJywO'),('hiro','ヒロ',_binary '\0',_binary '','$2a$10$gX4MfC.Fx4zhEOjucKpEw.KONhHeWCW6kK4Xvk9.ZsfJMw5yMzgwy'),('hiroko','弘子',_binary '\0',_binary '','$2a$10$fTC9VbRMEX3mQJaCpbOJUO.dAB15OOYTV/T4kVyL/AbAC4ZZxzavu'),('hiromi','ひろみん',_binary '\0',_binary '','$2a$10$ASGnfRg/oBNZR9Ocn2ZznOIWYjeD82PPdU7iaf6JLXBuUkgaI26ES'),('hisatugu','久継',_binary '\0',_binary '','$2a$10$UJyI5JnNY7YiRtC7yEiGTOrxdFO298DM9leDCSip6jBv/z7OE8FGm'),('inori','いのり',_binary '\0',_binary '','$2a$10$oSScoOy3fM1RZYb1wH7vGeQg2KSbsG7c1I3bkfkeOXL68JvCCQRpS'),('misato','美里',_binary '\0',_binary '','$2a$10$KQcJtRO7LFQ/XGTR3TtoV.fXURKHPNGxSe9UaiCCz83CRaMqc3Ea.'),('misatoaaa','美里aaa',_binary '\0',_binary '','$2a$10$dLEUZrM635sZsm3XijxuJ.2cdGyokr891b.irJI1xKIA0c.nhSl9m');
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `d_routine`
--

DROP TABLE IF EXISTS `d_routine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `d_routine` (
  `routineId` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) NOT NULL DEFAULT '不明',
  `NicePnt` int(11) NOT NULL DEFAULT '0',
  `account_accountid` varchar(255) DEFAULT NULL,
  `description` varchar(50) DEFAULT NULL,
  `currenttime` datetime(6) NOT NULL,
  PRIMARY KEY (`routineId`),
  KEY `FK5iu8xdfhpgjcsl4cl3bnbokb` (`account_accountid`),
  CONSTRAINT `FK5iu8xdfhpgjcsl4cl3bnbokb` FOREIGN KEY (`account_accountid`) REFERENCES `account` (`accountid`)
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `d_routine`
--

LOCK TABLES `d_routine` WRITE;
/*!40000 ALTER TABLE `d_routine` DISABLE KEYS */;
INSERT INTO `d_routine` VALUES (67,'テスト',5,'hiro','test用の説明文','2021-06-02 06:23:14.115000'),(68,'test',5,'dbadmin','test用の説明文','2021-06-01 21:44:24.060000'),(69,'私の朝活',3,'hiro','私が朝に行うルーティーン','2021-06-02 06:36:59.654000'),(70,'私の夜活',3,'hiro','私が夜に行うルーティーン','2021-06-02 06:23:11.990000'),(71,'管理者の一日',1,'admin','管理者のデイリールーティン','2021-06-02 07:39:22.711000'),(72,'通勤時間のルーティン',0,'misato','通勤時間に行うルーティン','2021-06-02 23:35:32.000000'),(73,'平日の活動',0,'hiromi','平日に行うルーティン一覧','2021-06-06 01:39:52.000000'),(74,'出勤時ルーティン',0,'admin','出勤時に決めているルーティン','2021-06-06 10:36:57.000000');
/*!40000 ALTER TABLE `d_routine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `d_routine2`
--

DROP TABLE IF EXISTS `d_routine2`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `d_routine2` (
  `routineid` int(11) NOT NULL AUTO_INCREMENT,
  `nicepnt` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  `account_accountid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`routineid`),
  KEY `FK8f4f5dwp8c6g9lh4a2998rnuj` (`account_accountid`),
  CONSTRAINT `FK8f4f5dwp8c6g9lh4a2998rnuj` FOREIGN KEY (`account_accountid`) REFERENCES `account` (`accountid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `d_routine2`
--

LOCK TABLES `d_routine2` WRITE;
/*!40000 ALTER TABLE `d_routine2` DISABLE KEYS */;
/*!40000 ALTER TABLE `d_routine2` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `routine_detail`
--

DROP TABLE IF EXISTS `routine_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `routine_detail` (
  `R_DETAILID` int(11) NOT NULL AUTO_INCREMENT,
  `CONTENT` varchar(100) NOT NULL DEFAULT '不明',
  `SUCCESS_CNT` int(11) NOT NULL DEFAULT '0',
  `COMPLATE_FLG` tinyint(1) NOT NULL DEFAULT '0',
  `d_routine_routineid` int(11) DEFAULT NULL,
  PRIMARY KEY (`R_DETAILID`),
  KEY `FKj7t5yqhh4jsad1rb9amd8hs8` (`d_routine_routineid`),
  CONSTRAINT `FKj7t5yqhh4jsad1rb9amd8hs8` FOREIGN KEY (`d_routine_routineid`) REFERENCES `d_routine` (`routineId`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `routine_detail`
--

LOCK TABLES `routine_detail` WRITE;
/*!40000 ALTER TABLE `routine_detail` DISABLE KEYS */;
INSERT INTO `routine_detail` VALUES (44,'コーヒータイム',1,0,69),(45,'朝シャワー',2,0,69),(46,'勉強２ｈ',2,0,69),(47,'5時半起床',2,0,69),(48,'読書',0,0,71),(49,'勉強1.5時間以上',1,0,70),(50,'筋トレ　最低１動画以上',1,0,70),(51,'アルゴリズムをはじめよう　を読書',0,0,72),(52,'オルタネート　の読書',1,0,72),(53,'コーヒータイム',0,0,73),(54,'メイク',0,0,73),(55,'朝食（パン）',0,0,73),(56,'一日のタスク書き出し',0,0,74);
/*!40000 ALTER TABLE `routine_detail` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-06-06 12:25:42
