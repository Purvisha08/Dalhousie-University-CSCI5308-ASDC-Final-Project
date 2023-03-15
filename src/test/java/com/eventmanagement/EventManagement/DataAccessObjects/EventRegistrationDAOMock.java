package com.eventmanagement.EventManagement.DataAccessObjects;

import com.eventmanagement.EventManagement.DataAccessObjects.interfaces.IEventRegistrationDAO;
import com.eventmanagement.EventManagement.model.EventRegistration;
import com.eventmanagement.EventManagement.model.interfaces.IEventRegistration;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class EventRegistrationDAOMock implements IEventRegistrationDAO
{
    @Override
    public List<IEventRegistration> getAllRegistrationByStudentId(Integer studentId)
    {
        List<IEventRegistration> eventRegistrationsList = new ArrayList<>();
        if(studentId == 1)
        {
            return eventRegistrationsList;
        }
        else
        {
            EventRegistration eventRegistration1 = new EventRegistration();
            eventRegistration1.setRegistrationId(1);
            eventRegistration1.setEventId(2);
            eventRegistration1.setStudentId(studentId);
            eventRegistration1.setRegistrationDate(new Date());
            eventRegistration1.setStatus("REGISTERED");
            eventRegistration1.setEventName("Event 1");
            eventRegistration1.setFinalPrice(100.0);
            eventRegistration1.setStudentName("Alex Hales");
            eventRegistration1.setEventStartDate(new Date());
            eventRegistrationsList.add(eventRegistration1);

            EventRegistration eventRegistration2 = new EventRegistration();
            eventRegistration2.setRegistrationId(2);
            eventRegistration2.setEventId(2);
            eventRegistration1.setStudentId(studentId);
            eventRegistration2.setRegistrationDate(new Date());
            eventRegistration2.setStatus("REGISTERED");
            eventRegistration2.setEventName("Event 2");
            eventRegistration2.setFinalPrice(200.0);
            eventRegistration2.setStudentName("Alex Hales");
            eventRegistration2.setEventStartDate(new Date());
            eventRegistrationsList.add(eventRegistration2);

            return eventRegistrationsList;
        }
    }

    @Override
    public List<IEventRegistration> findByEventId(Integer eventId)
    {
        List<IEventRegistration> eventRegistrationsList = new ArrayList<>();
        if(eventId == 1)
        {
            return eventRegistrationsList;
        }
        else
        {
            EventRegistration eventRegistration1 = new EventRegistration();
            eventRegistration1.setRegistrationId(1);
            eventRegistration1.setEventId(eventId);
            eventRegistration1.setStudentId(2);
            eventRegistration1.setRegistrationDate(new Date());
            eventRegistration1.setStatus("REGISTERED");
            eventRegistration1.setEventName("Event 1");
            eventRegistration1.setFinalPrice(100.0);
            eventRegistration1.setStudentName("Alex Hales");
            eventRegistration1.setEventStartDate(new Date());
            eventRegistrationsList.add(eventRegistration1);

            EventRegistration eventRegistration2 = new EventRegistration();
            eventRegistration2.setRegistrationId(2);
            eventRegistration2.setEventId(eventId);
            eventRegistration1.setStudentId(3);
            eventRegistration2.setRegistrationDate(new Date());
            eventRegistration2.setStatus("REGISTERED");
            eventRegistration2.setEventName("Event 1");
            eventRegistration2.setFinalPrice(200.0);
            eventRegistration2.setStudentName("Jason Roy");
            eventRegistration2.setEventStartDate(new Date());
            eventRegistrationsList.add(eventRegistration2);

            return eventRegistrationsList;
        }
    }

    @Override
    public boolean addRegistration(int eventId, int studentId, Date registrationDate, String description, Double price) {
        return true;
    }

    @Override
    public boolean cancelRegistration(int registrationId, double refundPrice, String description)
    {
        if(registrationId==5)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    @Override
    public Optional<IEventRegistration> getRegistrationById(Integer registrationId)
    {
        if(registrationId == 10)
        {
            return Optional.empty();
        }

        EventRegistration eventRegistration = new EventRegistration();
        eventRegistration.setRegistrationId(1);
        eventRegistration.setEventId(2);
        eventRegistration.setRegistrationDate(new Date());
        eventRegistration.setStatus("REGISTERED");
        eventRegistration.setEventName("Event 1");
        eventRegistration.setFinalPrice(100.0);
        eventRegistration.setStudentName("Alex Hales");
        eventRegistration.setEventStartDate(new Date());
        return Optional.of(eventRegistration);
    }

    @Override
    public Optional<IEventRegistration> getRegistrationByStudentAndEventId(Integer studentId, Integer eventId) {

        if(studentId == 10 && eventId == 5)
        {
            return Optional.empty();
        }

        EventRegistration eventRegistration = new EventRegistration();
        eventRegistration.setRegistrationId(1);
        eventRegistration.setEventId(5);
        eventRegistration.setRegistrationDate(new Date());
        eventRegistration.setStatus("REGISTERED");
        eventRegistration.setEventName("Event 1");
        eventRegistration.setFinalPrice(100.0);
        eventRegistration.setStudentName("Alex Hales");
        eventRegistration.setEventStartDate(new Date());
        return Optional.of(eventRegistration);
    }

    public int updateRegistration(EventRegistration registration) {
        if(registration.getStatus() == "REGISTERED") {
            return 1;
        } else {
            return 0;
        }
    };
}
