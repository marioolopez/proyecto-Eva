-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 25-11-2024 a las 15:28:38
-- Versión del servidor: 9.0.1
-- Versión de PHP: 8.0.30

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

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `actividad`
--

CREATE TABLE `actividad` (
  `id` int NOT NULL,
  `nombre` varchar(20) COLLATE utf8mb4_general_ci NOT NULL,
  `descripcion` varchar(20) COLLATE utf8mb4_general_ci NOT NULL,
  `idsala` int NOT NULL,
  `idempleado` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `id` int NOT NULL,
  `nombre` varchar(20) COLLATE utf8mb4_general_ci NOT NULL,
  `telefono` int NOT NULL,
  `dni` varchar(20) COLLATE utf8mb4_general_ci NOT NULL,
  `edad` int NOT NULL,
  `idtarifa` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`id`, `nombre`, `telefono`, `dni`, `edad`, `idtarifa`) VALUES
(1, 'jotrg', 444, '4444', 4, 1),
(2, 'aaaaaa', 999999999, '99999', 99999999, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `compra`
--

CREATE TABLE `compra` (
  `idpedido` int NOT NULL,
  `idproducto` int NOT NULL,
  `cantidad` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `compra`
--

INSERT INTO `compra` (`idpedido`, `idproducto`, `cantidad`) VALUES
(5, 2, 10),
(6, 2, 10),
(7, 2, 10),
(7, 1, 5),
(7, 2, 10),
(8, 1, 5),
(9, 2, 10),
(10, 2, 10),
(11, 2, 10),
(11, 1, 5),
(12, 2, 10),
(13, 2, 10),
(13, 1, 5);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleado`
--

CREATE TABLE `empleado` (
  `id` int NOT NULL,
  `nombre` varchar(20) COLLATE utf8mb4_general_ci NOT NULL,
  `dni` varchar(20) COLLATE utf8mb4_general_ci NOT NULL,
  `salario` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `equipamiento`
--

CREATE TABLE `equipamiento` (
  `id` int NOT NULL,
  `nombre` varchar(20) COLLATE utf8mb4_general_ci NOT NULL,
  `descripcion` varchar(20) COLLATE utf8mb4_general_ci NOT NULL,
  `idactividad` int NOT NULL,
  `idmantenimiento` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mantenimiento`
--

CREATE TABLE `mantenimiento` (
  `id` int NOT NULL,
  `nombre` varchar(20) COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pedido`
--

CREATE TABLE `pedido` (
  `id` int NOT NULL,
  `fechaEntrega` date NOT NULL,
  `IdCliente` int NOT NULL,
  `fechaRealizada` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `pedido`
--

INSERT INTO `pedido` (`id`, `fechaEntrega`, `IdCliente`, `fechaRealizada`) VALUES
(4, '2024-11-28', 1, '2024-11-24'),
(5, '2024-11-24', 2, '2024-11-24'),
(6, '2024-11-24', 1, '2024-11-24'),
(7, '2024-11-24', 1, '2024-11-24'),
(8, '2024-11-24', 1, '2024-11-24'),
(9, '2024-11-14', 1, '2024-11-24'),
(10, '2024-11-30', 2, '2024-11-25'),
(11, '2024-11-30', 2, '2024-11-25'),
(12, '2024-11-25', 1, '2024-11-25'),
(13, '2024-11-30', 2, '2024-11-25');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

CREATE TABLE `producto` (
  `id` int NOT NULL,
  `nombre` varchar(20) COLLATE utf8mb4_general_ci NOT NULL,
  `stock` int NOT NULL,
  `idproveedor` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`id`, `nombre`, `stock`, `idproveedor`) VALUES
(1, 'patatas', 5, 1),
(2, 'caramelos', 10, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proveedor`
--

CREATE TABLE `proveedor` (
  `id` int NOT NULL,
  `nombre` varchar(20) COLLATE utf8mb4_general_ci NOT NULL,
  `cif` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `proveedor`
--

INSERT INTO `proveedor` (`id`, `nombre`, `cif`) VALUES
(1, 'pro', 22);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sala`
--

CREATE TABLE `sala` (
  `id` int NOT NULL,
  `nombre` varchar(20) COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tarifa`
--

CREATE TABLE `tarifa` (
  `id` int NOT NULL,
  `nombre` varchar(20) COLLATE utf8mb4_general_ci NOT NULL,
  `descripcion` varchar(20) COLLATE utf8mb4_general_ci NOT NULL,
  `precio` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tarifa`
--

INSERT INTO `tarifa` (`id`, `nombre`, `descripcion`, `precio`) VALUES
(1, 'plana', 'aaa', 8);

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
  ADD CONSTRAINT `compra_ibfk_1` FOREIGN KEY (`idproducto`) REFERENCES `producto` (`id`) ON DELETE CASCADE,
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
