package com.eventmanagement.EventManagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.eventmanagement.EventManagement.model.factory.SessionFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LogoutController {

    @GetMapping("/logout")
    public String getLogout(HttpServletResponse response){
        Cookie cookie = SessionFactory.instance().makeCookie("email", "");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        Cookie cookie1 = SessionFactory.instance().makeCookie("userType", "");
        cookie1.setMaxAge(0);
        response.addCookie(cookie1);
        Cookie cookie2 = SessionFactory.instance().makeCookie("userId", "");
        response.addCookie(cookie2);
        return "redirect:/login";
    }
}
