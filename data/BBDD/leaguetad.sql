-- phpMyAdmin SQL Dump
-- version 4.8.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 13-05-2018 a las 20:00:42
-- Versión del servidor: 10.1.31-MariaDB
-- Versión de PHP: 7.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `leaguetad`
--
CREATE DATABASE IF NOT EXISTS `leaguetad` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `leaguetad`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `calendario`
--

CREATE TABLE `calendario` (
  `id` int(11) NOT NULL,
  `anyo` datetime DEFAULT NULL,
  `liga_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- RELACIONES PARA LA TABLA `calendario`:
--   `liga_id`
--       `liga` -> `id`
--

--
-- Volcado de datos para la tabla `calendario`
--

INSERT INTO `calendario` (`id`, `anyo`, `liga_id`) VALUES
(1, '2018-08-01 00:00:00', 1),
(2, '2018-08-01 00:00:00', 3),
(3, '2018-08-01 00:00:00', 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `equipo`
--

CREATE TABLE `equipo` (
  `id` int(11) NOT NULL,
  `nombre` varchar(100) DEFAULT NULL,
  `liga_id` int(11) DEFAULT NULL,
  `puntos` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- RELACIONES PARA LA TABLA `equipo`:
--   `liga_id`
--       `liga` -> `id`
--

--
-- Volcado de datos para la tabla `equipo`
--

INSERT INTO `equipo` (`id`, `nombre`, `liga_id`, `puntos`) VALUES
(10, 'Real Betis Balompie', 1, 59),
(11, 'Barcelona FC', 1, 55),
(12, 'Real Madrid', 1, 54),
(13, 'Valencia CF', 1, 50),
(14, 'Sevilla FC', 1, 35),
(15, 'Liverpool', 3, 60),
(16, 'Manchester united', 3, 58),
(17, 'Chelsea', 3, 49),
(18, 'Arsenal', 3, 49),
(19, 'Tottenham', 3, 45),
(20, 'Juventus', 2, 91),
(21, 'Nápoles', 2, 85),
(22, 'Roma', 2, 73),
(23, 'Lazio', 2, 71),
(24, 'Inter Milán', 2, 69);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estadistica`
--

CREATE TABLE `estadistica` (
  `id` int(11) NOT NULL,
  `golesLocal` int(11) DEFAULT NULL,
  `golesVisitante` int(11) DEFAULT NULL,
  `pasesLocal` int(11) DEFAULT NULL,
  `pasesVisitante` int(11) DEFAULT NULL,
  `faltasLocal` int(11) DEFAULT NULL,
  `faltasVisitante` int(11) DEFAULT NULL,
  `tirosLocal` int(11) DEFAULT NULL,
  `tirosVisitante` int(11) DEFAULT NULL,
  `partido_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- RELACIONES PARA LA TABLA `estadistica`:
--   `partido_id`
--       `partido` -> `id`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `jornada`
--

CREATE TABLE `jornada` (
  `id` int(11) NOT NULL,
  `numero` int(11) DEFAULT NULL,
  `fecha` datetime DEFAULT NULL,
  `calendario_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- RELACIONES PARA LA TABLA `jornada`:
--   `calendario_id`
--       `calendario` -> `id`
--   `calendario_id`
--       `calendario` -> `id`
--

--
-- Volcado de datos para la tabla `jornada`
--

INSERT INTO `jornada` (`id`, `numero`, `fecha`, `calendario_id`) VALUES
(1, 1, '2018-08-03 00:00:00', 1),
(2, 1, '2018-08-03 00:00:00', 2),
(3, 1, '2018-08-03 00:00:00', 3),
(4, 2, '2018-08-11 00:00:00', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `jugador`
--

CREATE TABLE `jugador` (
  `id` int(11) NOT NULL,
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
  `equipo_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- RELACIONES PARA LA TABLA `jugador`:
--   `equipo_id`
--       `equipo` -> `id`
--   `equipo_id`
--       `equipo` -> `id`
--

--
-- Volcado de datos para la tabla `jugador`
--

INSERT INTO `jugador` (`id`, `nombre`, `nacionalidad`, `posicion`, `edad`, `goles`, `pases`, `faltas`, `expulsiones`, `paradas`, `tiros`, `equipo_id`) VALUES
(1, 'Joaquin Sanchez', 'Español', 'Extremo derecho', 37, 6, 102, 5, 0, 0, 40, 10),
(2, 'Fabian Ruiz', 'Español', 'Centrocampista', 21, 3, 200, 9, 0, 0, 51, 10),
(3, 'Lionel Messi', 'Argentina', 'Delantero', 31, 22, 43, 5, 1, 0, 150, 11),
(4, 'Andres Iniesta', 'Español', 'Centrocampista', 35, 5, 300, 4, 0, 0, 18, 11),
(5, 'Cristiano Ronaldo', 'Portugues', 'Delantero', 34, 30, 49, 6, 2, 0, 40, 12),
(6, 'Keylor Navas', 'Costa Rica', 'Portero', 28, 0, 10, 0, 0, 25, 0, 12),
(7, 'Rodrigo', 'Español', 'Delantero', 24, 8, 26, 10, 3, 0, 16, 13),
(8, 'Gonzalo Guedes', 'Portugues', 'Extremo', 25, 4, 32, 10, 1, 0, 5, 13),
(9, 'Jesus Navas', 'Español', 'Lateral derecho', 30, 1, 27, 12, 2, 0, 3, 14),
(10, 'Steven Nzonzi', 'Francia', 'Centrocampista', 29, 0, 50, 15, 5, 0, 12, 14),
(11, 'Mohamed Salah', 'Egipcio', 'Delantero', 25, 15, 16, 17, 0, 0, 20, 15),
(12, 'Emre Can', 'Alemania', 'Centrocampista', 24, 3, 56, 20, 10, 0, 14, 15),
(13, 'Paul Pogba', 'Francia', 'Centrocampista', 25, 6, 42, 23, 1, 0, 20, 16),
(14, 'David de Gea', 'España', 'Portero', 27, 0, 50, 2, 0, 48, 0, 16),
(15, 'Oliver Giroud', 'Francia', 'Delantero', 31, 7, 20, 4, 0, 0, 32, 17),
(16, 'Marcos Alonso', 'España', 'Defensa', 27, 2, 41, 6, 0, 0, 4, 17),
(17, 'Mesut Ozil', 'Alemania', 'Centrocampista', 29, 10, 40, 7, 0, 0, 15, 18),
(18, 'Skhodran Mustafi', 'Alemania', 'Defensa', 29, 0, 36, 12, 5, 0, 0, 18),
(19, 'Harry Kane', 'Reino Unido', 'Delantero', 24, 20, 20, 1, 0, 0, 20, 19),
(20, 'Dele Alli', 'Reino Unido', 'Centrocampista', 22, 0, 40, 10, 2, 0, 1, 19);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `liga`
--

CREATE TABLE `liga` (
  `id` int(11) NOT NULL,
  `nombre` varchar(100) DEFAULT NULL,
  `pais` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- RELACIONES PARA LA TABLA `liga`:
--

--
-- Volcado de datos para la tabla `liga`
--

INSERT INTO `liga` (`id`, `nombre`, `pais`) VALUES
(1, 'Liga Santander', 'España'),
(2, 'Lega Calcio', 'Italia'),
(3, 'Premier League', 'Inglaterra');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `partido`
--

CREATE TABLE `partido` (
  `id` int(11) NOT NULL,
  `local_id` int(11) DEFAULT NULL,
  `visitante_id` int(11) DEFAULT NULL,
  `jornada_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- RELACIONES PARA LA TABLA `partido`:
--   `jornada_id`
--       `jornada` -> `id`
--   `visitante_id`
--       `equipo` -> `id`
--   `local_id`
--       `equipo` -> `id`
--   `local_id`
--       `equipo` -> `id`
--   `jornada_id`
--       `jornada` -> `id`
--   `visitante_id`
--       `equipo` -> `id`
--

--
-- Volcado de datos para la tabla `partido`
--

INSERT INTO `partido` (`id`, `local_id`, `visitante_id`, `jornada_id`) VALUES
(1, 11, 10, 1),
(2, 16, 15, 2),
(3, 12, 14, 1),
(4, 13, 10, 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tecnico`
--

CREATE TABLE `tecnico` (
  `id` int(11) NOT NULL,
  `nombre` varchar(100) DEFAULT NULL,
  `puesto` varchar(100) DEFAULT NULL,
  `equipo_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- RELACIONES PARA LA TABLA `tecnico`:
--   `equipo_id`
--       `equipo` -> `id`
--   `equipo_id`
--       `equipo` -> `id`
--

--
-- Volcado de datos para la tabla `tecnico`
--

INSERT INTO `tecnico` (`id`, `nombre`, `puesto`, `equipo_id`) VALUES
(1, 'Quique Setién', 'Entrenador', 10);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `calendario`
--
ALTER TABLE `calendario`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_calendario_liga_idx` (`liga_id`);

--
-- Indices de la tabla `equipo`
--
ALTER TABLE `equipo`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_equipo_liga_idx` (`liga_id`);

--
-- Indices de la tabla `estadistica`
--
ALTER TABLE `estadistica`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_estadistica_partido_idx` (`partido_id`);

--
-- Indices de la tabla `jornada`
--
ALTER TABLE `jornada`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_jornada_calendario_idx` (`calendario_id`);

--
-- Indices de la tabla `jugador`
--
ALTER TABLE `jugador`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_jugador_equipo_idx` (`equipo_id`);

--
-- Indices de la tabla `liga`
--
ALTER TABLE `liga`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `partido`
--
ALTER TABLE `partido`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_visitante_idx` (`visitante_id`),
  ADD KEY `fk_local_idx` (`local_id`),
  ADD KEY `fk_partido_jornada_idx` (`jornada_id`);

--
-- Indices de la tabla `tecnico`
--
ALTER TABLE `tecnico`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_tecnico_equipo_idx` (`equipo_id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `calendario`
--
ALTER TABLE `calendario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `equipo`
--
ALTER TABLE `equipo`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT de la tabla `estadistica`
--
ALTER TABLE `estadistica`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `jornada`
--
ALTER TABLE `jornada`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `jugador`
--
ALTER TABLE `jugador`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT de la tabla `liga`
--
ALTER TABLE `liga`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `partido`
--
ALTER TABLE `partido`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `tecnico`
--
ALTER TABLE `tecnico`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `calendario`
--
ALTER TABLE `calendario`
  ADD CONSTRAINT `fk_calendario_liga` FOREIGN KEY (`liga_id`) REFERENCES `liga` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `equipo`
--
ALTER TABLE `equipo`
  ADD CONSTRAINT `fk_equipo_liga` FOREIGN KEY (`liga_id`) REFERENCES `liga` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `estadistica`
--
ALTER TABLE `estadistica`
  ADD CONSTRAINT `fk_estadistica_partido` FOREIGN KEY (`partido_id`) REFERENCES `partido` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `jornada`
--
ALTER TABLE `jornada`
  ADD CONSTRAINT `FK6mkt8n4khlmcq7jntaho9igpc` FOREIGN KEY (`calendario_id`) REFERENCES `calendario` (`id`),
  ADD CONSTRAINT `fk_jornada_calendario` FOREIGN KEY (`calendario_id`) REFERENCES `calendario` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `jugador`
--
ALTER TABLE `jugador`
  ADD CONSTRAINT `FKaum61vdoi1f4v3nb6e27tdpdt` FOREIGN KEY (`equipo_id`) REFERENCES `equipo` (`id`),
  ADD CONSTRAINT `fk_jugador_equipo` FOREIGN KEY (`equipo_id`) REFERENCES `equipo` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `partido`
--
ALTER TABLE `partido`
  ADD CONSTRAINT `FK6kfgivo09t0abinel3wdrxkbu` FOREIGN KEY (`jornada_id`) REFERENCES `jornada` (`id`),
  ADD CONSTRAINT `FK8holox9q23gyen9w53t8rrnys` FOREIGN KEY (`visitante_id`) REFERENCES `equipo` (`id`),
  ADD CONSTRAINT `FKhvg714ja4vkwrdgks4lrenlty` FOREIGN KEY (`local_id`) REFERENCES `equipo` (`id`),
  ADD CONSTRAINT `fk_local` FOREIGN KEY (`local_id`) REFERENCES `equipo` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_partido_jornada` FOREIGN KEY (`jornada_id`) REFERENCES `jornada` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_visitante` FOREIGN KEY (`visitante_id`) REFERENCES `equipo` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `tecnico`
--
ALTER TABLE `tecnico`
  ADD CONSTRAINT `FKgux7jbudl70cjxgcqrdj70bp2` FOREIGN KEY (`equipo_id`) REFERENCES `equipo` (`id`),
  ADD CONSTRAINT `fk_tecnico_equipo` FOREIGN KEY (`equipo_id`) REFERENCES `equipo` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
