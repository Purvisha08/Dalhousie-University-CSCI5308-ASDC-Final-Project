DELIMITER $$
    CREATE PROCEDURE if not exists usp_studentinterest_select(IN studentId INT)
        BEGIN
			SELECT * FROM tblStudentInterest where tblStudentInterest.studentId = studentId ORDER BY tblStudentInterest.studentInterestId DESC;
        END$$
DELIMITER ;

DELIMITER $$
    CREATE PROCEDURE if not exists usp_studentinterest_delete(IN studentInterestId INT)
        BEGIN
			DELETE FROM tblStudentInterest where tblStudentInterest.studentInterestId = studentInterestId;
        END$$
DELIMITER ;

DELIMITER $$
    CREATE PROCEDURE if not exists usp_studentinterest_insert(IN studentId INT, IN categoryId VARCHAR(45))
        BEGIN
			IF NOT EXISTS(SELECT * FROM tblStudentInterest where tblStudentInterest.studentId = studentId AND tblStudentInterest.categoryId = categoryId)
            THEN
				INSERT INTO tblStudentInterest(studentId,categoryId) VALUES(studentId,categoryId);
		    END IF;
        END$$
DELIMITER ;
