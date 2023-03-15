package com.eventmanagement.EventManagement.model.interfaces;

import com.eventmanagement.EventManagement.DataAccessObjects.interfaces.IEventListDAO;

public interface IEventSortFactory {

    public IEventSort makeEventSort(IEventListDAO eventListDAO, int userId);

}
