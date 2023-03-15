package com.eventmanagement.EventManagement.model.interfaces;

import java.util.List;

import com.eventmanagement.EventManagement.DataAccessObjects.interfaces.IEventDAO;
import com.eventmanagement.EventManagement.model.Event;

public interface IEvent {
    public Event update (IEventDAO eventDAO);
    public Boolean delete (IEventDAO eventDAO);
    public List<IEventRegistration> findRegistrations(IEventDAO eventDAO);
    public Boolean updateRating(IEventDAO eventDAO);
}
