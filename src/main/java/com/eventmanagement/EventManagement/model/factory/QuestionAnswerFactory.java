package com.eventmanagement.EventManagement.model.factory;

import com.eventmanagement.EventManagement.DataAccessObjects.interfaces.IQuestionAnswerDAO;
import com.eventmanagement.EventManagement.model.QuestionAnswer;
import com.eventmanagement.EventManagement.model.interfaces.IQuestionAnswer;
import com.eventmanagement.EventManagement.model.interfaces.IQuestionAnswerFactory;

public class QuestionAnswerFactory implements IQuestionAnswerFactory {
    private static QuestionAnswerFactory singleInstance = null;

    public static QuestionAnswerFactory getInstance(){
        if (singleInstance == null){
            singleInstance = new QuestionAnswerFactory();
        }
        return singleInstance;
    }
    @Override
    public QuestionAnswer makeQuestionAnswer() {
        return new QuestionAnswer();
    }

    @Override
    public IQuestionAnswer makeQuestionAnswer(IQuestionAnswerDAO questionAnswerDAO) {
        return new QuestionAnswer(questionAnswerDAO);
    }
}
