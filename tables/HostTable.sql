CREATE TABLE host (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `university` VARCHAR(255),
  `created_at` DATETIME default now(),
  `updated_at` DATETIME default now(),
  PRIMARY KEY (`id`));
