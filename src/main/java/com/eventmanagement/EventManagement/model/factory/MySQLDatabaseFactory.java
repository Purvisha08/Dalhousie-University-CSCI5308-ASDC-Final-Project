package com.eventmanagement.EventManagement.model.factory;

import com.eventmanagement.EventManagement.DataAccessObjects.*;
import com.eventmanagement.EventManagement.DataAccessObjects.interfaces.*;
import com.eventmanagement.EventManagement.model.interfaces.IDatabaseAccessFactory;

public class MySQLDatabaseFactory implements IDatabaseAccessFactory
{
    private static MySQLDatabaseFactory singleInstance = null;

    private MySQLDatabaseFactory()
    {

    }

    public static MySQLDatabaseFactory instance() {
        if(singleInstance == null) {
            singleInstance = new MySQLDatabaseFactory();
        }
        return singleInstance;
    }

    @Override
    public IEventDAO makeEventDAO() {
        return new EventDAO();
    }

    @Override
    public IEventRegistrationDAO makeEventRegistrationDAO() {
        return new EventRegistrationDAO();
    }

    @Override
    public IWaitlistDAO makeWaitlistDAO() {
        return new WaitlistDAO();
    }

    @Override
    public ITransactionDAO makeTransactionDAO() {
        return new TransactionDAO();
    }

    @Override
    public IStudentInterestDAO makeStudentInterestDAO() {
        return new StudentInterestDAO();
    }

    @Override
    public IEventListDAO makeEventListDAO() {
        return new EventListDAO();
    }

    @Override
    public IFestivalDAO makeFestivalDAO() {
        return new FestivalDAO();
    }

    @Override
    public IStudentDAO makeStudentDAO() {
        return new StudentDAO();
    }

    @Override
    public IStudentPastInterestDAO makeStudentPastInterest() {
        return new StudentPastInterestDAO();
    }

    @Override
    public IHostDAO makeHostDAO() {
        return new HostDAO();
    }

    @Override
    public IQuestionAnswerDAO makeQuestionAnswerDAO() {
        return new QuestionAnswerDAO();
    }

    @Override
    public INotificationDAO makeNotificationDAO() {
        return new NotificationDAO();
    }


}
