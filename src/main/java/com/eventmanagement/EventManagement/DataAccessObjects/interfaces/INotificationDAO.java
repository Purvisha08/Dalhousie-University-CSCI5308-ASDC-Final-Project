package com.eventmanagement.EventManagement.DataAccessObjects.interfaces;

import com.eventmanagement.EventManagement.model.interfaces.INotification;
import java.util.List;

public interface INotificationDAO
{
    public void sendNotification(List<Integer> studentIdList, String message, Integer eventId);
    public  List<INotification> getNotifications(Integer studentId);

}
