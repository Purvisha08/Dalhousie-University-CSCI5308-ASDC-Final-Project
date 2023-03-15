package com.eventmanagement.EventManagement.controller;

import com.eventmanagement.EventManagement.model.CurrentSession;
import com.eventmanagement.EventManagement.DataAccessObjects.interfaces.IQuestionAnswerDAO;
import com.eventmanagement.EventManagement.model.factory.MySQLDatabaseFactory;
import com.eventmanagement.EventManagement.model.QuestionAnswer;
import com.eventmanagement.EventManagement.model.QuestionHost;
import com.eventmanagement.EventManagement.model.factory.SessionFactory;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class QuestionController {

    private IQuestionAnswerDAO questionAnswerDAO;

    public QuestionController(){
        this.questionAnswerDAO = MySQLDatabaseFactory.instance().makeQuestionAnswerDAO();
    }


    @GetMapping("/questions")
    public String getQuestionList(Model model, HttpServletRequest httpServletRequest){
        CurrentSession currentSession = SessionFactory.instance().makeCurrentSession(httpServletRequest);
        int hostId = currentSession.getUserId();
        QuestionAnswer questionAnswer = new QuestionAnswer();
        List<QuestionHost> questionHostList = questionAnswer.getQuestionsByHostId(questionAnswerDAO, hostId);
        model.addAttribute("questionList", questionHostList);
        model.addAttribute("answerForm", questionAnswer);

        return "questionsList";
    }

    @PostMapping("/answerquestion/{id}/event/{event_id}/student/{student_id}")
    public String answerQuestion(@ModelAttribute("answerForm") QuestionAnswer questionAnswer,
                                 @PathVariable(value = "id") Integer questionId,
                                 @PathVariable(value = "event_id") Integer eventId,
                                 @PathVariable(value = "student_id") Integer studentId,

                                 HttpServletRequest httpServletRequest){
        QuestionAnswer questionAnswerObject = new QuestionAnswer();
        questionAnswerObject.setQuestionId(questionId);
        questionAnswerObject.setAnswerText(questionAnswer.getAnswerText());
        questionAnswerObject.setStudentId(studentId);
        questionAnswerObject.setEventId(eventId);
        questionAnswer.answerQuestion(questionAnswerDAO, questionAnswerObject);
        return "redirect:/questions";
    }

    @PostMapping("/askquestion/{id}")
    public String askQuestion(@ModelAttribute("questionForm") QuestionAnswer questionAnswer,
                              @PathVariable(value = "id") Integer eventId,
                              HttpServletRequest httpServletRequest, Model model){
        QuestionAnswer newQuestion = new QuestionAnswer();
        CurrentSession currentSession = SessionFactory.instance().makeCurrentSession(httpServletRequest);
        questionAnswer.setStudentId(currentSession.getUserId());
        questionAnswer.setEventId(eventId);
        newQuestion.askQuestion(questionAnswerDAO,questionAnswer);

        return "redirect:/studentdashboard";
    }


}
