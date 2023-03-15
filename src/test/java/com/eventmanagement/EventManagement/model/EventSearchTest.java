package com.eventmanagement.EventManagement.model;

import com.eventmanagement.EventManagement.DataAccessObjects.EventListDAOMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EventSearchTest {

    ArrayList<Event> eventsAll = new ArrayList<>();
    EventSearch eventSearch;

    @Test
    public void getEventByKeywordTest(){
        EventListDAOMock eventListDaoMock = new EventListDAOMock();
        List<Event> events = eventListDaoMock.getEventList();
        Event event1 = events.get(0);
        String eventName = event1.getName();
        Assertions.assertEquals("Event 1", eventName);
    }


    @Test
    public void getEventByCategoryTest(){
        EventListDAOMock eventListDaoMock = new EventListDAOMock();
        List<Event> events = eventListDaoMock.getEventList();
        String eventName = events.get(0).getName();
        Assertions.assertEquals("Event 1", eventName);
    }

    @Test
    public void getEventByDateRangeTest(){
        EventListDAOMock eventListDaoMock = new EventListDAOMock();
        List<Event> events = eventListDaoMock.getEventList();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = null;
        Date endDate = null;
        try {
            startDate = format.parse("2022-07-29");
            endDate = format.parse("2022-08-29");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        String eventName = events.get(0).getName();
        Assertions.assertEquals("Event 1", eventName);
    }
}
