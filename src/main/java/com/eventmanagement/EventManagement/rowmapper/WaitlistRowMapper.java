package com.eventmanagement.EventManagement.rowmapper;

import com.eventmanagement.EventManagement.model.Waitlist;
import com.eventmanagement.EventManagement.model.interfaces.IWaitlist;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WaitlistRowMapper implements RowMapper<IWaitlist> {
    @Override
    public IWaitlist mapRow(ResultSet rs, int rowNum) throws SQLException
    {
        Waitlist waitlist = new Waitlist();
        waitlist.setCreatedAt(rs.getDate("createdAt"));
        waitlist.setEventStartDate(rs.getDate("eventStartDate"));
        waitlist.setEventId(rs.getInt("eventId"));
        waitlist.setStudentId(rs.getInt("studentId"));
        waitlist.setEventCapacity(rs.getInt("eventCapacity"));
        waitlist.setEventRegisterCount(rs.getInt("eventRegisterCount"));
        waitlist.setStudentName(rs.getString("studentName"));
        waitlist.setEventName(rs.getString("eventName"));
        return waitlist;
    }
}
