-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 25, 2021 at 02:44 PM
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
-- Database: `expensedb`
--

-- --------------------------------------------------------

--
-- Table structure for table `expenses`
--

CREATE TABLE `expenses` (
  `expenseId` int(11) NOT NULL,
  `expenseTitle` varchar(50) NOT NULL,
  `expenseDesc` varchar(50) DEFAULT NULL,
  `expenseAmount` double DEFAULT NULL,
  `expenseStatus` varchar(50) DEFAULT NULL,
  `expenseDate` date DEFAULT NULL,
  `aId` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `expenses`
--

INSERT INTO `expenses` (`expenseId`, `expenseTitle`, `expenseDesc`, `expenseAmount`, `expenseStatus`, `expenseDate`, `aId`) VALUES
(1004, 'Fund broker', 'Highway automation project', 250000000, 'Pending', '2020-01-01', '1006'),
(1005, 'Fund broker', 'Highway automation project', 250000000, 'Pending', '2020-01-01', '1006'),
(1006, 'Electricity Bill', 'pay pay pay', 100, 'To be paid', '2020-01-01', '1006'),
(1007, 'Electricity Bill', 'pay pay pay', 100, 'To be paid', '2020-01-01', '1006'),
(1008, 'Electricity Bill', 'pay pay pay', 100, 'To be paid', '2020-01-01', '1006'),
(1009, 'Electricity Bill', 'pay pay pay', 100, 'To be paid', '2020-01-01', '1006');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `expenses`
--
ALTER TABLE `expenses`
  ADD PRIMARY KEY (`expenseId`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
