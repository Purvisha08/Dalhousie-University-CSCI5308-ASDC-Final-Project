package com.eventmanagement.EventManagement.model.interfaces;

import com.eventmanagement.EventManagement.DataAccessObjects.interfaces.IQuestionAnswerDAO;

public interface IQuestionAnswerFactory {
    public IQuestionAnswer makeQuestionAnswer();
    public IQuestionAnswer makeQuestionAnswer(IQuestionAnswerDAO questionAnswerDAO);

}
