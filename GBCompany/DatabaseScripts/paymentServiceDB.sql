-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: localhost    Database: gb
-- ------------------------------------------------------
-- Server version	8.0.23

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `buyer`
--

DROP TABLE IF EXISTS `buyer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `buyer` (
  `buyId` varchar(50) NOT NULL,
  `first_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  `phone` varchar(10) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `registerDate` date DEFAULT NULL,
  PRIMARY KEY (`buyId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `buyer`
--

LOCK TABLES `buyer` WRITE;
/*!40000 ALTER TABLE `buyer` DISABLE KEYS */;
INSERT INTO `buyer` VALUES ('bu02','Jagath','Siripala','JagS','siri123','js@gmail.com','2222222222','male','2021-03-22');
/*!40000 ALTER TABLE `buyer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `expenses`
--

DROP TABLE IF EXISTS `expenses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `expenses` (
  `expenseId` int NOT NULL AUTO_INCREMENT,
  `expenseTitle` varchar(50) NOT NULL,
  `expenseDesc` varchar(50) DEFAULT NULL,
  `expenseAmount` varchar(50) DEFAULT NULL,
  `expenseStatus` varchar(50) DEFAULT NULL,
  `expenseDate` date DEFAULT NULL,
  `aId` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`expenseId`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `expenses`
--

LOCK TABLES `expenses` WRITE;
/*!40000 ALTER TABLE `expenses` DISABLE KEYS */;
INSERT INTO `expenses` VALUES (21,'Build','on developers','3300','pending','2014-03-03','e33');
/*!40000 ALTER TABLE `expenses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `expenses_payments`
--

DROP TABLE IF EXISTS `expenses_payments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `expenses_payments` (
  `exPayid` int NOT NULL AUTO_INCREMENT,
  `expenseId` int DEFAULT NULL,
  `amount` double DEFAULT NULL,
  `payment_status` varchar(50) DEFAULT NULL,
  `paid_date` datetime DEFAULT NULL,
  PRIMARY KEY (`exPayid`),
  KEY `expenseId` (`expenseId`),
  CONSTRAINT `expenses_payments_ibfk_1` FOREIGN KEY (`expenseId`) REFERENCES `expenses` (`expenseId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `expenses_payments`
--

LOCK TABLES `expenses_payments` WRITE;
/*!40000 ALTER TABLE `expenses_payments` DISABLE KEYS */;
INSERT INTO `expenses_payments` VALUES (1,21,12000,'Pending','2021-02-06 00:00:00'),(2,21,5000,'completed',NULL),(3,21,87000,'pending','2021-01-09 00:00:00'),(4,21,40000,'pending','2020-11-19 00:00:00');
/*!40000 ALTER TABLE `expenses_payments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fund_payments`
--

DROP TABLE IF EXISTS `fund_payments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fund_payments` (
  `fundPayid` int NOT NULL AUTO_INCREMENT,
  `fundId` int DEFAULT NULL,
  `amount` double DEFAULT NULL,
  `paid_date` datetime DEFAULT NULL,
  PRIMARY KEY (`fundPayid`),
  KEY `fundId` (`fundId`),
  CONSTRAINT `fund_payments_ibfk_1` FOREIGN KEY (`fundId`) REFERENCES `funds` (`fundId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fund_payments`
--

LOCK TABLES `fund_payments` WRITE;
/*!40000 ALTER TABLE `fund_payments` DISABLE KEYS */;
INSERT INTO `fund_payments` VALUES (1,2,20000,'2021-03-04 00:00:00');
/*!40000 ALTER TABLE `fund_payments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `funds`
--

DROP TABLE IF EXISTS `funds`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `funds` (
  `fundId` int NOT NULL AUTO_INCREMENT,
  `fundCode` varchar(5) NOT NULL,
  `fundType` varchar(45) NOT NULL,
  `amount` double NOT NULL,
  `date` varchar(10) NOT NULL,
  `status` char(3) DEFAULT NULL,
  PRIMARY KEY (`fundId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `funds`
--

LOCK TABLES `funds` WRITE;
/*!40000 ALTER TABLE `funds` DISABLE KEYS */;
INSERT INTO `funds` VALUES (2,'se09','build',85000,'2020-04-11','pnd');
/*!40000 ALTER TABLE `funds` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_payments`
--

DROP TABLE IF EXISTS `order_payments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_payments` (
  `pid` int NOT NULL AUTO_INCREMENT,
  `Order_ID` int DEFAULT NULL,
  `amount` double DEFAULT NULL,
  `credit_card_no` varchar(16) DEFAULT NULL,
  `cvv` int DEFAULT NULL,
  `payment_status` varchar(50) DEFAULT NULL,
  `paid_date` datetime DEFAULT NULL,
  PRIMARY KEY (`pid`),
  KEY `Order_ID` (`Order_ID`),
  CONSTRAINT `order_payments_ibfk_1` FOREIGN KEY (`Order_ID`) REFERENCES `orders` (`Order_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_payments`
--

LOCK TABLES `order_payments` WRITE;
/*!40000 ALTER TABLE `order_payments` DISABLE KEYS */;
INSERT INTO `order_payments` VALUES (5,1,275000,'2312365898912544',210,'Pending','2020-08-22 00:00:00'),(6,1,66000,'2312365898912544',181,'Pending','2020-12-02 00:00:00'),(7,1,78000,'2312365000912678',210,'Pending','2020-07-23 00:00:00'),(8,1,10000,'5782365000912678',181,'Pending','2020-10-22 00:00:00'),(9,1,5000,'5782365008769078',210,'Pending','2020-10-22 00:00:00'),(10,1,20000,'5782365008769078',210,'completed','2020-10-22 00:00:00'),(11,1,87000.5,'5782365008769078',210,'Pending','2020-03-16 00:00:00'),(12,1,13000.5,'5782365008769078',210,'Pending','2020-08-16 00:00:00');
/*!40000 ALTER TABLE `order_payments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `Order_ID` int NOT NULL AUTO_INCREMENT,
  `Order_Date` date DEFAULT NULL,
  `buyid` varchar(50) DEFAULT NULL,
  `Project_ID` int DEFAULT NULL,
  `pid` int DEFAULT NULL,
  PRIMARY KEY (`Order_ID`),
  KEY `buyid` (`buyid`),
  KEY `Project_ID` (`Project_ID`),
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`buyid`) REFERENCES `buyer` (`buyId`),
  CONSTRAINT `orders_ibfk_2` FOREIGN KEY (`Project_ID`) REFERENCES `project` (`Project_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,'2021-04-03','bu02',1,44);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project`
--

DROP TABLE IF EXISTS `project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `project` (
  `Project_ID` int NOT NULL AUTO_INCREMENT,
  `pmid` varchar(50) DEFAULT NULL,
  `fbid` varchar(50) DEFAULT NULL,
  `Project_Name` varchar(50) DEFAULT NULL,
  `Start_Date` datetime DEFAULT NULL,
  `Deadline_Date` datetime DEFAULT NULL,
  `Project_Status` varchar(10) DEFAULT NULL,
  `Project_Fund_Amt` double DEFAULT NULL,
  `Project_Sell_Amt` double DEFAULT NULL,
  PRIMARY KEY (`Project_ID`),
  KEY `pmid` (`pmid`),
  CONSTRAINT `project_ibfk_1` FOREIGN KEY (`pmid`) REFERENCES `project_manager` (`pmId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project`
--

LOCK TABLES `project` WRITE;
/*!40000 ALTER TABLE `project` DISABLE KEYS */;
INSERT INTO `project` VALUES (1,'pm002','fb008','Sarasavi','2021-02-08 00:00:00','2021-09-30 00:00:00','pending',67000,80000);
/*!40000 ALTER TABLE `project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project_manager`
--

DROP TABLE IF EXISTS `project_manager`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `project_manager` (
  `pmId` varchar(50) NOT NULL,
  `first_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  `phone` varchar(10) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `registerDate` date DEFAULT NULL,
  PRIMARY KEY (`pmId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project_manager`
--

LOCK TABLES `project_manager` WRITE;
/*!40000 ALTER TABLE `project_manager` DISABLE KEYS */;
INSERT INTO `project_manager` VALUES ('pm002','Nimal','Jayalath','NMJaya','jaya123','jaya@gmail.com','1111111111','male','2014-09-28');
/*!40000 ALTER TABLE `project_manager` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-04-25 12:59:03
