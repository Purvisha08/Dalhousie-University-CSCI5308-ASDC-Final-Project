package com.eventmanagement.EventManagement.model;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class CurrentSession {
    String email;
    String userType;
    Integer userId;
    public CurrentSession(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        for (Cookie temp: cookies){
            if (temp.getName().equals("email")){
                this.email = temp.getValue();
            }
            if (temp.getName().equals("userType")){
                this.userType = temp.getValue();
            }
            if (temp.getName().equals("userId")){
                this.userId = Integer.valueOf(temp.getValue());
            }
        }
    }

    public String getEmail(){
        return this.email;
    }

    public String getUserType(){
        return this.userType;
    }

    public Integer getUserId() {
        return this.userId;
    }

}
