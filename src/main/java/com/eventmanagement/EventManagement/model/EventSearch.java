package com.eventmanagement.EventManagement.model;

import com.eventmanagement.EventManagement.DataAccessObjects.EventListDAO;
import com.eventmanagement.EventManagement.DataAccessObjects.interfaces.IEventListDAO;
import com.eventmanagement.EventManagement.model.factory.EventSortFactory;
import com.eventmanagement.EventManagement.model.interfaces.IEventSearch;
import com.eventmanagement.EventManagement.model.interfaces.IEventSort;

import java.util.ArrayList;
import java.util.Date;

public class EventSearch implements IEventSearch {

    public ArrayList<Event> eventByKeyword = new ArrayList<Event>();
    public ArrayList<Event> eventsAll;

    public EventSearch(int userId){
        IEventSort eventSort = EventSortFactory.getInstance().makeEventSort(new EventListDAO(), userId);
        this.eventsAll = eventSort.sortedEvents();
    }

    public EventSearch(IEventListDAO eventListDAO, int userId){
        IEventSort eventSort = EventSortFactory.getInstance().makeEventSort(eventListDAO, userId);
        this.eventsAll = eventSort.sortedEvents();
    }

    public ArrayList<Event> getEventByKeyword(String keyword){
        String eventName = "";
        String eventDescription = "";
        keyword = keyword.toLowerCase();
        for (int i=0; i<this.eventsAll.size(); i++){
            eventName = this.eventsAll.get(i).getName().toLowerCase();
            eventDescription = this.eventsAll.get(i).getDescription().toLowerCase();
            if (eventName.contains(keyword) || eventDescription.contains(keyword)){
                this.eventByKeyword.add(this.eventsAll.get(i));
            }
        }
        return this.eventByKeyword;
    }

    public ArrayList<Event> getEventByCategory(String category){
        ArrayList<Event> eventArrayList = new ArrayList<Event>();
        for(int i=0; i<this.eventsAll.size(); i++){
            if (this.eventsAll.get(i).getCategory().equals(category)){
                eventArrayList.add(this.eventsAll.get(i));
            }
        }
        return eventArrayList;
    }

    public ArrayList<Event> getEventByDateRange(Date startDate, Date endDate){
        ArrayList<Event> eventArrayList = new ArrayList<>();
        for(int i=0; i<this.eventsAll.size(); i++){
            Date eventDate = this.eventsAll.get(i).getStartDate();

            boolean eventDateAfterStartDate = eventDate.compareTo(startDate) >= 0;
            boolean eventDateBeforeEndDate = eventDate.compareTo(endDate) <= 0;

            if (eventDateAfterStartDate && eventDateBeforeEndDate){
                eventArrayList.add(this.eventsAll.get(i));
            }
        }
        return eventArrayList;
    }

    public ArrayList<Event> getSortedEvents(){
        return eventsAll;
    }

}
