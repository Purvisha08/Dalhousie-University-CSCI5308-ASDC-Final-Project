package com.eventmanagement.EventManagement.model;

import com.eventmanagement.EventManagement.model.interfaces.IStudentPastInterest;

public class StudentPastInterest implements IStudentPastInterest {
    String interest;

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

}
