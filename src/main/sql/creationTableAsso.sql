CREATE TABLE `association` (
  `association_id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(200) NOT NULL,
  `description` varchar(200) NOT NULL,
  `nomPresident` varchar(200) NOT NULL,
  `nomFB` varchar(200) NOT NULL,
  `mail` varchar(200) NOT NULL,
  `pole` varchar(200) NOT NULL,
  `url_image` varchar(200) NOT NULL,

  PRIMARY KEY (`association_id`)
)

