package com.eventmanagement.EventManagement.model.interfaces;

import java.util.List;

public interface IWaitlist
{
    public boolean addWaitlist(int eventId, int studentId);
    public List<IWaitlist> getWaitlistForStudent(int studentId);
    public List<IWaitlist> getWaitlistForEvent(int eventId);
    public boolean delete(int eventId, int studentId);
}
