
DELIMITER $$
    CREATE PROCEDURE if not exists usp_host_create (
        IN  hostName VARCHAR(255),
        IN  hostEmail VARCHAR(255),
        IN  hostPwd VARCHAR(255),
        IN  hostUniversity varchar(255),
        IN hostContactNumber INT,
        IN  hostCreatedAt DATETIME,
        IN  hostUpdatedAt DATETIME
    )
        BEGIN
            INSERT INTO host (name, email, password, university, created_at, updated_at, contactNumber)
            VALUES (hostName, hostEmail, hostPwd, hostUniversity, hostCreatedAt, hostUpdatedAt, hostContactNumber);
        END$$
DELIMITER ;

DELIMITER $$
    CREATE PROCEDURE if not exists usp_host_findByEmail (IN hostEmail varchar(255))
        BEGIN
            SELECT
                *
            FROM
                host
            where
                email = hostEmail;
        END$$
DELIMITER ;


DELIMITER $$
    CREATE PROCEDURE `authenticate_host`(
        IN  email VARCHAR(255),
        IN  pwd VARCHAR(255),
        OUT res BOOLEAN)
		BEGIN
            IF EXISTS(Select * from host where email=host.email and pwd=host.password)
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
    CREATE PROCEDURE if not exists usp_host_updateRating (
        IN  hostId int,
        IN  hostRating float
    )
        BEGIN
            UPDATE host set rating = hostRating
            WHERE id = hostId;
        END$$
DELIMITER ;

DELIMITER $$
    CREATE PROCEDURE if not exists usp_host_findById (
        IN  hostId int
    )
        BEGIN
            SELECT * FROM host where id = hostId;
        END$$
DELIMITER ;


DELIMITER $$
    CREATE PROCEDURE if not exists usp_host_findAllEvents (
        IN  hostId int
    )
        BEGIN
            select * from event where festival_id in (SELECT f.festival_id  from festival f where f.host_id = hostId);
        END$$
DELIMITER ;
