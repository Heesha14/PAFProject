-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 25, 2021 at 07:48 AM
-- Server version: 10.4.17-MariaDB
-- PHP Version: 8.0.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `gbdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `aId` varchar(50) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  `phone` varchar(10) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `registerDate` date DEFAULT NULL,
  `designation` varchar(50) DEFAULT NULL,
  `first_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='Generate unique pk for admin';

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`aId`, `username`, `password`, `email`, `phone`, `gender`, `registerDate`, `designation`, `first_name`, `last_name`) VALUES
('AD1006', 'test', 'Test@123', 'test@gmail.com', '0713109890', 'M', '2021-04-02', 'admin', 'Test', 'Last Test'),
('AD1028', 'Heesha', 'Heesha@123', 'heesha14@gmail.com', '0713109890', 'F', '2021-04-17', 'admin', 'Heesha', 'Jaanvi');

--
-- Triggers `admin`
--
DELIMITER $$
CREATE TRIGGER `admin_BEFORE_INSERT` BEFORE INSERT ON `admin` FOR EACH ROW BEGIN
UPDATE sequence SET id=LAST_INSERT_ID(id+1) where role = 'AD';
 SET NEW.aId = CONCAT('AD',(SELECT LAST_INSERT_ID(id+1) FROM sequence where role = 'AD'));
 SET NEW.registerDate = NOW();
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `buyer`
--

CREATE TABLE `buyer` (
  `buyId` varchar(50) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  `phone` varchar(10) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `registerDate` date DEFAULT NULL,
  `designation` varchar(50) DEFAULT NULL,
  `first_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Triggers `buyer`
--
DELIMITER $$
CREATE TRIGGER `buyer_BEFORE_INSERT` BEFORE INSERT ON `buyer` FOR EACH ROW BEGIN
UPDATE sequence SET id=LAST_INSERT_ID(id+1) where role = 'BY';
 SET NEW.buyid = 'BY'||(SELECT LAST_INSERT_ID(id+1) FROM sequence where role = 'BY');
 SET NEW.registerDate = NOW();
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `funding_body`
--

CREATE TABLE `funding_body` (
  `fbId` varchar(50) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  `phone` varchar(10) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `registerDate` date DEFAULT NULL,
  `designation` varchar(50) DEFAULT NULL,
  `first_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `funding_body`
--

INSERT INTO `funding_body` (`fbId`, `username`, `password`, `email`, `phone`, `gender`, `registerDate`, `designation`, `first_name`, `last_name`) VALUES
('FB1012', 'sha', '123', 'hheeee', '00', 'm', '2021-04-02', 'FB', NULL, NULL),
('FB1020', 'sha', '123', 'hheeee', '00', 'm', '2021-04-12', 'FB', NULL, NULL);

--
-- Triggers `funding_body`
--
DELIMITER $$
CREATE TRIGGER `funding_body_BEFORE_INSERT` BEFORE INSERT ON `funding_body` FOR EACH ROW BEGIN
UPDATE sequence SET id=LAST_INSERT_ID(id+1) where role = 'FB';
 SET NEW.fbId = CONCAT('FB',(SELECT LAST_INSERT_ID(id+1) FROM sequence where role = 'AD'));
 SET NEW.registerDate = NOW();
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `project_manager`
--

CREATE TABLE `project_manager` (
  `pmId` varchar(50) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  `phone` varchar(10) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `registerDate` date DEFAULT NULL,
  `designation` varchar(50) DEFAULT NULL,
  `first_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `project_manager`
--

INSERT INTO `project_manager` (`pmId`, `username`, `password`, `email`, `phone`, `gender`, `registerDate`, `designation`, `first_name`, `last_name`) VALUES
('1', 'sha', '123', 'hheeee', '00', 'm', '2021-04-08', 'PM', NULL, NULL);

--
-- Triggers `project_manager`
--
DELIMITER $$
CREATE TRIGGER `project_manager_BEFORE_INSERT` BEFORE INSERT ON `project_manager` FOR EACH ROW BEGIN
UPDATE sequence SET id=LAST_INSERT_ID(id+1) where role = 'PM';
 SET NEW.pmId = 'PM'||(SELECT LAST_INSERT_ID(id+1) FROM sequence where role = 'PM');
 SET NEW.registerDate = NOW();
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `sequence`
--

CREATE TABLE `sequence` (
  `id` int(11) NOT NULL,
  `ROLE` varchar(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `sequence`
--

INSERT INTO `sequence` (`id`, `ROLE`) VALUES
(1066, 'AD'),
(7000, 'BY'),
(5002, 'FB'),
(3001, 'PM');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `uId` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `role` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`uId`, `username`, `password`, `role`) VALUES
(1, 'admin', 'admin', 'admin'),
(18, 'Heesha', 'Heesha@123', 'admin'),
(19, 'Suryaa', 'Heesha@123', 'admin');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`aId`);

--
-- Indexes for table `buyer`
--
ALTER TABLE `buyer`
  ADD PRIMARY KEY (`buyId`);
  
--
-- Indexes for table `funding_body`
--
ALTER TABLE `funding_body`
  ADD PRIMARY KEY (`fbId`);

--
-- Indexes for table `project_manager`
--
ALTER TABLE `project_manager`
  ADD PRIMARY KEY (`pmId`);

--
-- Indexes for table `sequence`
--
ALTER TABLE `sequence`
  ADD UNIQUE KEY `ROLE_UNIQUE` (`ROLE`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`uId`);

--
-- AUTO_INCREMENT for dumped tables
--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `uId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;
  
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
