DELIMITER $$
CREATE PROCEDURE usp_waitlist_add
    (
		IN eventId INT,
        IN studentId INT
    )
BEGIN
			IF NOT EXISTS (SELECT * from tblWaitlist WHERE tblWaitlist.studentId = studentId AND tblWaitlist.eventId = eventId)
            THEN
                        INSERT INTO tblWaitlist(eventId, studentId, createdAt)
                        VALUES (eventId, studentId, NOW());

            			UPDATE event SET waitlistCount = waitlistCount + 1 WHERE event.id = eventId;
            END IF;
END$$
DELIMITER ;

DELIMITER $$
    CREATE PROCEDURE if not exists usp_waitlist_select_by_event(IN eventId INT)
        BEGIN
			SELECT tblWaitlist.eventId,tblWaitlist.studentId, tblWaitlist.createdAt, student.name as "studentName",
				   event.name as "eventName", event.startDate as "eventStartDate", event.capacity as "eventCapacity", event.registerCount as "eventRegisterCount",
                   student.name as "studentName"
		    FROM ((tblWaitlist
            INNER JOIN event ON  tblWaitlist.eventId = event.id AND  tblWaitlist.eventId = eventId)
            INNER JOIN student ON student.id = studentId) ORDER BY tblWaitlist.createdAt DESC;
        END$$
DELIMITER ;

DELIMITER $$
    CREATE PROCEDURE if not exists usp_waitlist_select_by_student(IN studentId INT)
        BEGIN
			SELECT tblWaitlist.eventId,tblWaitlist.studentId, tblWaitlist.createdAt, student.name as "studentName",
				   event.name as "eventName", event.startDate as "eventStartDate", event.capacity as "eventCapacity", event.registerCount as "eventRegisterCount"
		    FROM ((tblWaitlist
            INNER JOIN event ON tblWaitlist.studentId = studentId AND tblWaitlist.eventId = event.id )
            INNER JOIN student ON student.id = studentId) ORDER BY tblWaitlist.createdAt DESC;
        END$$
DELIMITER ;

DELIMITER $$
    CREATE PROCEDURE if not exists usp_waitlist_delete(IN eventId INT, IN studentId INT)
        BEGIN
			DELETE FROM tblWaitlist WHERE tblWaitlist.eventId = eventId AND tblWaitlist.studentId = studentId;
        END$$
DELIMITER ;