DELIMITER $$
    CREATE PROCEDURE if not exists usp_student_findByEmail(IN studentEmail varchar(255))
        BEGIN
            SELECT
                *
            FROM
                student
            where
                email = studentEmail;
        END$$
DELIMITER ;

DELIMITER $$
    CREATE PROCEDURE if not exists usp_student_findById(IN studentId int)
        BEGIN
            SELECT
                *
            FROM
                student
            where
                id = studentId;
        END$$
DELIMITER ;

DELIMITER $$
    CREATE PROCEDURE if not exists usp_student_findAllByIds (IN  studentIds VARCHAR(255))
        BEGIN
            set @query = concat('Select * from student where id in (',studentIds,')');
            prepare myquery from @query;
            execute myquery;
        END$$
DELIMITER ;


DELIMITER $$
    CREATE PROCEDURE if not exists usp_student_insert (
        IN  studentName VARCHAR(25),
        IN  studentEmail VARCHAR(25),
        IN  studentPassword VARCHAR(25),
        IN studentCreatedAt DATETIME,
        IN studentUpdatedAt DATETIME
    )
        BEGIN
            INSERT INTO student (name, email, password, created_at, updated_at)
            VALUES (studentName, studentEmail, studentPassword, studentCreatedAt, studentUpdatedAt);
        END$$
DELIMITER ;

DELIMITER $$
    CREATE PROCEDURE `authenticate_student`(
        IN  email VARCHAR(255),
        IN  pwd VARCHAR(255),
        OUT res BOOLEAN)
		BEGIN
            IF EXISTS(Select * from student where email=student.email and pwd=student.password)
            THEN
				SET res = TRUE;
			ELSE
				SET res = FALSE;
			END IF;
		END$$
DELIMITER ;


DELIMITER $$
    CREATE PROCEDURE `checkIfStudentExistsByEmail`(
        IN  email VARCHAR(255),
        OUT res BOOLEAN)
		BEGIN
            IF EXISTS(Select * from student where email=student.email)
            THEN
				SET res = TRUE;
			ELSE
				SET res = FALSE;
			END IF;
		END$$
DELIMITER ;

DELIMITER $$
    CREATE PROCEDURE `checkIfHostExistsByEmail`(
        IN  email VARCHAR(255),
        OUT res BOOLEAN)
		BEGIN
            IF EXISTS(Select * from host where email=host.email)
            THEN
				SET res = TRUE;
			ELSE
				SET res = FALSE;
			END IF;
		END$$
DELIMITER ;

DELIMITER $$
    CREATE PROCEDURE if not exists sel_wallet_balance
    (
        IN  studentId INT,
        OUT balance DOUBLE
	)
		BEGIN
            IF EXISTS(Select * from student where student.id = studentId)
            THEN
				SET balance = (SELECT MAX(student.balance) from student where student.id = studentId);
			ELSE
				SET balance = 0;
			END IF;
		END$$
DELIMITER ;

DELIMITER $$
    CREATE PROCEDURE if not exists sel_student_by_email(IN studentEmail varchar(255))
        BEGIN
          SELECT * FROM student where email = studentEmail;
        END$$
DELIMITER ;
