package com.eventmanagement.EventManagement.DataAccessObjects;

import com.eventmanagement.EventManagement.DataAccessObjects.interfaces.IEventListDAO;
import com.eventmanagement.EventManagement.model.Event;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class EventListDAOMock implements IEventListDAO {
    @Override
    public List<Event> getEventList() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        Date startDateEvent1 = null;
        Date endDateEvent1 = null;
        try {
            startDateEvent1 = format.parse("2022-07-07");
            endDateEvent1 = format.parse("2022-07-09");

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        List<Event> eventList = new ArrayList<>();
        Event event1 = new Event();
        event1.setId(1);
        event1.setName("Event 1");
        event1.setFestivalId(1);
        event1.setCategory("Bootcamp");
        event1.setDescription("Event 1 description");
        event1.setStartDate(startDateEvent1);
        event1.setEndDate(endDateEvent1);



        Date startDateEvent2 = null;
        Date endDateEvent2 = null;
        try {
            startDateEvent2 = format.parse("2022-08-01");
            endDateEvent2 = format.parse("2022-08-03");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        Event event2 = new Event();
        event2.setId(2);
        event2.setName("Event 2");
        event2.setCategory("Hackathon");
        event2.setDescription("Event 2 description");
        event2.setStartDate(startDateEvent2);
        event2.setEndDate(endDateEvent2);

        eventList.add(event1);
        eventList.add(event2);
        return eventList;
    }

    @Override
    public Optional<Event> getEventById(int eventId) {
        Event event = new Event();
        if (eventId == 1){
            event.setId(1);
            event.setName("Event 1");
            event.setCategory("Bootcamp");
        }
        return Optional.of(event);
    }

}
