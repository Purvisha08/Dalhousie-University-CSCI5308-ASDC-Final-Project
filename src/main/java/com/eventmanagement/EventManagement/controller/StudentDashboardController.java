package com.eventmanagement.EventManagement.controller;

import com.eventmanagement.EventManagement.model.CurrentSession;
import com.eventmanagement.EventManagement.constants.Options;
import com.eventmanagement.EventManagement.model.Event;
import com.eventmanagement.EventManagement.model.factory.EventSearchFactory;
import com.eventmanagement.EventManagement.model.interfaces.IEventSearch;
import com.eventmanagement.EventManagement.model.factory.EventFactory;
import com.eventmanagement.EventManagement.model.factory.SessionFactory;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
public class StudentDashboardController
{

    @GetMapping("/studentdashboard")
    public String getDashboard(Model model, HttpServletRequest request)
    {
        try{
            CurrentSession currentSession = SessionFactory.instance().makeCurrentSession(request);
            if (currentSession.getUserType().equals("student")){
//                EventSearch eventSearch = new EventSearch(currentSession.getUserId());
                IEventSearch eventSearch = EventSearchFactory.getInstance().getEventSearch(currentSession.getUserId());
                ArrayList<Event> eventList = eventSearch.getSortedEvents();
                model.addAttribute("eventList", eventList);
                model.addAttribute("eventForm", EventFactory.instance().makeEvent());
                model.addAttribute("categoryOptions", Options.instance().getEventCategoryOptions());
                return "studentdashboard";
            }
            return "redirect:/login";
        }
        catch (NullPointerException nullPointerException){
            return "redirect:/login";
        }
    }

// Reference: https://codebun.com/spring-boot-search-example-using-thymeleaf-and-spring-data-jpa/
    @RequestMapping(path= {"/", "/search"})
    public String getDashboardKeyword(Model model, String keyword, HttpServletRequest request){
        if (keyword==null){
            return "studentdashboard";
        }
        else{
            CurrentSession currentSession = SessionFactory.instance().makeCurrentSession(request);
            int userId = currentSession.getUserId();
            IEventSearch eventSearch = EventSearchFactory.getInstance().getEventSearch(userId);
            ArrayList<Event> eventByKeyword = eventSearch.getEventByKeyword(keyword);
            model.addAttribute("eventList", eventByKeyword);
            model.addAttribute("keyword",keyword);
            model.addAttribute("eventForm", EventFactory.instance().makeEvent());
            model.addAttribute("categoryOptions", Options.instance().getEventCategoryOptions());

            return "studentdashboard";
        }
    }

    @PostMapping("/searchbycategory")
    public String getDashboardByCategory(@RequestParam String categoryName,
                                         HttpServletRequest request,
                                         Model model){
        CurrentSession currentSession = SessionFactory.instance().makeCurrentSession(request);
        IEventSearch eventSearch = EventSearchFactory.getInstance().getEventSearch(currentSession.getUserId());
        ArrayList<Event> eventByCategory = eventSearch.getEventByCategory(categoryName);
        model.addAttribute("eventList", eventByCategory);
        model.addAttribute("eventForm", EventFactory.instance().makeEvent());
        model.addAttribute("categoryOptions", Options.instance().getEventCategoryOptions());

        return "studentdashboard";
    }

    @PostMapping("/searchbydaterange")
    public String getDashboardByDateRange(@ModelAttribute("eventForm") Event eventObject,
                                          HttpServletRequest request,
                                          Model model){

        CurrentSession currentSession = SessionFactory.instance().makeCurrentSession(request);
        IEventSearch eventSearch = EventSearchFactory.getInstance().getEventSearch(currentSession.getUserId());
        ArrayList<Event> eventByDateRange = eventSearch.getEventByDateRange(eventObject.getStartDate(), eventObject.getEndDate());
        model.addAttribute("eventList", eventByDateRange);
        model.addAttribute("eventForm", EventFactory.instance().makeEvent());
        model.addAttribute("categoryOptions", Options.instance().getEventCategoryOptions());

        return "studentdashboard";
    }

}
