package com.eventmanagement.EventManagement.model;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class NotificationTest {
    @Test
    public void getNotifications() throws Exception {
        Notification notification = new Notification();
        notification.setNotificationId(1);
        notification.setEventId(1);
        notification.setMessage("Message");
        try {
            Assertions.assertNotNull(notification);
            Assertions.assertEquals(notification.getNotificationId(), 1);
            Assertions.assertEquals(notification.getEventId(), 1);
            Assertions.assertEquals(notification.getMessage(),"Message");
        } catch (Exception e) {
            Assertions.fail("Test 'UpdateFestivalTest' failed:" + e.getMessage());
        }
    }
}
