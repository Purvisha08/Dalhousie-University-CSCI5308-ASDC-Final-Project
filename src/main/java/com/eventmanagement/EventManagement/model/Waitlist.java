package com.eventmanagement.EventManagement.model;

import com.eventmanagement.EventManagement.DataAccessObjects.interfaces.IWaitlistDAO;
import com.eventmanagement.EventManagement.model.interfaces.IWaitlist;
import java.util.Date;
import java.util.List;

public class Waitlist implements IWaitlist
{
    private Integer eventId;
    private Integer studentId;
    private String eventName;
    private String studentName;
    private Integer eventCapacity;
    private Integer eventRegisterCount;
    private Date eventStartDate;
    private Date createdAt;
    private IWaitlistDAO waitlistDAO;

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Integer getEventCapacity() {
        return eventCapacity;
    }

    public void setEventCapacity(Integer eventCapacity) {
        this.eventCapacity = eventCapacity;
    }

    public Integer getEventRegisterCount() {
        return eventRegisterCount;
    }

    public void setEventRegisterCount(Integer eventRegisterCount) {
        this.eventRegisterCount = eventRegisterCount;
    }

    public Date getEventStartDate() {
        return eventStartDate;
    }

    public void setEventStartDate(Date eventStartDate) {
        this.eventStartDate = eventStartDate;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Waitlist(){}

    public Waitlist(IWaitlistDAO waitlistDAO)
    {
        this.waitlistDAO = waitlistDAO;
    }

    public boolean addWaitlist(int eventId, int studentId)
    {
        return this.waitlistDAO.addWaitlist(eventId, studentId);
    }

    public List<IWaitlist> getWaitlistForStudent(int studentId)
    {
        return this.waitlistDAO.getWaitlistForStudent(studentId);
    }

    public List<IWaitlist> getWaitlistForEvent(int eventId)
    {
        return this.waitlistDAO.getWaitlistForEvent(eventId);
    }

    public boolean delete(int eventId, int studentId)
    {
        return this.waitlistDAO.deleteWaitlist(eventId, studentId);
    }

}
