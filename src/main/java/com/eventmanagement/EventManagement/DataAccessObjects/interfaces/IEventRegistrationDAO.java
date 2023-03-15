package com.eventmanagement.EventManagement.DataAccessObjects.interfaces;

import com.eventmanagement.EventManagement.model.EventRegistration;
import com.eventmanagement.EventManagement.model.interfaces.IEventRegistration;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface IEventRegistrationDAO
{
    public boolean addRegistration(int eventId, int studentId, Date registrationDate, String description, Double price);
    public boolean cancelRegistration(int registrationId, double refundPrice, String description);
    public List<IEventRegistration> getAllRegistrationByStudentId(Integer studentId);
    public List<IEventRegistration> findByEventId(Integer eventId);
    public Optional<IEventRegistration> getRegistrationById(Integer registrationId);
    public Optional<IEventRegistration> getRegistrationByStudentAndEventId(Integer studentId, Integer eventId);
    public int updateRegistration(EventRegistration registration);
}
