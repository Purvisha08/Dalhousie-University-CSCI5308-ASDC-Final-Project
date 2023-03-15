package com.eventmanagement.EventManagement.model.interfaces;


import com.eventmanagement.EventManagement.DataAccessObjects.interfaces.IStudentInterestDAO;

public interface IStudentInterestFactory
{
    public IStudentInterest makeStudentInterest();
    public IStudentInterest makeStudentIntereset(IStudentInterestDAO studentInterestDAO);
}
