package com.eventmanagement.EventManagement.model;

import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import com.eventmanagement.EventManagement.DataAccessObjects.EventDAOMock;
import com.eventmanagement.EventManagement.DataAccessObjects.interfaces.IEventDAO;
import com.eventmanagement.EventManagement.model.factory.EventFactory;


public class EventTest {

    @Test
    public void getEventByIdTest() throws Exception {
		try {
			IEventDAO eventDao = new EventDAOMock();
			Event event1= Event.findById(eventDao, 11);
			Assertions.assertNotNull(event1);
            Assertions.assertEquals(event1.getName(), "Event 1");
            Assertions.assertEquals(event1.getDescription(), "Event 1 description");
            Assertions.assertEquals(event1.getCategory(),"Hackathon");
            Assertions.assertEquals(event1.getVenue(), "DAL");
            Assertions.assertEquals(event1.getCompleted(), false);
            Assertions.assertEquals(event1.getPrice(), 100);
            Assertions.assertEquals(event1.getCapacity(), 100);
            Assertions.assertEquals(event1.getFestivalId(), 1);

            Event event2 = Event.findById(eventDao, 20);
            Assertions.assertNull(event2);

		} catch (Exception e) {
			Assertions.fail("Test 'GetEventById' failed:" + e.getMessage());
		}
    }

    @Test
    public void getEventByFestivalIdTest() throws Exception {
        Event event = EventFactory.instance().makeEvent();
        event.setId(11);
        event.setFestivalId(1);
		try {
			IEventDAO eventDao = new EventDAOMock();
			List<Event> events= Event.findByFestivalId(eventDao, 1);
			Assertions.assertNotNull(events);
            Assertions.assertEquals(1, events.size());

            List<Event> eventList2 = Event.findByFestivalId(eventDao, 2);
            Assertions.assertNotNull(eventList2);
            Assertions.assertEquals(0, eventList2.size());

		} catch (Exception e) {
			Assertions.fail("Test 'GetEventByFestivalId' failed:" + e.getMessage());
		}
    }

    @Test
    public void updateEventTest() throws Exception {
        Event event = EventFactory.instance().makeEvent();
        event.setId(10);
        event.setName("Update Event");
		try {
			IEventDAO eventDao = new EventDAOMock();
			Event event1 = event.update(eventDao);
			Assertions.assertNotNull(event1);
            Assertions.assertEquals(event1.getName(), "Updated Event");
            Assertions.assertEquals(event1.getDescription(), "New Description");
            Assertions.assertEquals(event1.getCategory(),"New Category");
            Assertions.assertEquals(event1.getVenue(), "New Venue");

            event.setId(2);
            event.setName("Don't Update Event");
            Event event2 = event.update(eventDao);
            Assertions.assertNull(event2);

		} catch (Exception e) {
			Assertions.fail("Test 'UpdateEventTest' failed:" + e.getMessage());
		}
    }

    @Test
    public void deleteEventTest() throws Exception {
        Event event = EventFactory.instance().makeEvent();
        event.setId(11);
        event.setName("Delete Event");
		try {
			IEventDAO eventDao = new EventDAOMock();
			Boolean result1 = event.delete(eventDao);
			Assertions.assertNotNull(result1);
            Assertions.assertTrue(result1);

            event.setId(2);
            event.setName("Don't Delete Event");
            Boolean result2 = event.delete(eventDao);
            Assertions.assertFalse(result2);;

		} catch (Exception e) {
			Assertions.fail("Test 'DeleteEventTest' failed:" + e.getMessage());
		}
    }
}
