package com.eventmanagement.EventManagement.DataAccessObjects;

import com.eventmanagement.EventManagement.DataAccessObjects.interfaces.IWaitlistDAO;
import com.eventmanagement.EventManagement.model.Waitlist;
import com.eventmanagement.EventManagement.model.interfaces.IWaitlist;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class WaitlistDAOMock implements IWaitlistDAO
{

    @Override
    public boolean addWaitlist(int eventId, int studentId) {
        return true;
    }

    @Override
    public List<IWaitlist> getWaitlistForStudent(Integer studentId)
    {
        List<IWaitlist> waitlistArrayList = new ArrayList<>();
        Waitlist waitlist = new Waitlist();
        waitlist.setCreatedAt(new Date());
        waitlist.setEventStartDate(new Date());
        waitlist.setEventId(1);
        waitlist.setStudentId(studentId);
        waitlist.setEventCapacity(10);
        waitlist.setEventRegisterCount(12);
        waitlist.setStudentName("Student name1");
        waitlist.setEventName("Event name1");
        waitlistArrayList.add(waitlist);
        Waitlist waitlist2 = new Waitlist();
        waitlist2.setCreatedAt(new Date());
        waitlist2.setEventStartDate(new Date());
        waitlist2.setEventId(2);
        waitlist2.setStudentId(studentId);
        waitlist2.setEventCapacity(10);
        waitlist2.setEventRegisterCount(12);
        waitlist2.setStudentName("Student name2");
        waitlist2.setEventName("Event name2");
        waitlistArrayList.add(waitlist2);
        return waitlistArrayList;
    }

    @Override
    public List<IWaitlist> getWaitlistForEvent(Integer eventId) {
        List<IWaitlist> waitlistArrayList = new ArrayList<>();
        Waitlist waitlist = new Waitlist();
        waitlist.setCreatedAt(new Date());
        waitlist.setEventStartDate(new Date());
        waitlist.setEventId(eventId);
        waitlist.setStudentId(1);
        waitlist.setEventCapacity(10);
        waitlist.setEventRegisterCount(12);
        waitlist.setStudentName("Student name1");
        waitlist.setEventName("Event name1");
        waitlistArrayList.add(waitlist);
        Waitlist waitlist2 = new Waitlist();
        waitlist2.setCreatedAt(new Date());
        waitlist2.setEventStartDate(new Date());
        waitlist2.setEventId(eventId);
        waitlist2.setStudentId(2);
        waitlist2.setEventCapacity(10);
        waitlist2.setEventRegisterCount(12);
        waitlist2.setStudentName("Student name2");
        waitlist2.setEventName("Event name2");
        waitlistArrayList.add(waitlist2);
        return waitlistArrayList;
    }

    @Override
    public boolean deleteWaitlist(int eventId, int studentId)
    {
        if(eventId == 5 && studentId == 10)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

}