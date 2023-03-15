package com.eventmanagement.EventManagement.controller;

import com.eventmanagement.EventManagement.model.CurrentSession;
import com.eventmanagement.EventManagement.DataAccessObjects.FestivalDAO;
import com.eventmanagement.EventManagement.DataAccessObjects.HostDAO;
import com.eventmanagement.EventManagement.model.Festival;
import com.eventmanagement.EventManagement.model.Host;
import com.eventmanagement.EventManagement.model.factory.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

import javax.servlet.http.HttpServletRequest;


@Controller
public class HostDashboardController
{
    
    @Autowired
    private HostDAO hostDAO;

    @Autowired
    private FestivalDAO festivalDAO;

    @GetMapping(path = { "/hostdashboard", "/hostdashboard/search"})
    public String getDashboard(HttpServletRequest request, Model model,  String searchQuery)
    {
        try {
            CurrentSession currentSession = SessionFactory.instance().makeCurrentSession(request);
            if (currentSession.getUserType().equals("host")) {
                Host currentHost = Host.findByEmail(hostDAO, currentSession.getEmail());
                if(currentHost != null) {
                    List<Festival> hostFestivals;
                    if (searchQuery == null){
                        hostFestivals = Festival.findByhostId(festivalDAO, currentHost.getId());
                    } else {
                        hostFestivals = Festival.filterBySearchQuery(festivalDAO, currentHost.getId(), searchQuery);
                    }
                    model.addAttribute("festivals", hostFestivals);
                    return "hostdashboard";
                }
            }
            return "redirect:/login";
        }
        catch (NullPointerException nullPointerException){
            return "redirect:/login";
        }
    }
}
