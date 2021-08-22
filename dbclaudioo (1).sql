-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3306
-- Tiempo de generación: 14-03-2021 a las 04:55:37
-- Versión del servidor: 5.7.26
-- Versión de PHP: 7.2.18

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `dbclaudioo`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

DROP TABLE IF EXISTS `clientes`;
CREATE TABLE IF NOT EXISTS `clientes` (
  `ID_CLIENTE` int(11) NOT NULL AUTO_INCREMENT,
  `NOM_CLIENTE` varchar(50) NOT NULL,
  `DIR_CLIENTE` varchar(70) DEFAULT NULL,
  `CUIT_CLIENTE` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ID_CLIENTE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ctaclientes`
--

DROP TABLE IF EXISTS `ctaclientes`;
CREATE TABLE IF NOT EXISTS `ctaclientes` (
  `ID_CTA_CLIENTE` int(11) NOT NULL AUTO_INCREMENT,
  `FECHA` varchar(20) DEFAULT NULL,
  `DESCRIPCION` varchar(100) DEFAULT NULL,
  `DEBE` double DEFAULT NULL,
  `HABER` double DEFAULT NULL,
  `SALDO` double DEFAULT NULL,
  `ID_CLIENTE` int(11) NOT NULL,
  PRIMARY KEY (`ID_CTA_CLIENTE`),
  KEY `ID_CLIENTE` (`ID_CLIENTE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ctaproveedores`
--

DROP TABLE IF EXISTS `ctaproveedores`;
CREATE TABLE IF NOT EXISTS `ctaproveedores` (
  `ID_CTA_PROVEEDOR` int(11) NOT NULL AUTO_INCREMENT,
  `FECHA` varchar(25) DEFAULT NULL,
  `DESCRIPCION` varchar(100) DEFAULT NULL,
  `DEBE` double(10,2) DEFAULT NULL,
  `HABER` double(10,2) DEFAULT NULL,
  `SALDO` double(10,2) DEFAULT NULL,
  `ID_PROVEEDOR` int(11) NOT NULL,
  PRIMARY KEY (`ID_CTA_PROVEEDOR`),
  KEY `ID_PROVEEDOR` (`ID_PROVEEDOR`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

DROP TABLE IF EXISTS `productos`;
CREATE TABLE IF NOT EXISTS `productos` (
  `ID_PROD` int(11) NOT NULL AUTO_INCREMENT,
  `DESCRIPCION_PROD` varchar(80) NOT NULL,
  `CODIGO_PROD` int(11) NOT NULL,
  `PRECIO_PROD` decimal(10,2) NOT NULL,
  `CANTIDAD_PROD` int(11) NOT NULL,
  `ID_PROVEEDOR` int(11) NOT NULL,
  PRIMARY KEY (`ID_PROD`),
  KEY `ID_PROVEEDOR` (`ID_PROVEEDOR`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proveedor`
--

DROP TABLE IF EXISTS `proveedor`;
CREATE TABLE IF NOT EXISTS `proveedor` (
  `ID_PROVEEDOR` int(11) NOT NULL AUTO_INCREMENT,
  `NOM_PROVEEDOR` varchar(50) NOT NULL,
  PRIMARY KEY (`ID_PROVEEDOR`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `ctaclientes`
--
ALTER TABLE `ctaclientes`
  ADD CONSTRAINT `ctaclientes_ibfk_1` FOREIGN KEY (`ID_CLIENTE`) REFERENCES `clientes` (`ID_CLIENTE`);

--
-- Filtros para la tabla `ctaproveedores`
--
ALTER TABLE `ctaproveedores`
  ADD CONSTRAINT `ctaproveedores_ibfk_1` FOREIGN KEY (`ID_PROVEEDOR`) REFERENCES `proveedor` (`ID_PROVEEDOR`);

--
-- Filtros para la tabla `productos`
--
ALTER TABLE `productos`
  ADD CONSTRAINT `productos_ibfk_1` FOREIGN KEY (`ID_PROVEEDOR`) REFERENCES `proveedor` (`ID_PROVEEDOR`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
