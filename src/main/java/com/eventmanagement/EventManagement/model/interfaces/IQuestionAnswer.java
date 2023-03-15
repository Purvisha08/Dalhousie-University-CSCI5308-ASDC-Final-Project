package com.eventmanagement.EventManagement.model.interfaces;

import com.eventmanagement.EventManagement.DataAccessObjects.interfaces.IQuestionAnswerDAO;
import com.eventmanagement.EventManagement.model.QuestionAnswer;
import com.eventmanagement.EventManagement.model.QuestionHost;

import java.util.List;

public interface IQuestionAnswer {
    public boolean askQuestion(IQuestionAnswerDAO questionAnswerDAO, QuestionAnswer questionAnswer);
    public List<QuestionHost> getQuestionsByHostId(IQuestionAnswerDAO questionAnswerDAO, int hostId);
    public boolean answerQuestion(IQuestionAnswerDAO questionAnswerDAO, QuestionAnswer questionAnswer);
}
