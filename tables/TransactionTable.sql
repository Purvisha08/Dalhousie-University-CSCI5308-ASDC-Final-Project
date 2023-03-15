CREATE TABLE `transaction` (
  `transactionId` INT NOT NULL AUTO_INCREMENT,
  `studentId` INT NOT NULL,
  `amount` DOUBLE NOT NULL,
  `transactionType` VARCHAR(20) NOT NULL,
  `transactionDate` DATETIME NOT NULL,
  `description` VARCHAR(255) NOT NULL,
  `transactionSource` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`transactionId`));