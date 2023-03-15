package com.eventmanagement.EventManagement.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

import com.eventmanagement.EventManagement.model.Event;
import com.eventmanagement.EventManagement.model.factory.EventFactory;

public class EventRowMapper implements RowMapper<Event> {
    @Override
    public Event mapRow(ResultSet rs, int rowNum) throws SQLException {

        Event event = EventFactory.instance().makeEvent();
        event.setId(rs.getInt("ID"));
        event.setName(rs.getString("NAME"));
        event.setDescription(rs.getString("description"));
        event.setCategory(rs.getString("category"));
        event.setVenue(rs.getString("venue"));
        event.setCompleted(rs.getBoolean("completed"));
        event.setPrice(rs.getInt("price"));
        event.setCapacity(rs.getInt("capacity"));
        event.setFestivalId(rs.getInt("festival_id"));
        event.setWinner(rs.getInt("winner"));
        event.setFirstRunnerUp(rs.getInt("firstRunnerUp"));
        event.setSecondRunnerUp(rs.getInt("secondRunnerUp"));
        event.setRegisterCount(rs.getInt("registerCount"));
        event.setWaitListCount(rs.getInt("waitListCount"));
        event.setRating(rs.getDouble("rating"));
        if(rs.getTimestamp("startDate") != null) {
            event.setStartDate(rs.getDate("startDate"));
        } else {
            event.setStartDate(new Date());
        }

        if(rs.getTimestamp("endDate") != null) {
            event.setEndDate(rs.getDate("endDate"));
        } else {
            event.setEndDate(new Date());
        }
        return event;

}
}
