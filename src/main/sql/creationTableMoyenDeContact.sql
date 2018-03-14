CREATE TABLE `moyen_de_contact` (
  `moyen_de_contact_id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(200) NOT NULL,
  `precision` varchar(200) NOT NULL,
  `description` varchar(200) NOT NULL,
  `url_image` varchar(200) NOT NULL,

  PRIMARY KEY (`moyen_de_contact_id`)
);