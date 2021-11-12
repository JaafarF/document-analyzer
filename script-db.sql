CREATE TABLE `category_base` (
  `id` varchar(255) NOT NULL,
  `label` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `document` (
  `id` bigint(20) NOT NULL,
  `content` varchar(10000) DEFAULT NULL,
  `language` int(11) DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  `size` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `version` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `category` (
  `id` bigint(20) NOT NULL,
  `frequency` float DEFAULT NULL,
  `namespace` varchar(255) DEFAULT NULL,
  `score` int(11) DEFAULT NULL,
  `winner` bit(1) DEFAULT NULL,
  `category_base_id` varchar(255) NOT NULL,
  `document_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK1yy97fc0xp3aplvt9jhyyodfn` FOREIGN KEY (`category_base_id`) REFERENCES `category_base` (`id`),
  CONSTRAINT `FK73sh2kh7w6m842xu8gd0ch6ba` FOREIGN KEY (`document_id`) REFERENCES `document` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `position` (
  `id` bigint(20) NOT NULL,
  `end` bigint(20) DEFAULT NULL,
  `start` bigint(20) DEFAULT NULL,
  `category_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FKk296qji0pokn8bgk8cw0d6lmx` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `hierarchy` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `category_base_hierarchy` (
  `hierarchy_id` bigint(20) NOT NULL,
  `category_base_id` varchar(255) NOT NULL,
  CONSTRAINT `FK6mj6m4kgk4ug01q500fnyr6yb` FOREIGN KEY (`hierarchy_id`) REFERENCES `hierarchy` (`id`),
  CONSTRAINT `FKa2vnjxlk7q6yehjxqucgj22fx` FOREIGN KEY (`category_base_id`) REFERENCES `category_base` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
