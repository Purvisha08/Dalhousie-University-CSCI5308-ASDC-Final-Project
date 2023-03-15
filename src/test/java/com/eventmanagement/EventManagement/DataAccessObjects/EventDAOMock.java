package com.eventmanagement.EventManagement.DataAccessObjects;

import com.eventmanagement.EventManagement.DataAccessObjects.interfaces.IEventDAO;
import com.eventmanagement.EventManagement.model.Event;
import com.eventmanagement.EventManagement.model.EventRegistration;
import com.eventmanagement.EventManagement.model.factory.EventFactory;
import com.eventmanagement.EventManagement.model.interfaces.IEventRegistration;

import org.springframework.stereotype.Repository;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class EventDAOMock implements IEventDAO
{
    public Optional<Event> findById(int id) {
        DateFormat formatter = new SimpleDateFormat("dd-MMM-yy");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());

        if (id == 10) { // for update event
            Event event = EventFactory.instance().makeEvent();
			event.setName("Updated Event");
			event.setDescription("New Description");
			event.setVenue("New Venue");
			event.setCategory("New Category");
            return Optional.of(event);
        } else if (id == 11) {
            Event event = EventFactory.instance().makeEvent();
            event.setId(id);
            event.setName("Event 1");
            event.setDescription("Event 1 description");
            event.setCategory("Hackathon");
            event.setVenue("DAL");
            event.setCompleted(false);
            event.setPrice(100);
            event.setCapacity(100);
            event.setFestivalId(1);
            try {
                event.setStartDate(formatter.parse("01-01-2022"));
                event.setEndDate(formatter.parse("01-02-2022"));
            } catch (Exception e) {
                event.setStartDate(null);
                event.setEndDate(null);
            }
            return Optional.of(event);
        } else if(id == 1) {
            Event event = EventFactory.instance().makeEvent();
            event.setId(id);
            event.setName("Event 1");
            event.setDescription("Some description for event 1");
            event.setCategory("Hackathon");
            event.setVenue("DAL");
            event.setCompleted(false);
            event.setPrice(100);
            event.setCapacity(100);
            event.setFestivalId(1);
            calendar.add(Calendar.DATE, 5);
            event.setStartDate(calendar.getTime());
            calendar.add(Calendar.DATE,2);
            event.setEndDate(calendar.getTime());
            return Optional.of(event);
        } else if (id == 2) {
            Event event = EventFactory.instance().makeEvent();
            event.setId(id);
            event.setName("Event 2");
            event.setDescription("Some description for event 2");
            event.setCategory("Hackathon");
            event.setVenue("DAL");
            event.setCompleted(false);
            event.setPrice(200);
            event.setCapacity(200);
            event.setFestivalId(1);
            calendar.add(Calendar.DATE, 15);
            event.setStartDate(calendar.getTime());
            calendar.add(Calendar.DATE,2);
            event.setEndDate(calendar.getTime());
            return Optional.of(event);
        } else {
            return Optional.empty();
        }

    }

    public int addEvent(Event event) {
        if (event.getName().equals("New Event")) {
			event.setId(11);
			return event.getId();
		} else {
            return 0;
        }
    }

    public int updateEvent(Event event) {
        if (event.getName().equals("Update Event")) {
			return event.getId();
		} else {
            return 0;
        }
    }

    public int deleteEvent(int id) {
        if (id == 11) {
			return 1;
		} else {
            return 0;
        }
    }

    public List<Event> findByFestivalId(Integer festival_id) {
        DateFormat formatter = new SimpleDateFormat("dd-MMM-yy");
        List<Event> events = new ArrayList<Event>();
        if(festival_id == 1) {
            Event event = EventFactory.instance().makeEvent();
            event.setId(11);
            event.setName("Event 1");
            event.setDescription("Event 1 description");
            event.setCategory("Hackathon");
            event.setVenue("McGill");
            event.setCompleted(false);
            event.setPrice(100);
            event.setCapacity(100);
            event.setFestivalId(1);
            try {
                event.setStartDate(formatter.parse("01-01-2022"));
                event.setEndDate(formatter.parse("01-02-2022"));
            } catch (Exception e) {
                event.setStartDate(null);
                event.setEndDate(null);
            }
            events.add(event);
        }
        return events;
    }

    public List<IEventRegistration> findRegistrationsById(Integer eventId) {
        if(eventId.equals(1)) {
            List<IEventRegistration> events = new ArrayList<IEventRegistration>();
            EventRegistration eventRegistration = new EventRegistration();
            eventRegistration.setEventId(eventId);
            eventRegistration.setStudentId(1);
            events.add((IEventRegistration) eventRegistration);
            return events;
        } else {
            return null;
        }
    };

    public Integer updateRating(Event event) {
        if(event.getId() == 1) {
            event.setRating(100.0);
            return 1;
        } else {
            return 0;
        }
    };

}
