package com.eventmanagement.EventManagement.model.interfaces;

import com.eventmanagement.EventManagement.model.Event;

import java.util.ArrayList;
import java.util.Date;

public interface IEventSearch {
    public ArrayList<Event> getEventByKeyword(String keyword);
    public ArrayList<Event> getEventByCategory(String category);
    public ArrayList<Event> getEventByDateRange(Date startDate, Date endDate);
    public ArrayList<Event> getSortedEvents();
}
