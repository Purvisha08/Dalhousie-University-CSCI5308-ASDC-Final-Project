package com.eventmanagement.EventManagement.DataAccessObjects;

import com.eventmanagement.EventManagement.DataAccessObjects.interfaces.INotificationDAO;
import com.eventmanagement.EventManagement.model.DatabaseDataSource;
import com.eventmanagement.EventManagement.model.interfaces.INotification;
import com.eventmanagement.EventManagement.rowmapper.NotificationRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

@Repository
public class NotificationDAO implements INotificationDAO {
    public JdbcTemplate jdbcTemplate;

    public NotificationDAO() {
        jdbcTemplate = new JdbcTemplate(DatabaseDataSource.instance().getDriverManagerDataSource());
    }

    @Override
    public void sendNotification(List<Integer> studentIdList, String message, Integer eventId) {
        Object currentDateTime = new java.sql.Timestamp(new Date().getTime());
        String sql = "CALL usp_notification_insert(?,  ?,  ?, ?)";
        ListIterator<Integer> iterator = studentIdList.listIterator();
        while (iterator.hasNext()) {
            Integer studentId = iterator.next();
            jdbcTemplate.update(sql, studentId, message, currentDateTime, eventId);
        }
    }

    @Override
    public List<INotification> getNotifications(Integer studentId) {
        String sql = "CALL usp_notification_select_by_student(?)";
        return jdbcTemplate.query(sql, new NotificationRowMapper(),studentId);
    }
}
