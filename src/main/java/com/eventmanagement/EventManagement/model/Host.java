package com.eventmanagement.EventManagement.model;

import java.util.List;
import java.util.Optional;

import com.eventmanagement.EventManagement.DataAccessObjects.interfaces.IHostDAO;

public class Host extends User
{
    private Double rating;

    //gettters and setters
    public Double getRating() {
        return this.rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    // operations
    public static Host findByEmail(IHostDAO hostDAO, String email) {
        Optional<Host> host = hostDAO.findByEmail(email);
        if(host.isPresent()) {
            return host.get();
        } else {
            return null;
        }
    }

    public static Host findById(IHostDAO hostDAO, Integer id) {
        Optional<Host> host = hostDAO.findById(id);
        if(host.isPresent()) {
            return host.get();
        } else {
            return null;
        }
    }

    public Boolean updateRating(IHostDAO hostDAO) {
        List<Event> result = hostDAO.findAllEvents(this.getId());
        Double totalRating = 0.0;
        for (Event event : result) {
            Event castedEvent = (Event) event;
            totalRating += castedEvent.getRating();
        }

        Double eventRating = (double) totalRating/result.size();
        this.setRating(eventRating);
        Integer updated = hostDAO.updateRating(this);
        return updated == 1;
    }

}
