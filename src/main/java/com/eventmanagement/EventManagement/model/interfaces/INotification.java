package com.eventmanagement.EventManagement.model.interfaces;

import com.eventmanagement.EventManagement.model.ReceipientType;

import java.util.List;

public interface INotification
{
    public void sendNotification(ReceipientType receipientType, Integer eventId, String message);
    public List<INotification> getNotificationForStudent(Integer studentId);
    public void sendNotification(Integer studentId, String message, Integer eventId);
}
