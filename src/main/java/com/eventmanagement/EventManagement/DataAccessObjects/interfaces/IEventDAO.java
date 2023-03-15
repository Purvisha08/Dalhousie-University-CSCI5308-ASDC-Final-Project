package com.eventmanagement.EventManagement.DataAccessObjects.interfaces;

import com.eventmanagement.EventManagement.model.Event;
import com.eventmanagement.EventManagement.model.interfaces.IEventRegistration;

import java.util.List;
import java.util.Optional;

public interface IEventDAO
{
    List<Event> findByFestivalId(Integer festival_id);
    Optional<Event> findById(int id);
    int addEvent(Event event);
    int updateEvent(Event event);
    int deleteEvent(int id);
    List<IEventRegistration> findRegistrationsById(Integer eventId);
    Integer updateRating(Event event) ;
}
