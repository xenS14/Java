Une fois  le projet cloné, compiler l'app grâce à : 

- javac -d out -cp ".:lib/mysql-connector-j-9.2.0.jar" $(find src -name "*.java")

Puis lancer le projet grâce à :

- cd out/
- java -cp "../lib/mysql-connector-j-9.2.0.jar:." main.App

Ensuite vous aurez le choix entre plusieurs options : 

- 1 Ajouter un apprenant
- 2 Afficher tous les apprenants
- 3 Modifier les absences d'un apprenant
- 4 Supprimer un apprenant
- 5 Voir les apprenants d'une promotion
- 6 Rechercher un apprenant par email
- 7 Voir les statistiques générales
- 8 Voir l'apprenant avec le plus d'absences
- 9 Quitter

Voici un Export de la base de données avec quelques données enregistrées :

=============================================================================

-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : ven. 14 fév. 2025 à 14:28
-- Version du serveur : 8.2.0
-- Version de PHP : 8.2.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `java`
--

-- --------------------------------------------------------

--
-- Structure de la table `apprenant`
--

DROP TABLE IF EXISTS `apprenant`;
CREATE TABLE IF NOT EXISTS `apprenant` (
  `id` int NOT NULL AUTO_INCREMENT,
  `promotion` varchar(100) DEFAULT NULL,
  `nom` varchar(50) DEFAULT NULL,
  `prenom` varchar(50) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `telephone` varchar(20) DEFAULT NULL,
  `nombre_absences` int DEFAULT '0',
  `delegue` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `unique_nom_prenom` (`nom`,`prenom`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `apprenant`
--

INSERT INTO `apprenant` (`id`, `promotion`, `nom`, `prenom`, `email`, `telephone`, `nombre_absences`, `delegue`) VALUES
(3, 'DI23', 'Vincent', 'C', 'vincent@viacesi.fr', '0693758274', 2, 0),
(2, 'DI23', 'No�', 'Brognard', 'noe.brognard@viacesi.fr', '0695265983', 2, 0),
(4, 'DI23', 'Mathis', 'V', 'mathis.v@viacesi.fr', '1234567890', 5, 0),
(5, 'DI23', 'Vallois', 'Ewenn', 'ewenn.v@viacesi.fr', '1234567890', 7, 1);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

=============================================================================
