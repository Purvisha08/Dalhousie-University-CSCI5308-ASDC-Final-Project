package com.eventmanagement.EventManagement.controller;

import com.eventmanagement.EventManagement.model.CurrentSession;
import com.eventmanagement.EventManagement.DataAccessObjects.interfaces.IWaitlistDAO;
import com.eventmanagement.EventManagement.model.factory.MySQLDatabaseFactory;
import com.eventmanagement.EventManagement.model.factory.EventFactory;
import com.eventmanagement.EventManagement.model.factory.SessionFactory;
import com.eventmanagement.EventManagement.model.interfaces.IWaitlist;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class WaitlistController
{
    private IWaitlistDAO waitlistDAO;

    public WaitlistController()
    {
        this.waitlistDAO = MySQLDatabaseFactory.instance().makeWaitlistDAO();
    }

    @GetMapping("/waitlist")
    public String getWaitlistForStudent(Model model, HttpServletRequest request)
    {
        CurrentSession currentSession = SessionFactory.instance().makeCurrentSession(request);
        Integer studentId = currentSession.getUserId();
        IWaitlist waitlist = EventFactory.instance().makeWaitlist(this.waitlistDAO);
        List<IWaitlist> waitlistArray = waitlist.getWaitlistForStudent(studentId);
        model.addAttribute("waitlistArray",waitlistArray);
        return "waitlist";
    }

    @PostMapping("/waitlist/add/{id}")
    public String addWaitlist(@PathVariable(value = "id") Integer eventId, Model model, HttpServletRequest request)
    {
        CurrentSession currentSession = SessionFactory.instance().makeCurrentSession(request);
        Integer studentId = currentSession.getUserId();
        IWaitlist waitlist = EventFactory.instance().makeWaitlist(this.waitlistDAO);
        if(waitlist.addWaitlist(eventId,studentId))
        {
            return "redirect:/waitlist";
        }
        else
        {
            return "redirect:/events/event";
        }
    }

    @PostMapping("/waitlist/delete/{id}")
    public String deleteWaitlist(@PathVariable(value = "id") Integer eventId, Model model, HttpServletRequest request)
    {
        CurrentSession currentSession = SessionFactory.instance().makeCurrentSession(request);
        Integer studentId = currentSession.getUserId();
        IWaitlist waitlist = EventFactory.instance().makeWaitlist(this.waitlistDAO);
        if(waitlist.delete(eventId,studentId))
        {
            return "redirect:/waitlist";
        }
        else
        {
            return "redirect:/waitlist";
        }
    }

    @GetMapping("/waitlist/event/{id}")
    public String getWaitlistForEvent(@PathVariable(value = "id") Integer eventId, Model model, HttpServletRequest request)
    {
        IWaitlist waitlist = EventFactory.instance().makeWaitlist(this.waitlistDAO);
        List<IWaitlist> waitlistArray = waitlist.getWaitlistForEvent(eventId);
        model.addAttribute("waitlistArray",waitlistArray);
        return "waitlist";
    }

}
