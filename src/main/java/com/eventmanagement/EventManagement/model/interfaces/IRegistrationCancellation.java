package com.eventmanagement.EventManagement.model.interfaces;

public interface IRegistrationCancellation
{
    public void loadDetails(int registrationId);
    public boolean cancel(int registrationId);
}
