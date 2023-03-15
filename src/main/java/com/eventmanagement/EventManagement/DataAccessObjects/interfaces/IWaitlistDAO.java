package com.eventmanagement.EventManagement.DataAccessObjects.interfaces;

import com.eventmanagement.EventManagement.model.interfaces.IWaitlist;

import java.util.List;

public interface IWaitlistDAO
{
    public boolean addWaitlist(int eventId, int studentId);
    public List<IWaitlist> getWaitlistForStudent(Integer studentId);
    public List<IWaitlist> getWaitlistForEvent(Integer eventId);
    public boolean deleteWaitlist(int eventId, int studentId);
}
