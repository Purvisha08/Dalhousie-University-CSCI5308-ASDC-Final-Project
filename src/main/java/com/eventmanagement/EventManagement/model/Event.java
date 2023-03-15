package com.eventmanagement.EventManagement.model;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.BindingResult;

import com.eventmanagement.EventManagement.DataAccessObjects.interfaces.IEventDAO;
import com.eventmanagement.EventManagement.model.interfaces.IEvent;
import com.eventmanagement.EventManagement.model.interfaces.IEventRegistration;
import com.eventmanagement.EventManagement.validator.EventValidator;

public class Event implements IEvent {

    // attributes
    private Integer id;
    private Integer festival_id;
    private String name;
    private String description;
    private String category;
    private String venue;
    
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    private Integer price;
    private Integer capacity;
    private Boolean completed;
    private Integer registerCount;
    private Integer waitListCount;
    private Integer winner;
    private Integer firstRunnerUp;
    private Integer secondRunnerUp;

    private Student winnerStudent;
    private Student firstRunnerUpStudent;
    private Student secondRunnerUpStudent;
    private Double rating;

    // getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFestivalId() {
        return this.festival_id;
    }

    public void setFestivalId(Integer festival_id) {
        this.festival_id = festival_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String Category) {
        this.category = Category;
    }

    public String getVenue() {
        return this.venue;
    }

    public void setVenue(String Venue) {
        this.venue = Venue;
    }

    public Date getStartDate() {
        return this.startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return this.endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getPrice() {
        return this.price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getCapacity() {
        return this.capacity;
    }

    public void setCapacity(Integer Capacity) {
        this.capacity = Capacity;
    }

    public Boolean getCompleted() {
        if(this.completed == null) {
            return false;
        }
        return this.completed;
    }

    public void setCompleted(Boolean Completed) {
        this.completed = Completed;
    }

    public Integer getRegisterCount() {
        return this.registerCount;
    }

    public void setRegisterCount(Integer RegisterCount) {
        this.registerCount = RegisterCount;
    }

    public Integer getWaitListCount() {
        return this.waitListCount;
    }

    public void setWaitListCount(Integer WaitListCount) {
        this.waitListCount = WaitListCount;
    }

    public Integer getWinner() {
        return this.winner;
    }

    public void setWinner(Integer winner) {
        this.winner = winner;
    }

    public Integer getFirstRunnerUp() {
        return this.firstRunnerUp;
    }

    public void setFirstRunnerUp(Integer firstRunnerUp) {
        this.firstRunnerUp = firstRunnerUp;
    }

    public Integer getSecondRunnerUp() {
        return this.secondRunnerUp;
    }

    public void setSecondRunnerUp(Integer secondRunnerUp) {
        this.secondRunnerUp = secondRunnerUp;
    }

    public Student getWinnerStudent() {
        return this.winnerStudent;
    }

    public void setWinnerStudent(Student winnerStudent) {
        this.winnerStudent = winnerStudent;
    }

    public Student getFirstRunnerUpStudent() {
        return this.firstRunnerUpStudent;
    }

    public void setFirstRunnerUpStudent(Student firstRunnerUpStudent) {
        this.firstRunnerUpStudent = firstRunnerUpStudent;
    }

    public Student getSecondRunnerUpStudent() {
        return this.secondRunnerUpStudent;
    }

    public void setSecondRunnerUpStudent(Student secondRunnerUpStudent) {
        this.secondRunnerUpStudent = secondRunnerUpStudent;
    }

    public Double getRating() {
        return this.rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    // operations
    public static void validateEventData(EventValidator eventValidator, Event eventData, BindingResult bindingResult) {
        eventValidator.validate(eventData, bindingResult);
    }

    public static List<Event> findByFestivalId(IEventDAO eventDAO, Integer festival_id) {
        return eventDAO.findByFestivalId(festival_id);
    }

    public static Event findById(IEventDAO eventDAO, Integer id) {
        Optional<Event> event = eventDAO.findById(id);
        if(event.isPresent()) {
            return event.get();
        } else {
            return null;
        }
    }

    public static Event create (IEventDAO eventDAO, Event eventData) {
        Integer result = eventDAO.addEvent(eventData);
        Event newEvent = null;
        if(!result.equals(0)) {
            newEvent = eventDAO.findById(result).get();
        }
        return newEvent;
    }

    public Event update (IEventDAO eventDAO) {
        Integer result = eventDAO.updateEvent(this);
        Event updatedEvent = null;
        if(!result.equals(0)) {
            updatedEvent = eventDAO.findById(this.id).get();
        }
        return updatedEvent;
    }

    public Boolean delete (IEventDAO eventDAO) {
        Integer result = eventDAO.deleteEvent(this.id);
        return result == 1;
    }

    public List<IEventRegistration> findRegistrations(IEventDAO eventDAO) {
        return eventDAO.findRegistrationsById(this.getId());
    }

    public Boolean updateRating(IEventDAO eventDAO) {
        List<IEventRegistration> result = this.findRegistrations(eventDAO);
        Integer totalRating = 0;
        for (IEventRegistration reg : result) {
            EventRegistration eventRegistration = (EventRegistration) reg;
            totalRating += eventRegistration.getRating();
        }

        Double eventRating = (double) totalRating/result.size();
        this.setRating(eventRating);
        Integer updated = eventDAO.updateRating(this);
        return updated == 1;
    }
}
