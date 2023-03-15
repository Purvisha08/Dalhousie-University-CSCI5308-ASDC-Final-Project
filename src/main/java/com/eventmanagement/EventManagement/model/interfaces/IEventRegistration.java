package com.eventmanagement.EventManagement.model.interfaces;

import com.eventmanagement.EventManagement.model.*;
import java.util.List;

public interface IEventRegistration
{
    public boolean register(int eventId, int studentId);
    public int getPreviousRegistrationCount();
    public List<IEventRegistration> getPreviousRegistrations(int studentId);
    public List<IEventRegistration> findByEventId(int eventId);
    public IEventRegistration getRegistrationDetailById( int registrationId);
    public IEventRegistration getRegistrationDetailByStudentAndEventId(int studentId, int eventId);
    public void setDetails(int eventId, int studentId);
}
