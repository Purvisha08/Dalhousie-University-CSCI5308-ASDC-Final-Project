package com.eventmanagement.EventManagement.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import org.springframework.jdbc.core.RowMapper;
import com.eventmanagement.EventManagement.model.Festival;

public class FestivalRowMapper implements RowMapper<Festival> {
    @Override
    public Festival mapRow(ResultSet rs, int rowNum) throws SQLException {

        Festival festival = new Festival();
        festival.setId(rs.getInt("festival_id"));
        festival.setName(rs.getString("name"));
        festival.setDescription(rs.getString("description"));
        festival.setHostId(rs.getInt("host_id"));
        festival.setUniversity(rs.getString("university"));
        festival.setEventCount(rs.getInt("event_count"));
        festival.setTotalParticipants(rs.getInt("total_participants"));
        festival.setRating(rs.getDouble("rating"));

        if(rs.getTimestamp("startDate") != null) {
            festival.setStartDate(rs.getDate("startDate"));
        } else {
            festival.setStartDate(new Date());
        }

        if(rs.getTimestamp("endDate") != null) {
            festival.setEndDate(rs.getDate("endDate"));
        } else {
            festival.setEndDate(new Date());
        }
        return festival;
    }
}
