package com.eventmanagement.EventManagement.rowmapper;

import com.eventmanagement.EventManagement.model.QuestionHost;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QuestionHostRowMapper implements RowMapper<QuestionHost> {

    @Override
    public QuestionHost mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        QuestionHost questionHost = new QuestionHost();
        questionHost.setStudentId(resultSet.getInt("tblQuestionAnswer.studentId"));
        questionHost.setQuestionId(resultSet.getInt("tblQuestionAnswer.questionId"));
        questionHost.setEventName(resultSet.getString("event.name"));
        questionHost.setEventId(resultSet.getInt("event.id"));
        questionHost.setFestivalName(resultSet.getString("festival.name"));
        questionHost.setQuestionText(resultSet.getString("tblQuestionAnswer.questionText"));

        return questionHost;
    }



}
