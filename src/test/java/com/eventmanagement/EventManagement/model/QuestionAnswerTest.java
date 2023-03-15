package com.eventmanagement.EventManagement.model;

import com.eventmanagement.EventManagement.DataAccessObjects.QuestionAnswerDAOMock;
import com.eventmanagement.EventManagement.DataAccessObjects.interfaces.IQuestionAnswerDAO;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class QuestionAnswerTest {

    @Test
    public void getQuestionsByHostIdTest(){
        QuestionAnswer questionAnswer = new QuestionAnswer();
        try{
            IQuestionAnswerDAO questionAnswerDAOMock = new QuestionAnswerDAOMock();
            List<QuestionHost> questionHostList = questionAnswer.getQuestionsByHostId(questionAnswerDAOMock, 0);
            Assertions.assertNotNull(questionHostList);
            Assertions.assertEquals(questionHostList.size(), 1);
            QuestionHost questionHost = questionHostList.get(0);
            Assertions.assertEquals(questionHost.getQuestionId(), 1);
            Assertions.assertEquals(questionHost.getQuestionText(), "Sample Question");
            Assertions.assertEquals(questionHost.getEventName(), "Sample Event");
            Assertions.assertEquals(questionHost.getFestivalName(), "Sample Festival");

        }
        catch (Exception exception){
            Assertions.fail("Test:\t getQuestionsByHostIdTest() failed due to:\n" + exception.getMessage());
        }
    }

    @Test
    public void addQuestionTest(){
        QuestionAnswer questionAnswer = new QuestionAnswer();
        try{
            IQuestionAnswerDAO questionAnswerDAO = new QuestionAnswerDAOMock();
            QuestionAnswer currentQuestion = new QuestionAnswer();
            currentQuestion.setQuestionText("Sample Question");
            boolean questionAnswerResult = questionAnswer.askQuestion(questionAnswerDAO, currentQuestion);
            Assertions.assertEquals(questionAnswerResult, true);
        }
        catch (Exception exception){
            Assertions.fail("Test:\t addQuestionTest() failed due to:\n" + exception.getMessage());

        }
    }

    @Test
    public void answerQuestionTest(){
        QuestionAnswer questionAnswer = new QuestionAnswer();
        try {
            IQuestionAnswerDAO questionAnswerDAO = new QuestionAnswerDAOMock();
            QuestionAnswer currentQuestion = new QuestionAnswer();
            currentQuestion.setAnswerText("Sample Answer");
            boolean questionAnswerResult = questionAnswer.answerQuestion(questionAnswerDAO, currentQuestion);
            Assertions.assertEquals(questionAnswerResult, true);
        }
        catch (Exception exception){
            Assertions.fail("Test:\t answerQuestionTest() failed due to:\n" + exception.getMessage());
        }

    }

    @Test
    public void getQuestionAnswerByEventIdTest(){
        try{
            IQuestionAnswerDAO questionAnswerDAO = new QuestionAnswerDAOMock();
            List<QuestionAnswer> questionAnswerList = QuestionAnswer.getQuestionAnswerByEventId(questionAnswerDAO, 1);
            Assertions.assertNotNull(questionAnswerList);
            Assertions.assertEquals(questionAnswerList.size(), 1);
            QuestionAnswer currentQuestion = questionAnswerList.get(0);
            Assertions.assertEquals(currentQuestion.getQuestionId(), 1);
            Assertions.assertEquals(currentQuestion.getStudentId(), 1);
            Assertions.assertEquals(currentQuestion.getEventId(), 1);
            Assertions.assertEquals(currentQuestion.getQuestionText(), "Sample Question");
            Assertions.assertEquals(currentQuestion.getAnswerText(), "Sample Answer");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
            Date date = dateFormat.parse("2022-02-28");
            Assertions.assertEquals(currentQuestion.getCreatedDate(), date);

        }
        catch (Exception exception){
            Assertions.fail("Test:\t getQuestionAnswerByEventIdTest() failed due to:\n" + exception.getMessage());
        }
    }



}
