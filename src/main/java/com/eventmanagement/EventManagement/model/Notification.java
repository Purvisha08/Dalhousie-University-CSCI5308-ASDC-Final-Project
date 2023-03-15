package com.eventmanagement.EventManagement.model;


import com.eventmanagement.EventManagement.DataAccessObjects.interfaces.IEventRegistrationDAO;
import com.eventmanagement.EventManagement.DataAccessObjects.interfaces.INotificationDAO;
import com.eventmanagement.EventManagement.DataAccessObjects.interfaces.IWaitlistDAO;
import com.eventmanagement.EventManagement.model.factory.MySQLDatabaseFactory;
import com.eventmanagement.EventManagement.model.interfaces.IEventRegistration;
import com.eventmanagement.EventManagement.model.interfaces.INotification;
import com.eventmanagement.EventManagement.model.interfaces.IWaitlist;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;


public class Notification implements INotification {

    private IWaitlistDAO waitlistDAO;
    private IEventRegistrationDAO eventRegistrationDAO;
    private INotificationDAO notificationDAO;
    private Integer notificationId;
    private Integer studentId;
    private String message;
    private Integer eventId;
    private LocalDateTime notifiedTime;
    private String eventName;

    public String getEventName() { return eventName; }
    public void setEventName(String eventName) { this.eventName = eventName; }
    public Integer getNotificationId() {
        return notificationId;
    }
    public void setNotificationId(Integer notificationId) {
        this.notificationId = notificationId;
    }
    public Integer getStudentId() {
        return studentId;
    }
    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }
    public String getMessage() { return message; }
    public void setMessage(String message) {
        this.message = message;
    }
    public LocalDateTime getNotifiedTime() {
        return notifiedTime;
    }
    public void setNotifiedTime(LocalDateTime notifiedTime) {
        this.notifiedTime = notifiedTime;
    }
    public Integer getEventId() {
        return eventId;
    }
    public void setEventId(Integer eventId) {this.eventId = eventId;}
    public String getNotifiedTimeDisplayText() {
        return this.notifiedTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
    public Notification() {
        this.eventRegistrationDAO = MySQLDatabaseFactory.instance().makeEventRegistrationDAO();
        this.waitlistDAO = MySQLDatabaseFactory.instance().makeWaitlistDAO();
        this.notificationDAO = MySQLDatabaseFactory.instance().makeNotificationDAO();
    }

    @Override
    public void sendNotification(ReceipientType receipientType, Integer eventId, String message) {
        List<Integer> studentIds = new ArrayList<>();
        if(receipientType == ReceipientType.Participants)
        {
            setStudentIdsOfParticipants(eventId, studentIds);
        }
        else if(receipientType == ReceipientType.Waitlist)
        {
            setWaitlistedStudentIdsForEvent(eventId, studentIds);
        }
        else if(receipientType == ReceipientType.All)
        {
            setStudentIdsOfParticipants(eventId, studentIds);
            setWaitlistedStudentIdsForEvent(eventId, studentIds);
        }
        this.notificationDAO.sendNotification(studentIds,message,eventId);
    }



    @Override
    public List<INotification> getNotificationForStudent(Integer studentId) {
        return this.notificationDAO.getNotifications(studentId);
    }

    @Override
    public void sendNotification(Integer studentId, String message, Integer eventId) {
        List<Integer> studentIdList = new ArrayList<>();
        studentIdList.add(studentId);
        this.notificationDAO.sendNotification(studentIdList,message,eventId);
    }

    private void setStudentIdsOfParticipants(Integer eventId, List<Integer> studentIds)
    {
        List<IEventRegistration> participants= this.eventRegistrationDAO.findByEventId(eventId);
        ListIterator<IEventRegistration> eventRegistrationListIterator = participants.listIterator();
        while (eventRegistrationListIterator.hasNext())
        {
            EventRegistration eventRegistration = (EventRegistration) eventRegistrationListIterator.next();
            studentIds.add(eventRegistration.getStudentId());
        }
    }

    private void setWaitlistedStudentIdsForEvent(Integer eventId, List<Integer> studentIds)
    {
        List<IWaitlist> waitlistStudents= this.waitlistDAO.getWaitlistForEvent(eventId);
        ListIterator<IWaitlist> waitlistListIterator = waitlistStudents.listIterator();
        while (waitlistListIterator.hasNext())
        {
            Waitlist waitlist = (Waitlist) waitlistListIterator.next();
            studentIds.add(waitlist.getStudentId());
        }
    }

}
