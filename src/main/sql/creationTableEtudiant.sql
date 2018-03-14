CREATE TABLE `etudiant` (
  `etudiant_id` int(11) NOT NULL AUTO_INCREMENT,
  `prenom` varchar(200) NOT NULL,
  `nom` varchar(200) NOT NULL,
  `telephone` varchar(200) NOT NULL,

  PRIMARY KEY (`etudiant_id`)
);