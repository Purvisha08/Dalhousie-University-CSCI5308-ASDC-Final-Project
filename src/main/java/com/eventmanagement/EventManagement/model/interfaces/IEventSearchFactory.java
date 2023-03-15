package com.eventmanagement.EventManagement.model.interfaces;

import com.eventmanagement.EventManagement.DataAccessObjects.interfaces.IEventListDAO;

public interface IEventSearchFactory {
    public IEventSearch getEventSearch(int userId);
    public IEventSearch getEventSearch(IEventListDAO eventListDAO, int userID);

}
