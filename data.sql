-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.4.25-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for bank
CREATE DATABASE IF NOT EXISTS `bank` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `bank`;

-- Dumping structure for table bank.account
CREATE TABLE IF NOT EXISTS `account` (
  `accountno` bigint(20) NOT NULL,
  `accounttype` varchar(255) DEFAULT NULL,
  `balance` decimal(19,2) DEFAULT NULL,
  `openingdate` varchar(255) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`accountno`),
  KEY `FK7m8ru44m93ukyb61dfxw0apf6` (`user_id`),
  CONSTRAINT `FK7m8ru44m93ukyb61dfxw0apf6` FOREIGN KEY (`user_id`) REFERENCES `user` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table bank.account: ~3 rows (approximately)
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` (`accountno`, `accounttype`, `balance`, `openingdate`, `user_id`) VALUES
	(1001, 'Saving', 3800.00, '2022-12-22', 1),
	(1002, 'Saving', 2200.00, '2022-12-22', 2),
	(1003, 'FD', 3000.00, '2022-12-22', 3);
/*!40000 ALTER TABLE `account` ENABLE KEYS */;

-- Dumping structure for table bank.fdaccount
CREATE TABLE IF NOT EXISTS `fdaccount` (
  `autorenew` bit(1) DEFAULT NULL,
  `interestearnerd` decimal(19,2) DEFAULT NULL,
  `maturitydate` varchar(255) DEFAULT NULL,
  `accountno` bigint(20) NOT NULL,
  PRIMARY KEY (`accountno`),
  CONSTRAINT `FKpbdmmg70wwe4r7s9ifo2mti0l` FOREIGN KEY (`accountno`) REFERENCES `account` (`accountno`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table bank.fdaccount: ~1 rows (approximately)
/*!40000 ALTER TABLE `fdaccount` DISABLE KEYS */;
INSERT INTO `fdaccount` (`autorenew`, `interestearnerd`, `maturitydate`, `accountno`) VALUES
	(b'0', 438.00, '2022-12-25', 1003);
/*!40000 ALTER TABLE `fdaccount` ENABLE KEYS */;

-- Dumping structure for table bank.savingsaccount
CREATE TABLE IF NOT EXISTS `savingsaccount` (
  `interestearned` decimal(19,2) DEFAULT NULL,
  `accountno` bigint(20) NOT NULL,
  PRIMARY KEY (`accountno`),
  CONSTRAINT `FKrm9gk6n29j3x8wycmx47vfey3` FOREIGN KEY (`accountno`) REFERENCES `account` (`accountno`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table bank.savingsaccount: ~2 rows (approximately)
/*!40000 ALTER TABLE `savingsaccount` DISABLE KEYS */;
INSERT INTO `savingsaccount` (`interestearned`, `accountno`) VALUES
	(9.90, 1001),
	(9.90, 1002);
/*!40000 ALTER TABLE `savingsaccount` ENABLE KEYS */;

-- Dumping structure for table bank.transaction
CREATE TABLE IF NOT EXISTS `transaction` (
  `transactionid` int(11) NOT NULL AUTO_INCREMENT,
  `amount` decimal(19,2) DEFAULT NULL,
  `fromaccount` bigint(20) DEFAULT NULL,
  `toaccount` bigint(20) DEFAULT NULL,
  `trdate` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`transactionid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- Dumping data for table bank.transaction: ~1 rows (approximately)
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
INSERT INTO `transaction` (`transactionid`, `amount`, `fromaccount`, `toaccount`, `trdate`) VALUES
	(1, 800.00, 1002, 1001, '2022-12-22');
/*!40000 ALTER TABLE `transaction` ENABLE KEYS */;

-- Dumping structure for table bank.user
CREATE TABLE IF NOT EXISTS `user` (
  `userid` int(11) NOT NULL,
  `aadharno` varchar(255) DEFAULT NULL,
  `accounttype` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `firstname` varchar(255) DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `pancard` varchar(255) DEFAULT NULL,
  `phoneno` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table bank.user: ~3 rows (approximately)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`userid`, `aadharno`, `accounttype`, `address`, `firstname`, `lastname`, `pancard`, `phoneno`) VALUES
	(1, '123409874578', 'Saving', 'Delhi', 'raju', 'Mital', '67GHF56', '9876543123'),
	(2, '123409874578', 'Saving', 'Jaipur', 'Ashu', 'Mital', '67GHF56', '9876543178'),
	(3, '123409874789', 'FD', 'Mumbai', 'Ram', 'Kishan', '90GHF56', '8765423453');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
