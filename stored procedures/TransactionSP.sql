DELIMITER $$
    CREATE PROCEDURE if not exists sel_transaction_by_student(IN studentId INT)
        BEGIN
			SELECT * FROM transaction where transaction.studentId = studentId ORDER BY transaction.transactionDate DESC;
        END$$
DELIMITER ;

DELIMITER $$
    CREATE PROCEDURE if not exists add_transaction
    (
		IN studentId INT,
        IN amount INT,
        IN transactionType varchar(20),
        IN transactionDate DATETIME,
        IN description varchar(255),
        IN transactionSource varchar(20)
    )
        BEGIN
			INSERT INTO transaction(studentId, amount, transactionType, transactionDate, description, transactionSource)
            VALUES (studentId, amount, transactionType, transactionDate, description, transactionSource);

            IF transactionType='DEBIT'
			THEN
				UPDATE student SET balance = balance - amount where student.id = studentId;
			ELSE
				UPDATE student SET balance = balance + amount where student.id = studentId;
			END IF;
        END$$
DELIMITER ;