CREATE TABLE `membre` (
  `membre_id` int(11) NOT NULL AUTO_INCREMENT,
  `prenom` varchar(45) NOT NULL,
  `nom` varchar(45) NOT NULL,
  `poste` varchar(20) NOT NULL,
  `description` varchar(200) NOT NULL,
  `url_image` varchar(100) NOT NULL,

  PRIMARY KEY (`membre_id`)
);