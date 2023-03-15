package com.eventmanagement.EventManagement.DataAccessObjects.interfaces;

import com.eventmanagement.EventManagement.model.Event;
import com.eventmanagement.EventManagement.model.Festival;
import java.util.List;
import java.util.Optional;

public interface IFestivalDAO
{
    public List<Festival> findByHostId(Integer host_id);
    public Optional<Festival> findById(int id);
    public List<Festival> filterBySearchQuery(Integer host_id, String searchQuery);
    public List<Event> findAllEvents(Integer festivalId);
    public Integer updateRating(Festival festival);
    public Integer updateFestival(Festival festival);
    public int addFestival(Festival festival);

}
