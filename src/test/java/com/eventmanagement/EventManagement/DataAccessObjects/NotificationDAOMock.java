package com.eventmanagement.EventManagement.DataAccessObjects;

import com.eventmanagement.EventManagement.DataAccessObjects.interfaces.INotificationDAO;
import com.eventmanagement.EventManagement.model.Notification;
import com.eventmanagement.EventManagement.model.ReceipientType;
import com.eventmanagement.EventManagement.model.interfaces.INotification;

import java.util.ArrayList;
import java.util.List;

public class NotificationDAOMock implements INotificationDAO {
    @Override
    public void sendNotification(List<Integer> studentIdList, String message, Integer eventId) {

    }

    @Override
    public List<INotification> getNotifications(Integer studentId) {
        if(studentId.equals(1)) {
            List<INotification> notifications = new ArrayList<INotification>();
            Notification notification = new Notification();
            notification.sendNotification(ReceipientType.Waitlist, 1, "Message");
            notification.setStudentId(1);
            notification.setEventId(1);
            notification.setEventName("Event 1");
            notifications.add((INotification) notification);
            return notifications;
        } else {
            return null;
        }
    }
}
