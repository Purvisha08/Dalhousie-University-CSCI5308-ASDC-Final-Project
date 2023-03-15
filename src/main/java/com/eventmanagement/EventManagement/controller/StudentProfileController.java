package com.eventmanagement.EventManagement.controller;

import com.eventmanagement.EventManagement.model.CurrentSession;
import com.eventmanagement.EventManagement.DataAccessObjects.interfaces.IStudentDAO;
import com.eventmanagement.EventManagement.DataAccessObjects.interfaces.IStudentInterestDAO;
import com.eventmanagement.EventManagement.constants.Options;
import com.eventmanagement.EventManagement.model.*;
import com.eventmanagement.EventManagement.model.factory.MySQLDatabaseFactory;
import com.eventmanagement.EventManagement.model.factory.SessionFactory;
import com.eventmanagement.EventManagement.model.factory.StudentInterestFactory;
import com.eventmanagement.EventManagement.model.interfaces.IStudentInterest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

@Controller
public class StudentProfileController
{
    private IStudentInterestDAO studentInterestDAO;
    private IStudentDAO studentDAO;
    public StudentProfileController()
    {
        this.studentInterestDAO = MySQLDatabaseFactory.instance().makeStudentInterestDAO();
        this.studentDAO = MySQLDatabaseFactory.instance().makeStudentDAO();
    }

    @GetMapping("/studentprofile")
    public String getStudentProfile(Model model, HttpServletRequest request)
    {
        CurrentSession currentSession = SessionFactory.instance().makeCurrentSession(request);
        int studentId = currentSession.getUserId();
        IStudentInterest studentInterest = StudentInterestFactory.instance().makeStudentIntereset(studentInterestDAO);
        Student student = Student.findById(studentDAO, studentId);
        model.addAttribute("student", student);
        model.addAttribute("studentinterestslist",studentInterest.getStudentInterests(studentId));
        model.addAttribute("categoryOptions", Options.instance().getEventCategoryOptions());
        return "studentprofile";
    }

    @PostMapping("/addstudentinterest")
    public String getStudentProfile(@RequestParam String categoryId, HttpServletRequest request)
    {
        CurrentSession currentSession = SessionFactory.instance().makeCurrentSession(request);
        int studentId = currentSession.getUserId();
        IStudentInterest studentInterest = StudentInterestFactory.instance().makeStudentIntereset(studentInterestDAO);
        studentInterest.addStudentInterest(studentId,categoryId);
        return "redirect:/studentprofile";
    }

    @PostMapping(path = { "/deletestudentinterest/{id}" })
    public String deleteStudentInterest(@PathVariable(value = "id") Integer studentInterestId)
    {
        IStudentInterest studentInterest = StudentInterestFactory.instance().makeStudentIntereset(studentInterestDAO);;
        studentInterest.deleteStudentInterest(studentInterestId);
        return "redirect:/studentprofile";
    }

}
