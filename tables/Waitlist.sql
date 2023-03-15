CREATE TABLE tblWaitlist (
    eventId INT,
    studentId INT,
    createdAt DATE,
    PRIMARY KEY (eventId, studentId)
)