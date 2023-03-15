package com.eventmanagement.EventManagement.DataAccessObjects.interfaces;

import com.eventmanagement.EventManagement.model.interfaces.IUser;

public interface IUserDAO {
    Boolean verifyCredentials (String email, String password);
    Boolean create(IUser user);
}
