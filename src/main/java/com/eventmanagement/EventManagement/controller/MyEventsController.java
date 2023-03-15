package com.eventmanagement.EventManagement.controller;

import com.eventmanagement.EventManagement.model.CurrentSession;
import com.eventmanagement.EventManagement.DataAccessObjects.interfaces.IEventDAO;
import com.eventmanagement.EventManagement.DataAccessObjects.interfaces.IEventRegistrationDAO;
import com.eventmanagement.EventManagement.model.factory.MySQLDatabaseFactory;
import com.eventmanagement.EventManagement.model.factory.EventFactory;
import com.eventmanagement.EventManagement.model.factory.SessionFactory;
import com.eventmanagement.EventManagement.model.interfaces.IEventRegistration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class MyEventsController
{

    private IEventRegistrationDAO eventRegistrationDAO;
    private IEventDAO eventDAO;

    public MyEventsController()
    {
        this.eventRegistrationDAO = MySQLDatabaseFactory.instance().makeEventRegistrationDAO();
        this.eventDAO = MySQLDatabaseFactory.instance().makeEventDAO();
    }

    @GetMapping("/myevents")
    public String getMyEvents(Model model, HttpServletRequest request)
    {
        CurrentSession currentSession = SessionFactory.instance().makeCurrentSession(request);
        Integer studentId = currentSession.getUserId();
        IEventRegistration eventRegistration = EventFactory.instance().makeEventRegistration(this.eventRegistrationDAO, this.eventDAO);
        List<IEventRegistration> eventRegistrationArrayList= eventRegistration.getPreviousRegistrations(studentId);
        model.addAttribute("eventRegistrationsList",eventRegistrationArrayList);
        return "myevents";
    }

}
