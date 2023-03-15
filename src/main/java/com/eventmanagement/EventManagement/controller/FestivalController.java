package com.eventmanagement.EventManagement.controller;

import com.eventmanagement.EventManagement.model.CurrentSession;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.eventmanagement.EventManagement.DataAccessObjects.EventDAO;
import com.eventmanagement.EventManagement.DataAccessObjects.FestivalDAO;
import com.eventmanagement.EventManagement.constants.Options;
import com.eventmanagement.EventManagement.model.Event;
import com.eventmanagement.EventManagement.model.Festival;
import com.eventmanagement.EventManagement.model.factory.SessionFactory;

import org.springframework.web.bind.annotation.PostMapping;
import com.eventmanagement.EventManagement.validator.FestivalValidator;

@Controller
public class FestivalController {

    @Autowired
    private FestivalDAO festivalDAO;
    @Autowired
    private EventDAO eventDao;
    FestivalValidator festivalValidator;
    public FestivalController() { this.festivalValidator = new FestivalValidator();}

    @GetMapping(path = { "/festival/{festival_id}" })
    public String getFestivalEvents(@PathVariable(value = "festival_id") Integer festival_id, Model model) {
        model.addAttribute("festival", Festival.findById(this.festivalDAO, festival_id));
        model.addAttribute("festivalEvents", Event.findByFestivalId(this.eventDao, festival_id));
        return "festival";
    }

    @GetMapping(path =  "/festival/new", produces = "text/html")
    public String getNewFestival(Model model, HttpServletRequest request) {
        model.addAttribute("festivalForm", new Festival());
        model.addAttribute("universityOptions", Options.instance().getUniversityOptions());

        return "createFestival.html";
    }

    @PostMapping(path = { "/festival/new" })
    public String postNewFestival(@ModelAttribute("festivalForm") Festival festivalForm,
                                  BindingResult bindingResult, HttpServletRequest request) {
        System.out.println(festivalForm);
        CurrentSession currentSession = SessionFactory.instance().makeCurrentSession(request);
        Integer hostId = currentSession.getUserId();
        Festival.validateFestivalData(festivalValidator, festivalForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "createFestival.html";
        }
        festivalForm.setHostId(hostId);
        Festival.create(festivalDAO, festivalForm);
        return "redirect:/hostdashboard";
    }

    @GetMapping(path = { "/festival/{festival_id}/update" })
    public String getUpdateFestival(@PathVariable(value = "festival_id") Integer festival_id, Model model) {
        model.addAttribute("festival", Festival.findById(festivalDAO, festival_id));
        return "updateFestival";
    }

    @PostMapping(path = { "/festival/{festival_id}/update"})
    public String postUpdateFestival(@PathVariable(value = "festival_id") Integer festival_id,
                                     @ModelAttribute("festival") Festival festival, Model model, BindingResult bindingResult) {
        Festival.validateFestivalData(festivalValidator, festival, bindingResult);
        festival.setId(festival_id);
        if (bindingResult.hasErrors()) {
            return "updateFestival";
        }
        Festival updatedFestival = festival.update(festivalDAO);
        if (updatedFestival != null) {
            return "redirect:/festival/"+festival_id.toString();
        } else {
            model.addAttribute("update_error", "Some error occurred. Try again.");
            return "updateFestival";
        }
    }

}
