package com.eventmanagement.EventManagement.model.factory;


import com.eventmanagement.EventManagement.model.StudentPastInterest;

public class StudentPastInterestFactory {
    private static StudentPastInterestFactory onlyInstance = null;

    public static StudentPastInterestFactory getInstance(){
        if (onlyInstance == null){
            onlyInstance = new StudentPastInterestFactory();
        }
        return onlyInstance;
    }

    public StudentPastInterest makeStudentPastInterest(){
        return new StudentPastInterest();
    }

}
