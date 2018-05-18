CREATE DATABASE  IF NOT EXISTS `leaguetad` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `leaguetad`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: leaguetad
-- ------------------------------------------------------
-- Server version	5.5.5-10.1.31-MariaDB

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
-- Table structure for table `calendario`
--

DROP TABLE IF EXISTS `calendario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `calendario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `anyo` datetime DEFAULT NULL,
  `liga_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_calendario_liga_idx` (`liga_id`),
  CONSTRAINT `FK3j9nmcj931stnkp84jn4wgmvn` FOREIGN KEY (`liga_id`) REFERENCES `liga` (`id`),
  CONSTRAINT `fk_calendario_liga` FOREIGN KEY (`liga_id`) REFERENCES `liga` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `calendario`
--

LOCK TABLES `calendario` WRITE;
/*!40000 ALTER TABLE `calendario` DISABLE KEYS */;
INSERT INTO `calendario` VALUES (1,'2018-01-01 00:00:00',1),(2,'2018-01-01 00:00:00',2),(3,'2018-01-01 00:00:00',3),(4,'2017-01-01 00:00:00',1),(5,'2017-01-01 00:00:00',2),(6,'2017-01-01 00:00:00',3),(7,'2016-01-01 00:00:00',1),(8,'2016-01-01 00:00:00',2),(9,'2016-01-01 00:00:00',3);
/*!40000 ALTER TABLE `calendario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `equipo`
--

DROP TABLE IF EXISTS `equipo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `equipo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) DEFAULT NULL,
  `liga_id` int(11) DEFAULT NULL,
  `puntos` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_equipo_liga_idx` (`liga_id`),
  CONSTRAINT `FKmfpijwlspqsfg01epy96ffh6k` FOREIGN KEY (`liga_id`) REFERENCES `liga` (`id`),
  CONSTRAINT `fk_equipo_liga` FOREIGN KEY (`liga_id`) REFERENCES `liga` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equipo`
--

LOCK TABLES `equipo` WRITE;
/*!40000 ALTER TABLE `equipo` DISABLE KEYS */;
INSERT INTO `equipo` VALUES (10,'Real Betis Balompie',1,59),(11,'Barcelona FC',1,55),(12,'Real Madrid',1,54),(13,'Valencia CF',1,50),(14,'Sevilla FC',1,35),(15,'Liverpool',3,60),(16,'Manchester united',3,58),(17,'Chelsea',3,49),(18,'Arsenal',3,49),(19,'Tottenham',3,45),(20,'Juventus',2,91),(21,'Nápoles',2,85),(22,'Roma',2,73),(23,'Lazio',2,71),(24,'Inter Milán',2,69);
/*!40000 ALTER TABLE `equipo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estadistica`
--

DROP TABLE IF EXISTS `estadistica`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `estadistica` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `golesLocal` int(11) DEFAULT NULL,
  `golesVisitante` int(11) DEFAULT NULL,
  `pasesLocal` int(11) DEFAULT NULL,
  `pasesVisitante` int(11) DEFAULT NULL,
  `faltasLocal` int(11) DEFAULT NULL,
  `faltasVisitante` int(11) DEFAULT NULL,
  `tirosLocal` int(11) DEFAULT NULL,
  `tirosVisitante` int(11) DEFAULT NULL,
  `partido_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_estadistica_partido_idx` (`partido_id`),
  CONSTRAINT `fk_estadistica_partido` FOREIGN KEY (`partido_id`) REFERENCES `partido` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estadistica`
--

LOCK TABLES `estadistica` WRITE;
/*!40000 ALTER TABLE `estadistica` DISABLE KEYS */;
/*!40000 ALTER TABLE `estadistica` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jornada`
--

DROP TABLE IF EXISTS `jornada`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jornada` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `numero` int(11) DEFAULT NULL,
  `fecha` datetime DEFAULT NULL,
  `calendario_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_jornada_calendario_idx` (`calendario_id`),
  CONSTRAINT `FK6mkt8n4khlmcq7jntaho9igpc` FOREIGN KEY (`calendario_id`) REFERENCES `calendario` (`id`),
  CONSTRAINT `fk_jornada_calendario` FOREIGN KEY (`calendario_id`) REFERENCES `calendario` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jornada`
--

LOCK TABLES `jornada` WRITE;
/*!40000 ALTER TABLE `jornada` DISABLE KEYS */;
INSERT INTO `jornada` VALUES (1,1,'2018-05-05 00:00:00',1),(2,2,'2018-05-12 00:00:00',1),(3,3,'2018-05-19 00:00:00',1),(4,4,'2018-05-26 00:00:00',1),(5,1,'2018-05-05 00:00:00',2),(6,2,'2018-05-12 00:00:00',2),(7,3,'2018-05-19 00:00:00',2),(8,4,'2018-05-26 00:00:00',2),(9,1,'2018-05-05 00:00:00',3),(10,2,'2018-05-12 00:00:00',3),(11,3,'2018-05-19 00:00:00',3),(12,4,'2018-05-26 00:00:00',3),(13,1,'2017-05-05 00:00:00',1),(14,2,'2017-05-12 00:00:00',1),(15,3,'2017-05-19 00:00:00',1),(16,4,'2017-05-26 00:00:00',1);
/*!40000 ALTER TABLE `jornada` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jugador`
--

DROP TABLE IF EXISTS `jugador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jugador` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) DEFAULT NULL,
  `nacionalidad` varchar(100) DEFAULT NULL,
  `posicion` varchar(45) DEFAULT NULL,
  `edad` int(11) DEFAULT NULL,
  `goles` int(11) DEFAULT NULL,
  `pases` int(11) DEFAULT NULL,
  `faltas` int(11) DEFAULT NULL,
  `expulsiones` int(11) DEFAULT NULL,
  `paradas` int(11) DEFAULT NULL,
  `tiros` int(11) DEFAULT NULL,
  `equipo_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_jugador_equipo_idx` (`equipo_id`),
  CONSTRAINT `FKaum61vdoi1f4v3nb6e27tdpdt` FOREIGN KEY (`equipo_id`) REFERENCES `equipo` (`id`),
  CONSTRAINT `fk_jugador_equipo` FOREIGN KEY (`equipo_id`) REFERENCES `equipo` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jugador`
--

LOCK TABLES `jugador` WRITE;
/*!40000 ALTER TABLE `jugador` DISABLE KEYS */;
INSERT INTO `jugador` VALUES (1,'Joaquin Sanchez','Español','Extremo derecho',37,6,102,5,0,0,40,10),(2,'Fabian Ruiz','Español','Centrocampista',21,3,200,9,0,0,51,10),(3,'Lionel Messi','Argentina','Delantero',31,22,43,5,1,0,150,11),(4,'Andres Iniesta','Español','Centrocampista',35,5,300,4,0,0,18,11),(5,'Cristiano Ronaldo','Portugues','Delantero',34,30,49,6,2,0,40,12),(6,'Keylor Navas','Costa Rica','Portero',28,0,10,0,0,25,0,12),(7,'Rodrigo','Español','Delantero',24,8,26,10,3,0,16,13),(8,'Gonzalo Guedes','Portugues','Extremo',25,4,32,10,1,0,5,13),(9,'Jesus Navas','Español','Lateral derecho',30,1,27,12,2,0,3,14),(10,'Steven Nzonzi','Francia','Centrocampista',29,0,50,15,5,0,12,14),(11,'Mohamed Salah','Egipcio','Delantero',25,15,16,17,0,0,20,15),(12,'Emre Can','Alemania','Centrocampista',24,3,56,20,10,0,14,15),(13,'Paul Pogba','Francia','Centrocampista',25,6,42,23,1,0,20,16),(14,'David de Gea','España','Portero',27,0,50,2,0,48,0,16),(15,'Oliver Giroud','Francia','Delantero',31,7,20,4,0,0,32,17),(16,'Marcos Alonso','España','Defensa',27,2,41,6,0,0,4,17),(17,'Mesut Ozil','Alemania','Centrocampista',29,10,40,7,0,0,15,18),(18,'Skhodran Mustafi','Alemania','Defensa',29,0,36,12,5,0,0,18),(19,'Harry Kane','Reino Unido','Delantero',24,20,20,1,0,0,20,19),(20,'Dele Alli','Reino Unido','Centrocampista',22,0,40,10,2,0,1,19);
/*!40000 ALTER TABLE `jugador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `liga`
--

DROP TABLE IF EXISTS `liga`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `liga` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) DEFAULT NULL,
  `pais` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `liga`
--

LOCK TABLES `liga` WRITE;
/*!40000 ALTER TABLE `liga` DISABLE KEYS */;
INSERT INTO `liga` VALUES (1,'Liga Santander','España'),(2,'Lega Calcio','Italia'),(3,'Premier League','Inglaterra');
/*!40000 ALTER TABLE `liga` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `partido`
--

DROP TABLE IF EXISTS `partido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `partido` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `local_id` int(11) DEFAULT NULL,
  `visitante_id` int(11) DEFAULT NULL,
  `jornada_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_visitante_idx` (`visitante_id`),
  KEY `fk_local_idx` (`local_id`),
  KEY `fk_partido_jornada_idx` (`jornada_id`),
  CONSTRAINT `FK6kfgivo09t0abinel3wdrxkbu` FOREIGN KEY (`jornada_id`) REFERENCES `jornada` (`id`),
  CONSTRAINT `FK8holox9q23gyen9w53t8rrnys` FOREIGN KEY (`visitante_id`) REFERENCES `equipo` (`id`),
  CONSTRAINT `FKhvg714ja4vkwrdgks4lrenlty` FOREIGN KEY (`local_id`) REFERENCES `equipo` (`id`),
  CONSTRAINT `fk_local` FOREIGN KEY (`local_id`) REFERENCES `equipo` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_partido_jornada` FOREIGN KEY (`jornada_id`) REFERENCES `jornada` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_visitante` FOREIGN KEY (`visitante_id`) REFERENCES `equipo` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `partido`
--

LOCK TABLES `partido` WRITE;
/*!40000 ALTER TABLE `partido` DISABLE KEYS */;
INSERT INTO `partido` VALUES (1,11,10,1),(2,11,12,2),(3,12,14,3),(4,13,10,4),(5,10,11,5),(6,13,11,6),(7,10,11,7),(8,15,16,8),(9,14,12,9),(10,10,13,10),(11,11,10,11),(12,13,11,NULL);
/*!40000 ALTER TABLE `partido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tecnico`
--

DROP TABLE IF EXISTS `tecnico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tecnico` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) DEFAULT NULL,
  `puesto` varchar(100) DEFAULT NULL,
  `equipo_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_tecnico_equipo_idx` (`equipo_id`),
  CONSTRAINT `FKgux7jbudl70cjxgcqrdj70bp2` FOREIGN KEY (`equipo_id`) REFERENCES `equipo` (`id`),
  CONSTRAINT `fk_tecnico_equipo` FOREIGN KEY (`equipo_id`) REFERENCES `equipo` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tecnico`
--

LOCK TABLES `tecnico` WRITE;
/*!40000 ALTER TABLE `tecnico` DISABLE KEYS */;
INSERT INTO `tecnico` VALUES (1,'Quique Setién','Entrenador',10);
/*!40000 ALTER TABLE `tecnico` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'leaguetad'
--

--
-- Dumping routines for database 'leaguetad'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-05-18 14:09:54
