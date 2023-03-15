DELIMITER $$
    CREATE PROCEDURE if not exists usp_event_findById (IN eventId int)
        BEGIN
            SELECT
                *
            FROM
                event
            where
                id = eventId;
        END$$
DELIMITER ;

DELIMITER $$
    CREATE PROCEDURE if not exists usp_event_findByFestivalId (IN festivalId int)
        BEGIN
            SELECT
                *
            FROM
                event
            where
                festival_id = festivalId;
        END$$
DELIMITER ;


DELIMITER $$
    CREATE PROCEDURE if not exists usp_event_insert (
        In  festivalId int,
        IN  eventName VARCHAR(255),
        IN  eventDescription text,
        IN  eventVenue VARCHAR(255),
        IN  eventCategory VARCHAR(255),
        IN  eventStartDate DATETIME,
        IN  eventEndDate DATETIME,
        IN  eventPrice int,
        IN  eventCapacity int
    )
        BEGIN
            INSERT INTO event (festival_id, name, description, venue, category, startDate, endDate, price, capacity)
            VALUES (festivalId, eventName, eventDescription, eventVenue, eventCategory, eventStartDate, eventEndDate, eventPrice, eventCapacity);
        END$$
DELIMITER ;

DELIMITER $$
    CREATE PROCEDURE if not exists usp_event_update (
        IN  eventId int,
        IN  eventName VARCHAR(255),
        IN  eventDescription text,
        IN  eventVenue VARCHAR(255),
        IN  eventCategory VARCHAR(255),
        IN  eventStartDate DATETIME,
        IN  eventEndDate DATETIME,
        IN  eventPrice int,
        IN  eventCapacity int,
        IN  eventCompleted Boolean,
        IN  eventWinner INT,
        IN  eventFirstRunnerUp INT,
        IN  eventSecondRunnerUp INT
    )
        BEGIN
            UPDATE event set name = eventName,
                            description = eventDescription,
                            venue = eventVenue,
                            category = eventCategory,
                            startDate = eventStartDate,
                            endDate = eventEndDate,
                            price = eventPrice,
                            capacity = eventCapacity,
                            completed = eventCompleted,
                            winner = eventWinner,
                            firstRunnerUp = eventFirstRunnerUp,
                            secondRunnerUp = eventSecondRunnerUp
            WHERE id = eventId;
        END$$
DELIMITER ;

DELIMITER $$
    CREATE PROCEDURE if not exists usp_event_updateRating (
        IN  eventId int,
        IN  eventRating float
    )
        BEGIN
            UPDATE event set rating = eventRating
            WHERE id = eventId;
        END$$
DELIMITER ;



DELIMITER $$
    CREATE PROCEDURE if not exists usp_event_delete (IN eventId int)
        BEGIN
            DELETE FROM event WHERE id = eventId;
        END$$
DELIMITER ;
