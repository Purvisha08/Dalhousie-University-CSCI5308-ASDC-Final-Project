CREATE TABLE `tblRegistration` (
  `registrationId` INT NOT NULL AUTO_INCREMENT,
  `eventId` INT NOT NULL,
  `studentId` INT NOT NULL,
  `registrationDate` DATE NOT NULL,
  `transactionId` INT NOT NULL,
  `status` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`registrationId`));