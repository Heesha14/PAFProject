-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 25, 2021 at 02:37 PM
-- Server version: 10.4.18-MariaDB
-- PHP Version: 8.0.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `projects_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `project`
--

CREATE TABLE `project` (
  `Project_ID` int(11) NOT NULL,
  `pmid` varchar(50) DEFAULT NULL,
  `fbid` varchar(50) DEFAULT NULL,
  `Project_Name` varchar(50) DEFAULT NULL,
  `Start_Date` varchar(50) DEFAULT NULL,
  `Deadline_Date` varchar(50) DEFAULT NULL,
  `Project_Status` varchar(10) DEFAULT NULL,
  `Project_Fund_Amt` varchar(50) DEFAULT NULL,
  `Project_Sell_Amt` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `project`
--

INSERT INTO `project` (`Project_ID`, `pmid`, `fbid`, `Project_Name`, `Start_Date`, `Deadline_Date`, `Project_Status`, `Project_Fund_Amt`, `Project_Sell_Amt`) VALUES
(7, 'P007', 'F7', 'Project Test 6', '29/04/2021', '09/05/2021', 'Developing', '100000', '500000'),
(8, 'P008', 'F8', 'Project Test 8', '30/04/2021', '15/05/2021', 'Approved', '130000', '400000'),
(9, 'P009', 'F9', 'Project Test 9', '28/04/2021', '22/05/2021', 'Approved', '450000', '900000'),
(10, 'P009', 'F9', 'Project Test 9', '28/04/2021', '22/05/2021', 'Approved', '450000', '900000');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `project`
--
ALTER TABLE `project`
  ADD PRIMARY KEY (`Project_ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `project`
--
ALTER TABLE `project`
  MODIFY `Project_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
