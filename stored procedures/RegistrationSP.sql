DELIMITER $$
    CREATE PROCEDURE if not exists usp_registration_add
    (
		IN eventId INT,
        IN studentId INT,
        IN registrationDate DATE,
        IN description varchar(255),
        IN amount DOUBLE
    )
        BEGIN

            DECLARE transactionId INT DEFAULT -1;

			INSERT INTO transaction(studentId, amount, transactionType, transactionDate, description, transactionSource)
            VALUES (studentId, amount, "Debit", NOW(), description, "Wallet");

            SELECT LAST_INSERT_ID() INTO transactionId;

			UPDATE student SET balance = balance - amount where student.id = studentId;

			INSERT INTO tblRegistration(eventId, studentId, registrationDate, transactionId, status)
            VALUES (eventId, studentId, registrationDate, transactionId, "REGISTERED");

            UPDATE event SET registerCount = (registerCount + 1) WHERE event.id = eventId;

        END$$
DELIMITER ;

DELIMITER $$
    CREATE PROCEDURE if not exists usp_registration_select_by_student(IN studentId INT)
        BEGIN
			SELECT tblRegistration.registrationId,tblRegistration.studentId, tblRegistration.registrationDate, tblRegistration.status, tblRegistration.eventId,
				   event.name as "eventName", transaction.amount, student.name as "studentName", event.startDate as "eventDate"
		    FROM ((tblRegistration
            INNER JOIN event ON  tblRegistration.eventId = event.id AND  tblRegistration.studentId = studentId)
            INNER JOIN transaction ON  tblRegistration.transactionId = transaction.transactionId
            INNER JOIN student ON tblRegistration.studentId = student.id) ORDER BY tblRegistration.registrationId DESC;
        END$$
DELIMITER ;

DELIMITER $$
    CREATE PROCEDURE if not exists usp_registration_findByEventId (IN registrationEventId int)
        BEGIN
            SELECT
                tblRegistration.*,
                event.name as "eventName",
                transaction.amount,
                student.name as "studentName",
                event.startDate as "eventDate"
            FROM
                (
                    (tblRegistration INNER JOIN event ON  tblRegistration.eventId = event.id AND  tblRegistration.registrationId = registrationId)
                    INNER JOIN transaction ON  tblRegistration.transactionId = transaction.transactionId
                    INNER JOIN student ON tblRegistration.studentId = student.id)
            where
                tblRegistration.eventId = registrationEventId
            ORDER BY tblRegistration.registrationId DESC;
        END$$
DELIMITER ;

DELIMITER $$
    CREATE PROCEDURE if not exists usp_registration_findByStudentAndEventId (IN registrationEventId int, IN registrationStudentId int)
        BEGIN
            SELECT
                tblRegistration.*,
                event.name as "eventName",
                transaction.amount,
                student.name as "studentName",
                event.startDate as "eventDate"
            FROM
                (
                    (tblRegistration INNER JOIN event ON  tblRegistration.eventId = event.id AND  tblRegistration.registrationId = registrationId)
                    INNER JOIN transaction ON  tblRegistration.transactionId = transaction.transactionId
                    INNER JOIN student ON tblRegistration.studentId = student.id)
            where
                tblRegistration.studentId = registrationStudentId and
                tblRegistration.eventId = registrationEventId
            ORDER BY tblRegistration.registrationId DESC;
        END$$
DELIMITER ;


DELIMITER $$
    CREATE PROCEDURE if not exists usp_registration_update (IN updateRegistrationId int, IN registrationStatus varchar(255), IN registrationRating int)
        BEGIN
            update
                tblRegistration
            SET
                status = registrationStatus,
                rating = registrationRating
            where
                registrationId = updateRegistrationId;
        END$$
DELIMITER ;

DELIMITER ;
    CREATE PROCEDURE if not exists usp_registration_select_by_registrationId(IN registrationId INT)
        BEGIN
			SELECT tblRegistration.registrationId, tblRegistration.studentId, tblRegistration.registrationDate, tblRegistration.status, tblRegistration.eventId,
				   event.name as "eventName", transaction.amount, student.name as "studentName", event.startDate as "eventDate"
		    FROM ((tblRegistration
            INNER JOIN event ON  tblRegistration.eventId = event.id AND  tblRegistration.registrationId = registrationId)
            INNER JOIN transaction ON  tblRegistration.transactionId = transaction.transactionId
            INNER JOIN student ON tblRegistration.studentId = student.id) ORDER BY tblRegistration.registrationId DESC;
        END$$
DELIMITER ;

DELIMITER $$
    CREATE PROCEDURE if not exists usp_registration_select_by_student_event_id(IN studentId INT, IN eventId INT)
        BEGIN
			SELECT tblRegistration.registrationId, tblRegistration.studentId, tblRegistration.registrationDate, tblRegistration.status, tblRegistration.eventId,
				   event.name as "eventName", transaction.amount, student.name as "studentName", event.startDate as "eventDate"
		    FROM ((tblRegistration
            INNER JOIN event ON  tblRegistration.eventId = event.id AND  tblRegistration.studentId = studentId AND tblRegistration.eventId = eventId AND tblRegistration.status = "REGISTERED")
            INNER JOIN transaction ON  tblRegistration.transactionId = transaction.transactionId
            INNER JOIN student ON tblRegistration.studentId = student.id) ORDER BY tblRegistration.registrationId DESC;
        END$$
DELIMITER ;
