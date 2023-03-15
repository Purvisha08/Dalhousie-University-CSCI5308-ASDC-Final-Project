package com.eventmanagement.EventManagement.DataAccessObjects.interfaces;

import com.eventmanagement.EventManagement.model.QuestionAnswer;
import com.eventmanagement.EventManagement.model.QuestionHost;

import java.util.List;
import java.util.Optional;

public interface IQuestionAnswerDAO {

    public boolean addQuestion(QuestionAnswer questionAnswer);

    public Optional<QuestionAnswer> findById(Integer id);

    public List<QuestionHost> getQuestionsByHostId(Integer hostId);

    public List<QuestionAnswer> getQuestionAnswerByEventId(int eventId);

    public boolean answerQuestion(QuestionAnswer questionAnswer);

}
