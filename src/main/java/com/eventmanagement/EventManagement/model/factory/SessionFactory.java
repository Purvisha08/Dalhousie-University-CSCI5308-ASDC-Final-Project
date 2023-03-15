package com.eventmanagement.EventManagement.model.factory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.eventmanagement.EventManagement.model.CurrentSession;

public class SessionFactory {
    private static SessionFactory singleInstance;

    public static SessionFactory instance()
    {
        if(singleInstance == null)
        {
            singleInstance = new SessionFactory();
        }
        return singleInstance;
    }

    public CurrentSession makeCurrentSession(HttpServletRequest request) {
        return new CurrentSession(request);
    }

    public Cookie makeCookie(String key, String value) {
        return new Cookie(key, value);
    }

}
