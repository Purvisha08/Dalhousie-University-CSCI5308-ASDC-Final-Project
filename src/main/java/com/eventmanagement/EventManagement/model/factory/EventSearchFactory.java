package com.eventmanagement.EventManagement.model.factory;

import com.eventmanagement.EventManagement.DataAccessObjects.interfaces.IEventListDAO;
import com.eventmanagement.EventManagement.model.EventSearch;
import com.eventmanagement.EventManagement.model.interfaces.IEventSearch;
import com.eventmanagement.EventManagement.model.interfaces.IEventSearchFactory;

public class EventSearchFactory implements IEventSearchFactory {

    private static EventSearchFactory singleInstance = null;

    public static EventSearchFactory getInstance(){
        if (singleInstance == null){
            singleInstance = new EventSearchFactory();
        }
        return singleInstance;
    }

    @Override
    public IEventSearch getEventSearch(int userId){
        return new EventSearch(userId);
    }

    @Override
    public IEventSearch getEventSearch(IEventListDAO eventListDAO, int userID){
        return new EventSearch(eventListDAO, userID);
    }

}
