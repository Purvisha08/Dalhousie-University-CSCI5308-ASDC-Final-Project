package com.eventmanagement.EventManagement.model.interfaces;

import com.eventmanagement.EventManagement.DataAccessObjects.interfaces.IEventDAO;
import com.eventmanagement.EventManagement.DataAccessObjects.interfaces.IEventRegistrationDAO;
import com.eventmanagement.EventManagement.DataAccessObjects.interfaces.IWaitlistDAO;

public interface IEventFactory
{
    public IEventRegistration makeEventRegistration();
    public IEventRegistration makeEventRegistration(IEventRegistrationDAO eventRegistrationDAO, IEventDAO eventDAO);
    public IRegistrationCancellation makeRegistrationCancellation();
    public IRegistrationCancellation makeRegistrationCancellation(IEventRegistrationDAO eventRegistrationDAO, IEventDAO eventDAO);
    public IWaitlist makeWaitist();
    public IWaitlist makeWaitlist(IWaitlistDAO waitlistDAO);
}
