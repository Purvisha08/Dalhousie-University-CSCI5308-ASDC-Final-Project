package com.eventmanagement.EventManagement.model;

import com.eventmanagement.EventManagement.DataAccessObjects.WaitlistDAOMock;
import com.eventmanagement.EventManagement.DataAccessObjects.interfaces.IWaitlistDAO;
import com.eventmanagement.EventManagement.model.interfaces.IWaitlist;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;

@SpringBootTest
public class WaitlistTest
{
    @Test
    public void getWailistForStudentTest()
    {
        int studentId = 1;
        IWaitlistDAO waitlistDAO = new WaitlistDAOMock();
        Waitlist waitlist = new Waitlist(waitlistDAO);
        List<IWaitlist> waitlistArrayList = waitlist.getWaitlistForStudent(studentId);
        Assertions.assertEquals(waitlistArrayList.size(),2);
        Waitlist waitlist1 = (Waitlist) waitlistArrayList.get(0);
        Assertions.assertEquals(studentId,waitlist1.getStudentId());
        Assertions.assertEquals(1,waitlist1.getEventId());
        Assertions.assertEquals("Event name1",waitlist1.getEventName());
        Waitlist waitlist2 = (Waitlist) waitlistArrayList.get(1);
        Assertions.assertEquals(studentId, waitlist2.getStudentId());
        Assertions.assertEquals( 2,waitlist2.getEventId());
        Assertions.assertEquals("Event name2",waitlist2.getEventName());
    }

    @Test
    public void getWailistForEventTest()
    {
        int eventId = 1;
        IWaitlistDAO waitlistDAO = new WaitlistDAOMock();
        Waitlist waitlist = new Waitlist(waitlistDAO);
        List<IWaitlist> waitlistArrayList = waitlist.getWaitlistForEvent(eventId);
        Assertions.assertEquals(waitlistArrayList.size(),2);
        Waitlist waitlist1 = (Waitlist) waitlistArrayList.get(0);
        Assertions.assertEquals(1,waitlist1.getStudentId());
        Assertions.assertEquals(eventId, waitlist1.getEventId());
        Assertions.assertEquals("Student name1", waitlist1.getStudentName());
        Waitlist waitlist2 = (Waitlist) waitlistArrayList.get(1);
        Assertions.assertEquals(2, waitlist2.getStudentId());
        Assertions.assertEquals(eventId, waitlist2.getEventId() );
        Assertions.assertEquals("Student name2", waitlist2.getStudentName());
    }

    @Test
    public void deleteSuccessTest()
    {
        int eventId = 1;
        int studentId = 2;
        IWaitlistDAO waitlistDAO = new WaitlistDAOMock();
        Waitlist waitlist = new Waitlist(waitlistDAO);
        Assertions.assertEquals(true, waitlist.delete(eventId,studentId));
    }

    @Test
    public void deleteFailTest()
    {
        int eventId = 5;
        int studentId = 10;
        IWaitlistDAO waitlistDAO = new WaitlistDAOMock();
        Waitlist waitlist = new Waitlist(waitlistDAO);
        Assertions.assertEquals(false, waitlist.delete(eventId,studentId));
    }

}
