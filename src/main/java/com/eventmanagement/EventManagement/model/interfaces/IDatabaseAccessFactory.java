package com.eventmanagement.EventManagement.model.interfaces;

import com.eventmanagement.EventManagement.DataAccessObjects.interfaces.*;

public interface IDatabaseAccessFactory
{
    public IEventDAO makeEventDAO();
    public IEventRegistrationDAO makeEventRegistrationDAO();
    public IWaitlistDAO makeWaitlistDAO();
    public ITransactionDAO makeTransactionDAO();
    public IStudentInterestDAO makeStudentInterestDAO();
    public IEventListDAO makeEventListDAO();
    public IFestivalDAO makeFestivalDAO();
    public IStudentDAO makeStudentDAO();
    public IStudentPastInterestDAO makeStudentPastInterest();
    public IHostDAO makeHostDAO();

    public IQuestionAnswerDAO makeQuestionAnswerDAO();
    public INotificationDAO makeNotificationDAO();
}
