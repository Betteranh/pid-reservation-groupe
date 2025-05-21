-- MySQL dump 10.13  Distrib 9.1.0, for Win64 (x86_64)
--
-- Host: localhost    Database: reservations
-- ------------------------------------------------------
-- Server version	9.1.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `artist_type`
--

DROP TABLE IF EXISTS `artist_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `artist_type` (
  `id` int NOT NULL AUTO_INCREMENT,
  `artist_id` int NOT NULL,
  `type_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `artist_type_artist_id_ IDX_3060D1B6B7970CF8_fk_artists_id` (`artist_id`),
  KEY `artist_type_type_id_ IDX_3060D1B6C54C8C93_fk_types_id` (`type_id`),
  CONSTRAINT `artist_type_artist_id_ 3060D1B6B7970CF8_fk_artists_id` FOREIGN KEY (`artist_id`) REFERENCES `artists` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `artist_type_type_id_ 3060D1B6C54C8C93 _fk_artists_id` FOREIGN KEY (`type_id`) REFERENCES `types` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `artist_type`
--

LOCK TABLES `artist_type` WRITE;
/*!40000 ALTER TABLE `artist_type` DISABLE KEYS */;
INSERT INTO `artist_type` VALUES (1,1,1),(2,2,1),(3,1,2),(4,2,2),(5,1,3),(6,3,1),(7,4,2),(8,5,3),(9,6,3),(10,7,3),(11,8,3),(12,9,3),(13,10,1),(14,11,2),(15,10,3),(16,12,1),(17,13,1),(18,12,3),(19,13,3),(20,14,1),(21,15,2),(22,16,3),(23,17,1),(24,18,2),(25,19,3),(26,20,1),(27,21,2),(28,22,3),(29,23,1),(30,24,2),(31,25,3),(32,26,1),(33,27,2),(34,28,3),(35,29,1),(36,30,2),(37,31,3);
/*!40000 ALTER TABLE `artist_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `artist_type_show`
--

DROP TABLE IF EXISTS `artist_type_show`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `artist_type_show` (
  `id` int NOT NULL AUTO_INCREMENT,
  `artist_type_id` int NOT NULL,
  `show_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_9F6421FED0C1FC64` (`show_id`),
  KEY `IDX_9F6421FE7203D2A4` (`artist_type_id`),
  CONSTRAINT `FK_9F6421FE7203D2A4` FOREIGN KEY (`artist_type_id`) REFERENCES `artist_type` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `FK_9F6421FED0C1FC64` FOREIGN KEY (`show_id`) REFERENCES `shows` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `artist_type_show`
--

LOCK TABLES `artist_type_show` WRITE;
/*!40000 ALTER TABLE `artist_type_show` DISABLE KEYS */;
INSERT INTO `artist_type_show` VALUES (1,1,1),(2,2,1),(3,3,1),(4,4,1),(5,5,1),(6,6,2),(7,7,2),(8,8,2),(9,9,2),(10,10,2),(11,11,2),(12,12,2),(13,13,3),(14,14,3),(15,15,3),(16,4,4),(17,16,4),(18,17,4),(19,18,4),(20,19,4),(21,20,5),(22,21,5),(23,22,5),(24,23,6),(25,24,6),(26,25,6),(27,26,7),(28,27,7),(29,28,7),(30,29,8),(31,30,8),(32,31,8),(33,32,9),(34,33,9),(35,34,9),(36,35,10),(37,36,10),(38,37,10);
/*!40000 ALTER TABLE `artist_type_show` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `artists`
--

DROP TABLE IF EXISTS `artists`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `artists` (
  `id` int NOT NULL AUTO_INCREMENT,
  `firstname` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `lastname` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `artists`
--

LOCK TABLES `artists` WRITE;
/*!40000 ALTER TABLE `artists` DISABLE KEYS */;
INSERT INTO `artists` VALUES (1,'Daniel','Marcelin'),(2,'Philippe','Laurent'),(3,'Marius','Von Mayenburg'),(4,'Olivier','Boudon'),(5,'Anne Marie','Loop'),(6,'Pietro','Varasso'),(7,'Laurent','Caron'),(8,'Élena','Perez'),(9,'Guillaume','Alexandre'),(10,'Claude','Semal'),(11,'Laurence','Warin'),(12,'Pierre','Wayburn'),(13,'Gwendoline','Gauthier'),(14,'Jacques','Brel'),(15,'Salvatore','Adamo'),(16,'Stromae','Cowboy'),(17,'Angèle','Van Laeken'),(18,'Arno','Hintjens'),(19,'Alex','Callier'),(20,'Wouter','De Backer'),(21,'Jean','Dupont'),(22,'Marie','Martin'),(23,'Paul','Moreau'),(24,'Sophie','Lefèvre'),(25,'Antoine','Petit'),(26,'Claire','Laurent'),(27,'Nicolas','Roche'),(28,'Émilie','Dubois'),(29,'Guillaume','Durand'),(30,'Camille','Leroy'),(31,'Jan','De Vries'),(32,'Piet','Janssen'),(33,'Karel','De Bruin'),(34,'Femke','Visser'),(35,'Bram','Smit'),(36,'Sanne','Bakker'),(37,'Tom','Verhoeven'),(38,'Lotte','De Wit'),(39,'Daan','Van Dijk'),(40,'Emma','De Lange'),(41,'Michael','Smith'),(42,'Jennifer','Johnson'),(43,'William','Brown'),(44,'Elizabeth','Davis'),(45,'James','Wilson'),(46,'Mary','Taylor'),(47,'Robert','Anderson'),(48,'Patricia','Thomas'),(49,'David','Jackson'),(50,'Linda','White'),(51,'Martin','Robinson'),(52,'Sophie','Edwards'),(53,'Alexandre','Bernard'),(54,'Chloé','Fontaine'),(55,'Laurent','Marchand'),(56,'Isabelle','Garnier'),(57,'Sébastien','Girard'),(58,'Manon','Petitjean'),(59,'Olivier','Dupuis'),(60,'Juliette','Renault'),(61,'Hugo','Rousseau'),(62,'Élise','Fontaine'),(63,'Pascal','Martin'),(64,'Claire','Fournier'),(65,'Laurent','Morel'),(66,'Amandine','Caron'),(67,'Vincent','Lefèvre'),(68,'Audrey','Mercier'),(69,'Romain','Gauthier'),(70,'Marine','Dupont'),(71,'Dirk','Vermeer'),(72,'Anke','Van den Berg'),(73,'Tim','De Jong'),(74,'Eva','De Graaf'),(75,'Joost','De Ruiter'),(76,'Lisa','Veenstra'),(77,'Bas','Mulder'),(78,'Marieke','De Klerk'),(79,'Rik','Bos'),(80,'Sander','De Vries'),(81,'Oliver','Jones'),(82,'Amelia','Harris'),(83,'Benjamin','Thompson'),(84,'Charlotte','Martin'),(85,'Henry','Walker'),(86,'Grace','Robinson'),(87,'Samuel','Lewis'),(88,'Victoria','Clark'),(89,'Jack','Hall'),(90,'Emily','Allen'),(91,'Mathieu','Bernard'),(92,'Fiona','Gallagher'),(93,'Sophie','Rousseau'),(94,'Leo','Gibson'),(95,'Emma','Cooper'),(96,'Liam','Carter'),(97,'Zoe','Marshall'),(98,'Mason','Scott'),(99,'Ava','Peterson'),(100,'Oliver','Wright');
/*!40000 ALTER TABLE `artists` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flyway_schema_history`
--

DROP TABLE IF EXISTS `flyway_schema_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `flyway_schema_history` (
  `installed_rank` int NOT NULL,
  `version` varchar(50) DEFAULT NULL,
  `description` varchar(200) NOT NULL,
  `type` varchar(20) NOT NULL,
  `script` varchar(1000) NOT NULL,
  `checksum` int DEFAULT NULL,
  `installed_by` varchar(100) NOT NULL,
  `installed_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `execution_time` int NOT NULL,
  `success` tinyint(1) NOT NULL,
  PRIMARY KEY (`installed_rank`),
  KEY `flyway_schema_history_s_idx` (`success`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flyway_schema_history`
--

LOCK TABLES `flyway_schema_history` WRITE;
/*!40000 ALTER TABLE `flyway_schema_history` DISABLE KEYS */;
INSERT INTO `flyway_schema_history` VALUES (1,'1.1','create artists table','SQL','V1_1__create_artists_table.sql',1930805307,'root','2025-05-20 23:13:20',490,1),(2,'1.2','insert artists table','SQL','V1_2__insert_artists_table.sql',1711840850,'root','2025-05-20 23:13:20',5,1),(3,'2.1','create users table','SQL','V2_1__create_users_table.sql',433411370,'root','2025-05-20 23:13:20',18,1),(4,'2.2','insert users table','SQL','V2_2__insert_users_table.sql',1830020814,'root','2025-05-20 23:13:20',6,1),(5,'3.1','create types table','SQL','V3_1__create_types_table.sql',-714946083,'root','2025-05-20 23:13:21',15,1),(6,'3.2','insert types table','SQL','V3_2__insert_types_table.sql',-1224026954,'root','2025-05-20 23:13:21',4,1),(7,'4.1','create localities table','SQL','V4_1__create_localities_table.sql',877901794,'root','2025-05-20 23:13:21',17,1),(8,'4.2','insert localities table','SQL','V4_2__insert_localities_table.sql',-694845285,'root','2025-05-20 23:13:21',3,1),(9,'5.1','create roles table','SQL','V5_1__create_roles_table.sql',-250331844,'root','2025-05-20 23:13:21',18,1),(10,'5.2','insert roles table','SQL','V5_2__insert_roles_table.sql',-1425221773,'root','2025-05-20 23:13:21',3,1),(11,'6.1','create locations table','SQL','V6_1__create_locations_table.sql',613662933,'root','2025-05-20 23:13:21',86,1),(12,'6.2','insert locations table','SQL','V6_2__insert_locations_table.sql',-2107314338,'root','2025-05-20 23:13:21',14,1),(13,'7.1','create shows table','SQL','V7_1__create_shows_table.sql',-251778180,'root','2025-05-20 23:13:21',82,1),(14,'7.2','insert shows table','SQL','V7_2__insert_shows_table.sql',221623019,'root','2025-05-20 23:13:21',2,1),(15,'8.1','create representations table','SQL','V8_1__create_representations_table.sql',-326404921,'root','2025-05-20 23:13:21',135,1),(16,'8.2','insert representations table','SQL','V8_2__insert_representations_table.sql',1441833475,'root','2025-05-20 23:13:21',4,1),(17,'9.1','create artist type table','SQL','V9_1__create_artist_type_table.sql',-865740315,'root','2025-05-20 23:13:21',135,1),(18,'9.2','insert artist type table','SQL','V9_2__insert_artist_type_table.sql',1045350802,'root','2025-05-20 23:13:21',3,1),(19,'10.1','create artist type show table','SQL','V10_1__create_artist_type_show_table.sql',716058841,'root','2025-05-20 23:13:21',91,1),(20,'10.2','insert artist type show table','SQL','V10_2__insert_artist_type_show_table.sql',742901213,'root','2025-05-20 23:13:21',3,1),(21,'11.1','create user role table','SQL','V11_1__create_user_role_table.sql',1943854436,'root','2025-05-20 23:13:22',135,1),(22,'11.2','insert user role table','SQL','V11_2__insert_user_role_table.sql',-382620278,'root','2025-05-20 23:13:22',2,1),(23,'12.1','create reservations table','SQL','V12_1__create_reservations_table.sql',-933945556,'root','2025-05-20 23:13:22',71,1),(24,'12.2','insert reservations table','SQL','V12_2__insert_reservations_table.sql',399871119,'root','2025-05-20 23:13:22',3,1),(25,'13.1','create prices table','SQL','V13_1__create_prices_table.sql',-1676046384,'root','2025-05-20 23:13:22',28,1),(26,'13.2','insert prices table','SQL','V13_2__insert_prices_table.sql',-747473909,'root','2025-05-20 23:13:22',2,1),(27,'14.1','create representation reservation table','SQL','V14_1__create_representation_reservation_table.sql',-1813757770,'root','2025-05-20 23:13:22',99,1),(28,'15.1','create reviews table','SQL','V15_1__create_reviews_table.sql',-459061990,'root','2025-05-20 23:13:22',81,1),(29,'16.1','create show price table','SQL','V16_1__create_show_price_table.sql',284933353,'root','2025-05-20 23:13:22',88,1),(30,'16.2','insert show price table','SQL','V16_2__insert_show_price_table.sql',-775025755,'root','2025-05-20 23:13:22',2,1),(31,'17.1','create tags table','SQL','V17_1__create_tags_table.sql',357458051,'root','2025-05-20 23:13:22',7,1),(32,'17.2','insert tags table','SQL','V17_2__insert_tags_table.sql',2130966173,'root','2025-05-20 23:13:22',3,1),(33,'18.1','create show tag table','SQL','V18_1__create_show_tag_table.sql',386781069,'root','2025-05-20 23:13:22',6,1),(34,'19.1','create videos table','SQL','V19_1__create_videos_table.sql',-891588289,'root','2025-05-20 23:13:22',25,1),(35,'19.2','insert videos table','SQL','V19_2__insert_videos_table.sql',745622660,'root','2025-05-20 23:13:22',2,1);
/*!40000 ALTER TABLE `flyway_schema_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `localities`
--

DROP TABLE IF EXISTS `localities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `localities` (
  `id` int NOT NULL AUTO_INCREMENT,
  `postal_code` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `locality` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `localities`
--

LOCK TABLES `localities` WRITE;
/*!40000 ALTER TABLE `localities` DISABLE KEYS */;
INSERT INTO `localities` VALUES (1,'1000','Bruxelles'),(2,'1020','Laeken'),(3,'1030','Schaerbeek'),(4,'1170','Watermael-Boistfort'),(5,'1040','Etterbeek'),(6,'1050','Ixelles'),(7,'1060','Saint-Gilles'),(8,'1070','Anderlecht'),(9,'1080','Molenbeek-Saint-Jean'),(10,'1090','Forest'),(11,'4000','Liège'),(12,'4020','Seraing'),(13,'5000','Namur'),(14,'6000','Charleroi'),(15,'7000','Mons'),(16,'8000','Bruges'),(17,'9000','Ghent'),(18,'2000','Anvers'),(19,'3000','Leuven'),(20,'3500','Hasselt'),(21,'8400','Oostende');
/*!40000 ALTER TABLE `localities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `locations`
--

DROP TABLE IF EXISTS `locations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `locations` (
  `id` int NOT NULL AUTO_INCREMENT,
  `slug` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `designation` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `locality_id` int NOT NULL,
  `website` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `phone` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `capacity` int NOT NULL DEFAULT '100',
  PRIMARY KEY (`id`),
  UNIQUE KEY `slug` (`slug`),
  KEY `locations_locality_id_22dd0b44_fk_localities_id` (`locality_id`),
  CONSTRAINT `locations_locality_id_22dd0b44_fk_localities_id` FOREIGN KEY (`locality_id`) REFERENCES `localities` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `locations`
--

LOCK TABLES `locations` WRITE;
/*!40000 ALTER TABLE `locations` DISABLE KEYS */;
INSERT INTO `locations` VALUES (1,'espace-delvaux-la-venerie','Espace Delvaux / La Vénerie','3 rue Gratès',4,'https://www.lavenerie.be','+32 (0)2/663.85.50',100),(2,'dexia-art-center','Dexia Art Center','50 rue de l\'Ecuyer',1,NULL,NULL,100),(3,'la-samaritaine','La Samaritaine','16 rue de la samaritaine',1,'http://www.lasamaritaine.be/',NULL,100),(4,'espace-magh','Espace Magh','17 rue du Poinçon',1,'http://www.espacemagh.be','+32 (0)2/274.05.10',100),(5,'theatre-national-de-bruxelles','Théâtre National de Bruxelles','Boulevard du Jardin Botanique 59, 1000 Bruxelles',1,'http://www.tnb.be','+32 2 513 28 00',100),(6,'centre-culturel-laeken','Centre Culturel de Laeken','Rue du Centre 99, 1020 Laeken',2,'https://www.centreculturellaeken.be','+32 2 600 00 00',100),(7,'salle-des-fetes-laeken','Salle des Fêtes de Laeken','Avenue de la Fête 12, 1020 Laeken',2,'http://www.saledesfeteslaeken.be','+32 2 600 01 02',100),(8,'theatre-schaerbeek','Théâtre de Schaerbeek','Avenue des Arts 8, 1030 Schaerbeek',3,'http://www.theatredeschaerbeek.be','+32 2 234 56 78',100),(9,'salle-des-fetes-schaerbeek','Salle des Fêtes de Schaerbeek','Rue de Schaerbeek 12, 1030 Schaerbeek',3,'http://www.sallefetes-schaerbeek.be','+32 2 123 45 67',100),(10,'salle-polyvalente-watermael','Salle Polyvalente Watermael','Rue de Watermael 5, 1170 Watermael-Boistfort',4,'http://www.sallewatermael.be','+32 2 345 67 89',100),(11,'theatre-etterbeek','Théâtre d\'Etterbeek','Avenue d\'Etterbeek 10, 1040 Etterbeek',5,'http://www.theatre-etterbeek.be','+32 2 987 65 43',100),(12,'salle-polyvalente-etterbeek','Salle Polyvalente d\'Etterbeek','Rue de la Culture 5, 1040 Etterbeek',5,'http://www.salleetterbeek.be','+32 2 876 54 32',100),(13,'le-botanique','Le Botanique','Chaussée d\'Ixelles 134, 1050 Ixelles',6,'https://botanique.be','+32 2 588 50 00',100),(14,'theatre-ixelles','Théâtre d\'Ixelles','Rue de Bruxelles 45, 1050 Ixelles',6,'http://www.theatreixelles.be','+32 2 499 33 22',100),(15,'theatre-saint-gilles','Théâtre de Saint-Gilles','Rue de la Station 47, 1060 Saint-Gilles',7,'http://www.theatrestgilles.be','+32 2 555 55 55',100),(16,'salle-des-fetes-saint-gilles','Salle des Fêtes de Saint-Gilles','Avenue du Progrès 25, 1060 Saint-Gilles',7,'http://www.fetes-saintgilles.be','+32 2 567 89 01',100),(17,'theatre-anderlecht','Théâtre d\'Anderlecht','Rue du Drapeau 5, 1070 Anderlecht',8,'https://www.theatre-anderlecht.be','+32 2 444 44 44',100),(18,'salle-culturelle-anderlecht','Salle Culturelle d\'Anderlecht','Boulevard de la Culture 9, 1070 Anderlecht',8,'http://www.salleculturale-anderlecht.be','+32 2 453 67 89',100),(19,'theatre-molenbeek','Théâtre de Molenbeek','Avenue de Molenbeek 22, 1080 Molenbeek-Saint-Jean',9,'http://www.theatremolenbeek.be','+32 2 666 66 66',100),(20,'salle-des-fetes-molenbeek','Salle des Fêtes de Molenbeek','Rue des Fêtes 3, 1080 Molenbeek-Saint-Jean',9,'http://www.fetesmolenbeek.be','+32 2 654 32 10',100),(21,'theatre-forest','Théâtre de Forest','Rue des Chasseurs 1, 1090 Forest',10,'http://www.theatreforest.be','+32 2 777 77 77',100),(22,'salle-polyvalente-forest','Salle Polyvalente de Forest','Boulevard de Forest 20, 1090 Forest',10,'http://www.polyforest.be','+32 2 888 88 88',100),(23,'opera-royal-wallonie','Opéra Royal de Wallonie','Rue de l\'Opéra 1, 4000 Liège',11,'https://www.operaliege.be','+32 4 123 45 67',100),(24,'salle-jeune-garde-liege','Salle de la Jeune Garde','Avenue de la Jeune Garde 7, 4000 Liège',11,'http://www.jeunegardeliege.be','+32 4 234 56 78',100),(25,'theatre-seraing','Théâtre de Seraing','Rue de l\'Industrie 15, 4020 Seraing',12,'http://www.theatreseraing.be','+32 4 234 56 78',100),(26,'salle-polyvalente-seraing','Salle Polyvalente de Seraing','Avenue des Fêtes 8, 4020 Seraing',12,'http://www.fetesseraing.be','+32 4 345 67 89',100),(27,'theatre-royal-namur','Théâtre Royal de Namur','Boulevard de la Reine 2, 5000 Namur',13,'http://www.theatrenamur.be','+32 81 23 45 67',100),(28,'salle-des-fetes-namur','Salle des Fêtes de Namur','Rue de Namur 12, 5000 Namur',13,'http://www.fetesnamur.be','+32 81 34 56 78',100),(29,'theatre-charleroi','Théâtre de Charleroi','Rue du Théâtre 10, 6000 Charleroi',14,'http://www.theatre-charleroi.be','+32 71 12 34 56',100),(30,'salle-polyvalente-charleroi','Salle Polyvalente de Charleroi','Avenue des Arts 25, 6000 Charleroi',14,'http://www.polycharleroi.be','+32 71 23 45 67',100),(31,'theatre-royal-mons','Théâtre Royal de Mons','Rue de Mons 1, 7000 Mons',15,'http://www.theatremons.be','+32 65 12 34 56',100),(32,'salle-des-fetes-mons','Salle des Fêtes de Mons','Boulevard des Fêtes 5, 7000 Mons',15,'http://www.fetesmons.be','+32 65 23 45 67',100),(33,'theatre-bruges','Théâtre de Bruges','Kasteelstraat 20, 8000 Bruges',16,'http://www.theatrebruges.be','+32 50 123 45 67',100),(34,'salle-polyvalente-bruges','Salle Polyvalente de Bruges','Lange Steenstraat 30, 8000 Bruges',16,'http://www.polybruges.be','+32 50 234 56 78',100),(35,'de-vooruit','De Vooruit','Korenmarkt 1, 9000 Ghent',17,'https://www.devooruit.be','+32 9 234 56 78',100),(36,'theatre-gent','Théâtre de Ghent','Veldstraat 50, 9000 Ghent',17,'http://www.theatredelgent.be','+32 9 345 67 89',100),(37,'salle-culturelle-gent','Salle Culturelle de Ghent','Graanmarkt 8, 9000 Ghent',17,'http://www.salleculturaleghent.be','+32 9 456 78 90',100),(38,'de-roma','De Roma','Schilderstraat 10, 2000 Anvers',18,'http://www.roma.be','+32 3 123 45 67',100),(39,'salle-polyvalente-anvers','Salle Polyvalente d\'Anvers','Lindengracht 15, 2000 Anvers',18,'http://www.polyanvers.be','+32 3 234 56 78',100),(40,'stuk-leuven','STUK Leuven','Ladeuzeplein 1, 3000 Leuven',19,'http://www.stuk.be','+32 16 23 45 67',100),(41,'theatre-leuven','Théâtre de Leuven','Diestsesteenweg 60, 3000 Leuven',19,'http://www.theatredeluven.be','+32 16 34 56 78',100),(42,'theatre-hasselt','Théâtre de Hasselt','Grote Markt 5, 3500 Hasselt',20,'http://www.theatrehasselt.be','+32 11 123 45 67',100),(43,'salle-polyvalente-hasselt','Salle Polyvalente de Hasselt','Kerkplein 2, 3500 Hasselt',20,'http://www.polyhasselt.be','+32 11 234 56 78',100),(44,'theatre-oostende','Théâtre d\'Oostende','Zeedijk 15, 8400 Oostende',21,'http://www.theatreoostende.be','+32 59 234 56 78',100),(45,'salle-des-fetes-oostende','Salle des Fêtes d\'Oostende','Rue du Port 10, 8400 Oostende',21,'http://www.fetesoostende.be','+32 59 345 67 89',100),(46,'theatre-royal-de-la-monnaie','Théâtre Royal de la Monnaie','Place de la Monnaie, 1000 Bruxelles',1,'https://www.lamonnaie.be','+32 2 512 33 11',100),(47,'ancienne-belgique','Ancienne Belgique','Rue Dansaert 58, 1000 Bruxelles',1,'http://www.abconcerts.be','+32 2 515 99 00',100);
/*!40000 ALTER TABLE `locations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prices`
--

DROP TABLE IF EXISTS `prices`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prices` (
  `id` int NOT NULL AUTO_INCREMENT,
  `type` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `prices_id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prices`
--

LOCK TABLES `prices` WRITE;
/*!40000 ALTER TABLE `prices` DISABLE KEYS */;
INSERT INTO `prices` VALUES (1,'Tarif plein - Ayiti',18.00,'2025-01-01','2025-12-31'),(2,'Tarif réduit - Ayiti',12.00,'2025-01-01','2025-12-31'),(3,'Tarif plein - Cible mouvante',20.00,'2025-01-01','2025-12-31'),(4,'Étudiant - Cible mouvante',10.00,'2025-01-01','2025-12-31'),(5,'Standard - Chanteur belge',15.00,'2025-01-01','2025-12-31'),(6,'Senior - Chanteur belge',12.00,'2025-01-01','2025-12-31'),(7,'Tarif normal - Manneke',17.00,'2025-01-01','2025-12-31'),(8,'Tarif enfant - Manneke',8.00,'2025-01-01','2025-12-31'),(9,'Tarif plein - Cyrano',22.00,'2025-01-01','2025-12-31'),(10,'Tarif réduit - Cyrano',15.00,'2025-01-01','2025-12-31'),(11,'Tarif plein - Malade',19.00,'2025-01-01','2025-12-31'),(12,'Tarif étudiant - Malade',11.00,'2025-01-01','2025-12-31'),(13,'Standard - Godot',16.00,'2025-01-01','2025-12-31'),(14,'Tarif réduit - Godot',10.00,'2025-01-01','2025-12-31'),(15,'Tarif plein - Femmes',17.00,'2025-01-01','2025-12-31'),(16,'Tarif réduit - Femmes',12.00,'2025-01-01','2025-12-31'),(17,'Tarif plein - Le Cid',21.00,'2025-01-01','2025-12-31'),(18,'Tarif réduit - Le Cid',14.00,'2025-01-01','2025-12-31'),(19,'Tarif plein - Rhino',20.00,'2025-01-01','2025-12-31'),(20,'Tarif réduit - Rhino',13.00,'2025-01-01','2025-12-31');
/*!40000 ALTER TABLE `prices` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `representation_reservation`
--

DROP TABLE IF EXISTS `representation_reservation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `representation_reservation` (
  `id` int NOT NULL AUTO_INCREMENT,
  `representation_id` int DEFAULT NULL,
  `reservation_id` int DEFAULT NULL,
  `price_id` int DEFAULT NULL,
  `quantity` tinyint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `representation_reservation_representation_id` (`representation_id`),
  KEY `representation_reservation_reservation_id` (`reservation_id`),
  KEY `representation_reservation_price_id` (`price_id`),
  CONSTRAINT `representation_reservation_price_id` FOREIGN KEY (`price_id`) REFERENCES `prices` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `representation_reservation_representation_id` FOREIGN KEY (`representation_id`) REFERENCES `representations` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `representation_reservation_reservation_id` FOREIGN KEY (`reservation_id`) REFERENCES `reservations` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `representation_reservation`
--

LOCK TABLES `representation_reservation` WRITE;
/*!40000 ALTER TABLE `representation_reservation` DISABLE KEYS */;
/*!40000 ALTER TABLE `representation_reservation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `representations`
--

DROP TABLE IF EXISTS `representations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `representations` (
  `id` int NOT NULL AUTO_INCREMENT,
  `show_id` int NOT NULL,
  `location_id` int DEFAULT NULL,
  `when` datetime(6) NOT NULL,
  `capacity` int NOT NULL DEFAULT '100',
  PRIMARY KEY (`id`),
  KEY `representations_location_id_a6832141_fk_locations_id` (`location_id`),
  KEY `representations_show_id_a6832141_fk_shows_id` (`show_id`),
  CONSTRAINT `representations_location_id_a6832141_fk_locations_id` FOREIGN KEY (`location_id`) REFERENCES `locations` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `representations_show_id_a6832141_fk_shows_id` FOREIGN KEY (`show_id`) REFERENCES `shows` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `representations`
--

LOCK TABLES `representations` WRITE;
/*!40000 ALTER TABLE `representations` DISABLE KEYS */;
INSERT INTO `representations` VALUES (1,1,1,'2025-06-05 20:00:00.000000',100),(2,1,1,'2025-06-12 20:00:00.000000',100),(3,1,1,'2025-06-19 20:00:00.000000',100),(4,2,2,'2025-06-06 20:00:00.000000',100),(5,2,2,'2025-06-13 20:00:00.000000',100),(6,3,NULL,'2025-06-07 20:00:00.000000',100),(7,3,NULL,'2025-06-14 20:00:00.000000',100),(8,3,NULL,'2025-06-21 20:00:00.000000',100),(9,3,NULL,'2025-06-28 20:00:00.000000',100),(10,4,3,'2025-06-08 20:30:00.000000',100),(11,5,5,'2025-07-02 20:00:00.000000',100),(12,5,5,'2025-07-09 20:00:00.000000',100),(13,5,5,'2025-07-16 20:00:00.000000',100),(14,5,5,'2025-07-23 20:00:00.000000',100),(15,5,5,'2025-07-30 20:00:00.000000',100),(16,6,11,'2025-07-03 20:00:00.000000',100),(17,6,11,'2025-07-10 20:00:00.000000',100),(18,6,11,'2025-07-17 20:00:00.000000',100),(19,7,17,'2025-07-04 20:00:00.000000',100),(20,7,17,'2025-07-11 20:00:00.000000',100),(21,8,27,'2025-07-05 20:00:00.000000',100),(22,8,27,'2025-07-12 20:00:00.000000',100),(23,8,27,'2025-07-19 20:00:00.000000',100),(24,8,27,'2025-07-26 20:00:00.000000',100),(25,9,29,'2025-07-06 20:00:00.000000',100),(26,9,29,'2025-07-13 20:00:00.000000',100),(27,9,29,'2025-07-20 20:00:00.000000',100),(28,10,35,'2025-07-07 20:00:00.000000',100);
/*!40000 ALTER TABLE `representations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservations`
--

DROP TABLE IF EXISTS `reservations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reservations` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `booking_date` datetime DEFAULT NULL,
  `status` varchar(60) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `reservations_user_id` (`user_id`),
  CONSTRAINT `reservations_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservations`
--

LOCK TABLES `reservations` WRITE;
/*!40000 ALTER TABLE `reservations` DISABLE KEYS */;
INSERT INTO `reservations` VALUES (1,1,'2025-01-15 10:00:00','confirmed'),(2,2,'2025-01-15 10:00:00','confirmed'),(3,3,'2025-01-16 09:00:00','confirmed'),(4,4,'2025-01-17 09:30:00','confirmed'),(5,5,'2025-01-18 10:00:00','confirmed'),(6,6,'2025-01-19 10:30:00','confirmed'),(7,7,'2025-01-20 11:00:00','confirmed'),(8,8,'2025-01-21 11:30:00','confirmed'),(9,9,'2025-01-22 12:00:00','confirmed'),(10,10,'2025-01-23 12:30:00','confirmed'),(11,11,'2025-01-24 13:00:00','confirmed'),(12,12,'2025-01-25 13:30:00','confirmed'),(13,13,'2025-01-26 14:00:00','confirmed'),(14,14,'2025-01-27 14:30:00','confirmed'),(15,15,'2025-01-28 15:00:00','confirmed'),(16,16,'2025-01-29 15:30:00','confirmed'),(17,17,'2025-01-30 16:00:00','confirmed'),(18,18,'2025-01-31 16:30:00','confirmed'),(19,19,'2025-02-01 17:00:00','confirmed'),(20,20,'2025-02-02 17:30:00','confirmed'),(21,21,'2025-02-03 09:15:00','confirmed'),(22,22,'2025-02-04 09:45:00','confirmed'),(23,23,'2025-02-05 10:15:00','confirmed'),(24,24,'2025-02-06 10:45:00','confirmed'),(25,25,'2025-02-07 11:15:00','confirmed'),(26,26,'2025-02-08 11:45:00','confirmed'),(27,27,'2025-02-09 12:15:00','confirmed'),(28,28,'2025-02-10 12:45:00','confirmed'),(29,29,'2025-02-11 13:15:00','confirmed'),(30,30,'2025-02-12 13:45:00','confirmed'),(31,31,'2025-02-13 14:15:00','confirmed'),(32,32,'2025-02-14 14:45:00','confirmed'),(33,33,'2025-02-15 15:15:00','confirmed'),(34,34,'2025-02-16 15:45:00','confirmed'),(35,35,'2025-02-17 16:15:00','confirmed'),(36,36,'2025-02-18 16:45:00','confirmed'),(37,37,'2025-02-19 17:15:00','confirmed'),(38,38,'2025-02-20 17:45:00','confirmed'),(39,39,'2025-02-21 09:00:00','confirmed'),(40,40,'2025-02-22 09:30:00','confirmed'),(41,41,'2025-02-23 10:00:00','confirmed'),(42,42,'2025-02-24 10:30:00','confirmed'),(43,43,'2025-02-25 11:00:00','confirmed'),(44,44,'2025-02-26 11:30:00','confirmed'),(45,45,'2025-02-27 12:00:00','confirmed'),(46,46,'2025-02-28 12:30:00','confirmed'),(47,47,'2025-03-01 13:00:00','confirmed'),(48,48,'2025-03-02 13:30:00','confirmed'),(49,49,'2025-03-03 14:00:00','confirmed'),(50,50,'2025-03-04 14:30:00','confirmed'),(51,51,'2025-03-05 15:00:00','confirmed'),(52,52,'2025-03-06 15:30:00','confirmed'),(53,53,'2025-03-07 16:00:00','confirmed'),(54,54,'2025-03-08 16:30:00','confirmed'),(55,55,'2025-03-09 17:00:00','confirmed'),(56,56,'2025-03-10 17:30:00','confirmed'),(57,57,'2025-03-11 09:15:00','confirmed'),(58,58,'2025-03-12 09:45:00','confirmed'),(59,59,'2025-03-13 10:15:00','confirmed'),(60,60,'2025-03-14 10:45:00','confirmed'),(61,61,'2025-03-15 11:15:00','confirmed'),(62,62,'2025-03-16 11:45:00','confirmed'),(63,63,'2025-03-17 12:15:00','confirmed'),(64,64,'2025-03-18 12:45:00','confirmed'),(65,65,'2025-03-19 13:15:00','confirmed'),(66,66,'2025-03-20 13:45:00','confirmed'),(67,67,'2025-03-21 14:15:00','confirmed'),(68,68,'2025-03-22 14:45:00','confirmed'),(69,69,'2025-03-23 15:15:00','confirmed'),(70,70,'2025-03-24 15:45:00','confirmed'),(71,71,'2025-03-25 16:15:00','confirmed'),(72,72,'2025-03-26 16:45:00','confirmed'),(73,73,'2025-03-27 17:15:00','confirmed'),(74,74,'2025-03-28 17:45:00','confirmed'),(75,75,'2025-03-29 09:00:00','confirmed'),(76,76,'2025-03-30 09:30:00','confirmed'),(77,77,'2025-03-31 10:00:00','confirmed'),(78,78,'2025-04-01 10:30:00','confirmed'),(79,79,'2025-04-02 11:00:00','confirmed'),(80,80,'2025-04-03 11:30:00','confirmed'),(81,81,'2025-04-04 12:00:00','confirmed'),(82,82,'2025-04-05 12:30:00','confirmed'),(83,83,'2025-04-06 13:00:00','confirmed'),(84,84,'2025-04-07 13:30:00','confirmed'),(85,85,'2025-04-08 14:00:00','confirmed'),(86,86,'2025-04-09 14:30:00','confirmed'),(87,87,'2025-04-10 15:00:00','confirmed'),(88,88,'2025-04-11 15:30:00','confirmed'),(89,89,'2025-04-12 16:00:00','confirmed'),(90,90,'2025-04-13 16:30:00','confirmed'),(91,91,'2025-04-14 17:00:00','confirmed'),(92,92,'2025-04-15 17:30:00','confirmed'),(93,93,'2025-04-16 09:15:00','confirmed'),(94,94,'2025-04-17 09:45:00','confirmed'),(95,95,'2025-04-18 10:15:00','confirmed'),(96,96,'2025-04-19 10:45:00','confirmed'),(97,97,'2025-04-20 11:15:00','confirmed'),(98,98,'2025-04-21 11:45:00','confirmed'),(99,99,'2025-04-22 12:15:00','confirmed'),(100,100,'2025-04-23 12:45:00','confirmed');
/*!40000 ALTER TABLE `reservations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reviews`
--

DROP TABLE IF EXISTS `reviews`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reviews` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `show_id` int NOT NULL,
  `review` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `stars` tinyint unsigned NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `reviews_user_id` (`user_id`),
  KEY `reviews_show_id` (`show_id`),
  CONSTRAINT `reviews_show_id` FOREIGN KEY (`show_id`) REFERENCES `shows` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `reviews_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reviews`
--

LOCK TABLES `reviews` WRITE;
/*!40000 ALTER TABLE `reviews` DISABLE KEYS */;
/*!40000 ALTER TABLE `reviews` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `id` int NOT NULL AUTO_INCREMENT,
  `role` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `role` (`role`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'admin'),(3,'affiliate'),(2,'member');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `show_price`
--

DROP TABLE IF EXISTS `show_price`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `show_price` (
  `id` int NOT NULL AUTO_INCREMENT,
  `show_id` int NOT NULL,
  `price_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `show_price_show_id` (`show_id`),
  KEY `show_price_price_id` (`price_id`),
  CONSTRAINT `fk_show_price_price` FOREIGN KEY (`price_id`) REFERENCES `prices` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_show_price_show` FOREIGN KEY (`show_id`) REFERENCES `shows` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `show_price`
--

LOCK TABLES `show_price` WRITE;
/*!40000 ALTER TABLE `show_price` DISABLE KEYS */;
INSERT INTO `show_price` VALUES (1,1,1),(2,1,2),(3,2,3),(4,2,4),(5,3,5),(6,3,6),(7,4,7),(8,4,8),(9,5,9),(10,5,10),(11,6,11),(12,6,12),(13,7,13),(14,7,14),(15,8,15),(16,8,16),(17,9,17),(18,9,18),(19,10,19),(20,10,20);
/*!40000 ALTER TABLE `show_price` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `show_tag`
--

DROP TABLE IF EXISTS `show_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `show_tag` (
  `show_id` bigint NOT NULL,
  `tag_id` bigint NOT NULL,
  PRIMARY KEY (`show_id`,`tag_id`),
  KEY `fk_tag` (`tag_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `show_tag`
--

LOCK TABLES `show_tag` WRITE;
/*!40000 ALTER TABLE `show_tag` DISABLE KEYS */;
/*!40000 ALTER TABLE `show_tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shows`
--

DROP TABLE IF EXISTS `shows`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shows` (
  `id` int NOT NULL AUTO_INCREMENT,
  `slug` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` longtext COLLATE utf8mb4_unicode_ci,
  `poster_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `bookable` tinyint(1) NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `location_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `slug` (`slug`),
  KEY `shows_location_id_a6832141_fk_locations_id` (`location_id`),
  CONSTRAINT `shows_location_id_a6832141_fk_locations_id` FOREIGN KEY (`location_id`) REFERENCES `locations` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shows`
--

LOCK TABLES `shows` WRITE;
/*!40000 ALTER TABLE `shows` DISABLE KEYS */;
INSERT INTO `shows` VALUES (1,'ayiti','Ayiti','Un homme est bloqué à l’aéroport.\n Questionné par les douaniers, il doit alors justifier son identité, et surtout prouver qu\'il est haïtien – qu\'est-ce qu\'être haïtien ?','ayiti.jpg',1,'2020-04-21 19:08:44.583000',NULL,1),(2,'cible-mouvante','Cible mouvante','Dans ce « thriller d’anticipation », des adultes semblent alimenter et véhiculer une crainte féroce envers les enfants âgés entre 10 et 12 ans.','cible.jpg',1,'2020-04-21 19:08:53.156000',NULL,2),(3,'ceci-nest-pas-un-chanteur-belge','Ceci n\'est pas un chanteur belge','Non peut-être ?!\nEntre Magritte (pour le surréalisme comique) et Maigret (pour le réalisme mélancolique), ce dixième opus semalien propose quatorze nouvelles chansons mêlées à de petits textes humoristiques et à quelques fortes images poétiques.','claudebelgesaison220.jpg',1,'2020-04-21 19:08:53.189000','2020-04-21 19:08:53.189000',NULL),(4,'manneke','Manneke… !','A tour de rôle, Pierre se joue de ses oncles, tantes, grands-parents et surtout de sa mère.','wayburn.jpg',1,'2020-04-21 19:09:02.426000','2020-04-21 19:09:02.426000',3),(5,'cyrano-de-bergerac','Cyrano de Bergerac','Une adaptation contemporaine du célèbre classique d’’Edmond Rostand, mêlant verve poétique et dialogues percutants.',NULL,1,'2025-03-01 10:00:00.000000',NULL,5),(6,'le-malade-imaginaire','Le Malade imaginaire','Une mise en scène moderne de la comédie-ballet de Molière, explorant la satire de la médecine et les travers humains.',NULL,1,'2025-03-02 10:00:00.000000',NULL,11),(7,'en-attendant-godot','En attendant Godot','La pièce emblématique de Samuel Beckett, interrogeant le sens de l’existence à travers l’attente interminable d’un personnage mythique.',NULL,1,'2025-03-03 10:00:00.000000',NULL,17),(8,'les-femmes-savantes','Les Femmes savantes','Une adaptation audacieuse de Molière mettant en lumière l’hyper-intellectualisation et les travers d’une famille bourgeoisie.',NULL,1,'2025-03-04 10:00:00.000000',NULL,27),(9,'le-cid','Le Cid','Un drame romantique inspiré par la légende de Rodrigue, où l’honneur et l’amour se confrontent dans un univers intemporel.',NULL,1,'2025-03-05 10:00:00.000000',NULL,29),(10,'rhinoceros','Rhinocéros','Une pièce absurde d’’Eugène Ionesco, dans laquelle la transformation des hommes en rhinocéros offre une métaphore saisissante de l’homogénéisation sociale.',NULL,1,'2025-03-06 10:00:00.000000',NULL,35);
/*!40000 ALTER TABLE `shows` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tags`
--

DROP TABLE IF EXISTS `tags`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tags` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `tag` varchar(30) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `tag` (`tag`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tags`
--

LOCK TABLES `tags` WRITE;
/*!40000 ALTER TABLE `tags` DISABLE KEYS */;
INSERT INTO `tags` VALUES (1,'famille'),(2,'drame'),(3,'comédie'),(4,'jeune public'),(5,'musique'),(6,'classique'),(7,'aventure'),(8,'nouveauté');
/*!40000 ALTER TABLE `tags` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `types`
--

DROP TABLE IF EXISTS `types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `types` (
  `id` int NOT NULL AUTO_INCREMENT,
  `type` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `types`
--

LOCK TABLES `types` WRITE;
/*!40000 ALTER TABLE `types` DISABLE KEYS */;
INSERT INTO `types` VALUES (1,'auteur'),(2,'scénographe'),(3,'comédien');
/*!40000 ALTER TABLE `types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `role_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_role_user_id` (`user_id`),
  KEY `user_role_role_id` (`role_id`),
  CONSTRAINT `user_role_role_id` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `user_role_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,1,1),(2,2,2),(3,3,3);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `login` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `firstname` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `lastname` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `langue` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `role` enum('ADMIN','MEMBER','AFFILIATE','PRESS','PRODUCER','') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'bob','$2a$12$EyDxX5wWsrJGIyo2MFIRR.LqLmmqssUdPgwasMrG7TvZmjW9ECeLW','Bob','Sull','bob@sull.com','fr','ADMIN','2010-01-01 12:00:00'),(2,'anna','$2a$12$EyDxX5wWsrJGIyo2MFIRR.LqLmmqssUdPgwasMrG7TvZmjW9ECeLW','Lana','Sull','lana@sull.com','fr','MEMBER','2010-01-01 12:00:00'),(3,'affiliate','$2a$12$EyDxX5wWsrJGIyo2MFIRR.LqLmmqssUdPgwasMrG7TvZmjW9ECeLW','Affi','Liate','contact@affiliate.com','fr','AFFILIATE','2020-01-01 12:00:00'),(4,'user4','$2a$12$EyDxX5wWsrJGIyo2MFIRR.LqLmmqssUdPgwasMrG7TvZmjW9ECeLW','UserFirstname4','UserLastname4','user4@example.com','fr','MEMBER','2025-01-01 00:00:00'),(5,'user5','$2a$12$EyDxX5wWsrJGIyo2MFIRR.LqLmmqssUdPgwasMrG7TvZmjW9ECeLW','UserFirstname5','UserLastname5','user5@example.com','fr','MEMBER','2025-01-01 00:00:00'),(6,'user6','$2a$12$EyDxX5wWsrJGIyo2MFIRR.LqLmmqssUdPgwasMrG7TvZmjW9ECeLW','UserFirstname6','UserLastname6','user6@example.com','fr','MEMBER','2025-01-01 00:00:00'),(7,'user7','$2a$12$EyDxX5wWsrJGIyo2MFIRR.LqLmmqssUdPgwasMrG7TvZmjW9ECeLW','UserFirstname7','UserLastname7','user7@example.com','fr','MEMBER','2025-01-01 00:00:00'),(8,'user8','$2a$12$EyDxX5wWsrJGIyo2MFIRR.LqLmmqssUdPgwasMrG7TvZmjW9ECeLW','UserFirstname8','UserLastname8','user8@example.com','fr','MEMBER','2025-01-01 00:00:00'),(9,'user9','$2a$12$EyDxX5wWsrJGIyo2MFIRR.LqLmmqssUdPgwasMrG7TvZmjW9ECeLW','UserFirstname9','UserLastname9','user9@example.com','fr','MEMBER','2025-01-01 00:00:00'),(10,'user10','$2a$12$EyDxX5wWsrJGIyo2MFIRR.LqLmmqssUdPgwasMrG7TvZmjW9ECeLW','UserFirstname10','UserLastname10','user10@example.com','fr','MEMBER','2025-01-01 00:00:00'),(11,'user11','$2a$12$EyDxX5wWsrJGIyo2MFIRR.LqLmmqssUdPgwasMrG7TvZmjW9ECeLW','UserFirstname11','UserLastname11','user11@example.com','fr','MEMBER','2025-01-01 00:00:00'),(12,'user12','$2a$12$EyDxX5wWsrJGIyo2MFIRR.LqLmmqssUdPgwasMrG7TvZmjW9ECeLW','UserFirstname12','UserLastname12','user12@example.com','fr','MEMBER','2025-01-01 00:00:00'),(13,'user13','$2a$12$EyDxX5wWsrJGIyo2MFIRR.LqLmmqssUdPgwasMrG7TvZmjW9ECeLW','UserFirstname13','UserLastname13','user13@example.com','fr','MEMBER','2025-01-01 00:00:00'),(14,'user14','$2a$12$EyDxX5wWsrJGIyo2MFIRR.LqLmmqssUdPgwasMrG7TvZmjW9ECeLW','UserFirstname14','UserLastname14','user14@example.com','fr','MEMBER','2025-01-01 00:00:00'),(15,'user15','$2a$12$EyDxX5wWsrJGIyo2MFIRR.LqLmmqssUdPgwasMrG7TvZmjW9ECeLW','UserFirstname15','UserLastname15','user15@example.com','fr','MEMBER','2025-01-01 00:00:00'),(16,'user16','$2a$12$EyDxX5wWsrJGIyo2MFIRR.LqLmmqssUdPgwasMrG7TvZmjW9ECeLW','UserFirstname16','UserLastname16','user16@example.com','fr','MEMBER','2025-01-01 00:00:00'),(17,'user17','$2a$12$EyDxX5wWsrJGIyo2MFIRR.LqLmmqssUdPgwasMrG7TvZmjW9ECeLW','UserFirstname17','UserLastname17','user17@example.com','fr','MEMBER','2025-01-01 00:00:00'),(18,'user18','$2a$12$EyDxX5wWsrJGIyo2MFIRR.LqLmmqssUdPgwasMrG7TvZmjW9ECeLW','UserFirstname18','UserLastname18','user18@example.com','fr','MEMBER','2025-01-01 00:00:00'),(19,'user19','$2a$12$EyDxX5wWsrJGIyo2MFIRR.LqLmmqssUdPgwasMrG7TvZmjW9ECeLW','UserFirstname19','UserLastname19','user19@example.com','fr','MEMBER','2025-01-01 00:00:00'),(20,'user20','$2a$12$EyDxX5wWsrJGIyo2MFIRR.LqLmmqssUdPgwasMrG7TvZmjW9ECeLW','UserFirstname20','UserLastname20','user20@example.com','fr','MEMBER','2025-01-01 00:00:00'),(21,'user21','$2a$12$EyDxX5wWsrJGIyo2MFIRR.LqLmmqssUdPgwasMrG7TvZmjW9ECeLW','UserFirstname21','UserLastname21','user21@example.com','fr','MEMBER','2025-01-01 00:00:00'),(22,'user22','$2a$12$EyDxX5wWsrJGIyo2MFIRR.LqLmmqssUdPgwasMrG7TvZmjW9ECeLW','UserFirstname22','UserLastname22','user22@example.com','fr','MEMBER','2025-01-01 00:00:00'),(23,'user23','$2a$12$EyDxX5wWsrJGIyo2MFIRR.LqLmmqssUdPgwasMrG7TvZmjW9ECeLW','UserFirstname23','UserLastname23','user23@example.com','fr','MEMBER','2025-01-01 00:00:00'),(24,'user24','$2a$12$EyDxX5wWsrJGIyo2MFIRR.LqLmmqssUdPgwasMrG7TvZmjW9ECeLW','UserFirstname24','UserLastname24','user24@example.com','fr','MEMBER','2025-01-01 00:00:00'),(25,'user25','$2a$12$EyDxX5wWsrJGIyo2MFIRR.LqLmmqssUdPgwasMrG7TvZmjW9ECeLW','UserFirstname25','UserLastname25','user25@example.com','fr','MEMBER','2025-01-01 00:00:00'),(26,'user26','$2a$12$EyDxX5wWsrJGIyo2MFIRR.LqLmmqssUdPgwasMrG7TvZmjW9ECeLW','UserFirstname26','UserLastname26','user26@example.com','fr','MEMBER','2025-01-01 00:00:00'),(27,'user27','$2a$12$EyDxX5wWsrJGIyo2MFIRR.LqLmmqssUdPgwasMrG7TvZmjW9ECeLW','UserFirstname27','UserLastname27','user27@example.com','fr','MEMBER','2025-01-01 00:00:00'),(28,'user28','$2a$12$EyDxX5wWsrJGIyo2MFIRR.LqLmmqssUdPgwasMrG7TvZmjW9ECeLW','UserFirstname28','UserLastname28','user28@example.com','fr','MEMBER','2025-01-01 00:00:00'),(29,'user29','$2a$12$EyDxX5wWsrJGIyo2MFIRR.LqLmmqssUdPgwasMrG7TvZmjW9ECeLW','UserFirstname29','UserLastname29','user29@example.com','fr','MEMBER','2025-01-01 00:00:00'),(30,'user30','$2a$12$EyDxX5wWsrJGIyo2MFIRR.LqLmmqssUdPgwasMrG7TvZmjW9ECeLW','UserFirstname30','UserLastname30','user30@example.com','fr','MEMBER','2025-01-01 00:00:00'),(31,'user31','$2a$12$EyDxX5wWsrJGIyo2MFIRR.LqLmmqssUdPgwasMrG7TvZmjW9ECeLW','UserFirstname31','UserLastname31','user31@example.com','fr','MEMBER','2025-01-01 00:00:00'),(32,'user32','$2a$12$EyDxX5wWsrJGIyo2MFIRR.LqLmmqssUdPgwasMrG7TvZmjW9ECeLW','UserFirstname32','UserLastname32','user32@example.com','fr','MEMBER','2025-01-01 00:00:00'),(33,'user33','$2a$12$EyDxX5wWsrJGIyo2MFIRR.LqLmmqssUdPgwasMrG7TvZmjW9ECeLW','UserFirstname33','UserLastname33','user33@example.com','fr','MEMBER','2025-01-01 00:00:00'),(34,'user34','$2a$12$EyDxX5wWsrJGIyo2MFIRR.LqLmmqssUdPgwasMrG7TvZmjW9ECeLW','UserFirstname34','UserLastname34','user34@example.com','fr','MEMBER','2025-01-01 00:00:00'),(35,'user35','$2a$12$EyDxX5wWsrJGIyo2MFIRR.LqLmmqssUdPgwasMrG7TvZmjW9ECeLW','UserFirstname35','UserLastname35','user35@example.com','fr','MEMBER','2025-01-01 00:00:00'),(36,'user36','$2a$12$EyDxX5wWsrJGIyo2MFIRR.LqLmmqssUdPgwasMrG7TvZmjW9ECeLW','UserFirstname36','UserLastname36','user36@example.com','fr','MEMBER','2025-01-01 00:00:00'),(37,'user37','$2a$12$EyDxX5wWsrJGIyo2MFIRR.LqLmmqssUdPgwasMrG7TvZmjW9ECeLW','UserFirstname37','UserLastname37','user37@example.com','fr','MEMBER','2025-01-01 00:00:00'),(38,'user38','$2a$12$EyDxX5wWsrJGIyo2MFIRR.LqLmmqssUdPgwasMrG7TvZmjW9ECeLW','UserFirstname38','UserLastname38','user38@example.com','fr','MEMBER','2025-01-01 00:00:00'),(39,'user39','$2a$12$EyDxX5wWsrJGIyo2MFIRR.LqLmmqssUdPgwasMrG7TvZmjW9ECeLW','UserFirstname39','UserLastname39','user39@example.com','fr','MEMBER','2025-01-01 00:00:00'),(40,'user40','$2a$12$EyDxX5wWsrJGIyo2MFIRR.LqLmmqssUdPgwasMrG7TvZmjW9ECeLW','UserFirstname40','UserLastname40','user40@example.com','fr','MEMBER','2025-01-01 00:00:00'),(41,'user41','$2a$12$EyDxX5wWsrJGIyo2MFIRR.LqLmmqssUdPgwasMrG7TvZmjW9ECeLW','UserFirstname41','UserLastname41','user41@example.com','fr','MEMBER','2025-01-01 00:00:00'),(42,'user42','$2a$12$EyDxX5wWsrJGIyo2MFIRR.LqLmmqssUdPgwasMrG7TvZmjW9ECeLW','UserFirstname42','UserLastname42','user42@example.com','fr','MEMBER','2025-01-01 00:00:00'),(43,'user43','$2a$12$EyDxX5wWsrJGIyo2MFIRR.LqLmmqssUdPgwasMrG7TvZmjW9ECeLW','UserFirstname43','UserLastname43','user43@example.com','fr','MEMBER','2025-01-01 00:00:00'),(44,'user44','$2a$12$EyDxX5wWsrJGIyo2MFIRR.LqLmmqssUdPgwasMrG7TvZmjW9ECeLW','UserFirstname44','UserLastname44','user44@example.com','fr','MEMBER','2025-01-01 00:00:00'),(45,'user45','$2a$12$EyDxX5wWsrJGIyo2MFIRR.LqLmmqssUdPgwasMrG7TvZmjW9ECeLW','UserFirstname45','UserLastname45','user45@example.com','fr','MEMBER','2025-01-01 00:00:00'),(46,'user46','$2a$12$EyDxX5wWsrJGIyo2MFIRR.LqLmmqssUdPgwasMrG7TvZmjW9ECeLW','UserFirstname46','UserLastname46','user46@example.com','fr','MEMBER','2025-01-01 00:00:00'),(47,'user47','$2a$12$EyDxX5wWsrJGIyo2MFIRR.LqLmmqssUdPgwasMrG7TvZmjW9ECeLW','UserFirstname47','UserLastname47','user47@example.com','fr','MEMBER','2025-01-01 00:00:00'),(48,'user48','$2a$12$EyDxX5wWsrJGIyo2MFIRR.LqLmmqssUdPgwasMrG7TvZmjW9ECeLW','UserFirstname48','UserLastname48','user48@example.com','fr','MEMBER','2025-01-01 00:00:00'),(49,'user49','$2a$12$EyDxX5wWsrJGIyo2MFIRR.LqLmmqssUdPgwasMrG7TvZmjW9ECeLW','UserFirstname49','UserLastname49','user49@example.com','fr','MEMBER','2025-01-01 00:00:00'),(50,'user50','$2a$12$EyDxX5wWsrJGIyo2MFIRR.LqLmmqssUdPgwasMrG7TvZmjW9ECeLW','UserFirstname50','UserLastname50','user50@example.com','fr','MEMBER','2025-01-01 00:00:00'),(51,'user51','$2a$12$EyDxX5wWsrJGIyo2MFIRR.LqLmmqssUdPgwasMrG7TvZmjW9ECeLW','UserFirstname51','UserLastname51','user51@example.com','fr','MEMBER','2025-01-01 00:00:00'),(52,'user52','$2a$12$EyDxX5wWsrJGIyo2MFIRR.LqLmmqssUdPgwasMrG7TvZmjW9ECeLW','UserFirstname52','UserLastname52','user52@example.com','fr','MEMBER','2025-01-01 00:00:00'),(53,'user53','$2a$12$EyDxX5wWsrJGIyo2MFIRR.LqLmmqssUdPgwasMrG7TvZmjW9ECeLW','UserFirstname53','UserLastname53','user53@example.com','fr','MEMBER','2025-01-01 00:00:00'),(54,'user54','$2a$12$EyDxX5wWsrJGIyo2MFIRR.LqLmmqssUdPgwasMrG7TvZmjW9ECeLW','UserFirstname54','UserLastname54','user54@example.com','fr','MEMBER','2025-01-01 00:00:00'),(55,'user55','$2a$12$EyDxX5wWsrJGIyo2MFIRR.LqLmmqssUdPgwasMrG7TvZmjW9ECeLW','UserFirstname55','UserLastname55','user55@example.com','fr','MEMBER','2025-01-01 00:00:00'),(56,'user56','$2a$12$EyDxX5wWsrJGIyo2MFIRR.LqLmmqssUdPgwasMrG7TvZmjW9ECeLW','UserFirstname56','UserLastname56','user56@example.com','fr','MEMBER','2025-01-01 00:00:00'),(57,'user57','$2a$12$EyDxX5wWsrJGIyo2MFIRR.LqLmmqssUdPgwasMrG7TvZmjW9ECeLW','UserFirstname57','UserLastname57','user57@example.com','fr','MEMBER','2025-01-01 00:00:00'),(58,'user58','$2a$12$EyDxX5wWsrJGIyo2MFIRR.LqLmmqssUdPgwasMrG7TvZmjW9ECeLW','UserFirstname58','UserLastname58','user58@example.com','fr','MEMBER','2025-01-01 00:00:00'),(59,'user59','$2a$12$EyDxX5wWsrJGIyo2MFIRR.LqLmmqssUdPgwasMrG7TvZmjW9ECeLW','UserFirstname59','UserLastname59','user59@example.com','fr','MEMBER','2025-01-01 00:00:00'),(60,'user60','$2a$12$EyDxX5wWsrJGIyo2MFIRR.LqLmmqssUdPgwasMrG7TvZmjW9ECeLW','UserFirstname60','UserLastname60','user60@example.com','fr','MEMBER','2025-01-01 00:00:00'),(61,'user61','$2a$12$EyDxX5wWsrJGIyo2MFIRR.LqLmmqssUdPgwasMrG7TvZmjW9ECeLW','UserFirstname61','UserLastname61','user61@example.com','fr','MEMBER','2025-01-01 00:00:00'),(62,'user62','$2a$12$EyDxX5wWsrJGIyo2MFIRR.LqLmmqssUdPgwasMrG7TvZmjW9ECeLW','UserFirstname62','UserLastname62','user62@example.com','fr','MEMBER','2025-01-01 00:00:00'),(63,'user63','$2a$12$EyDxX5wWsrJGIyo2MFIRR.LqLmmqssUdPgwasMrG7TvZmjW9ECeLW','UserFirstname63','UserLastname63','user63@example.com','fr','MEMBER','2025-01-01 00:00:00'),(64,'user64','$2a$12$EyDxX5wWsrJGIyo2MFIRR.LqLmmqssUdPgwasMrG7TvZmjW9ECeLW','UserFirstname64','UserLastname64','user64@example.com','fr','MEMBER','2025-01-01 00:00:00'),(65,'user65','$2a$12$EyDxX5wWsrJGIyo2MFIRR.LqLmmqssUdPgwasMrG7TvZmjW9ECeLW','UserFirstname65','UserLastname65','user65@example.com','fr','MEMBER','2025-01-01 00:00:00'),(66,'user66','$2a$12$EyDxX5wWsrJGIyo2MFIRR.LqLmmqssUdPgwasMrG7TvZmjW9ECeLW','UserFirstname66','UserLastname66','user66@example.com','fr','MEMBER','2025-01-01 00:00:00'),(67,'user67','$2a$12$EyDxX5wWsrJGIyo2MFIRR.LqLmmqssUdPgwasMrG7TvZmjW9ECeLW','UserFirstname67','UserLastname67','user67@example.com','fr','MEMBER','2025-01-01 00:00:00'),(68,'user68','$2a$12$EyDxX5wWsrJGIyo2MFIRR.LqLmmqssUdPgwasMrG7TvZmjW9ECeLW','UserFirstname68','UserLastname68','user68@example.com','fr','MEMBER','2025-01-01 00:00:00'),(69,'user69','$2a$12$EyDxX5wWsrJGIyo2MFIRR.LqLmmqssUdPgwasMrG7TvZmjW9ECeLW','UserFirstname69','UserLastname69','user69@example.com','fr','MEMBER','2025-01-01 00:00:00'),(70,'user70','$2a$12$EyDxX5wWsrJGIyo2MFIRR.LqLmmqssUdPgwasMrG7TvZmjW9ECeLW','UserFirstname70','UserLastname70','user70@example.com','fr','MEMBER','2025-01-01 00:00:00'),(71,'user71','$2a$12$EyDxX5wWsrJGIyo2MFIRR.LqLmmqssUdPgwasMrG7TvZmjW9ECeLW','UserFirstname71','UserLastname71','user71@example.com','fr','MEMBER','2025-01-01 00:00:00'),(72,'user72','$2a$12$EyDxX5wWsrJGIyo2MFIRR.LqLmmqssUdPgwasMrG7TvZmjW9ECeLW','UserFirstname72','UserLastname72','user72@example.com','fr','MEMBER','2025-01-01 00:00:00'),(73,'user73','$2a$12$EyDxX5wWsrJGIyo2MFIRR.LqLmmqssUdPgwasMrG7TvZmjW9ECeLW','UserFirstname73','UserLastname73','user73@example.com','fr','MEMBER','2025-01-01 00:00:00'),(74,'user74','$2a$12$EyDxX5wWsrJGIyo2MFIRR.LqLmmqssUdPgwasMrG7TvZmjW9ECeLW','UserFirstname74','UserLastname74','user74@example.com','fr','MEMBER','2025-01-01 00:00:00'),(75,'user75','$2a$12$EyDxX5wWsrJGIyo2MFIRR.LqLmmqssUdPgwasMrG7TvZmjW9ECeLW','UserFirstname75','UserLastname75','user75@example.com','fr','MEMBER','2025-01-01 00:00:00'),(76,'user76','$2a$12$EyDxX5wWsrJGIyo2MFIRR.LqLmmqssUdPgwasMrG7TvZmjW9ECeLW','UserFirstname76','UserLastname76','user76@example.com','fr','MEMBER','2025-01-01 00:00:00'),(77,'user77','$2a$12$EyDxX5wWsrJGIyo2MFIRR.LqLmmqssUdPgwasMrG7TvZmjW9ECeLW','UserFirstname77','UserLastname77','user77@example.com','fr','MEMBER','2025-01-01 00:00:00'),(78,'user78','$2a$12$EyDxX5wWsrJGIyo2MFIRR.LqLmmqssUdPgwasMrG7TvZmjW9ECeLW','UserFirstname78','UserLastname78','user78@example.com','fr','MEMBER','2025-01-01 00:00:00'),(79,'user79','$2a$12$EyDxX5wWsrJGIyo2MFIRR.LqLmmqssUdPgwasMrG7TvZmjW9ECeLW','UserFirstname79','UserLastname79','user79@example.com','fr','MEMBER','2025-01-01 00:00:00'),(80,'user80','$2a$12$EyDxX5wWsrJGIyo2MFIRR.LqLmmqssUdPgwasMrG7TvZmjW9ECeLW','UserFirstname80','UserLastname80','user80@example.com','fr','MEMBER','2025-01-01 00:00:00'),(81,'user81','$2a$12$EyDxX5wWsrJGIyo2MFIRR.LqLmmqssUdPgwasMrG7TvZmjW9ECeLW','UserFirstname81','UserLastname81','user81@example.com','fr','MEMBER','2025-01-01 00:00:00'),(82,'user82','$2a$12$EyDxX5wWsrJGIyo2MFIRR.LqLmmqssUdPgwasMrG7TvZmjW9ECeLW','UserFirstname82','UserLastname82','user82@example.com','fr','MEMBER','2025-01-01 00:00:00'),(83,'user83','$2a$12$EyDxX5wWsrJGIyo2MFIRR.LqLmmqssUdPgwasMrG7TvZmjW9ECeLW','UserFirstname83','UserLastname83','user83@example.com','fr','MEMBER','2025-01-01 00:00:00'),(84,'user84','$2a$12$EyDxX5wWsrJGIyo2MFIRR.LqLmmqssUdPgwasMrG7TvZmjW9ECeLW','UserFirstname84','UserLastname84','user84@example.com','fr','MEMBER','2025-01-01 00:00:00'),(85,'user85','$2a$12$EyDxX5wWsrJGIyo2MFIRR.LqLmmqssUdPgwasMrG7TvZmjW9ECeLW','UserFirstname85','UserLastname85','user85@example.com','fr','MEMBER','2025-01-01 00:00:00'),(86,'user86','$2a$12$EyDxX5wWsrJGIyo2MFIRR.LqLmmqssUdPgwasMrG7TvZmjW9ECeLW','UserFirstname86','UserLastname86','user86@example.com','fr','MEMBER','2025-01-01 00:00:00'),(87,'user87','$2a$12$EyDxX5wWsrJGIyo2MFIRR.LqLmmqssUdPgwasMrG7TvZmjW9ECeLW','UserFirstname87','UserLastname87','user87@example.com','fr','MEMBER','2025-01-01 00:00:00'),(88,'user88','$2a$12$EyDxX5wWsrJGIyo2MFIRR.LqLmmqssUdPgwasMrG7TvZmjW9ECeLW','UserFirstname88','UserLastname88','user88@example.com','fr','MEMBER','2025-01-01 00:00:00'),(89,'user89','$2a$12$EyDxX5wWsrJGIyo2MFIRR.LqLmmqssUdPgwasMrG7TvZmjW9ECeLW','UserFirstname89','UserLastname89','user89@example.com','fr','MEMBER','2025-01-01 00:00:00'),(90,'user90','$2a$12$EyDxX5wWsrJGIyo2MFIRR.LqLmmqssUdPgwasMrG7TvZmjW9ECeLW','UserFirstname90','UserLastname90','user90@example.com','fr','MEMBER','2025-01-01 00:00:00'),(91,'user91','$2a$12$EyDxX5wWsrJGIyo2MFIRR.LqLmmqssUdPgwasMrG7TvZmjW9ECeLW','UserFirstname91','UserLastname91','user91@example.com','fr','MEMBER','2025-01-01 00:00:00'),(92,'user92','$2a$12$EyDxX5wWsrJGIyo2MFIRR.LqLmmqssUdPgwasMrG7TvZmjW9ECeLW','UserFirstname92','UserLastname92','user92@example.com','fr','MEMBER','2025-01-01 00:00:00'),(93,'user93','$2a$12$EyDxX5wWsrJGIyo2MFIRR.LqLmmqssUdPgwasMrG7TvZmjW9ECeLW','UserFirstname93','UserLastname93','user93@example.com','fr','MEMBER','2025-01-01 00:00:00'),(94,'user94','$2a$12$EyDxX5wWsrJGIyo2MFIRR.LqLmmqssUdPgwasMrG7TvZmjW9ECeLW','UserFirstname94','UserLastname94','user94@example.com','fr','MEMBER','2025-01-01 00:00:00'),(95,'user95','$2a$12$EyDxX5wWsrJGIyo2MFIRR.LqLmmqssUdPgwasMrG7TvZmjW9ECeLW','UserFirstname95','UserLastname95','user95@example.com','fr','MEMBER','2025-01-01 00:00:00'),(96,'user96','$2a$12$EyDxX5wWsrJGIyo2MFIRR.LqLmmqssUdPgwasMrG7TvZmjW9ECeLW','UserFirstname96','UserLastname96','user96@example.com','fr','MEMBER','2025-01-01 00:00:00'),(97,'user97','$2a$12$EyDxX5wWsrJGIyo2MFIRR.LqLmmqssUdPgwasMrG7TvZmjW9ECeLW','UserFirstname97','UserLastname97','user97@example.com','fr','MEMBER','2025-01-01 00:00:00'),(98,'user98','$2a$12$EyDxX5wWsrJGIyo2MFIRR.LqLmmqssUdPgwasMrG7TvZmjW9ECeLW','UserFirstname98','UserLastname98','user98@example.com','fr','MEMBER','2025-01-01 00:00:00'),(99,'user99','$2a$12$EyDxX5wWsrJGIyo2MFIRR.LqLmmqssUdPgwasMrG7TvZmjW9ECeLW','UserFirstname99','UserLastname99','user99@example.com','fr','MEMBER','2025-01-01 00:00:00'),(100,'user100','$2a$12$EyDxX5wWsrJGIyo2MFIRR.LqLmmqssUdPgwasMrG7TvZmjW9ECeLW','UserFirstname100','UserLastname100','user100@example.com','fr','MEMBER','2025-01-01 00:00:00');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `videos`
--

DROP TABLE IF EXISTS `videos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `videos` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `video_url` varchar(255) NOT NULL,
  `show_id` int NOT NULL,
  `artist_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `video_url` (`video_url`),
  KEY `show_id` (`show_id`),
  CONSTRAINT `videos_ibfk_1` FOREIGN KEY (`show_id`) REFERENCES `shows` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `videos`
--

LOCK TABLES `videos` WRITE;
/*!40000 ALTER TABLE `videos` DISABLE KEYS */;
INSERT INTO `videos` VALUES (1,'Trailer Ayiti Test','https://youtu.be/cR-6b3KQkLU?si=_ydq4JAQ16tzHgDG',1,'Marcelin');
/*!40000 ALTER TABLE `videos` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-05-21  1:44:21
