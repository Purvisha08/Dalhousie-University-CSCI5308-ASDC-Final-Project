package com.eventmanagement.EventManagement.rowmapper;

import com.eventmanagement.EventManagement.model.Notification;
import com.eventmanagement.EventManagement.model.interfaces.INotification;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NotificationRowMapper implements RowMapper<INotification> {
    @Override
    public INotification mapRow(ResultSet rs, int rowNum) throws SQLException
    {
        Notification notification = new Notification();
        notification.setEventName(rs.getString("eventName"));
        notification.setMessage(rs.getString("message"));
        notification.setEventId(rs.getInt("event_id"));
        notification.setNotifiedTime(rs.getTimestamp("notified_time").toLocalDateTime());
        notification.setStudentId(rs.getInt("student_id"));
        return notification;
    }
}
