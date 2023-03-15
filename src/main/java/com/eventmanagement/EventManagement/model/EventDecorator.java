package com.eventmanagement.EventManagement.model;

import java.util.Date;

import com.eventmanagement.EventManagement.DataAccessObjects.interfaces.IEventDAO;
import com.eventmanagement.EventManagement.model.interfaces.IEvent;

public class EventDecorator extends Event {

    private Event decoratedEvent;

    public EventDecorator(IEvent event) {
        super();
        this.decoratedEvent = (Event) event;
    }

    // getters and setters
    @Override
    public Integer getId() {
        return this.decoratedEvent.getId();
    }

    @Override
    public void setId(Integer id) {
        this.decoratedEvent.setId(id);
    }

    @Override
    public Integer getFestivalId() {
        return this.decoratedEvent.getFestivalId();
    }

    @Override
    public void setFestivalId(Integer festival_id) {
        this.decoratedEvent.setFestivalId(festival_id);
    }

    @Override
    public String getName() {
        return this.decoratedEvent.getName();
    }

    @Override
    public void setName(String name) {
        this.decoratedEvent.setName(name);;
    }

    @Override
    public String getDescription() {
        return this.decoratedEvent.getDescription();
    }

    @Override
    public void setDescription(String description) {
        this.decoratedEvent.setDescription(description);;
    }

    @Override
    public String getCategory() {
        return this.decoratedEvent.getCategory();
    }

    @Override
    public void setCategory(String Category) {
        this.decoratedEvent.setCategory(Category);;
    }

    @Override
    public String getVenue() {
        return this.decoratedEvent.getVenue();
    }

    @Override
    public void setVenue(String Venue) {
        this.decoratedEvent.setVenue(Venue);;
    }

    @Override
    public Date getStartDate() {
        return this.decoratedEvent.getStartDate();
    }

    @Override
    public void setStartDate(Date startDate) {
        this.decoratedEvent.setStartDate(startDate);;
    }

    @Override
    public Date getEndDate() {
        return this.decoratedEvent.getEndDate();
    }

    @Override
    public void setEndDate(Date endDate) {
        this.decoratedEvent.setEndDate(endDate);;
    }

    @Override
    public Integer getPrice() {
        return this.decoratedEvent.getPrice();
    }

    @Override
    public void setPrice(Integer price) {
        this.decoratedEvent.setPrice(price);;
    }

    @Override
    public Integer getCapacity() {
        return this.decoratedEvent.getCapacity();
    }

    @Override
    public void setCapacity(Integer Capacity) {
        this.decoratedEvent.setCapacity(Capacity);;
    }

    @Override
    public Boolean getCompleted() {
        return this.decoratedEvent.getCompleted();
    }

    @Override
    public void setCompleted(Boolean Completed) {
        this.decoratedEvent.setCompleted(Completed);;
    }

    @Override
    public Integer getRegisterCount() {
        return this.decoratedEvent.getRegisterCount();
    }

    @Override
    public void setRegisterCount(Integer RegisterCount) {
        this.decoratedEvent.setRegisterCount(RegisterCount);;
    }

    @Override
    public Integer getWaitListCount() {
        return this.decoratedEvent.getWaitListCount();
    }

    @Override
    public void setWaitListCount(Integer WaitListCount) {
        this.decoratedEvent.setWaitListCount(WaitListCount);;
    }

    @Override
    public Integer getWinner() {
        return this.decoratedEvent.getWinner();
    }

    @Override
    public void setWinner(Integer winner) {
        this.decoratedEvent.setWinner(winner);;
    }

    @Override
    public Integer getFirstRunnerUp() {
        return this.decoratedEvent.getFirstRunnerUp();
    }

    @Override
    public void setFirstRunnerUp(Integer firstRunnerUp) {
        this.decoratedEvent.setFirstRunnerUp(firstRunnerUp);;
    }

    @Override
    public Integer getSecondRunnerUp() {
        return this.decoratedEvent.getSecondRunnerUp();
    }

    @Override
    public void setSecondRunnerUp(Integer secondRunnerUp) {
        this.decoratedEvent.setSecondRunnerUp(secondRunnerUp);;
    }

    //operations
    @Override
    public Event update (IEventDAO eventDAO) {
        return this.decoratedEvent.update(eventDAO);
    }
}
