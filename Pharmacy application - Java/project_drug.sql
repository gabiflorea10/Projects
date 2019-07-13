-- MySQL dump 10.13  Distrib 8.0.15, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: project
-- ------------------------------------------------------
-- Server version	8.0.15

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `drug`
--

DROP TABLE IF EXISTS `drug`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `drug` (
  `iddrug` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `producer` varchar(45) NOT NULL,
  `usefulfor` varchar(45) NOT NULL,
  `termofvalidity` date NOT NULL,
  `price` int(11) NOT NULL,
  PRIMARY KEY (`iddrug`)
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `drug`
--

LOCK TABLES `drug` WRITE;
/*!40000 ALTER TABLE `drug` DISABLE KEYS */;
INSERT INTO `drug` VALUES (15,'v','v','v','2019-05-19',0),(26,'Drug1','DMed','Flu','2019-05-20',0),(27,'Drug2','DMed','Flu','2019-05-21',0),(34,'a','a','a','2019-06-06',100),(35,'b','b','b','2019-05-18',7),(36,'b','b','b','2019-05-17',25),(37,'c','c','c','2019-05-17',32),(38,'d','d','d','2019-05-20',40),(39,'rr','rr','r','2019-06-17',100),(40,'rr1','rr1','rr1','2019-05-17',100),(41,'rr2','rr1','rr1','2019-05-18',100),(42,'ww','ww','ww','2019-05-01',100),(43,'ww1','ww','ww','2019-05-18',100),(44,'ww1','ww','ww','2019-05-17',100),(45,'ww1','ww','ww','2019-05-19',100),(46,'ww1','ww','ww','2019-05-20',100),(47,'wwrrrr','ww','ww','2019-05-20',100),(48,'wwrrrr1','ww','ww','2019-05-21',100),(49,'wwrrrr1','ww','ww','2019-05-22',100),(50,'med1','prod1','use1','2019-05-25',100),(51,'med2','prod2','use2','2019-05-22',100),(52,'med3','prod3','use3','2019-05-21',100),(53,'med4','prod4','use4','2019-05-20',100),(54,'med5','prod5','use5','2019-05-19',100),(55,'med6','prod6','use6','2019-05-18',100),(56,'med7','prod7','use7','2019-05-17',100),(57,'q1','q1','q1','2019-05-17',100),(58,'q2','q1','q1','2019-05-18',100),(59,'q3','q1','q1','2019-05-19',100),(60,'q3','q1','q1','2019-05-22',100),(61,'q3','q1','q1','2019-05-21',100),(62,'t5','t5','t5','2019-05-18',100),(63,'t6','t5','t5','2019-05-19',100);
/*!40000 ALTER TABLE `drug` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-05-19 21:05:30
