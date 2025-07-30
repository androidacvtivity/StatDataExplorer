-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               8.0.42 - MySQL Community Server - GPL
-- Server OS:                    Win64
-- HeidiSQL Version:             12.11.0.7074
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- Dumping structure for table u331875177_db2.struct_bns
CREATE TABLE IF NOT EXISTS `struct_bns` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'empty field',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT 'empty field',
  `statut` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT 'xxxxx',
  `name_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT 'empty field',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table u331875177_db2.struct_bns: ~60 rows (approximately)
INSERT INTO `struct_bns` (`id`, `type`, `name`, `statut`, `name_id`) VALUES
	(1, 'star', 'Director general', 'xxxxx', '1'),
	(2, 'star', 'Directori generali adjuncţi', 'xxxxx', '2'),
	(3, 'serviciu', 'Cabinetul directorului general', 'xxxxx', '3'),
	(4, 'serviciu', 'Serviciul audit intern', 'xxxxx', '4'),
	(5, 'serviciu', 'Serviciul protecția datelor cu caracter personal', 'xxxxx', '5'),
	(6, 'depart', 'Direcţia integrare europeană și cooperare internaţională', 'xxxxx', '6'),
	(7, 'depart', 'Direcția coordonare statistică', 'xxxxx', '7'),
	(8, 'depart', 'Direcția instruire în statistica oficială', 'xxxxx', '8'),
	(9, 'depart', 'Direcţia diseminare şi comunicare', 'xxxxx', '9'),
	(10, 'sectia', 'Secția diseminare', 'xxxxx', '10'),
	(11, 'sectia', 'Secția comunicare și relații cu utilizatorii', 'xxxxx', '11'),
	(12, 'star', 'Direcția generală metode și inovații statistice', 'xxxxx', '12'),
	(13, 'depart', 'Direcția cercetări prin sondaj', 'xxxxx', '13'),
	(14, 'depart', 'Direcția surse de date alternative și instrumente inovatoare în statistică', 'xxxxx', '14'),
	(15, 'depart', 'Direcția sisteme informaționale geografice', 'xxxxx', '15'),
	(16, 'sectia', 'Secția managementul calității', 'xxxxx', '16'),
	(17, 'depart', 'Direcţia conturi naţionale', 'xxxxx', '17'),
	(18, 'sectia', 'Secția conturi anuale și trimestriale', 'xxxxx', '18'),
	(19, 'sectia', 'Secția conturi regionale', 'xxxxx', '19'),
	(20, 'sectia', 'Secția conturi integrate', 'xxxxx', '20'),
	(21, 'depart', 'Direcţia statistica preţurilor de consum', 'xxxxx', '21'),
	(22, 'depart', 'Direcţia statistica comerţului exterior', 'xxxxx', '22'),
	(23, 'star', 'Direcţia generală statistica întreprinderilor', 'xxxxx', '23'),
	(24, 'depart', 'Direcţia statistica industriei şi energeticii', 'xxxxx', '24'),
	(25, 'depart', 'Direcţia statistica investiţiilor şi construcţiilor', 'xxxxx', '25'),
	(26, 'depart', 'Direcţia statistica structurală', 'xxxxx', '26'),
	(27, 'depart', 'Direcția statistica turismului, tehnologiei informației și comunicațiilor', 'xxxxx', '27'),
	(28, 'depart', 'Direcția statistica transporturilor și serviciilor', 'xxxxx', '28'),
	(29, 'depart', 'Direcţia statistica populaţiei şi migraţiei', 'xxxxx', '29'),
	(30, 'depart', 'Direcţia statistica nivelului de trai', 'xxxxx', '30'),
	(31, 'depart', 'Direcția statistica forței de muncă', 'xxxxx', '31'),
	(32, 'depart', 'Direcţia statistica pieței muncii', 'xxxxx', '32'),
	(33, 'depart', 'Direcţia statistica educaţiei, ştiinţei şi culturii', 'xxxxx', '33'),
	(34, 'depart', 'Direcţia statistica serviciilor sociale', 'xxxxx', '34'),
	(35, 'depart', 'Direcţia recensăminte', 'xxxxx', '35'),
	(36, 'depart', 'Direcția colectarea datelor în gospodării', 'xxxxx', '36'),
	(37, 'depart', 'Direcţia statistica agriculturii şi mediului', 'xxxxx', '37'),
	(38, 'sectia', 'Secţia statistica producției vegetale și animaliere', 'xxxxx', '38'),
	(39, 'sectia', 'Secţia cercetări selective în agricultură', 'xxxxx', '39'),
	(40, 'sectia', 'Secția calcule economice în agricultură', 'xxxxx', '40'),
	(41, 'serviciu', 'Serviciul statistica mediului', 'xxxxx', '41'),
	(42, 'depart', 'Direcția depozitarul public al situațiilor financiare și antreprenoriat', 'xxxxx', '42'),
	(43, 'sectia', 'Secția depozitar', 'xxxxx', '43'),
	(44, 'sectia', 'Secția statistica antreprenoriatului', 'xxxxx', '44'),
	(45, 'star', 'Direcţia generală tehnologii informaţionale', 'xxxxx', '45'),
	(46, 'depart', 'Direcția dezvoltare sisteme informaționale', 'xxxxx', '46'),
	(47, 'depart', 'Direcţia proiectare aplicaţii', 'xxxxx', '47'),
	(48, 'depart', 'Direcţia administrare a sistemului informaţional', 'xxxxx', '48'),
	(49, 'serviciu', 'Serviciul suport respondenți', 'xxxxx', '49'),
	(50, 'star', 'Direcția generală management financiar', 'xxxxx', '50'),
	(51, 'depart', 'Direcția planificare', 'xxxxx', '51'),
	(52, 'depart', 'Direcția evidență contabilă', 'xxxxx', '52'),
	(53, 'depart', 'Direcția administrare şi achiziţii', 'xxxxx', '53'),
	(54, 'sectia', 'Secția juridică', 'xxxxx', '54'),
	(55, 'depart', 'Direcția resurse umane', 'xxxxx', '55'),
	(56, 'sectia', 'Secția managementul documentelor', 'xxxxx', '56'),
	(57, '0100000', 'Centrul regional pentru statistică Chişinău', 'xxxxx', '57'),
	(58, '1111111', 'Centrul regional pentru statistică Nord', 'xxxxx', '58'),
	(59, '2222222', 'Centrul regional pentru statistică Centru', 'xxxxx', '59'),
	(60, '3333333', 'Centrul regional pentru statistică Sud', 'xxxxx', '60');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
