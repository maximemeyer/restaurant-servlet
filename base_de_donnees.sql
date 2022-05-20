-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : jeu. 19 mai 2022 à 21:40
-- Version du serveur :  5.7.31
-- Version de PHP : 7.4.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `restaurant`
--

-- --------------------------------------------------------

--
-- Structure de la table `recette`
--

DROP TABLE IF EXISTS `recette`;
CREATE TABLE IF NOT EXISTS `recette` (
  `id_recette` int(11) NOT NULL AUTO_INCREMENT,
  `id_resto` int(11) DEFAULT NULL,
  `nom_recette` varchar(50) DEFAULT NULL,
  `id_type_recette` int(11) DEFAULT NULL,
  `prix` double DEFAULT NULL,
  PRIMARY KEY (`id_recette`),
  KEY `id_resto` (`id_resto`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `recette`
--

INSERT INTO `recette` (`id_recette`, `id_resto`, `nom_recette`, `id_type_recette`, `prix`) VALUES
(1, 1, 'Nems', 3, 10),
(2, 1, 'Rouleau de printemps', 2, 7.85),
(3, 2, 'Pizza 4 fromages', 2, 13.5),
(4, 2, 'Tiramisu', 3, 7.5),
(5, 3, 'Salade César', 1, 17.99),
(6, 3, 'Risotto à la truffe', 2, 25),

-- --------------------------------------------------------

--
-- Structure de la table `restaurant`
--

DROP TABLE IF EXISTS `restaurant`;
CREATE TABLE IF NOT EXISTS `restaurant` (
  `id_resto` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(20) DEFAULT NULL,
  `adresse` varchar(50) DEFAULT NULL,
  `type_cuisine` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id_resto`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `restaurant`
--

INSERT INTO `restaurant` (`id_resto`, `nom`, `adresse`, `type_cuisine`) VALUES
(1, 'Restaurant Chinois', '1 rue des saules', 'Chinois'),
(2, 'Restaurant Italien', '1 rue des Saules', 'Italien'),
(3, 'Restaurant Gastro', '1 Rue des Saules', 'Gastronomique');

-- --------------------------------------------------------

--
-- Structure de la table `type_recetterestaurant`
--

DROP TABLE IF EXISTS `type_recetterestaurant`;
CREATE TABLE IF NOT EXISTS `type_recetterestaurant` (
  `id_type` int(11) NOT NULL,
  `libelle` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id_type`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `type_recetterestaurant`
--

INSERT INTO `type_recetterestaurant` (`id_type`, `libelle`) VALUES
(1, 'Entrée'),
(2, 'Plat'),
(3, 'Dessert');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
