package com.eventmanagement.EventManagement.rowmapper;

import com.eventmanagement.EventManagement.model.QuestionAnswer;
import com.eventmanagement.EventManagement.model.factory.QuestionAnswerFactory;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QuestionAnswerRowMapper implements RowMapper<QuestionAnswer> {

    @Override
    public QuestionAnswer mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        QuestionAnswer questionAnswer = QuestionAnswerFactory.getInstance().makeQuestionAnswer();
        questionAnswer.setQuestionId(resultSet.getInt("questionId"));
        questionAnswer.setStudentId(resultSet.getInt("studentId"));
        questionAnswer.setEventId(resultSet.getInt("eventId"));
        questionAnswer.setQuestionText(resultSet.getString("questionText"));
        questionAnswer.setAnswerText(resultSet.getString("answerText"));
        questionAnswer.setCreatedDate(resultSet.getDate("createdDate"));

        return questionAnswer;
    }
}
