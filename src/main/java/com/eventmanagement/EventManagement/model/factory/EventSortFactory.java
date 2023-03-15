package com.eventmanagement.EventManagement.model.factory;

import com.eventmanagement.EventManagement.DataAccessObjects.interfaces.IEventListDAO;
import com.eventmanagement.EventManagement.model.EventSort;
import com.eventmanagement.EventManagement.model.interfaces.IEventSort;
import com.eventmanagement.EventManagement.model.interfaces.IEventSortFactory;

public class EventSortFactory implements IEventSortFactory {
    private static EventSortFactory singleInstance = null;

    public static EventSortFactory getInstance(){
        if (singleInstance == null){
            singleInstance = new EventSortFactory();
        }
        return singleInstance;
    }

    @Override
    public IEventSort makeEventSort(IEventListDAO eventListDAO, int userId){
        return new EventSort(eventListDAO, userId);
    }

}
