package com.eventmanagement.EventManagement.model;

import java.time.LocalDateTime;

import com.eventmanagement.EventManagement.DataAccessObjects.interfaces.IUserDAO;
import com.eventmanagement.EventManagement.model.interfaces.IUser;

public class User implements IUser {
    
    private Integer id;
    private String name;
    private String email;
    private String password;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private String passwordConfirm;
    private String userType;
    private String university;
    private Integer contactNumber;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public void setCreatedAt(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public LocalDateTime getCreatedAt() {
        return this.created_at;
    }

    public void setUpdatedAt(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }

    public LocalDateTime getUpdatedAt() {
        return this.updated_at;
    }

    public String getUserType() {
        if(this.userType == null) {
            return "Student";
        } else {
            return this.userType;
        }
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }


    public Integer getContactNumber() {
        return this.contactNumber;
    }

    public void setContactNumber(Integer contactNumber) {
        this.contactNumber = contactNumber;
    }

    // operations
    public Boolean verifyCredentials(IUserDAO userDAO) {
        return userDAO.verifyCredentials(this.getEmail(), this.getPassword());
    }

    public Boolean create(IUserDAO userDAO) {
        Boolean result = userDAO.create(this);
        return result;
    }

}
