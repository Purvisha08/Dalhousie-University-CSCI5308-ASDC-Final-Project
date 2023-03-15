package com.eventmanagement.EventManagement.model.factory;

import com.eventmanagement.EventManagement.model.Notification;
import com.eventmanagement.EventManagement.model.interfaces.INotification;
import com.eventmanagement.EventManagement.model.interfaces.INotificationFactory;

public class NotificationFactory implements INotificationFactory {

    private static NotificationFactory singleInstance;

    private NotificationFactory()
    {

    }

    public static NotificationFactory instance()
    {
        if(singleInstance == null)
        {
            singleInstance = new NotificationFactory();
        }
        return singleInstance;
    }

    @Override
    public INotification makeNotification() {
        return new Notification();
    }
}
