package com.eventmanagement.EventManagement.model;

import com.eventmanagement.EventManagement.DataAccessObjects.EventDAOMock;
import com.eventmanagement.EventManagement.DataAccessObjects.EventRegistrationDAOMock;
import com.eventmanagement.EventManagement.model.factory.EventFactory;
import com.eventmanagement.EventManagement.model.interfaces.IEventRegistration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@SpringBootTest
public class EventRegistrationTest {

    private EventRegistrationDAOMock eventRegistrationDAO;
    private EventDAOMock eventDAO;

    public EventRegistrationTest()
    {
        eventRegistrationDAO = new EventRegistrationDAOMock();
        eventDAO = new EventDAOMock();
    }

    @Test
    public void setDiscountDetailsWhenFirstRegistrationTest() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        Event event = EventFactory.instance().makeEvent();
        event.setId(1);
        event.setName("Event 1");
        event.setDescription("Some description for event 1");
        event.setCategory("Hackathon");
        event.setVenue("DAL");
        event.setCompleted(false);
        event.setPrice(100);
        event.setCapacity(100);
        event.setFestivalId(1);
        calendar.add(Calendar.DATE, 5);
        event.setStartDate(calendar.getTime());
        calendar.add(Calendar.DATE, 2);
        event.setEndDate(calendar.getTime());
        EventRegistration eventRegistration = new EventRegistration(eventRegistrationDAO, eventDAO);
        eventRegistration.setStudentId(1);
        eventRegistration.setEventId(1);
        eventRegistration.setDiscountDetails(event);
        Assertions.assertEquals(20, eventRegistration.getDiscountPercentage());
        Assertions.assertEquals(80, eventRegistration.getFinalPrice());
    }

    @Test
    public void setDiscountDetailsWhenRegularRegistrationButNoDiscountTest() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        Event event = EventFactory.instance().makeEvent();
        event.setId(1);
        event.setName("Event 1");
        event.setDescription("Some description for event 1");
        event.setCategory("Hackathon");
        event.setVenue("DAL");
        event.setCompleted(false);
        event.setPrice(100);
        event.setCapacity(100);
        event.setFestivalId(1);
        calendar.add(Calendar.DATE, 5);
        event.setStartDate(calendar.getTime());
        calendar.add(Calendar.DATE, 2);
        event.setEndDate(calendar.getTime());
        EventRegistration eventRegistration = new EventRegistration(eventRegistrationDAO, eventDAO);
        eventRegistration.setStudentId(2);
        eventRegistration.setEventId(2);
        eventRegistration.setDiscountDetails(event);
        Assertions.assertEquals(0,eventRegistration.getDiscountPercentage());
        Assertions.assertEquals(100, eventRegistration.getFinalPrice());
    }

    @Test
    public void setDiscountDetailsWhenRegularRegistrationButOnePercentDiscountTest() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        Event event = EventFactory.instance().makeEvent();
        event.setId(1);
        event.setName("Event 1");
        event.setDescription("Some description for event 1");
        event.setCategory("Hackathon");
        event.setVenue("DAL");
        event.setCompleted(false);
        event.setPrice(100);
        event.setCapacity(100);
        event.setFestivalId(1);
        calendar.add(Calendar.DATE, 15);
        event.setStartDate(calendar.getTime());
        calendar.add(Calendar.DATE, 2);
        event.setEndDate(calendar.getTime());
        EventRegistration eventRegistration = new EventRegistration(eventRegistrationDAO, eventDAO);
        eventRegistration.setStudentId(2);
        eventRegistration.setEventId(2);
        eventRegistration.setDiscountDetails(event);
        Assertions.assertEquals(1, eventRegistration.getDiscountPercentage());
        Assertions.assertEquals(99, eventRegistration.getFinalPrice());
    }

    @Test
    public void setDiscountDetailsWhenRegularRegistrationButFivePercentDiscountTest() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        Event event = EventFactory.instance().makeEvent();
        event.setId(1);
        event.setName("Event 1");
        event.setDescription("Some description for event 1");
        event.setCategory("Hackathon");
        event.setVenue("DAL");
        event.setCompleted(false);
        event.setPrice(100);
        event.setCapacity(100);
        event.setFestivalId(1);
        calendar.add(Calendar.DATE, 55);
        event.setStartDate(calendar.getTime());
        calendar.add(Calendar.DATE, 2);
        event.setEndDate(calendar.getTime());
        EventRegistration eventRegistration = new EventRegistration(eventRegistrationDAO, eventDAO);
        eventRegistration.setStudentId(2);
        eventRegistration.setEventId(2);
        eventRegistration.setDiscountDetails(event);
        Assertions.assertEquals(5, eventRegistration.getDiscountPercentage());
        Assertions.assertEquals(95, eventRegistration.getFinalPrice());
    }

    @Test
    public void getRegistrationDetailByIdValidTest() {
        int registrationId = 1;
        Optional<IEventRegistration> eventRegistration = eventRegistrationDAO.getRegistrationById(registrationId);
        Assertions.assertEquals(true, eventRegistration.isPresent());
    }

    @Test
    public void getRegistrationDetailByIdInvalidTest() {
        int registrationId = 10;
        Optional<IEventRegistration> eventRegistration = eventRegistrationDAO.getRegistrationById(registrationId);
        Assertions.assertEquals(false, eventRegistration.isPresent());
    }

    @Test
    public void getRegistrationDetailByStudentAndEventIdValidTest()
    {
        int studentId = 1;
        int eventId = 2;
        Optional<IEventRegistration> eventRegistration = eventRegistrationDAO.getRegistrationByStudentAndEventId(studentId,eventId);
        Assertions.assertEquals(true, eventRegistration.isPresent());
    }

    @Test
    public void getRegistrationDetailByStudentAndEventIdInvalidTest()
    {
        int studentId = 10;
        int eventId = 5;
        Optional<IEventRegistration> eventRegistration = eventRegistrationDAO.getRegistrationByStudentAndEventId(studentId,eventId);
        Assertions.assertEquals(false, eventRegistration.isPresent());
    }

}
