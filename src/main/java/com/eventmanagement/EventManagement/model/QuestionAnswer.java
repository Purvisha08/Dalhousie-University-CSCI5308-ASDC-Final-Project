package com.eventmanagement.EventManagement.model;

import com.eventmanagement.EventManagement.DataAccessObjects.interfaces.IQuestionAnswerDAO;
import com.eventmanagement.EventManagement.model.factory.NotificationFactory;
import com.eventmanagement.EventManagement.model.interfaces.INotification;
import com.eventmanagement.EventManagement.model.interfaces.IQuestionAnswer;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class QuestionAnswer implements IQuestionAnswer {

    private Integer questionId;
    private Integer studentId;
    private Integer eventId;
    private String questionText;
    private String answerText;
    private Integer levelOfUrgency;
    private IQuestionAnswerDAO questionAnswerDAO;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createdDate;

    public QuestionAnswer(){

    }

    public QuestionAnswer(IQuestionAnswerDAO questionAnswerDAO){
        this.questionAnswerDAO = questionAnswerDAO;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getLevelOfUrgency() {
        return levelOfUrgency;
    }

    public void setLevelOfUrgency(Integer levelOfUrgency) {
        this.levelOfUrgency = levelOfUrgency;
    }

    public boolean askQuestion(IQuestionAnswerDAO questionAnswerDAO,QuestionAnswer questionAnswer){
        questionAnswerDAO.addQuestion(questionAnswer);
        return true;
    }

    public static QuestionAnswer findByID(IQuestionAnswerDAO questionAnswerDAO, Integer id) {
        Optional<QuestionAnswer> questionAnswer = questionAnswerDAO.findById(id);
        if(questionAnswer.isPresent()) {
            return questionAnswer.get();
        } else {
            return null;
        }
    }

    public List<QuestionHost> getQuestionsByHostId(IQuestionAnswerDAO questionAnswerDAO, int hostId){
        List<QuestionHost> questionHostList = questionAnswerDAO.getQuestionsByHostId(hostId);
        return questionHostList;
    }

    public static List<QuestionAnswer> getQuestionAnswerByEventId(IQuestionAnswerDAO questionAnswerDAO,int eventId){
        List<QuestionAnswer> questionAnswers = questionAnswerDAO.getQuestionAnswerByEventId(eventId);
        return questionAnswers;
    }

    public boolean answerQuestion(IQuestionAnswerDAO questionAnswerDAO, QuestionAnswer questionAnswer){
        questionAnswerDAO.answerQuestion(questionAnswer);
        INotification notification = NotificationFactory.instance().makeNotification();
        notification.sendNotification(questionAnswer.getStudentId(), "Answer received", questionAnswer.getEventId());
        return true;
    }

}
