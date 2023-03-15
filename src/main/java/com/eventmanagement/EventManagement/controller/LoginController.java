package com.eventmanagement.EventManagement.controller;

import com.eventmanagement.EventManagement.DataAccessObjects.HostDAO;
import com.eventmanagement.EventManagement.DataAccessObjects.StudentDAO;
import com.eventmanagement.EventManagement.model.SessionManagement;
import com.eventmanagement.EventManagement.model.Host;
import com.eventmanagement.EventManagement.model.Security;
import com.eventmanagement.EventManagement.model.Student;
import com.eventmanagement.EventManagement.model.User;
import com.eventmanagement.EventManagement.model.factory.UserFactory;
import com.eventmanagement.EventManagement.model.interfaces.ISecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController
{
    @Autowired
    private StudentDAO studentDAO;

    @Autowired
    private HostDAO hostDAO;

    @GetMapping("/login")
    public String getLogin(Model model)
    {
        model.addAttribute("loginForm", UserFactory.instance().makeUser());
        return "login";
    }

    @PostMapping("/login")
    public String postLogin(@ModelAttribute("loginForm") User loginForm, Model model, HttpServletResponse response)
    {
        boolean result = false;
        SessionManagement sessionManagement = new SessionManagement(response);

        ISecurity security = new Security();
        String hashedPassword = security.getHashedPassword(loginForm.getPassword());
        loginForm.setPassword(hashedPassword);

        if(loginForm.getUserType().equals("Host"))
        {
             result = loginForm.verifyCredentials(hostDAO);
             if(result)
             {
                Host currentHost = Host.findByEmail(hostDAO, loginForm.getEmail());
                sessionManagement.addUserIdToCookie(currentHost.getId());
                sessionManagement.addEmailToCookie(loginForm.getEmail());
                sessionManagement.addUserTypeToCookie("host");
                return "redirect:/hostdashboard";
             }
        }
        else
        {
             result = loginForm.verifyCredentials(studentDAO);
            if(result)
            {
                Student student = Student.findByEmail(studentDAO, loginForm.getEmail());
                sessionManagement.addUserIdToCookie(student.getId());
                sessionManagement.addEmailToCookie(loginForm.getEmail());
                sessionManagement.addUserTypeToCookie("student");
                return "redirect:/studentdashboard";
            }
        }

        model.addAttribute("invalidCredentialsError","Invalid username and password");
        return "login";
    }
}
