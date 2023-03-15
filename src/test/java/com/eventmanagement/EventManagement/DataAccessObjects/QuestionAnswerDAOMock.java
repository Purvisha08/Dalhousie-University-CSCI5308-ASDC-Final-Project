package com.eventmanagement.EventManagement.DataAccessObjects;

import com.eventmanagement.EventManagement.DataAccessObjects.interfaces.IQuestionAnswerDAO;
import com.eventmanagement.EventManagement.model.QuestionAnswer;
import com.eventmanagement.EventManagement.model.QuestionHost;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class QuestionAnswerDAOMock implements IQuestionAnswerDAO {

    @Override
    public Optional<QuestionAnswer> findById(Integer id) {
        if (id == 1){
            QuestionAnswer questionAnswer = new QuestionAnswer();
            questionAnswer.setQuestionId(1);
            questionAnswer.setQuestionText("Sample Question");
            questionAnswer.setAnswerText("Sample Answer");
            return Optional.of(questionAnswer);
        }
        return Optional.empty();
    }

    @Override
    public boolean addQuestion(QuestionAnswer questionAnswer) {
        if (questionAnswer.getQuestionText().equals("Sample Question")){
            return true;
        }
        return false;
    }

    @Override
    public List<QuestionHost> getQuestionsByHostId(Integer hostId) {
        List<QuestionHost> questionHostList = new ArrayList<QuestionHost>();
        QuestionHost questionHost = new QuestionHost();
        if (hostId == 0){
            questionHost.setQuestionId(1);
            questionHost.setQuestionText("Sample Question");
            questionHost.setEventName("Sample Event");
            questionHost.setFestivalName("Sample Festival");
        }
        questionHostList.add(questionHost);

        return questionHostList;
    }

    @Override
    public List<QuestionAnswer> getQuestionAnswerByEventId(int eventId) {
        List<QuestionAnswer> questionAnswerList = new ArrayList<QuestionAnswer>();
        QuestionAnswer questionAnswer = new QuestionAnswer();

        if (eventId == 1){
            questionAnswer.setStudentId(1);
            questionAnswer.setQuestionId(1);
            questionAnswer.setEventId(1);
            questionAnswer.setQuestionText("Sample Question");
            questionAnswer.setAnswerText("Sample Answer");
            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
            try {
                date = dateFormat.parse("2022-02-28");
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            questionAnswer.setCreatedDate(date);
        }
        questionAnswerList.add(questionAnswer);

        return questionAnswerList;
    }

    @Override
    public boolean answerQuestion(QuestionAnswer questionAnswer) {
        if (questionAnswer.getAnswerText().equals("Sample Answer")){
            return true;
        }
        return false;
    }
}
