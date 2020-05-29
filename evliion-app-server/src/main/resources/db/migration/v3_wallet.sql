CREATE TABLE `wallet` (
  `user_token` varchar(64) NOT NULL,
  `card_token` varchar(64) NOT NULL,
  `user_id` bigint(20) NOT NULL UNIQUE,
  PRIMARY KEY (`user_id`),
  FOREIGN KEY (`user_id`) REFERENCES `users`.`id`
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;