package com.eventmanagement.EventManagement.model.factory;

import com.eventmanagement.EventManagement.DataAccessObjects.interfaces.IStudentInterestDAO;
import com.eventmanagement.EventManagement.model.StudentInterest;
import com.eventmanagement.EventManagement.model.interfaces.IStudentInterest;
import com.eventmanagement.EventManagement.model.interfaces.IStudentInterestFactory;

public class StudentInterestFactory implements IStudentInterestFactory
{
    private static StudentInterestFactory singleInstance;

    private StudentInterestFactory()
    {

    }

    public static StudentInterestFactory instance()
    {
        if(singleInstance == null)
        {
            singleInstance = new StudentInterestFactory();
        }
        return singleInstance;
    }

    @Override
    public IStudentInterest makeStudentInterest() {
        return new StudentInterest();
    }

    @Override
    public IStudentInterest makeStudentIntereset(IStudentInterestDAO studentInterestDAO) {
        return new StudentInterest(studentInterestDAO);
    }
}
