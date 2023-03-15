DELIMITER $$
    CREATE PROCEDURE if not exists usp_select_questions_by_event_id (IN event_id int)
        BEGIN
            SELECT *
            FROM tblQuestionAnswer
            WHERE eventId = event_id;
        END$$
DELIMITER ;


DELIMITER $$
    CREATE PROCEDURE if not exists usp_question_answer_findById (IN qaId int)
        BEGIN
            SELECT *
            FROM tblQuestionAnswer
            WHERE questionId = qaId;
        END$$
DELIMITER ;
