package com.eventmanagement.EventManagement.DataAccessObjects;

import com.eventmanagement.EventManagement.DataAccessObjects.interfaces.IQuestionAnswerDAO;
import com.eventmanagement.EventManagement.model.DatabaseDataSource;
import com.eventmanagement.EventManagement.model.QuestionAnswer;
import com.eventmanagement.EventManagement.model.QuestionHost;
import com.eventmanagement.EventManagement.rowmapper.QuestionAnswerRowMapper;
import com.eventmanagement.EventManagement.rowmapper.QuestionHostRowMapper;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Repository
public class QuestionAnswerDAO implements IQuestionAnswerDAO {

    public JdbcTemplate jdbcTemplate;

    public QuestionAnswerDAO()
    {
        jdbcTemplate = new JdbcTemplate(DatabaseDataSource.instance().getDriverManagerDataSource());
    }

    public Optional<QuestionAnswer> findById(Integer id) {
        String query = "CALL usp_question_answer_findById(?)";
        return jdbcTemplate.query(query, new QuestionAnswerRowMapper(), id)
                .stream()
                .findFirst();
    }

    public boolean addQuestion(QuestionAnswer questionAnswer){
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String sqlCallSP = "CALL add_question(?, ?, ?, ?, ?)";

        jdbcTemplate.update(
                sqlCallSP,
                questionAnswer.getStudentId(),
                questionAnswer.getEventId(),
                questionAnswer.getQuestionText(),
                dateTimeFormatter.format(localDate),
                questionAnswer.getLevelOfUrgency()
        );

        return true;
    }

    public List<QuestionHost> getQuestionsByHostId(Integer hostId){
        String query = "CALL usp_select_questions_by_host_id(?)";
        List<QuestionHost> questionsForHost = jdbcTemplate.query(query,
                new QuestionHostRowMapper(),
                hostId);
        return questionsForHost;
    }

    public List<QuestionAnswer> getQuestionAnswerByEventId(int eventId){
        String query = "CALL usp_select_questions_by_event_id(?)";
        List<QuestionAnswer> questionAnswers = jdbcTemplate.query(
                query,
                new QuestionAnswerRowMapper(),
                eventId);
        return questionAnswers;
    }

    public boolean answerQuestion(QuestionAnswer questionAnswer){
        String query = "CALL add_answer(?, ?)";
        jdbcTemplate.update(query, questionAnswer.getQuestionId(),
                questionAnswer.getAnswerText());

        return true;
    }

}
