CREATE TABLE `evenement` (
  `evenement_id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(200) NOT NULL,
  `adresse` varchar(200) NOT NULL,
  `horaires` varchar(200) NOT NULL,
  `description` varchar(200) NOT NULL,
  `url_image` varchar(200) NOT NULL,

  PRIMARY KEY (`evenement_id`)
)