CREATE TABLE `tblStudentInterest` (
  `studentInterestId` INT NOT NULL AUTO_INCREMENT,
  `studentId` INT NOT NULL,
  `categoryId` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`studentInterestId`));

