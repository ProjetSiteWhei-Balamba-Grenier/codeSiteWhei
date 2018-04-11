CREATE TABLE `membre` (
  `membre_id` int(11) NOT NULL AUTO_INCREMENT,
  `prenom` varchar(200) NOT NULL,
  `nom` varchar(200) NOT NULL,
  `poste` varchar(200) NOT NULL,
  `description` varchar(200) NOT NULL,
  `url_image` varchar(200) NOT NULL,

  PRIMARY KEY (`membre_id`)
);