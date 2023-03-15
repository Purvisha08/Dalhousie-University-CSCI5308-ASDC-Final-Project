package com.eventmanagement.EventManagement.model.factory;

import com.eventmanagement.EventManagement.DataAccessObjects.EventListDAO;
import com.eventmanagement.EventManagement.DataAccessObjects.interfaces.IEventDAO;
import com.eventmanagement.EventManagement.DataAccessObjects.interfaces.IEventRegistrationDAO;
import com.eventmanagement.EventManagement.DataAccessObjects.interfaces.IWaitlistDAO;
import com.eventmanagement.EventManagement.model.interfaces.IRegistrationCancellation;
import com.eventmanagement.EventManagement.model.Event;
import com.eventmanagement.EventManagement.model.EventComplete;
import com.eventmanagement.EventManagement.model.EventRegistration;
import com.eventmanagement.EventManagement.model.RegistrationCancellation;
import com.eventmanagement.EventManagement.model.Waitlist;
import com.eventmanagement.EventManagement.model.interfaces.IEvent;
import com.eventmanagement.EventManagement.model.interfaces.IEventFactory;
import com.eventmanagement.EventManagement.model.interfaces.IEventRegistration;
import com.eventmanagement.EventManagement.model.interfaces.IWaitlist;
import com.eventmanagement.EventManagement.rowmapper.EventRegistrationRowMapper;
import com.eventmanagement.EventManagement.rowmapper.EventRowMapper;
import com.eventmanagement.EventManagement.validator.EventValidator;

public class EventFactory implements IEventFactory
{
    private static EventFactory singleInstance;

    public static EventFactory instance()
    {
        if(singleInstance == null)
        {
            singleInstance = new EventFactory();
        }
        return singleInstance;
    }

    public EventRegistrationRowMapper makeEventRegistrationRowMapper() {
        return new EventRegistrationRowMapper();
    }

    public EventRowMapper makeEventRowMapper() {
        return new EventRowMapper();
    }

    public EventComplete makeEventComplete(IEvent event) {
        return new EventComplete(event);
    }

    public EventListDAO makeEventListDAO() {
        return new EventListDAO();
    }

    public Event makeEvent() {
        return new Event();
    }

    public EventValidator makeEventValidator() {
        return new EventValidator();
    }

    @Override
    public IEventRegistration makeEventRegistration() {
        return new EventRegistration();
    }

    @Override
    public IEventRegistration makeEventRegistration(IEventRegistrationDAO eventRegistrationDAO, IEventDAO eventDAO) {
        return new EventRegistration(eventRegistrationDAO,eventDAO);
    }

    @Override
    public IRegistrationCancellation makeRegistrationCancellation() {
        return new RegistrationCancellation();
    }

    @Override
    public IRegistrationCancellation makeRegistrationCancellation(IEventRegistrationDAO eventRegistrationDAO, IEventDAO eventDAO) {
        return new RegistrationCancellation(eventRegistrationDAO,eventDAO);
    }

    @Override
    public IWaitlist makeWaitist()
    {
        return new Waitlist();
    }

    @Override
    public IWaitlist makeWaitlist(IWaitlistDAO waitlistDAO)
    {
        return new Waitlist(waitlistDAO);
    }
}
