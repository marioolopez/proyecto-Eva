-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 02-12-2024 a las 21:02:43
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `gimnasio`
--
CREATE DATABASE IF NOT EXISTS `gimnasio` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `gimnasio`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `actividad`
--

CREATE TABLE `actividad` (
  `id` int(11) NOT NULL,
  `nombre` varchar(20) NOT NULL,
  `descripcion` varchar(20) NOT NULL,
  `idsala` int(11) NOT NULL,
  `idempleado` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `actividad`
--

INSERT INTO `actividad` (`id`, `nombre`, `descripcion`, `idsala`, `idempleado`) VALUES
(1, 'Correr', 'Ha correr!', 1, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `id` int(11) NOT NULL,
  `nombre` varchar(20) NOT NULL,
  `telefono` int(11) NOT NULL,
  `dni` varchar(20) NOT NULL,
  `edad` int(11) NOT NULL,
  `idtarifa` int(11) NOT NULL,
  `sexo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`id`, `nombre`, `telefono`, `dni`, `edad`, `idtarifa`, `sexo`) VALUES
(1, 'Mario', 65432143, '76543243', 22, 1, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `compra`
--

CREATE TABLE `compra` (
  `idpedido` int(11) NOT NULL,
  `idproducto` int(11) NOT NULL,
  `cantidad` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `compra`
--

INSERT INTO `compra` (`idpedido`, `idproducto`, `cantidad`) VALUES
(1, 1, 23);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleado`
--

CREATE TABLE `empleado` (
  `id` int(11) NOT NULL,
  `nombre` varchar(20) NOT NULL,
  `dni` varchar(20) NOT NULL,
  `salario` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `empleado`
--

INSERT INTO `empleado` (`id`, `nombre`, `dni`, `salario`) VALUES
(1, 'Sergio', '654321544', 2100);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `equipamiento`
--

CREATE TABLE `equipamiento` (
  `id` int(11) NOT NULL,
  `nombre` varchar(20) NOT NULL,
  `descripcion` varchar(20) NOT NULL,
  `idactividad` int(11) NOT NULL,
  `idmantenimiento` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `equipamiento`
--

INSERT INTO `equipamiento` (`id`, `nombre`, `descripcion`, `idactividad`, `idmantenimiento`) VALUES
(1, 'Mancuernas', 'Hasta 60 kg', 1, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mantenimiento`
--

CREATE TABLE `mantenimiento` (
  `id` int(11) NOT NULL,
  `nombre` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `mantenimiento`
--

INSERT INTO `mantenimiento` (`id`, `nombre`) VALUES
(1, 'Pablo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pedido`
--

CREATE TABLE `pedido` (
  `id` int(11) NOT NULL,
  `fechaEntrega` date NOT NULL,
  `IdCliente` int(11) NOT NULL,
  `fechaRealizada` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `pedido`
--

INSERT INTO `pedido` (`id`, `fechaEntrega`, `IdCliente`, `fechaRealizada`) VALUES
(1, '2024-12-11', 1, '2024-12-25');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

CREATE TABLE `producto` (
  `id` int(11) NOT NULL,
  `nombre` varchar(20) NOT NULL,
  `stock` int(11) NOT NULL,
  `idproveedor` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`id`, `nombre`, `stock`, `idproveedor`) VALUES
(1, 'barrita', 6, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proveedor`
--

CREATE TABLE `proveedor` (
  `id` int(11) NOT NULL,
  `nombre` varchar(20) NOT NULL,
  `cif` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `proveedor`
--

INSERT INTO `proveedor` (`id`, `nombre`, `cif`) VALUES
(1, 'juanito', 1234);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sala`
--

CREATE TABLE `sala` (
  `id` int(11) NOT NULL,
  `nombre` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `sala`
--

INSERT INTO `sala` (`id`, `nombre`) VALUES
(1, 'sala1');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tarifa`
--

CREATE TABLE `tarifa` (
  `id` int(11) NOT NULL,
  `nombre` varchar(20) NOT NULL,
  `descripcion` varchar(20) NOT NULL,
  `precio` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tarifa`
--

INSERT INTO `tarifa` (`id`, `nombre`, `descripcion`, `precio`) VALUES
(1, 'estudiante', 'para estudiantes', 12);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `actividad`
--
ALTER TABLE `actividad`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idsala` (`idsala`),
  ADD KEY `idempleado` (`idempleado`);

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idtarifa` (`idtarifa`);

--
-- Indices de la tabla `compra`
--
ALTER TABLE `compra`
  ADD KEY `idpedido` (`idpedido`),
  ADD KEY `idproducto` (`idproducto`);

--
-- Indices de la tabla `empleado`
--
ALTER TABLE `empleado`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `equipamiento`
--
ALTER TABLE `equipamiento`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idactividad` (`idactividad`),
  ADD KEY `idmantenimiento` (`idmantenimiento`);

--
-- Indices de la tabla `mantenimiento`
--
ALTER TABLE `mantenimiento`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `pedido`
--
ALTER TABLE `pedido`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `producto`
--
ALTER TABLE `producto`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idproveedor` (`idproveedor`);

--
-- Indices de la tabla `proveedor`
--
ALTER TABLE `proveedor`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `sala`
--
ALTER TABLE `sala`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `tarifa`
--
ALTER TABLE `tarifa`
  ADD PRIMARY KEY (`id`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `actividad`
--
ALTER TABLE `actividad`
  ADD CONSTRAINT `actividad_ibfk_1` FOREIGN KEY (`idsala`) REFERENCES `sala` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `actividad_ibfk_2` FOREIGN KEY (`idempleado`) REFERENCES `empleado` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD CONSTRAINT `cliente_ibfk_1` FOREIGN KEY (`idtarifa`) REFERENCES `tarifa` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `compra`
--
ALTER TABLE `compra`
  ADD CONSTRAINT `compra_ibfk_1` FOREIGN KEY (`idproducto`) REFERENCES `producto` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `compra_ibfk_2` FOREIGN KEY (`idpedido`) REFERENCES `pedido` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `equipamiento`
--
ALTER TABLE `equipamiento`
  ADD CONSTRAINT `equipamiento_ibfk_1` FOREIGN KEY (`idactividad`) REFERENCES `actividad` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `equipamiento_ibfk_2` FOREIGN KEY (`idmantenimiento`) REFERENCES `mantenimiento` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `producto`
--
ALTER TABLE `producto`
  ADD CONSTRAINT `producto_ibfk_1` FOREIGN KEY (`idproveedor`) REFERENCES `proveedor` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
