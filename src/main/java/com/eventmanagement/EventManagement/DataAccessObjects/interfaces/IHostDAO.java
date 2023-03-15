package com.eventmanagement.EventManagement.DataAccessObjects.interfaces;

import com.eventmanagement.EventManagement.model.Event;
import com.eventmanagement.EventManagement.model.Host;

import java.util.List;
import java.util.Optional;

public interface IHostDAO
{
    public Optional<Host> findByEmail(String email);
    public Optional<Host> findById(Integer id);
    public List<Event> findAllEvents(Integer hostId);
    public Integer updateRating(Host host);

}
