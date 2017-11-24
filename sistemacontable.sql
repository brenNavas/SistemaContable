-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 28-11-2016 a las 07:35:38
-- Versión del servidor: 10.1.16-MariaDB
-- Versión de PHP: 7.0.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `sistemacontable`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ajustes`
--

CREATE TABLE `ajustes` (
  `idAjuste` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `monto` double NOT NULL,
  `tipo` varchar(50) NOT NULL,
  `descripcion` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `ajustes`
--

INSERT INTO `ajustes` (`idAjuste`, `fecha`, `monto`, `tipo`, `descripcion`) VALUES
(1, '2016-11-27', 8000, 'Gastos Pagados por Adelantado', 'jajjaa');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `controlproducto`
--

CREATE TABLE `controlproducto` (
  `cantidad` int(11) NOT NULL,
  `costoU` decimal(5,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cuenta`
--

CREATE TABLE `cuenta` (
  `idCuenta` int(11) NOT NULL,
  `codigoCuenta` bigint(45) NOT NULL,
  `nombreCuenta` varchar(90) NOT NULL,
  `tipo` varchar(45) NOT NULL,
  `saldoDeudor` double DEFAULT NULL,
  `saldoAcreedor` double DEFAULT NULL,
  `saldo` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `cuenta`
--

INSERT INTO `cuenta` (`idCuenta`, `codigoCuenta`, `nombreCuenta`, `tipo`, `saldoDeudor`, `saldoAcreedor`, `saldo`) VALUES
(193, 6, 'Cuenta Liquidadora de Resultados', 'Cuenta de Resultado Acreedor', 0, 0, 0),
(24, 12, 'Activos no Corrientes', 'Activo', 0, 0, 0),
(86, 31, 'Capital Contable', 'Capital', 0, 0, 0),
(88, 32, 'Superavit por Revaluaciones', 'Capital', 0, 0, 0),
(90, 33, 'Resultados Acmulados', 'Capital', 0, 0, 0),
(98, 41, 'Costos y Gastos de Operacion', 'Cuenta de Resultado Deudor', 0, 0, 0),
(181, 51, 'Productos de Operacion', 'Cuenta de Resultado Acreedor', 0, 0, 0),
(186, 52, 'Productos', 'Cuenta de Resultado Acreedor', 0, 0, 0),
(194, 61, 'Resultados Operativos', 'Cuenta de Resultado Acreedor', 0, 0, 0),
(196, 71, 'Cuentas de Orden', 'Cuentas de Orden Deudoras', 0, 0, 0),
(197, 81, 'Cuentas de Orden por contra', 'Cuentas de Orden Acreedoras', 0, 0, 0),
(8, 111, 'Cuentas y Documentos por Cobrar', 'Activo', 25700, 0, 0),
(16, 112, 'Estimaciones para Cuentas Incobrables', 'Activo', 0, 0, 0),
(17, 113, 'Inventario de mercancias (o existencias)', 'Activo', 0, 0, 0),
(18, 115, 'IVA Credito Fiscal', 'Activo', 0, 0, 0),
(22, 116, 'Gastos Pagados por anticipado', 'Activo', 0, 0, 0),
(25, 120, 'Bienes Inmuebles', 'Activo', 0, 0, 0),
(29, 121, 'Bienes Muebles', 'Activo', 0, 0, 0),
(34, 122, 'Depreciaciones Acumuladas (CR)', 'Activo', 0, 0, 0),
(36, 123, 'Revaluaciones', 'Activo', 0, 0, 0),
(37, 124, 'Pago a Cuenta', 'Activo', 0, 0, 0),
(38, 125, 'Gastos de Organizacion', 'Activo', 0, 0, 0),
(39, 126, 'Obras en Proceso', 'Activo', 0, 0, 0),
(44, 128, 'Amortizacciones Acumuladas (CR)', 'Activo', 0, 0, 0),
(47, 210, 'Prestamos y Sobregiros Bancarios', 'Pasivo', 0, 0, 0),
(48, 211, 'Cuentas y Documentos por Pagar', 'Pasivo', 0, 15200, 0),
(53, 212, 'Provisiones y Retenciones', 'Pasivo', 0, 0, 0),
(72, 213, 'IVA Debito Fiscal', 'Pasivo', 0, 0, 0),
(73, 214, 'Impuesto sobre la Renta por Pagar', 'Pasivo', 0, 0, 0),
(75, 215, 'Asuntos Pendientes', 'Pasivo', 0, 0, 0),
(78, 216, 'Anticipos de Clientes', 'Pasivo', 0, 0, 0),
(80, 217, 'Cobros Anticipados', 'Pasivo', 0, 0, 0),
(83, 220, 'Documentos por pagar a largo plazo', 'Pasivo', 0, 0, 0),
(87, 310, 'Capital Social', 'Capital', 0, 120000, 0),
(89, 320, 'Superávit por Revaluaciones de Activos', 'Capital', 0, 0, 0),
(91, 330, 'Utilidades Restringidas', 'Capital', 0, 0, 0),
(92, 331, 'Utilidades no Distribuidas', 'Capital', 0, 0, 0),
(95, 332, 'Déficit Acumulado (R)', 'Capital', 0, 0, 0),
(99, 411, 'Costos de Ventas de Mercaderias', 'Cuenta de Resultado Deudor', 10000, 0, 0),
(100, 413, 'Gastos sobre Compras', 'Cuenta de Resultado Deudor', 0, 0, 0),
(101, 414, 'Rebajas y Devoluciones en Ventas', 'Cuenta de Resultado Deudor', 0, 0, 0),
(102, 415, 'Gastos de Administracion', 'Cuenta de Resultado Deudor', 0, 0, 0),
(139, 416, 'Gastos de Ventas', 'Cuenta de Resultado Deudor', 14823, 0, 0),
(175, 417, 'Gastos Financieros', 'Cuenta de Resultado Deudor', 14000, 0, 0),
(180, 418, 'Otros Gastos', 'Cuenta de Resultado Deudor', 0, 0, 0),
(182, 510, 'Ventas', 'Cuenta de Resultado Acreedor', 0, 25000, 0),
(185, 511, 'Rebajas y Devoluciones sobre compras', 'Cuenta de Resultado Acreedor', 0, 0, 0),
(187, 520, 'Otros Productos', 'Cuenta de Resultado Acreedor', 0, 0, 0),
(195, 610, 'Perdidas y Ganancias', 'Cuenta de Resultado Acreedor', 0, 0, 0),
(97, 33202, 'Pérdidas del Ejercicio (R)', 'Capital', 0, 0, 0),
(120, 41020, 'Viaticos', 'Cuenta de Resultado Deudor', 0, 0, 0),
(129, 41030, 'Cuentas Incobrables', 'Cuenta de Resultado Deudor', 0, 0, 0),
(153, 41616, 'Gastos de Representacion', 'Cuenta de Resultado Deudor', 0, 0, 0),
(154, 41617, 'Servicio de Agua', 'Cuenta de Resultado Deudor', 0, 0, 0),
(1, 110001, 'Caja General', 'Activo', 350, 0, 0),
(2, 110002, 'Caja Chica', 'Activo', 0, 0, 0),
(3, 110003, 'Bancos', 'Activo', 12500, 0, 0),
(9, 111001, 'Cuenta Personal', 'Activo', 0, 0, 0),
(10, 111002, 'Clientes', 'Activo', 0, 0, 0),
(11, 111003, 'Documentos por Cobrar', 'Activo', 20000, 0, 0),
(12, 111004, 'Deudores Varios', 'Activo', 0, 0, 0),
(13, 111006, 'Otros Deudores', 'Activo', 0, 0, 0),
(14, 111011, 'IVA Retenido', 'Activo', 0, 0, 0),
(15, 111012, 'IVA Percibido', 'Activo', 0, 0, 0),
(19, 115001, 'IVA Credito Fiscal por Bienes Muebles', 'Activo', 0, 0, 0),
(20, 115002, 'IVA Credito Fiscal por Compras y Gastos', 'Activo', 0, 0, 0),
(21, 115003, 'IVA Credito Fiscal por Importaciones', 'Activo', 0, 0, 0),
(23, 116003, 'Intereses pagados por anticipado', 'Activo', 0, 0, 0),
(26, 120001, 'Terrenos', 'Activo', 0, 0, 0),
(27, 120002, 'Edificaciones', 'Activo', 86250, 0, 0),
(28, 120003, 'Instalaciones', 'Activo', 0, 0, 0),
(30, 121001, 'Mobiliario y Equipo de Oficina', 'Activo', 0, 0, 0),
(31, 121002, 'Vehiculo para Reparto', 'Activo', 15600, 0, 0),
(32, 121005, 'Herramienta y Acessorios', 'Activo', 0, 0, 0),
(33, 121006, 'Otros Activos', 'Activo', 0, 0, 0),
(35, 122001, 'Mobiliario y Equipo de Oficina', 'Activo', 19000, 0, 0),
(40, 126001, 'Instalaciones en proceso', 'Activo', 0, 0, 0),
(41, 126002, 'Construcciones en Proceso', 'Activo', 0, 0, 0),
(42, 127001, 'Patentes y Marcas', 'Activo', 0, 0, 0),
(45, 128001, 'Amortizaciones de Patentes y Marcas', 'Activo', 0, 0, 0),
(46, 129001, 'Bonos y Acciones', 'Activo', 0, 0, 0),
(49, 211001, 'Proveedores', 'Pasivo', 0, 0, 0),
(50, 211005, 'Documentos por Pagar', 'Pasivo', 0, 20300, 0),
(51, 211006, 'Intereses por Pagar', 'Pasivo', 0, 0, 0),
(54, 212001, 'Provisiones ', 'Pasivo', 0, 0, 0),
(58, 212002, 'Retenciones', 'Pasivo', 0, 0, 0),
(66, 212003, 'Beneficios a empleados por pagar', 'Pasivo', 0, 0, 0),
(74, 214001, 'ISR por pagar corriente', 'Pasivo', 0, 0, 0),
(76, 215001, 'Valores pendientes de pago', 'Pasivo', 0, 0, 0),
(77, 215002, 'Diversos', 'Pasivo', 0, 0, 0),
(79, 216001, 'Rebajas y Devoluciones pendientes de Aplicar', 'Pasivo', 0, 0, 0),
(81, 217001, 'Intereses no devengados', 'Pasivo', 0, 0, 0),
(82, 217002, 'Alquileres Cobrados por anticipado', 'Pasivo', 0, 0, 0),
(84, 220001, 'Prestamos Bancarios', 'Pasivo', 0, 0, 0),
(93, 331001, 'Utilidades de Ejercicios Anteriores', 'Capital', 0, 0, 0),
(94, 331002, 'Utilidad del Ejercicio', 'Capital', 0, 0, 0),
(96, 332001, 'Pérdidas de Ejercicios Anteriores (R)', 'Capital', 0, 0, 0),
(103, 415001, 'Sueldos', 'Cuenta de Resultado Deudor', 10177, 0, 0),
(104, 415002, 'Vacaciones y Bonificaciones', 'Cuenta de Resultado Deudor', 0, 0, 0),
(105, 415003, 'Aguinaldos y Gratificaciones', 'Cuenta de Resultado Deudor', 0, 0, 0),
(106, 415004, 'Gasto por Alquiler', 'Cuenta de Resultado Deudor', 0, 25000, 0),
(107, 415007, 'Papeleria y utiles', 'Cuenta de Resultado Deudor', 0, 0, 0),
(108, 415008, 'Comunicaciones', 'Cuenta de Resultado Deudor', 0, 0, 0),
(109, 415009, 'Primas de Seguros', 'Cuenta de Resultado Deudor', 0, 0, 0),
(110, 415010, 'Impuestos', 'Cuenta de Resultado Deudor', 177, 0, 0),
(111, 415011, 'Luz Electrica ', 'Cuenta de Resultado Deudor', 0, 0, 0),
(112, 415012, 'Depreciaciones', 'Cuenta de Resultado Deudor', 0, 0, 0),
(113, 415013, 'Mantenimiento Equipo y Oficina', 'Cuenta de Resultado Deudor', 0, 0, 0),
(114, 415014, 'Mantenimiento de Edificio', 'Cuenta de Resultado Deudor', 0, 0, 0),
(115, 415015, 'Donaciones', 'Cuenta de Resultado Deudor', 0, 0, 0),
(116, 415016, 'Gastos de Representacion', 'Cuenta de Resultado Deudor', 0, 0, 0),
(117, 415017, 'Servicio de Agua', 'Cuenta de Resultado Deudor', 0, 0, 0),
(118, 415018, 'Publicaciones e Inscripciones', 'Cuenta de Resultado Deudor', 0, 0, 0),
(119, 415019, 'Aseo y Ornato', 'Cuenta de Resultado Deudor', 0, 0, 0),
(121, 415021, 'Indemnizcoines', 'Cuenta de Resultado Deudor', 0, 0, 0),
(122, 415022, 'Encomiendas', 'Cuenta de Resultado Deudor', 0, 0, 0),
(123, 415023, 'Comisiones', 'Cuenta de Resultado Deudor', 0, 0, 0),
(124, 415024, 'Publicidad', 'Cuenta de Resultado Deudor', 0, 0, 0),
(125, 415025, 'Comisiones Bancarias', 'Cuenta de Resultado Deudor', 0, 0, 0),
(126, 415027, 'Mantenimiento de Vehiculos', 'Cuenta de Resultado Deudor', 0, 0, 0),
(127, 415028, 'Transportes y Pasajes', 'Cuenta de Resultado Deudor', 0, 0, 0),
(128, 415029, 'Combustibles y Lubricantes', 'Cuenta de Resultado Deudor', 0, 0, 0),
(130, 415031, 'Suscripciones', 'Cuenta de Resultado Deudor', 0, 0, 0),
(131, 415032, 'Vigilancia', 'Cuenta de Resultado Deudor', 0, 0, 0),
(132, 415033, 'Mantenimiento de Instalaciones', 'Cuenta de Resultado Deudor', 0, 0, 0),
(133, 415034, 'Capacitaciones al personal', 'Cuenta de Resultado Deudor', 0, 0, 0),
(134, 415036, 'Tramites y Servicios', 'Cuenta de Resultado Deudor', 0, 0, 0),
(135, 415037, 'Horas Extras', 'Cuenta de Resultado Deudor', 0, 0, 0),
(137, 415038, 'INSAFORP', 'Cuenta de Resultado Deudor', 0, 0, 0),
(136, 415039, 'Amortizacion de Intangibles', 'Cuenta de Resultado Deudor', 0, 0, 0),
(138, 415040, 'Diversos', 'Cuenta de Resultado Deudor', 0, 0, 0),
(140, 416001, 'Sueldos', 'Cuenta de Resultado Deudor', 0, 0, 0),
(141, 416002, 'Vacaciones y Bonificaciones', 'Cuenta de Resultado Deudor', 0, 0, 0),
(142, 416003, 'Aguinaldos y Gratificaciones', 'Cuenta de Resultado Deudor', 0, 0, 0),
(143, 416004, 'Alquileres', 'Cuenta de Resultado Deudor', 0, 0, 0),
(144, 416007, 'Papeleria y utiles', 'Cuenta de Resultado Deudor', 0, 0, 0),
(145, 416008, 'Comunicaciones ', 'Cuenta de Resultado Deudor', 0, 0, 0),
(146, 416009, 'Primas de Seguros', 'Cuenta de Resultado Deudor', 0, 0, 0),
(147, 416010, 'Impuestos', 'Cuenta de Resultado Deudor', 0, 0, 0),
(148, 416011, 'Luz Electrica', 'Cuenta de Resultado Deudor', 0, 0, 0),
(149, 416012, 'Depreciaciones', 'Cuenta de Resultado Deudor', 0, 0, 0),
(150, 416013, 'Mantenimiento Equipo de Oficina', 'Cuenta de Resultado Deudor', 823, 0, 0),
(151, 416014, 'Mantenimiento de Edificio', 'Cuenta de Resultado Deudor', 0, 0, 0),
(152, 416015, 'Donaciones', 'Cuenta de Resultado Deudor', 0, 0, 0),
(154, 416018, 'Publicaciones e Inscripciones', 'Cuenta de Resultado Deudor', 0, 0, 0),
(155, 416019, 'Aseo y Ornato', 'Cuenta de Resultado Deudor', 0, 0, 0),
(156, 416020, 'Viaticos', 'Cuenta de Resultado Deudor', 0, 0, 0),
(157, 416021, 'Indemnizaciones', 'Cuenta de Resultado Deudor', 0, 0, 0),
(158, 416022, 'Encomiendas', 'Cuenta de Resultado Deudor', 0, 0, 0),
(159, 416023, 'Comisiones', 'Cuenta de Resultado Deudor', 0, 0, 0),
(160, 416024, 'Publicidad', 'Cuenta de Resultado Deudor', 0, 0, 0),
(161, 416025, 'Comisiones Bancarias ', 'Cuenta de Resultado Deudor', 0, 0, 0),
(162, 416027, 'Mantenimiento de Vehiculos', 'Cuenta de Resultado Deudor', 0, 0, 0),
(163, 416028, 'Transporte y Pasajes', 'Cuenta de Resultado Deudor', 0, 0, 0),
(164, 416029, 'Combustibles y Lubricantes', 'Cuenta de Resultado Deudor', 0, 0, 0),
(165, 416030, 'Cuentas Incobrables', 'Cuenta de Resultado Deudor', 0, 0, 0),
(166, 416031, 'Suscripciones', 'Cuenta de Resultado Deudor', 0, 0, 0),
(167, 416032, 'Vigilancia', 'Cuenta de Resultado Deudor', 0, 0, 0),
(168, 416033, 'Mantenimiento de Instalaciones', 'Cuenta de Resultado Deudor', 0, 0, 0),
(169, 416034, 'Capacitacion al personal', 'Cuenta de Resultado Deudor', 0, 0, 0),
(170, 416036, 'Tramites y Servicio', 'Cuenta de Resultado Deudor', 0, 0, 0),
(171, 416037, 'Horas Extras', 'Cuenta de Resultado Deudor', 0, 0, 0),
(172, 416038, 'INSAFORP', 'Cuenta de Resultado Deudor', 0, 0, 0),
(173, 416039, 'Amortizaciones de Intangibles', 'Cuenta de Resultado Deudor', 0, 0, 0),
(174, 416040, 'Diversos', 'Cuenta de Resultado Deudor', 0, 0, 0),
(176, 417001, 'Intereses', 'Cuenta de Resultado Deudor', 0, 0, 0),
(177, 417002, 'Comisiones', 'Cuenta de Resultado Deudor', 0, 0, 0),
(178, 417003, 'Bonificaciones a Clientes', 'Cuenta de Resultado Deudor', 0, 0, 0),
(179, 417006, 'Otros', 'Cuenta de Resultado Deudor', 0, 0, 0),
(183, 510001, 'Ventas al contado', 'Cuenta de Resultado Acreedor', 0, 0, 0),
(184, 510002, 'Ventas al credito', 'Cuenta de Resultado Acreedor', 0, 0, 0),
(188, 520001, 'Intereses', 'Cuenta de Resultado Acreedor', 0, 0, 0),
(191, 520002, 'Comisiones', 'Cuenta de Resultado Acreedor', 0, 0, 0),
(192, 520003, 'Diversos', 'Cuenta de Resultado Acreedor', 0, 0, 0),
(7, 11000303, 'Depositos a Plazo', 'Activo', 0, 0, 0),
(60, 21002002, 'Procuraduria General', 'Pasivo ', 0, 0, 0),
(4, 110003001, 'Depositos en Cuentas Corrientes', 'Activo', 0, 0, 0),
(6, 110003002, 'Depositos en Cuentas de Ahorro', 'Activo', 0, 0, 0),
(43, 127001001, 'Costo de Adquisicion de Patentes y Marcas', 'Activo', 0, 0, 0),
(52, 211004001, 'Letras y Pagares', 'Pasivo', 0, 0, 0),
(55, 212001001, 'Impuesto IVA', 'Pasivo', 0, 0, 0),
(56, 212001002, 'Impuesto Pago a Cuenta', 'Pasivo', 0, 0, 0),
(57, 212001003, 'Impuestos Municipales', 'Pasivo', 0, 0, 0),
(59, 212002001, 'Impuesto sobre la renta retenido', 'Pasivo', 0, 0, 0),
(61, 212002004, 'Unidad de Pensiones ISSS', 'Pasivo', 0, 0, 0),
(62, 212002005, 'Administracion de Fondo de Pensiones', 'Pasivo', 0, 0, 0),
(65, 212002006, 'Otras Retenciones', 'Pasivo', 0, 0, 0),
(67, 212003001, 'Planillas por Pagar', 'Pasivo', 0, 0, 0),
(68, 212003002, 'Bonificaciones', 'Pasivo', 0, 0, 0),
(69, 212003003, 'Comisiones', 'Pasivo', 0, 0, 0),
(70, 212003004, 'Aguinaldos', 'Pasivo', 0, 0, 0),
(71, 212003005, 'Vacaciones', 'Pasivo', 0, 0, 0),
(85, 220001001, 'Garantia Hipotecaria', 'Pasivo', 0, 23900, 0),
(189, 520001001, 'Depositos de Ahoro', 'Cuenta de Resultado Acreedor', 0, 0, 0),
(190, 520001002, 'Clientes', 'Cuenta de Resultado Acreedor', 0, 0, 0),
(5, 110003001001, 'Banco Agricola', 'Activo', 0, 0, 0),
(63, 212002005001, 'AFP-Confia', 'Pasivo', 0, 0, 0),
(64, 212002005002, 'AFP-Crecer', 'Pasivo', 0, 0, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detallerequisicion`
--

CREATE TABLE `detallerequisicion` (
  `idRequisicion` int(11) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `costoUnitario` double NOT NULL,
  `monto` double NOT NULL,
  `codigoArticulo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `kardex`
--

CREATE TABLE `kardex` (
  `codigoKardex` int(11) NOT NULL,
  `tipoKardex` varchar(45) DEFAULT NULL,
  `idPeriodo` int(11) DEFAULT NULL,
  `codigoProducto` int(11) DEFAULT NULL,
  `costoUe` decimal(5,2) DEFAULT NULL,
  `cantidadE` int(11) DEFAULT NULL,
  `costoUs` decimal(5,2) DEFAULT NULL,
  `cantidadS` int(11) DEFAULT NULL,
  `cantidadT` int(11) DEFAULT NULL,
  `costoUt` decimal(5,2) DEFAULT NULL,
  `fecha` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `kardex2`
--

CREATE TABLE `kardex2` (
  `costoUe` decimal(5,2) DEFAULT NULL,
  `costoUs` decimal(5,2) DEFAULT NULL,
  `cantidadE` int(11) DEFAULT NULL,
  `cantidadS` int(11) DEFAULT NULL,
  `cantidadT` int(11) DEFAULT NULL,
  `costoUt` decimal(5,2) DEFAULT NULL,
  `fecha` varchar(45) DEFAULT NULL,
  `codigoKardex` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `kardex2`
--

INSERT INTO `kardex2` (`costoUe`, `costoUs`, `cantidadE`, `cantidadS`, `cantidadT`, `costoUt`, `fecha`, `codigoKardex`) VALUES
('10.00', '0.00', 10, 0, 10, '10.00', '1994-12-12', 48),
('10.00', '0.00', 10, 0, 20, '10.00', '1995-10-10', 50),
('0.00', '10.00', 0, 20, 0, '10.00', '1996-12-12', 51),
('10.00', '0.00', 100, 0, 100, '10.00', '1997-12-12', 53),
('10.00', '0.00', 100, 0, 200, '10.00', '1998-12-12', 55),
('20.00', '0.00', 200, 0, 400, '15.00', '1999-12-12', 56),
('0.00', '15.00', 0, 50, 350, '15.00', '2000-12-12', 57),
('0.00', '15.00', 0, 50, 300, '15.00', '2001-12-12', 58),
('0.00', '15.00', 0, 50, 250, '15.00', '2002-12-12', 59),
('0.00', '15.00', 0, 50, 200, '15.00', '2003-12-12', 60),
('0.00', '15.00', 0, 200, 0, '15.00', '2004-12-12', 61),
('18.00', '0.00', 200, 0, 200, '18.00', '2004-12-13', 62),
('20.00', '0.00', 200, 0, 400, '19.00', '2004-12-14', 63),
('0.00', '19.00', 0, 100, 200, '19.00', '2005-10-01', 65),
('30.00', '0.00', 100, 0, 300, '22.67', '2006-12-12', 66),
('0.00', '22.67', 0, 100, 200, '22.67', '2007-12-12', 67),
('0.00', '22.67', 0, 5, 195, '22.67', '2012-11-20', 70);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `movimientoarticulo`
--

CREATE TABLE `movimientoarticulo` (
  `codigoProducto` int(11) NOT NULL,
  `tipoMV` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `cantidad` int(11) NOT NULL,
  `cantidadAcumulada` int(11) NOT NULL,
  `costoUnitario` double NOT NULL,
  `precioVenta` double NOT NULL,
  `proveedor` varchar(45) NOT NULL,
  `saldo` double NOT NULL,
  `saldoAcumulado` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `movimientocuenta`
--

CREATE TABLE `movimientocuenta` (
  `idTransaccion` int(11) NOT NULL,
  `monto` double NOT NULL,
  `tipo` int(45) NOT NULL,
  `codigoCuenta` bigint(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `nuevatransaccion`
--

CREATE TABLE `nuevatransaccion` (
  `codigo` int(11) NOT NULL,
  `monto` double NOT NULL,
  `tipo` varchar(50) NOT NULL,
  `descripcion` varchar(80) NOT NULL,
  `fecha` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `nuevatransaccion`
--

INSERT INTO `nuevatransaccion` (`codigo`, `monto`, `tipo`, `descripcion`, `fecha`) VALUES
(12, 123, 'Venta', 'Venta de Cedro', '2016-11-10'),
(34, 345, 'Compra', 'Venta de Pinos de Madera', '2016-11-19'),
(67, 345, 'Compra', 'compra', '2016-11-19'),
(78, 456, 'Compra', 'Compra de Materiales', '2016-11-19'),
(109, 240, 'Compra', 'venta', '2016-11-11'),
(123, 56, 'Venta', 'venta', '2016-11-19'),
(126, 345, 'Compra', 'compras', '2016-11-19'),
(250, 560, 'Compra', 'muebles', '2016-11-27'),
(890, 259.9, 'Compra', 'compra2', '2016-11-27');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `periodo`
--

CREATE TABLE `periodo` (
  `idPeriodo` int(11) NOT NULL,
  `fin` varchar(45) NOT NULL,
  `inicio` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `periodo`
--

INSERT INTO `periodo` (`idPeriodo`, `fin`, `inicio`) VALUES
(1, '2016-11-30', '2016-11-18'),
(2, '2016-11-30', '2016-11-17'),
(3, '2016-12-29', '2016-11-29');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `persona`
--

CREATE TABLE `persona` (
  `idPersona` int(11) NOT NULL,
  `nombrePersona` varchar(45) NOT NULL,
  `apellido_ma` varchar(45) NOT NULL,
  `apellido_pa` varchar(45) NOT NULL,
  `num_dui` varchar(45) NOT NULL,
  `direccion` varchar(45) NOT NULL,
  `telefono` varchar(9) NOT NULL,
  `email` varchar(45) DEFAULT NULL,
  `sueldo` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `persona`
--

INSERT INTO `persona` (`idPersona`, `nombrePersona`, `apellido_ma`, `apellido_pa`, `num_dui`, `direccion`, `telefono`, `email`, `sueldo`) VALUES
(1, 'Liesel', 'García', 'Rodríguez', '123456', 'Bella Vista', '89765432', 'liesel@hotmail.com', 1234),
(2, 'Yesenia Elizabeth', 'García', 'Rodríguez', '123456', 'Sierra Morena', '78904567', 'yeseliz@gmail.com', 3456),
(3, 'Bryan Arthur', 'Chamagua', 'Miranda', '5678923', 'lalalla', '12346', 'bryan@hotmail.com', 3456),
(4, 'Jaime Ernesto', 'Calderón', 'Mejía', '45689', 'lalala', '67894567', 'jaime@gmail.com', 3456),
(5, 'Juan Francisco', 'Grande', 'Palacios', '456789', 'lala', '78904567', 'frank@hotmail.com', 3456),
(6, 'Osiris Alexander', 'Molina', 'Lovos', '3456789', 'Ciudad Delgado', '123456', 'osiris@hotmail.com', 3456);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proceso`
--

CREATE TABLE `proceso` (
  `idProceso` int(11) NOT NULL,
  `inicio` varchar(45) NOT NULL,
  `fin` varchar(45) DEFAULT NULL,
  `cliente` varchar(45) NOT NULL,
  `descripcion` varchar(45) DEFAULT NULL,
  `unidades` int(11) NOT NULL,
  `estado` int(11) NOT NULL,
  `precioVenta` double NOT NULL,
  `idPeriodo` int(11) NOT NULL,
  `codigoProducto` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

CREATE TABLE `producto` (
  `codigoProducto` int(11) NOT NULL,
  `nombreProducto` varchar(45) NOT NULL,
  `tipoProducto` varchar(45) NOT NULL,
  `unidadMedida` varchar(45) NOT NULL,
  `proceso` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`codigoProducto`, `nombreProducto`, `tipoProducto`, `unidadMedida`, `proceso`) VALUES
(1, 'Cedro', 'Materia Prima', 'Vara', NULL),
(2, 'Conacaste', 'Materia Prima', 'Vara', NULL),
(3, 'Caoba', 'Materia Prima', 'Vara', NULL),
(4, 'Laurel', 'Materia Prima', 'Vara', NULL),
(5, 'Cenicero', 'Materia Prima', 'Vara', NULL),
(6, 'Ciprés', 'Materia Prima', 'Vara', NULL),
(7, 'Pino', 'Materia Prima', 'Vara', NULL),
(8, 'Ron ron', 'Materia Prima', 'Vara', NULL),
(9, 'Cortés Blanco', 'Materia Prima', 'Vara', NULL),
(10, 'Volador', 'Materia Prima', 'Vara', NULL),
(11, 'Funera', 'Producto en Proceso', 'Vara', 'Derramado y Tala'),
(12, 'Pino', 'Materia Prima', 'Vara', NULL),
(21, 'Tabloncillo', 'Producto en Proceso', 'Vara', 'Acabado'),
(22, 'Renglón', 'Producto Terminado', 'Unidad', NULL),
(23, 'Costanera', 'Producto Terminado', 'Unidad', NULL),
(24, 'Tabla', 'Producto Terminado', 'Unidad', NULL),
(25, 'Pilarillo', 'Producto en Proceso', 'Vara', 'Troceado y aserrado'),
(26, 'Tabla ancha', 'Producto Terminado', 'Unidad', NULL),
(45, 'Cedro', 'Producto Terminado', 'Metros', NULL),
(234, 'Tablita', 'Madera', 'Metros', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `requisicion`
--

CREATE TABLE `requisicion` (
  `idRequisicion` int(11) NOT NULL,
  `fecha_R` date NOT NULL,
  `montoRequisicion` double NOT NULL,
  `idProceso` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `trabajador`
--

CREATE TABLE `trabajador` (
  `idPersona` int(11) NOT NULL,
  `cargo` varchar(45) NOT NULL,
  `tipo_acceso` varchar(45) NOT NULL,
  `nombreUsuario` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `trabajador`
--

INSERT INTO `trabajador` (`idPersona`, `cargo`, `tipo_acceso`, `nombreUsuario`, `password`) VALUES
(1, 'Presidente', 'Administrador', 'admin', 'sic'),
(3, 'Empleado', 'Usuario', 'bryan', 'bryan123'),
(5, 'Empleado', 'Usuario', 'francisco', 'francisco123'),
(4, 'Empleado', 'Usuario', 'jaime', 'jaime123'),
(6, 'Empleado', 'Usuario', 'osiris', 'osiris123'),
(2, 'Empleado', 'Usuario', 'yeseliz', 'yeseliz123');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `transaccion`
--

CREATE TABLE `transaccion` (
  `idTransaccion` int(11) NOT NULL,
  `tipoTransaccion` varchar(45) NOT NULL,
  `fecha` date NOT NULL,
  `descripcion` varchar(45) NOT NULL,
  `idPeriodo` int(11) NOT NULL,
  `monto` double NOT NULL,
  `debe` double NOT NULL,
  `haber` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `ajustes`
--
ALTER TABLE `ajustes`
  ADD PRIMARY KEY (`idAjuste`);

--
-- Indices de la tabla `cuenta`
--
ALTER TABLE `cuenta`
  ADD PRIMARY KEY (`codigoCuenta`);

--
-- Indices de la tabla `detallerequisicion`
--
ALTER TABLE `detallerequisicion`
  ADD PRIMARY KEY (`idRequisicion`),
  ADD KEY `FK_detalleRequision_articulo` (`codigoArticulo`);

--
-- Indices de la tabla `kardex`
--
ALTER TABLE `kardex`
  ADD PRIMARY KEY (`codigoKardex`),
  ADD UNIQUE KEY `FK_a_idx` (`codigoProducto`),
  ADD UNIQUE KEY `FK_p_idx` (`idPeriodo`);

--
-- Indices de la tabla `kardex2`
--
ALTER TABLE `kardex2`
  ADD PRIMARY KEY (`codigoKardex`);

--
-- Indices de la tabla `movimientoarticulo`
--
ALTER TABLE `movimientoarticulo`
  ADD PRIMARY KEY (`codigoProducto`);

--
-- Indices de la tabla `movimientocuenta`
--
ALTER TABLE `movimientocuenta`
  ADD UNIQUE KEY `FK_movimientoCuenta_transaccion_idx` (`idTransaccion`),
  ADD UNIQUE KEY `FK_movimientoCuenta_cuenta_idx` (`codigoCuenta`);

--
-- Indices de la tabla `nuevatransaccion`
--
ALTER TABLE `nuevatransaccion`
  ADD PRIMARY KEY (`codigo`);

--
-- Indices de la tabla `periodo`
--
ALTER TABLE `periodo`
  ADD PRIMARY KEY (`idPeriodo`);

--
-- Indices de la tabla `persona`
--
ALTER TABLE `persona`
  ADD PRIMARY KEY (`idPersona`);

--
-- Indices de la tabla `proceso`
--
ALTER TABLE `proceso`
  ADD PRIMARY KEY (`idProceso`),
  ADD KEY `FK_proceso_periodo` (`idPeriodo`),
  ADD KEY `FK_proceso_producto` (`codigoProducto`);

--
-- Indices de la tabla `producto`
--
ALTER TABLE `producto`
  ADD PRIMARY KEY (`codigoProducto`);

--
-- Indices de la tabla `requisicion`
--
ALTER TABLE `requisicion`
  ADD PRIMARY KEY (`idRequisicion`),
  ADD UNIQUE KEY `FK_requisicion_proceso_idx` (`idProceso`);

--
-- Indices de la tabla `trabajador`
--
ALTER TABLE `trabajador`
  ADD UNIQUE KEY `usuario_UNIQUE` (`nombreUsuario`),
  ADD KEY `FK_persona_idx` (`idPersona`);

--
-- Indices de la tabla `transaccion`
--
ALTER TABLE `transaccion`
  ADD PRIMARY KEY (`idTransaccion`),
  ADD KEY `FK_transaccion_periodo_idx` (`idPeriodo`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `ajustes`
--
ALTER TABLE `ajustes`
  MODIFY `idAjuste` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT de la tabla `detallerequisicion`
--
ALTER TABLE `detallerequisicion`
  MODIFY `idRequisicion` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `kardex`
--
ALTER TABLE `kardex`
  MODIFY `codigoKardex` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `kardex2`
--
ALTER TABLE `kardex2`
  MODIFY `codigoKardex` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=71;
--
-- AUTO_INCREMENT de la tabla `movimientoarticulo`
--
ALTER TABLE `movimientoarticulo`
  MODIFY `codigoProducto` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `movimientocuenta`
--
ALTER TABLE `movimientocuenta`
  MODIFY `idTransaccion` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `periodo`
--
ALTER TABLE `periodo`
  MODIFY `idPeriodo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT de la tabla `persona`
--
ALTER TABLE `persona`
  MODIFY `idPersona` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT de la tabla `proceso`
--
ALTER TABLE `proceso`
  MODIFY `idProceso` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `producto`
--
ALTER TABLE `producto`
  MODIFY `codigoProducto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=235;
--
-- AUTO_INCREMENT de la tabla `requisicion`
--
ALTER TABLE `requisicion`
  MODIFY `idRequisicion` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `trabajador`
--
ALTER TABLE `trabajador`
  MODIFY `idPersona` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT de la tabla `transaccion`
--
ALTER TABLE `transaccion`
  MODIFY `idTransaccion` int(11) NOT NULL AUTO_INCREMENT;
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `detallerequisicion`
--
ALTER TABLE `detallerequisicion`
  ADD CONSTRAINT `FK_detalleRequision_articulo` FOREIGN KEY (`codigoArticulo`) REFERENCES `producto` (`codigoProducto`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `FK_detalleRequision_requisicion` FOREIGN KEY (`idRequisicion`) REFERENCES `requisicion` (`idRequisicion`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `kardex`
--
ALTER TABLE `kardex`
  ADD CONSTRAINT `FK_kardex_periodo` FOREIGN KEY (`idPeriodo`) REFERENCES `periodo` (`idPeriodo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `FK_kardex_producto` FOREIGN KEY (`codigoProducto`) REFERENCES `producto` (`codigoProducto`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `movimientoarticulo`
--
ALTER TABLE `movimientoarticulo`
  ADD CONSTRAINT `FK_movimientoArticulo_producto` FOREIGN KEY (`codigoProducto`) REFERENCES `producto` (`codigoProducto`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `movimientocuenta`
--
ALTER TABLE `movimientocuenta`
  ADD CONSTRAINT `FK_movimientoCuenta_cuenta` FOREIGN KEY (`codigoCuenta`) REFERENCES `cuenta` (`codigoCuenta`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `FK_movimientoCuenta_transaccion` FOREIGN KEY (`idTransaccion`) REFERENCES `transaccion` (`idTransaccion`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `proceso`
--
ALTER TABLE `proceso`
  ADD CONSTRAINT `FK_proceso_periodo` FOREIGN KEY (`idPeriodo`) REFERENCES `periodo` (`idPeriodo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `FK_proceso_producto` FOREIGN KEY (`codigoProducto`) REFERENCES `producto` (`codigoProducto`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `requisicion`
--
ALTER TABLE `requisicion`
  ADD CONSTRAINT `FK_requisicion_proceso` FOREIGN KEY (`idProceso`) REFERENCES `proceso` (`idProceso`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `trabajador`
--
ALTER TABLE `trabajador`
  ADD CONSTRAINT `FK_persona` FOREIGN KEY (`idPersona`) REFERENCES `persona` (`idPersona`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `transaccion`
--
ALTER TABLE `transaccion`
  ADD CONSTRAINT `FK_transaccion_periodo` FOREIGN KEY (`idPeriodo`) REFERENCES `periodo` (`idPeriodo`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
