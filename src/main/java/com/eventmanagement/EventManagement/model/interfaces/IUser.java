package com.eventmanagement.EventManagement.model.interfaces;

import java.time.LocalDateTime;

import com.eventmanagement.EventManagement.DataAccessObjects.interfaces.IUserDAO;

public interface IUser {

    //getters and setters
    public Integer getId();
    public void setId(Integer id);
    public String getName();
    public void setName(String name);
    public String getEmail() ;
    public void setEmail(String email);
    public String getPassword();
    public void setPassword(String password);
    public String getPasswordConfirm();
    public void setPasswordConfirm(String passwordConfirm);
    public void setCreatedAt(LocalDateTime created_at);
    public LocalDateTime getCreatedAt();
    public void setUpdatedAt(LocalDateTime updated_at);
    public LocalDateTime getUpdatedAt();
    public String getUserType();
    public void setUserType(String userType);
    public String getUniversity();
    public void setUniversity(String university);
    public Integer getContactNumber();
    public void setContactNumber(Integer contactNumber);

    //operations
    public Boolean verifyCredentials(IUserDAO userDAO);
    public Boolean create(IUserDAO userDAO);
}
