DELIMITER $$
    CREATE PROCEDURE if not exists usp_festival_findById (IN festivalId int)
        BEGIN
            select *,
	        (select count(*) from event e where e.festival_id = f.festival_id )as event_count,
	        (select count(*) from tblRegistration tr where tr.eventId in (select id from event e where e.festival_id = f.festival_id) and tr.status = 'REGISTERED') as total_participants
	        from festival f
            where
                f.festival_id = festivalId;
        END$$
DELIMITER ;

DELIMITER $$
    CREATE PROCEDURE if not exists usp_festival_findByHostId (IN hostId int)
        BEGIN
            select *,
            (select count(*) from event e where e.festival_id = f.festival_id )as event_count,
            (select count(*) from tblRegistration tr where tr.eventId in (select id from event e where e.festival_id = f.festival_id) and tr.status = 'REGISTERED') as total_participants
            from festival f
            where
                f.host_id = hostId;
        END$$
DELIMITER ;

DELIMITER $$
    CREATE PROCEDURE if not exists usp_festival_filterBySearchQuery (IN hostId int, IN searchQuery varchar(255))
        BEGIN
            select *,
            (select count(*) from event e where e.festival_id = f.festival_id )as event_count,
            (select count(*) from tblRegistration tr where tr.eventId in (select id from event e where e.festival_id = f.festival_id) and tr.status = 'REGISTERED') as total_participants
            from festival f
            where f.host_id = hostId
                and (f.name like searchQuery or f.description like searchQuery or f.university like searchQuery);
        END$$
DELIMITER ;

DELIMITER $$
    CREATE PROCEDURE if not exists usp_festival_updateRating (
        IN  festivalId int,
        IN  festivalRating float
    )
        BEGIN
            UPDATE festival set rating = festivalRating
            WHERE festival_id = festivalId;
        END$$
DELIMITER ;


DELIMITER $$
    CREATE PROCEDURE if not exists usp_festival_findAllEvents (
        IN  festivalId int
    )
        BEGIN
            SELECT * from event where festival_id = festivalID;
        END$$
DELIMITER ;
