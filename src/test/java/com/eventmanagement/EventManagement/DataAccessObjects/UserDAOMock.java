package com.eventmanagement.EventManagement.DataAccessObjects;

import com.eventmanagement.EventManagement.DataAccessObjects.interfaces.IUserDAO;
import com.eventmanagement.EventManagement.model.interfaces.IUser;

public class UserDAOMock implements IUserDAO {

    public Boolean create(IUser user) {
        if(user.getName().equals("New User")) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean verifyCredentials(String email, String password) {
        if(email.equals("validUser@example.com") && password.equals("validPassword")) {
            return true;
        } else {
            return  false;
        }
    }

}
