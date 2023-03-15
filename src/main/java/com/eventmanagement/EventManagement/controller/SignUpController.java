package com.eventmanagement.EventManagement.controller;

import com.eventmanagement.EventManagement.DataAccessObjects.HostDAO;
import com.eventmanagement.EventManagement.DataAccessObjects.StudentDAO;
import com.eventmanagement.EventManagement.model.Security;
import com.eventmanagement.EventManagement.model.User;
import com.eventmanagement.EventManagement.model.factory.UserFactory;
import com.eventmanagement.EventManagement.model.interfaces.ISecurity;
import com.eventmanagement.EventManagement.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class SignUpController {

    @Autowired
    private StudentDAO studentDAO;

    @Autowired
    private HostDAO hostDAO;

    @Autowired
    private UserValidator signUpFormValidator;

    @GetMapping("/signup")
    public String getSignUp(Model model) {
        model.addAttribute("signUpForm", UserFactory.instance().makeUser());
        return "signup";
    }

    @PostMapping("/signup")
    public String postSignUp(@ModelAttribute("signUpForm") User signUpForm, BindingResult bindingResult) {
        signUpFormValidator.validate(signUpForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "signup";
        }

        ISecurity security = new Security();
        String hashedPassword = security.getHashedPassword(signUpForm.getPassword());
        signUpForm.setPassword(hashedPassword);

        if(signUpForm.getUserType().equals("Host"))
        {
            if(signUpForm.create(hostDAO))
            {
                return "redirect:/login";
            }
        }
        else
        {
            if(signUpForm.create(studentDAO)) {
                return "redirect:/login";
            }
        }
        return "signup";
    }
}
