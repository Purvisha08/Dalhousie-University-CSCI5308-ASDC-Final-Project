package com.eventmanagement.EventManagement.controller;

import com.eventmanagement.EventManagement.model.CurrentSession;
import com.eventmanagement.EventManagement.model.factory.SessionFactory;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomePageController {

    @GetMapping("/")
    public String sendGreetings(HttpServletRequest request) {
        try{
            CurrentSession currentSession = SessionFactory.instance().makeCurrentSession(request);
            if (currentSession.getEmail() == null){
                return "redirect:/login";
            }
            else if (currentSession.getUserType().equals("host")){
                return "redirect:/hostdashboard";
            }
            else{
                return "redirect:/studentdashboard";
            }
        }
        catch (NullPointerException | NumberFormatException n){
            return "redirect:/login";
        }

    }
}
