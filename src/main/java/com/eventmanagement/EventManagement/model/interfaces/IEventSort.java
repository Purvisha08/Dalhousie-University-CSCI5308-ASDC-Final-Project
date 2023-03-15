package com.eventmanagement.EventManagement.model.interfaces;

import com.eventmanagement.EventManagement.model.Event;

import java.util.ArrayList;

public interface IEventSort {
    public void populateEventScoreListWithEvents();
    public void sortEvents(int userId);
    public void setPastInterestByUserId(int userId);
    public ArrayList<Event> sortedEvents();
}
