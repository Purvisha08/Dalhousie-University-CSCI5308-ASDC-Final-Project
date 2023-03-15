package com.eventmanagement.EventManagement.DataAccessObjects;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.eventmanagement.EventManagement.DataAccessObjects.interfaces.IHostDAO;
import com.eventmanagement.EventManagement.model.Event;
import com.eventmanagement.EventManagement.model.Host;
import com.eventmanagement.EventManagement.model.factory.EventFactory;
import com.eventmanagement.EventManagement.model.factory.UserFactory;

public class HostDAOMock implements IHostDAO {

    public Optional<Host> findByEmail(String email) {
        if(email == null || email.trim().length() == 0) {
            return Optional.empty();
        }
        Host host = UserFactory.instance().makeHost();
        host.setId(1);
        host.setName("Host 1");
        host.setEmail(email);
        try {
            host.setCreatedAt(LocalDateTime.now());
            host.setUpdatedAt(LocalDateTime.now());
        } catch (Exception e) {
            host.setCreatedAt(null);
            host.setUpdatedAt(null);
        }
        return Optional.of(host);
    };

    public Optional<Host> findById(Integer id) {
        if(id == 0) {
            return Optional.empty();
        }
        if(id == 1) {
            Host host = UserFactory.instance().makeHost();
            host.setId(id);
            host.setName("Host 1");
            host.setEmail("host1@example.com");
            try {
                host.setCreatedAt(LocalDateTime.now());
                host.setUpdatedAt(LocalDateTime.now());
            } catch (Exception e) {
                host.setCreatedAt(null);
                host.setUpdatedAt(null);
            }
            return Optional.of(host);
        } else {
            return Optional.empty();
        }
    };

    public List<Event> findAllEvents(Integer hostId) {
        if(hostId != 1) {
            return null;
        }

        List<Event> events = new ArrayList<Event>();
        Integer[] mockEventIds = {1, 2, 3, 4, 5};

        for(int id: mockEventIds) {
            Event event = EventFactory.instance().makeEvent();
            event.setId(id);
            event.setName("Event "+id);
            event.setDescription("Event"+id+" description");
            event.setRating((double) id/5);
            events.add(event);
        }
        return events;
        
    };

    public Integer updateRating(Host host) {
        if(host.getId() == 1) {
            host.setRating(10.0);
            return 1;
        } else {
            return 0;
        }
    };
    
}
