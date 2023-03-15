package com.eventmanagement.EventManagement.model;

import com.eventmanagement.EventManagement.DataAccessObjects.interfaces.IEventDAO;
import com.eventmanagement.EventManagement.model.interfaces.IEvent;

public class EventComplete extends EventDecorator {

    private String messageToParticipants;
    private Boolean declareWinners;

    public EventComplete(IEvent event){
        super(event);
    }

    public String getMessageToParticipants() {
        return this.messageToParticipants;
    }

    public void setMessageToParticipants(String messageToParticipants) {
        this.messageToParticipants = messageToParticipants;
    }

    public Boolean getDeclareWinners() {
        return this.declareWinners;
    }

    public void setDeclareWinners(Boolean declareWinners) {
        this.declareWinners = declareWinners;
    }

    public Event updateEvent (IEventDAO eventDAO) {
        return this.update(eventDAO);
    }

}
