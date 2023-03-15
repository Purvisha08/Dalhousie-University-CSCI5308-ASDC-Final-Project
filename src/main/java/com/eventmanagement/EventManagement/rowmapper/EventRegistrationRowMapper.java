package com.eventmanagement.EventManagement.rowmapper;

import com.eventmanagement.EventManagement.model.EventRegistration;
import com.eventmanagement.EventManagement.model.interfaces.IEventRegistration;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EventRegistrationRowMapper implements RowMapper<IEventRegistration>
{
    @Override
    public IEventRegistration mapRow(ResultSet rs, int rowNum) throws SQLException {
        EventRegistration eventRegistration = new EventRegistration();
        eventRegistration.setRegistrationId(rs.getInt("registrationId"));
        eventRegistration.setStudentId(rs.getInt("studentId"));
        eventRegistration.setEventId(rs.getInt("eventId"));
        eventRegistration.setRating(rs.getInt("rating"));
        eventRegistration.setRegistrationDate(rs.getDate("registrationDate"));
        eventRegistration.setStatus(rs.getString("status"));
        eventRegistration.setEventName(rs.getString("eventName"));
        eventRegistration.setFinalPrice(rs.getDouble("amount"));
        eventRegistration.setStudentName(rs.getString("studentName"));
        eventRegistration.setEventStartDate(rs.getDate("eventDate"));
        return eventRegistration;
    }
}
