package com.eventmanagement.EventManagement.model;

import com.eventmanagement.EventManagement.constants.AppConstants;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import com.eventmanagement.EventManagement.model.factory.SessionFactory;

public class SessionManagement {
    HttpServletResponse response;
    private int cookieAge = AppConstants.MAXIMUM_SESSION_COOKIE_AGE;
    public SessionManagement(HttpServletResponse response){
        this.response = response;
    }

    public void addEmailToCookie(String email){
        Cookie cookie = SessionFactory.instance().makeCookie("email", email);
        cookie.setMaxAge(cookieAge);
        this.response.addCookie(cookie);
    }

    public void addUserTypeToCookie(String userType){
        Cookie cookie = SessionFactory.instance().makeCookie("userType", userType);
        cookie.setMaxAge(cookieAge);
        this.response.addCookie(cookie);
    }

    public void addUserIdToCookie(Integer userId)
    {
        Cookie cookie = SessionFactory.instance().makeCookie("userId", String.valueOf(userId));
        cookie.setMaxAge(cookieAge);
        this.response.addCookie(cookie);
    }

}
