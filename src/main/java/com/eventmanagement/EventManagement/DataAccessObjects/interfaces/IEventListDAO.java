package com.eventmanagement.EventManagement.DataAccessObjects.interfaces;

import com.eventmanagement.EventManagement.model.Event;

import java.util.List;
import java.util.Optional;

public interface IEventListDAO
{
    public List<Event> getEventList();
    public Optional<Event> getEventById(int eventId);
}
